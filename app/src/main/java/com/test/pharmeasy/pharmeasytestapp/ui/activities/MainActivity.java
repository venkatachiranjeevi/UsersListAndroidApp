package com.test.pharmeasy.pharmeasytestapp.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.test.pharmeasy.pharmeasytestapp.R;
import com.test.pharmeasy.pharmeasytestapp.model.ApiInterface;
import com.test.pharmeasy.pharmeasytestapp.model.ApiService;
import com.test.pharmeasy.pharmeasytestapp.model.pojo.UsersData;
import com.test.pharmeasy.pharmeasytestapp.ui.adapters.UsersAdapter;
import com.test.pharmeasy.pharmeasytestapp.ui.presenters.UsersApiPresenter;
import com.test.pharmeasy.pharmeasytestapp.ui.presenters.impl.UsersApiPresenterImpl;
import com.test.pharmeasy.pharmeasytestapp.ui.screen_mechanism.UserListScreen;


import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements UserListScreen {

    @BindView(R.id.user_profile_recycler_view)
    RecyclerView usersRecyclerView;

    UsersApiPresenter usersApiPresenter;

    private LinearLayoutManager layoutManager;

    UsersAdapter usersAdapter;

    UsersData usersData = new UsersData();

    ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("USERS");
        }
        ButterKnife.bind(this);
        apiService =
                ApiService.getService().create(ApiInterface.class);

        initRecyclerView();
        implementScrollListener();
        usersApiPresenter = new UsersApiPresenterImpl(apiService);
    }

    @Override
    protected void onResume() {
        super.onResume();
        usersApiPresenter.loadUserData(usersData, usersAdapter);
    }

    @Override
    public void appendNewUsersData(final UsersData newUsersData) {
        usersData.setPage(newUsersData.getPage());
        usersData.setTotalPages(newUsersData.getTotalPages());
        usersData.setResultsCount(newUsersData.getResultsCount());
        usersData.addToUsers(newUsersData.getUsers());
    }

    public void initRecyclerView() {
        usersRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager((usersRecyclerView.getContext()));
        usersRecyclerView.setLayoutManager(layoutManager);
        usersRecyclerView.setItemAnimator(new DefaultItemAnimator());
        usersAdapter = new UsersAdapter();
        usersRecyclerView.setAdapter(usersAdapter);
    }

    public void implementScrollListener() {
        usersRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                    if (usersData.isListLoading() && (firstVisibleItem + visibleItemCount) >= totalItemCount && usersData.getPage() < usersData.getTotalPages()) {
                        usersData.setListLoading(false);
                        usersApiPresenter.loadUserData(usersData, usersAdapter);
                    }
                }
            }

        });
    }

}
