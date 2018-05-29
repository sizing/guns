package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 出库表
 * </p>
 *
 * @author lishuai123
 * @since 2018-04-20
 */
@TableName("sys_ck")
public class Ck extends Model<Ck> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 出库人
     */
    @TableField("c_name")
    private String cName;
    /**
     * 出库时间
     */
    @TableField("c_time")
    private String cTime;
    /**
     * 出库机型
     */
    @TableField("c_model")
    private String cModel;
    /**
     * 开票类型（1.收据 2.普票  3.专票）
     */
    @TableField("c_bill_type")
    private Integer cBillType;
    /**
     * 税号
     */
    @TableField("c_duty_no")
    private String cDutyNo;
    /**
     * 账号
     */
    @TableField("c_no")
    private String cNo;
    /**
     * 地址
     */
    @TableField("c_addre")
    private String cAddre;
    /**
     * 电话
     */
    @TableField("c_phone")
    private String cPhone;
    /**
     * 开户行
     */
    @TableField("c_bank")
    private String cBank;
    /**
     * 分账id
     */
    @TableField("fz_id")
    private Integer fzId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public String getcModel() {
        return cModel;
    }

    public void setcModel(String cModel) {
        this.cModel = cModel;
    }

    public Integer getcBillType() {
        return cBillType;
    }

    public void setcBillType(Integer cBillType) {
        this.cBillType = cBillType;
    }

    public String getcDutyNo() {
        return cDutyNo;
    }

    public void setcDutyNo(String cDutyNo) {
        this.cDutyNo = cDutyNo;
    }

    public String getcNo() {
        return cNo;
    }

    public void setcNo(String cNo) {
        this.cNo = cNo;
    }

    public String getcAddre() {
        return cAddre;
    }

    public void setcAddre(String cAddre) {
        this.cAddre = cAddre;
    }

    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    public String getcBank() {
        return cBank;
    }

    public void setcBank(String cBank) {
        this.cBank = cBank;
    }

    public Integer getFzId() {
        return fzId;
    }

    public void setFzId(Integer fzId) {
        this.fzId = fzId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Ck{" +
        "id=" + id +
        ", cName=" + cName +
        ", cTime=" + cTime +
        ", cModel=" + cModel +
        ", cBillType=" + cBillType +
        ", cDutyNo=" + cDutyNo +
        ", cNo=" + cNo +
        ", cAddre=" + cAddre +
        ", cPhone=" + cPhone +
        ", cBank=" + cBank +
        ", fzId=" + fzId +
        "}";
    }
}
