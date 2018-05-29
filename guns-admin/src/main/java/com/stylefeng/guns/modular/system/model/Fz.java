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
 * 分账表
 * </p>
 *
 * @author ls123
 * @since 2018-04-18
 */
@TableName("sys_fz")
public class Fz extends Model<Fz> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "ID_FZ", type = IdType.AUTO)
    private Long idFz;
    /**
     * 编号
     */
    @TableField("NO_FZ")
    private String noFz;
    /**
     * 商户名称
     */
    @TableField("PNAME_FZ")
    private String pnameFz;
    /**
     * 发展人
     */
    @TableField("NAME_FZ")
    private String nameFz;
    /**
     * 所属
     */
    @TableField("BELONG_FZ")
    private String belongFz;
    /**
     * 商户简称
     */
    @TableField("PAK_FZ")
    private String pakFz;
    /**
     * 终端数量
     */
    @TableField("ZCOUNT_FZ")
    private Integer zcountFz;
    /**
     * 入网模式
     */
    @TableField("NET_WORK_FZ")
    private String netWorkFz;
    /**
     * 租赁金额
     */
    @TableField("LEASING_FZ")
    private Integer leasingFz;
    /**
     * 终端购买费
     */
    @TableField("COST_FZ")
    private Integer costFz;
    /**
     * 通讯费
     */
    @TableField("PHONE_FZ")
    private String phoneFz;
    /**
     * 机型
     */
    @TableField("TYPE_FZ")
    private String typeFz;
    /**
     * 提交审核时间
     */
    @TableField("CHECK_FZ")
    private String checkFz;
    /**
     * 商户编号
     */
    @TableField("MERCHANT_FZ")
    private String merchantFz;
    /**
     * 传统终端号
     */
    @TableField("TRADITIONAL_FZ")
    private String traditionalFz;
    /**
     * 中信终端号
     */
    @TableField("CITIC_FZ")
    private String citicFz;


    public Long getIdFz() {
        return idFz;
    }

    public void setIdFz(Long idFz) {
        this.idFz = idFz;
    }

    public String getNoFz() {
        return noFz;
    }

    public void setNoFz(String noFz) {
        this.noFz = noFz;
    }

    public String getPnameFz() {
        return pnameFz;
    }

    public void setPnameFz(String pnameFz) {
        this.pnameFz = pnameFz;
    }

    public String getNameFz() {
        return nameFz;
    }

    public void setNameFz(String nameFz) {
        this.nameFz = nameFz;
    }

    public String getBelongFz() {
        return belongFz;
    }

    public void setBelongFz(String belongFz) {
        this.belongFz = belongFz;
    }

    public String getPakFz() {
        return pakFz;
    }

    public void setPakFz(String pakFz) {
        this.pakFz = pakFz;
    }

    public Integer getZcountFz() {
        return zcountFz;
    }

    public void setZcountFz(Integer zcountFz) {
        this.zcountFz = zcountFz;
    }

    public String getNetWorkFz() {
        return netWorkFz;
    }

    public void setNetWorkFz(String netWorkFz) {
        this.netWorkFz = netWorkFz;
    }

    public Integer getLeasingFz() {
        return leasingFz;
    }

    public void setLeasingFz(Integer leasingFz) {
        this.leasingFz = leasingFz;
    }

    public Integer getCostFz() {
        return costFz;
    }

    public void setCostFz(Integer costFz) {
        this.costFz = costFz;
    }

    public String getPhoneFz() {
        return phoneFz;
    }

    public void setPhoneFz(String phoneFz) {
        this.phoneFz = phoneFz;
    }

    public String getTypeFz() {
        return typeFz;
    }

    public void setTypeFz(String typeFz) {
        this.typeFz = typeFz;
    }

    public String getCheckFz() {
        return checkFz;
    }

    public void setCheckFz(String checkFz) {
        this.checkFz = checkFz;
    }

    public String getMerchantFz() {
        return merchantFz;
    }

    public void setMerchantFz(String merchantFz) {
        this.merchantFz = merchantFz;
    }

    public String getTraditionalFz() {
        return traditionalFz;
    }

    public void setTraditionalFz(String traditionalFz) {
        this.traditionalFz = traditionalFz;
    }

    public String getCiticFz() {
        return citicFz;
    }

    public void setCiticFz(String citicFz) {
        this.citicFz = citicFz;
    }

    @Override
    protected Serializable pkVal() {
        return this.idFz;
    }

    @Override
    public String toString() {
        return "Fz{" +
        "idFz=" + idFz +
        ", noFz=" + noFz +
        ", pnameFz=" + pnameFz +
        ", nameFz=" + nameFz +
        ", belongFz=" + belongFz +
        ", pakFz=" + pakFz +
        ", zcountFz=" + zcountFz +
        ", netWorkFz=" + netWorkFz +
        ", leasingFz=" + leasingFz +
        ", costFz=" + costFz +
        ", phoneFz=" + phoneFz +
        ", typeFz=" + typeFz +
        ", checkFz=" + checkFz +
        ", merchantFz=" + merchantFz +
        ", traditionalFz=" + traditionalFz +
        ", citicFz=" + citicFz +
        "}";
    }
}
