package grocery;

import java.util.EnumMap;
import java.util.Map;

public class ShoppingBasket {

    private final EnumMap<Item, Integer> items = new EnumMap<>(Item.class);

    public void putItems(Item item, Integer quantity) {

        if (items.containsKey(item)) {
            items.put(item, quantity + getItems().get(item));
        } else {
            items.put(item, quantity);
        }

    }

    public Map<Item, Integer> getItems() {
        return items;
    }


}
