/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BMDPC;

import com.thowo.jmjavaframework.JMDate;
import com.thowo.jmjavaframework.JMFormatCollection;
import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.db.JMResultSet;
import com.thowo.jmjavaframework.report.JMExcel;
import com.thowo.jmjavaframework.table.JMRow;
import com.thowo.jmjavaframework.table.JMTable;
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
    //private List<AsetDBTool> asets;
    private DoubleInterface[] dblInterfaces;
    
    private String mapLok;
    private String mapRek;
    private List<DBToolKibA> kibA;
    private List<DBToolKibB> kibB;
    private List<DBToolKibC> kibC;
    private List<DBToolKibD> kibD;
    private List<DBToolKibE> kibE;
    private List<DBToolKibF> kibF;
    private List<DBToolKibLainnya> kibLainnya;
    private List<DBToolInventaris> inventaris;
    private int dup=0;
    
    private JMTable tMapLok=null;
    private JMTable tMapRek=null;
    
    public static SimdaFromExcel create(DoubleInterface[] dblInterfaces,String mapLok, String mapRek,String fileA, String fileB, String fileC, String fileD, String fileE, String fileF, String fileL, String fileInventaris){
        return new SimdaFromExcel(dblInterfaces,mapLok,mapRek,fileA, fileB, fileC, fileD, fileE, fileF, fileL, fileInventaris);
    }
    
    public SimdaFromExcel(DoubleInterface[] dblInterfaces,String mapLok, String mapRek,String fileA, String fileB, String fileC, String fileD, String fileE, String fileF, String fileL, String fileInventaris){
        //this.asets=new ArrayList();
        boolean completed=true;
        this.dblInterfaces=dblInterfaces;
        this.mapLok=mapLok;
        this.mapRek=mapRek;
        this.kibA=new ArrayList();
        this.kibB=new ArrayList();
        this.kibC=new ArrayList();
        this.kibD=new ArrayList();
        this.kibE=new ArrayList();
        this.kibF=new ArrayList();
        this.kibLainnya=new ArrayList();
        this.inventaris=new ArrayList();
        
        if(!mapLok.equals("")){
            this.fillMapLok();
        }
        if(!mapRek.equals("")){
           this.fillMapRek();
        }
        this.tMapLok=JMTable.create("select * from tb_map_rek_lok", JMTable.DBTYPE_MYSQL);
        this.tMapLok.addKeyColumn(0);
        this.tMapRek=JMTable.create("select * from tb_map_rek_barang", JMTable.DBTYPE_MYSQL);
        this.tMapRek.addKeyColumn(0);
        if(!fileA.equals("")){
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("delete from tb_xls_a where manual='0'", false);
            if(!fileA.equals("$EMPTY"))prosesA(fileA);
        }
        if(!fileB.equals("")){
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("delete from tb_xls_b where manual='0'", false);
            if(!fileB.equals("$EMPTY"))prosesB(fileB);
        }
        if(!fileC.equals("")){
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("delete from tb_xls_c where manual='0'", false);
            if(!fileC.equals("$EMPTY"))prosesC(fileC);
        }
        if(!fileD.equals("")){
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("delete from tb_xls_d where manual='0'", false);
            if(!fileD.equals("$EMPTY"))prosesD(fileD);
        }
        if(!fileE.equals("")){
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("delete from tb_xls_e where manual='0'", false);
            if(!fileE.equals("$EMPTY"))prosesE(fileE);
        }
        if(!fileF.equals("")){
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("delete from tb_xls_f where manual='0'", false);
            if(!fileF.equals("$EMPTY"))prosesF(fileF);
        }
        if(!fileL.equals("")){
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("delete from tb_xls_lainnya where manual='0'", false);
            if(!fileL.equals("$EMPTY"))prosesL(fileL);
        }
        if(!fileInventaris.equals("")){
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("delete from tb_xls_inventaris where manual='0'", false);
            prosesInventaris(fileInventaris);
        }
        
        if(this.kibA.size()!=0){
            String q="";
            for(DBToolKibA aset:this.kibA){
                if(q.equals("")){
                    q=aset.getQueryHeader()+ " values"+aset.getQueryValues();
                }else{
                    if(q.charAt(q.length()-1)==')')q+=",";
                    q+=aset.getQueryValues();
                }
            }
            //JMFunctions.trace(q);
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("insert into "+q, false);
            
        }
        
        if(this.kibB.size()!=0){
            String q="";
            for(DBToolKibB aset:this.kibB){
                if(q.equals("")){
                    q=aset.getQueryHeader()+ " values"+aset.getQueryValues();
                }else{
                    if(q.charAt(q.length()-1)==')')q+=",";
                    q+=aset.getQueryValues();
                }
            }
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("insert into "+q, false);
        }
        
        if(this.kibC.size()!=0){
            String q="";
            for(DBToolKibC aset:this.kibC){
                if(q.equals("")){
                    q=aset.getQueryHeader()+ " values"+aset.getQueryValues();
                }else{
                    if(q.charAt(q.length()-1)==')')q+=",";
                    q+=aset.getQueryValues();
                }
            }
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("insert into "+q, false);
        }
        
        if(this.kibD.size()!=0){
            String q="";
            for(DBToolKibD aset:this.kibD){
                if(q.equals("")){
                    q=aset.getQueryHeader()+ " values"+aset.getQueryValues();
                }else{
                    if(q.charAt(q.length()-1)==')')q+=",";
                    q+=aset.getQueryValues();
                }
            }
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("insert into "+q, false);
        }
        
        if(this.kibE.size()!=0){
            String q="";
            for(DBToolKibE aset:this.kibE){
                if(q.equals("")){
                    q=aset.getQueryHeader()+ " values"+aset.getQueryValues();
                }else{
                    if(q.charAt(q.length()-1)==')')q+=",";
                    q+=aset.getQueryValues();
                }
            }
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("insert into "+q, false);
        }
        
        if(this.kibF.size()!=0){
            String q="";
            for(DBToolKibF aset:this.kibF){
                if(q.equals("")){
                    q=aset.getQueryHeader()+ " values"+aset.getQueryValues();
                }else{
                    if(q.charAt(q.length()-1)==')')q+=",";
                    q+=aset.getQueryValues();
                }
            }
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("insert into "+q, false);
        }
        
        if(this.kibLainnya.size()!=0){
            String q="";
            for(DBToolKibLainnya aset:this.kibLainnya){
                if(q.equals("")){
                    q=aset.getQueryHeader()+ " values"+aset.getQueryValues();
                }else{
                    if(q.charAt(q.length()-1)==')')q+=",";
                    q+=aset.getQueryValues();
                }
            }
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("insert into "+q, false);
        }
        
        if(this.inventaris.size()!=0){
            String q="";
            for(DBToolInventaris aset:this.inventaris){
                if(q.equals("")){
                    q=aset.getQueryHeader()+ " values"+aset.getQueryValues();
                }else{
                    if(q.charAt(q.length()-1)==')')q+=",";
                    q+=aset.getQueryValues();
                }
            }
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("insert into "+q, false);
            if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL("update tb_inventaris "
                    + "set "
                    + "kd_xls_inventaris='', "
                    + "kd_xls_kib_a='', "
                    + "kd_xls_kib_b='', "
                    + "kd_xls_kib_c='', "
                    + "kd_xls_kib_d='', "
                    + "kd_xls_kib_e='', "
                    + "kd_xls_kib_f='', "
                    + "kd_xls_kib_lainnya=''"
                    + " where manual='0'"
                    , false);
            
            q=QueryHelper.q01NoDouble();
            if(!q.equals(""))if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL(q, false);
            
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_inventaris");
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_a");
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_b");
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_c");
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_d");
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_e");
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_f");
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_lainnya");
            
            q=QueryHelper.q05NewInvUpdate();
            if(!q.equals(""))if(completed)completed=JMFunctions.getCurrentConnection().queryUpdateMySQL(q, false);
            
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_inventaris");
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_a");
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_b");
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_c");
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_d");
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_e");
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_f");
            if(completed)completed=dblInterfaces[0].getSync("tb_xls_lainnya");
            
            if(completed)completed=dblInterfaces[1].getSync("");
            
            if(!completed){
                JMFunctions.traceAndShow("Update failed. Please try again");
            }
        }
        JMFunctions.trace("SELESAI");
    }
    
    private void fillMapLok(){
        if(this.mapLok.equals(""))return;
        JMExcel xls=JMExcel.create(this.mapLok).setSheet(0);
        String q="";
        while(xls.rowNotNull()){
            if(xls.getCurrentRowNum()==0)xls.nextRow();
            String vals="";
            while(xls.cellNotNull()){
                if(xls.getCurrentCellNum()==0){
                    vals="('"+xls.getString()+"'";
                }else{
                    vals+=",'"+xls.getString()+"'";
                }
                xls.nextCell();
            }
            if(!vals.equals(""))vals+=")";
            if(q.equals("")){
                q=vals;
            }else{
                q+=","+vals;
            }
            xls.nextRow();
        }
        if(q.equals(""))return;
        JMFunctions.getCurrentConnection().queryUpdateMySQL("delete from tb_map_rek_lok", false);
        q="insert into tb_map_rek_lok(rek_lok_xls,rek_lok_app) values"+q;
        JMFunctions.getCurrentConnection().queryUpdateMySQL(q, false);
    }
    private void fillMapRek(){
        if(this.mapRek.equals(""))return;
        JMExcel xls=JMExcel.create(this.mapRek).setSheet(0);
        String q="";
        while(xls.rowNotNull()){
            if(xls.getCurrentRowNum()==0)xls.nextRow();
            String vals="";
            while(xls.cellNotNull()){
                if(xls.getCurrentCellNum()==0){
                    vals="('"+xls.getString()+"'";
                }else{
                    vals+=",'"+xls.getString()+"'";
                }
                xls.nextCell();
            }
            if(!vals.equals(""))vals+=")";
            if(q.equals("")){
                q=vals;
            }else{
                q+=","+vals;
            }
            xls.nextRow();
        }
        if(q.equals(""))return;
        JMFunctions.getCurrentConnection().queryUpdateMySQL("delete from tb_map_rek_barang", false);
        q="insert into tb_map_rek_barang(rek_barang_xls,rek_barang_app) values"+q;
        JMFunctions.getCurrentConnection().queryUpdateMySQL(q, false);
    }
    
    private void prosesA(String fileXls){
        Integer totalDataCol=14;
        String kodeLokSign="NO. KODE LOKASI";
        String endOfDataSign="Jumlah Harga";
        Integer kodeLokasiDataStepFromSign=2;
        List<Integer> kodeLokNoZeroIndex=new ArrayList();
        kodeLokNoZeroIndex.add(7);
        Integer dataBeginStepFromSign=3;
        Integer regColIndex=3;
        Integer hargaColIndex=12;
        Double multiplyHarga=1000.0;
        proses("A",fileXls,regColIndex, totalDataCol, kodeLokSign, endOfDataSign, kodeLokasiDataStepFromSign, dataBeginStepFromSign,hargaColIndex,multiplyHarga,kodeLokNoZeroIndex);
    }
    
    private void prosesB(String fileXls){
        Integer totalDataCol=16;
        String kodeLokSign="NO. KODE LOKASI";
        String endOfDataSign="Jumlah Harga";
        Integer kodeLokasiDataStepFromSign=2;
        List<Integer> kodeLokNoZeroIndex=new ArrayList();
        kodeLokNoZeroIndex.add(7);
        Integer dataBeginStepFromSign=3;
        Integer regColIndex=3;
        Integer hargaColIndex=14;
        Double multiplyHarga=1000.0;
        proses("B",fileXls,regColIndex, totalDataCol, kodeLokSign, endOfDataSign, kodeLokasiDataStepFromSign, dataBeginStepFromSign,hargaColIndex,multiplyHarga,kodeLokNoZeroIndex);
    }
    
    private void prosesC(String fileXls){
        Integer totalDataCol=17;
        String kodeLokSign="NO. KODE LOKASI";
        String endOfDataSign="Jumlah Harga";
        Integer kodeLokasiDataStepFromSign=2;
        List<Integer> kodeLokNoZeroIndex=new ArrayList();
        kodeLokNoZeroIndex.add(7);
        Integer dataBeginStepFromSign=3;
        Integer regColIndex=3;
        Integer hargaColIndex=15;
        Double multiplyHarga=1000.0;
        proses("C",fileXls,regColIndex, totalDataCol, kodeLokSign, endOfDataSign, kodeLokasiDataStepFromSign, dataBeginStepFromSign,hargaColIndex,multiplyHarga,kodeLokNoZeroIndex);
    }
    
    private void prosesD(String fileXls){
        Integer totalDataCol=17;
        String kodeLokSign="NO. KODE LOKASI";
        List<Integer> kodeLokNoZeroIndex=new ArrayList();
        kodeLokNoZeroIndex.add(7);
        String endOfDataSign="Jumlah Harga";
        Integer kodeLokasiDataStepFromSign=2;
        Integer dataBeginStepFromSign=3;
        Integer regColIndex=3;
        Integer hargaColIndex=14;
        Double multiplyHarga=1000.0;
        proses("D",fileXls,regColIndex, totalDataCol, kodeLokSign, endOfDataSign, kodeLokasiDataStepFromSign, dataBeginStepFromSign,hargaColIndex,multiplyHarga,kodeLokNoZeroIndex);
    }
    
    private void prosesE(String fileXls){
        Integer totalDataCol=16;
        String kodeLokSign="NO. KODE LOKASI";
        String endOfDataSign="Jumlah Harga";
        Integer kodeLokasiDataStepFromSign=2;
        List<Integer> kodeLokNoZeroIndex=new ArrayList();
        kodeLokNoZeroIndex.add(7);
        Integer dataBeginStepFromSign=3;
        Integer regColIndex=3;
        Integer hargaColIndex=14;
        Double multiplyHarga=1000.0;
        proses("E",fileXls,regColIndex, totalDataCol, kodeLokSign, endOfDataSign, kodeLokasiDataStepFromSign, dataBeginStepFromSign,hargaColIndex,multiplyHarga,kodeLokNoZeroIndex);
    }
    
    private void prosesF(String fileXls){
        Integer totalDataCol=15;
        String kodeLokSign="NO. KODE LOKASI";
        String endOfDataSign="Jumlah Harga";
        Integer kodeLokasiDataStepFromSign=2;
        List<Integer> kodeLokNoZeroIndex=new ArrayList();
        kodeLokNoZeroIndex.add(7);
        Integer dataBeginStepFromSign=3;
        Integer regColIndex=-1;
        Integer hargaColIndex=13;
        Double multiplyHarga=1000.0;
        proses("F",fileXls,regColIndex, totalDataCol, kodeLokSign, endOfDataSign, kodeLokasiDataStepFromSign, dataBeginStepFromSign,hargaColIndex,multiplyHarga,kodeLokNoZeroIndex);
    }
    
    private void prosesL(String fileXls){
        Integer totalDataCol=12;
        String kodeLokSign="NO. KODE LOKASI";
        String endOfDataSign="Jumlah Harga";
        Integer kodeLokasiDataStepFromSign=2;
        List<Integer> kodeLokNoZeroIndex=new ArrayList();
        kodeLokNoZeroIndex.add(7);
        Integer dataBeginStepFromSign=3;
        Integer regColIndex=3;
        Integer hargaColIndex=10;
        Double multiplyHarga=1000.0;
        proses("L",fileXls,regColIndex, totalDataCol, kodeLokSign, endOfDataSign, kodeLokasiDataStepFromSign, dataBeginStepFromSign,hargaColIndex,multiplyHarga,kodeLokNoZeroIndex);
    }
    
    private void prosesInventaris(String fileXls){
        Integer totalDataCol=15;
        String kodeLokSign="NO. KODE LOKASI";
        String endOfDataSign="Jumlah";
        Integer kodeLokasiDataStepFromSign=0;
        List<Integer> kodeLokNoZeroIndex=new ArrayList();
        kodeLokNoZeroIndex.add(7);
        Integer dataBeginStepFromSign=3;
        Integer regColIndex=2;
        Integer hargaColIndex=13;
        Double multiplyHarga=1.0;
        proses("Inventaris",fileXls,regColIndex, totalDataCol, kodeLokSign, endOfDataSign, kodeLokasiDataStepFromSign, dataBeginStepFromSign,hargaColIndex,multiplyHarga,kodeLokNoZeroIndex);
    }
    
    private void proses(String kib, String fileXls, Integer regColIndex, Integer totalDataCol, String kodeLokSign, String endOfDataSign, Integer kodeLokasiDataStepFromSign, Integer dataBeginStepFromSign,Integer hargaColIndex,Double multiplyHarga, List<Integer> kodeLokNoZeroIndex){
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
                            }else{
                                
                                tmp.add(xls.getString());
                            }
                        }else{
                            break;
                        }
                    }
                }else{
                    if(xls.getString().contains(kodeLokSign)){
                        if(kodeLokasiDataStepFromSign==0){
                            String kdLokTmp=xls.getString();
                            String[] lokArr=JMFormatCollection.strToArray(kdLokTmp, "[:]");
                            if(lokArr.length==2){
                                kodeLok=JMFunctions.removeSpaces(lokArr[1]);
                                findData=true;
                                for(int i=0;i<dataBeginStepFromSign-1;i++){
                                    xls.nextRow();
                                }
                                break;
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
                        }
                    }
                    break;
                }
                if(!pauseCell){
                    xls.nextCell();
                }else pauseCell=false;
            }
            
            if(!tmp.isEmpty()){
                if(multiRegStep>0){
                    Double hrg=JMFormatCollection.strToDouble(tmp.get(hargaColIndex+1));
                    hrg=hrg/(multiRegStep+1);
                    tmp.set(hargaColIndex+1, String.valueOf(hrg));
                }
                if(col<totalDataCol-1){
                    for(int i=col;i<totalDataCol;i++){
                        tmp.add(""); 
                    }
                }
                //tmp.set(0, tmp.get(0)+"."+tmp.get(3)+"."+tmp.get(4));
                
                for(int i=0;i<multiRegStep+1;i++){
                    //if(kib.equals("F"))JMFunctions.traceAndShow(multiRegStep+"");
                    List<String> dupTmp=duplicateListString(tmp);
                    if(regColIndex!=-1)dupTmp.set(regColIndex+1, String.format("%06d", multiRegStart));
                    multiRegStart++;
                    dataA.add(dupTmp);
                }
                multiRegStep=0;
                multiRegStart=-1;
            }
            xls.nextRow();
        }
        for(List<String> myData:dataA){
            String dupAdder="";
            List<String> keys=new ArrayList();
            JMRow tmp=null;
            switch(kib){
                case "A":
                    //this.asets.add(new AsetDBTool(myData.get(0), intFromString(myData.get(1)), myData.get(2), myData.get(3), myData.get(4), doubleFromString(myData.get(5)), intFromString(myData.get(6)), myData.get(7), myData.get(8), dateFromString(myData.get(9)), myData.get(10), myData.get(11), myData.get(12), doubleFromString(myData.get(13)), myData.get(14)));
                    keys=new ArrayList();
                    keys.add(myData.get(0));
                    tmp=this.tMapLok.findByKeys(keys);
                    if(tmp!=null)myData.set(0, tmp.getCells().get(1).getValueString());
                    
                    keys=new ArrayList();
                    keys.add(myData.get(3));
                    tmp=this.tMapRek.findByKeys(keys);
                    if(tmp!=null)myData.set(3, tmp.getCells().get(1).getValueString());
                    
                    dupAdder=this.dupAdderA(myData.get(0), myData.get(3), myData.get(4));
                    this.kibA.add(DBToolKibA.createKIB(
                            myData.get(0), 
                            myData.get(3), 
                            myData.get(4), 
                            myData.get(2), 
                            myData.get(5), 
                            myData.get(6), 
                            myData.get(7), 
                            myData.get(8), 
                            myData.get(9), 
                            myData.get(10), 
                            myData.get(11), 
                            myData.get(12), 
                            myData.get(13), 
                            myData.get(14),
                            multiplyHarga,
                            dupAdder,kodeLokNoZeroIndex)
                    );
                    break;
                case "B":
                    //this.asets.add(new AsetDBTool(myData.get(0), intFromString(myData.get(1)), myData.get(2), myData.get(3), myData.get(4), myData.get(5), myData.get(6), myData.get(7), intFromString(myData.get(8)), myData.get(9), myData.get(10), myData.get(11), myData.get(12), myData.get(13), myData.get(14), doubleFromString(myData.get(15)), myData.get(16)));
                    keys=new ArrayList();
                    keys.add(myData.get(0));
                    tmp=this.tMapLok.findByKeys(keys);
                    if(tmp!=null)myData.set(0, tmp.getCells().get(1).getValueString());
                    
                    keys=new ArrayList();
                    keys.add(myData.get(2));
                    tmp=this.tMapRek.findByKeys(keys);
                    if(tmp!=null)myData.set(2, tmp.getCells().get(1).getValueString());
                    
                    dupAdder=this.dupAdderB(myData.get(0), myData.get(2), myData.get(4));
                    this.kibB.add(
                            DBToolKibB.createKIB( 
                                    myData.get(0), 
                                    myData.get(2), 
                                    myData.get(4), 
                                    myData.get(3), 
                                    myData.get(5), 
                                    myData.get(6), 
                                    myData.get(7), 
                                    myData.get(8), 
                                    myData.get(9), 
                                    myData.get(10), 
                                    myData.get(11), 
                                    myData.get(12), 
                                    myData.get(13), 
                                    myData.get(14), 
                                    myData.get(15), 
                                    myData.get(16),
                                    multiplyHarga,
                                    dupAdder,kodeLokNoZeroIndex
                            )
                    );
                    break;
                case "C":
                    //this.asets.add(new AsetDBTool(myData.get(0), intFromString(myData.get(1)), myData.get(2), myData.get(3), myData.get(4), myData.get(5), booleanFromString(myData.get(6)), booleanFromString(myData.get(7)), doubleFromString(myData.get(8)), myData.get(9), dateFromString(myData.get(10)), myData.get(11), doubleFromString(myData.get(12)), myData.get(13), myData.get(14), myData.get(15), doubleFromString(myData.get(16)), myData.get(17)));
                    keys=new ArrayList();
                    keys.add(myData.get(0));
                    tmp=this.tMapLok.findByKeys(keys);
                    if(tmp!=null)myData.set(0, tmp.getCells().get(1).getValueString());
                    
                    keys=new ArrayList();
                    keys.add(myData.get(3));
                    tmp=this.tMapRek.findByKeys(keys);
                    if(tmp!=null)myData.set(3, tmp.getCells().get(1).getValueString());
                    
                    dupAdder=this.dupAdderC(myData.get(0), myData.get(3), myData.get(4));
                    this.kibC.add(
                            DBToolKibC.createKIB(
                                    myData.get(0), 
                                    myData.get(3), 
                                    myData.get(4), 
                                    myData.get(2), 
                                    myData.get(5), 
                                    myData.get(6), 
                                    myData.get(7), 
                                    myData.get(8), 
                                    myData.get(9), 
                                    myData.get(10), 
                                    myData.get(11), 
                                    myData.get(12), 
                                    myData.get(13), 
                                    myData.get(14), 
                                    myData.get(15), 
                                    myData.get(16), 
                                    myData.get(17),
                                    multiplyHarga,
                                    dupAdder,kodeLokNoZeroIndex
                            )
                    );
                    break;
                case "D":
                    //this.asets.add(new AsetDBTool(myData.get(0), intFromString(myData.get(1)), myData.get(2), myData.get(3), myData.get(4), myData.get(5), doubleFromString(myData.get(6)), doubleFromString(myData.get(7)), doubleFromString(myData.get(8)), myData.get(9), dateFromString(myData.get(10)), myData.get(11), myData.get(12), myData.get(13), myData.get(14), doubleFromString(myData.get(15)), myData.get(16), myData.get(17)));
                    keys=new ArrayList();
                    keys.add(myData.get(0));
                    tmp=this.tMapLok.findByKeys(keys);
                    if(tmp!=null)myData.set(0, tmp.getCells().get(1).getValueString());
                    
                    keys=new ArrayList();
                    keys.add(myData.get(3));
                    tmp=this.tMapRek.findByKeys(keys);
                    if(tmp!=null)myData.set(3, tmp.getCells().get(1).getValueString());
                    
                    dupAdder=this.dupAdderD(myData.get(0), myData.get(3), myData.get(4));
                    this.kibD.add(
                            DBToolKibD.createKIB(
                                    myData.get(0), 
                                    myData.get(3), 
                                    myData.get(4), 
                                    myData.get(2), 
                                    myData.get(5), 
                                    myData.get(6), 
                                    myData.get(7), 
                                    myData.get(8), 
                                    myData.get(9), 
                                    myData.get(10), 
                                    myData.get(11), 
                                    myData.get(12), 
                                    myData.get(13), 
                                    myData.get(14), 
                                    myData.get(15), 
                                    myData.get(16), 
                                    myData.get(17),
                                    multiplyHarga,
                                    dupAdder,kodeLokNoZeroIndex
                            )
                    );
                    break;
                case "E":
                    //this.asets.add(new AsetDBTool(myData.get(0), intFromString(myData.get(1)), myData.get(2), myData.get(3), myData.get(4), myData.get(5), myData.get(6), myData.get(7), myData.get(8), myData.get(9), myData.get(10), myData.get(11), doubleFromString(myData.get(12)), intFromString(myData.get(13)), doubleFromString(myData.get(14)), myData.get(15)));
                    keys=new ArrayList();
                    keys.add(myData.get(0));
                    tmp=this.tMapLok.findByKeys(keys);
                    if(tmp!=null)myData.set(0, tmp.getCells().get(1).getValueString());
                    
                    keys=new ArrayList();
                    keys.add(myData.get(3));
                    tmp=this.tMapRek.findByKeys(keys);
                    if(tmp!=null)myData.set(3, tmp.getCells().get(1).getValueString());
                    
                    dupAdder=this.dupAdderE(myData.get(0), myData.get(3), myData.get(4));
                    this.kibE.add(
                            DBToolKibE.createKIB(
                                    myData.get(0), 
                                    myData.get(3), 
                                    myData.get(4), 
                                    myData.get(2), 
                                    myData.get(5), 
                                    myData.get(6), 
                                    myData.get(7), 
                                    myData.get(8), 
                                    myData.get(9), 
                                    myData.get(10), 
                                    myData.get(11), 
                                    myData.get(12), 
                                    myData.get(13), 
                                    myData.get(14), 
                                    myData.get(15),
                                    myData.get(16),
                                    multiplyHarga,
                                    dupAdder,kodeLokNoZeroIndex
                            )
                    );
                    break;
                case "F":
                    //this.asets.add(new AsetDBTool(myData.get(0), intFromString(myData.get(1)), myData.get(2), myData.get(3), booleanFromString(myData.get(4)), booleanFromString(myData.get(5)), doubleFromString(myData.get(6)), myData.get(7), dateFromString(myData.get(8)), myData.get(9), dateFromString(myData.get(10)), myData.get(11), myData.get(12), myData.get(13), doubleFromString(myData.get(14)), myData.get(15)));
                    keys=new ArrayList();
                    keys.add(myData.get(0));
                    tmp=this.tMapLok.findByKeys(keys);
                    if(tmp!=null)myData.set(0, tmp.getCells().get(1).getValueString());
                    
                    dupAdder=this.dupAdderF(myData.get(0), String.valueOf(JMFormatCollection.strToInteger(myData.get(1))), String.valueOf(JMFormatCollection.strToInteger(myData.get(1))));
                    this.kibF.add(
                            DBToolKibF.createKIB(
                                    myData.get(0), 
                                    String.valueOf(JMFormatCollection.strToInteger(myData.get(1))), 
                                    String.valueOf(JMFormatCollection.strToInteger(myData.get(1))), 
                                    myData.get(2), 
                                    myData.get(3), 
                                    myData.get(4), 
                                    myData.get(5), 
                                    myData.get(6), 
                                    myData.get(7), 
                                    myData.get(8), 
                                    myData.get(9), 
                                    myData.get(10), 
                                    myData.get(11), 
                                    myData.get(12), 
                                    myData.get(13), 
                                    myData.get(14), 
                                    myData.get(15),
                                    multiplyHarga,
                                    dupAdder,kodeLokNoZeroIndex
                            )
                    );
                    break;
                case "L":
                    //this.asets.add(new AsetDBTool(myData.get(0), intFromString(myData.get(1)), myData.get(2), myData.get(3), myData.get(4), intFromString(myData.get(5)), myData.get(6), myData.get(7), myData.get(8), myData.get(9), myData.get(10), doubleFromString(myData.get(11)), myData.get(12)));
                    keys=new ArrayList();
                    keys.add(myData.get(0));
                    tmp=this.tMapLok.findByKeys(keys);
                    if(tmp!=null)myData.set(0, tmp.getCells().get(1).getValueString());
                    
                    keys=new ArrayList();
                    keys.add(myData.get(3));
                    tmp=this.tMapRek.findByKeys(keys);
                    if(tmp!=null)myData.set(3, tmp.getCells().get(1).getValueString());
                    
                    dupAdder=this.dupAdderLainnya(myData.get(0), myData.get(3), myData.get(4));
                    this.kibLainnya.add(
                            DBToolKibLainnya.createKIB(
                                    myData.get(0), 
                                    myData.get(3), 
                                    myData.get(4), 
                                    myData.get(2), 
                                    myData.get(5), 
                                    myData.get(6), 
                                    myData.get(7), 
                                    myData.get(8), 
                                    myData.get(9), 
                                    myData.get(10), 
                                    myData.get(11), 
                                    myData.get(12),
                                    multiplyHarga,
                                    dupAdder,kodeLokNoZeroIndex
                            )
                    );
                    break;
                case "Inventaris":
                    //this.asets.add(new AsetDBTool(myData.get(0), intFromString(myData.get(1)), myData.get(2), myData.get(3), myData.get(4), intFromString(myData.get(5)), myData.get(6), myData.get(7), myData.get(8), myData.get(9), myData.get(10), doubleFromString(myData.get(11)), myData.get(12)));
                    keys=new ArrayList();
                    keys.add(myData.get(0));
                    tmp=this.tMapLok.findByKeys(keys);
                    if(tmp!=null)myData.set(0, tmp.getCells().get(1).getValueString());
                    
                    keys=new ArrayList();
                    keys.add(myData.get(2));
                    tmp=this.tMapRek.findByKeys(keys);
                    if(tmp!=null)myData.set(2, tmp.getCells().get(1).getValueString());
                    
                    dupAdder=this.dupAdderInventaris(myData.get(0), myData.get(2), myData.get(3));
                    this.inventaris.add(
                            DBToolInventaris.createKIB(
                                    myData.get(0), 
                                    myData.get(2), 
                                    myData.get(3), 
                                    myData.get(4), 
                                    myData.get(5), 
                                    myData.get(6), 
                                    myData.get(7), 
                                    myData.get(8), 
                                    myData.get(9), 
                                    myData.get(10), 
                                    myData.get(11), 
                                    myData.get(12), 
                                    myData.get(13), 
                                    myData.get(14), 
                                    myData.get(15),
                                    multiplyHarga,
                                    dupAdder,kodeLokNoZeroIndex
                            )
                    );
                    break;
            }
        }
    }
    
    private String validKodeRek(String kodeRek){
        String ret="";
        kodeRek=JMFunctions.removeSpaces(kodeRek);
        String[] arrKode=JMFormatCollection.strToArray(kodeRek, "[.]");
        if(arrKode.length==0)return kodeRek;
        for(String tmp:arrKode){
            if(!ret.equals(""))ret+=".";
            ret+=JMFormatCollection.strToInteger(tmp);
        }
        return ret;
    }
    
    private String dupAdderA(String lok,String brg,String reg){
        String ret="";
        lok=this.validKodeRek(lok);
        brg=this.validKodeRek(brg);
        reg=this.validKodeRek(reg);
        String tmp=lok+"."+brg+"."+reg;
        for(DBToolKibA a:this.kibA){
            if(a.getKey().equals(tmp)){
                ret="."+this.dup++;
                break;
            }
        }
        if(!ret.equals(""))JMFunctions.trace("A : "+tmp+ret);
        return ret;
    }
    
    private String dupAdderB(String lok,String brg,String reg){
        String ret="";
        lok=this.validKodeRek(lok);
        brg=this.validKodeRek(brg);
        reg=this.validKodeRek(reg);
        String tmp=lok+"."+brg+"."+reg;
        for(DBToolKibB a:this.kibB){
            if(a.getKey().equals(tmp)){
                ret="."+this.dup++;
                break;
            }
        }
        if(!ret.equals(""))JMFunctions.trace("B : "+tmp+ret);
        return ret;
    }
    
    private String dupAdderC(String lok,String brg,String reg){
        String ret="";
        lok=this.validKodeRek(lok);
        brg=this.validKodeRek(brg);
        reg=this.validKodeRek(reg);
        String tmp=lok+"."+brg+"."+reg;
        for(DBToolKibC a:this.kibC){
            if(a.getKey().equals(tmp)){
                ret="."+this.dup++;
                break;
            }
        }
        if(!ret.equals(""))JMFunctions.trace("C : "+tmp+ret);
        return ret;
    }
    
    private String dupAdderD(String lok,String brg,String reg){
        String ret="";
        lok=this.validKodeRek(lok);
        brg=this.validKodeRek(brg);
        reg=this.validKodeRek(reg);
        String tmp=lok+"."+brg+"."+reg;
        for(DBToolKibD a:this.kibD){
            if(a.getKey().equals(tmp)){
                ret="."+this.dup++;
                break;
            }
        }
        if(!ret.equals(""))JMFunctions.trace("D : "+tmp+ret);
        return ret;
    }
    
    private String dupAdderE(String lok,String brg,String reg){
        String ret="";
        lok=this.validKodeRek(lok);
        brg=this.validKodeRek(brg);
        reg=this.validKodeRek(reg);
        String tmp=lok+"."+brg+"."+reg;
        for(DBToolKibE a:this.kibE){
            if(a.getKey().equals(tmp)){
                ret="."+this.dup++;
                break;
            }
        }
        if(!ret.equals(""))JMFunctions.trace("E : "+tmp+ret);
        return ret;
    }
    
    private String dupAdderF(String lok,String brg,String reg){
        String ret="";
        lok=this.validKodeRek(lok);
        brg=this.validKodeRek(brg);
        reg=this.validKodeRek(reg);
        String tmp=lok+"."+brg+"."+reg;
        for(DBToolKibF a:this.kibF){
            if(a.getKey().equals(tmp)){
                ret="."+this.dup++;
                break;
            }
        }
        if(!ret.equals(""))JMFunctions.trace("F : "+tmp+ret);
        return ret;
    }
    
    private String dupAdderLainnya(String lok,String brg,String reg){
        String ret="";
        lok=this.validKodeRek(lok);
        brg=this.validKodeRek(brg);
        reg=this.validKodeRek(reg);
        String tmp=lok+"."+brg+"."+reg;
        for(DBToolKibLainnya a:this.kibLainnya){
            if(a.getKey().equals(tmp)){
                ret="."+this.dup++;
                break;
            }
        }
        if(!ret.equals(""))JMFunctions.trace("Lainnya : "+tmp+ret);
        return ret;
    }
    
    private String dupAdderInventaris(String lok,String brg,String reg){
        String ret="";
        lok=this.validKodeRek(lok);
        brg=this.validKodeRek(brg);
        reg=this.validKodeRek(reg);
        String tmp=lok+"."+brg+"."+reg;
        for(DBToolInventaris a:this.inventaris){
            if(a.getKey().equals(tmp)){
                ret="."+this.dup++;
                break;
            }
        }
        if(!ret.equals(""))JMFunctions.trace("Inventaris : "+tmp+ret);
        return ret;
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
}
