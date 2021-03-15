package services;

import domain.Customer;
import domain.ProductItem;
import exceptions.SupermarketPricingException;

import static io.vavr.API.*;

public class CustomerCartBusinessService {

    private Customer customer = new Customer();


    /**
     * Adding an item to the cart
     *
     * @param theProductItem
     * @param thePurchasedQuantity
     * @throws SupermarketPricingException
     */
    public void addProductItemToCart(ProductItem theProductItem, float thePurchasedQuantity) throws SupermarketPricingException {
        if (getCustomer().getCart().containsKey(theProductItem)) {
            if (theProductItem.isByItemWeight() && (thePurchasedQuantity - (int) thePurchasedQuantity) != 0) {
                throw new SupermarketPricingException("Cannot buy half of an item.");
            } else {
                getCustomer().getCart().put(theProductItem, thePurchasedQuantity + (Float) getCustomer().getCart().get(theProductItem));
            }
        } else {
            getCustomer().getCart().put(theProductItem, thePurchasedQuantity);
        }
    }

    /**
     * Removal of an item from the cart
     *
     * @param theProductItem
     * @param theNumber
     * @throws SupermarketPricingException
     */
    public void removeProductItemFromCart(ProductItem theProductItem, float theNumber) {
        if (getCustomer().getCart().containsKey(theProductItem)) {
            Match((Float) getCustomer().getCart().get(theProductItem) - theNumber).of(
                    Case($(item -> item > 0), () -> getCustomer().getCart().replace(theProductItem, (Float) getCustomer().getCart().get(theProductItem) - theNumber)),
                    Case($(), () -> getCustomer().getCart().remove(theProductItem)));
        }
    }

    public Customer getCustomer() {
        return customer;
    }

}
