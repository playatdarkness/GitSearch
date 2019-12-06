package com.rahulwadhwa238.gitsearch.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rahulwadhwa238.gitsearch.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button search;
    public EditText query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ProfileSearchResult.class);
        query = findViewById(R.id.query);
        String q = query.getText().toString();
        if (TextUtils.isEmpty(q))   {
            query.setError("Search field can't be empty!");
        }
        else    {
            intent.putExtra("query",q);
            startActivity(intent);
        }
    }

}
