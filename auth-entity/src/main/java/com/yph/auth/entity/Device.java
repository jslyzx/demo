package com.yph.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yph.auth.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @package com.yph.auth.entity
 * @title: device实体
 * @description: device实体
 * @author: zhaoxiang
 * @date: 2024-05-23 08:30:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("device")
@SuppressWarnings("serial")
public class Device extends BaseEntity {

    /**
     *id
     */
    @TableId(value = "id", type = IdType.AUTO)
	private Long id;
    /**
     *设备ID
     */
    @TableField(value = "device_id")
	private String deviceId;
    /**
     *绑定位置
     */
    @TableField(value = "binding_location")
	private String bindingLocation;
    /**
     *位置类型 0 老人 1位置
     */
    @TableField(value = "location_type")
	private Integer locationType;
    /**
     *物理位置类型 0楼层 1房间 2床位
     */
    @TableField(value = "physical_location_type")
	private Integer physicalLocationType;
    /**
     *设备名称
     */
    @TableField(value = "device_name")
	private String deviceName;
    /**
     *备注名称
     */
    @TableField(value = "note_name")
	private String noteName;
    /**
     *产品key
     */
    @TableField(value = "product_id")
	private String producid;
    /**
     *产品名称
     */
    @TableField(value = "produce_name")
	private String produceName;
    /**
     *位置备注
     */
    @TableField(value = "device_description")
	private String deviceDescription;
    /**
     *创建时间
     */
    @TableField(value = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
    /**
     *更新时间
     */
    @TableField(value = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
    /**
     *是否删除
     */
    @TableField(value = "is_deleted")
	private Short isDeleted;
    /**
     *创建人id
     */
    @TableField(value = "create_by")
	private Long createBy;
    /**
     *更新人id
     */
    @TableField(value = "update_by")
	private Long updateBy;
    /**
     *备注
     */
    @TableField(value = "remark")
	private String remark;
	
}