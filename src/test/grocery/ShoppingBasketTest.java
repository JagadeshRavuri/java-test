package grocery;

import org.junit.Test;

import static grocery.Item.APPLE;
import static org.junit.Assert.assertEquals;


public class ShoppingBasketTest {

    @Test
    public void putItems() {
        ShoppingBasket basket = new ShoppingBasket();

        basket.putItems(APPLE, 1);

        assertEquals(Integer.valueOf(1), basket.getItems().get(APPLE));
    }

    @Test
    public void putItemsMultipleTimes() {
        ShoppingBasket basket = new ShoppingBasket();

        basket.putItems(APPLE, 1);
        basket.putItems(APPLE, 2);

        assertEquals(Integer.valueOf(3), basket.getItems().get(APPLE));
    }

}