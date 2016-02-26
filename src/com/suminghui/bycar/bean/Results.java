package com.suminghui.bycar.bean;

public enum Results {
    result("result"),
    toPage("toPage"),
    status("status"),
    time("time"),
    errCode("error_code"),
    errMessage("error_message"),
    totalCount("total_count"),
    count("count"),
    url("search_result_url");

    private String value;

    public String getValue() {
        return this.value;
    }

    private Results(String value) {
        this.value = value;
    }
}
