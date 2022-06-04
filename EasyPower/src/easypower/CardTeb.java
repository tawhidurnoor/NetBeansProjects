package easypower;

public class CardTeb {
    public String day;
    public String month;
    public String year;
    public int ammount;
    public CardTeb(String day,String month,String year,int ammount){
        this.day = day;
        this.month = month;
        this.year = year;
        this.ammount = ammount;
    }
    public String getmonth(){
        return month;
    }
    public String getyear(){
        return year;
    }
    public String getday(){
        return day;
    }
    public int getammount(){
        return ammount;
    }
    
}
