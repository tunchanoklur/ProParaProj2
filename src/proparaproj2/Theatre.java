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
                    user_in.nextLine(); //read left over /r
                    if(seats<=0)throw new Exception();
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
                    user_in.nextLine(); //read left over /r
                    if(checkpoint<=0|| checkpoint>10)throw new Exception();
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
        //String filename;
        for(int i=0;i<counters.length;i++){
            /*
            switch (i) {
                case 0 : filename="C1.txt";break;
                case 1 : filename="C2.txt";break;
                default: filename="C3.txt";break;
            }*/
            counters[i]=new TicketCounter("Counter "+(i+1),shows,"C"+(i+1)+".txt");
            //counters[i].printname();
            //counters[i].start();
        }
        
        
    }
}
