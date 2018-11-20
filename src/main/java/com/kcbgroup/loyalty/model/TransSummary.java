package com.kcbgroup.loyalty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TRANSSUMMARY")
public class TransSummary {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="INFINIAFILEID")
	private String infiniaFileId;
	
	@Column(name="KCBFILEID")
	private String kcbFileId;
	
	@Column(name="INFINIAFILECOUNT")
	private Integer infiniaFileCount;
	
	@Column(name="KCBFILECOUNT")
	private Integer kcbFileCount;
	
	@Column(name="COUNTMATCHED")
	private String countMatched;
	
	@Column(name="ALLCUSTOMEREXIST")
	private String allCustomersExist;
	
	@Column(name="NONCUSTOMERCOUNTINF")
	private Integer nonCustomerCountInf;
	
	@Column(name="INFINIATOTALPOINTS")
	private Integer infiniaTotalPoints;
	
	@Column(name="KCBTOTALPOINTS")
	private Integer kcbTotalPoints;
	
	@Column(name="POINTSMATCHED")
	private String pointsMatched;
	 
	@Column(name="INFINIADISTINCTTXNCODE")
	private Integer infiniaDistinctTxnCode;
	
	@Column(name="KCBDISTINCTTXNCODE")
	private Integer kcbDistinctTxnCode;
	
	@Column(name="DISTINCTTXNCODEMATCHED")
	private String distinctTxnCodeMatched;
	
	@Column(name="INFINIASUCCESSCOUNT")
	private Integer infiniaSuccessCount;
	
	@Column(name="KCBSUCCESSCOUNT")
	private Integer kcbSuccessCount;
	
	@Column(name="SUCCESSCOUNTMATCHED")
	private String successCountMatched;
	
	@Column(name="INFINIAERRORCOUNT")
	private Integer infiniaErrorCount;
	
	@Column(name="KCBERRORCOUNT")
	private Integer kcbErrorCount;
	
	@Column(name="ERRORCOUNTMATCHED")
	private String errorCountMatched;
	
	@Column(name="INFINIATOTALAMOUNT1")
	private Integer infiniaTotalAmount1;
	
	@Column(name="KCBTOTALAMOUNT1")
	private Integer kcbTotalAmount1;
	
	@Column(name="AMOUNT1MATCHED")
	private String amount1Matched;
	
	@Column(name="INFINIATOTALAMOUNT2")
	private Integer infiniaTotalAmount2;
	
	@Column(name="KCBTOTALAMOUNT2")
	private Integer kcbTotalAmount2;
	
	@Column(name="AMOUNT2MATCHED")
	private String amount2Matched;
	
	@Column(name="FILEMATCHED")
	private String fileMatched;
	
	@Column(name="CLOSED")
	private String closed;
	
	@Column(name="NONCUSTOMERCOUNTKCB")
	private String nonCustomerCountKcb;
	
	public Long getId() {
		return id;
	}

	public String getInfiniaFileId() {
		return infiniaFileId;
	}

	public void setInfiniaFileId(String infiniaFileId) {
		this.infiniaFileId = infiniaFileId;
	}

	public String getKcbFileId() {
		return kcbFileId;
	}

	public void setKcbFileId(String kcbFileId) {
		this.kcbFileId = kcbFileId;
	}

	public Integer getInfiniaFileCount() {
		return infiniaFileCount;
	}

	public void setInfiniaFileCount(Integer infiniaFileCount) {
		this.infiniaFileCount = infiniaFileCount;
	}

	public Integer getKcbFileCount() {
		return kcbFileCount;
	}

	public void setKcbFileCount(Integer kcbFileCount) {
		this.kcbFileCount = kcbFileCount;
	}

	public String getCountMatched() {
		return countMatched;
	}

	public void setCountMatched(String countMatched) {
		this.countMatched = countMatched;
	}

	public String getAllCustomersExist() {
		return allCustomersExist;
	}

	public void setAllCustomersExist(String allCustomersExist) {
		this.allCustomersExist = allCustomersExist;
	}

	public Integer getNonCustomerCountInf() {
		return nonCustomerCountInf;
	}

	public void setNonCustomerCountInf(Integer nonCustomerCountInf) {
		this.nonCustomerCountInf = nonCustomerCountInf;
	}

	public Integer getInfiniaTotalPoints() {
		return infiniaTotalPoints;
	}

	public void setInfiniaTotalPoints(Integer infiniaTotalPoints) {
		this.infiniaTotalPoints = infiniaTotalPoints;
	}

	public Integer getKcbTotalPoints() {
		return kcbTotalPoints;
	}

	public void setKcbTotalPoints(Integer kcbTotalPoints) {
		this.kcbTotalPoints = kcbTotalPoints;
	}

	public String getPointsMatched() {
		return pointsMatched;
	}

	public void setPointsMatched(String pointsMatched) {
		this.pointsMatched = pointsMatched;
	}

	public Integer getInfiniaDistinctTxnCode() {
		return infiniaDistinctTxnCode;
	}

	public void setInfiniaDistinctTxnCode(Integer infiniaDistinctTxnCode) {
		this.infiniaDistinctTxnCode = infiniaDistinctTxnCode;
	}

	public Integer getKcbDistinctTxnCode() {
		return kcbDistinctTxnCode;
	}

	public void setKcbDistinctTxnCode(Integer kcbDistinctTxnCode) {
		this.kcbDistinctTxnCode = kcbDistinctTxnCode;
	}

	public String getDistinctTxnCodeMatched() {
		return distinctTxnCodeMatched;
	}

	public void setDistinctTxnCodeMatched(String distinctTxnCodeMatched) {
		this.distinctTxnCodeMatched = distinctTxnCodeMatched;
	}

	public Integer getInfiniaSuccessCount() {
		return infiniaSuccessCount;
	}

	public void setInfiniaSuccessCount(Integer infiniaSuccessCount) {
		this.infiniaSuccessCount = infiniaSuccessCount;
	}

	public Integer getKcbSuccessCount() {
		return kcbSuccessCount;
	}

	public void setKcbSuccessCount(Integer kcbSuccessCount) {
		this.kcbSuccessCount = kcbSuccessCount;
	}

	public String getSuccessCountMatched() {
		return successCountMatched;
	}

	public void setSuccessCountMatched(String successCountMatched) {
		this.successCountMatched = successCountMatched;
	}

	public Integer getInfiniaErrorCount() {
		return infiniaErrorCount;
	}

	public void setInfiniaErrorCount(Integer infiniaErrorCount) {
		this.infiniaErrorCount = infiniaErrorCount;
	}

	public Integer getKcbErrorCount() {
		return kcbErrorCount;
	}

	public void setKcbErrorCount(Integer kcbErrorCount) {
		this.kcbErrorCount = kcbErrorCount;
	}

	public String getErrorCountMatched() {
		return errorCountMatched;
	}

	public void setErrorCountMatched(String errorCountMatched) {
		this.errorCountMatched = errorCountMatched;
	}

	public Integer getInfiniaTotalAmount1() {
		return infiniaTotalAmount1;
	}

	public void setInfiniaTotalAmount1(Integer infiniaTotalAmount1) {
		this.infiniaTotalAmount1 = infiniaTotalAmount1;
	}

	public Integer getKcbTotalAmount1() {
		return kcbTotalAmount1;
	}

	public void setKcbTotalAmount1(Integer kcbTotalAmount1) {
		this.kcbTotalAmount1 = kcbTotalAmount1;
	}

	public String getAmount1Matched() {
		return amount1Matched;
	}

	public void setAmount1Matched(String amount1Matched) {
		this.amount1Matched = amount1Matched;
	}

	public Integer getInfiniaTotalAmount2() {
		return infiniaTotalAmount2;
	}

	public void setInfiniaTotalAmount2(Integer infiniaTotalAmount2) {
		this.infiniaTotalAmount2 = infiniaTotalAmount2;
	}

	public Integer getKcbTotalAmount2() {
		return kcbTotalAmount2;
	}

	public void setKcbTotalAmount2(Integer kcbTotalAmount2) {
		this.kcbTotalAmount2 = kcbTotalAmount2;
	}

	public String getAmount2Matched() {
		return amount2Matched;
	}

	public void setAmount2Matched(String amount2Matched) {
		this.amount2Matched = amount2Matched;
	}

	public String getFileMatched() {
		return fileMatched;
	}

	public void setFileMatched(String fileMatched) {
		this.fileMatched = fileMatched;
	}

	public String getClosed() {
		return closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	public String getNonCustomerCountKcb() {
		return nonCustomerCountKcb;
	}

	public void setNonCustomerCountKcb(String nonCustomerCountKcb) {
		this.nonCustomerCountKcb = nonCustomerCountKcb;
	}

}
