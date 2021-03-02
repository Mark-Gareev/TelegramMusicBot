package MainMusicAndDataWork;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Preparer {
    private String[] messages;
    public Queue<Message> messageQueue= new LinkedList<>();
    private long previousUpdateID;
    public long currentUpdateID;



    public Preparer(String in)
    {
        //Parsing Json to message queue with botapi message parameters;
        Pattern upd = Pattern.compile("\"update_id\":.+?,");
        Matcher updsrc = upd.matcher(in);
        while (updsrc.find()) {
            this.currentUpdateID = Integer.parseInt(in.substring(updsrc.start() + 12, updsrc.end() - 1));
        }
        this.previousUpdateID = currentUpdateID;
        //if(previousUpdateID<currentUpdateID)
        //{
            Pattern msg = Pattern.compile("\"message\":");
            messages = msg.split(in);
            if (messages.length != 1) {
                for (int i = 1; i < messages.length; i++) {
                    messageQueue.offer(new Message(messages[i]));
                    System.out.println(messageQueue.peek().getMessageText() +" "+ messageQueue.peek().getChatID());
                }
            }
        //}
    }
    public long getCurrentUpdateID(){
        return currentUpdateID;
    }

    public Queue<Message> getMessageQueue() {
        return messageQueue;
    }
}
