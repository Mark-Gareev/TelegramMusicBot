package com.company;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Queue;



public class MusicWorker {
    private Message message;
    public MusicWorker(Queue<Message> queue, String conn){
        try {
            while (!(queue.isEmpty())) {
                this.message = queue.poll();
                System.out.println(message.getChatID());
                System.out.println();
                URL url = new URL(conn + "/sendMessage?chat_id="+message.getChatID()+"&text="+message.getMessageText());
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                System.out.println(con.getResponseCode());
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String k;
                System.out.println("OutFromTeleg");
                while ( (k = reader.readLine()) != null)
                    System.out.print(k);
                System.out.println();
                con = null;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
