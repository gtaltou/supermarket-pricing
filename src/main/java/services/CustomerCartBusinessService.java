package services;

import domain.Customer;
import domain.ProductItem;
import exceptions.CannotBuyHalfOfAnItemException;


import static io.vavr.API.*;

public class CustomerCartBusinessService {

    /**
     * The customer
     */
    private final Customer customer = new Customer();


    /**
     * Adding an item to the cart
     *
     * @param theProductItem: The product item
     * @param thePurchasedQuantity: The purchased item quantity
     * @throws CannotBuyHalfOfAnItemException, SupermarketPricingException
     */
    public void addProductItemToCart(ProductItem theProductItem, float thePurchasedQuantity) throws CannotBuyHalfOfAnItemException {
        if (getCustomer().getCart().containsKey(theProductItem)) {
            if (theProductItem.isByItemWeight() && (thePurchasedQuantity - (int) thePurchasedQuantity) != 0) {
                throw new CannotBuyHalfOfAnItemException();
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
     * @param theProductItem : The product item
     * @param theNumber : The number
     *
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
