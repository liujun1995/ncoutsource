package com.ncoutsource.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;


/**
 * 请购单主表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("NC65.PO_PRAYBILL")
public class Praybill extends Model<Praybill>{

    private static final  long serialVersionUID = -6425279921645246525L;

    @TableId(type = IdType.INPUT)
    private  String PK_PRAYBILL;

    @TableField
    private  String APPROVER;

    @TableField
    private  String BDIRECTTRANSIT;

    @TableField
    private  String BFILTERZEROFLAG ;

    @TableField
    private  String BILLMAKER ;

    @TableField
    private  String BISEFFECTPRICE;

    @TableField
    private  String BISGENASKBILL;

    @TableField
    private  String BISGENORDERBILL;

    @TableField
    private  String BISGENPRICEAUDITBILL;

    @TableField
    private  String BISLATEST;

    @TableField
    private  String BISNGENCT;

    @TableField
    private  String BISPOSITIONINV;

    @TableField
    private  String BISWAITAUDIT;

    @TableField
    private  String BORDERNUMEXEC;

    @TableField
    private  String BSCTYPE;

    @TableField
    private  String CCURRENCYID;

    @TableField
    private  String CHPROJECTID;

    @TableField
    private  String CREATIONTIME;

    @TableField
    private  String CREATOR;

    @TableField
    private  String CREVISEOPERID;

    @TableField
    private  String CTRANTYPEID;

    @TableField
    private  String DBILLDATE;

    @TableField
    private  String DMAKEDATE;

    @TableField
    private  String FBILLSTATUS;

    @TableField
    private  String FPOTYPE;

    @TableField
    private  String FPRAYSOURCE;

    @TableField
    private  String IPRINTCOUNT;

    @TableField
    private  String MODIFIEDTIME;

    @TableField
    private  String MODIFIER;

    @TableField
    private  String NTOTALASTNUM;

    @TableField
    private  String NTOTALTAXMNY;

    @TableField
    private  String NVERSION;

    @TableField
    private  String PK_GROUP;

    @TableField
    private  String PK_ORG;

    @TableField
    private  String PK_ORG_V;

    @TableField
    private  String PK_PLANDEPT;

    @TableField
    private  String PK_PLANDEPT_V;

    @TableField
    private  String PK_PLANPSN;

    @TableField
    private  String PK_SRCPRAYBILL;

    @TableField
    private  String SPLIT;

    @TableField
    private  String TAUDITTIME;

    @TableField
    private  String TREVISIONTIME;

    @TableField
    private  String TS;

    @TableField
    private  String VBILLCODE;

    @TableField
    private  String VDEF1;

    @TableField
    private  String VDEF10;

    @TableField
    private  String VDEF11 ;

    @TableField
    private  String VDEF12 ;

    @TableField
    private  String VDEF13 ;

    @TableField
    private  String VDEF14 ;

    @TableField
    private  String VDEF15 ;

    @TableField
    private  String VDEF16 ;

    @TableField
    private  String VDEF17 ;

    @TableField
    private  String VDEF18 ;

    @TableField
    private  String VDEF19 ;

    @TableField
    private  String VDEF2 ;

    @TableField
    private  String VDEF20 ;

    @TableField
    private  String VDEF3 ;

    @TableField
    private  String VDEF4 ;

    @TableField
    private  String VDEF5 ;

    @TableField
    private  String VDEF6 ;

    @TableField
    private  String VDEF7 ;

    @TableField
    private  String VDEF8 ;

    @TableField
    private  String VDEF9 ;

    @TableField
    private  String VMEMO ;

    @TableField
    private  String VTRANTYPECODE ;

    @Override
    protected Serializable pkVal() {
        return this.PK_PRAYBILL;
    }

}
