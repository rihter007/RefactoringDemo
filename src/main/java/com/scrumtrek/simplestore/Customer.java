package com.scrumtrek.simplestore;

import java.util.ArrayList;
import java.util.List;

public class Customer
{

	private String name;
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name)
	{
		this.name = name;
	}

	public void addRental(Rental rental)
	{
        // we can create statement at this point, but I am lazy :)
		this.rentals.add(rental);
	}

    public String getName()
    {
        return this.name;
    }

	public void Statement(IReportCreator report, IPriceCalculator priceCalculator)
    {
        report.initialize(this.name);

        for (Rental rentalInfo : this.rentals)
        {
            report.addReportItem(new ReportItem(rentalInfo.getMovie().getTitle(), priceCalculator.calc(rentalInfo), getFrequentPointsForRental(rentalInfo)));
        }

        report.finish(null);
    }

    private static int getFrequentPointsForRental(Rental rental)
    {
        if ((rental.getMovie().getPriceCode() == PriceCodes.NewRelease) && (rental.getDaysRented() > 1))
            return 2;
        return 1;
    }
}

