package MainMusicAndDataWork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionManager {

    private String conn;
    private String inputJSON = "";
    private String k;
    private long offset;
    private Preparer prep;
    private MusicWorker mess;


    public ConnectionManager()
    {
        this.conn =  "https://api.telegram.org/bot1358271942:AAFKWnVK8tghnlDVMNI7rBQab3m2r4N6GXo";

    }

    public void connectToTakeReq(){
        try {
            URL adr = new URL(conn + "/GetUpdates?offset="+(offset+1) );
            URLConnection toTakeReq = adr.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(toTakeReq.getInputStream()));
            while((k = in.readLine()) != null)
            {
                inputJSON += k;
            }
            System.out.println(inputJSON);
            in.close();
            this.prep = new Preparer(inputJSON);
            this.offset = prep.getCurrentUpdateID();
            //System.out.println("offset is :" + offset);
            inputJSON = "";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
    public void connectToResponse(){

        mess = new MusicWorker(prep.getMessageQueue(), conn);
        this.prep = null;
        this.mess = null;
    }
}
