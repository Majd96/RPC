package package1.servers;

import package1.rows.BinderRow;
import package1.modules.Binder;

import java.net.ServerSocket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class MaxServer {
    private ExecutorService service;
    private String ip;
    private int portNumber;
    private String funName;
    private ServerSocket serverSocket;


    public MaxServer(String ip, int portNumber) throws Exception{
        this.ip=ip;
        this.portNumber=portNumber;
        service=Executors.newFixedThreadPool(100);
        funName="max";
        serverSocket=new ServerSocket(portNumber);


    }

    public int getPortNumber() {
        return portNumber;
    }

    public int performTheMax (String parametrs) throws Exception{
        return service.submit(new PerformTheMax(parametrs)).get();


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


class PerformTheMax implements Callable<Integer> {
    String parametrs;

    public PerformTheMax(String parametrs) {
        this.parametrs = parametrs;
    }

    @Override
    public Integer call() throws Exception {
        String arr[]=parametrs.split("-");
        int max=Integer.parseInt(arr[0]);
        for(String num:arr) {
            if (Integer.parseInt(num) > max)
                max = Integer.parseInt(num);
        }
        return max;
    }



}


