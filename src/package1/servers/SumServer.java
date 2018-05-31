package package1.servers;

import package1.rows.BinderRow;
import package1.modules.Binder;

import java.net.ServerSocket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SumServer {


    private ExecutorService service;
    private String ip;
    private int portNumber;
    private String funName;
    private ServerSocket serverSocket;


    public SumServer(String ip, int portNumber) throws Exception{
       this.ip=ip;
       this.portNumber=portNumber;
       service =Executors.newFixedThreadPool(100);
       funName="sum";
       serverSocket=new ServerSocket(portNumber);


    }

    public int getPortNumber() {
        return portNumber;
    }

    public int performTheSum (String parametrs) throws Exception{
        return service.submit(new PerformTheSum(parametrs)).get();


    }

    public void registerServer(Binder binder){

        binder.registerFunction(new BinderRow(ip, portNumber, funName));
    }
    public void shutDownServer(Binder binder) throws Exception{

        System.out.println("sumServer is shout down ");
       binder.deRegisterFunction(new BinderRow(ip,portNumber,funName));
        serverSocket.close();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}


class PerformTheSum implements Callable<Integer>{
    String parametrs;

    public PerformTheSum(String parametrs){
        this.parametrs=parametrs;
    }

    @Override
    public Integer call() throws Exception {

        String arr[]=parametrs.split("-");
        int sum=0;
        for(String num:arr)
           sum+=Integer.parseInt(num);
        return sum;
    }


}


