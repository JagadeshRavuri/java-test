package grocery;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static BigDecimal computeBill(ShoppingBasket shoppingBasket, LocalDateTime purchaseDate) {
        return shoppingBasket.getItems().keySet().stream().map(k -> k.getOffer().calculatePrice(shoppingBasket, k, purchaseDate)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static boolean isValidOffer(LocalDateTime offerStartDate, LocalDateTime offerEndDate, LocalDateTime purchaseDate) {
        return (purchaseDate.isAfter(offerStartDate) || purchaseDate.isEqual(offerStartDate)) && (purchaseDate.isBefore(offerEndDate) || purchaseDate.isEqual(offerEndDate));
    }


}


