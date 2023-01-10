package com.nc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 请购单子表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("NC65.PO_PRAYBILL_B")
public class PraybillB extends Model<PraybillB> {


    private static final long serialVersionUID = -3432997440833423530L;

    @TableId(type = IdType.INPUT)
    private  String PK_PRAYBILL_B ;

    @TableField
    private  String BCANPURCHASEORGEDIT ;

    @TableField
    private  String BFIXEDRATE ;

    @TableField
    private  String BISARRANGE ;

    @TableField
    private  String BISGENSAORDER ;

    @TableField
    private  String BPUBLISHTOEC ;

    @TableField
    private  String BROWCLOSE ;

    @TableField
    private  String CASSCUSTID ;

    @TableField
    private  String CASTUNITID ;

    @TableField
    private  String CFFILEID ;

    @TableField
    private  String CFIRSTBID ;

    @TableField
    private  String CFIRSTID ;

    @TableField
    private  String CFIRSTTYPECODE ;

    @TableField
    private  String CORDERTRANTYPECODE ;

    @TableField
    private  String CPRODUCTORID ;

    @TableField
    private  String CPROJECTID ;

    @TableField
    private  String CPROJECTTASKID ;

    @TableField
    private  String CROWNO ;

    @TableField
    private  String CSOURCEBID ;

    @TableField
    private  String CSOURCEID ;

    @TableField
    private  String CSOURCETYPECODE ;

    @TableField
    private  String CUNITID ;

    @TableField
    private  String DBILLDATE ;

    @TableField
    private  String DREQDATE ;

    @TableField
    private  String DSUGGESTDATE ;

    @TableField
    private  String NACCUMULATENUM ;

    @TableField
    private  String NASTNUM ;

    @TableField
    private  String NGENCT ;

    @TableField
    private  String NNUM ;

    @TableField
    private  String NPRICEAUDITBILL ;

    @TableField
    private  String NQUOTEBILL;

    @TableField
    private  String NTAXMNY ;

    @TableField
    private  String NTAXPRICE ;

    @TableField
    private  String PK_BATCHCODE ;

    @TableField
    private  String PK_EMPLOYEE ;

    @TableField
    private  String PK_GROUP;

    @TableField
    private  String PK_MATERIAL;

    @TableField
    private  String PK_ORG ;

    @TableField
    private  String PK_ORG_V ;

    @TableField
    private  String PK_PRAYBILL ;

    @TableField
    private  String PK_PRODUCT;

    @TableField
    private  String PK_PRODUCT_V ;

    @TableField
    private  String PK_PURCHASEORG;

    @TableField
    private  String PK_PURCHASEORG_V ;

    @TableField
    private  String PK_REQDEPT ;

    @TableField
    private  String PK_REQDEPT_V ;

    @TableField
    private  String PK_REQSTOORG ;

    @TableField
    private  String PK_REQSTOORG_V ;

    @TableField
    private  String PK_REQSTOR ;

    @TableField
    private  String PK_SRCMATERIAL ;

    @TableField
    private  String PK_SUGGESTSUPPLIER ;

    @TableField
    private  String SOURCEBTS ;

    @TableField
    private  String SOURCETS ;

    @TableField
    private  String TS ;

    @TableField
    private  String VBATCHCODE ;

    @TableField
    private  String VBDEF1 ;

    @TableField
    private  String VBDEF10 ;

    @TableField
    private  String VBDEF11;

    @TableField
    private  String VBDEF12;

    @TableField
    private  String VBDEF13 ;

    @TableField
    private  String VBDEF14 ;

    @TableField
    private  String VBDEF15 ;

    @TableField
    private  String VBDEF16 ;

    @TableField
    private  String VBDEF17 ;

    @TableField
    private  String VBDEF18 ;

    @TableField
    private  String VBDEF19 ;

    @TableField
    private  String VBDEF2 ;

    @TableField
    private  String VBDEF20 ;

    @TableField
    private  String VBDEF3 ;

    @TableField
    private  String VBDEF4 ;

    @TableField
    private  String VBDEF5 ;

    @TableField
    private  String VBDEF6 ;

    @TableField
    private  String VBDEF7 ;

    @TableField
    private  String VBDEF8 ;

    @TableField
    private  String VBDEF9 ;

    @TableField
    private  String VBMEMO ;

    @TableField
    private  String VCHANGERATE ;

    @TableField
    private  String VFIRSTCODE ;

    @TableField
    private  String VFIRSTROWNO ;

    @TableField
    private  String VFIRSTTRANTYPE ;

    @TableField
    private  String VFREE1 ;

    @TableField
    private  String VFREE10 ;

    @TableField
    private  String VFREE2 ;

    @TableField
    private  String VFREE3 ;

    @TableField
    private  String VFREE4 ;

    @TableField
    private  String VFREE5 ;

    @TableField
    private  String VFREE6 ;

    @TableField
    private  String VFREE7;

    @TableField
    private  String VFREE8 ;

    @TableField
    private  String VFREE9 ;

    @TableField
    private  String VSOURCECODE ;

    @TableField
    private  String VSOURCEROWNO ;

    @TableField
    private  String VSRCTRANTYPECODE ;

    @TableField
    private  String VBDEF21 ;

    @TableField
    private  String VBDEF22 ;

    @TableField
    private  String VBDEF23 ;

    @TableField
    private  String VBDEF24 ;

    @TableField
    private  String VBDEF25 ;

    @TableField
    private  String VBDEF26 ;

    @TableField
    private  String VBDEF27 ;

    @TableField
    private  String VBDEF28 ;

    @TableField
    private  String VBDEF29 ;

    @TableField
    private  String VBDEF30 ;

    @TableField
    private  String VBDEF31 ;

    @TableField
    private  String VBDEF32 ;

    @TableField
    private  String VBDEF33 ;

    @TableField
    private  String VBDEF34 ;

    @TableField
    private  String VBDEF35 ;

    @TableField
    private  String VBDEF36 ;

    @TableField
    private  String VBDEF37 ;

    @TableField
    private  String VBDEF38 ;

    @TableField
    private  String VBDEF39 ;

    @TableField
    private  String VBDEF40 ;

    @TableField
    private  String VBDEF41 ;

    @TableField
    private  String VBDEF42 ;

    @TableField
    private  String VBDEF43 ;

    @TableField
    private  String VBDEF44 ;

    @TableField
    private  String VBDEF45 ;

    @TableField
    private  String VBDEF46 ;

    @TableField
    private  String VBDEF47 ;

    @TableField
    private  String VBDEF48 ;

    @TableField
    private  String VBDEF49 ;

    @TableField
    private  String VBDEF50 ;

    @Override
    protected Serializable pkVal() {
        return this.PK_PRAYBILL_B;
    }
}
