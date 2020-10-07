/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BMDPC;

import com.thowo.jmjavaframework.report.JMExcel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author User
 */
public class SimdaFromExcel {
    private List<AsetDBTool> asets;
    
    public SimdaFromExcel(String fileA, String fileB, String fileC, String fileD, String fileE, String fileF, String fileL){
        this.asets=new ArrayList();
        if(!fileA.equals(""))prosesA(fileA);
        if(!fileB.equals(""))prosesB(fileB);
        if(!fileC.equals(""))prosesC(fileC);
        if(!fileD.equals(""))prosesD(fileD);
        if(!fileE.equals(""))prosesE(fileE);
        if(!fileF.equals(""))prosesF(fileF);
        if(!fileL.equals(""))prosesL(fileL);
        for(AsetDBTool aset:this.asets){
            System.out.println("\n\n"+aset.myQueryInsertStr());
        }
    }
    private void prosesA(String fileXls){
        Integer totalDataCol=14;
        String kodeLokSign="NO. KODE LOKASI";
        String endOfDataSign="Jumlah Harga";
        Integer kodeLokasiDataStepFromSign=2;
        Integer dataBeginStepFromSign=3;
        Integer regColIndex=3;
        proses("A",fileXls,regColIndex, totalDataCol, kodeLokSign, endOfDataSign, kodeLokasiDataStepFromSign, dataBeginStepFromSign);
    }
    
    private void prosesB(String fileXls){
        Integer totalDataCol=16;
        String kodeLokSign="NO. KODE LOKASI";
        String endOfDataSign="Jumlah Harga";
        Integer kodeLokasiDataStepFromSign=2;
        Integer dataBeginStepFromSign=3;
        Integer regColIndex=3;
        proses("B",fileXls,regColIndex, totalDataCol, kodeLokSign, endOfDataSign, kodeLokasiDataStepFromSign, dataBeginStepFromSign);
    }
    
    private void prosesC(String fileXls){
        Integer totalDataCol=17;
        String kodeLokSign="NO. KODE LOKASI";
        String endOfDataSign="Jumlah Harga";
        Integer kodeLokasiDataStepFromSign=2;
        Integer dataBeginStepFromSign=3;
        Integer regColIndex=3;
        proses("C",fileXls,regColIndex, totalDataCol, kodeLokSign, endOfDataSign, kodeLokasiDataStepFromSign, dataBeginStepFromSign);
    }
    
    private void prosesD(String fileXls){
        Integer totalDataCol=17;
        String kodeLokSign="NO. KODE LOKASI";
        String endOfDataSign="Jumlah Harga";
        Integer kodeLokasiDataStepFromSign=2;
        Integer dataBeginStepFromSign=3;
        Integer regColIndex=3;
        proses("D",fileXls,regColIndex, totalDataCol, kodeLokSign, endOfDataSign, kodeLokasiDataStepFromSign, dataBeginStepFromSign);
    }
    
    private void prosesE(String fileXls){
        Integer totalDataCol=16;
        String kodeLokSign="NO. KODE LOKASI";
        String endOfDataSign="Jumlah Harga";
        Integer kodeLokasiDataStepFromSign=2;
        Integer dataBeginStepFromSign=3;
        Integer regColIndex=3;
        proses("E",fileXls,regColIndex, totalDataCol, kodeLokSign, endOfDataSign, kodeLokasiDataStepFromSign, dataBeginStepFromSign);
    }
    
    private void prosesF(String fileXls){
        Integer totalDataCol=15;
        String kodeLokSign="NO. KODE LOKASI";
        String endOfDataSign="Jumlah Harga";
        Integer kodeLokasiDataStepFromSign=2;
        Integer dataBeginStepFromSign=3;
        Integer regColIndex=-1;
        proses("F",fileXls,regColIndex, totalDataCol, kodeLokSign, endOfDataSign, kodeLokasiDataStepFromSign, dataBeginStepFromSign);
    }
    
    private void prosesL(String fileXls){
        Integer totalDataCol=12;
        String kodeLokSign="NO. KODE LOKASI";
        String endOfDataSign="Jumlah Harga";
        Integer kodeLokasiDataStepFromSign=2;
        Integer dataBeginStepFromSign=3;
        Integer regColIndex=3;
        proses("L",fileXls,regColIndex, totalDataCol, kodeLokSign, endOfDataSign, kodeLokasiDataStepFromSign, dataBeginStepFromSign);
    }
    
    private void proses(String kib, String fileXls, Integer regColIndex, Integer totalDataCol, String kodeLokSign, String endOfDataSign, Integer kodeLokasiDataStepFromSign, Integer dataBeginStepFromSign){
        JMExcel xls=JMExcel.create(fileXls).setSheet(0);
        List<List<String>> dataA=new ArrayList();
        boolean findData=false;
        String kodeLok="";
        boolean pauseCell=false;
        Integer multiRegStep=0;
        Integer multiRegStart=-1;
        while(xls.rowNotNull()){
            Integer col=-1;
            List<String> tmp=new ArrayList();
            if(findData)tmp.add(kodeLok);
            while(xls.cellNotNull()){    
                if(!pauseCell){
                    xls.nextCell();
                }else pauseCell=false;
                col++;
                if(!xls.cellNotNull())break;
                if(findData){
                    if(xls.getString().equals(endOfDataSign)){
                        tmp.remove(0);
                        findData=false;
                        break;
                    }else{
                        if(col<totalDataCol){
                            if(col==regColIndex){
                                String tmpReg=xls.getString();
                                if(tmpReg.indexOf("s/d")>=0){
                                    String[] regs=tmpReg.split(" s/d ");
                                    multiRegStart=Integer.valueOf(regs[0]);
                                    Integer multiRegFinish=Integer.valueOf(regs[1]);
                                    multiRegStep=multiRegFinish-multiRegStart;
                                }else{
                                    multiRegStart=Integer.valueOf(xls.getString());
                                }
                            }
                            if(xls.getCurrentCellNum()!=col){
                                for(int i=0;i<xls.getCurrentCellNum()-col+1;i++){
                                    tmp.add(""); 
                                    col++;
                                }
                                col--;
                                pauseCell=true;
                            }else tmp.add(xls.getString());
                        }else{
                            break;
                        }
                    }
                }else{
                    if(xls.getString().equals(kodeLokSign)){
                        for(int i=0;i<kodeLokasiDataStepFromSign;i++){
                            xls.nextCell();
                        }
                        kodeLok=xls.getString();
                        col--;
                        findData=true;
                        for(int i=0;i<dataBeginStepFromSign-1;i++){
                            xls.nextRow();
                        }
                        break;
                    }
                    break;
                }
                xls.nextCell();
            }
            if(!tmp.isEmpty()){
                if(col<totalDataCol-1){
                    for(int i=col;i<totalDataCol;i++){
                        tmp.add(""); 
                    }
                }
                //tmp.set(0, tmp.get(0)+"."+tmp.get(3)+"."+tmp.get(4));
                for(int i=0;i<multiRegStep+1;i++){
                    List<String> dupTmp=duplicateListString(tmp);
                    dupTmp.set(regColIndex+1, String.format("%06d", multiRegStart));
                    multiRegStart++;
                    dataA.add(dupTmp);
                }
                multiRegStep=0;
                multiRegStart=-1;
            }
            xls.nextRow();
        }
        for(List<String> myData:dataA){
            switch(kib){
                case "A":
                    this.asets.add(new AsetDBTool(myData.get(0), intFromString(myData.get(1)), myData.get(2), myData.get(3), myData.get(4), doubleFromString(myData.get(5)), intFromString(myData.get(6)), myData.get(7), myData.get(8), dateFromString(myData.get(9)), myData.get(10), myData.get(11), myData.get(12), doubleFromString(myData.get(13)), myData.get(14)));
                    break;
                case "B":
                    this.asets.add(new AsetDBTool(myData.get(0), intFromString(myData.get(1)), myData.get(2), myData.get(3), myData.get(4), myData.get(5), myData.get(6), myData.get(7), intFromString(myData.get(8)), myData.get(9), myData.get(10), myData.get(11), myData.get(12), myData.get(13), myData.get(14), doubleFromString(myData.get(15)), myData.get(16)));
                    break;
                case "C":
                    this.asets.add(new AsetDBTool(myData.get(0), intFromString(myData.get(1)), myData.get(2), myData.get(3), myData.get(4), myData.get(5), booleanFromString(myData.get(6)), booleanFromString(myData.get(7)), doubleFromString(myData.get(8)), myData.get(9), dateFromString(myData.get(10)), myData.get(11), doubleFromString(myData.get(12)), myData.get(13), myData.get(14), myData.get(15), doubleFromString(myData.get(16)), myData.get(17)));
                    break;
                case "D":
                    this.asets.add(new AsetDBTool(myData.get(0), intFromString(myData.get(1)), myData.get(2), myData.get(3), myData.get(4), myData.get(5), doubleFromString(myData.get(6)), doubleFromString(myData.get(7)), doubleFromString(myData.get(8)), myData.get(9), dateFromString(myData.get(10)), myData.get(11), myData.get(12), myData.get(13), myData.get(14), doubleFromString(myData.get(15)), myData.get(16), myData.get(17)));
                    break;
                case "E":
                    this.asets.add(new AsetDBTool(myData.get(0), intFromString(myData.get(1)), myData.get(2), myData.get(3), myData.get(4), myData.get(5), myData.get(6), myData.get(7), myData.get(8), myData.get(9), myData.get(10), myData.get(11), doubleFromString(myData.get(12)), intFromString(myData.get(13)), doubleFromString(myData.get(14)), myData.get(15)));
                    break;
                case "F":
                    this.asets.add(new AsetDBTool(myData.get(0), intFromString(myData.get(1)), myData.get(2), myData.get(3), booleanFromString(myData.get(4)), booleanFromString(myData.get(5)), doubleFromString(myData.get(6)), myData.get(7), dateFromString(myData.get(8)), myData.get(9), dateFromString(myData.get(10)), myData.get(11), myData.get(12), myData.get(13), doubleFromString(myData.get(14)), myData.get(15)));
                    break;
                case "L":
                    this.asets.add(new AsetDBTool(myData.get(0), intFromString(myData.get(1)), myData.get(2), myData.get(3), myData.get(4), intFromString(myData.get(5)), myData.get(6), myData.get(7), myData.get(8), myData.get(9), myData.get(10), doubleFromString(myData.get(11)), myData.get(12)));
                    break;

            }
        }
    }
    
    private List<String> duplicateListString(List<String> ls){
        List<String> ret= new ArrayList();
        for(String tmp:ls){
            ret.add(tmp);
        }
        return ret;
    }
    
    private Integer intFromString(String i){
        if(i.equals(""))return 0;
        return Integer.valueOf((int) Math.round(Double.valueOf(i)));
    }
    
    private boolean booleanFromString(String i){
        if(i.equals("TRUE") || i.equals("true") || i.equals("True")) return true;
        return false;
    }
    
    private Double doubleFromString(String i){
        if(i.equals(""))return 0.0;
        return Double.valueOf(i);
    }
    
    private Date dateFromString(String i){
        if(i.equals(""))return null;
        return dateFromSerialString(i);
    }
    
    private Date dateFromSerialString(String serial){
        Date ret=null;
        if(!serial.equals("")){
            BigDecimal countFromEpoch= new BigDecimal(serial);
            long days=countFromEpoch.longValue();
            LocalDate localDate= LocalDate.of(1899, Month.DECEMBER, 30).plusDays(days);
            
            ret=Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        return ret;
    }
    
}
