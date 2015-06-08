package com.jeroen.chatapp.activity;

import android.support.v7.app.ActionBarActivity;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.jeroen.applicatie.R;
import com.jeroen.chatapp.ServerListItem;
import com.jeroen.chatapp.fragment.ServerDetailFragment;

public class DetailActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // check if activity has been switched to landscape mode
        // if yes, finish this activity
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ServerListItem item = (ServerListItem) extras.get("Item");
            ServerDetailFragment detailFragment = (ServerDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);
            detailFragment.setText(item);
        }
    }
}
