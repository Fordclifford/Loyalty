package com.kcbgroup.loyalty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DAO")
public class Dao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="BRANCHCODE")
	private String branchCode;
	
	@Column(name="BRANCHNAME")
	private String branchName;
	
	@Column(name="PROFITLOSSAC")
	private String profitLossAc;
	
	@Column(name="LOYALTYAC")
	private String loyaltyAc;
	
	@Column(name="HOLDINGAC")
	private String holdingAc;
	
	public Long getId() {
		return id;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getProfitLossAc() {
		return profitLossAc;
	}

	public void setProfitLossAc(String profitLossAc) {
		this.profitLossAc = profitLossAc;
	}

	public String getLoyaltyAc() {
		return loyaltyAc;
	}

	public void setLoyaltyAc(String loyaltyAc) {
		this.loyaltyAc = loyaltyAc;
	}

	public String getHoldingAc() {
		return holdingAc;
	}

	public void setHoldingAc(String holdingAc) {
		this.holdingAc = holdingAc;
	}

}
