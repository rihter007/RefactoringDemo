import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import com.scrumtrek.simplestore.Rental;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Rihter on 20.11.2015.
 * Checks that single rent for specified number of days and PriceCode
 */

@RunWith(Parameterized.class)
public class CustomerRentalParamTest
{
    private static class RentalPriceIndex
    {
        public RentalPriceIndex(PriceCodes priceCode, int daysRent, double expectedPriceValue)
        {
            this.priceCode = priceCode;
            this.daysRent = daysRent;
            this.expectedPriceValue = expectedPriceValue;
        }

        public final PriceCodes priceCode;
        public final int daysRent;

        public final double expectedPriceValue;
    }

    public final RentalPriceIndex testParameter;

    public CustomerRentalParamTest(RentalPriceIndex param)
    {
        this.testParameter = param;
    }

    @Parameterized.Parameters
    public static Collection<RentalPriceIndex> addedNumbers()
    {
        return Arrays.asList(new RentalPriceIndex(PriceCodes.Childrens, 1, 1.5));
    }

    private static void checkMoviePrice(String movieName, double price, String actualStatement)
    {
        final String searchedPattern = movieName + "\t" + price;
        Assert.assertTrue(actualStatement.contains(searchedPattern));
    }

    @Test
    public void checkCustomerSingleRentalItem()
    {
        Movie childrenMovie = new Movie("ChildrenMovieName", this.testParameter.priceCode);

        // Create customers
        Customer dummyCustomer = new Customer("dummy_customer");

        // Create rentals
        Rental inputRent = new Rental(childrenMovie, this.testParameter.daysRent);

        dummyCustomer.addRental(inputRent);

        checkMoviePrice(childrenMovie.getTitle(), this.testParameter.expectedPriceValue, dummyCustomer.Statement());
    }
}