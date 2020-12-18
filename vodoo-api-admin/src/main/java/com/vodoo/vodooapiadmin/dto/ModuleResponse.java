package com.vodoo.vodooapiadmin.dto;

public class ModuleResponse {
    private int result;
    private String errorDescription;
    private Object objectResponse;

    public ModuleResponse() {
    }

    public ModuleResponse(int result, String errorDescription, Object objectResponse) {
        this.result = result;
        this.errorDescription = errorDescription;
        this.objectResponse = objectResponse;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public Object getObjectResponse() {
        return objectResponse;
    }

    public void setObjectResponse(Object objectResponse) {
        this.objectResponse = objectResponse;
    }
}
