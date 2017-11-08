package proparaproj2;

public class Customer{
    int trans_id; //tansaction id
    String name;
    int day; // 1 2 3
    String time;
    int seats_req;
    
    public Customer(int id,String n,int d,String t,int rs/*required seat*/){
        trans_id = id;
        name = n;
        day = d;
        time = t;
        seats_req = rs;
    }
}
