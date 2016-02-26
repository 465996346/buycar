package com.suminghui.bycar.bean;

public enum WebResults {
    status("status"), message("message"), errMessage("errMessage"), time("time"), url("url");

    private String value;

    public String getValue() {
        return this.value;
    }

    private WebResults(String value) {
        this.value = value;
    }
}
