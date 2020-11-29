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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jimi
 */
public class DBToolKibB {
    private String queryHeader="tb_xls_b(kd_kib_b, kd_lokasi, kd_barang, kd_reg, jenis, merk, ukuran, bahan, tahun, no_pabrik, no_rangka, "
                + "no_mesin, no_polisi, no_BPKB, asal, harga, ket)";
    
    private String kd_kib_b="";
    private String kd_lokasi="";
    private String kd_barang="";
    private String kd_reg="";
    private String jenis="";
    private String merk;
    private String ukuran;
    private String bahan;
    private Integer tahun;
    private String no_pabrik;
    private String no_rangka;
    private String no_mesin;
    private String no_polisi;
    private String no_BPKB;
    private String asal;
    private Double harga;
    private String ket;
    
    public static DBToolKibB createKIB(String kd_lokasi,String kd_barang,String kd_reg,String jenis,String merk,String ukuran,String bahan,String tahun,String no_pabrik,String no_rangka,String no_mesin,String no_polisi,String no_BPKB,String asal,String harga,String ket, Double multiplyHarga,String dupAdder, List<Integer> kodeLokNoZeroIndices){
        return new DBToolKibB(kd_lokasi, kd_barang, kd_reg, jenis, merk, ukuran, bahan, tahun, no_pabrik, no_rangka, no_mesin, no_polisi, no_BPKB, asal, harga, ket, multiplyHarga,dupAdder,kodeLokNoZeroIndices);
    }
    public static DBToolKibB createKIB(String kd_lokasi,String kd_barang,String kd_reg,String jenis,String merk,String ukuran,String bahan,String tahun,String no_pabrik,String no_rangka,String no_mesin,String no_polisi,String no_BPKB,String asal,String harga,String ket,String dupAdder, List<Integer> kodeLokNoZeroIndices){
        return new DBToolKibB(kd_lokasi, kd_barang, kd_reg, jenis, merk, ukuran, bahan, tahun, no_pabrik, no_rangka, no_mesin, no_polisi, no_BPKB, asal, harga, ket,1.0,dupAdder,kodeLokNoZeroIndices);
    }
    public DBToolKibB(String kd_lokasi,String kd_barang,String kd_reg,String jenis,String merk,String ukuran,String bahan,String tahun,String no_pabrik,String no_rangka,String no_mesin,String no_polisi,String no_BPKB,String asal,String harga,String ket, Double multiplyHarga,String dupAdder, List<Integer> kodeLokNoZeroIndices){
        String lok=DBToolInventaris.validKodeRek(kd_lokasi,kodeLokNoZeroIndices);
        String brg=DBToolInventaris.validKodeRek(kd_barang,null);
        String reg=DBToolInventaris.validKodeRek(kd_reg,null);
        this.kd_kib_b=lok+"."+brg+"."+reg+dupAdder;
        this.kd_lokasi=lok;
        this.kd_barang=brg;
        this.kd_reg=reg;
        this.jenis=jenis;
        this.merk=merk;
        this.ukuran=ukuran;
        this.bahan=bahan;
        this.tahun=JMFormatCollection.strToInteger(tahun);
        this.no_pabrik=no_pabrik;
        this.no_rangka=no_rangka;
        this.no_mesin=no_mesin;
        this.no_polisi=no_polisi;
        this.no_BPKB=no_BPKB;
        this.asal=asal;
        this.harga=JMFormatCollection.strToDouble(harga)*multiplyHarga;
        this.ket=ket;
    }
    public String getKey(){
        return this.kd_kib_b;
    }
    
    public String getQueryHeader(){
        return this.queryHeader;
    }
    
    public String getQueryValues(){
        String ret="(";
        ret+="'"+this.kd_kib_b+"',";
        ret+="'"+this.kd_lokasi+"',";
        ret+="'"+this.kd_barang+"',";
        ret+="'"+this.kd_reg+"',";
        ret+="'"+this.jenis+"',";
        ret+="'"+this.merk+"',";
        ret+="'"+this.ukuran+"',";
        ret+="'"+this.bahan+"',";
        ret+="'"+this.tahun+"',";
        ret+="'"+this.no_pabrik+"',";
        ret+="'"+this.no_rangka+"',";
        ret+="'"+this.no_mesin+"',";
        ret+="'"+this.no_polisi+"',";
        ret+="'"+this.no_BPKB+"',";
        ret+="'"+this.asal+"',";
        ret+="'"+this.harga+"',";
        ret+="'"+this.ket+"')";

        return ret;
    }
}
