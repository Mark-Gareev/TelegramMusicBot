package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Message {
    private long chatID;
    private String MessageText;
    public Message(String in){
        Pattern chat = Pattern.compile("\"chat\".+?,");
        Pattern text = Pattern.compile("\"text\".+?}");
        Matcher chatsearch = chat.matcher(in);
        Matcher textsearch = text.matcher(in);
        while(chatsearch.find())
            this.chatID = Integer.parseInt(in.substring(chatsearch.start()+13, chatsearch.end()-1));
        while(textsearch.find())
            this.MessageText = in.substring(textsearch.start()+8, textsearch.end()-2);
    }
    public String getMessageText(){
        return MessageText;
    }
    public long getChatID(){
        return chatID;
    }
}
//1024316979