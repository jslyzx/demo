package com.yph.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yph.auth.entity.DeviceData;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @package com.yph.auth.service
* @title: deviceData服务接口
* @description: device_data服务接口
* @author: author
* @date: 2024-05-22 16:52:25
*/
public interface IDeviceDataService extends IService<DeviceData> {

    /**
     * 导出excel
     *
     * @author author
     * @date 2024-05-22 16:52:25
     * @param [wrapper]
     * @return void
     */
    void export(HttpServletRequest request, HttpServletResponse response,Map<String,Object> queryMap);
    /**  
     * 分页查询
     *   
     * @author author
     * @date 2024-05-22 16:52:25
     * @param [page,queryMap]
     * @return com.baomidou.mybatisplus.core.metadata.IPage<DeviceData>  
     */ 
    IPage<DeviceData> selectAll(Page<DeviceData> page, Map<String,Object> queryMap);
}