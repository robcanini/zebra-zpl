package com.develon.open.zpl.model.element;

import com.develon.open.zpl.constant.ZebraRotation;
import com.develon.open.zpl.model.PrinterOptions;
import com.develon.open.zpl.model.ZebraElement;
import com.develon.open.zpl.utils.ZplUtils;

import java.awt.*;

/**
 * Abstract Zebra element to represent a bar code instruction
 * <p>
 * Command ZPL : All instruction starting ^B
 *
 * @author ttropard
 */
public abstract class ZebraBarCode extends ZebraElement {

    /**
     * The Bar code heigth.
     */
    protected Integer barCodeHeigth;

    /**
     * The Module width.
     */
    private Integer moduleWidth;
    /**
     * The Wide bar ratio.
     */
    private Integer wideBarRatio;

    /**
     * The Zebra rotation.
     */
    ZebraRotation zebraRotation = ZebraRotation.NORMAL;

    /**
     * Parameters used to print text( default on bellow)
     */
    boolean showTextInterpretation = true;

    /**
     * Parameters to set to true if you want textInterpretation above code (top)
     */
    boolean showTextInterpretationAbove = false;

    /**
     * The Text.
     */
    String text;

    /**
     * Instantiates a new Zebra bar code.
     *
     * @param positionX the position x
     * @param positionY the position y
     * @param text      the text
     */
    ZebraBarCode(int positionX, int positionY, String text) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.text = text;
    }

    /**
     * Default Constructeur width position and text
     *
     * @param positionX     left margin (explain in dots)
     * @param positionY     top margin (explain in dots)
     * @param text          code to write
     * @param barCodeHeigth height of code bar
     */
    ZebraBarCode(int positionX, int positionY, String text, int barCodeHeigth) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.barCodeHeigth = barCodeHeigth;
        this.text = text;
    }

    /**
     * Default Constructeur width position and text
     *
     * @param positionX     left margin (explain in dots)
     * @param positionY     top margin (explain in dots)
     * @param text          code to write
     * @param barCodeHeigth height of code bar
     * @param moduleWidth   the module width
     * @param wideBarRatio  wide bar to narrow bar width ratio
     */
    ZebraBarCode(int positionX, int positionY, String text, int barCodeHeigth, int moduleWidth, int wideBarRatio) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.barCodeHeigth = barCodeHeigth;
        this.text = text;
        this.moduleWidth = moduleWidth;
        this.wideBarRatio = wideBarRatio;
    }

    /**
     * Default Constructor width position and text
     *
     * @param positionX              left margin (explain in dots)
     * @param positionY              top margin (explain in dots)
     * @param text                   code to write
     * @param barCodeHeigth          height of code bar
     * @param showTextInterpretation true to print interpretation line
     * @param moduleWidth            width(optionnal) of code bar
     * @param wideBarRatio           wide bar to narrow bar width ratio
     */
    ZebraBarCode(int positionX, int positionY, String text, int barCodeHeigth, boolean showTextInterpretation, int moduleWidth, int wideBarRatio) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.barCodeHeigth = barCodeHeigth;
        this.showTextInterpretation = showTextInterpretation;
        this.text = text;
        this.moduleWidth = moduleWidth;
        this.wideBarRatio = wideBarRatio;
    }

    /**
     * Constructeur used to print text (above or below) with code
     *
     * @param positionX                   left margin (explain in dots)
     * @param positionY                   top margin (explain in dots)
     * @param text                        code to write
     * @param barCodeHeigth               height of code bar
     * @param showTextInterpretation      true to print interpretation line
     * @param showTextInterpretationAbove true to add above, false to add below
     */
    ZebraBarCode(int positionX, int positionY, String text, int barCodeHeigth, boolean showTextInterpretation, boolean showTextInterpretationAbove) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.barCodeHeigth = barCodeHeigth;
        this.text = text;
        this.showTextInterpretation = showTextInterpretation;
        this.showTextInterpretationAbove = showTextInterpretationAbove;
    }

    /**
     * Gets start zpl code builder.
     *
     * @return the start zpl code builder
     */
    StringBuilder getStartZplCodeBuilder() {
        StringBuilder zpl = new StringBuilder();
        //On précise la position
        zpl.append(getZplCodePosition());
        zpl.append("\n");
        if (moduleWidth != null) {
            zpl.append(ZplUtils.zplCommandSautLigne("BY", moduleWidth, wideBarRatio, barCodeHeigth));
        }
        return zpl;
    }

    /**
     * Used to draw label preview.
     * This method should be overloader by child class.
     * <p>
     * Default draw a rectangle
     *
     * @param graphic
     */
    public void drawPreviewGraphic(PrinterOptions printerOptions, Graphics2D graphic) {
        int top = 0;
        int left = 0;
        if (positionX != null) {
            left = ZplUtils.convertPointInPixel(positionX);
        }
        if (positionY != null) {
            top = ZplUtils.convertPointInPixel(positionY);
        }
        graphic.setColor(Color.BLACK);

        Font font = new Font("Arial", Font.BOLD, barCodeHeigth / 2);

        graphic.drawRect(left, top, ZplUtils.convertPointInPixel(Math.round(moduleWidth * wideBarRatio * 9 * text.length())), ZplUtils.convertPointInPixel(barCodeHeigth));

        drawTopString(graphic, font, text, left, top);
    }

    /**
     * Gets bar code width.
     *
     * @return the bar code width
     */
    public Integer getBarCodeWidth() {
        return moduleWidth;
    }

    /**
     * Gets bar code heigth.
     *
     * @return the bar code heigth
     */
    public Integer getBarCodeHeigth() {
        return barCodeHeigth;
    }

    /**
     * Gets wide bar ratio.
     *
     * @return the wide bar ratio
     */
    public Integer getWideBarRatio() {
        return wideBarRatio;
    }

    /**
     * Gets zebra rotation.
     *
     * @return the zebra rotation
     */
    public ZebraRotation getZebraRotation() {
        return zebraRotation;
    }

    /**
     * Is show text interpretation boolean.
     *
     * @return the boolean
     */
    public boolean isShowTextInterpretation() {
        return showTextInterpretation;
    }

    /**
     * Is show text interpretation above boolean.
     *
     * @return the boolean
     */
    public boolean isShowTextInterpretationAbove() {
        return showTextInterpretationAbove;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets bar code width.
     *
     * @param barCodeWidth the bar code width
     * @return the bar code width
     */
    public ZebraBarCode setBarCodeWidth(Integer barCodeWidth) {
        this.moduleWidth = barCodeWidth;
        return this;
    }

    /**
     * Sets bar code heigth.
     *
     * @param barCodeHeigth the bar code heigth
     * @return the bar code heigth
     */
    public ZebraBarCode setBarCodeHeigth(Integer barCodeHeigth) {
        this.barCodeHeigth = barCodeHeigth;
        return this;
    }

    /**
     * Sets wide bar ratio.
     *
     * @param wideBarRatio the wide bar ratio
     * @return the wide bar ratio
     */
    public ZebraBarCode setWideBarRatio(Integer wideBarRatio) {
        this.wideBarRatio = wideBarRatio;
        return this;
    }

    /**
     * Sets zebra rotation.
     *
     * @param zebraRotation the zebra rotation
     * @return the zebra rotation
     */
    public ZebraBarCode setZebraRotation(ZebraRotation zebraRotation) {
        this.zebraRotation = zebraRotation;
        return this;
    }

    /**
     * Sets show text interpretation.
     *
     * @param showTextInterpretation the show text interpretation
     * @return the show text interpretation
     */
    public ZebraBarCode setShowTextInterpretation(boolean showTextInterpretation) {
        this.showTextInterpretation = showTextInterpretation;
        return this;
    }

    /**
     * Sets show text interpretation above.
     *
     * @param showTextInterpretationAbove the show text interpretation above
     * @return the show text interpretation above
     */
    public ZebraBarCode setShowTextInterpretationAbove(boolean showTextInterpretationAbove) {
        this.showTextInterpretationAbove = showTextInterpretationAbove;
        return this;
    }

    /**
     * Sets text.
     *
     * @param text the text
     * @return the text
     */
    public ZebraBarCode setText(String text) {
        this.text = text;
        return this;
    }

}
