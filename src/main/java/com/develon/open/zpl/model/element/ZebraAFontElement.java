package com.develon.open.zpl.model.element;

import com.develon.open.zpl.model.PrinterOptions;
import com.develon.open.zpl.model.ZebraElement;
import com.develon.open.zpl.utils.ZplUtils;
import com.develon.open.zpl.constant.ZebraFont;
import com.develon.open.zpl.constant.ZebraRotation;

/**
 * Element to set a font and size (explain in dot).
 * <p>
 * This command is apply only on the next element (in zebraElements list).
 * <p>
 * This command could be use when your font and PPP is not yet implemented
 * <p>
 * ZPL Command : ^A
 *
 * @author ttropard
 */
public class ZebraAFontElement extends ZebraElement {

	private ZebraFont zebraFont;

	private ZebraRotation zebraRotation = ZebraRotation.INHERITED;

	private int dotHeigth;
	private int dotsWidth;

	/**
	 * Use this constructor if you want to change the font of the next element.
	 *
	 * @param zebraFont zebra font
	 */
	public ZebraAFontElement(ZebraFont zebraFont) {
		super();
		this.zebraFont = zebraFont;
	}

	/**
	 * Constructor to set font and size of the next element
	 *
	 * @param zebraFont font zebra
	 * @param dotHeigth height explain in dots
	 * @param dotsWidth height explain in dots
	 */
	public ZebraAFontElement(ZebraFont zebraFont, int dotHeigth, int dotsWidth) {
		super();
		this.zebraFont = zebraFont;
		this.dotHeigth = dotHeigth;
		this.dotsWidth = dotsWidth;
	}

	/**
	 * Constructor to use if you want have non-horizontal text.
	 *
	 * @param zebraFont     font zebra
	 * @param zebraRotation text rotation
	 * @param dotHeigth     height explain in dots
	 * @param dotsWidth     height explain in dots
	 */
	public ZebraAFontElement(ZebraFont zebraFont, ZebraRotation zebraRotation, int dotHeigth, int dotsWidth) {
		super();
		this.zebraFont = zebraFont;
		this.zebraRotation = zebraRotation;
		this.dotHeigth = dotHeigth;
		this.dotsWidth = dotsWidth;
	}

	/* (non-Javadoc)
	 * @see ZebraElement#getZplCode(PrinterOptions)
	 */
	@Override
	public String getZplCode(PrinterOptions printerOptions) {
		return ZplUtils.zplCommandSautLigne("A", zebraFont.getLetter(), zebraRotation.getLetter(), dotHeigth, dotsWidth).toString();
	}
}
