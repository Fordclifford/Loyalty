package com.kcbgroup.loyalty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.Table;

@NamedStoredProcedureQuery(name="incoming_kcb_cursor", procedureName="INCOMING_KCB_CURSOR")
@Entity
@Table(name="INFILEKCB")
public class Kcb {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="FILEID")
    private String fileId;
	
	@Column(name="RECORDNO")
    private Long recordNo;
	
	@Column(name="CUSTOMERNO")
    private String customerNo;
	
	@Column(name="MAINTRANSCODE")
    private String mainTransCode;
	
	@Column(name="VALUEX1")
    private String valueX1;
	
	@Column(name="ACCOUNTTYPE")
    private Integer accountType;
	
	@Column(name="TRANSCODE")
    private String transCode;
	
	@Column(name="INCOMINGDATE")
    private Integer incomingDate;
	
	@Column(name="INCOMINGTIME")
    private Integer incomingTime;
	
	@Column(name="TRANSREF")
    private String transRef;
	
	@Column(name="ACCOUNTNO")
    private String accountNo;
	
	@Column(name="NARRATION")
    private String narration;
	
	@Column(name="DEPOSITDAO")
    private String depositDao;
	
	@Column(name="VALUEX2")
    private String valueX2;
	
	@Column(name="VALUEX3")
    private String valueX3;
	
	@Column(name="VALUEX4")
    private String valueX4;
	
	@Column(name="DRVALUE")
    private String drValue;
	
	@Column(name="VALUEX5")
    private String valueX5;
	
	@Column(name="AMOUNT1")
    private Double amount1;
	
	@Column(name="AMOUNT2")
    private Double amount2;
	
	@Column(name="CURRENCY")
    private String currency;
	
	@Column(name="MATCHED")
    private String matched;
	
	@Column(name="CHECKFLAG")
    private String checkFlag;
	
	@Column(name="POINTSEARNED")
    private Integer pointsEarned;
	
	@Column(name="CLOSED")
    private String closed;
	
	@Column(name="SUCCESSERRORFLAG")
    private String successErrorFlag;
	
	@Column(name="GENERATED")
    private Integer generated;
	
	@Column(name="PROCESSED")
    private Integer processed;
	
	@Column(name="RETURNED")
    private Integer returned;
	
	public Long getId() {
		return id;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Long getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(Long recordNo) {
		this.recordNo = recordNo;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getMainTransCode() {
		return mainTransCode;
	}

	public void setMainTransCode(String mainTransCode) {
		this.mainTransCode = mainTransCode;
	}

	public String getValueX1() {
		return valueX1;
	}

	public void setValueX1(String valueX1) {
		this.valueX1 = valueX1;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public Integer getIncomingDate() {
		return incomingDate;
	}

	public void setIncomingDate(Integer incomingDate) {
		this.incomingDate = incomingDate;
	}

	public Integer getIncomingTime() {
		return incomingTime;
	}

	public void setIncomingTime(Integer incomingTime) {
		this.incomingTime = incomingTime;
	}

	public String getTransRef() {
		return transRef;
	}

	public void setTransRef(String transRef) {
		this.transRef = transRef;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getDepositDao() {
		return depositDao;
	}

	public void setDepositDao(String depositDao) {
		this.depositDao = depositDao;
	}

	public String getValueX2() {
		return valueX2;
	}

	public void setValueX2(String valueX2) {
		this.valueX2 = valueX2;
	}

	public String getValueX3() {
		return valueX3;
	}

	public void setValueX3(String valueX3) {
		this.valueX3 = valueX3;
	}

	public String getValueX4() {
		return valueX4;
	}

	public void setValueX4(String valueX4) {
		this.valueX4 = valueX4;
	}

	public String getDrValue() {
		return drValue;
	}

	public void setDrValue(String drValue) {
		this.drValue = drValue;
	}

	public String getValueX5() {
		return valueX5;
	}

	public void setValueX5(String valueX5) {
		this.valueX5 = valueX5;
	}

	public Double getAmount1() {
		return amount1;
	}

	public void setAmount1(Double amount1) {
		this.amount1 = amount1;
	}

	public Double getAmount2() {
		return amount2;
	}

	public void setAmount2(Double amount2) {
		this.amount2 = amount2;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMatched() {
		return matched;
	}

	public void setMatched(String matched) {
		this.matched = matched;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public Integer getPointsEarned() {
		return pointsEarned;
	}

	public void setPointsEarned(Integer pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	public String getClosed() {
		return closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}
	
	public String getSuccessErrorFlag() {
		return successErrorFlag;
	}

	public void setSuccessErrorFlag(String successErrorFlag) {
		this.successErrorFlag = successErrorFlag;
	}

	public Integer getGenerated() {
		return generated;
	}

	public void setGenerated(Integer generated) {
		this.generated = generated;
	}

	public Integer getProcessed() {
		return processed;
	}

	public void setProcessed(Integer processed) {
		this.processed = processed;
	}

	public Integer getReturned() {
		return returned;
	}

	public void setReturned(Integer returned) {
		this.returned = returned;
	}

}
