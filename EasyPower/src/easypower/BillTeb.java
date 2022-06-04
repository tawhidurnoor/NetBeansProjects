package easypower;

public class BillTeb {
    private String month;
    private String year;
    private int bill;
    private String status;
    public BillTeb(String month,String year,int bill,String status){
        this.month = month;
        this.year = year;
        this.bill = bill;
        this.status = status;
    }
    public String getmonth(){
        return month;
    }
    public String getyear(){
        return year;
    }
    public int getbill(){
        return bill;
    }
    public String getstatus(){
        return status;
    }
    
}
