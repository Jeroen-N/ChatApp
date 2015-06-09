package com.jeroen.chatapp.activity;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

import com.example.jeroen.applicatie.R;
import com.jeroen.chatapp.fragment.ChatFragment;
import com.jeroen.chatapp.fragment.InfoFragment;
import com.jeroen.chatapp.fragment.NavigationDrawerFragment;
import com.jeroen.chatapp.fragment.ServerListFragment;
import com.jeroen.chatapp.fragment.SettingsFragment;

public class MainActivity extends ActionBarActivity

        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private String TAG = "MainActivity";

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Log.v(TAG, "Item selected " + position);
        //Replace container with selected fragment from drawer menu
        switch(position){
            case 0:
                fragmentManager.beginTransaction().replace(R.id.container, new InfoFragment()).commit();
                mTitle = getString(R.string.title_section1);
                break;
            case 1:
                fragmentManager.beginTransaction().replace(R.id.container, new ChatFragment()).commit();
                mTitle = getString(R.string.title_section2);
                break;
            case 2:
                Intent intent = new Intent(this, ServerActivity.class);
                startActivity(intent);
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main_drawer, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.action_settings:
                Log.d(TAG,"settings_action");
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new SettingsFragment()).commit();
                Toast.makeText(this, getString(R.string.settings), Toast.LENGTH_SHORT).show();
            return true;
            case R.id.action_attach:
                Log.d(TAG,"attach_action");
                Intent galleryintent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryintent, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
