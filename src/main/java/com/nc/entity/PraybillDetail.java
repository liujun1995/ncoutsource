package com.nc.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@TableName("NC65.PRAYBILL_DETAIL")
public class PraybillDetail extends Model<PraybillDetail> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 自输入
	 */
	@TableId(type = IdType.INPUT,value = "PK_PRAYBILL_B")
	private String PK_PRAYBILL_B;

	/**
	 * 外包申请单号
	 */
	@TableField(value = "VBILLCODE")
	private String VBILLCODE;

	/**
	 * 外包申请日期
	 */
	@TableField(value = "DBILLDATE")
	private String DBILLDATE;

	/**
	 * 请购类型主键
	 */
	@TableField(value = "CTRANTYPEID")
	private String CTRANTYPEID;

	/**
	 * 请购类型编码
	 */
	@TableField(value = "CTRANTYPECODE")
	private String CTRANTYPECODE;

	/**
	 *人员基本信息 (bd_psndoc)主键
	 * 申请人主键
	 */
	@TableField(value = "PK_PLANPSN")
	private String PK_PLANPSN;

	/**
	 * 员基本信息 (bd_psndoc)主键编码
	 * 申请人编码
	 */
	@TableField(value = "CODE_PLANPSN")
	private String CODE_PLANPSN;

	/**
	 * 需求部门主键
	 */
	@TableField(value = "PK_PLANDEPT")
	private String PK_PLANDEPT;

	/**
	 * 需求部门编码
	 */
	@TableField(value = "CODE_PLANDEPT")
	private String CODE_PLANDEPT;

	/**
	 *单据状态数字枚举
	 */
	@TableField(value = "FBILLSTATUS_NUMBER")
	private String FBILLSTATUS_NUMBER;

	/**
	 *单据状态英文名称
	 */
	@TableField(value = "FBILLSTATUS_ENGNAME")
	private String  FBILLSTATUS_ENGNAME;

	/**
	 *外包类型主键
	 */
	@TableField(value = "PK_VDEF5")
	private String PK_VDEF5;

	/**
	 *外包类型编码
	 */
	@TableField(value = "CODE_VDEF5")
	private String CODE_VDEF5;

	/**
	 * 请购单表头
	 * 订单号
	 */
	@TableField(value = "VDEF7")
	private String VDEF7;

	/**
	 *外包内容
	 */
	@TableField("VMEMO")
	private String VMEMO;

	/**
	 * 备注
	 */
	@TableField("VDEF8")
	private String VDEF8;

	/**
	 *请购单表体 行号
	 */
	@TableField("CROWNO")
	private String CROWNO;

	/**
	 *外包产品主键
	 */
	@TableField("PK_MATERIAL")
	private String PK_MATERIAL;

	/**
	 *外包产品编码
	 */
	@TableField("CODE_MATERIAL")
	private String CODE_MATERIAL;


	/**
	 *外协产品名称
	 */
	@TableField("NAME_MATERIAL")
	private String NAME_MATERIAL;

	/**
	 *版本号
	 */
	@TableField("VERSION_MATERIAL")
	private String VERSION_MATERIAL;

	/**
	 *规格型号
	 */
	@TableField("SPEC_MATERIAL")
	private String SPEC_MATERIAL;

	/**
	 *计量单位主键
	 */
	@TableField("PK_MEASDOC")
	private String PK_MEASDOC;

	/**
	 *计量单位编码
	 */
	@TableField("CODE_MEASDOC")
	private String CODE_MEASDOC;

	/**
	 *计量单位名称
	 */
	@TableField("NAME_MEASDOC")
	private String NAME_MEASDOC;

	/**
	 * 原始缺货数量
	 */
	@TableField("VBDEF9")
	private String VBDEF9;

	/**
	 *数量
	 */
	@TableField("NASTNUM")
	private String NASTNUM;

	/**
	 *含税单价
	 */
	@TableField("NTAXPRICE")
	private String NTAXPRICE;

	/**
	 *价税合计
	 */
	@TableField("NTAXMNY")
	private String NTAXMNY;


	/**
	 *需求日期
	 */
	@TableField("DREQDATE")
	private String DREQDATE;

	/**
	 *建议订货日期
	 */
	@TableField("DSUGGESTDATE")
	private String DSUGGESTDATE;

	/**
	 *建议外包单位主键
	 */
	@TableField("PK_SUGGESTSUPPLIER")
	private String PK_SUGGESTSUPPLIER;

	/**
	 *建议外包单位编码
	 */
	@TableField("CODE_SUGGESTSUPPLIER")
	private String CODE_SUGGESTSUPPLIER;

	/**
	 *建议外包单位名称
	 */
	@TableField("NAME_SUGGESTSUPPLIER")
	private String NAME_SUGGESTSUPPLIER;

	/**
	 *选择外包单位的理由
	 */
	@TableField("VBMEMO")
	private String VBMEMO;

	/**
	 *项目id
	 */
	@TableField("CPROJECTID")
	private String CPROJECTID;

	/**
	 *项目编码
	 */
	@TableField("CPROJECTCODE")
	private String CPROJECTCODE;

	/**
	 * 项目名称
	 */
	@TableField("CPROJECTNAME")
	private String CPROJECTNAME;

	/**
	 *项目代号
	 */
	@TableField("CPROJECTID_DEF1")
	private String CPROJECTID_DEF1;

	/**
	 * 开工单号
	 */
	@TableField("CPROJECTID_DEF16")
	private String CPROJECTID_DEF16;

	/**
	 *生产部门主键
	 */
	@TableField("PRODUCE_DEPTID")
	private String PRODUCE_DEPTID;

	/**
	 * 生产部门编码
	 */
	@TableField("PRODUCE_DEPTCODE")
	private String PRODUCE_DEPTCODE;

	/**
	 * 质检分类主键
	 */
	@TableField("QI_CLASSIFICATIONID")
	private String QI_CLASSIFICATIONID;

	/**
	 * 质检分类编码
	 */
	@TableField("QI_CLASSIFICATIONCODE")
	private String QI_CLASSIFICATIONCODE;

	/**
	 * 重排日期
	 */
	@TableField("VBDEF16")
	private String VBDEF16;

	/**
	 *预留数量
	 */
	@TableField("VBDEF19")
	private String VBDEF19;

	/**
	 * 预留转移数量
	 */
	@TableField("VBDEF20")
	private String VBDEF20;

	/**
	 * 询报价单号
	 */
	@TableField("VBDEF21")
	private String VBDEF21;

	/**
	 * 价格审批单号
	 */
	@TableField("VBDEF22")
	private String VBDEF22;

	/**
	 *采购订单号
	 */
	@TableField("VBDEF23")
	private String VBDEF23;

	/**
	 *生成订单次数
	 */
	@TableField("VBDEF24")
	private String VBDEF24;

	/**
	 * 是否抓取
	 */
	@TableField("FLAG")
	private String FLAG="0";

	/**
	 * 抓取时间
	 */
	@TableField("GETDATE")
	private String GETDATE;

	@TableField("DATA_SOURCE")
	private String DATA_SOURCE="1";

	@Override
	protected Serializable pkVal() {
		return this.PK_PRAYBILL_B;
	}

}
