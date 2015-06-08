package com.jeroen.chatapp.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jeroen.applicatie.R;
import com.jeroen.chatapp.ServerListItem;
import com.jeroen.chatapp.fragment.ServerDetailFragment;
import com.jeroen.chatapp.fragment.ServerListFragment;
import com.jeroen.chatapp.fragment.SettingsFragment;

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

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        restoreActionBar();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new SettingsFragment()).commit();
            Toast.makeText(this, getString(R.string.settings), Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
