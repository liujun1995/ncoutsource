package com.nc.entity.wxwb;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("OUT_CONTRACT_INFO")
public class OutContractInfo extends Model<OutContractInfo> {

	@TableId
	private String contractId;

	@TableField("APPROVAL_ID")
	private String approvalId;

	@TableField("APPROVAL_BILLNUMBER")
	private String approvalBillnumber;

	@TableField("PROJECT_NAME")
	private String projectName;

	@TableField("CONTRACT_NUMBER")
	private String contractNumber;

	@TableField("OUTSOURCING_TYPE")
	private String outsourcingType;

	@TableField("CONTRACT_AMOUNT")
	private String contractAmount;

	@TableField("OUT_SUB_TYPE")
	private Integer outSubType;

	@TableField("PAID_AMOUNT")
	private String paidAmount;

	@TableField("SUPPLIER_ID")
	private String supplierId;

	@TableField("SUPPLIER_CODE")
	private String supplierCode;

	@TableField("SUPPLIER_NAME")
	private String supplierName;

	@TableField("REMARK")
	private String remark;

	@TableField("CREATE_TIME")
	private Date createTime;

	@TableField("STATE")
	private Integer state;

	@TableField("BUY_SOURCE")
	private Integer buySource;

	@TableField("IS_USE")
	private Integer isUse;

	@TableField("CONTRACTLOG_ID")
	private String contractlogId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Serializable pkVal() {
		return contractId;
	}

}
