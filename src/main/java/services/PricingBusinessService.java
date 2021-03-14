package services;

import domain.ProductItem;

public abstract class PricingBusinessService {

    public abstract float computeTotalPrice(ProductItem theProductItem, float thePurchasedProductQuantity);

}
