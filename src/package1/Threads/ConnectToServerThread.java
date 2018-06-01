package package1.Threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ConnectToServerThread implements Callable<Object> {

    private String msg;

    public ConnectToServerThread(String msg){
        this.msg=msg;
    }


    @Override
    public Object call() throws Exception {
        Object result=null;

        String arr[]=msg.split("#");
        String address=arr[0];
        String parameters=arr[1];
        String addressArray[]=address.split("-");
        System.out.println("the serverStub  try to connect the "+addressArray[2]+"Server");

        Socket soc = new Socket(addressArray[0],Integer.parseInt(addressArray[1]));

        BufferedReader bufr = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        PrintWriter bufw = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()));


        String msgServer;

        bufw.println(parameters);

        bufw.flush();



        msgServer = bufr.readLine();
        if (msgServer!=null)
        {

            result=msgServer;
            System.out.println("result from server:  "+ result);
            System.out.println("----------------------------------------------------");
        }



        bufw.println("finish");
        bufw.flush();
        bufr.close();
        bufw.close();
        return result;
    }
}