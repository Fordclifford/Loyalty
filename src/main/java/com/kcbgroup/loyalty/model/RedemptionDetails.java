package com.kcbgroup.loyalty.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REDEMPTIONDETAILS")
public class RedemptionDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="CUSTOMERNO")
	private String customerNo;
	
	@Column(name="CREATEDDATE")
    private LocalDateTime createdDate;
	
	@Column(name="POINTS")
	private Integer points;
	
	@Column(name="POINTSFLAG")
    private String pointsFlag;
	
	@Column(name="FILEID")
	private String fileId;
	
	@Column(name="RECORDNO")
    private Integer recordNo;
	
	@Column(name="TRANSREF")
	private String transRef;
	
	@Column(name="AMOUNT")
	private Double amount;
	
	@Column(name="NARRATION1")
	private String narration1;
	
	@Column(name="NARRATION2")
	private String narration2;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="MOBILENO")
	private String mobileNo;
	
	@Column(name="TRANSDATE")
	private LocalDateTime transDate;
	
	@Column(name="TRANSCODE")
	private String transCode;
	
	@Column(name="DAO")
    private String dao;
	
	public Long getId() {
		return id;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getPointsFlag() {
		return pointsFlag;
	}

	public void setPointsFlag(String pointsFlag) {
		this.pointsFlag = pointsFlag;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Integer getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}

	public String getTransRef() {
		return transRef;
	}

	public void setTransRef(String transRef) {
		this.transRef = transRef;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getNarration1() {
		return narration1;
	}

	public void setNarration1(String narration1) {
		this.narration1 = narration1;
	}

	public String getNarration2() {
		return narration2;
	}

	public void setNarration2(String narration2) {
		this.narration2 = narration2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public LocalDateTime getTransDate() {
		return transDate;
	}

	public void setTransDate(LocalDateTime transDate) {
		this.transDate = transDate;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	
	public String getDao() {
		return dao;
	}

	public void setDao(String dao) {
		this.dao = dao;
	}

}
