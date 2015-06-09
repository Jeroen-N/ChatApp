package com.jeroen.chatapp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jeroen.applicatie.R;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.jeroen.chatapp.ChatArrayAdapter;
import com.jeroen.chatapp.ChatMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Chat Fragment used to display the chat and type new messages
 */
public class ChatFragment extends Fragment {
    private ChatArrayAdapter chatAdapter;
    private ListView listView;
    //User details
    private boolean loggedIn = false;
    private String username;
    //Log TAG
    private String TAG = "ChatFragment";
    //Socket
    private Socket chatSocket;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Make sure other views are gone
        container.removeAllViews();
        //Inflate new view
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        //Get username
        getActivity().getApplicationContext();
        SharedPreferences preferences = this.getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        username = preferences.getString("name_preference", getString(R.string.default_name));
        try {
            //Server socket IP chatserver
            chatSocket = IO.socket(preferences.getString("server_ip", "http://10.0.2.2:3000/"));
        } catch (URISyntaxException ignored) {}

        //Socket listener
        chatSocket.on("new message", onNewMessage);
        chatSocket.on("user joined", onUserJoined);
        chatSocket.on("user left", onUserLeft);
        chatSocket.connect();
        Log.i(TAG, "Found username " + username);
        /* Return the view */
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Arraylist of messages
        ArrayList<ChatMessage> chatMessages = new ArrayList<>();
        //Create adapter
        chatAdapter = new ChatArrayAdapter(this.getActivity().getApplicationContext(), chatMessages);
        //Fetch listview
        listView = (ListView) view.findViewById(R.id.listView);
        //Set adapter to listview
        listView.setAdapter(chatAdapter);
        //Add listener to button
        Button button = (Button) view.findViewById(R.id.chatButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Send message");
                // Send the message
                sendMessage();
            }
        });

        //If user not logged in log in and change boolean
        if(!loggedIn){
            Log.d(TAG, "Log in" + username);
            chatSocket.emit("add user", username);
            loggedIn = true;
        }
    }

    //Listening if any new messages arrive
    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        username = data.getString("username");
                        message = data.getString("message");
                        Log.d(TAG, "Found user " + username + " message " + message);
                    } catch (JSONException e) {
                        return;
                    }
                    chatAdapter.add(new ChatMessage(username, message));
                }
            });
        }
    };

    //Listening if user joined
    private Emitter.Listener onUserJoined = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    int nrUsers;
                    try {
                        username = data.getString("username");
                        nrUsers = data.getInt("numUsers");
                        Log.d(TAG, "Found user " + username + " joining | nr of users:" + nrUsers);
                    } catch (JSONException e) {
                        return;
                    }
                    chatAdapter.add(new ChatMessage("", username + " " + getString(R.string.joined)));
                }
            });
        }
    };

    //Listening if user left
    private Emitter.Listener onUserLeft = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    int nrUsers;
                    try {
                        username = data.getString("username");
                        nrUsers = data.getInt("numUsers");
                        Log.d(TAG, "Found user " + username + " leaving | nr of users: " + nrUsers);
                    } catch (JSONException e) {
                        return;
                    }
                    chatAdapter.add(new ChatMessage("", username + " " + getString(R.string.left)));
                }
            });
        }
    };

    public void sendMessage(){
        EditText text = (EditText) getActivity().findViewById(R.id.editMessage);
        String message = text.getText().toString();
        //Add the message to own client
        chatAdapter.add(new ChatMessage(username,message));
        // Select the last row so that the latest message appears on screen
        listView.setSelection(chatAdapter.getCount() - 1);
        //Empty the textfield
        text.setText("");
        //Send the message
        chatSocket.emit("new message", message);
        Log.d(TAG, "Message sent " + message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Disconnect and stop listening
        chatSocket.disconnect();
        chatSocket.off("user joined", onUserJoined);
        chatSocket.off("user left", onUserLeft);
        chatSocket.off("new message");
    }
}
