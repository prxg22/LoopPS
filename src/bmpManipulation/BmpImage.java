package bmpManipulation;

import java.io.File;
import java.io.FileInputStream;

public class BmpImage {
	private byte[] data;
	private int width;
	private int height;
	private int padding; 
	private int start;
	
	public BmpImage(String path) {
		FileInputStream fileInputStream = null;

		File file = new File(path);

		data = readFile(path);

		start = ((0xFF & data[13]) << 24)
				| ((0xFF & data[12]) << 16) | ((0xFF & data[11]) << 8)
				| ((0xFF & data[10]));
		;

		this.setWidth(data[18], data[19], data[20], data[21]);
		this.setHeight(data[22], data[23], data[24], data[25]);
		
		// cálculo do padding de cada linha (pixel = 3 bytes)
		padding = width*3 % 4;

	}
	
	// Carrega bmp em um array de bytes
	private byte[] readFile(String path) {
		FileInputStream fileInputStream = null;

		File file = new File(path);

		byte[] bmpFile = new byte[(int) file.length()];

		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bmpFile);
			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bmpFile;

	}
	
	private void setWidth(byte b1, byte b2, byte b3, byte b4) {
		width = BmpImage.byteToInt(b1, b2, b3, b4);
	}

	private void setHeight(byte b1, byte b2, byte b3, byte b4) {
		height = BmpImage.byteToInt(b1, b2, b3, b4);
	}
	
	static public int byteToInt(byte b1, byte b2, byte b3, byte b4){
		return ((0xFF & b4) << 24) | ((0xFF & b3) << 16) | ((0xFF & b2) << 8)
		| ((0xFF & b1));
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getPadding(){
		return padding;
	}
	
	public int getStart(){
		return start;
	}
	
	public byte[] getData(){
		return data;
	}
}
