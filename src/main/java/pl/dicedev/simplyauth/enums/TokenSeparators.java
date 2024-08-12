package pl.dicedev.simplyauth.enums;

import lombok.Getter;

@Getter
public enum TokenSeparators {
    SECTIONS("+"),
    DETAIL("-"),
    ;

    private final String separator;

    TokenSeparators(String separator) {
        this.separator = separator;
    }

    public String getSeparatorWithEscape() {
        return """
                \\%s
                """.trim().formatted(separator);
    }
}
