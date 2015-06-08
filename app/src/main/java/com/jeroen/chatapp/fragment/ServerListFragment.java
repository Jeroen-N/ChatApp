package com.jeroen.chatapp.fragment;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.jeroen.applicatie.R;
import com.jeroen.chatapp.ServerArrayAdapter;
import com.jeroen.chatapp.ServerListItem;

import java.util.ArrayList;

public class ServerListFragment extends ListFragment implements  AdapterView.OnItemClickListener {
    String TAG = "ServerListFragment";
    private OnItemSelectedListener listener;
    private ArrayAdapter<ServerListItem> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the view
        return inflater.inflate(R.layout.fragment_server_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<ServerListItem> serverListItems = new ArrayList<>();
        adapter = new ServerArrayAdapter(this.getActivity().getApplicationContext(), serverListItems);
        setListAdapter(adapter);
        Log.v(TAG, "Adapter set");
        adapter.add(new ServerListItem("Localhost", "http://10.0.2.2:3000/"));
        adapter.add(new ServerListItem("Socket.IO demo", "http://chat.socket.io/"));
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //Call the onItemSelected so that the activity gets the data from the interface
        listener.onItemSelected(adapter.getItem(i));
        Log.v(TAG, "Item clicked");
    }

    public interface OnItemSelectedListener {
        public void onItemSelected(ServerListItem item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement ServerListFragment.OnItemSelectedListener");
        }
    }

}
