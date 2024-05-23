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
 * @title: deviceData实体
 * @description: device_data实体
 * @author: author
 * @date: 2024-05-22 16:52:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("device_data")
@SuppressWarnings("serial")
public class DeviceData extends BaseEntity {

	/**
	 *告警规则ID，自增主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	/**
	 *设备名称
	 */
	@TableField(value = "device_name")
	private String deviceName;
	/**
	 *设备ID
	 */
	@TableField(value = "iot_id")
	private String ioid;
	/**
	 *备注名称
	 */
	@TableField(value = "note_name")
	private String noteName;
	/**
	 *所属产品的key
	 */
	@TableField(value = "product_id")
	private String producid;
	/**
	 *产品名称
	 */
	@TableField(value = "product_name")
	private String producname;
	/**
	 *功能名称
	 */
	@TableField(value = "function_name")
	private String functionName;
	/**
	 *接入位置
	 */
	@TableField(value = "access_location")
	private String accessLocation;
	/**
	 *数据值
	 */
	@TableField(value = "data_value")
	private String dataValue;
	/**
	 *报警时间
	 */
	@TableField(value = "alarm_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date alarmTime;
	/**
	 *处理结果
	 */
	@TableField(value = "processing_result")
	private String processingResult;
	/**
	 *处理人
	 */
	@TableField(value = "processor")
	private String processor;
	/**
	 *处理时间
	 */
	@TableField(value = "processing_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date processingTime;
	/**
	 *状态 0 正常 1 异常 2待处理 3已处理
	 */
	@TableField(value = "status")
	private String status;
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