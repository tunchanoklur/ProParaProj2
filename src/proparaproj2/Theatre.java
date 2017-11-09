package proparaproj2;

import java.util.*;
import java.io.*;

public class Theatre {
    public static void main(String[] args) {
        //Create 6 Show objects
        Show[][] shows= new Show[3][2]; //3 days, each with 2 time
        String time;
        Scanner user_in = new Scanner(System.in);
        int seats=0,checkpoint=0;
        boolean wrong_input;
        
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
        
        do{
            wrong_input = false;
                try{
                    System.out.printf("Checkpoint at transaction ID: ");
                    checkpoint = user_in.nextInt();
                    if(checkpoint<=0|| checkpoint>10)throw new NumberFormatException();
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
        
        for(int i=0;i<shows.length;i++){
            for(int j=0;j<shows[i].length;j++){
                if(j==0)time="afternoon";
                else time="evening";
                
                shows[i][j]=new Show(i+1,time,seats);
            }
        }
        
        //Create 3 TicketCounter
        TicketCounter[] counters=new TicketCounter[3];
        for(int i=0;i<counters.length;i++){
            counters[i]=new TicketCounter("Counter "+(i+1),shows,"C"+(i+1)+".txt");
            counters[i].start();
        }
        
        //check point
        
        
        //print summary
        try{
           counters[0].join();
           counters[1].join();
           counters[2].join();
       }
       catch (InterruptedException e){ }
        
        System.out.println("Summary");
        for(int i=0;i<shows.length;i++){
            for(int j=0;j<shows[i].length;j++){
                shows[i][j].printsummary(seats);
            }
        }
    }
}
