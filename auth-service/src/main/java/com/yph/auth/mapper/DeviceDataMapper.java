package com.yph.auth.mapper;

import com.yph.auth.entity.DeviceData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.List;


 /**
 * @package com.yph.auth.mapper
 * @title: deviceData数据库控制层接口
 * @description: device_data数据库控制层接口
 * @author: author
 * @date: 2024-05-22 16:52:25
 */
@Mapper
@Component
public interface DeviceDataMapper extends BaseMapper<DeviceData> {

    /**
	  *
	  * @author author
	  * @description  查询device_data列表
	  * @date 2024-05-22 16:52:25
	  * @param [queryMap]
	  * @return List<DeviceData>
	  */
	 List<DeviceData> selectAll(@Param("queryMap") Map<String, Object> queryMap);
	/**  
	 *   
	 * @author author
	 * @description  分页查询device_data列表
	 * @date 2024-05-22 16:52:25
	 * @param [page, queryMap]  
	 * @return com.baomidou.mybatisplus.core.metadata.IPage<DeviceData>
	 */ 
	IPage<DeviceData> selectAll(Page<DeviceData> page, @Param("queryMap") Map<String,Object> queryMap);
}