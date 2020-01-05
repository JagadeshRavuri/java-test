package grocery;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static grocery.Item.MILK;
import static org.hamcrest.MatcherAssert.assertThat;

public class OfferTest {

    private final Offer offer = new Offer() {
    };

    @Test
    public void shouldGiveActualPrice() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.putItems(MILK, 100);

        BigDecimal price = offer.calculatePrice(basket, MILK, LocalDateTime.now());

        assertThat(BigDecimal.valueOf(130), Matchers.comparesEqualTo(price));
    }

    @Test
    public void shouldGiveActualPriceForAnyDate() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.putItems(MILK, 10);


        BigDecimal futureDatePrice = offer.calculatePrice(basket, MILK, LocalDateTime.now().plusYears(10));
        BigDecimal pastDatePrice = offer.calculatePrice(basket, MILK, LocalDateTime.now().minusYears(10));

        assertThat(futureDatePrice, Matchers.comparesEqualTo(pastDatePrice));
    }

}