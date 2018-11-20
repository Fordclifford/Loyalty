package com.kcbgroup.loyalty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="POINTSMATRIX")
public class PointsMatrix {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="CHANNELNAME")
	private String channelName;
	
	@Column(name="CHANNELCODE")
	private String channelCode;
	
	@Column(name="PRODUCTNAME")
	private String productName;
	
	@Column(name="PRODUCTCODE")
	private String productCode;
	
	@Column(name="DEALCODE")
	private String dealCode;
	
	@Column(name="POS")
	private String pos;
	
	@Column(name="MINDAY")
	private String minDay;
	
	@Column(name="MAXDAY")
	private String maxDay;
	
	@Column(name="POINTSVALUE")
	private Integer pointsValue;
	
	@Column(name="POINTSAMOUNT")
	private Double pointsAmount;
	
	@Column(name="ONLINETRANSCODE")
	private String onlineTransCode;
	
	@Column(name="INTLTRANSCODE")
	private String intlTransCode;
	
	@Column(name="POSTXNCODE")
	private String posTxnCode;
	
	@Column(name="CURRENCY")
	private String currency;
	
	public Long getId() {
		return id;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDealCode() {
		return dealCode;
	}

	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getMinDay() {
		return minDay;
	}

	public void setMinDay(String minDay) {
		this.minDay = minDay;
	}

	public String getMaxDay() {
		return maxDay;
	}

	public void setMaxDay(String maxDay) {
		this.maxDay = maxDay;
	}

	public Integer getPointsValue() {
		return pointsValue;
	}

	public void setPointsValue(Integer pointsValue) {
		this.pointsValue = pointsValue;
	}

	public Double getPointsAmount() {
		return pointsAmount;
	}

	public void setPointsAmount(Double pointsAmount) {
		this.pointsAmount = pointsAmount;
	}

	public String getOnlineTransCode() {
		return onlineTransCode;
	}

	public void setOnlineTransCode(String onlineTransCode) {
		this.onlineTransCode = onlineTransCode;
	}

	public String getIntlTransCode() {
		return intlTransCode;
	}

	public void setIntlTransCode(String intlTransCode) {
		this.intlTransCode = intlTransCode;
	}

	public String getPosTxnCode() {
		return posTxnCode;
	}

	public void setPosTxnCode(String posTxnCode) {
		this.posTxnCode = posTxnCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "PointsMatrix [id=" + id + ", channelName=" + channelName + ", channelCode=" + channelCode
			+ ", productName=" + productName + ", productCode=" + productCode + ", dealCode=" + dealCode + ", pos="
			+ pos + ", minDay=" + minDay + ", maxDay=" + maxDay + ", pointsValue=" + pointsValue + ", pointsAmount="
			+ pointsAmount + ", onlineTransCode=" + onlineTransCode + ", intlTransCode=" + intlTransCode
			+ ", posTxnCode=" + posTxnCode + ", currency=" + currency + "]";
	}

}
