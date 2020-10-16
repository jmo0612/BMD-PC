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
public class DBToolInventaris {
    private String queryHeader="tb_xls_inventaris(kd_inventaris, kd_lokasi, kd_barang, kd_reg, jenis, merk, no_SPCM, bahan, asal, tahun, "
                + "ukuran, satuan, keadaan, jumlah, harga, ket)";
    
    private String kd_inventaris="";
    private String kd_lokasi="";
    private String kd_barang="";
    private String kd_reg="";
    private String jenis="";
    private String merk="";
    private String no_SPCM="";
    private String bahan="";
    private String asal="";
    private Integer tahun=0;
    private String ukuran="";
    private String satuan="";
    private String keadaan="";
    private Integer jumlah=0;
    private Double harga=0.0;
    private String ket="";
    
    public static DBToolInventaris createKIB(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String merk, String no_SPCM, String bahan, String asal, String tahun, String ukuran, String satuan, String keadaan, String jumlah, String harga, String ket, Double multiplyHarga,String dupAdder){
        return new DBToolInventaris(kd_lokasi, kd_barang, kd_reg, jenis, merk, no_SPCM, bahan, asal, tahun, ukuran, satuan, keadaan, jumlah, harga, ket, multiplyHarga,dupAdder);
    }
    public static DBToolInventaris createKIB(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String merk, String no_SPCM, String bahan, String asal, String tahun, String ukuran, String satuan, String keadaan, String jumlah, String harga, String ket,String dupAdder){
        return new DBToolInventaris(kd_lokasi, kd_barang, kd_reg, jenis, merk, no_SPCM, bahan, asal, tahun, ukuran, satuan, keadaan, jumlah, harga, ket,1.0,dupAdder);
    }
    public DBToolInventaris(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String merk, String no_SPCM, String bahan, String asal, String tahun, String ukuran, String satuan, String keadaan, String jumlah, String harga, String ket, Double multiplyHarga,String dupAdder){
        String lok=this.validKodeRek(kd_lokasi);
        String brg=this.validKodeRek(kd_barang);
        String reg=this.validKodeRek(kd_reg);
        this.kd_inventaris=lok+"."+brg+"."+reg+dupAdder;
        this.kd_lokasi=lok;
        this.kd_barang=brg;
        this.kd_reg=reg;
        this.jenis=jenis;
        this.merk=merk;
        this.no_SPCM=no_SPCM;
        this.bahan=bahan;
        this.asal=asal;
        this.tahun=JMFormatCollection.strToInteger(tahun);
        this.ukuran=ukuran;
        this.satuan=satuan;
        this.keadaan=keadaan;
        this.jumlah=JMFormatCollection.strToInteger(jumlah);
        this.harga=JMFormatCollection.strToDouble(harga)*multiplyHarga;
        this.ket=ket;
    }
    public String getKey(){
        return this.kd_inventaris;
    }
    
    public String getQueryHeader(){
        return this.queryHeader;
    }
    
    public String getQueryValues(){
        String ret="(";
        ret+="'"+this.kd_inventaris+"',";
        ret+="'"+this.kd_lokasi+"',";
        ret+="'"+this.kd_barang+"',";
        ret+="'"+this.kd_reg+"',";
        ret+="'"+this.jenis+"',";
        ret+="'"+this.merk+"',";
        ret+="'"+this.no_SPCM+"',";
        ret+="'"+this.bahan+"',";
        ret+="'"+this.asal+"',";
        ret+="'"+this.tahun+"',";
        ret+="'"+this.ukuran+"',";
        ret+="'"+this.satuan+"',";
        ret+="'"+this.keadaan+"',";
        ret+="'"+this.jumlah+"',";
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
