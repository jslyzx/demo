package com.yph.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yph.auth.entity.Device;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @package com.yph.auth.service
* @title: device服务接口
* @description: device服务接口
* @author: zhaoxiang
* @date: 2024-05-23 08:30:07
*/
public interface IDeviceService extends IService<Device> {

    /**
     * 导出excel
     *
     * @author zhaoxiang
     * @date 2024-05-23 08:30:07
     * @param [wrapper]
     * @return void
     */
    void export(HttpServletRequest request, HttpServletResponse response,Map<String,Object> queryMap);
    /**  
     * 分页查询
     *   
     * @author zhaoxiang
     * @date 2024-05-23 08:30:07
     * @param [page,queryMap]
     * @return com.baomidou.mybatisplus.core.metadata.IPage<Device>  
     */ 
    IPage<Device> selectAll(Page<Device> page, Map<String,Object> queryMap);
}