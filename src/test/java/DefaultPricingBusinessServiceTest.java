import domain.ProductItem;
import org.junit.Test;
import services.DefaultPricingBusinessService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DefaultPricingBusinessServiceTest {

    private DefaultPricingBusinessService aDefaultPricing;

    @Test
    public void test_calculate_price() {
        //Data initialization
        ProductItem aProductItem = new ProductItem("mask", 7, true, null);
        aDefaultPricing = new DefaultPricingBusinessService();
        int anItemQuantity = 5;

        //calculating the total price
        aDefaultPricing.computeTotalPrice(aProductItem, anItemQuantity);

        //Expected outcome
        assertThat(aDefaultPricing.computeTotalPrice(aProductItem, anItemQuantity), is(35f));
    }
}
