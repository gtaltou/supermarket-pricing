package services;

import domain.ProductItem;

public class PackagePricingBusinessService extends PricingBusinessService {

    //Calculating total price
    public float computeTotalPrice(ProductItem theProductItem, float thePurchasedItemQuantity) {

        int numberOfDiscount = theProductItem.getItemDiscountValue()._1();

        int appliedDiscountNumberOfTimes = computeAppliedDiscountNumberOfTimes(numberOfDiscount, thePurchasedItemQuantity);
        float numberOfFullPricedProducts = computeNumberOfFullPricedProducts(numberOfDiscount, thePurchasedItemQuantity);

        float itemReductionValue = theProductItem.getItemDiscountValue()._2();

        float discountedPrice = computeDiscountedPrices(theProductItem, appliedDiscountNumberOfTimes, numberOfDiscount, itemReductionValue);
        float unDiscountedPrice = computeUnDiscountedPrices(theProductItem, numberOfFullPricedProducts);

        return discountedPrice + unDiscountedPrice;
    }
   //Compute applied discount number of times
    private int computeAppliedDiscountNumberOfTimes(int numberOfDiscount, float thePurchasedItemQuantity){
        return (int) (thePurchasedItemQuantity / numberOfDiscount);
    }
    //Compute number of full priced products
    private float computeNumberOfFullPricedProducts(int numberOfDiscount, float thePurchasedItemQuantity){
        return thePurchasedItemQuantity % numberOfDiscount;
    }
    //Compute reduced prices
    private float computeDiscountedPrices(ProductItem theProductItem, int appliedDiscountNumberOfTimes, int numberOfDiscount, float itemReductionValue){
        return appliedDiscountNumberOfTimes * theProductItem.getItemPrice() * numberOfDiscount * itemReductionValue;
    }
    //Compute unreduced prices
    private float computeUnDiscountedPrices(ProductItem theProductItem, float numberOfFullPricedProducts){
        return theProductItem.getItemPrice() * numberOfFullPricedProducts;
    }
}


