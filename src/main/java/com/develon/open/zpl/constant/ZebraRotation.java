package com.develon.open.zpl.constant;

/**
 * The enum Zebra rotation.
 */
public enum ZebraRotation {

    /**
     * Normal zebra rotation.
     */
    NORMAL("N"),

    /**
     * Rotate 90 zebra rotation.
     */
    ROTATE_90("R"),

    /**
     * Inverted zebra rotation.
     */
    INVERTED("I"),

    /**
     * Read from bottom zebra rotation.
     */
    READ_FROM_BOTTOM("B"),

    /**
     * Inherited zebra rotation.
     */
    INHERITED("");

    /**
     * The Letter.
     */
    String letter;

    ZebraRotation(String letter) {
        this.letter = letter;
    }

    /**
     * Gets letter.
     *
     * @return the letter
     */
    public String getLetter() {
        return letter;
    }

}
