package com.develon.open.zpl.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import com.develon.open.zpl.utils.ZplUtils;

/**
 * The type Zebra element.
 */
public abstract class ZebraElement {

	/**
	 * x-axis location (in dots)
	 */
	protected Integer positionX;
	/**
	 * y-axis location (in dots)
	 */
	protected Integer positionY;

	/**
	 * Will draw a default box on the graphic if drawGraphic method is not overload
	 */
	protected boolean defaultDrawGraphic = true;

	/**
	 * Gets position x.
	 *
	 * @return the positionX
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * Sets position x.
	 *
	 * @param positionX the positionX to set
	 * @return the position x
	 */
	public ZebraElement setPositionX(int positionX) {
		this.positionX = positionX;
		return this;
	}

	/**
	 * Gets position y.
	 *
	 * @return the positionY
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * Sets position y.
	 *
	 * @param positionY the positionY to set
	 * @return the position y
	 */
	public ZebraElement setPositionY(int positionY) {
		this.positionY = positionY;
		return this;
	}

	/**
	 * Return Zpl code for this Element
	 *
	 * @param printerOptions the printer options
	 * @return zpl code
	 */
	public String getZplCode(PrinterOptions printerOptions) {
		return "";
	}

	/**
	 * Function used by child class if you want to set position before draw your element.
	 *
	 * @return zpl code position
	 */
	protected String getZplCodePosition() {

		StringBuilder zpl = new StringBuilder();
		if (positionX != null && positionY != null) {
			zpl.append(ZplUtils.zplCommand("FT", positionX, positionY));
		}
		return zpl.toString();
	}

	/**
	 * Used to draw label preview.
	 * This method should be overloader by child class.
	 * <p>
	 * Default draw a rectangle
	 *
	 * @param printerOptions TODO
	 * @param graphic        the graphic
	 */
	public void drawPreviewGraphic(PrinterOptions printerOptions, Graphics2D graphic) {
		if (defaultDrawGraphic) {
			int top = 0;
			int left = 0;
			if (positionX != null) {
				left = Math.round((positionX / printerOptions.getZebraPPP().getDotByMm()) * 10);
			}
			if (positionY != null) {
				top = Math.round((positionY / printerOptions.getZebraPPP().getDotByMm()) * 10);
			}
			graphic.setColor(Color.BLACK);
			graphic.drawRect(left, top, 100, 20);
			drawTopString(graphic, new Font("Arial", Font.BOLD, 11), "Default", left, top);
		}
	}

	/**
	 * Function to draw Element, based on top position.
	 * <p>
	 * Default drawString write text on vertical middle (Zebra not)
	 *
	 * @param graphic   the graphic
	 * @param font      the font
	 * @param text      the text
	 * @param positionX the position x
	 * @param positionY the position y
	 */
	protected void drawTopString(Graphics2D graphic, Font font, String text, int positionX, int positionY) {
		graphic.setFont(font);
		FontMetrics fm = graphic.getFontMetrics(font);
		Rectangle2D rect = fm.getStringBounds(text, graphic);
		int textHeight = (int) (rect.getHeight());
		positionY = positionY + textHeight;
		graphic.drawString(text, positionX, positionY); // Draw the string.
	}
}
