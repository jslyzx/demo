package com.yph.auth.service.impl;

import com.yph.auth.service.IDeviceDataService;
import com.yph.auth.entity.DeviceData;
import com.yph.auth.mapper.DeviceDataMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yph.authcommon.ExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
* @package com.yph.auth.service.impl
* @title: deviceData服务实现
* @description: device_data服务实现
* @author: author
* @date: 2024-05-22 16:52:25
*/
@Transactional
@Service("devicedataService")
public class DeviceDataServiceImpl  extends ServiceImpl<DeviceDataMapper, DeviceData> implements  IDeviceDataService {

	protected Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
    private DeviceDataMapper deviceDataMapper;

    @Override
    public void export(HttpServletRequest request, HttpServletResponse response, Map<String,Object> queryMap)
    {
        try
        {
            //查询待导出数据
            List<DeviceData> resultList = baseMapper.selectAll(queryMap);

            //封装导出参数
            String excelName = "deviceData数据导出";//导出的EXCEL名字
            String sheetName = "deviceData数据导出";//导出的SHEET名字

            Class cls = DeviceData.class;
            Field[] fields = cls.getDeclaredFields();

            String[] headers = new String[fields.length];//导出的表格的表头
            String[] ds_titles = new String[fields.length];//导出的数据 map.get(key) 对应的 key
            int[] ds_format = new int[fields.length];//导出数据的样式

            for (int i = 0; i < fields.length; i++)
            {
                Field f = fields[i];
                headers[i] = f.getName();
                ds_titles[i] = f.getName();
                ds_format[i] = ExcelUtils.DS_FORMAT_STRING_CENTER;
            }

            List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
            for (DeviceData obj : resultList)
            {
                //实体对象转map对象
                Map<String, Object> map = BeanMap.create(obj);
                data.add(map);
            }
            ExcelUtils.export(excelName, sheetName, headers, ds_titles, ds_format, null, data, request, response);
        } catch (IOException e)
        {
            LOGGER.error("导出excel出错", e);
        }
    }
    
    @Override
    public IPage<DeviceData> selectAll(Page<DeviceData> page, Map<String, Object> queryMap) {
        return deviceDataMapper.selectAll(page,queryMap);
    }
}