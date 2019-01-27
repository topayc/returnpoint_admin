package com.returnp.admin.dto;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.poi.util.SystemOutLogger;

public class QueryCondition{
	
	private boolean pagination = false;
	private int pageSize=10;
	private int page=0;
	private int total=0;	
	private int offset = 0;
	private String order;
	
	public boolean isPagination() {
		return pagination;
	}
	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}	
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}	
	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	public QueryCondition valueOf(Object obj) {

        Field[] fields = obj.getClass().getSuperclass().getDeclaredFields();
        for (int i = 0; i <= fields.length - 1; i++) {
        	
            fields[i].setAccessible(true);

            String key = fields[i].getName();
            Class<?> type = fields[i].getType();
            String methodString = "set"+key.substring(0,1).toUpperCase()+key.substring(1);
            Object value = "";
            try {
                value = fields[i].get(obj);
				if (this.getClass().getMethod(methodString, type)!=null && value != null) {
					Method method = this.getClass().getMethod(methodString, type);
					
					try {						
						method.invoke(this, value);
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}  
            } catch (IllegalArgumentException e) {
                    e.printStackTrace();
            } catch (IllegalAccessException e) {
                    e.printStackTrace();
            } catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            
        }
        return this;
	}
}
