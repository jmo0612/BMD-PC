/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BMDPC;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.thowo.jmjavaframework.report.JMExcel;
import com.thowo.jmjavaframework.report.JMWord;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class Global {
    public static void makeQR(){
        JMExcel xls=JMExcel.create("E:\\jm\\work\\aset\\2019.xlsx").setSheet("advance");
        while(xls.rowNotNull()){
            if(xls.getCurrentRowNum()>0){
                String text="";
                String fn="";
                while(xls.cellNotNull()){
                    if(xls.getCurrentCellNum()==0){
                        text=xls.getString();
                    }else if(xls.getCurrentCellNum()==44){
                        fn=xls.getString();
                    }
                    xls.nextCell();
                }
                if(!text.equals("") && !fn.equals("")){
                    //System.out.println(text+"   "+fn);
                    genQR(text,"E:\\jm\\work\\aset\\2019QRs\\"+fn+".png");
                }
            }
            xls.nextRow();
        }
        System.out.println("FINISHED");
    }
    
    public static void genQR(String text,String imgFile){
        try {
            //text = "https://crunchify.com/";
            //imgFile = "E:\\jm\\work\\aset\\2019QRs\\CrunchifyQR.png";
            int size = 250;
            String fileType = "png";
            File myFile = new File(imgFile);
            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            
            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();
            
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, fileType, myFile);
            System.out.println("\n\nYou have successfully created QR Code.");
        } catch (WriterException ex) {
            Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
