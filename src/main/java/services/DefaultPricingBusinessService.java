package services;

import domain.ProductItem;

public class DefaultPricingBusinessService extends PricingBusinessService {

    //Calculating total price
    public float computeTotalPrice(ProductItem productItem, float thePurchasedItemQuantity) {
        return (productItem.getItemPrice() * thePurchasedItemQuantity);
    }
}

