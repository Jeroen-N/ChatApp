package com.jeroen.chatapp.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;

import com.example.jeroen.applicatie.R;

import java.util.List;

public class SettingsFragment extends PreferenceFragment {
    private String TAG = "Settings";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setHasOptionsMenu(false);
        // Define the settings file to use by this settings fragment
        getPreferenceManager().setSharedPreferencesName("preferences");

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Make sure other views are gone
        container.removeAllViews();

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
