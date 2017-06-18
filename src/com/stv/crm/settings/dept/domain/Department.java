package com.stv.crm.settings.dept.domain;

public class Department {
	private String code;
	private String name;
	private String manager;
	private String phone;
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toJson() {
		return "{\"code\": \"" + code + "\", " + "\"name\": \"" + name + "\", " + "\"manager\": \"" + manager + "\", "
				+ "\"phone\": \"" + phone + "\", " + "\"description\": \"" + description + "\"}";
	}

}
