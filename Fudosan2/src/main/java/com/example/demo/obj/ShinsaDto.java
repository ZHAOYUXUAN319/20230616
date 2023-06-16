package com.example.demo.obj;

public class ShinsaDto {
	private Long id;
	private Long bukenId;
	private String shinsaA;
	private String shinsaB;
	private String status;
	private String dropdown1;
	private String dropdown2;

	public ShinsaDto(Long id, Long bukenId, String shinsaA, String shinsaB, String status, String dropdown2, String dropdown1) {
		this.id = id;
		this.bukenId = bukenId;
		this.shinsaA = shinsaA;
		this.shinsaB = shinsaB;
		this.status = status;
		this.dropdown1 = dropdown1;
		this.dropdown2 = dropdown2;
	}

	public String getDropdown1() {
		return dropdown1;
	}

	public void setDropdown1(String dropdown1) {
		this.dropdown1 = dropdown1;
	}
	
	public String getDropdown2() {
	    return dropdown2;
	}

	public void setDropdown2(String dropdown2) {
	    this.dropdown2 = dropdown2;
	}

	public ShinsaDto() {
		// TODO Auto-generated constructor stub
	}

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
