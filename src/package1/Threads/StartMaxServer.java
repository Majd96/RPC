package package1.Threads;




import package1.modules.Binder;
import package1.servers.MaxServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.net.Socket;





public class StartMaxServer extends Thread {

    private MaxServer maxServer;
    private Binder binder;


    public StartMaxServer(MaxServer maxServer, Binder binder){

        this.maxServer = maxServer;
        this.binder=binder;

    }



    @Override
    public void run()  {
        try{
        maxServer.registerServer(binder);

        Socket conn=null;
        System.out.println("maxServer is Started");
        while (true) {

            conn = maxServer.getServerSocket().accept();


            BufferedReader bufr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            PrintWriter bufw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
            String msg;
            while (!(msg = bufr.readLine()).equals("finish")) {
                if (msg != null)


                    bufw.println(maxServer.performTheMax(msg));

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


