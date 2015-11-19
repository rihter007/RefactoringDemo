/**
 * Created by Rihter on 19.11.2015.
 */
import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import com.scrumtrek.simplestore.Rental;
import com.sun.istack.internal.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class CustomerTest
{
    private static void checkMoviePrice(@NotNull String movieName, double price, @NotNull String actualStatement)
    {
        final String searchedPattern = movieName + "\t" + price;
        Assert.assertTrue(actualStatement.contains(searchedPattern));
    }

    @Test
    public void checkCustomerRentalItem()
    {
        final double ChildrenMoviePriceIndex = 1.5;

        Movie childrenMovie = new Movie("ChildrenMovieName", PriceCodes.Childrens);

        // Create customers
        Customer dummyCustomer = new Customer("dummy_customer");

        // Create rentals
        Rental oneDayRent = new Rental(childrenMovie, 1);

        dummyCustomer.addRental(oneDayRent);

        checkMoviePrice(childrenMovie.getTitle(), ChildrenMoviePriceIndex, dummyCustomer.Statement());
    }
}
