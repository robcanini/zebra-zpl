package com.develon.open.zpl.constant;

/**
 * The enum Zebra font.
 */
public enum ZebraFont {

    /**
     * Zebra zero zebra font.
     */
    ZEBRA_ZERO("0"),

    /**
     * Zebra a zebra font.
     */
    ZEBRA_A("A"),

    /**
     * Zebra b zebra font.
     */
    ZEBRA_B("B"),

    /**
     * Zebra c zebra font.
     */
    ZEBRA_C("C"),

    /**
     * Zebra d zebra font.
     */
    ZEBRA_D("D"),

    /**
     * Zebra f zebra font.
     */
    ZEBRA_F("F"),

    /**
     * Zebra g zebra font.
     */
    ZEBRA_G("G");

    /**
     * The Letter.
     */
    String letter;

    ZebraFont(String letter) {
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

    /**
     * Fonction use for preview to find an equivalent font compatible with Graphic2D
     *
     * @param zebraFont the zebra font
     * @return string
     */
    public static String findBestEquivalentFontForPreview(ZebraFont zebraFont) {
        return "Arial";
    }
}
