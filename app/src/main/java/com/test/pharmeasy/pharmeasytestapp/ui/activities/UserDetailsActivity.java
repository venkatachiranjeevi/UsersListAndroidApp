package com.test.pharmeasy.pharmeasytestapp.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.pharmeasy.pharmeasytestapp.R;
import com.test.pharmeasy.pharmeasytestapp.model.pojo.User;
import com.test.pharmeasy.pharmeasytestapp.ui.screen_mechanism.UserDetailsScreen;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailsActivity extends AppCompatActivity implements UserDetailsScreen {

    @BindView(R.id.first_name)
    TextView firstName;

    @BindView(R.id.last_name)
    TextView lastName;

    @BindView(R.id.user_image)
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("USER DETAILS");
        }
        ButterKnife.bind(this);
        User user = (User) getIntent().getSerializableExtra("user");
        fillUserDetails(user);
    }

    @Override
    public void fillUserDetails(User user) {
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        Picasso.with(this).load(user.getProfilePic()).into(avatar);
    }
}
