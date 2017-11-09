package proparaproj2;

import java.util.ArrayList;

public class Show{
    int day; //1 2 3
    String time; //afternoon evening
    int seats;
    ArrayList<Customer> customers;
    
    public Show(int d,String t,int seat_in){
        day=d;
        time=t;
        seats=seat_in;
        customers=new ArrayList<Customer>();
    }
    
    //Should be access by one thread at a time
    synchronized public boolean bookSeats(int booking,Customer booker){
        if(booking<=seats){
            seats -= booking;
            customers.add(booker);
            return true;
        }
        else{
            return false;
        }
    }
    public void printavailseat(){
        System.out.printf("Day %d (%-10s): available seats = %3d ",day,time,seats);
    }
    public void printsummary(){
        System.out.printf("Day %d (%-10s)\n",day,time);
        for(int i=0;customers.size()>i;i++){
            customers.get(i).printbookinginfo();
        }
    }
}
