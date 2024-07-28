package pl.dicedev.simplyauth.enums;

public enum TokenSeparators {
    SECTIONS("+"),
    DETAIL("-"),
    ;

    private final String separator;

    TokenSeparators(String separator) {
        this.separator = separator;
    }

    public String getSeparator() {
        return separator;
    }

    public String getSeparatorWithEscape() {
        return """
                \\%s
                """.trim().formatted(separator);
    }
}
