package proparaproj2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TicketCounter extends Thread{
    private Show[][] shows;//2d array of show
    private Scanner scan;
    private CyclicBarrier finish;
    private int checkpoint;
    static private boolean cp_printed;
    
    public TicketCounter(String name,Show[][] show_in,String filename,int cp/*checkpoint*/){
        super(name);
        shows= new Show[3][2]; //3 days, each with 2 time
        shows=show_in;
        checkpoint=cp;
        cp_printed=false;
        try{
            scan=new Scanner(new File(filename));
        }
        catch(FileNotFoundException e){ 
                System.out.println(e);
        }
    }
    public void setBarrier(CyclicBarrier br){
        finish=br;
    }
    public void run(){
        String line,status;
        String[] buf;
        int id,day,time,req_seat;
        Customer[] booker = new Customer[1];
        boolean booked = false;
        while(scan!=null && scan.hasNext()){
            //get info from file
            line = scan.nextLine();
            buf = line.split("\\s+");
            id = Integer.parseInt(buf[0]);
            
            if(id==checkpoint){
                try{
                    finish.await();
                    if(!cp_printed){
                        cp_printed=true;
                        checkpoint_printing();
                    }
                    sleep(5);//let the printing thread finish printing first
                }
                catch(InterruptedException | BrokenBarrierException e){}
            }
            
            day = Integer.parseInt(buf[2]);
            if(buf[3].compareTo("afternoon")==0)time=0;
            else time=1;
            req_seat = Integer.parseInt(buf[4]);
            booker[0] = new Customer(id,buf[1],day,time,req_seat);
            //booking the seat
            for(int i=0;i<(2-time);i++){//if it is afternoon, we need to check 2 times(it can still take evening show)
                if(i==1)booker[0].changetime();//check for new time on same day
                booked = shows[day-1][time+i].bookSeats(req_seat,booker[0]);
                if(booked){
                    status="succeed";
                    printbooking(booker[0],status);
                    break;
                }
                else{
                    status="fail";
                    printbooking(booker[0],status);
                }
            }
        }
    }
    public void printbooking(Customer booker,String status){
        String time;
        if(booker.time==0)time="afternoon";
        else time="evening";
        System.out.printf("%-10s > #%2d %-10s books %2d seats for day %d (%-9s) -- %-10s\n",getName(),booker.trans_id,booker.name,booker.seats_req,booker.day,time,status);
    }
    public synchronized void checkpoint_printing(){
        System.out.println("");
        System.out.println("Checkpoint");
            for(int i=0;i<shows.length;i++){
                for(int j=0;j<shows[i].length;j++){
                    shows[i][j].printavailseat();
            }
        }
        System.out.println("");
    }
}
