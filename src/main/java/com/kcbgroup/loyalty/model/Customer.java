package com.kcbgroup.loyalty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMERS")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="CUSTOMERCODE")
    private String customerCode;
	
	@Column(name="MNEMONIC")
    private String mnemonic;
	
	@Column(name="SHORTNAME")
    private String shortName;
	
	@Column(name="NAME")
    private String name;
	
	@Column(name="OPTINDATE")
    private String optInDate;
	
	@Column(name="STREET")
    private String street;
	
	@Column(name="ADDRESS")
    private String address;
	
	@Column(name="TOWN")
    private String town;
	
	@Column(name="POSTCODE")
    private String postCode;
	
	@Column(name="COUNTRY")
    private String country;
	
	@Column(name="SECTOR")
    private String sector;
	
	@Column(name="ACCOUNTOFFICER")
    private String accountOfficer;
	
	@Column(name="OTHEROFFICER")
    private String otherOfficer;
	
	@Column(name="INDUSTRY")
    private String industry;
	
	@Column(name="TARGET")
    private String target;
	
	@Column(name="NATIONALITY")
    private String nationality;
	
	@Column(name="CUSTOMERSTATUS")
    private String customerStatus;
	
	@Column(name="RESIDENCE")
    private String residence;
	
	@Column(name="CONTACTDATE")
    private String contactDate;
	
	@Column(name="CUSTOFFICE")
    private String custOffice;
	
	@Column(name="CUSTMOBILE")
    private String custMobile;
	
	public Long getId() {
		return id;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getMnemonic() {
		return mnemonic;
	}

	public void setMnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOptInDate() {
		return optInDate;
	}

	public void setOptInDate(String optInDate) {
		this.optInDate = optInDate;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getAccountOfficer() {
		return accountOfficer;
	}

	public void setAccountOfficer(String accountOfficer) {
		this.accountOfficer = accountOfficer;
	}

	public String getOtherOfficer() {
		return otherOfficer;
	}

	public void setOtherOfficer(String otherOfficer) {
		this.otherOfficer = otherOfficer;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getContactDate() {
		return contactDate;
	}

	public void setContactDate(String contactDate) {
		this.contactDate = contactDate;
	}

	public String getCustOffice() {
		return custOffice;
	}

	public void setCustOffice(String custOffice) {
		this.custOffice = custOffice;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

}
