package package1.rows;


import package1.modules.ClientStub;

public class BinderRow {

    private String address;
    private int portNumber;
    private String funName;

    public BinderRow(String address, int portNumber, String funName){
        this.address=address;
        this.portNumber=portNumber;
        this.funName=funName;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getFunName() {
        return funName;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return address+"-"+portNumber+"-"+funName;

    }


    public static void startTimerForCashing(BinderRow row, ClientStub clientStub){
        Thread t = new Thread() {
            public void run() {


                    long maxduration = 10000; // 10 seconds.
                    long endtime = System.currentTimeMillis() + maxduration;

                    while (System.currentTimeMillis() < endtime) ;
                    clientStub.deRegisterFunction(row);


            }
        };
        t.start();



    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof BinderRow))
            return false;
        BinderRow other = (BinderRow) obj;
        return address.equals(other.address) &&  portNumber == other.portNumber && funName.equals(other.funName) ;
    }

    public int hashCode() {
        return funName.hashCode();
    }
}
