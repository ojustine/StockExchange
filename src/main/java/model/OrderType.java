package model;

public enum OrderType {
    SALE("Sale"),
    BUY("Buy");

    private final String name;

    OrderType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static OrderType forName(String name) {
        switch (name) {
            case "s":
            case "S":
            case "sale":
            case "Sale":
            case "SALE":
                return SALE;
            case "b":
            case "B":
            case "buy":
            case "Buy":
            case "BUY":
                return BUY;
            default:
                throw new IllegalArgumentException("No order type for name: " + name);
        }
    }
}
