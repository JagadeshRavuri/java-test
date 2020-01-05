package grocery;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


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

}
