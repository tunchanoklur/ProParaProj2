package proparaproj2;

import java.util.*;
import java.util.concurrent.CyclicBarrier;

public class Theatre {
    public static void main(String[] args) {
        //Create 6 Show objects
        Show[][] shows= new Show[3][2]; //3 days, each with 2 time
        String time;
        Scanner user_in = new Scanner(System.in);
        int seats=0,checkpoint=0;
        boolean wrong_input;
        //get theatre seats
        do{
            wrong_input = false;
                try{
                    System.out.printf("Maximum seats in the theatre: ");
                    seats = user_in.nextInt();
                    if(seats<=0)throw new NumberFormatException();
                }
                catch(NumberFormatException e){
                    System.out.println("Input not in range! please try again");
                    wrong_input = true;
                }
                catch(Exception e){
                    user_in.next();//read left over word
                    System.out.println("Wrong input type! please try again");
                    wrong_input = true;
                }
        }
        while(wrong_input);
        //get checkpoint for transaction id
        do{
            wrong_input = false;
                try{
                    System.out.printf("Checkpoint at transaction ID<1-10>: ");
                    checkpoint = user_in.nextInt();
                    if(checkpoint<1|| checkpoint>10)throw new NumberFormatException();
                }
                catch(NumberFormatException e){
                    System.out.println("Input not in range! please try again");
                    wrong_input = true;
                }
                catch(Exception e){
                    user_in.next();//read left over word
                    System.out.println("Wrong input type! please try again");
                    wrong_input = true;
                }
        }
        while(wrong_input);
        //create the shows
        for(int i=0;i<shows.length;i++){
            for(int j=0;j<shows[i].length;j++){
                if(j==0)time="afternoon";
                else time="evening";
                
                shows[i][j]=new Show(i+1,time,seats);
            }
        }
        
        //Create 3 TicketCounter + barrier for checkpoint
        TicketCounter[] counters=new TicketCounter[3];
        CyclicBarrier br = new CyclicBarrier(3);
        for(int i=0;i<counters.length;i++){
            counters[i]=new TicketCounter("Counter "+(i+1),shows,"C"+(i+1)+".txt",checkpoint);
            counters[i].setBarrier(br);
            counters[i].start();
        }
  
        //print summary
        try{
           counters[0].join();
           counters[1].join();
           counters[2].join();
       }
       catch (InterruptedException e){ }
        
        System.out.println("");
        System.out.println("Summary");
        for(int i=0;i<shows.length;i++){
            for(int j=0;j<shows[i].length;j++){
                shows[i][j].printsummary(seats);
            }
        }
    }
}
