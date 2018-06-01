package package1.modules;

import package1.rows.BinderRow;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Binder {

    private String binderIp;
    private int portNumber;
    private ServerSocket serverSocket;

    private CopyOnWriteArrayList<BinderRow> binderRowsTable;

    public Binder(String binderIp, int portNumber) throws Exception {
        binderRowsTable = new CopyOnWriteArrayList<>();
        this.binderIp = binderIp;
        this.portNumber = portNumber;
        serverSocket=new ServerSocket(portNumber);


    }


    public void registerFunction(BinderRow binderRow) {
        binderRowsTable.add(binderRow);


    }

    public void deRegisterFunction(BinderRow binderRow) {

        for (BinderRow row:binderRowsTable){
            if(binderRow.getFunName().equals(row.getFunName()))
                binderRowsTable.remove(binderRow);
        }


    }


    public String lookUpForServerIP(String funName) {
        for (BinderRow binderRow : binderRowsTable) {
            if (binderRow.getFunName().equals(funName)) {
                return binderRow.getAddress();
            }
        }
        return " ";

    }

    public int lookUpForServerPort(String funName) {
        for (BinderRow binderRow : binderRowsTable) {
            if (binderRow.getFunName().equals(funName)) {
                return binderRow.getPortNumber();
            }
        }
        return  0;

    }

    public void ShutDown() throws Exception{
        serverSocket.close();

    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getBinderIp() {
        return binderIp;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}



