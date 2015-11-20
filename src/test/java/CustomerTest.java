import com.scrumtrek.simplestore.Customer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rihter on 20.11.2015.
 */
public class CustomerTest
{
    @Test
    public void checkCustomerName()
    {
        final String dummyTestName = "some_name";

        Customer customer = new Customer(dummyTestName);
        Assert.assertEquals(dummyTestName, customer.getName());
    }
}
