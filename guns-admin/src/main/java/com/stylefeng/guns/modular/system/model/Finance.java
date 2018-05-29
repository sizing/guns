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
 * 财务添加
 * </p>
 *
 * @author stylefeng123
 * @since 2018-04-23
 */
@TableName("sys_finance")
public class Finance extends Model<Finance> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 缴款人员
     */
    @TableField("fname_no")
    private String fnameNo;
    /**
     * 缴款单号
     */
    private String jkno;
    /**
     * 销售款
     */
    @TableField("fmarket_money")
    private Integer fmarketMoney;
    /**
     * 票据编号
     */
    private String fno;
    /**
     * 押金
     */
    private Integer fmoney;
    /**
     * 缴款日期
     */
    private String ftime;
    /**
     * 收款方式 1.pos机 2.微信 3.其他
     */
    private Integer ftype;
    /**
     * 通讯费
     */
    @TableField("fphone_money")
    private Integer fphoneMoney;
    /**
     * 是否缴款 1.是 0 否
     */
    private Integer ismake;
    /**
     * 是否开发票 1.是  0否
     */
    private Integer isbill;
    /**
     * 分账id
     */
    private Integer fid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFnameNo() {
        return fnameNo;
    }

    public void setFnameNo(String fnameNo) {
        this.fnameNo = fnameNo;
    }

    public String getJkno() {
        return jkno;
    }

    public void setJkno(String jkno) {
        this.jkno = jkno;
    }

    public Integer getFmarketMoney() {
        return fmarketMoney;
    }

    public void setFmarketMoney(Integer fmarketMoney) {
        this.fmarketMoney = fmarketMoney;
    }

    public String getFno() {
        return fno;
    }

    public void setFno(String fno) {
        this.fno = fno;
    }

    public Integer getFmoney() {
        return fmoney;
    }

    public void setFmoney(Integer fmoney) {
        this.fmoney = fmoney;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    public Integer getFtype() {
        return ftype;
    }

    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    public Integer getFphoneMoney() {
        return fphoneMoney;
    }

    public void setFphoneMoney(Integer fphoneMoney) {
        this.fphoneMoney = fphoneMoney;
    }

    public Integer getIsmake() {
        return ismake;
    }

    public void setIsmake(Integer ismake) {
        this.ismake = ismake;
    }

    public Integer getIsbill() {
        return isbill;
    }

    public void setIsbill(Integer isbill) {
        this.isbill = isbill;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Finance{" +
        "id=" + id +
        ", fnameNo=" + fnameNo +
        ", jkno=" + jkno +
        ", fmarketMoney=" + fmarketMoney +
        ", fno=" + fno +
        ", fmoney=" + fmoney +
        ", ftime=" + ftime +
        ", ftype=" + ftype +
        ", fphoneMoney=" + fphoneMoney +
        ", ismake=" + ismake +
        ", isbill=" + isbill +
        ", fid=" + fid +
        "}";
    }
}
