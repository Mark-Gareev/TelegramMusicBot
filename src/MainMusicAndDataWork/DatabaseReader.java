package MainMusicAndDataWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseReader{

    private String URL = "jdbc:mysql://localhost/botdata?serverTimezone=Europe/Moscow&useSSL=false";
    private String user = "root";
    private String password = "qwerty";
    private Connection datacon;

    DatabaseReader()
    {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            datacon = DriverManager.getConnection(URL, user, password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public String getTrackWithMood(String mood)
    {
        String in = mood.substring(1,mood.length());
        System.out.println(in + " is the mood text");
        try
        {
            Statement statement = datacon.createStatement();
            String track = "raw";
            if(statement.execute("SELECT link FROM music WHERE mood = \'"+in+"\';"))
            {
                ResultSet res = statement.executeQuery("SELECT link FROM music WHERE mood = \'"+in+"\';");
                while(res.next())//there will be array of tracks, where one of them will be t
                {
                    track = res.getString("link");
                }
                statement.close();
                return track;
            }
            else
            {
                statement.close();
                return ("Incorrect query");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
    }


}
