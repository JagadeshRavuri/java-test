package grocery;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AppleOffer implements Offer {

    @Override
    public BigDecimal calculatePrice(ShoppingBasket shoppingBasket, Item item, LocalDateTime calculationTime) {
        if (!shoppingBasket.getItems().containsKey(item)) {
            return BigDecimal.ZERO;
        }
        return item.getPrice().multiply(BigDecimal.valueOf(shoppingBasket.getItems().get(item)));
    }

}
