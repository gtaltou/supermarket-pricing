package services;

import domain.ProductItem;
import exceptions.SupermarketPricingException;

public abstract class PricingBusinessService {

    /**
     * @param theProductItem : The product item
     * @param thePurchasedProductQuantity : The purchesed pproduct quantity
     * @return : The return statement
     * @throws SupermarketPricingException : The supermarket pricing exception
     */
    public abstract float computeTotalPrice(ProductItem theProductItem, float thePurchasedProductQuantity) throws SupermarketPricingException;

}
