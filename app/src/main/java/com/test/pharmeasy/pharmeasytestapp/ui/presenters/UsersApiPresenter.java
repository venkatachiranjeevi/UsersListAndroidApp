package com.test.pharmeasy.pharmeasytestapp.ui.presenters;


import com.test.pharmeasy.pharmeasytestapp.model.pojo.UsersData;
import com.test.pharmeasy.pharmeasytestapp.ui.adapters.UsersAdapter;

public interface UsersApiPresenter {
    void loadUserData(final UsersData usersData, final UsersAdapter usersAdapter);
}
