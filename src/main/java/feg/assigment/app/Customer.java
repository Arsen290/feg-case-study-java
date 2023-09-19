package feg.assigment.app;

public class Customer {
    private String name;
    private double revenue;
    public Customer (String name,double revenue){
            this.name=name;
            this.revenue=revenue;
    }
    public String getName() {
        return name;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}
