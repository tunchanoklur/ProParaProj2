package proparaproj2;

import java.util.*;
import java.io.*;

public class Theatre {
    public static void main(String[] args) {
        //Create 6 Show objects
        Show[][] shows= new Show[3][2]; //3 days, each with 2 time
        String time;
        Scanner user_in = new Scanner(System.in);
        int seats=0;
        boolean wrong_input;
        for(int i=0;i<shows.length;i++){
            for(int j=0;j<shows[i].length;j++){
                if(j==0)time="afternoon";
                else time="evening";
                do{
                    try{
                        wrong_input = false;
                        System.out.printf("Enter the number of seats for day %d, %s:",i+1,time);
                        seats = user_in.nextInt();
                        if(seats<0)throw new Exception();
                    }
                    catch(Exception e){
                        wrong_input = true;
                        System.err.println("Wrong input type! please try again");
                    }
                }
                while(wrong_input);
                
                shows[i][j]=new Show(i+1,time,seats);
            }
        }
        
    }
}
