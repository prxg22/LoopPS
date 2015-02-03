package bmpManipulation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;




public class DecodeImage { 
	public static int getLSB(byte b){
		return 0x01 & b; 		
	}
	
	public static String decodeImage(BmpImage img){
		int i = 0, bitCounter = 0;
		byte letter = 0;
		byte[] data;
		char c = 'a';
		StringBuilder msg = new StringBuilder();
		
		data = img.getData();
		
		while(c != '\0' && i <= data.length){
			if(i % img.getWidth() == 0 && i != 0){
				i = i + img.getPadding();
			}
			
			letter = (byte) (letter + (DecodeImage.getLSB(data[i + img.getStart()]) << bitCounter));
			bitCounter++;
			if(bitCounter >= 8){
				bitCounter = 0;
				c = (char) letter;
				msg.append(c);
				letter = 0;
			}
			i++;
		}
		
		return new String(msg);
	}
	
	
	
	public static void main(String[] args) {
		
		BmpImage img = new BmpImage("testImage.bmp");
		System.out.println(DecodeImage.decodeImage(img));
		
		//System.out.println(map.msg);
	}
	
}
