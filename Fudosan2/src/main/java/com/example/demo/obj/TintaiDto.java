package com.example.demo.obj;

public class TintaiDto {
	private Long id;
	private String okyaku;
	private Long tintaiBukenId;
	private String shinsasyaA;
	private String shinsasyaB;

	public TintaiDto(Long id, String okyaku, Long tintaiBukenId, String shinsasyaA, String shinsasyaB) {
		this.id = id;
		this.okyaku = okyaku;
		this.tintaiBukenId = tintaiBukenId;
		this.shinsasyaA = shinsasyaA;
		this.shinsasyaB = shinsasyaB;
	}

	public TintaiDto() {
		// TODO Auto-generated constructor stub
	}

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
