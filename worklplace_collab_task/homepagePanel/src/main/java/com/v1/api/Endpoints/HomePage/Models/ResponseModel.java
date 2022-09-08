package com.v1.api.Endpoints.HomePage.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseModel {
    @JsonProperty("Status")
    private boolean Status;

    public void setStatus(boolean status) {
        Status = status;
    }
}
