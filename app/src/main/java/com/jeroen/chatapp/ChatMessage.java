package com.jeroen.chatapp;

/**
 * Message to be displayed on the screen containing a name and message
 */
public class ChatMessage {
    private String name;
    private String message;

    public ChatMessage(String name, String message){
        this.name = name;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }
}
