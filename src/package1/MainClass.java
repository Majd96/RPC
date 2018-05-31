package package1;

import package1.GUI.MainForm;
import package1.Threads.StartBinder;
import package1.Threads.StartSumServer;
import package1.Threads.StartServerStup;
import package1.Threads.StartMaxServer;
import package1.modules.*;
import package1.modules.Package;
import package1.rows.BinderRow;
import package1.servers.MaxServer;
import package1.servers.SumServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {

    static boolean binderFlag=false;
    private static ClientStub clientStub;
    private static Binder binder;
    private static BinderRow binderRow;




    public static void main(String args[]) throws Exception{
        clientStub= new ClientStub("localhost", 5001);
        binder=new Binder("localhost",5540);
        StartBinder startBinder=new StartBinder(binder);
        startBinder.start();

        MaxServer maxServer =new MaxServer("localhost",8006);
        StartMaxServer startMaxServer =new StartMaxServer(maxServer,binder);
        startMaxServer.start();


        ExecutorService service=Executors.newFixedThreadPool(3);
        //start the sumServer
        SumServer sumServer =new SumServer("localhost",8005);
        service.submit(new StartSumServer(sumServer,binder));


        //start the binder

        //startBinder.join();
        //TimeUnit.SECONDS.sleep(5);

        //start the stub sumServer
        ServerStub serverStub=new ServerStub("localhost",5000);
        StartServerStup startServerStup =new StartServerStup(serverStub);
        startServerStup.start();
        //startServerStup.join();


        //startMaxServer.join();




            MainForm mainForm=new MainForm();
            JFrame jFrame=new JFrame();
            jFrame.setContentPane(mainForm.rootJpanel);
            jFrame.setSize(600,600);
            jFrame.setVisible(true);


            mainForm.findTheAverageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        String num=mainForm.numberstextField.getText().toString();
                        String nums[]=num.split(" ");
                        Integer integers[]=new Integer[nums.length];
                        for (int i=0;i<nums.length;i++){
                            integers[i]=Integer.parseInt(nums[i]);

                        }



                        Package pakage = clientStub.packageUpTheInputParameters("sum", integers);



                        if (clientStub.ifAlreadyExist(pakage.getFunName()) != null) {
                            binderRow = clientStub.ifAlreadyExist(pakage.getFunName());
                            System.out.println("took from the cashingTable");
                        } else {
                            binderRow = clientStub.contactTheBinder(binder, pakage.getFunName());
                            System.out.println("took from the Binder");
                        }

                        String sum=clientStub.sendRquestToSeverStup(serverStub, binderRow, pakage);
                        mainForm.averageResultLabel.setText(String.valueOf(Double.parseDouble(sum)/nums.length));

                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                }
            });

            mainForm.findTheMaxButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    try {


                        String num = mainForm.numberstextField.getText().toString();
                        String nums[] = num.split(" ");
                        Integer integers[] = new Integer[nums.length];
                        for (int i = 0; i < nums.length; i++) {
                            integers[i] = Integer.parseInt(nums[i]);

                        }


                        Package pakage = clientStub.packageUpTheInputParameters("max", integers);


                        if (clientStub.ifAlreadyExist(pakage.getFunName()) != null) {
                            binderRow = clientStub.ifAlreadyExist(pakage.getFunName());
                            System.out.println("took from the cashingTable");
                        } else {
                            binderRow = clientStub.contactTheBinder(binder, pakage.getFunName());
                            System.out.println("took from the Binder");
                        }

                        String max = clientStub.sendRquestToSeverStup(serverStub, binderRow, pakage);
                        mainForm.maxLabel.setText(max);


                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                }


            });



        /*


        BinderRow binderRow;
        Package pakage2=clientStub.packageUpTheInputParameters("sub",new Integer[]{1,2,3});
        if(clientStub.ifAlreadyExist(pakage2.getFunName())!=null){
            binderRow=clientStub.ifAlreadyExist(pakage2.getFunName());
            System.out.println("took from the cashingTable"+binderRow.getFunName());
        }
        else {
            binderRow= clientStub.contactTheBinder(binder, pakage2.getFunName());
            System.out.println("took from the Binder");
        }

        clientStub.sendRquestToSeverStup(serverStub, binderRow,pakage2);


        TimeUnit.SECONDS.sleep(15);

        Package p=clientStub.packageUpTheInputParameters("sub",new Integer[]{1,2,3});
        if(clientStub.ifAlreadyExist(p.getFunName())!=null){
            binderRow=clientStub.ifAlreadyExist(p.getFunName());
            System.out.println("took from the cashingTable"+binderRow.getFunName());
        }
        else {
            binderRow= clientStub.contactTheBinder(binder, p.getFunName());
            System.out.println("took from the Binder");
        }

        clientStub.sendRquestToSeverStup(serverStub, binderRow,p);

        */


















    }

}
