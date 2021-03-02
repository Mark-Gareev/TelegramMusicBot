package MainMusicAndDataWork;


public class Bot extends Thread{
    private ConnectionManager con = new ConnectionManager();
    @Override
    public void run(){
        while(true)
        {
            try
            {
                con.connectToTakeReq();
                //con.workWithReq();
                con.connectToResponse();
                Thread.sleep(1000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
