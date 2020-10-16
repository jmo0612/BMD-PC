/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BMDPC;

import com.thowo.jmjavaframework.JMDate;
import com.thowo.jmjavaframework.JMFormatCollection;
import com.thowo.jmjavaframework.JMFunctions;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jimi
 */
public class DBToolKibLainnya {
    private String queryHeader="tb_xls_lainnya(kd_kib_lainnya, kd_lokasi, kd_barang, kd_reg, jenis, tahun, judul, pencipta, "
                + "spesifikasi, kondisi, asal, harga, ket)";
    
    private String kd_kib_lainnya="";
    private String kd_lokasi="";
    private String kd_barang="";
    private String kd_reg="";
    private String jenis="";
    private Integer tahun=0;
    private String judul="";
    private String pencipta="";
    private String spesifikasi="";
    private String kondisi="";
    private String asal="";
    private Double harga=0.0;
    private String ket="";
    
    public static DBToolKibLainnya createKIB(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String tahun, String judul, String pencipta, String spesifikasi, String kondisi, String asal, String harga, String ket, Double multiplyHarga,String dupAdder){
        return new DBToolKibLainnya(kd_lokasi, kd_barang, kd_reg, jenis, tahun, judul, pencipta, spesifikasi, kondisi, asal, harga, ket, multiplyHarga,dupAdder);
    }
    public static DBToolKibLainnya createKIB(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String tahun, String judul, String pencipta, String spesifikasi, String kondisi, String asal, String harga, String ket,String dupAdder){
        return new DBToolKibLainnya(kd_lokasi, kd_barang, kd_reg, jenis, tahun, judul, pencipta, spesifikasi, kondisi, asal, harga, ket,1.0,dupAdder);
    }
    public DBToolKibLainnya(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String tahun, String judul, String pencipta, String spesifikasi, String kondisi, String asal, String harga, String ket, Double multiplyHarga,String dupAdder){
        String lok=this.validKodeRek(kd_lokasi);
        String brg=this.validKodeRek(kd_barang);
        String reg=this.validKodeRek(kd_reg);
        this.kd_kib_lainnya=lok+"."+brg+"."+reg+dupAdder;
        this.kd_lokasi=lok;
        this.kd_barang=brg;
        this.kd_reg=reg;
        this.jenis=jenis;
        this.tahun=JMFormatCollection.strToInteger(tahun);
        this.judul=judul;
        this.pencipta=pencipta;
        this.spesifikasi=spesifikasi;
        this.kondisi=kondisi;
        this.asal=asal;
        this.harga=JMFormatCollection.strToDouble(harga)*multiplyHarga;
        this.ket=ket;
    }
    public String getKey(){
        return this.kd_kib_lainnya;
    }
    
    public String getQueryHeader(){
        return this.queryHeader;
    }
    
    public String getQueryValues(){
        String ret="(";
        ret+="'"+this.kd_kib_lainnya+"',";
        ret+="'"+this.kd_lokasi+"',";
        ret+="'"+this.kd_barang+"',";
        ret+="'"+this.kd_reg+"',";
        ret+="'"+this.jenis+"',";
        ret+="'"+this.tahun+"',";
        ret+="'"+this.judul+"',";
        ret+="'"+this.pencipta+"',";
        ret+="'"+this.spesifikasi+"',";
        ret+="'"+this.kondisi+"',";
        ret+="'"+this.asal+"',";
        ret+="'"+this.harga+"',";
        ret+="'"+this.ket+"')";

        return ret;
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
}
