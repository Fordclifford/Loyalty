package com.kcbgroup.loyalty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DIRECTORY")
public class Directory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="KCBINDIR")
	private String kcbInDir;
	
	@Column(name="KCBOUTDIR")
	private String kcbOutDir;
	
	@Column(name="INFINIAINDIR")
    private String infiniaInDir;
	
	@Column(name="INFINIAOUTDIR")
    private String infiniaOutDir;
	
	@Column(name="CUSTOMERINDIR")
    private String customerInDir;
	
	@Column(name="CUSTOMEROUTDIR")
    private String customerOutDir;
	
	@Column(name="KCBBKDIR")
    private String kcbBkDir;
	
	@Column(name="INFINIABKDIR")
    private String infiniaBkDir;
	
	@Column(name="CUSTOMERBKDIR")
    private String customerBkDir;
	
	public Long getId() {
		return id;
	}

	public String getKcbInDir() {
		return kcbInDir;
	}

	public void setKcbInDir(String kcbInDir) {
		this.kcbInDir = kcbInDir;
	}

	public String getKcbOutDir() {
		return kcbOutDir;
	}

	public void setKcbOutDir(String kcbOutDir) {
		this.kcbOutDir = kcbOutDir;
	}

	public String getInfiniaInDir() {
		return infiniaInDir;
	}

	public void setInfiniaInDir(String infiniaInDir) {
		this.infiniaInDir = infiniaInDir;
	}

	public String getInfiniaOutDir() {
		return infiniaOutDir;
	}

	public void setInfiniaOutDir(String infiniaOutDir) {
		this.infiniaOutDir = infiniaOutDir;
	}

	public String getCustomerInDir() {
		return customerInDir;
	}

	public void setCustomerInDir(String customerInDir) {
		this.customerInDir = customerInDir;
	}

	public String getCustomerOutDir() {
		return customerOutDir;
	}

	public void setCustomerOutDir(String customerOutDir) {
		this.customerOutDir = customerOutDir;
	}

	public String getKcbBkDir() {
		return kcbBkDir;
	}

	public void setKcbBkDir(String kcbBkDir) {
		this.kcbBkDir = kcbBkDir;
	}

	public String getInfiniaBkDir() {
		return infiniaBkDir;
	}

	public void setInfiniaBkDir(String infiniaBkDir) {
		this.infiniaBkDir = infiniaBkDir;
	}

	public String getCustomerBkDir() {
		return customerBkDir;
	}

	public void setCustomerBkDir(String customerBkDir) {
		this.customerBkDir = customerBkDir;
	}

}
