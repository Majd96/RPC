package package1.modules;


import package1.rows.BinderRow;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientStub {


    private String clientStupIp;
    private ServerSocket serverSocket;
    private int portNumber;
    private Socket clientSocket;
    private CopyOnWriteArrayList<BinderRow> cashingTable;

    //HashMap table


    public ClientStub(String clientStupIp,int portNumber) throws Exception{
        System.out.println("Client Stup started");
        this.clientStupIp=clientStupIp;
        this.portNumber=portNumber;
        cashingTable=new CopyOnWriteArrayList<>();

    }

    public Package packageUpTheInputParameters(String funName,Object... args){
        //package up the input parameters
        //object... *args  arrys of args

        return new Package(funName,args);


    }

    public BinderRow contactTheBinder(Binder binder, String funName) throws Exception{
        System.out.println("the client try to connect the binder");

        Socket soc = new Socket(binder.getBinderIp(),binder.getPortNumber());

        BufferedReader bufr = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        PrintWriter bufw = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()));


                String msgServer;

                bufw.println(funName);

                bufw.flush();



            msgServer = bufr.readLine();




        bufw.println("finish");
        bufw.flush();
        bufr.close();
        bufw.close();
        String msgs[]=msgServer.split("-");




        BinderRow binderRow= new BinderRow(msgs[0],Integer.parseInt(msgs[1]),funName);
         cachTheResponse(new BinderRow(msgs[0],Integer.parseInt(msgs[1]),funName));
        System.out.println("new server is  cashed :::"+ "table size is: "+cashingTable.size());

         return binderRow;

    }

    public void cachTheResponse(BinderRow row){

            cashingTable.add(row);
            BinderRow.startTimerForCashing(row,this);
    }

    public void deRegisterFunction(BinderRow row) {


            for (BinderRow row1 : cashingTable) {
                if (row1.getFunName().equals(row.getFunName())) {
                    cashingTable.remove(row);
                    System.out.println("Server removed from cashing table ::::"+"table size is: "+cashingTable.size());

                }

        }

   }

    public String sendRquestToSeverStup(ServerStub serverStub, BinderRow binderRow, Package packge) {
        String msgServer="";
        try {


            System.out.println("the clientStub  try to connect the ServerStub");

            Socket soc = new Socket(serverStub.getServerStubIP(), serverStub.getPortNubmber());
            //soc.setSoTimeout(10000);

            BufferedReader bufr = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            PrintWriter bufw = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()));





            bufw.println(binderRow.toString() + "#" + packge.toString());

            bufw.flush();
            //TimeUnit.SECONDS.sleep(5);


            msgServer = bufr.readLine();





            bufw.println("finish");
            bufw.flush();
            bufr.close();
            bufw.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return msgServer;

    }

    public String decodeTheOutputFromResponse(){
        //package up the input parameters
        return null;


    }

    public BinderRow ifAlreadyExist(String funName){

        for (BinderRow row1 : cashingTable) {
            if (row1.getFunName().equals(funName))
                return row1;
        }
        return null;

    }


}
