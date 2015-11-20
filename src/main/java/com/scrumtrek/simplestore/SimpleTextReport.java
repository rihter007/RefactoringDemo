package com.scrumtrek.simplestore;

/**
 * Created by Rihter on 20.11.2015.
 */
public class SimpleTextReport implements IReportCreator
{
    private String report;
    private double totalAmount = 0.0;
    private int frequentRenterPoints = 0;

    public void initialize(String title)
    {
        this.report = "Rental record for " + title + "\n";
    }

    public void addReportItem(ReportItem reportItem)
    {
        report += "\t" + reportItem.movieTitle + "\t" + reportItem.rentalPrice + "\n";
        totalAmount += reportItem.rentalPrice;
        frequentRenterPoints += reportItem.bonus;
    }

    public void finish(String optional)
    {
        report += "Amount owed is " + totalAmount + "\n";
        report += "You earned " + frequentRenterPoints + " frequent renter points.";
    }

    public String getReport()
    {
        return report;
    }
}
