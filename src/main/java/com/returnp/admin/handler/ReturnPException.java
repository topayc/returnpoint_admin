package com.returnp.admin.handler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "Forbidden Error!!!!")
public class ReturnPException extends RuntimeException {
    public ReturnPException() {
        super("Forbidden Error!!!!");
    }
    
    public ReturnPException(Exception e) {
        super("500 Server Error!!");
    }
}