package com.rahulwadhwa238.gitsearch.controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import com.bumptech.glide.Glide;
import com.rahulwadhwa238.gitsearch.R;

public class ProfileDetails extends AppCompatActivity   {

    TextView link,username,description;
    ImageView dp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_detail);

        dp = findViewById(R.id.user_image);
        username = findViewById(R.id.username);
        link = findViewById(R.id.profile_link);
        description = findViewById(R.id.description);

        String LinkURL = getIntent().getExtras().getString("profile_url");
        link.setText(LinkURL);
        Linkify.addLinks(link, Linkify.WEB_URLS);
        link.setLinkTextColor(Color.parseColor("#ffffff"));

        String Username = getIntent().getExtras().getString("login");
        username.setText(Username);

        String AvatarURL = getIntent().getExtras().getString("avatar");
        Glide.with(this)
                .load(AvatarURL)
                .placeholder(R.drawable.load)
                .into(dp);

        String Description = getIntent().getExtras().getString("description");
        description.setText(Description);
    }

    private Intent createShareIntent()  {
        String Username = getIntent().getExtras().getString("login");
        String LinkURL = getIntent().getExtras().getString("profile_url");
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Checkout this profile @" + Username + ", " + LinkURL)
                .getIntent();

        return shareIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareIntent());
        return true;
    }
}
