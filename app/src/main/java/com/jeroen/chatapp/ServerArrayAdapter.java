package com.jeroen.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jeroen.applicatie.R;

import java.util.ArrayList;

/**
 * Created by Jeroen on 8-6-2015.
 */
public class ServerArrayAdapter extends ArrayAdapter<ServerListItem> {
    public ServerArrayAdapter(Context context, ArrayList<ServerListItem> serverlist){
        super(context,0,serverlist);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        //Message from this position
        ServerListItem item = getItem(position);
        //Check if view exists or else inflate
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_server, parent , false);
        }
        //View data
        TextView name = (TextView) convertView.findViewById(R.id.text_list);
        //Insert data in the view
        name.setText(item.getName());
        //Return view
        return convertView;
    }
}
