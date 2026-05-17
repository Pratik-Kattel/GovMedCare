package com.govmedcare.model;

public class Report {
    private double revenue;
    private double taxAmount;
    public Report(){}


    public Report(double revenue, double taxAmount) {
        this.revenue = revenue;
        this.taxAmount = taxAmount;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }


}
