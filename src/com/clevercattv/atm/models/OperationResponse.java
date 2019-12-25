package com.clevercattv.atm.models;

public class OperationResponse {

    private boolean success;
    private String additionInformation;

    public OperationResponse(boolean success, String additionInformation) {
        this.success = success;
        this.additionInformation = additionInformation;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAdditionInformation() {
        return additionInformation;
    }

    public void setAdditionInformation(String additionInformation) {
        this.additionInformation = additionInformation;
    }

}
