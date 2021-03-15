package services;

import domain.ProductItem;
import exceptions.SupermarketPricingException;

public class DefaultPricingBusinessService extends PricingBusinessService {


    /**
     * Calculating total price
     *
     * @param theProductItem
     * @param thePurchasedItemQuantity
     * @return
     * @throws SupermarketPricingException
     */
    @Override
    public float computeTotalPrice(ProductItem theProductItem, float thePurchasedItemQuantity) {
        return (theProductItem.getItemPrice() * thePurchasedItemQuantity);
    }
}

