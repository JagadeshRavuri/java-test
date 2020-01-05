package grocery;


import java.math.BigDecimal;

public enum Item {

    APPLE(BigDecimal.valueOf(0.1), new AppleOffer()),
    MILK(BigDecimal.valueOf(1.3), new Offer() {
    });

    private final BigDecimal price;
    private final Offer offer;

    Item(BigDecimal price, Offer offer) {
        this.price = price;
        this.offer = offer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Offer getOffer() {
        return offer;
    }
}