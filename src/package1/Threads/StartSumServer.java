package package1.Threads;




import package1.modules.Binder;
import package1.servers.SumServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;




public class StartSumServer extends Thread {

    private SumServer sumServer;
    private Binder binder;

    public StartSumServer(SumServer sumServer,Binder binder){

        this.sumServer = sumServer;
        this.binder=binder;

    }



    @Override
    public void run()  {
        try{


            sumServer.registerServer(binder);


        System.out.println("SumServer is Started");
        Socket conn=null;

        while (true) {
            conn = sumServer.getServerSocket().accept();


            BufferedReader bufr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            PrintWriter bufw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
            String msg;
            while (!(msg = bufr.readLine()).equals("finish")) {
                if (msg != null)


                    bufw.println(sumServer.performTheSum(msg));

                if (msg.equals("finish"))
                    break;
            }
            conn.close();
        }
            }catch(Exception e){
                e.printStackTrace();
            }


        }



    }


