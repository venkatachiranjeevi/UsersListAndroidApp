package com.test.pharmeasy.pharmeasytestapp.ui.presenters.impl;


import android.util.Log;

import com.test.pharmeasy.pharmeasytestapp.model.ApiInterface;
import com.test.pharmeasy.pharmeasytestapp.model.pojo.UsersData;
import com.test.pharmeasy.pharmeasytestapp.ui.adapters.UsersAdapter;
import com.test.pharmeasy.pharmeasytestapp.ui.presenters.UsersApiPresenter;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersApiPresenterImpl implements UsersApiPresenter {
    private ApiInterface apiService;

    public UsersApiPresenterImpl(final ApiInterface apiService) {
        this.apiService = apiService;
    }

    @Override
    public void loadUserData(final UsersData usersData, final UsersAdapter usersAdapter) {
        Call<UsersData> call = apiService.getUsers(usersData.getPage() + 1);
        call.enqueue(new Callback<UsersData>() {
            @Override
            public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                UsersData newUsersData = response.body();
                usersData.setPage(newUsersData.getPage());
                usersData.setTotalPages(newUsersData.getTotalPages());
                usersData.setResultsCount(newUsersData.getResultsCount());
                usersData.setListLoading(true);
                usersData.addToUsers(newUsersData.getUsers());
                usersAdapter.addUsers(response.body().getUsers());

            }

            @Override
            public void onFailure(Call<UsersData> call, Throwable t) {
                Log.e("MainActivity", "Request call failed");
            }

        });

    }
}
