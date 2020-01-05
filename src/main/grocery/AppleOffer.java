package grocery;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class AppleOffer implements Offer {

    private static final BigDecimal TEN_PERCENT_OF_ONE = BigDecimal.valueOf(0.1);

    @Override
    public BigDecimal calculatePrice(ShoppingBasket shoppingBasket, Item item, LocalDateTime calculationTime) {
        if (!shoppingBasket.getItems().containsKey(item)) {
            return BigDecimal.ZERO;
        }

        BigDecimal discountPrice = getOfferByDates(calculationTime);

        return item.getPrice().multiply(BigDecimal.valueOf(shoppingBasket.getItems().get(item))).multiply(discountPrice);
    }

    private BigDecimal getOfferByDates(LocalDateTime calculationTime) {
        if (Utils.isValidOffer(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()), calculationTime)) {
            return BigDecimal.ONE.subtract(TEN_PERCENT_OF_ONE);

        }
        return BigDecimal.ONE;
    }
}
