package com.codewithMantu.blog.payLoad;

public class FileResponse {
	private String FileName;
	private String Message;
	
	public FileResponse(String fileName, String Message) {
		
		this.FileName = fileName;
		this.Message= Message;
	}

	
	

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
	

	
	

}
