package grocery;

import java.time.LocalDateTime;

public final class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValidOffer(LocalDateTime offerStartDate, LocalDateTime offerEndDate, LocalDateTime purchaseDate) {
        return (purchaseDate.isAfter(offerStartDate) || purchaseDate.isEqual(offerStartDate)) && (purchaseDate.isBefore(offerEndDate) || purchaseDate.isEqual(offerEndDate));
    }

}


