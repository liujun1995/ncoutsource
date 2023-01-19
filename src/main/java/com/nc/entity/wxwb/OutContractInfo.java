package com.nc.entity.wxwb;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
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
	private String approvalId;
	private String approvalBillnumber;
	private String projectName;
	private String contractNumber;
	private String outsourcingType;
	private String contractAmount;
	private Integer outSubType;
	private String paidAmount;
	private String supplierId;
	private String supplierCode;
	private String supplierName;
	private String remark;
	private Date createTime;
	private Integer state;
	private Integer buySource;
	private Integer isUse;
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
