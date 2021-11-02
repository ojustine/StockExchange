package model;

public enum Stock {
    A("A"),
    B("B"),
    C("C"),
    D("D");

    private final String name;

    Stock(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Stock forName(String name) {
        switch (name) {
            case "A":
            case "a":
                return A;
            case "B":
            case "b":
                return B;
            case "C":
            case "c":
                return C;
            case "D":
            case "d":
                return D;
            default:
                throw new IllegalArgumentException("No stock for name: " + name);
        }
    }
}
