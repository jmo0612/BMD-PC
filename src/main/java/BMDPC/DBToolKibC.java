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
public class DBToolKibC {
    private String queryHeader="tb_xls_c(kd_kib_c, kd_lokasi, kd_barang, kd_reg, jenis, kondisi, bertingkat, beton, "
            + "luas_lantai, letak, tgl_dok, no_dok, luas, status, no_kd_tanah, asal, harga, ket)";
    
    private String kd_kib_c="";
    private String kd_lokasi="";
    private String kd_barang="";
    private String kd_reg="";
    private String jenis="";
    private String kondisi="";
    private String bertingkat="";
    private String beton="";
    private Double luas_lantai=0.0;
    private String letak="";
    private JMDate tgl_dok=null;
    private String no_dok="";
    private Double luas=0.0;
    private String status="";
    private String no_kd_tanah="";
    private String asal="";
    private Double harga=0.0;
    private String ket="";
    
    public static DBToolKibC createKIB(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String kondisi, String bertingkat, String beton, String luas_lantai, String letak, String tgl_dok, String no_dok, String luas, String status, String no_kd_tanah, String asal, String harga, String ket, Double multiplyHarga,String dupAdder){
        return new DBToolKibC(kd_lokasi, kd_barang, kd_reg, jenis, kondisi, bertingkat, beton, luas_lantai, letak, tgl_dok, no_dok, luas, status, no_kd_tanah, asal, harga, ket, multiplyHarga,dupAdder);
    }
    public static DBToolKibC createKIB(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String kondisi, String bertingkat, String beton, String luas_lantai, String letak, String tgl_dok, String no_dok, String luas, String status, String no_kd_tanah, String asal, String harga, String ket,String dupAdder){
        return new DBToolKibC(kd_lokasi, kd_barang, kd_reg, jenis, kondisi, bertingkat, beton, luas_lantai, letak, tgl_dok, no_dok, luas, status, no_kd_tanah, asal, harga, ket,1.0,dupAdder);
    }
    public DBToolKibC(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String kondisi, String bertingkat, String beton, String luas_lantai, String letak, String tgl_dok, String no_dok, String luas, String status, String no_kd_tanah, String asal, String harga, String ket, Double multiplyHarga,String dupAdder){
        String lok=this.validKodeRek(kd_lokasi);
        String brg=this.validKodeRek(kd_barang);
        String reg=this.validKodeRek(kd_reg);
        this.kd_kib_c=lok+"."+brg+"."+reg+dupAdder;
        this.kd_lokasi=lok;
        this.kd_barang=brg;
        this.kd_reg=reg;
        this.jenis=jenis;
        this.kondisi=kondisi;
        this.bertingkat=bertingkat;
        this.beton=beton;
        this.luas_lantai=JMFormatCollection.strToDouble(luas_lantai);
        this.letak=letak;
        this.tgl_dok=JMFormatCollection.strSerialToJMDate(tgl_dok);
        this.no_dok=no_dok;
        this.luas=JMFormatCollection.strToDouble(luas);
        this.status=status;
        this.no_kd_tanah=no_kd_tanah;
        this.asal=asal;
        this.harga=JMFormatCollection.strToDouble(harga)*multiplyHarga;
        this.ket=ket;
    }
    public String getKey(){
        return this.kd_kib_c;
    }
    
    public String getQueryHeader(){
        return this.queryHeader;
    }
    
    public String getQueryValues(){
        String ret="(";
        ret+="'"+this.kd_kib_c+"',";
        ret+="'"+this.kd_lokasi+"',";
        ret+="'"+this.kd_barang+"',";
        ret+="'"+this.kd_reg+"',";
        ret+="'"+this.jenis+"',";
        ret+="'"+this.kondisi+"',";
        ret+="'"+this.bertingkat+"',";
        ret+="'"+this.beton+"',";
        ret+="'"+this.luas_lantai+"',";
        ret+="'"+this.letak+"',";
        ret+=JMFunctions.validDBValue(this.tgl_dok.dateDB(),"NULL")+",";
        ret+="'"+this.no_dok+"',";
        ret+="'"+this.luas+"',";
        ret+="'"+this.status+"',";
        ret+="'"+this.no_kd_tanah+"',";
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
