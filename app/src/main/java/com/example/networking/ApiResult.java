package com.example.networking;

import java.util.ArrayList;

public class ApiResult {

    private Integer total_counts;
    private Boolean incomplete_results;
    private ArrayList<githubuser> items;

    public ApiResult(Integer total_counts, Boolean incomplete_results , ArrayList<githubuser> items){

        this.incomplete_results = incomplete_results;
        this.total_counts = total_counts;
        this.items = items;

    }

    public Integer getTotal_counts() {
        return total_counts;
    }

    public ArrayList<githubuser> getItems() {
        return items;
    }

    public Boolean getIncomplete_results() {
        return incomplete_results;
    }

    public void setTotal_counts(Integer total_counts) {
        this.total_counts = total_counts;
    }

    public void setIncomplete_results(Boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public void setItems(ArrayList<githubuser> items) {
        this.items = items;
    }
}
