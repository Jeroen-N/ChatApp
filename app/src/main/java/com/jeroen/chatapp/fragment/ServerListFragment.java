package com.jeroen.chatapp.fragment;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeroen.applicatie.R;

public class ServerListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the view
        return inflater.inflate(R.layout.fragment_server_list, container, false);
    }
}
