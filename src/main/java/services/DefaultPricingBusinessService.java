package services;

import domain.ProductItem;

public class DefaultPricingBusinessService extends PricingBusinessService {


    /**
     * Calculating total price
     *
     * @param theProductItem : The product item
     * @param thePurchasedItemQuantity : The purchased item quantity
     * @return : The return statement
     *
     */
    @Override
    public float computeTotalPrice(ProductItem theProductItem, float thePurchasedItemQuantity) {
        return (theProductItem.getItemPrice() * thePurchasedItemQuantity);
    }
}

