package proparaproj2;

public class Customer{
    public int trans_id; //tansaction id
    public String name;
    public int day; // 1 2 3
    public int time;// 0 for afternoon, 1 for evening 
    public int seats_req;
    
    public Customer(int id,String n,int d,int t,int rs/*required seat*/){
        trans_id = id;
        name = n;
        day = d;
        time=t;
        seats_req = rs;
    }
    public void changetime(){
        time++;
    }
}
