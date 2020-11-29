/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BMDPC;

import com.thowo.jmjavaframework.JMDate;
import com.thowo.jmjavaframework.JMDateBuilder;
import com.thowo.jmjavaframework.JMFormatCollection;
import static com.thowo.jmjavaframework.JMFormatCollection.JMO_DATE_STANDARD;
import static com.thowo.jmjavaframework.JMFormatCollection.strFormat;
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
public class DBToolKibA {

    private String queryHeader="tb_xls_a(kd_kib_a, kd_lokasi, kd_barang, kd_reg, jenis, luas, tahun, letak, hak, tgl_sertifikat, no_sertifikat, "
                + "penggunaan, asal, harga, ket)";
    
    private String kd_kib_a="";
    private String kd_lokasi="";
    private String kd_barang="";
    private String kd_reg="";
    private String jenis="";
    private Double luas=0.0;
    private Integer tahun=0;
    private String letak="";
    private String hak="";
    private JMDate tgl_sertifikat=null;
    private String no_sertifikat="";
    private String penggunaan="";
    private String asal="";
    private Double harga=0.0;
    private String ket="";
    
    public static DBToolKibA createKIB(String kd_lokasi,String kd_barang,String kd_reg,String jenis,String luas,String tahun,String letak,String hak,String tgl_sertifikat,String no_sertifikat,String penggunaan,String asal,String harga,String ket, Double multiplyHarga,String dupAdder, List<Integer> kodeLokNoZeroIndices){
        return new DBToolKibA(kd_lokasi,kd_barang,kd_reg,jenis,luas,tahun,letak,hak,tgl_sertifikat,no_sertifikat,penggunaan,asal,harga,ket,multiplyHarga,dupAdder,kodeLokNoZeroIndices);
    }
    public static DBToolKibA createKIB(String kd_lokasi,String kd_barang,String kd_reg,String jenis,String luas,String tahun,String letak,String hak,String tgl_sertifikat,String no_sertifikat,String penggunaan,String asal,String harga,String ket,String dupAdder, List<Integer> kodeLokNoZeroIndices){
        return new DBToolKibA(kd_lokasi,kd_barang,kd_reg,jenis,luas,tahun,letak,hak,tgl_sertifikat,no_sertifikat,penggunaan,asal,harga,ket,1.0,dupAdder,kodeLokNoZeroIndices);
    }
    public DBToolKibA(String kd_lokasi,String kd_barang,String kd_reg,String jenis,String luas,String tahun,String letak,String hak,String tgl_sertifikat,String no_sertifikat,String penggunaan,String asal,String harga,String ket, Double multiplyHarga,String dupAdder, List<Integer> kodeLokNoZeroIndices){
        String lok=DBToolInventaris.validKodeRek(kd_lokasi,kodeLokNoZeroIndices);
        String brg=DBToolInventaris.validKodeRek(kd_barang,null);
        String reg=DBToolInventaris.validKodeRek(kd_reg,null);
        this.kd_kib_a=lok+"."+brg+"."+reg+dupAdder;
        this.kd_lokasi=lok;
        this.kd_barang=brg;
        this.kd_reg=reg;
        this.jenis=jenis;
        this.luas=JMFormatCollection.strToDouble(luas);
        this.tahun=JMFormatCollection.strToInteger(tahun);
        this.letak=letak;
        this.hak=hak;
        this.tgl_sertifikat=JMFormatCollection.strSerialToJMDate(tgl_sertifikat);
        this.no_sertifikat=no_sertifikat;
        
        this.penggunaan=penggunaan;
        this.asal=asal;
        this.harga=JMFormatCollection.strToDouble(harga)*multiplyHarga;
        this.ket=ket;
    }
    
    public String getKey(){
        return this.kd_kib_a;
    }
    
    public String getQueryHeader(){
        return this.queryHeader;
    }
    
    public String getQueryValues(){
        String ret="(";
        ret+="'"+this.kd_kib_a+"',";
        ret+="'"+this.kd_lokasi+"',";
        ret+="'"+this.kd_barang+"',";
        ret+="'"+this.kd_reg+"',";
        ret+="'"+this.jenis+"',";
        ret+="'"+this.luas+"',";
        ret+="'"+this.tahun+"',";
        ret+="'"+this.letak+"',";
        ret+="'"+this.hak+"',";
        ret+=JMFunctions.validDBValue(this.tgl_sertifikat.dateDB(),"NULL")+",";
        ret+="'"+this.no_sertifikat+"',";
        ret+="'"+this.penggunaan+"',";
        ret+="'"+this.asal+"',";
        ret+="'"+this.harga+"',";
        ret+="'"+this.ket+"')";

        return ret;
    }
    
    
}
