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
     * @param theProductItem
     * @return
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
     * @param theCustomerCartBusinessService
     * @return
     */
    //
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
     * @param theInputMap
     * @param theBill
     * @return
     */
    //Computing package pricing
    public float computePackagePricing(LinkedHashMap<ProductItem, Float> theInputMap, float theBill)
    {
        return theInputMap.entrySet().stream()
                .filter(item -> itemInPromotion(item.getKey()).isPresent())
                .map(item -> new PackagePricingBusinessService().computeTotalPrice(item.getKey(), item.getValue()))
                .reduce(theBill, (item1, item2) -> item1 + item2);
    }

    /**
     * Computing default pricing
     * @param theMapInput
     * @param theBill
     * @return
     */
    public float computeDefaultPricing(LinkedHashMap<ProductItem, Float> theMapInput, float theBill)
    {
        return theMapInput.entrySet().stream()
                .filter(item -> !itemInPromotion(item.getKey()).isPresent())
                .map(item -> new DefaultPricingBusinessService().computeTotalPrice(item.getKey(), item.getValue()))
                .reduce(theBill, (item1, item2) -> item1 + item2);
    }

    /**
     * Applying réduction
     * @param theProductItem
     * @param theNumberToBuy
     * @param theReduction
     */
    public void applyDiscount(ProductItem theProductItem, int theNumberToBuy, float theReduction)
    {
        theProductItem.setItemDiscountValue(new Tuple2<>(theNumberToBuy, theReduction));
    }

    /**
     * Removing reduction
     * @param theProductItem
     */
    public void removeDiscount(ProductItem theProductItem)
    {
        theProductItem.setItemDiscountValue(new Tuple2<>(null, null));
    }
}