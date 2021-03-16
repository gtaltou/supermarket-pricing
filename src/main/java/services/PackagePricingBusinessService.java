package services;

import domain.ProductItem;

public class PackagePricingBusinessService extends PricingBusinessService {

    /**
     * Calculating total price

     * @param theProductItem : The product item
     * @param thePurchasedItemQuantity ; The purchased item quantity
     * @return : The return statement
     *
     */
    @Override
    public float computeTotalPrice(ProductItem theProductItem, float thePurchasedItemQuantity) {

        int numberOfDiscount = theProductItem.getItemDiscountValue()._1();

        int appliedDiscountNumberOfTimes = computeAppliedDiscountNumberOfTimes(numberOfDiscount, thePurchasedItemQuantity);
        float numberOfFullPricedProducts = computeNumberOfFullPricedProducts(numberOfDiscount, thePurchasedItemQuantity);

        float itemReductionValue = theProductItem.getItemDiscountValue()._2();

        float discountedPrice = computeDiscountedPrices(theProductItem, appliedDiscountNumberOfTimes, numberOfDiscount, itemReductionValue);
        float unDiscountedPrice = computeUnDiscountedPrices(theProductItem, numberOfFullPricedProducts);

        return discountedPrice + unDiscountedPrice;
    }

    /**
     * Compute applied discount number of times
     * @param numberOfDiscount : The number of discount
     * @param thePurchasedItemQuantity : The purchased item quantity
     * @return : The return statement
     * @throws ArithmeticException : The arithmetic exception
     */
    private int computeAppliedDiscountNumberOfTimes(int numberOfDiscount, float thePurchasedItemQuantity) throws ArithmeticException {
       return (int) (thePurchasedItemQuantity / numberOfDiscount);
    }

    /**
     * Compute number of full priced products
     * @param numberOfDiscount : The number of discount
     * @param thePurchasedItemQuantity : The purchased item quantity
     * @return : The return statement
     * @throws ArithmeticException : The arithmetic exception
     */
    private float computeNumberOfFullPricedProducts(int numberOfDiscount, float thePurchasedItemQuantity) throws ArithmeticException {
       return thePurchasedItemQuantity % numberOfDiscount;
    }

    /**
     * Compute reduced prices
     * @param theProductItem : The product item
     * @param appliedDiscountNumberOfTimes : The applied discount number of times
     * @param numberOfDiscount : The number of discount
     * @param itemReductionValue : The item discounted value
     * @return : The return statement
     * @throws ArithmeticException : The arithmetic exception
     */
    private float computeDiscountedPrices(ProductItem theProductItem, int appliedDiscountNumberOfTimes, int numberOfDiscount, float itemReductionValue) throws ArithmeticException {
      return  appliedDiscountNumberOfTimes * theProductItem.getItemPrice() * numberOfDiscount * itemReductionValue;
    }

    /**
     * Compute unreduced prices
     * @param theProductItem : The product item
     * @param theNumberOfFullPricedProducts : The number of full price products
     * @return : the return statement
     * @throws ArithmeticException : The arithmetic exception
     */

    private float computeUnDiscountedPrices(ProductItem theProductItem, float theNumberOfFullPricedProducts) throws ArithmeticException {
        return theProductItem.getItemPrice() * theNumberOfFullPricedProducts;

    }
}


