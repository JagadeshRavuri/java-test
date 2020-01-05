package grocery;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static grocery.Item.APPLE;
import static org.hamcrest.MatcherAssert.assertThat;

public class AppleOfferTest {

    @Test
    public void promotionNotActiveWhenDateOutRange() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.putItems(APPLE, 10);

        BigDecimal price = new AppleOffer().calculatePrice(basket, APPLE, LocalDateTime.now());

        assertThat(BigDecimal.valueOf(1), Matchers.comparesEqualTo(price));
    }

    @Test
    public void applyPromotionWhenDateIsInRange() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.putItems(Item.APPLE, 10);

        BigDecimal price = new AppleOffer().calculatePrice(basket, Item.APPLE, LocalDateTime.now().plusDays(5));

        assertThat(BigDecimal.valueOf(0.9), Matchers.comparesEqualTo(price));
    }
}