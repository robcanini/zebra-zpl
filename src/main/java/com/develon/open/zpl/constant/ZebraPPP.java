package com.develon.open.zpl.constant;

/**
 * Contante used to define printed precision
 *
 * @author ttropard
 */
public enum ZebraPPP {

    /**
     * Dpi 203 zebra ppp.
     */
    DPI_203(8),

    /**
     * Dpi 300 zebra ppp.
     */
    DPI_300(12),

    /**
     * Dpi 600 zebra ppp.
     */
    DPI_600(23.5F);

    private float dotByMm;

    ZebraPPP(float dotByMm) {
        this.dotByMm = dotByMm;
    }

    /**
     * Gets dot by mm.
     *
     * @return the dotByMm
     */
    public float getDotByMm() {
        return dotByMm;
    }
}
