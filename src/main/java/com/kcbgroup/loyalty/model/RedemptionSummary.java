package com.kcbgroup.loyalty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REDEMPTION")
public class RedemptionSummary {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="CUSTOMERNO")
	private String customerNo;
	
	@Column(name="NAME")
    private String name;
	
	@Column(name="CUSTMOBILE")
    private String custMobile;
	
	@Column(name="DAO")
    private String dao;
	
	@Column(name="POINTSEARNED")
    private String pointsEarned;
	
	@Column(name="POINTSREDEEMED")
    private String pointsRedeemed;
	
	@Column(name="POINTSBALANCE")
    private String pointsBalance;
	
	public Long getId() {
		return id;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public String getDao() {
		return dao;
	}

	public void setDao(String dao) {
		this.dao = dao;
	}

	public String getPointsEarned() {
		return pointsEarned;
	}

	public void setPointsEarned(String pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	public String getPointsRedeemed() {
		return pointsRedeemed;
	}

	public void setPointsRedeemed(String pointsRedeemed) {
		this.pointsRedeemed = pointsRedeemed;
	}

	public String getPointsBalance() {
		return pointsBalance;
	}

	public void setPointsBalance(String pointsBalance) {
		this.pointsBalance = pointsBalance;
	}

}
