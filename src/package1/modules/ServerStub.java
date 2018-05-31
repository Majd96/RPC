package package1.modules;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerStub {

    private String ServerStubIP;
    private int portNubmber;
    private ExecutorService service=Executors.newFixedThreadPool(100);

    public ServerStub(String serverStubIP, int portNubmber) {
        this.portNubmber = portNubmber;
        this.ServerStubIP = serverStubIP;
    }

    public int getPortNubmber() {
        return portNubmber;
    }

    public String getServerStubIP() {
        return ServerStubIP;
    }



}




