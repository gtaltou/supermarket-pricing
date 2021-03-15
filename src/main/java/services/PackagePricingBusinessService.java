package services;

import domain.ProductItem;
import exceptions.SupermarketPricingException;

public class PackagePricingBusinessService extends PricingBusinessService {

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
     * @param numberOfDiscount
     * @param thePurchasedItemQuantity
     * @return
     * @throws ArithmeticException
     */
    private int computeAppliedDiscountNumberOfTimes(int numberOfDiscount, float thePurchasedItemQuantity) throws ArithmeticException {
       return (int) (thePurchasedItemQuantity / numberOfDiscount);
    }

    /**
     * Compute number of full priced products
     * @param numberOfDiscount
     * @param thePurchasedItemQuantity
     * @return
     * @throws ArithmeticException
     */
    private float computeNumberOfFullPricedProducts(int numberOfDiscount, float thePurchasedItemQuantity) throws ArithmeticException {
       return thePurchasedItemQuantity % numberOfDiscount;
    }

    /**
     * Compute reduced prices
     * @param theProductItem
     * @param appliedDiscountNumberOfTimes
     * @param numberOfDiscount
     * @param itemReductionValue
     * @return
     * @throws ArithmeticException
     */
    private float computeDiscountedPrices(ProductItem theProductItem, int appliedDiscountNumberOfTimes, int numberOfDiscount, float itemReductionValue) throws ArithmeticException {
      return  appliedDiscountNumberOfTimes * theProductItem.getItemPrice() * numberOfDiscount * itemReductionValue;
    }

    /**
     * Compute unreduced prices
     * @param theProductItem
     * @param theNumberOfFullPricedProducts
     * @return
     * @throws ArithmeticException
     */

    private float computeUnDiscountedPrices(ProductItem theProductItem, float theNumberOfFullPricedProducts) throws ArithmeticException {
        return theProductItem.getItemPrice() * theNumberOfFullPricedProducts;

    }
}


