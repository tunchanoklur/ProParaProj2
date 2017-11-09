package proparaproj2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TicketCounter extends Thread{
    private Show[][] shows;//2d array of show
    private Scanner scan;
    
    public TicketCounter(String name,Show[][] show_in,String filename){
        super(name);
        shows= new Show[3][2]; //3 days, each with 2 time
        shows=show_in;
        try{
            scan=new Scanner(new File(filename));
        }
        catch(FileNotFoundException e){ 
                System.out.println(e);
        }
    }
    public void run(){
        
    }
    /*
    public void printname(){
        System.out.println(getName());
    }
    */
}
