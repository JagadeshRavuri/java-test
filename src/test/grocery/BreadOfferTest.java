package grocery;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static grocery.Item.BREAD;
import static grocery.Item.SOUP;
import static org.hamcrest.MatcherAssert.assertThat;

public class BreadOfferTest {

    @Test
    public void noOfferWhenBreadOnly() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.putItems(BREAD, 10);

        BigDecimal price = new BreadOffer().calculatePrice(basket, BREAD, LocalDateTime.now());

        assertThat(BigDecimal.valueOf(8), Matchers.comparesEqualTo(price));
    }

    @Test
    public void noOfferWhenWhenDateOutOfRange() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.putItems(BREAD, 10);
        BigDecimal price = new BreadOffer().calculatePrice(basket, BREAD, LocalDateTime.now().plusMonths(5));

        assertThat(BigDecimal.valueOf(8), Matchers.comparesEqualTo(price));
    }

    @Test
    public void noChargeForMissingItem() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.putItems(SOUP, 10);

        BigDecimal price = new BreadOffer().calculatePrice(basket, BREAD, LocalDateTime.now());

        assertThat(BigDecimal.ZERO, Matchers.comparesEqualTo(price));
    }

    @Test
    public void appropriateOfferWhenSoupIsNotEnoughForDiscount() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.putItems(BREAD, 10);
        basket.putItems(SOUP, 1);
        BigDecimal price = new BreadOffer().calculatePrice(basket, BREAD, LocalDateTime.now());

        assertThat(BigDecimal.valueOf(8), Matchers.comparesEqualTo(price));
    }

    @Test
    public void appropriateOfferWhenSoupIsMoreThanBread() {

        ShoppingBasket basket = new ShoppingBasket();
        basket.putItems(BREAD, 1);
        basket.putItems(SOUP, 12);

        BigDecimal price = new BreadOffer().calculatePrice(basket, BREAD, LocalDateTime.now());

        assertThat(BigDecimal.valueOf(0.4), Matchers.comparesEqualTo(price));
    }

    @Test
    public void appropriateOfferWhenSoupIsLessThanBread() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.putItems(BREAD, 10);
        basket.putItems(SOUP, 2);

        BigDecimal price = new BreadOffer().calculatePrice(basket, BREAD, LocalDateTime.now());

        assertThat(BigDecimal.valueOf(7.6), Matchers.comparesEqualTo(price));
    }
}