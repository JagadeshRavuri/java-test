package grocery;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

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

    @Test
    public void calculateZeroIfItemIsMissingInItemList() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.putItems(Item.BREAD, 1);

        BigDecimal price = new AppleOffer().calculatePrice(basket, Item.APPLE, LocalDateTime.now().plusDays(5));

        assertThat(BigDecimal.ZERO, Matchers.comparesEqualTo(price));
    }

    @Test
    public void calculateCorrectDiscountWithManyItemsInList() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.putItems(Item.APPLE, 20);
        basket.putItems(Item.BREAD, 10);
        basket.putItems(Item.MILK, 10);
        basket.putItems(Item.SOUP, 10);

        BigDecimal price = new AppleOffer().calculatePrice(basket, Item.APPLE, LocalDateTime.now().plusDays(5));

        assertThat(BigDecimal.valueOf(1.8), Matchers.comparesEqualTo(price));
    }


    @Test
    public void promotionEndsAfterFollowingMonthEndDate() {
        ShoppingBasket basket = new ShoppingBasket();
        basket.putItems(Item.APPLE, 10);

        BigDecimal priceAfter = new AppleOffer().calculatePrice(basket, Item.APPLE, LocalDateTime.now().plusMonths(2).withDayOfMonth(1).plusSeconds(5));
        BigDecimal priceEndDate = new AppleOffer().calculatePrice(basket, Item.APPLE, LocalDateTime.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).minusSeconds(5));

        assertThat(BigDecimal.ONE, Matchers.comparesEqualTo(priceAfter));
        assertThat(BigDecimal.valueOf(0.9), Matchers.comparesEqualTo(priceEndDate));
    }
}