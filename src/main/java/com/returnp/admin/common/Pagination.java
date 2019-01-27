package com.returnp.admin.common;

public class Pagination {
	public enum PaginationPattern{
		EXPRESSION, JSTL
	}
	
	public static final String BLANK_TEMPLATE = "list not existed";
	
	//전체 row 수 
	private int rowTotalCount; 
	
	//전체 페이지 수 
	private int pageTotalCount;
	
	//현재 페이지 
	private int currentPage;
	
	//페이징당 보여줄 row 갯수 
	private int rowPerPage;
	//페이지 리스트에서 보여줄 페이지 수 
	
	private int pageGroupSize;
	private int startRow;
	private int endRow;
	
	private int startPage;
	private int endPage;
	
	//pagintion 을 위한 Css 
	private String commonCss;
	private String selectedCss;
	private String selectStartTag;
	private String selectEndTag;
	
	// 각 pagination 를 위한 시작 태크
	private String startTag;
	
	//각 pagination 를 위한 마침 태그 
	private String endTag;
	
	private String tagElement;
	
	private String url;
	private String pageParameterName;
	
	public Pagination() {}

	public Pagination(int rowTotalCount,int currentPage, int rowPerPage, int pageGroupSize,String url, String tagElememt) {
		this.rowTotalCount = rowTotalCount;
		this.currentPage = currentPage;
		this.rowPerPage = rowPerPage;
		this.pageGroupSize = pageGroupSize;
		this.url = url;
		this.tagElement =  tagElememt;
		this.pageParameterName = "page";
		this.setTag(tagElememt);
		this.initPagination();
	}
	
	public Pagination(int rowTotalCount,int currentPage, int rowPerPage, int pageGroupSize,String url) {
		this(rowTotalCount,currentPage, rowPerPage,  pageGroupSize, url, null);
	}
	
	public Pagination(int rowTotalCount,int currentPage, int rowPerPage, int pageGroupSize) {
		this(rowTotalCount,currentPage, rowPerPage,  pageGroupSize, null, null);
	}
	
	public Pagination(int rowTotalCount,int currentPage, int rowPerPage) {
		this(rowTotalCount,currentPage, rowPerPage,  0 , null, null);
	}
	
	/**
	 * 페이지 네이션 링크를 생성하기 위한 태그 설정 
	 * @param tag  페이지 네이션 링크를 생성할 때 사용할 태그 스트링 
	 */
	public void setTag(String tag){
		if (tag == null) {
			this.startTag = this.commonCss !=null ? "<span css='" + this.commonCss +  "'>" : "<span>";
			this.endTag = "</span>";
		}else {
			this.startTag= this.commonCss !=null ? "<" + tag + " css ='" + this.commonCss + "'>": "<" + tag + "'>";
			this.endTag = "</"+ tag + ">";
		}
		if (this.selectedCss != null){
			this.selectStartTag = "<" + this.tagElement + " css='" + this.selectedCss + "'>";
		}
	}
	/**
	 * 내부 멤버 필드에 대한 초기화 작업 수행. 이 작업이 수행된 후, 각각의 값이 채워짐 
	 * 객체를 생성한 후, 반드시 호출필요
	 */
	public void initPagination(){
		this.pageTotalCount = (this.rowTotalCount /this.rowPerPage) + 
				(this.rowTotalCount %  this.rowPerPage ==0 ? 0 :1);
		this.startRow = (this.currentPage -1 ) * rowPerPage + 1;
		this.endRow = this.currentPage + rowPerPage;
		int numPageGroup = (int)Math.ceil((double)this.currentPage / pageGroupSize );
		this.startPage = (numPageGroup -1 )*this.pageGroupSize + 1; 
		int tmpEndPage = this.startPage * this.pageGroupSize -1;
		if (tmpEndPage > pageTotalCount) this.endPage = this.pageTotalCount;
	}

	/**  
	 * 뷰에서서 보여줄 Pagination Template을 생성
	 */
	public String generateTemplate(){
		if (this.rowTotalCount < 1) 
			return generateBlankTemplate();
		return generateTemplateString();
	}
	
	/**
	 * 뷰에서 보여줄 Pagination 스트링을 생성하는 private 메서드 
	 */
	private String generateTemplateString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<a href='" + this.url + "?" + this.pageParameterName + "=" + String.valueOf(1) + "'> 처음</a>");
		if (this.currentPage > 1){
			buffer.append("<a href='" + this.url + "?" + this.pageParameterName +
					"=" + String.valueOf(this.currentPage-1) + "'> 이전</a>");
		}
		
		for (int i = this.startPage; i <= this.endPage; i++){
			if (this.currentPage != i){
				buffer.append(this.startTag);
				buffer.append("<a href='" + this.url + "?" + this.pageParameterName + "=" + String.valueOf(i) + "'>");
				buffer.append(String.valueOf(i));
				buffer.append("</a>");
			}else {
				buffer.append(this.selectStartTag);
				buffer.append(String.valueOf(i));
			}
			buffer.append(this.endTag);
		}
		if (this.currentPage < this.pageTotalCount){
			buffer.append("<a href='" + this.url + "?" + this.pageParameterName +
					"=" + String.valueOf(this.currentPage+1) + "'> 다음</a>");
		}
		buffer.append("<a href='" + this.url + "?" + this.pageParameterName +
				"=" + String.valueOf(this.pageTotalCount) + "'>마지막</a>");
		return buffer.toString();
	}

	/**
	 * 페이지 네이션을 구성할 데이타가 없을 경우의 문자열을 반환하는 메서드 
	 */
	private String generateBlankTemplate() {
		return BLANK_TEMPLATE;
	}

	public int getRowTotalCount() {return rowTotalCount;}
	public void setRowTotalCount(int rowTotalCount) {this.rowTotalCount = rowTotalCount;}

	public int getPageTotalCount() {return pageTotalCount;}
	public void setPageTotalCount(int pageTotalCount) {this.pageTotalCount = pageTotalCount;}

	public int getCurrentPage() {return currentPage;}
	public void setCurrentPage(int currentPage) {this.currentPage = currentPage;}

	public int getRowPerPage() {return rowPerPage;}
	public void setRowPerPage(int rowPerPage) {this.rowPerPage = rowPerPage;}

	public int getStartPage() {return startPage;}
	public void setStartPage(int startPage) {this.startPage = startPage;}

	public int getEndPage() {return endPage;}
	public void setEndPage(int endPage) {this.endPage = endPage;}

	public String getCommonCss() {return commonCss;}
	public void setCommonCss(String commonCss) {this.commonCss = commonCss;}

	public String getStartTag() {return startTag;}
	public void setStartTag(String startTag) {this.startTag = startTag;}

	public String getEndTag() {return endTag;}
	public void setEndTag(String endTag) {this.endTag = endTag;}

	public int getStartRow() {return startRow;}
	public void setStartRow(int startRow) {this.startRow = startRow;}

	public int getEndRow() {return endRow;}
	public void setEndRow(int endRow) {this.endRow = endRow;}

	public int getPageGroupSize() {return pageGroupSize;}
	public void setPageGroupSize(int pageGroupSize) {this.pageGroupSize = pageGroupSize;}

	public String getUrl() {return url;}
	public void setUrl(String url) {this.url = url;}

	public String getPageParameterName() {return pageParameterName;}
	public void setPageParameterName(String pageParameterName) {this.pageParameterName = pageParameterName;}

	public String getSelectedCss() {return selectedCss;}
	public void setSelectedCss(String selectedCss) {this.selectedCss = selectedCss;}

	public String getTagElement() {return tagElement;}
	public void setTagElement(String tagElement) {this.tagElement = tagElement;}

	public String getSelectStartTag() {return selectStartTag;}
	public void setSelectStartTag(String selectStartTag) {this.selectStartTag = selectStartTag;}

	public String getSelectEndTag() {return selectEndTag;}
	public void setSelectEndTag(String selectEndTag) {this.selectEndTag = selectEndTag;
}
}
