package com.jeroen.chatapp;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * An item which contains the url of a server and a name
 */
public class ServerListItem implements Serializable {
    private String name;
    private String serverUrl;

    public ServerListItem(String name, String serverUrl){
        this.name = name;
        this.serverUrl = serverUrl;
    }

    public String getName() {
        return name;
    }

    public String getServerUrl() {
        return serverUrl;
    }
}
