package com.scrumtrek.simplestore;

import java.util.ArrayList;
import java.util.List;

public class Customer
{
	private static final double REGULAR_INITIAL_BONUS = 2;
	private static final double REGULAR_MIN_BONUS_DAYS = 2;
	private static final double REGULAR_DAY_BONUS = 1.5;

	private static final double NEW_RELEASE_DAY_BONUS = 3;

	private static final double CHILDRENS_INITIAL_BONUS = 1.5;
	private static final double CHILDRENS_MIN_BONUS_DAYS = 3;
	private static final double CHILDRENS_DAY_BONUS = 1.5;

	private String name;
	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	public void addRental(Rental arg)
	{
		this.rentals.add(arg);
	}

	public String Statement()
	{
		double totalAmount = 0;
		int frequentRenterPoints = 0;

		String result = "Rental record for " + this.name + "\n";

		for (Rental each : this.rentals)
		{
			double thisAmount = getBonusForTargetPriceCode(each.getMovie().getPriceCode(), each.getDaysRented());

			// Add frequent renter points
			frequentRenterPoints++;

			// Add bonus for a two-day new-release rental
			if ((each.getMovie().getPriceCode() == PriceCodes.NewRelease) && (each.getDaysRented() > 1))
			{
				frequentRenterPoints++;
			}

			// Show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}

		// Add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points.";
		return result;
	}

    private double getBonusForTargetPriceCode(PriceCodes priceCode, int daysRented)
    {
        switch (priceCode)
        {
            case Regular:
                return calculateBonusForRegularPriceCode(daysRented);

            case NewRelease:
                return calculateBonusForNewReleasePriceCode(daysRented);

            case Childrens:
                return calculateBonusForChildrensPriceCode(daysRented);
        }

        return 0;
    }

	private double calculateBonusForRegularPriceCode(int daysRented)
	{
		double resultAmount = REGULAR_INITIAL_BONUS;
		if (daysRented > REGULAR_MIN_BONUS_DAYS)
			resultAmount += (daysRented - REGULAR_MIN_BONUS_DAYS) * REGULAR_DAY_BONUS;

		return resultAmount;
	}

	private double calculateBonusForNewReleasePriceCode(int daysRented)
	{
		return daysRented * NEW_RELEASE_DAY_BONUS;
	}

	private double calculateBonusForChildrensPriceCode(int daysRented)
	{
		double resultAmount = CHILDRENS_INITIAL_BONUS;
		if (daysRented > CHILDRENS_MIN_BONUS_DAYS)
			resultAmount += (daysRented - CHILDRENS_MIN_BONUS_DAYS) * CHILDRENS_DAY_BONUS;
		return resultAmount;
	}
}

