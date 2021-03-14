package domain;

import io.vavr.Tuple;
import io.vavr.Tuple2;

import java.util.Objects;

public class ProductItem {

    private String itemName;
    private int itemPrice;
    private boolean byItemWeight;
    private Tuple2<Integer, Float> itemDiscountValue = Tuple.of(null, null);

    public ProductItem(String itemName, int itemPrice, boolean byItemWeight, Tuple2<Integer, Float> itemDiscountValue) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.byItemWeight = byItemWeight;
        this.itemDiscountValue = itemDiscountValue;
    }

    public String getItemName() { return itemName; }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public boolean isByItemWeight() {
        return byItemWeight;
    }

    public void setByItemWeight(boolean byItemWeight) {
        this.byItemWeight = byItemWeight;
    }

    public Tuple2<Integer, Float> getItemDiscountValue() {
        return itemDiscountValue;
    }

    public void setItemDiscountValue(Tuple2<Integer, Float> itemDiscountValue) {
        this.itemDiscountValue = itemDiscountValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItem productItem = (ProductItem) o;
        return itemPrice == productItem.itemPrice && byItemWeight == productItem.byItemWeight && itemName.equals(productItem.itemName) && itemDiscountValue.equals(productItem.itemDiscountValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, itemPrice, byItemWeight, itemDiscountValue);
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", byItemWeight=" + byItemWeight +
                ", itemReductionValue=" + itemDiscountValue +
                '}';
    }
}
