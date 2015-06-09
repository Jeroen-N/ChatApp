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
 * Created by Jeroen on 4-6-2015.
 * Adapter array used to fill the chat screen with messages
 */
public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {

    public ChatArrayAdapter(Context context, ArrayList<ChatMessage> messages){
        super(context, 0, messages);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        //Message from this position
        ChatMessage message = getItem(position);
        //Check if view exists or else inflate
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_message, parent , false);
        }
        //View data
        TextView nameMessage = (TextView) convertView.findViewById(R.id.messageName);
        TextView textMessage = (TextView) convertView.findViewById(R.id.messageText);
        //Insert data in the view
        nameMessage.setText(message.getName());
        textMessage.setText(message.getMessage());
        //Return view
        return convertView;
    }
}
