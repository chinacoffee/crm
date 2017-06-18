package com.stv.crm.util;

public class PageInfo {
	private int pageNo;
	private int pageSize;
	private int totalRecords;
	private int totalPages;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String toJson() {
		return "{\"pageInfo\":{\"pageNo\":\"" + pageNo + "\", \"pageSize\":\"" + pageSize + "\", \"totalRecords\":\"" + totalRecords
				+ "\", \"totalPages\":\"" + totalPages + "\"}}";
	}
	
	

}
