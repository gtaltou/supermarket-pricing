package services;

import domain.ProductItem;
import io.vavr.Tuple2;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Optional;

public class SuperMarketBusinessService {
    //Dealing item in promotion
    private Optional<Integer> itemInPromotion(ProductItem productItem)
    {
        return Optional.ofNullable(productItem.getItemDiscountValue())
                .filter(discountList -> Objects.nonNull(discountList._1))
                .filter(discountList -> Objects.nonNull(discountList._2))
                .map(discountList -> discountList._1);
    }
    //Calculating billing
    public float computeBilling(CustomerCartBusinessService theCustomerCartBusinessService)
    {
        LinkedHashMap<ProductItem, Float> input = theCustomerCartBusinessService.getCustomer().getCart();
        float aBill = 0f;

        aBill = computeDefaultPricing(input, aBill);
        aBill = computePackagePricing(input, aBill);

        return aBill;
    }
    //Computing package pricing
    public float computePackagePricing(LinkedHashMap<ProductItem, Float> theInputMap, float theBill)
    {
        return theInputMap.entrySet().stream()
                .filter(item -> itemInPromotion(item.getKey()).isPresent())
                .map(item -> new PackagePricingBusinessService().computeTotalPrice(item.getKey(), item.getValue()))
                .reduce(theBill, (item1, item2) -> item1 + item2);
    }
    //Computing default pricing
    public float computeDefaultPricing(LinkedHashMap<ProductItem, Float> inputMap, float theBill)
    {
        return inputMap.entrySet().stream()
                .filter(item -> !itemInPromotion(item.getKey()).isPresent())
                .map(item -> new DefaultPricingBusinessService().computeTotalPrice(item.getKey(), item.getValue()))
                .reduce(theBill, (item1, item2) -> item1 + item2);
    }
    //Applying réduction
    public void applyDiscount(ProductItem productItem, int numberToBuy, float reduction)
    {
        productItem.setItemDiscountValue(new Tuple2<>(numberToBuy, reduction));
    }
    //Removing reduction
    public void removeDiscount(ProductItem productItem)
    {
        productItem.setItemDiscountValue(new Tuple2<>(null, null));
    }
}
