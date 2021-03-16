
import domain.ProductItem;
import exceptions.CannotBuyHalfOfAnItemException;
import exceptions.SupermarketPricingException;
import io.vavr.Tuple;
import io.vavr.Tuple2;

import org.junit.Test;
import services.CustomerCartBusinessService;
import services.SuperMarketBusinessService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class SuperMarketBusinessServiceTest {


    private SuperMarketBusinessService aSuperMarketBusinessService;
    private CustomerCartBusinessService aCustomerCartBusinessService;


    @Test
    public void test_items_must_be_updated_when_the_discount_is_applied() {
        //Data initialization
        aSuperMarketBusinessService = new SuperMarketBusinessService();
        ProductItem aProductItem = new ProductItem("mask", 20, false, null);

        //Adding reduction
        aSuperMarketBusinessService.applyDiscount(aProductItem, 4, 0.7f);

        //Expected outcome
        assertThat(aProductItem.getItemDiscountValue()._1(), is(4));
        assertThat(aProductItem.getItemDiscountValue()._2(), is(0.7f));
    }

    @Test
    public void test_the_price_reduction_of_items_must_be_updated_when_a_new_reduction_is_applied() {

        //Data initialization
        aSuperMarketBusinessService = new SuperMarketBusinessService();
        ProductItem anItem = new ProductItem("mask", 20, false, null);
        aSuperMarketBusinessService.applyDiscount(anItem, 8, 0.8f);

        //Adding reduction
        aSuperMarketBusinessService.applyDiscount(anItem, 5, 0.6f);

        //Expected outcome
        assertThat(anItem.getItemDiscountValue()._1(), is(5));
        assertThat(anItem.getItemDiscountValue()._2(), is(0.6f));
    }


    @Test
    public void test_item_prices_should_be_updated_when_the_discount_is_removed() {

        //Data initialization
        aSuperMarketBusinessService = new SuperMarketBusinessService();
        Tuple2<Integer, Float> inputDiscount = Tuple.of(2, 0.6f);
        ProductItem aProductItem = new ProductItem("mask", 20, false, inputDiscount);

        //Removing reduction
        aSuperMarketBusinessService.removeDiscount(aProductItem);

        //Expected outcome
        assertThat(aProductItem.getItemDiscountValue()._1(), is(nullValue()));
    }

    @Test
    public void test_the_item_prices_must_be_correct_when_billing() throws CannotBuyHalfOfAnItemException,SupermarketPricingException {

        //Data initialization
        aSuperMarketBusinessService = new SuperMarketBusinessService();
        aCustomerCartBusinessService = new CustomerCartBusinessService();
        ProductItem aMask = new ProductItem("mask", 20, false, null);
        ProductItem aChocolate = new ProductItem("chocolate", 7, false, null);
        ProductItem aSandwich = new ProductItem("sandwich", 12, false, null);
        aCustomerCartBusinessService.addProductItemToCart(aMask, 5);
        aCustomerCartBusinessService.addProductItemToCart(aChocolate, 4.3f);
        aCustomerCartBusinessService.addProductItemToCart(aSandwich, 4);
        aSuperMarketBusinessService.applyDiscount(aSandwich, 3, 0.7f);

        //Billing
        aSuperMarketBusinessService.computeBilling(aCustomerCartBusinessService);

        //Expected outcome
        assertThat(aSuperMarketBusinessService.computeBilling(aCustomerCartBusinessService), is(167.3f));
    }

}
