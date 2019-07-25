package com.develon.open.zpl.utils;

import com.develon.open.zpl.model.ZebraLabel;
import com.develon.open.zpl.model.ZebraPrintException;
import com.develon.open.zpl.model.ZebraPrintNotFoundException;

import javax.print.*;
import javax.print.attribute.standard.PrinterName;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Utilities to print zpl
 *
 * @author ttropard
 */
public class ZebraUtils {

	/**
	 * Function to print code Zpl to network zebra
	 *
	 * @param zpl  code Zpl to print
	 * @param ip   ip adress
	 * @param port port
	 * @throws ZebraPrintException if zpl could not be printed
	 */
	public static void printZpl(String zpl, String ip, int port) throws ZebraPrintException {
		try {
			try (Socket clientSocket = new Socket(ip, port)) {
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				outToServer.writeBytes(zpl);
			}
		} catch (IOException e1) {
			throw new ZebraPrintException("Cannot print label on this printer : " + ip + ":" + port, e1);
		}
	}

	/**
	 * Function to print code Zpl to local zebra(usb)
	 *
	 * @param zpl         code Zpl to print
	 * @param printerName the printer name
	 * @throws ZebraPrintException if zpl could not be printed
	 */
	public static void printZpl(String zpl, String printerName) throws ZebraPrintException {
		try {
			PrintService psZebra = null;
			PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);

			for (PrintService service : services) {
				PrinterName attr = service.getAttribute(PrinterName.class);
				String sPrinterName = attr.getValue();
				if (sPrinterName.toLowerCase().contains(printerName)) {
					psZebra = service;
					break;
				}
			}

			if (psZebra == null) {
				throw new ZebraPrintNotFoundException("Zebra printer not found : " + printerName);
			}
			DocPrintJob job = psZebra.createPrintJob();

			byte[] by = zpl.getBytes();
			DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
			Doc doc = new SimpleDoc(by, flavor, null);
			job.print(doc, null);

		} catch (PrintException e) {
			throw new ZebraPrintException("Cannot print label on this printer : " + printerName, e);
		}
	}

	/**
	 * Fonction to print zebraLabel
	 *
	 * @param zebraLabel zebraLabel
	 * @param ip         ip adress
	 * @param port       port
	 * @throws ZebraPrintException if zpl could not be printed
	 */
	public static void printZpl(ZebraLabel zebraLabel, String ip, int port) throws ZebraPrintException {
		printZpl(zebraLabel.getZplCode(), ip, port);
	}

	/**
	 * Fonction to print zebraLabel
	 *
	 * @param zebraLabel  zebraLabel
	 * @param printerName the printer name
	 * @throws ZebraPrintException if zpl could not be printed
	 */
	public static void printZpl(ZebraLabel zebraLabel, String printerName) throws ZebraPrintException {
		printZpl(zebraLabel.getZplCode(), printerName);
	}

	/**
	 * Fonction to print multiple zebraLabel to network printer
	 *
	 * @param zebraLabels list of zebra labels
	 * @param ip          ip adress
	 * @param port        port
	 * @throws ZebraPrintException if zpl could not be printed
	 */
	public static void printZpl(List<ZebraLabel> zebraLabels, String ip, int port) throws ZebraPrintException {
		StringBuilder zpl = new StringBuilder();
		for (ZebraLabel zebraLabel : zebraLabels) {
			zpl.append(zebraLabel.getZplCode());
		}
		printZpl(zpl.toString(), ip, port);
	}

	/**
	 * Fonction to print multiple zebraLabel to local printer
	 *
	 * @param zebraLabels list of zebra labels
	 * @param printerName the printer name
	 * @throws ZebraPrintException if zpl could not be printed
	 */
	public static void printZpl(List<ZebraLabel> zebraLabels, String printerName) throws ZebraPrintException {
		StringBuilder zpl = new StringBuilder();
		for (ZebraLabel zebraLabel : zebraLabels) {
			zpl.append(zebraLabel.getZplCode());
		}
		printZpl(zpl.toString(), printerName);
	}

}
