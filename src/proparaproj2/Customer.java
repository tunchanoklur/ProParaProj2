package proparaproj2;

public class Customer{
    //no need to keep day since it will be keep in show
    public int trans_id; //tansaction id
    public String name;
    public int time;// 0 for afternoon, 1 for evening 
    public int seats_req;
    
    public Customer(int id,String n,int t,int rs/*required seat*/){
        trans_id = id;
        name = n;
        time=t;
        seats_req = rs;
    }
    public void changetime(){
        time++;
    }
    public void printbookinginfo(){
        System.out.printf("\t%-11s (%2d seats)\n",name,seats_req);
    }
}
