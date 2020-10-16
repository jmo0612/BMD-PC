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
public class DBToolKibE {
    private String queryHeader="tb_xls_e(kd_kib_e, kd_lokasi, kd_barang, kd_reg, jenis, judul_pencipta_buku, spesifikasi_buku, "
                + "asal_daerah_seni, pencipta_seni, bahan_seni, jenis_HTT, ukuran, jumlah, asal, tahun, harga, ket)";
    
    private String kd_kib_e="";
    private String kd_lokasi="";
    private String kd_barang="";
    private String kd_reg="";
    private String jenis="";
    private String judul_pencipta_buku="";
    private String spesifikasi_buku="";
    private String asal_daerah_seni="";
    private String pencipta_seni="";
    private String bahan_seni="";
    private String jenis_HTT="";
    private String ukuran="";
    private Double jumlah=0.0;
    private String asal="";
    private Integer tahun=0;
    private Double harga=0.0;
    private String ket="";
    
    public static DBToolKibE createKIB(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String judul_pencipta_buku, String spesifikasi_buku, String asal_daerah_seni, String pencipta_seni, String bahan_seni, String jenis_HTT, String ukuran, String jumlah, String asal, String tahun, String harga, String ket, Double multiplyHarga,String dupAdder){
        return new DBToolKibE(kd_lokasi, kd_barang, kd_reg, jenis, judul_pencipta_buku, spesifikasi_buku, asal_daerah_seni, pencipta_seni, bahan_seni, jenis_HTT, ukuran, jumlah, asal, tahun, harga, ket, multiplyHarga,dupAdder);
    }
    public static DBToolKibE createKIB(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String judul_pencipta_buku, String spesifikasi_buku, String asal_daerah_seni, String pencipta_seni, String bahan_seni, String jenis_HTT, String ukuran, String jumlah, String asal, String tahun, String harga, String ket,String dupAdder){
        return new DBToolKibE(kd_lokasi, kd_barang, kd_reg, jenis, judul_pencipta_buku, spesifikasi_buku, asal_daerah_seni, pencipta_seni, bahan_seni, jenis_HTT, ukuran, jumlah, asal, tahun, harga, ket,1.0,dupAdder);
    }
    public DBToolKibE(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String judul_pencipta_buku, String spesifikasi_buku, String asal_daerah_seni, String pencipta_seni, String bahan_seni, String jenis_HTT, String ukuran, String jumlah, String asal, String tahun, String harga, String ket, Double multiplyHarga,String dupAdder){
        String lok=this.validKodeRek(kd_lokasi);
        String brg=this.validKodeRek(kd_barang);
        String reg=this.validKodeRek(kd_reg);
        this.kd_kib_e=lok+"."+brg+"."+reg+dupAdder;
        this.kd_lokasi=lok;
        this.kd_barang=brg;
        this.kd_reg=reg;
        this.jenis=jenis;
        this.judul_pencipta_buku=judul_pencipta_buku;
        this.spesifikasi_buku=spesifikasi_buku;
        this.asal_daerah_seni=asal_daerah_seni;
        this.pencipta_seni=pencipta_seni;
        this.bahan_seni=bahan_seni;
        this.jenis_HTT=jenis_HTT;
        this.ukuran=ukuran;
        this.jumlah=JMFormatCollection.strToDouble(jumlah);
        this.asal=asal;
        this.tahun=JMFormatCollection.strToInteger(tahun);
        this.harga=JMFormatCollection.strToDouble(harga)*multiplyHarga;
        this.ket=ket;
    }
    public String getKey(){
        return this.kd_kib_e;
    }
    
    public String getQueryHeader(){
        return this.queryHeader;
    }
    
    public String getQueryValues(){
        String ret="(";
        ret+="'"+this.kd_kib_e+"',";
        ret+="'"+this.kd_lokasi+"',";
        ret+="'"+this.kd_barang+"',";
        ret+="'"+this.kd_reg+"',";
        ret+="'"+this.jenis+"',";
        ret+="'"+this.judul_pencipta_buku+"',";
        ret+="'"+this.spesifikasi_buku+"',";
        ret+="'"+this.asal_daerah_seni+"',";
        ret+="'"+this.pencipta_seni+"',";
        ret+="'"+this.bahan_seni+"',";
        ret+="'"+this.jenis_HTT+"',";
        ret+="'"+this.ukuran+"',";
        ret+="'"+this.jumlah+"',";
        ret+="'"+this.asal+"',";
        ret+="'"+this.tahun+"',";
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
