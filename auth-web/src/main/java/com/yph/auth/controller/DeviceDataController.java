package com.yph.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

import com.yph.auth.service.IDeviceDataService;
import com.yph.auth.entity.DeviceData;


/**
 *
 * @package com.yph.auth.controller
 * @title: deviceData控制器
 * @description: device_data控制器
 * @author: author
 * @date: 2024-05-22 16:52:25
 */
@Api(description = "device_data接口")
@RestController
@RequestMapping("deviceData")
public class DeviceDataController extends BaseController<DeviceData> {

	@Autowired
	private IDeviceDataService deviceDataService;

	/**  
	 *   
	 * @author author
	 * @description  device_data列表页面跳转
	 * @date 2024-05-22 16:52:25
	 * @param [model, request, response]  
	 * @return org.springframework.web.servlet.ModelAndView  
	 */ 
	@GetMapping("list")
	public ModelAndView list(Model model, HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("deviceData/list");
	}

	/**  
	 *   
	 * @author author
	 * @description  查询device_data列表数据
	 * @date 2024-05-22 16:52:25
	 * @param [request, response, page]  
	 * @return com.dyk.common.controller.ApiResult  
	 */ 
	@ApiOperation(value = "查询device_data" ,  notes="查询device_data列表")
	@GetMapping(value = "ajaxList")
	public ApiResult ajaxList(HttpServletRequest request, HttpServletResponse response, Page<DeviceData> page) {
		
		Map<String,Object> queryMap = getQueryParam(request);
        IPage<DeviceData> pageResult = deviceDataService.selectAll(page, queryMap);
        return new ApiResult(pageResult);
	}

	/**  
	 *   
	 * @author author
	 * @description  新增device_data表单初始化请求
	 * @date 2024-05-22 16:52:25
	 * @param [model, request, response]  
	 * @return com.dyk.common.controller.ApiResult  
	 */ 
	@GetMapping(value = "add")
	public ApiResult add(Model model, HttpServletRequest request, HttpServletResponse response) {
		return new ApiResult(ApiResult.SUCCESS,"",null);
	}

	/**  
	 *   
	 * @author author
	 * @description  新增device_data提交
	 * @date 2024-05-22 16:52:25
	 * @param [entity, result, request, response]  
	 * @return com.dyk.common.controller.ApiResult  
	 */ 
	@ApiOperation(value = "新增device_data" ,  notes="新增device_data记录")
	@PostMapping("save")
	public ApiResult save(DeviceData entity,HttpServletRequest request, HttpServletResponse response) {

		deviceDataService.save(entity);
		return new ApiResult(ApiResult.SUCCESS,"添加成功");
	}

    /**  
	 *   
	 * @author author
	 * @description  查询device_data详情
	 * @date 2024-05-22 16:52:25
	 * @param [id, model, request, response]  
	 * @return com.dyk.common.controller.ApiResult  
	 */ 
	@ApiOperation(value = "查询device_data详情" ,  notes="查询device_data详情数据")
	@GetMapping(value = "{id}/view")
	public ApiResult view(@PathVariable("id") String id, Model model, HttpServletRequest request,
							   HttpServletResponse response) {
		
        Map<String,Object> map = new HashMap<>();
		DeviceData deviceData = deviceDataService.getById(id);
		map.put("deviceData",deviceData);

		return new ApiResult(ApiResult.SUCCESS,"",map);
	}

    /**  
	 *   
	 * @author author
	 * @description  查询device_data详情
	 * @date 2024-05-22 16:52:25
	 * @param [entity, result, request, response]  
	 * @return com.dyk.common.controller.ApiResult  
	 */ 
	@ApiOperation(value = "更新device_data" ,  notes="更新device_data数据")
	@PostMapping("{id}/update")
	public ApiResult update(DeviceData entity, BindingResult result,
						   HttpServletRequest request, HttpServletResponse response) {

		deviceDataService.updateById(entity);
		return new ApiResult(ApiResult.SUCCESS,"更新成功");
	}

    /**  
	 *   
	 * @author author
	 * @description  删除device_data
	 * @date 2024-05-22 16:52:25
	 * @param [id]  
	 * @return com.dyk.common.controller.ApiResult  
	 */ 
	@ApiOperation(value = "删除device_data" ,  notes="删除device_data数据")
	@PostMapping("{id}/delete")
	public ApiResult delete(@PathVariable("id") Integer id) {
		deviceDataService.removeById(id);
		return new ApiResult(ApiResult.SUCCESS,"删除成功");
	}

    /**  
	 *   
	 * @author author
	 * @description  批量删除device_data
	 * @date 2024-05-22 16:52:25
	 * @param [ids]  
	 * @return com.dyk.common.controller.ApiResult  
	 */ 
	@ApiOperation(value = "批量删除device_data" ,  notes="批量删除device_data数据")
	@PostMapping("batch/delete")
	public ApiResult batchDelete(@RequestParam("ids") String[] ids) {
		List<String> idList = java.util.Arrays.asList(ids);
		deviceDataService.removeByIds(idList);
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
    @ApiOperation(value = "导出device_data列表", notes = "导出device_data列表")
    @GetMapping("export")
    public void export(HttpServletRequest request, HttpServletResponse response)
    {
		Map<String,Object> queryMap = getQueryParam(request);
        deviceDataService.export(request, response, queryMap);
    }

}