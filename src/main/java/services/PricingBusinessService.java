package services;

import domain.ProductItem;
import exceptions.SupermarketPricingException;

public abstract class PricingBusinessService {

    /**
     * @param theProductItem
     * @param thePurchasedProductQuantity
     * @return
     * @throws SupermarketPricingException
     */
    public abstract float computeTotalPrice(ProductItem theProductItem, float thePurchasedProductQuantity) throws SupermarketPricingException;

}
