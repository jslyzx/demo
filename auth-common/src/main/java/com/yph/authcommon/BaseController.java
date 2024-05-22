package com.yph.authcommon;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yph.authcommon.JsonUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description of this
 *
 * @param
 * @author allen
 * @description
 * @date 2019/2/27
 * @return
 */
public class BaseController<T> {
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());


    /**
     * 获取request参数
     *
     * @param request
     * @return
     * @author Allen
     * @since 2016年11月23日
     */
    protected Map<String, Object> getQueryParam(HttpServletRequest request)
    {
        Map<String, Object> queryMap = new HashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements())
        {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1)
            {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0)
                {
                    queryMap.put(paramName, paramValue.trim());
                }
            }
        }
        return queryMap;
    }

    /**
     * 回填查询参数至页面
     *
     * @param request
     * @param model
     * @author Allen
     * @since 2016年11月23日
     */
    @ModelAttribute
    public void setQueryParam(HttpServletRequest request, ModelMap model)
    {
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements())
        {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1)
            {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0)
                {
                    model.addAttribute(paramName, paramValue.trim());
                }
            }
        }
    }

    /**
     * @param wrapper
     */
    public Integer setWrapper(HttpServletRequest request, QueryWrapper wrapper, Class<T> tClass) {
        Map<String, Object> queryMap = getQueryParam(request);
        Integer queryType = 0;
        for (Map.Entry<String, Object> entry : queryMap.entrySet()) {
            if (entry.getKey().equals("queryType")) {
                queryType = Integer.valueOf(entry.getValue().toString());
            }
            try {
                Field column = tClass.getDeclaredField(entry.getKey());
                if (column != null) {
                    if (column.getName().equals("id")) {
                        wrapper.eq("id", entry.getValue());
                    } else {
                        String sqlField = column.getAnnotation(TableField.class).value();
                        if (column.getType().equals(String.class)) {
                            wrapper.like(sqlField, entry.getValue());
                        }
                        if (column.getType().equals(Integer.class)) {
                            wrapper.eq(sqlField, entry.getValue());
                        }
                    }
                }
            } catch (NoSuchFieldException e) {
                continue;
            }

        }
        return queryType;
    }

    /**
     * 共享的验证规则 验证失败返回true
     *
     * @param entity
     * @param result
     * @return
     */
    protected boolean hasError(T entity, BindingResult result) {
        Assert.notNull(entity);
        return result.hasErrors();
    }


    /**
     * @param result
     * @return
     * @title: errorMsg
     * @description: 错误信息组装
     * @return: String
     */
    protected String errorMsg(BindingResult result) {
        String errorMsg = "";
        if (result.getErrorCount() > 0) {
            List<ObjectError> objectErrorList = result.getAllErrors();
            for (ObjectError objectError : objectErrorList) {
                String message = objectError.getCode() + ", " + objectError.getDefaultMessage() + ", " + objectError.getArguments();
                if (!StringUtils.isEmpty(message)) {
                    errorMsg = errorMsg + message + "<br />";
                }
            }
        }
        return errorMsg;
    }

    public Map<String, Object> upload(@RequestParam MultipartFile file,
                                      String uploadPicURL) throws IllegalStateException, IOException {
        HttpHeaders headers = new HttpHeaders();
        String originalFilename = file.getOriginalFilename();
        //判断是否为IE浏览器的文件名，IE浏览器下文件名会带有盘符信息
        // Check for Unix-style path
        int unixSep = originalFilename.lastIndexOf('/');
        // Check for Windows-style path
        int winSep = originalFilename.lastIndexOf('\\');
        // Cut off at latest possible point
        int pos = (winSep > unixSep ? winSep : unixSep);
        if (pos != -1) {
            // Any sort of path separator found...
            originalFilename = originalFilename.substring(pos + 1);
        }
        if(originalFilename.contains(".doc")||originalFilename.contains(".docx")){
            String tempFileName = UUID.randomUUID() + "_" + originalFilename;
            String tempFilePath = SystemUtils.USER_DIR + File.separator
                    + tempFileName;
            File tempFile = new File(tempFilePath);
            ResponseEntity<String> responseEntity =null;
            try{
            file.transferTo(tempFile);
            FileSystemResource fileSystemResource = new FileSystemResource(
                    tempFilePath);
            MediaType type = MediaType
                    .parseMediaType("multipart/form-data;charset=UTF-8");
            headers.setContentType(type);
            String cd = "filename=\"" + file.getOriginalFilename() + "\"";
            headers.add("Content-Disposition", cd);
            MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
            form.add("file", fileSystemResource);
            form.add("fileName", URLEncoder.encode(originalFilename, "UTF-8"));
            HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form,
                    headers);
            RestTemplate restTemplate = new RestTemplate();
            responseEntity = restTemplate
                    .exchange(uploadPicURL, HttpMethod.POST, files, String.class);
            tempFile.delete();
            }catch (Exception e){
                LOGGER.info("上传文件异常");
            }finally {
                tempFile.delete();
            }
            return JsonUtil.getMap4Json(responseEntity.getBody());

        }else {
            String tempFileName = UUID.randomUUID() + "_" + originalFilename;
            String tempFilePath = SystemUtils.USER_DIR + File.separator
                    + tempFileName;
            File tempFile = new File(tempFilePath);
            File tempFile2 =null;
            ResponseEntity<String> responseEntity =null;
            try {
                    file.transferTo(tempFile);
                    // 根据服务器里的图片对图片进行比例压缩
                    String thumbnailFilePathName = uploadFileAndCreateThumbnail(tempFilePath);
                    tempFile2 = new File(thumbnailFilePathName);
                    FileSystemResource fileSystemResource = new FileSystemResource(
                            thumbnailFilePathName);
                    MediaType type = MediaType
                            .parseMediaType("multipart/form-data;charset=UTF-8");
                    headers.setContentType(type);
                    String cd = "filename=\"" + file.getOriginalFilename() + "\"";
                    headers.add("Content-Disposition", cd);
                    MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
                    form.add("file", fileSystemResource);
                    form.add("fileName", URLEncoder.encode(originalFilename, "UTF-8"));
                    HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form,
                            headers);
                    RestTemplate restTemplate = new RestTemplate();
                    responseEntity = restTemplate
                            .exchange(uploadPicURL, HttpMethod.POST, files, String.class);

                    tempFile.delete();
                    tempFile2.delete();
                    return JsonUtil.getMap4Json(responseEntity.getBody());
                }catch (Exception e){
                  LOGGER.info("上传图片异常",e);
                }finally {
                tempFile.delete();
                tempFile2.delete();
                }

            }
            return JsonUtil.getMap4Json(null);
    }

    public String  uploadFileAndCreateThumbnail(String thumbnailPathName) {
        //拼接后台文件名称
        File file = new File(thumbnailPathName);
        if (file == null || !file.exists()) {
            return thumbnailPathName;
        }
        long size = file.length();
        double scale = 1.0d;
        if (size >= 500 * 1024 ) {
            scale = (500 * 1024f) / size;
            System.out.println(scale);
        }else{
            return  thumbnailPathName;
        }
        //拼接文件路劲
        String thumbnailFilePathName = thumbnailPathName.substring(0, thumbnailPathName.lastIndexOf(".")) + "_min.jpg";
        try {
            if (size > 500 * 1024) {
        // Thumbnails.of(thumbnailPathName).size(500,500).toFile(thumbnailFilePathName);//变为400*300,遵循原图比例缩或放到400*某个高度
                Thumbnails.of(thumbnailPathName).scale(1f).outputQuality(scale).outputFormat("jpg").toFile(thumbnailFilePathName);// 图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
            }
        } catch (Exception e1) {
            LOGGER.info("图片压缩异常",e1);
        }finally {
        }
        return thumbnailFilePathName ;
    }

    public void paramsKeyword(Map<String,Object> params){
        if (null!=params.get("keyword")){
            String keyword = String.valueOf(params.get("keyword"));
            //如果不是B开头或包含中文字符则查询患者姓名
            if (!String.valueOf(keyword.charAt(0)).equals("B")){
                Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
                Matcher m = p.matcher(keyword);
                if (m.find()){
                    params.remove("keyword");
                    params.put("patientName",keyword);
                }
            }
        }
    }
}
