package com.yph.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yph.authcommon.ApiResult;
import com.yph.authcommon.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.yph.auth.service.IDeviceService;
import com.yph.auth.entity.Device;


/**
 *
 * @package com.yph.auth.controller
 * @title: device控制器
 * @description: device控制器
 * @author: zhaoxiang
 * @date: 2024-05-23 08:30:07
 */
@Api(description = "device接口")
@RestController
@RequestMapping("device")
public class DeviceController extends BaseController<Device> {

	@Autowired
	private IDeviceService deviceService;

	/**  
	 *   
	 * @author zhaoxiang
	 * @description  device列表页面跳转
	 * @date 2024-05-23 08:30:07
	 * @param [model, request, response]  
	 * @return org.springframework.web.servlet.ModelAndView  
	 */ 
	@GetMapping("list")
	public ModelAndView list(Model model, HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("device/list");
	}

	/**  
	 *   
	 * @author zhaoxiang
	 * @description  查询device列表数据
	 * @date 2024-05-23 08:30:07
	 * @param [request, response, page]  
	 * @return com.dyk.common.controller.ApiResult  
	 */ 
	@ApiOperation(value = "查询device" ,  notes="查询device列表")
	@GetMapping(value = "ajaxList")
	public ApiResult ajaxList(HttpServletRequest request, HttpServletResponse response, Page<Device> page) {
		
		Map<String,Object> queryMap = getQueryParam(request);
        IPage<Device> pageResult = deviceService.selectAll(page, queryMap);
        return new ApiResult(pageResult);
	}

	/**  
	 *   
	 * @author zhaoxiang
	 * @description  新增device表单初始化请求
	 * @date 2024-05-23 08:30:07
	 * @param [model, request, response]  
	 * @return com.dyk.common.controller.ApiResult  
	 */ 
	@GetMapping(value = "add")
	public ApiResult add(Model model, HttpServletRequest request, HttpServletResponse response) {
		return new ApiResult(ApiResult.SUCCESS,"",null);
	}

	/**  
	 *   
	 * @author zhaoxiang
	 * @description  新增device提交
	 * @date 2024-05-23 08:30:07
	 * @param [entity, result, request, response]  
	 * @return com.dyk.common.controller.ApiResult  
	 */ 
	@ApiOperation(value = "新增device" ,  notes="新增device记录")
	@PostMapping("save")
	public ApiResult save(Device entity,HttpServletRequest request, HttpServletResponse response) {

		deviceService.save(entity);
		return new ApiResult(ApiResult.SUCCESS,"添加成功");
	}

    /**  
	 *   
	 * @author zhaoxiang
	 * @description  查询device详情
	 * @date 2024-05-23 08:30:07
	 * @param [id, model, request, response]  
	 * @return com.dyk.common.controller.ApiResult  
	 */ 
	@ApiOperation(value = "查询device详情" ,  notes="查询device详情数据")
	@GetMapping(value = "{id}/view")
	public ApiResult view(@PathVariable("id") String id, Model model, HttpServletRequest request,
							   HttpServletResponse response) {
		
        Map<String,Object> map = new HashMap<>();
		Device device = deviceService.getById(id);
		map.put("device",device);

		return new ApiResult(ApiResult.SUCCESS,"",map);
	}

    /**  
	 *   
	 * @author zhaoxiang
	 * @description  查询device详情
	 * @date 2024-05-23 08:30:07
	 * @param [entity, result, request, response]  
	 * @return com.dyk.common.controller.ApiResult  
	 */ 
	@ApiOperation(value = "更新device" ,  notes="更新device数据")
	@PostMapping("{id}/update")
	public ApiResult update(Device entity, BindingResult result,
						   HttpServletRequest request, HttpServletResponse response) {

		deviceService.updateById(entity);
		return new ApiResult(ApiResult.SUCCESS,"更新成功");
	}

    /**  
	 *   
	 * @author zhaoxiang
	 * @description  删除device
	 * @date 2024-05-23 08:30:07
	 * @param [id]  
	 * @return com.dyk.common.controller.ApiResult  
	 */ 
	@ApiOperation(value = "删除device" ,  notes="删除device数据")
	@PostMapping("{id}/delete")
	public ApiResult delete(@PathVariable("id") Integer id) {
		deviceService.removeById(id);
		return new ApiResult(ApiResult.SUCCESS,"删除成功");
	}

    /**  
	 *   
	 * @author zhaoxiang
	 * @description  批量删除device
	 * @date 2024-05-23 08:30:07
	 * @param [ids]  
	 * @return com.dyk.common.controller.ApiResult  
	 */ 
	@ApiOperation(value = "批量删除device" ,  notes="批量删除device数据")
	@PostMapping("batch/delete")
	public ApiResult batchDelete(@RequestParam("ids") String[] ids) {
		List<String> idList = java.util.Arrays.asList(ids);
		deviceService.removeByIds(idList);
		return new ApiResult(ApiResult.SUCCESS,"删除成功");
	} 
    /**
     * 导出excel
     *
     * @param [request, response]
     * @return void
     * @author pengqiang
     * @date 2019/3/11
     */
    @ApiOperation(value = "导出device列表", notes = "导出device列表")
    @GetMapping("export")
    public void export(HttpServletRequest request, HttpServletResponse response)
    {
		Map<String,Object> queryMap = getQueryParam(request);
        deviceService.export(request, response, queryMap);
    }

}