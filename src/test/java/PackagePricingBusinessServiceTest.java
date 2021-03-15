import domain.ProductItem;
import exceptions.SupermarketPricingException;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import services.PackagePricingBusinessService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class PackagePricingBusinessServiceTest {

    private PackagePricingBusinessService aPackagePricing;

    @Test
    @Parameters({"3f, 135f",
            "4f, 185f",
            "5f, 235f",
            "7f, 320f"})
    public void test_reduction_when_condition_reached(float theInputNumber, float theExpectedPrice) throws SupermarketPricingException {

        ////Data initialization
        Tuple2<Integer, Float> inputReduction = Tuple.of(3, 0.9f);
        ProductItem aProductItem = new ProductItem("productItem", 50, true, inputReduction);
        aPackagePricing = new PackagePricingBusinessService();

        //Calculating total price
        aPackagePricing.computeTotalPrice(aProductItem, theInputNumber);

        //Expected outcome
        assertThat(aPackagePricing.computeTotalPrice(aProductItem, theInputNumber), is(theExpectedPrice));
    }
}
