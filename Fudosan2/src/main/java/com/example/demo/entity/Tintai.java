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
@Table(name = "tintai")
public class Tintai implements Serializable {
	/**
	 * ID
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * お客さん
	 */
	@Column(name = "okyaku")
	private String okyaku;
	/**
	 * 契約物件ID
	 */
	@Column(name = "tintai_buken_id")
	private Long tintaiBukenId;
	/**
	 * 一回審査者
	 */
	@Column(name = "shinsasyaA")
	private String shinsasyaA;
	/**
	 * 二回審査者
	 */
	@Column(name = "shinsasyaB")
	private String shinsasyaB;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOkyaku() {
		return okyaku;
	}

	public void setOkyaku(String okyaku) {
		this.okyaku = okyaku;
	}

	public Long getTintaiBukenId() {
		return tintaiBukenId;
	}

	public void setTintaiBukenId(Long tintaiBukenId) {
		this.tintaiBukenId = tintaiBukenId;
	}

	public String getShinsasyaA() {
		return shinsasyaA;
	}

	public void setShinsasyaA(String shinsasyaA) {
		this.shinsasyaA = shinsasyaA;
	}
	
	public String getShinsasyaB() {
		return shinsasyaB;
	}

	public void setShinsasyaB(String shinsasyaB) {
		this.shinsasyaB = shinsasyaB;
	}
}