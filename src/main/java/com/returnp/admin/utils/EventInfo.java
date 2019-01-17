package com.returnp.admin.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventInfo {
	public enum EventStatus {
		ON("1"), OFF("2");
	
		private final String value;
		
		EventStatus(String value){
			this.value = value;
		}
		
		public String value() {
			return this.value;
		}
		
		public static EventStatus valueFrom(String value) throws Exception {
			if (value.equalsIgnoreCase("1")) {
				return ON;
			} else if (value.equalsIgnoreCase("2")) {
				return OFF;
			} else {
				throw new Exception("Unknown value : " + value);
			}
		}
	}
	
	EventStatus status() default EventStatus.ON;
	String description();
	String name();
	String key();
	String link();
	String startDate();
	String endDate();
}
