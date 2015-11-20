package com.scrumtrek.simplestore;

/**
 * Created by Rihter on 20.11.2015.
 */
public class ReportItem
{
    public ReportItem(String movieTitle, double rentalPrice, int bonus)
    {
        this.movieTitle = movieTitle;
        this.rentalPrice = rentalPrice;
        this.bonus = bonus;
    }

    public String movieTitle;
    public double rentalPrice;
    public int bonus;
}
