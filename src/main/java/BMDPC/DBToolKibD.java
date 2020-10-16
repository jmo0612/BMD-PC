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
public class DBToolKibD {
    private String queryHeader="tb_xls_d(kd_kib_d, kd_lokasi, kd_barang, kd_reg, jenis, konstruksi, panjang, lebar, luas, letak, "
                + "tgl_dok, no_dok, status, no_kd_tanah, asal, harga, kondisi, ket)";
    
    private String kd_kib_d="";
    private String kd_lokasi="";
    private String kd_barang="";
    private String kd_reg="";
    private String jenis="";
    private String konstruksi="";
    private Double panjang=0.0;
    private Double lebar=0.0;
    private Double luas=0.0;
    private String letak="";
    private JMDate tgl_dok=null;
    private String no_dok="";
    private String status="";
    private String no_kd_tanah="";
    private String asal="";
    private Double harga=0.0;
    private String kondisi="";
    private String ket="";
    
    public static DBToolKibD createKIB(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String konstruksi, String panjang, String lebar, String luas, String letak, String tgl_dok, String no_dok, String status, String no_kd_tanah, String asal, String harga, String kondisi, String ket, Double multiplyHarga,String dupAdder){
        return new DBToolKibD(kd_lokasi, kd_barang, kd_reg, jenis, konstruksi, panjang, lebar, luas, letak, tgl_dok, no_dok, status, no_kd_tanah, asal, harga, kondisi, ket, multiplyHarga,dupAdder);
    }
    public static DBToolKibD createKIB(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String konstruksi, String panjang, String lebar, String luas, String letak, String tgl_dok, String no_dok, String status, String no_kd_tanah, String asal, String harga, String kondisi, String ket,String dupAdder){
        return new DBToolKibD(kd_lokasi, kd_barang, kd_reg, jenis, konstruksi, panjang, lebar, luas, letak, tgl_dok, no_dok, status, no_kd_tanah, asal, harga, kondisi, ket,1.0,dupAdder);
    }
    public DBToolKibD(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String konstruksi, String panjang, String lebar, String luas, String letak, String tgl_dok, String no_dok, String status, String no_kd_tanah, String asal, String harga, String kondisi, String ket, Double multiplyHarga,String dupAdder){
        String lok=this.validKodeRek(kd_lokasi);
        String brg=this.validKodeRek(kd_barang);
        String reg=this.validKodeRek(kd_reg);
        this.kd_kib_d=lok+"."+brg+"."+reg+dupAdder;
        this.kd_lokasi=lok;
        this.kd_barang=brg;
        this.kd_reg=reg;
        this.jenis=jenis;
        this.konstruksi=konstruksi;
        this.panjang=JMFormatCollection.strToDouble(panjang);
        this.lebar=JMFormatCollection.strToDouble(lebar);
        this.luas=JMFormatCollection.strToDouble(luas);
        this.letak=letak;
        this.tgl_dok=JMFormatCollection.strSerialToJMDate(tgl_dok);
        this.no_dok=no_dok;
        this.status=status;
        this.no_kd_tanah=no_kd_tanah;
        this.asal=asal;
        this.harga=JMFormatCollection.strToDouble(harga)*multiplyHarga;
        this.kondisi=kondisi;
        this.ket=ket;
    }
    public String getKey(){
        return this.kd_kib_d;
    }
    
    public String getQueryHeader(){
        return this.queryHeader;
    }
    
    public String getQueryValues(){
        String ret="(";
        ret+="'"+this.kd_kib_d+"',";
        ret+="'"+this.kd_lokasi+"',";
        ret+="'"+this.kd_barang+"',";
        ret+="'"+this.kd_reg+"',";
        ret+="'"+this.jenis+"',";
        ret+="'"+this.konstruksi+"',";
        ret+="'"+this.panjang+"',";
        ret+="'"+this.lebar+"',";
        ret+="'"+this.luas+"',";
        ret+="'"+this.letak+"',";
        ret+=JMFunctions.validDBValue(this.tgl_dok.dateDB(),"NULL")+",";
        ret+="'"+this.no_dok+"',";
        ret+="'"+this.status+"',";
        ret+="'"+this.no_kd_tanah+"',";
        ret+="'"+this.asal+"',";
        ret+="'"+this.harga+"',";
        ret+="'"+this.kondisi+"',";
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
