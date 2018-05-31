package package1.Threads;

import package1.modules.Binder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartBinder extends Thread  {

    private Binder binder;

     public StartBinder(Binder binder){

         this.binder=binder;

     }

    @Override
    public void run() {
        try{
        ServerSocket server = new ServerSocket(binder.getPortNumber());
        Socket conn=null;
        System.out.println("Binder is Started");

        ExecutorService service = Executors.newFixedThreadPool(10);
        while (true) {
            conn = server.accept();


            BufferedReader bufr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            PrintWriter bufw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
            String msg;
            while (!(msg = bufr.readLine()).equals("finish")) {
                if (msg != null)


                    bufw.println(binder.lookUpForServerIP(msg) + "-" + binder.lookUpForServerPort(msg));

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

