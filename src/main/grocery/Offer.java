package grocery;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface Offer {
    default BigDecimal calculatePrice(ShoppingBasket shoppingBasket, Item item, LocalDateTime purchaseDate) {

        return item.getPrice().multiply(BigDecimal.valueOf(shoppingBasket.getItems().get(item)));
    }
}
