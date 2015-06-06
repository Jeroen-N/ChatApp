package com.jeroen.chatapp;

/**
 * An item which contains the url of a server and a name
 */
public class ServerListItem {
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
