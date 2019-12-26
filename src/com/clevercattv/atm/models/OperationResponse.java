package com.clevercattv.atm.models;

public class OperationResponse<T> {

    private T item;
    private boolean success;
    private String additionInformation;

    public OperationResponse(T item, boolean success, String additionInformation) {
        this.item = item;
        this.success = success;
        this.additionInformation = additionInformation;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
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
