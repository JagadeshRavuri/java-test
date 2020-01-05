package grocery;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public class BreadOffer implements Offer {

    private static final BigDecimal TWO = BigDecimal.valueOf(2);

    @Override
    public BigDecimal calculatePrice(ShoppingBasket basket, Item item, LocalDateTime calculationTime) {
        if (!basket.getItems().containsKey(item)) {
            return BigDecimal.ZERO;
        }
        if (Utils.isValidOffer(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(7), calculationTime)) {
            Integer offerItems = getOfferItems(basket.getItems(), item);
            BigDecimal discountPrice = item.getPrice().divide(TWO, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(offerItems));
            Integer unOfferItems = basket.getItems().get(item) - offerItems;
            if (unOfferItems > 0) {
                return discountPrice.add(item.getPrice().multiply(BigDecimal.valueOf(unOfferItems)));
            } else {
                return discountPrice;
            }
        }
        return item.getPrice().multiply(BigDecimal.valueOf(basket.getItems().get(item)));
    }

    private Integer getOfferItems(Map<Item, Integer> items, Item item) {
        return Math.min(items.get(item), Optional.ofNullable(items.get(Item.SOUP)).orElse(0) / 2);
    }


}
