package package1.rows;

public class ClientStubRow {




        private String address;
        private int portNumber;
        private String funName;
        private long timeInCashingTable;

        public ClientStubRow(String address, int portNumber, String funName,long timeInCashingTable) {
            this.address = address;
            this.portNumber = portNumber;
            this.funName = funName;
            this.timeInCashingTable=timeInCashingTable;

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

        public void startTimerForCashing(ClientStubRow clientStubRow){



            long maxduration = 10000; // 10 seconds.
            long endtime = System.currentTimeMillis() + maxduration;

            while (System.currentTimeMillis() < endtime) {
                // ...
            }

        }



}
