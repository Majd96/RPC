package package1.Threads;

import package1.modules.ClientStub;

import package1.modules.ServerStub;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class StartServerStup extends Thread {

    private ServerStub serverStub;

    public StartServerStup(ServerStub serverStub){

        this.serverStub=serverStub;

    }


    @Override
    public void run() {
        try{

        Socket conn=null;
        ExecutorService service=Executors.newFixedThreadPool(100);
        System.out.println("ServerStub is Started");

        while (true) {
            conn = serverStub.getServerSocket().accept();


            BufferedReader bufr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            PrintWriter bufw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()), true);
            String msg;
            while (!(msg = bufr.readLine()).equals("finish")) {
                if (msg != null)


                    bufw.println(service.submit(new ConnectToServerThread(msg)).get());
                //System.out.println(serverStub.connectToTheServer(msg).toString());

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

