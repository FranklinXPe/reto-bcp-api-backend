package com.bcp.tipocambio.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class TipoCambioNotFoundException extends  RuntimeException{
    public TipoCambioNotFoundException(String message){
        super(message);
    }
}
