package com.test.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class bill {//订单
    private int id = 0;
    private String billCode ="";
    private String productName ="";
    private String productDesc ="";//订单品种
    private String productUnit ="";//计量单位
    private BigDecimal  productCount ;//'商品数量'
    private BigDecimal  totalPrice ;//'商品总额'
    private boolean isPayment ;//'是否支付（1：未支付 2：已支付,true）'
    private int createdBy = 0;
    private LocalDateTime creationDate ;//创建时间
    private int modifyBy =0 ;//更新者（userId）
    private  LocalDateTime modifyDate ;//更新时间
    private int providerId =0;//供应商ID

    public bill(int id, String billCode, String productName, String productDesc, String productUnit, BigDecimal productCount, BigDecimal totalPrice, boolean isPayment, int createdBy, LocalDateTime creationDate, int modifyBy, LocalDateTime modifyDate, int providerId) {
        this.id = id;
        this.billCode = billCode;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productUnit = productUnit;
        this.productCount = productCount;
        this.totalPrice = totalPrice;
        this.isPayment = isPayment;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.providerId = providerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public void setProductCount(BigDecimal productCount) {
        this.productCount = productCount;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPayment(boolean payment) {
        isPayment = payment;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setModifyBy(int modifyBy) {
        this.modifyBy = modifyBy;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public int getId() {
        return id;
    }

    public String getBillCode() {
        return billCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public BigDecimal getProductCount() {
        return productCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public boolean isPayment() {
        return isPayment;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public int getModifyBy() {
        return modifyBy;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public int getProviderId() {
        return providerId;
    }
}
