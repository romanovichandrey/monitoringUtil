package by.bytechs.entity;

import java.io.Serializable;

/**
 * @author Romanovich Andrei
 */
public enum TerminalType implements Serializable {
    NA(-1), BANKOMAT(1), KIOSK(2), WRONG(0);

    private final int value;

    TerminalType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
