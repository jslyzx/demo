package com.yph.auth.mapper;

import com.yph.auth.entity.Device;
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
 * @title: device数据库控制层接口
 * @description: device数据库控制层接口
 * @author: zhaoxiang
 * @date: 2024-05-23 08:30:07
 */
@Mapper
@Component
public interface DeviceMapper extends BaseMapper<Device> {

    /**
	  *
	  * @author zhaoxiang
	  * @description  查询device列表
	  * @date 2024-05-23 08:30:07
	  * @param [queryMap]
	  * @return List<Device>
	  */
	 List<Device> selectAll(@Param("queryMap") Map<String, Object> queryMap);
	/**  
	 *   
	 * @author zhaoxiang
	 * @description  分页查询device列表
	 * @date 2024-05-23 08:30:07
	 * @param [page, queryMap]  
	 * @return com.baomidou.mybatisplus.core.metadata.IPage<Device>
	 */ 
	IPage<Device> selectAll(Page<Device> page, @Param("queryMap") Map<String,Object> queryMap);
}