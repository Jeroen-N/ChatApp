package com.jeroen.chatapp.fragment;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jeroen.applicatie.R;
import com.jeroen.chatapp.ChatMessage;
import com.jeroen.chatapp.ServerArrayAdapter;
import com.jeroen.chatapp.ServerListItem;

import java.util.ArrayList;

public class ServerListFragment extends ListFragment implements  AdapterView.OnItemClickListener {
    String TAG = "ServerListFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the view
        return inflater.inflate(R.layout.fragment_server_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<ServerListItem> serverListItems = new ArrayList<>();
        ArrayAdapter adapter = new ServerArrayAdapter(this.getActivity().getApplicationContext(), serverListItems);
        setListAdapter(adapter);
        adapter.add(new ServerListItem("Test 1", "1111"));
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getActivity(), "Number " + i, Toast.LENGTH_SHORT);
        Log.v(TAG, "Item clicked");
    }
}
