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

/**
 *
 * @author jimi
 */
public class DBToolKibF {
    private String queryHeader="tb_xls_f(kd_kib_f, kd_lokasi, kd_barang, kd_reg, jenis, bangunan_P_SP_D, bertingkat, beton, luas, letak, tgl_dok, no_dok, "
                + "tgl_mulai, status, no_kd_tanah, asal, nilai_kontrak, ket)";
    
    private String kd_kib_f="";
    private String kd_lokasi="";
    private String kd_barang="";
    private String kd_reg="";
    private String jenis="";
    private String bangunan_P_SP_D="";
    private String bertingkat="";
    private String beton="";
    private Double luas=0.0;
    private String letak="";
    private JMDate tgl_dok=null;
    private String no_dok="";
    private JMDate tgl_mulai=null;
    private String status="";
    private String no_kd_tanah="";
    private String asal="";
    private Double nilai_kontrak=0.0;
    private String ket="";
    
    public static DBToolKibF createKIB(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String bangunan_P_SP_D, String bertingkat, String beton, String luas, String letak, String tgl_dok, String no_dok, String tgl_mulai, String status, String no_kd_tanah, String asal, String nilai_kontrak, String ket, Double multiplyHarga,String dupAdder, List<Integer> kodeLokNoZeroIndices){
        return new DBToolKibF(kd_lokasi, kd_barang, kd_reg, jenis, bangunan_P_SP_D, bertingkat, beton, luas, letak, tgl_dok, no_dok, tgl_mulai, status, no_kd_tanah, asal, nilai_kontrak, ket, multiplyHarga,dupAdder,kodeLokNoZeroIndices);
    }
    public static DBToolKibF createKIB(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String bangunan_P_SP_D, String bertingkat, String beton, String luas, String letak, String tgl_dok, String no_dok, String tgl_mulai, String status, String no_kd_tanah, String asal, String nilai_kontrak, String ket,String dupAdder, List<Integer> kodeLokNoZeroIndices){
        return new DBToolKibF(kd_lokasi, kd_barang, kd_reg, jenis, bangunan_P_SP_D, bertingkat, beton, luas, letak, tgl_dok, no_dok, tgl_mulai, status, no_kd_tanah, asal, nilai_kontrak, ket,1.0,dupAdder,kodeLokNoZeroIndices);
    }
    public DBToolKibF(String kd_lokasi, String kd_barang, String kd_reg, String jenis, String bangunan_P_SP_D, String bertingkat, String beton, String luas, String letak, String tgl_dok, String no_dok, String tgl_mulai, String status, String no_kd_tanah, String asal, String nilai_kontrak, String ket, Double multiplyHarga,String dupAdder, List<Integer> kodeLokNoZeroIndices){
        String lok=DBToolInventaris.validKodeRek(kd_lokasi,kodeLokNoZeroIndices);
        String brg=DBToolInventaris.validKodeRek(kd_barang,null);
        String reg=DBToolInventaris.validKodeRek(kd_reg,null);
        this.kd_kib_f=lok+"."+brg+"."+reg+dupAdder;
        this.kd_lokasi=lok;
        this.kd_barang=brg;
        this.kd_reg=reg;
        this.jenis=jenis;
        this.bangunan_P_SP_D=bangunan_P_SP_D;
        this.bertingkat=bertingkat;
        this.beton=beton;
        this.luas=JMFormatCollection.strToDouble(luas);
        this.letak=letak;
        this.tgl_dok=JMFormatCollection.strSerialToJMDate(tgl_dok);
        this.no_dok=no_dok;
        this.tgl_mulai=JMFormatCollection.strSerialToJMDate(tgl_mulai);
        this.status=status;
        this.no_kd_tanah=no_kd_tanah;
        this.asal=asal;
        this.nilai_kontrak=JMFormatCollection.strToDouble(nilai_kontrak)*multiplyHarga;
        this.ket=ket;
    }
    public String getKey(){
        return this.kd_kib_f;
    }
    
    public String getQueryHeader(){
        return this.queryHeader;
    }
    
    public String getQueryValues(){
        String ret="(";
        ret+="'"+this.kd_kib_f+"',";
        ret+="'"+this.kd_lokasi+"',";
        ret+="'"+this.kd_barang+"',";
        ret+="'"+this.kd_reg+"',";
        ret+="'"+this.jenis+"',";
        ret+="'"+this.bangunan_P_SP_D+"',";
        ret+="'"+this.bertingkat+"',";
        ret+="'"+this.beton+"',";
        ret+="'"+this.luas+"',";
        ret+="'"+this.letak+"',";
        ret+=JMFunctions.validDBValue(this.tgl_dok.dateDB(),"NULL")+",";
        ret+="'"+this.no_dok+"',";
        ret+=JMFunctions.validDBValue(this.tgl_mulai.dateDB(),"NULL")+",";
        ret+="'"+this.status+"',";
        ret+="'"+this.no_kd_tanah+"',";
        ret+="'"+this.asal+"',";
        ret+="'"+this.nilai_kontrak+"',";
        ret+="'"+this.ket+"')";

        return ret;
    }
    
}
