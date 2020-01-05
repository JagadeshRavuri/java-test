package grocery;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.*;


public class UtilsTest {

    @Test
    public void checkPromotionValidNow() {
        assertTrue(Utils.isValidOffer(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), LocalDateTime.now()));
    }

    @Test
    public void doNotApplyDiscountOnApplesWhenInvalidDate() {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.putItems(Item.APPLE, 6);
        shoppingBasket.putItems(Item.MILK, 1);

        BigDecimal bill = Utils.computeBill(shoppingBasket, LocalDateTime.now());

        assertEquals(BigDecimal.valueOf(1.90), bill);
    }

    @Test
    public void applyDiscountOnApplesWhenValidDate() {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.putItems(Item.APPLE, 6);
        shoppingBasket.putItems(Item.MILK, 1);

        BigDecimal bill = Utils.computeBill(shoppingBasket, LocalDateTime.now().plusDays(5));

        assertEquals(BigDecimal.valueOf(1.84), bill);
    }

    @Test
    public void applyDiscountOnOneLoafBreadWhenValidNumberOfSoups() {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.putItems(Item.SOUP, 3);
        shoppingBasket.putItems(Item.BREAD, 2);

        BigDecimal bill = Utils.computeBill(shoppingBasket, LocalDateTime.now());

        assertEquals(BigDecimal.valueOf(3.15), bill);
    }

    @Test
    public void applyDiscountOnOneLoafBreadAndApplesWhenValid() {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.putItems(Item.APPLE, 3);
        shoppingBasket.putItems(Item.SOUP, 2);
        shoppingBasket.putItems(Item.BREAD, 1);

        BigDecimal bill = Utils.computeBill(shoppingBasket, LocalDateTime.now().plusDays(5));

        assertEquals(BigDecimal.valueOf(1.97), bill);
    }

    @Test
    public void checkPromotionValidForFutureDate() {
        assertTrue(Utils.isValidOffer(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(2)));
    }

    @Test
    public void checkPromotionValidForPastDate() {
        assertTrue(Utils.isValidOffer(LocalDateTime.now().minusDays(5), LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(4)));
    }

    @Test
    public void checkPromotionValidForTheSameDayItEnds() {
        assertTrue(Utils.isValidOffer(LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).minusSeconds(5)));
    }

    @Test
    public void checkPromotionValidForTheSameDayItStarts() {
        assertTrue(Utils.isValidOffer(LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(3), LocalDateTime.now().minusDays(5).plusSeconds(5)));
    }

    @Test
    public void checkPromotionInvalidWhenDateBeforeStartTime() {
        assertFalse(Utils.isValidOffer(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), LocalDateTime.now().minusDays(4)));
    }

    @Test
    public void checkPromotionInvalidWhenDateAfterEndTime() {
        assertFalse(Utils.isValidOffer(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(4)));
    }
}
