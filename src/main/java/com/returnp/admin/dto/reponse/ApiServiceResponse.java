package com.returnp.admin.dto.reponse;

import java.util.ArrayList;

public class ApiServiceResponse<T> extends ReturnpBaseResponse{
	public int total;
	public ArrayList<T> rows;

	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public ArrayList<T> getRows() {
		return rows;
	}
	public void setRows(ArrayList<T> rows) {
		this.rows = rows;
	}
	
	T object;

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
	
	public String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
