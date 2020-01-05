package grocery;


import java.math.BigDecimal;

public enum Item {

    APPLE(BigDecimal.valueOf(0.1)),
    MILK(BigDecimal.valueOf(1.3));

    private final BigDecimal price;

    Item(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }


}