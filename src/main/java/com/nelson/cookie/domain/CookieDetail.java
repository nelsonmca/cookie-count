package com.nelson.cookie.domain;

import java.util.Date;

public class CookieDetail {
	
	
	
	public CookieDetail(String cookieValue, Date timeStamp) {
		super();
		this.cookieValue = cookieValue;
		this.timeStamp = timeStamp;
	}

	
	private String cookieValue;
	private Date timeStamp;
	
	public String getCookieValue() {
		return cookieValue;
	}
	public void setCookieValue(String cookieValue) {
		this.cookieValue = cookieValue;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cookieValue == null) ? 0 : cookieValue.hashCode());
		result = prime * result + ((timeStamp == null) ? 0 : timeStamp.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CookieDetail other = (CookieDetail) obj;
		if (cookieValue == null) {
			if (other.cookieValue != null)
				return false;
		} else if (!cookieValue.equals(other.cookieValue))
			return false;
		if (timeStamp == null) {
			if (other.timeStamp != null)
				return false;
		} else if (!timeStamp.equals(other.timeStamp))
			return false;
		return true;
	}
	
	
	
	
	
}
