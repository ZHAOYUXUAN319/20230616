package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ユーザー情報 Entity
 */
@Entity
@Data
@Table(name = "shinsa")
public class Shinsa implements Serializable {
	/**
	 * ID
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 審査され物件ID
	 */
	@Column(name = "buken_id")
	private Long bukenId;
	/**
	 * 審査管理者A
	 */
	@Column(name = "shinsa_a")
	private String shinsaA;
	/**
	 * 審査管理者B
	 */
	@Column(name = "shinsa_b")
	private String shinsaB;
	/**
	 * status
	 */
	@Column(name = "status")
	private String status;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getBukenId() {
		return bukenId;
	}

	public void setBukenId(Long bukenId) {
		this.bukenId = bukenId;
	}

	public String getShinsaA() {
		return shinsaA;
	}

	public void setShinsaA(String shinsaA) {
		this.shinsaA = shinsaA;
	}

	public String getShinsaB() {
		return shinsaB;
	}

	public void setShinsaB(String shinsaB) {
		this.shinsaB = shinsaB;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}