package pl.dicedev.simplyauth.enums;

public enum Rights {

    USER ("name", "position"),
    TRAINEE ("nickname", "nn"),
    ADD_USERS ("au", "create"),
    REMOVE_USERS ("ru", "delete"),
    ORDER_FOOD ("login", "company"),
    ADD_RESERVATIONS ("ar", "customer"),
    REMOVE_RESERVATIONS ("rr", "cut"),
    ;

    private final String ust;
    private final String xst;

    Rights(String ust, String xst) {
        this.ust = ust;
        this.xst = xst;
    }

    public String getUst() {
        return ust;
    }

    public String getXst() {
        return xst;
    }

    public static Rights findByName(String value) {
        for (Rights r : values()) {
            if (r.name().equals(value)) {
                return r;
            }
        }
        return null;
    }
}
