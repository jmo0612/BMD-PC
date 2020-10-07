/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BMDPC;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */


public class AsetDBTool {
    private final String id_CONST="id";
    private final String jenis_CONST="jenis";
    private final String luasM2_CONST="luas_m2";
    private final String tahun_CONST="tahun";
    private final String alamat_CONST="alamat";
    private final String tglDok_CONST="tgl_dok";
    private final String noDok_CONST="no_dok";
    private final String penggunaan_CONST="penggunaan";
    private final String asal_CONST="asal";
    private final String hargaRibu_CONST="harga_ribu";
    private final String ket_CONST="ket";
    private final String kib_CONST="kib";
    private final String hak_CONST="hak";
    private final String merkType_CONST="merk_type";
    private final String ukuranCC_CONST="ukuran_cc";
    private final String bahan_CONST="bahan";
    private final String noPabrik_CONST="no_pabrik";
    private final String noRangka_CONST="no_rangka";
    private final String noMesin_CONST="no_mesin";
    private final String noPolisi_CONST="no_polisi";
    private final String noBPKB_CONST="no_bpkb";
    private final String kondisi_CONST="kondisi";
    private final String bertingkat_CONST="bertingkat";
    private final String beton_CONST="beton";
    private final String luasLantaiM2_CONST="luas_lantai_m2";
    private final String statusTanah_CONST="status_tanah";
    private final String noKodeTanah_CONST="no_kode_tanah";
    private final String konstruksi_CONST="konstruksi";
    private final String panjangKM_CONST="panjang_km";
    private final String lebarM_CONST="lebar_m";
    private final String judul_CONST="judul";
    private final String spesifikasi_CONST="spesifikasi";
    private final String asalDaerah_CONST="asal_daerah";
    private final String pencipta_CONST="pencipta";
    private final String jenisHewan_CONST="jenis_hewan";
    private final String ukuranHewan_CONST="ukuran_hewan";
    private final String jumlah_CONST="jumlah";
    private final String tahunCetak_CONST="tahun_cetak";
    private final String bangunanP_SP_D_CONST="bangunan_P_SP_D";
    private final String tglMulai_CONST="tgl_mulai";
    private final String judulNama_CONST="judul_nama";

    
    private String id="";
    private String kib="";
    
    private String kodeLok="";
    private Integer no=0;
    private String jenis="";
    private String kode="";
    private String reg="";
    private Double luasM2=0.0;
    private Integer tahun=0;
    private String alamat="";
    private Date tglDok=null;
    private String noDok="";
    private String penggunaan="";
    private String asal="";
    private Double hargaRibu=0.0;
    private String ket="";
    
    //A
    private String hak="";
    
    //B
    private String merkType="";
    private String ukuranCC="";
    private String bahan="";
    private String noPabrik="";
    private String noRangka="";
    private String noMesin="";
    private String noPolisi="";
    private String noBPKB="";
    
    //C
    private String kondisi="";
    private boolean bertingkat=false;
    private boolean beton=false;
    private Double luasLantaiM2=0.0;
    private String statusTanah="";
    private String noKodeTanah="";
    
    //D
    private String konstruksi="";
    private Double panjangKM=0.0;
    private Double lebarM=0.0;
    
    //E
    private String judul="";
    private String spesifikasi="";
    private String asalDaerah="";
    private String pencipta="";
    private String jenisHewan="";
    private String ukuranHewan="";
    private Double jumlah=0.0;
    private Integer tahunCetak=0;
    
    //F
    private String bangunanP_SP_D="";
    private Date tglMulai=null;
    
    //L
    private String judulNama="";
    
    public AsetDBTool(String kodeLok, Integer no, String jenis, String kode, String reg, Double luasM2, Integer tahun, String alamat, String hak, Date tglDok, String noDok, String penggunaan, String asal, Double hargaRibu, String ket){
        this.kib="A";
        this.kodeLok=kodeLok;
        this.no=no;
        this.jenis=jenis;
        this.kode=kode;
        this.reg=reg;
        this.luasM2=luasM2;
        this.tahun=tahun;
        this.alamat=alamat;
        this.hak=hak;
        this.tglDok=tglDok;
        this.noDok=noDok;
        this.penggunaan=penggunaan;
        this.asal=asal;
        this.hargaRibu= hargaRibu;
        this.ket=ket;
        this.makeId();
        
    }
    
    public AsetDBTool(String kodeLok, Integer no, String kode, String jenis, String reg, String merkType, String ukuranCC, String bahan, Integer tahun, String noPabrik, String noRangka, String noMesin, String noPolisi, String noBPKB, String asal, Double hargaRibu, String ket){
        this.kib="B";
        
        this.kodeLok=kodeLok;
        this.no=no;
        this.kode=kode;
        this.jenis=jenis;
        this.reg=reg;
        this.merkType=merkType;
        this.ukuranCC=ukuranCC;
        this.bahan=bahan;
        this.tahun=tahun;
        this.noPabrik=noPabrik;
        this.noRangka=noRangka;
        this.noMesin=noMesin;
        this.noPolisi=noPolisi;
        this.noBPKB=noBPKB;
        this.asal=asal;
        this.hargaRibu=hargaRibu;
        this.ket=ket;
        this.makeId();
    }
    
    public AsetDBTool(String kodeLok, Integer no, String jenis, String kode, String reg, String kondisi, boolean bertingkat, boolean beton, Double luasLantaiM2, String alamat, Date tglDok, String noDok, Double luasM2, String statusTanah, String noKodeTanah, String asal, Double hargaRibu, String ket){
        this.kib="C";
        this.kodeLok=kodeLok;
        this.no=no;
        this.jenis=jenis;
        this.kode=kode;
        this.reg=reg;
        this.kondisi=kondisi;
        this.bertingkat=bertingkat;
        this.beton=beton;
        this.luasLantaiM2=luasLantaiM2;
        this.alamat=alamat;
        this.tglDok=tglDok;
        this.noDok=noDok;
        this.luasM2=luasM2;
        this.statusTanah=statusTanah;
        this.noKodeTanah=noKodeTanah;
        this.asal=asal;
        this.hargaRibu=hargaRibu;
        this.ket=ket;
        this.makeId();
    }
    
    public AsetDBTool(String kodeLok, Integer no, String jenis, String kode, String reg, String konstruksi, Double panjangKM, Double lebarM, Double luasM2, String alamat, Date tglDok, String noDok, String statusTanah, String noKodeTanah, String asal, Double hargaRibu, String kondisi, String ket){
        this.kib="D";
        this.kodeLok=kodeLok;
        this.no=no;
        this.jenis=jenis;
        this.kode=kode;
        this.reg=reg;
        this.konstruksi=konstruksi;
        this.panjangKM=panjangKM;
        this.lebarM=lebarM;
        this.luasM2=luasM2;
        this.alamat=alamat;
        this.tglDok=tglDok;
        this.noDok=noDok;
        this.statusTanah=statusTanah;
        this.noKodeTanah=noKodeTanah;
        this.asal=asal;
        this.hargaRibu=hargaRibu;
        this.kondisi=kondisi;
        this.ket=ket;
        this.makeId();
    }
    
    public AsetDBTool(String kodeLok, Integer no, String jenis, String kode, String reg, String judul, String spesifikasi, String asalDaerah, String pencipta, String bahan, String jenisHewan, String ukuranHewan, Double jumlah, Integer tahunCetak, Double hargaRibu, String ket){
        this.kib="E";
        this.kodeLok=kodeLok;
        this.no=no;
        this.jenis=jenis;
        this.kode=kode;
        this.reg=reg;
        this.judul=judul;
        this.spesifikasi=spesifikasi;
        this.asalDaerah=asalDaerah;
        this.pencipta=pencipta;
        this.bahan=bahan;
        this.jenisHewan=jenisHewan;
        this.ukuranHewan=ukuranHewan;
        this.jumlah=jumlah;
        this.tahunCetak=tahunCetak;
        this.hargaRibu=hargaRibu;
        this.ket=ket;
        this.makeId();
    }
    
    public AsetDBTool(String kodeLok, Integer no, String jenis, String bangunanP_SP_D, boolean bertingkat, boolean beton, Double luasM2, String alamat, Date tglDok, String noDok, Date tglMulai, String statusTanah, String noKodeTanah, String asal, Double hargaRibu, String ket){
        this.kib="F";
        this.kodeLok=kodeLok;
        this.no=no;
        this.jenis=jenis;
        this.bangunanP_SP_D=bangunanP_SP_D;
        this.bertingkat=bertingkat;
        this.beton=beton;
        this.luasM2=luasM2;
        this.alamat=alamat;
        this.tglDok=tglDok;
        this.noDok=noDok;
        this.tglMulai=tglMulai;
        this.statusTanah=statusTanah;
        this.noKodeTanah=noKodeTanah;
        this.asal=asal;
        this.hargaRibu=hargaRibu;
        this.ket=ket;
        this.makeId();
    }
    
    public AsetDBTool(String kodeLok, Integer no, String jenis, String kode, String reg, Integer tahun, String judulNama, String pencipta, String spesifikasi, String kondisi, String asal, Double hargaRibu, String ket){
        this.kib="L";
        this.kodeLok=kodeLok;
        this.no=no;
        this.jenis=jenis;
        this.kode=kode;
        this.reg=reg;
        this.tahun=tahun;
        this.judulNama=judulNama;
        this.pencipta=pencipta;
        this.spesifikasi=spesifikasi;
        this.kondisi=kondisi;
        this.asal=asal;
        this.hargaRibu=hargaRibu;
        this.ket=ket;
        this.makeId();
    }
    
    private void makeId(){
        this.id=this.kodeLok+"."+this.kode+"."+this.reg;
    }
    
    public String myQueryInsertStr(){
        String ret="insert into tb_bmd_simda(";
        ret+=id_CONST+",";
        ret+=jenis_CONST+",";
        ret+=luasM2_CONST+",";
        ret+=tahun_CONST+",";
        ret+=alamat_CONST+",";
        ret+=tglDok_CONST+",";
        ret+=noDok_CONST+",";
        ret+=penggunaan_CONST+",";
        ret+=asal_CONST+",";
        ret+=hargaRibu_CONST+",";
        ret+=ket_CONST+",";
        ret+=kib_CONST+",";
        ret+=hak_CONST+",";
        ret+=merkType_CONST+",";
        ret+=ukuranCC_CONST+",";
        ret+=bahan_CONST+",";
        ret+=noPabrik_CONST+",";
        ret+=noRangka_CONST+",";
        ret+=noMesin_CONST+",";
        ret+=noPolisi_CONST+",";
        ret+=noBPKB_CONST+",";
        ret+=kondisi_CONST+",";
        ret+=bertingkat_CONST+",";
        ret+=beton_CONST+",";
        ret+=luasLantaiM2_CONST+",";
        ret+=statusTanah_CONST+",";
        ret+=noKodeTanah_CONST+",";
        ret+=konstruksi_CONST+",";
        ret+=panjangKM_CONST+",";
        ret+=lebarM_CONST+",";
        ret+=judul_CONST+",";
        ret+=spesifikasi_CONST+",";
        ret+=asalDaerah_CONST+",";
        ret+=pencipta_CONST+",";
        ret+=jenisHewan_CONST+",";
        ret+=ukuranHewan_CONST+",";
        ret+=jumlah_CONST+",";
        ret+=tahunCetak_CONST+",";
        ret+=bangunanP_SP_D_CONST+",";
        ret+=tglMulai_CONST+",";
        ret+=judulNama_CONST+")";

        ret+=" values(";
        ret+="'"+id+"',";
        ret+="'"+jenis+"',";
        ret+="'"+luasM2+"',";
        ret+="'"+tahun+"',";
        ret+="'"+alamat+"',";
        ret+="'"+dateString(tglDok)+"',";
        ret+="'"+noDok+"',";
        ret+="'"+penggunaan+"',";
        ret+="'"+asal+"',";
        ret+="'"+hargaRibu+"',";
        ret+="'"+ket+"',";
        ret+="'"+kib+"',";
        ret+="'"+hak+"',";
        ret+="'"+merkType+"',";
        ret+="'"+ukuranCC+"',";
        ret+="'"+bahan+"',";
        ret+="'"+noPabrik+"',";
        ret+="'"+noRangka+"',";
        ret+="'"+noMesin+"',";
        ret+="'"+noPolisi+"',";
        ret+="'"+noBPKB+"',";
        ret+="'"+kondisi+"',";
        ret+="'"+bertingkat+"',";
        ret+="'"+beton+"',";
        ret+="'"+luasLantaiM2+"',";
        ret+="'"+statusTanah+"',";
        ret+="'"+noKodeTanah+"',";
        ret+="'"+konstruksi+"',";
        ret+="'"+panjangKM+"',";
        ret+="'"+lebarM+"',";
        ret+="'"+judul+"',";
        ret+="'"+spesifikasi+"',";
        ret+="'"+asalDaerah+"',";
        ret+="'"+pencipta+"',";
        ret+="'"+jenisHewan+"',";
        ret+="'"+ukuranHewan+"',";
        ret+="'"+jumlah+"',";
        ret+="'"+tahunCetak+"',";
        ret+="'"+bangunanP_SP_D+"',";
        ret+="'"+dateString(tglMulai)+"',";
        ret+="'"+judulNama+"')";

        return ret;
        
    
    }
    
    private String dateString(Date dt){
        String ret="";
        if(dt!=null){
            ret=new SimpleDateFormat("Y-M-d").format(dt);
        }
        return ret;
    }
}
