package MainMusicAndDataWork;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Queue;



public class MusicWorker {
    private Message message;
    private String text;
    DatabaseReader reader;
    public MusicWorker(Queue<Message> queue, String conn){
        try {
            reader = new DatabaseReader();
            while (!(queue.isEmpty())) {
                this.message = queue.poll();
                System.out.println(message.getChatID());
                System.out.println(message.getMessageText()+" is text of message");
                //there will be data work;
                if(message.getMessageText().equals("/start"))
                {
                    text = "SomeGreetingsForYou";
                }
                else
                {
                    text = reader.getTrackWithMood(message.getMessageText());
                }
                URL url = new URL(conn + "/sendMessage?chat_id="+message.getChatID()+"&text="+text);
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                System.out.println(con.getResponseCode());

                con = null;
                text = "";
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
