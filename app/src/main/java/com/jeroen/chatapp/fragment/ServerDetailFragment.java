package com.jeroen.chatapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jeroen.applicatie.R;
import com.jeroen.chatapp.ServerListItem;

public class ServerDetailFragment extends Fragment {
    String TAG = "ServerDetailFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_server_detail,
                container, false);
    }

    public void setText(ServerListItem item) {
        TextView name = (TextView) getView().findViewById(R.id.text_name_data);
        TextView server = (TextView) getView().findViewById(R.id.text_server_data);
        name.setText(item.getName());
        server.setText(item.getServerUrl());
        Log.v(TAG, "Text set " + item.getName() + " " + item.getServerUrl());
    }
}
