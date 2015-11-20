package com.scrumtrek.simplestore;

import java.util.ArrayList;
import java.util.List;

public class Customer
{
    private static class RegularPriceCalculus
    {
        private static final double REGULAR_INITIAL_BONUS = 2;
        private static final double REGULAR_MIN_BONUS_DAYS = 2;
        private static final double REGULAR_DAY_BONUS = 1.5;

        public static double calculate(int daysRented)
        {
            double resultAmount = REGULAR_INITIAL_BONUS;
            if (daysRented > REGULAR_MIN_BONUS_DAYS)
                resultAmount += (daysRented - REGULAR_MIN_BONUS_DAYS) * REGULAR_DAY_BONUS;

            return resultAmount;
        }
    }

    private static class NewReleasePriceCalculus
    {
        private static final double NEW_RELEASE_DAY_BONUS = 3;

        public static double calculate(int daysRented)
        {
            return daysRented * NEW_RELEASE_DAY_BONUS;
        }
    }

    private static class ChildrenPriceCalculus
    {
        private static final double CHILDRENS_INITIAL_BONUS = 1.5;
        private static final double CHILDRENS_MIN_BONUS_DAYS = 3;
        private static final double CHILDRENS_DAY_BONUS = 1.5;

        public static double calculate(int daysRented)
        {
            double resultAmount = CHILDRENS_INITIAL_BONUS;
            if (daysRented > CHILDRENS_MIN_BONUS_DAYS)
                resultAmount += (daysRented - CHILDRENS_MIN_BONUS_DAYS) * CHILDRENS_DAY_BONUS;
            return resultAmount;
        }
    }

    private class RentalInfo
    {
        public String movieTitle;
        public double totalAmount;
        public int frequentPoints;

        public RentalInfo(Rental rental)
        {
            this.movieTitle = rental.getMovie().getTitle();
            this.totalAmount = getBonusForTargetRental(rental);
            this.frequentPoints = getFrequentPointsForRental(rental);
        }
    }

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

    /*public double getTotalPayment()
    {
        double totalAmount = 0.0;
        for (Rental rentalInfo : this.rentals)
            totalAmount += rentalInfo.totalAmount;

        return totalAmount;
    }

    public int getTotalBonus()
    {
        int frequentRenterPoints = 0;
        for (Rental rentalInfo : this.rentals)
            frequentRenterPoints += rentalInfo.frequentPoints;

        return frequentRenterPoints;
    }*/

	public void Statement(IReportCreator report)
    {
        report.initialize(this.name);

        for (Rental rentalInfo : this.rentals)
        {
            report.addReportItem(new ReportItem(rentalInfo.getMovie().getTitle(), getBonusForTargetRental(rentalInfo), getFrequentPointsForRental(rentalInfo)));
        }

        report.finalize(null);
    }

    private static double getBonusForTargetRental(Rental rental)
    {
        switch (rental.getMovie().getPriceCode())
        {
            case Regular:
                return RegularPriceCalculus.calculate(rental.getDaysRented());

            case NewRelease:
                return NewReleasePriceCalculus.calculate(rental.getDaysRented());

            case Childrens:
                return ChildrenPriceCalculus.calculate(rental.getDaysRented());
        }

        throw new AssertionError();
    }

    private static int getFrequentPointsForRental(Rental rental)
    {
        if ((rental.getMovie().getPriceCode() == PriceCodes.NewRelease) && (rental.getDaysRented() > 1))
            return 2;
        return 1;
    }
}

