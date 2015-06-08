package com.jeroen.chatapp.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jeroen.applicatie.R;
import com.jeroen.chatapp.ServerListItem;
import com.jeroen.chatapp.fragment.ServerDetailFragment;
import com.jeroen.chatapp.fragment.ServerListFragment;

public class ServerActivity extends ActionBarActivity implements ServerListFragment.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
    }

    @Override
    public void onItemSelected(ServerListItem item){
        ServerDetailFragment fragment = (ServerDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);
        if(fragment != null && fragment.isInLayout()){
            fragment.setText(item);
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    DetailActivity.class);
            intent.putExtra("Item", item);
            startActivity(intent);

        }
    }
}
