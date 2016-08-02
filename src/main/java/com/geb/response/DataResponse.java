package com.geb.response;

public class DataResponse extends Response {
	private Object data;

	public DataResponse(String status, String response, Object data) {
		super(status, response);
		this.data = data;
	}
	
	protected DataResponse() {
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


}
