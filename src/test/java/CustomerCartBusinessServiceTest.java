import domain.ProductItem;
import exceptions.CannotBuyHalfOfAnItemException;


import org.junit.Test;
import services.CustomerCartBusinessService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomerCartBusinessServiceTest {

    private ProductItem aMask, aChocolate;
    private CustomerCartBusinessService aCustomerCart;


    @Test
    public void test_add_item_to_cart() throws CannotBuyHalfOfAnItemException {

        //Data initialization
        aCustomerCart = new CustomerCartBusinessService();
        aMask = new ProductItem("mask",30,false,null);
        aChocolate = new ProductItem("chocolate",3,false,null);

        //Adding data
        aCustomerCart.addProductItemToCart(aMask, 5f);
        aCustomerCart.addProductItemToCart(aChocolate, 5.2f);

        //Expected outcome
        assertThat(aCustomerCart.getCustomer().getCart().toString(), is("{" + aMask.toString() + "=5.0, " + aChocolate.toString() + "=5.2}"));
    }

    @Test
    public void test_update_value_in_cart() throws CannotBuyHalfOfAnItemException {

        //Data initialization
        aCustomerCart = new CustomerCartBusinessService();
        aMask = new ProductItem("mask", 8, false, null);
        aChocolate = new ProductItem("chocolate", 6, false, null);

        //updating data
        aCustomerCart.addProductItemToCart(aMask, 5f);
        aCustomerCart.addProductItemToCart(aChocolate, 5.2f);
        aCustomerCart.addProductItemToCart(aMask, 5f);

        //Expected outcome
        assertThat(aCustomerCart.getCustomer().getCart().toString(), is("{" + aMask.toString() + "=10.0, " + aChocolate.toString() + "=5.2}"));
    }

    @Test
    public void test_remove_item_from_cart() throws CannotBuyHalfOfAnItemException{

        //Data initialization
        aCustomerCart = new CustomerCartBusinessService();
        aMask = new ProductItem("aMask", 30,false,null);
        aChocolate = new ProductItem("chocolate", 9, true, null);
        aCustomerCart.addProductItemToCart(aChocolate, 3.7f);
        aCustomerCart.addProductItemToCart(aMask, 4.0f);

        //Deleting data
        aCustomerCart.removeProductItemFromCart(aMask, 4.0f);
        aCustomerCart.removeProductItemFromCart(aChocolate, 3.2f);

        //Expected outcome
        assertThat(aCustomerCart.getCustomer().getCart().toString(), is("{" + aChocolate.toString() + "=0.5}"));
    }
}
