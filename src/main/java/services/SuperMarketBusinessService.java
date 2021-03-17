package services;

import domain.ProductItem;
import io.vavr.Tuple2;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Optional;

public class SuperMarketBusinessService {
    //

    /**
     * Dealing item in promotion
     * @param theProductItem : The product item
     * @return : The return statement
     */
    private Optional<Integer> itemInPromotion(ProductItem theProductItem)
    {
        return Optional.ofNullable(theProductItem.getItemDiscountValue())
                .filter(discountList -> Objects.nonNull(discountList._1))
                .filter(discountList -> Objects.nonNull(discountList._2))
                .map(discountList -> discountList._1);
    }

    /**
     * Calculating billing
     * @param theCustomerCartBusinessService : The customer cart business service
     * @return : The return statement
     */
    public float computeBilling(CustomerCartBusinessService theCustomerCartBusinessService)
    {
        LinkedHashMap<ProductItem, Float> mapInput = theCustomerCartBusinessService.getCustomer().getCart();
        float aBill = 0f;

        aBill = computeDefaultPricing(mapInput, aBill);
        aBill = computePackagePricing(mapInput, aBill);

        return aBill;
    }

    /**
     * Computing package pricing
     * @param theInputMap : The input map
     * @param theBill : The bill
     * @return : The return statement
     */

    public float computePackagePricing(LinkedHashMap<ProductItem, Float> theInputMap, float theBill)
    {
        return theInputMap.entrySet().stream()
                .filter(item -> itemInPromotion(item.getKey()).isPresent())
                .map(item -> new PackagePricingBusinessService().computeTotalPrice(item.getKey(), item.getValue()))
                .reduce(theBill, Float::sum);
    }

    /**
     * Computing default pricing
     * @param theMapInput : The input map
     * @param theBill : The bill
     * @return : The return statement
     */
    public float computeDefaultPricing(LinkedHashMap<ProductItem, Float> theMapInput, float theBill)
    {
        return theMapInput.entrySet().stream()
                .filter(item -> !itemInPromotion(item.getKey()).isPresent())
                .map(item -> new DefaultPricingBusinessService().computeTotalPrice(item.getKey(), item.getValue()))
                .reduce(theBill, Float::sum);
    }

    /**
     * Applying réduction
     * @param theProductItem : The proudut Item
     * @param theNumberToBuy : The item number to buy
     * @param theReduction  : The reduction
     */
    public void applyDiscount(ProductItem theProductItem, int theNumberToBuy, float theReduction)
    {
        theProductItem.setItemDiscountValue(new Tuple2<>(theNumberToBuy, theReduction));
    }

    /**
     * Removing reduction
     * @param theProductItem: The product Item
     */
    public void removeDiscount(ProductItem theProductItem)
    {
        theProductItem.setItemDiscountValue(new Tuple2<>(null, null));
    }
}