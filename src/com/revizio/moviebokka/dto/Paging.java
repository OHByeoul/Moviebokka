package com.revizio.moviebokka.dto;

public class Paging {
	private int totalRowData;
	private int pagingSize;
	private int pageIndexSize;
	private int currentPage;
	private int startIndex;
	private int endIndex;
	
	public Paging(int pagingSize, int pageIndexSize, int currentPage, int startIndex, int endIndex) {
		super();
		this.pagingSize = pagingSize;
		this.pageIndexSize = pageIndexSize;
		this.currentPage = currentPage;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	public int getTotalRowData() {
		return totalRowData;
	}
	
	public void setTotalRowData(int totalRowData) {
		this.totalRowData = totalRowData;
	}
	
	public int getPagingSize() {
		return pagingSize;
	}
	
	public void setPagingSize(int pagingSize) {
		this.pagingSize = pagingSize;
	}
	
	public int getPageIndexSize() {
		return pageIndexSize;
	}
	
	public void setPageIndexSize(int pageIndexSize) {
		this.pageIndexSize = pageIndexSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	
	public void setStartIndex(int startIndex) {
		System.out.println("paging : "+startIndex);
		this.startIndex = startIndex;
	}
	
	public int getEndIndex() {
		return endIndex;
	}
	
	public void setEndIndex(int endIndex) {
		System.out.println("paging : "+endIndex);
		this.endIndex = endIndex;
	}	
}
