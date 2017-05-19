package by.bytechs.entity.terminalParameters;

/**
 * Contains the name of the parameter and its identifier,
 * it is intended for definition of parameter identifier
 * @author Romanovich Andrei
 */
public enum EnumTerminalParameters {
    COMMUNICATION_CHANNEL(1),
    IP_ADDRESS(2),
    TERMINAL_ID(3),
    LOGICAL_NAME(4),
    NUMBER_PORT(5),
    MODEL(6),
    CBU(7),
    ADDRESS(8),
    LATITUDE(9),
    LONGITUDE(10),
    MODE_OPERATION(11),
    SERVICE_ACCESS_TIME(12),
    SUPPORT_TIME(13),
    BASE_CURRENCY(14);

    private final int value;

    EnumTerminalParameters(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
