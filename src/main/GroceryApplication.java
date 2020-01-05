import grocery.Item;
import grocery.ShoppingBasket;
import grocery.Utils;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Logger;

import static grocery.Item.*;

public class GroceryApplication {

    private static final Logger LOGGER = Logger.getLogger(GroceryApplication.class.getName());

    public static void main(String[] args) {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        Scanner scan = new Scanner(System.in);

        LOGGER.info("How many tins of soup: ");
        shoppingBasket.putItems(SOUP, scan.nextInt());

        LOGGER.info("How many loafs of bread: ");
        shoppingBasket.putItems(BREAD, scan.nextInt());

        LOGGER.info("How many bottles of Milk: ");
        shoppingBasket.putItems(MILK, scan.nextInt());

        LOGGER.info("How many number of Apples: ");
        shoppingBasket.putItems(APPLE, scan.nextInt());

        LOGGER.info("******* CHECKOUT *******");

        shoppingBasket.getItems().forEach((item, quantity) -> displayBill(item, shoppingBasket));

        System.out.printf("Total Bill:  \t%f", Utils.computeBill(shoppingBasket, LocalDateTime.now()));
    }

    private static void displayBill(Item item, ShoppingBasket shoppingBasket) {
        System.out.printf("%d\t%s\t=\t%f%n", shoppingBasket.getItems().get(item), item.name(), item.getOffer().calculatePrice(shoppingBasket, item, LocalDateTime.now()));
    }

}
