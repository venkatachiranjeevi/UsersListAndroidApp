package com.test.pharmeasy.pharmeasytestapp.model.pojo;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UsersData {

    @SerializedName("page")
    private Integer page = 0;

    @SerializedName("per_page")
    private Integer resultsCount;

    @SerializedName("total_pages")
    private Integer totalPages;

    @SerializedName("data")
    private List<User> users;

    public UsersData() {
        this.users = new ArrayList<>();
    }

    private boolean listLoading = true;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getResultsCount() {
        return resultsCount;
    }

    public void setResultsCount(Integer resultsCount) {
        this.resultsCount = resultsCount;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addToUsers( List<User> users) {
        this.users.addAll(users);
    }

    public boolean isListLoading() {
        return listLoading;
    }

    public void setListLoading(boolean listLoading) {
        this.listLoading = listLoading;
    }
}
