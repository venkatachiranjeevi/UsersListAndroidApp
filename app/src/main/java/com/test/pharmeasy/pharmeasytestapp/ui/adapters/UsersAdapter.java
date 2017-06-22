package com.test.pharmeasy.pharmeasytestapp.ui.adapters;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.test.pharmeasy.pharmeasytestapp.R;
import com.test.pharmeasy.pharmeasytestapp.model.pojo.User;
import com.test.pharmeasy.pharmeasytestapp.ui.activities.UserDetailsActivity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<User> users;

    public UsersAdapter() {
        this.users = new ArrayList<>();

    }

    public void addUsers(final List<User> newUsers) {
        users.addAll(newUsers);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_profile_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = viewHolder.user;
                Intent intent = new Intent(v.getContext(), UserDetailsActivity.class);
                intent.putExtra("user", user);
                v.getContext().startActivity(intent);

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.user = users.get(position);
        viewHolder.firstName.setText(users.get(position).getFirstName());
        viewHolder.lastName.setText(users.get(position).getLastName());
        Picasso.with(viewHolder.avatar.getContext()).load(users.get(position).getProfilePic()).into(viewHolder.avatar);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public User getUser(int postion) {
        return users.get(postion);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.first_name)
        TextView firstName;
        @BindView(R.id.last_name)
        TextView lastName;
        @BindView(R.id.user_image)
        ImageView avatar;

        User user;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
