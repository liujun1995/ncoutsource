package com.nc.entity.wxwb;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("MATTER_INFO")
public class MatterInfo extends Model<MatterInfo> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("MATTER_ID")
    private String matterId;

    @TableField("MATTER_CODE")
    private String matterCode;

    @TableField("BUY_ID")
    private String buyId;

    @TableField("CONTRACT_ID")
    private String contractId;

    @TableField("CONTRACT_NUMBER")
    private String contractNumber;

    @TableField("MATTER_COUNT")
    private String matterCount;

    @TableField("CREATE_TIME")
    private String createTime;

    @TableField("MATTER_NAME")
    private String  matterName;

    @TableField("STATE")
    private Integer state;

    @TableField("CONTRACTLOG_ID")
    private String contractlogId;

    @TableField("IS_USED")
    private Integer isUsed;

    @TableField("ERP_USED")
    private Integer erpUsed;

    @TableField("MATTER_TYPE")
    private Integer matterType;


    @Override
    protected Serializable pkVal() {
        return id;
    }

}
