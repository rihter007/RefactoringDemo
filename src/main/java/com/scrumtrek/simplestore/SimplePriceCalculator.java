package com.scrumtrek.simplestore;

/**
 * Created by Rihter on 20.11.2015.
 */
public class SimplePriceCalculator implements IPriceCalculator
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

    public double calc(Rental rental)
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
}
