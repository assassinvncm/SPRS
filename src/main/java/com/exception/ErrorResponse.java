package com.exception;

import java.util.ArrayList;
import java.util.List;



public class ErrorResponse {
	
	public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
	
	public ErrorResponse(int code,String message, List<String> details) {
        super();
        this.code = code;
        this.message = message;
        this.details = details;
    }
	
	private int code;
 
    //General error message about nature of error
    private String message;
 
    //Specific errors in API request processing
    private List<String> details;
 
    //Getter and setters
}
