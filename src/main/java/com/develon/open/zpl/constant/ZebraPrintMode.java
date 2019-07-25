package com.develon.open.zpl.constant;

/**
 * Command to determine this action the printer takes after a label or group of label has been printed.
 * <p>
 * ZPL command : ^MM
 *
 * @author ttropard
 */
public enum ZebraPrintMode {

    /**
     * Tear off zebra print mode.
     */
    TEAR_OFF("T"),

    /**
     * Rewind zebra print mode.
     */
    REWIND("R"),

    /**
     * Peel off select zebra print mode.
     */
    PEEL_OFF_SELECT("P", true),

    /**
     * Peel off noselect zebra print mode.
     */
    PEEL_OFF_NOSELECT("P", false),

    /**
     * Cutter zebra print mode.
     */
    CUTTER("C");

    /**
     * The Desired mode.
     */
    String desiredMode;
    /**
     * The Pre peel select.
     */
    String prePeelSelect;

    ZebraPrintMode(String desiredMode) {
        this.desiredMode = desiredMode;
        this.prePeelSelect = "";
    }

    ZebraPrintMode(String desiredMode, boolean prePeelSelectB) {
        this.desiredMode = desiredMode;
        if (prePeelSelectB) {
            prePeelSelect = ",Y";
        } else {
            prePeelSelect = ",N";
        }
    }

    /**
     * Gets desired mode.
     *
     * @return the desiredMode
     */
    public String getDesiredMode() {
        return desiredMode;
    }

    /**
     * Gets pre peel select.
     *
     * @return the prePeelSelect
     */
    public String getPrePeelSelect() {
        return prePeelSelect;
    }

    /**
     * Function which return ^MM command
     *
     * @return zpl code
     */
    public String getZplCode() {
        return "^MM" + desiredMode + prePeelSelect + "\n";
    }
}
