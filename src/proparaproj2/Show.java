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
    
    synchronized public int bookSeats(){
        return 0;
    }
}
