package com.jeroen.chatapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jeroen.applicatie.R;

public class InfoFragment extends Fragment {
    @Nullable
    @Override //TODO Layout
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Make sure other views are gone
        container.removeAllViews();
        setHasOptionsMenu(false);
        //Inflate new view
        return inflater.inflate(R.layout.fragment_info, container, false);
    }
}