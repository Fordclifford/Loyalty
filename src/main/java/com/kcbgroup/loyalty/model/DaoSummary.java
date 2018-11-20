package com.kcbgroup.loyalty.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T24POSTING")
public class DaoSummary {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="DAO")
	private String dao;
	
	@Column(name="AMOUNT1")
    private Integer amount1;
	
	@Column(name="AMOUNT2")
    private Integer amount2;
	
	@Column(name="TOTALPOINTS")
    private Integer totalPoints;
	
	@Column(name="POSTING75")
    private Integer posting75;
	
	@Column(name="POSTING25")
    private Integer posting25;
	
	@Column(name="PROFITLOSSAC")
	private String profitLossAc;
	
	@Column(name="SUSPENSEAC")
	private String suspenseAc;
	
	@Column(name="HOLDINGAC")
	private String holdingAc;
	
	@Column(name="POSTINGDATE")
	private LocalDateTime postingDate;
	
	@Column(name="T24REF")
	private String t24Ref;
	
	@Column(name="MESSAGEID")
	private String messageId;
	
	@Column(name="FILEID")
	private String fileId;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="REASON")
	private String reason;
	
	public Long getId() {
		return id;
	}

	public String getDao() {
		return dao;
	}

	public void setDao(String dao) {
		this.dao = dao;
	}

	public Integer getAmount1() {
		return amount1;
	}

	public void setAmount1(Integer amount1) {
		this.amount1 = amount1;
	}

	public Integer getAmount2() {
		return amount2;
	}

	public void setAmount2(Integer amount2) {
		this.amount2 = amount2;
	}

	public Integer getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}

	public Integer getPosting75() {
		return posting75;
	}

	public void setPosting75(Integer posting75) {
		this.posting75 = posting75;
	}

	public Integer getPosting25() {
		return posting25;
	}

	public void setPosting25(Integer posting25) {
		this.posting25 = posting25;
	}

	public String getProfitLossAc() {
		return profitLossAc;
	}

	public void setProfitLossAc(String profitLossAc) {
		this.profitLossAc = profitLossAc;
	}

	public String getSuspenseAc() {
		return suspenseAc;
	}

	public void setSuspenseAc(String suspenseAc) {
		this.suspenseAc = suspenseAc;
	}

	public String getHoldingAc() {
		return holdingAc;
	}

	public void setHoldingAc(String holdingAc) {
		this.holdingAc = holdingAc;
	}

	public LocalDateTime getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(LocalDateTime postingDate) {
		this.postingDate = postingDate;
	}

	public String getT24Ref() {
		return t24Ref;
	}

	public void setT24Ref(String t24Ref) {
		this.t24Ref = t24Ref;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
