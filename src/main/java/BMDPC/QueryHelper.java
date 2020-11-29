/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BMDPC;

import com.thowo.jmjavaframework.JMDate;
import com.thowo.jmjavaframework.JMFormatCollection;
import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.table.JMCell;
import com.thowo.jmjavaframework.table.JMRow;
import com.thowo.jmjavaframework.table.JMTable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author jimi
 */
public class QueryHelper {
    public static String q01NoDouble(){
        String qConst=QueryHelper.qNoDouble;
        
        String ret="";
        JMTable tbl=JMTable.create(qConst, JMTable.DBTYPE_MYSQL);
        if(!tbl.isEmpty()){
            JMRow r=tbl.firstRow(false);
            while(r!=null){
                if(ret.equals("")){
                    ret="(";
                }else{
                    ret+=",(";
                }
                for(int i=0;i<17;i++){
                    String tmp=r.getCells().get(i).getDBValue();
                    if(tmp==null)tmp="";
                    ret+="'"+tmp+"'";
                    if(i<16)ret+=",";
                }
                ret+=")";
                r=r.getNext();
            }
        }
        if(!ret.equals("")){
            ret="REPLACE INTO tb_inventaris(kd_inventaris, kd_xls_inventaris, kd_xls_kib_a, kd_xls_kib_b, kd_xls_kib_c, kd_xls_kib_d, kd_xls_kib_e, kd_xls_kib_f, kd_xls_kib_lainnya, kd_lokasi, kd_barang, kd_reg, uraian, harga, ket, manual, kd_urt_app) VALUES "+ret;
        }
        return ret;
    }
    
    public static String q02Double(String tableName){
        if(tableName.equals("tb_xls_inventaris")){
            return QueryHelper.qDoubleInv;
        }else if(tableName.equals("tb_xls_a")){
            return QueryHelper.qDoubleA;
        }else if(tableName.equals("tb_xls_b")){
            return QueryHelper.qDoubleB;
        }else if(tableName.equals("tb_xls_c")){
            return QueryHelper.qDoubleC;
        }else if(tableName.equals("tb_xls_d")){
            return QueryHelper.qDoubleD;
        }else if(tableName.equals("tb_xls_e")){
            return QueryHelper.qDoubleE;
        }else if(tableName.equals("tb_xls_f")){
            return QueryHelper.qDoubleF;
        }else if(tableName.equals("tb_xls_lainnya")){
            return QueryHelper.qDoubleLainnya;
        }else{
            return "";
        }
    }
    
    public static String q03DoubleInv(String tableName,String filter){
        if(filter.equals(""))return "";
        if(tableName.equals("tb_xls_inventaris")){
            return QueryHelper.qDoubleRealInv+" WHERE kd_xls_inventaris='' AND ("+filter+")";
        }else if(tableName.equals("tb_xls_a")){
            return QueryHelper.qDoubleRealInv+" WHERE kd_xls_kib_a='' AND ("+filter+")";
        }else if(tableName.equals("tb_xls_b")){
            return QueryHelper.qDoubleRealInv+" WHERE kd_xls_kib_b='' AND ("+filter+")";
        }else if(tableName.equals("tb_xls_c")){
            return QueryHelper.qDoubleRealInv+" WHERE kd_xls_kib_c='' AND ("+filter+")";
        }else if(tableName.equals("tb_xls_d")){
            return QueryHelper.qDoubleRealInv+" WHERE kd_xls_kib_d='' AND ("+filter+")";
        }else if(tableName.equals("tb_xls_e")){
            return QueryHelper.qDoubleRealInv+" WHERE kd_xls_kib_e='' AND ("+filter+")";
        }else if(tableName.equals("tb_xls_f")){
            return QueryHelper.qDoubleRealInv+" WHERE kd_xls_kib_f='' AND ("+filter+")";
        }else if(tableName.equals("tb_xls_lainnya")){
            return QueryHelper.qDoubleRealInv+" WHERE kd_xls_kib_lainnya='' AND ("+filter+")";
        }else{
            return "";
        }
    }
    
    private static String getXlsKey(List<String> tbInvKeys,List<String> tbXlsKeys, String invKey){
        ListIterator<String> li=tbInvKeys.listIterator();
        ListIterator<String> liXls=tbXlsKeys.listIterator();
        while(li.hasNext() && liXls.hasNext()){
            String key1=li.next();
            String key2=liXls.next();
            if(key1.equals(invKey))return key2;
        }
        return "";
    }
    
    public static String q04DoubleInvUpdate(String tableName,List<String> tbInvKeys,List<String> tbXlsKeys){
        if(tbInvKeys==null)return "";
        if(tbInvKeys.isEmpty())return "";
        
        int kdInd=-1;
        if(tableName.equals("tb_xls_inventaris")){
            kdInd=1;
        }else if(tableName.equals("tb_xls_a")){
            kdInd=2;
        }else if(tableName.equals("tb_xls_b")){
            kdInd=3;
        }else if(tableName.equals("tb_xls_c")){
            kdInd=4;
        }else if(tableName.equals("tb_xls_d")){
            kdInd=5;
        }else if(tableName.equals("tb_xls_e")){
            kdInd=6;
        }else if(tableName.equals("tb_xls_f")){
            kdInd=7;
        }else if(tableName.equals("tb_xls_lainnya")){
            kdInd=8;
        }
        
        String qInv="";
        for(String inv:tbInvKeys){
            if(qInv.equals("")){
                qInv="kd_inventaris='"+inv+"'";
            }else{
                qInv+=" OR kd_inventaris='"+inv+"'";
            }
        }
        qInv="select * from tb_inventaris WHERE ("+qInv+")";
        JMTable tbInv=JMTable.create(qInv, JMTable.DBTYPE_MYSQL);
        if(!tbInv.isEmpty()){
            String qTmp="";
            do{
                String tmp="";
                List<JMCell> cells=tbInv.getCurrentRow().getCells();
                String curKey="";
                for(int i=0;i<cells.size();i++){
                    String cellVal=cells.get(i).getDBValue();
                    if(i==0){
                        curKey=cellVal;
                    }else{
                        if(i==kdInd)cellVal=QueryHelper.getXlsKey(tbInvKeys, tbXlsKeys, curKey);
                    }
                    
                    if(tmp.equals("")){
                        tmp="'"+cellVal+"'";
                    }else{
                        tmp+=",'"+cellVal+"'";
                    }
                }
                
                
                if(qTmp.equals("")){
                    qTmp="VALUES("+tmp+")";
                }else{
                    qTmp+=",("+tmp+")";
                }
            }while(tbInv.nextRow(false)!=null);
            if(!qTmp.equals(""))qTmp="REPLACE INTO tb_inventaris(kd_inventaris, kd_xls_inventaris, kd_xls_kib_a, kd_xls_kib_b, kd_xls_kib_c, kd_xls_kib_d, kd_xls_kib_e, kd_xls_kib_f, kd_xls_kib_lainnya, kd_lokasi, kd_barang, kd_reg, uraian, harga, ket, manual, kd_urt_app) "+qTmp;
            return qTmp;
        }
        return "";
    }
    
    public static int newKdUrtApp(){
        String q="select MAX(urt) AS urt FROM\n" +
                    "(select tb_inventaris.*,CAST(SUBSTRING(tb_inventaris.kd_urt_app, 6) AS DOUBLE) AS urt \n" +
                    "from \n" +
                    "tb_inventaris \n" +
                    "where kd_urt_app<>''\n" +
                    ") AS inv";
        
        JMTable tmp=JMTable.create(q, JMTable.DBTYPE_MYSQL);
        if(tmp.isEmpty())return 1;
        JMFunctions.trace(tmp.getCurrentRow().getCells().get(0).getDBValue());
        Double d=tmp.getCurrentRow().getCells().get(0).getValueDouble();
        if(d>Integer.MAX_VALUE)return -1;
        return JMFormatCollection.doubleToInt(d)+1;
    }
    
    public static int newIdInv(String prefix){
        String q="select CAST(SUBSTRING(kd_inventaris, 12) AS SIGNED) AS id \n" +
                    "from tb_inventaris\n" +
                    "where kd_inventaris like '"+prefix+"%'\n" +
                    "order by kd_inventaris desc";
        
        JMTable tmp=JMTable.create(q, JMTable.DBTYPE_MYSQL);
        if(tmp.isEmpty())return 1;
        JMFunctions.trace(tmp.getCurrentRow().getCells().get(0).getDBValue());
        Double d=tmp.getCurrentRow().getCells().get(0).getValueDouble();
        if(d>Integer.MAX_VALUE)return -1;
        return JMFormatCollection.doubleToInt(d)+1;
    }
    
    
    public static String q05NewInvUpdate(){
        String ret="";
        JMTable tbNew=JMTable.create(QueryHelper.qNewInv, JMTable.DBTYPE_MYSQL);
        if(tbNew.isEmpty())return "";
        int newUrt=QueryHelper.newKdUrtApp();
        JMDate dt=JMDate.now();
        String prefix="INV"+dt.getYearFull()+JMFormatCollection.leadingZero(dt.getMonth(), 2)+JMFormatCollection.leadingZero(dt.getDayOfMonth(), 2);
        int newId=QueryHelper.newIdInv(prefix);
        if(newId==-1){
            JMFunctions.traceAndShow("Too many records, can't get new integer id");
            return "";
        }
        
        boolean setUrt=true;
        if(newUrt==-1)setUrt=false;
        String qTmp="";
        do{
            String s="'"+prefix+JMFormatCollection.leadingZero(newId, 5)+"'";
            List<JMCell> cells=tbNew.getCurrentRow().getCells();
            for(JMCell cell:cells){
                String dbVal=cell.getDBValue();
                if(dbVal==null)dbVal="";
                s+=",'"+dbVal+"'";
            }
            if(setUrt){
                s+=",'','0','PUPR-"+newUrt+"'";
            }else{
                s+=",'','0',''";
            }
            s="("+s+")";
            if(qTmp.equals("")){
                qTmp=s;
            }else{
                qTmp+=","+s;
            }
            if(newUrt==Integer.MAX_VALUE){
                setUrt=false;
            }else{
                newUrt++;
            }
            if(newId==Integer.MAX_VALUE){
                JMFunctions.traceAndShow("Too many records, can't get new integer id");
                return "";
            }else{
                newId++;
            }
        }while(tbNew.nextRow(false)!=null);
        ret="REPLACE INTO tb_inventaris(kd_inventaris, kd_xls_inventaris, kd_xls_kib_a, kd_xls_kib_b, kd_xls_kib_c, kd_xls_kib_d, kd_xls_kib_e, kd_xls_kib_f, kd_xls_kib_lainnya, kd_lokasi, kd_barang, kd_reg, uraian, harga, ket, manual, kd_urt_app)";
        ret+=" VALUES"+qTmp;
        return ret;
    }
    
    public static List<String> q06ManualUpdate(List<String> tbAutoKeys,List<String> tbManualKeys){
        if(tbAutoKeys==null)return null;
        if(tbAutoKeys.isEmpty())return null;
        List<String> ret=new ArrayList();
        
        String q="";
        for(String manual:tbManualKeys){
            if(q.equals("")){
                q="kd_inventaris='"+manual+"'";
            }else{
                q+=" OR kd_inventaris='"+manual+"'";
            }
        }
        q="delete * from tb_xls_inventaris where "+q;
        ret.add(q);
        
        q="";
        for(String manual:tbManualKeys){
            if(q.equals("")){
                q="kd_kib_a='"+manual+"'";
            }else{
                q+=" OR kd_kib_a='"+manual+"'";
            }
        }
        q="delete * from tb_xls_a where "+q;
        ret.add(q);
        
        q="";
        for(String manual:tbManualKeys){
            if(q.equals("")){
                q="kd_kib_b='"+manual+"'";
            }else{
                q+=" OR kd_kib_b='"+manual+"'";
            }
        }
        q="delete * from tb_xls_b where "+q;
        ret.add(q);
        
        q="";
        for(String manual:tbManualKeys){
            if(q.equals("")){
                q="kd_kib_c='"+manual+"'";
            }else{
                q+=" OR kd_kib_c='"+manual+"'";
            }
        }
        q="delete * from tb_xls_c where "+q;
        ret.add(q);
        
        q="";
        for(String manual:tbManualKeys){
            if(q.equals("")){
                q="kd_kib_d='"+manual+"'";
            }else{
                q+=" OR kd_kib_d='"+manual+"'";
            }
        }
        q="delete * from tb_xls_d where "+q;
        ret.add(q);
        
        q="";
        for(String manual:tbManualKeys){
            if(q.equals("")){
                q="kd_kib_e='"+manual+"'";
            }else{
                q+=" OR kd_kib_e='"+manual+"'";
            }
        }
        q="delete * from tb_xls_e where "+q;
        ret.add(q);
        
        q="";
        for(String manual:tbManualKeys){
            if(q.equals("")){
                q="kd_kib_f='"+manual+"'";
            }else{
                q+=" OR kd_kib_f='"+manual+"'";
            }
        }
        q="delete * from tb_xls_f where "+q;
        ret.add(q);
        
        q="";
        for(String manual:tbManualKeys){
            if(q.equals("")){
                q="kd_kib_lainnya='"+manual+"'";
            }else{
                q+=" OR kd_kib_lainnya='"+manual+"'";
            }
        }
        q="delete * from tb_xls_lainnya where "+q;
        ret.add(q);
        
        q="";
        for(String manual:tbManualKeys){
            if(q.equals("")){
                q="kd_inventaris='"+manual+"'";
            }else{
                q+=" OR kd_inventaris='"+manual+"'";
            }
        }
        q="delete * from tb_inventaris where "+q;
        ret.add(q);
        
        
        ListIterator<String> liAuto=tbAutoKeys.listIterator();
        ListIterator<String> liManual=tbManualKeys.listIterator();
        while(liAuto.hasNext() && liManual.hasNext()){
            String keyAuto=liAuto.next();
            String keyManual=liManual.next();
            if(!keyAuto.equals("")){
                ret.add("update tb_inventaris set kd_inventaris='"+keyAuto+"' where kd_inventaris='"+keyManual+"'");
            }
        }
        
        return ret;
    }
    
    
    private static final String qNewInv="SELECT \n" +
                                        "	qInv.kd_inventaris as kd_xls_inventaris,\n" +
                                        "    qA.kd_kib_a as kd_xls_kib_a,\n" +
                                        "    qB.kd_kib_b as kd_xls_kib_b,\n" +
                                        "    qC.kd_kib_c as kd_xls_kib_c,\n" +
                                        "    qD.kd_kib_d as kd_xls_kib_d,\n" +
                                        "    qE.kd_kib_e as kd_xls_kib_e,\n" +
                                        "    qF.kd_kib_f as kd_xls_kib_f,\n" +
                                        "    qLainnya.kd_kib_lainnya as kd_xls_kib_lainnya,\n" +
                                        "    qInv.kd_lokasi as kd_lokasi,\n" +
                                        "    qInv.kd_barang as kd_barang,\n" +
                                        "    qInv.kd_reg as kd_reg,\n" +
                                        "    qInv.uraian as uraian,\n" +
                                        "    qInv.harga as harga\n" +
                                        "FROM\n" +
                                        "\n" +
                                        "(select\n" +
                                        "	tb_xls_inventaris.kd_inventaris as kd_inventaris,\n" +
                                        "    tb_xls_inventaris.kd_lokasi as kd_lokasi,\n" +
                                        "    tb_xls_inventaris.kd_barang as kd_barang,\n" +
                                        "    tb_xls_inventaris.kd_reg as kd_reg,\n" +
                                        "	CONCAT(\n" +
                                        "        tb_xls_inventaris.jenis,' (',\n" +
                                        "        tb_xls_inventaris.tahun,')',CHAR(10),\n" +
                                        "        tb_xls_inventaris.merk,CHAR(10),\n" +
                                        "        tb_xls_inventaris.no_SPCM) as uraian,\n" +
                                        "    tb_xls_inventaris.harga as harga\n" +
                                        "FROM tb_xls_inventaris\n" +
                                        "LEFT JOIN tb_inventaris\n" +
                                        "ON tb_inventaris.kd_xls_inventaris=tb_xls_inventaris.kd_inventaris\n" +
                                        "WHERE \n" +
                                        "	tb_inventaris.kd_xls_inventaris IS NULL and \n" +
                                        "    tb_xls_inventaris.manual='0') as qInv\n" +
                                        "    \n" +
                                        "LEFT JOIN\n" +
                                        "\n" +
                                        "(SELECT tba.*\n" +
                                        "FROM\n" +
                                        "(SELECT \n" +
                                        "	tb_xls_a.kd_kib_a,\n" +
                                        " 	tb_xls_a.kd_lokasi,\n" +
                                        " 	tb_xls_a.kd_barang,\n" +
                                        " 	tb_xls_a.kd_reg,\n" +
                                        "    COUNT(tb_xls_a.kd_kib_a) as jlh\n" +
                                        "FROM tb_xls_a\n" +
                                        "WHERE tb_xls_a.manual='0'\n" +
                                        "GROUP BY\n" +
                                        "	tb_xls_a.kd_lokasi,\n" +
                                        "    tb_xls_a.kd_barang,\n" +
                                        "    tb_xls_a.kd_reg) as tba\n" +
                                        "WHERE tba.jlh='1') as qA\n" +
                                        "ON \n" +
                                        "	qA.kd_lokasi=qInv.kd_lokasi AND\n" +
                                        "	qA.kd_barang=qInv.kd_barang AND\n" +
                                        "    qA.kd_reg=qInv.kd_reg\n" +
                                        "    \n" +
                                        "    \n" +
                                        "LEFT JOIN\n" +
                                        "\n" +
                                        "(SELECT tbb.*\n" +
                                        "FROM\n" +
                                        "(SELECT \n" +
                                        "	tb_xls_b.kd_kib_b,\n" +
                                        " 	tb_xls_b.kd_lokasi,\n" +
                                        " 	tb_xls_b.kd_barang,\n" +
                                        " 	tb_xls_b.kd_reg,\n" +
                                        "    COUNT(tb_xls_b.kd_kib_b) as jlh\n" +
                                        "FROM tb_xls_b\n" +
                                        "WHERE tb_xls_b.manual='0'\n" +
                                        "GROUP BY\n" +
                                        "	tb_xls_b.kd_lokasi,\n" +
                                        "    tb_xls_b.kd_barang,\n" +
                                        "    tb_xls_b.kd_reg) as tbb\n" +
                                        "WHERE tbb.jlh='1') as qB\n" +
                                        "ON \n" +
                                        "	qB.kd_lokasi=qInv.kd_lokasi AND\n" +
                                        "	qB.kd_barang=qInv.kd_barang AND\n" +
                                        "    qB.kd_reg=qInv.kd_reg\n" +
                                        "    \n" +
                                        "        \n" +
                                        "LEFT JOIN\n" +
                                        "\n" +
                                        "(SELECT tbc.*\n" +
                                        "FROM\n" +
                                        "(SELECT \n" +
                                        "	tb_xls_c.kd_kib_c,\n" +
                                        " 	tb_xls_c.kd_lokasi,\n" +
                                        " 	tb_xls_c.kd_barang,\n" +
                                        " 	tb_xls_c.kd_reg,\n" +
                                        "    COUNT(tb_xls_c.kd_kib_c) as jlh\n" +
                                        "FROM tb_xls_c\n" +
                                        "WHERE tb_xls_c.manual='0'\n" +
                                        "GROUP BY\n" +
                                        "	tb_xls_c.kd_lokasi,\n" +
                                        "    tb_xls_c.kd_barang,\n" +
                                        "    tb_xls_c.kd_reg) as tbc\n" +
                                        "WHERE tbc.jlh='1') as qC\n" +
                                        "ON \n" +
                                        "	qC.kd_lokasi=qInv.kd_lokasi AND\n" +
                                        "	qC.kd_barang=qInv.kd_barang AND\n" +
                                        "    qC.kd_reg=qInv.kd_reg\n" +
                                        "    \n" +
                                        "            \n" +
                                        "LEFT JOIN\n" +
                                        "\n" +
                                        "(SELECT tbd.*\n" +
                                        "FROM\n" +
                                        "(SELECT \n" +
                                        "	tb_xls_d.kd_kib_d,\n" +
                                        " 	tb_xls_d.kd_lokasi,\n" +
                                        " 	tb_xls_d.kd_barang,\n" +
                                        " 	tb_xls_d.kd_reg,\n" +
                                        "    COUNT(tb_xls_d.kd_kib_d) as jlh\n" +
                                        "FROM tb_xls_d\n" +
                                        "WHERE tb_xls_d.manual='0'\n" +
                                        "GROUP BY\n" +
                                        "	tb_xls_d.kd_lokasi,\n" +
                                        "    tb_xls_d.kd_barang,\n" +
                                        "    tb_xls_d.kd_reg) as tbd\n" +
                                        "WHERE tbd.jlh='1') as qD\n" +
                                        "ON \n" +
                                        "	qD.kd_lokasi=qInv.kd_lokasi AND\n" +
                                        "	qD.kd_barang=qInv.kd_barang AND\n" +
                                        "    qD.kd_reg=qInv.kd_reg\n" +
                                        "    \n" +
                                        "                \n" +
                                        "LEFT JOIN\n" +
                                        "\n" +
                                        "(SELECT tbe.*\n" +
                                        "FROM\n" +
                                        "(SELECT \n" +
                                        "	tb_xls_e.kd_kib_e,\n" +
                                        " 	tb_xls_e.kd_lokasi,\n" +
                                        " 	tb_xls_e.kd_barang,\n" +
                                        " 	tb_xls_e.kd_reg,\n" +
                                        "    COUNT(tb_xls_e.kd_kib_e) as jlh\n" +
                                        "FROM tb_xls_e\n" +
                                        "WHERE tb_xls_e.manual='0'\n" +
                                        "GROUP BY\n" +
                                        "	tb_xls_e.kd_lokasi,\n" +
                                        "    tb_xls_e.kd_barang,\n" +
                                        "    tb_xls_e.kd_reg) as tbe\n" +
                                        "WHERE tbe.jlh='1') as qE\n" +
                                        "ON \n" +
                                        "	qE.kd_lokasi=qInv.kd_lokasi AND\n" +
                                        "	qE.kd_barang=qInv.kd_barang AND\n" +
                                        "    qE.kd_reg=qInv.kd_reg\n" +
                                        "    \n" +
                                        "                    \n" +
                                        "LEFT JOIN\n" +
                                        "\n" +
                                        "(SELECT tbf.*\n" +
                                        "FROM\n" +
                                        "(SELECT \n" +
                                        "	tb_xls_f.kd_kib_f,\n" +
                                        " 	tb_xls_f.kd_lokasi,\n" +
                                        " 	tb_xls_f.kd_barang,\n" +
                                        " 	tb_xls_f.kd_reg,\n" +
                                        "    COUNT(tb_xls_f.kd_kib_f) as jlh\n" +
                                        "FROM tb_xls_f\n" +
                                        "WHERE tb_xls_f.manual='0'\n" +
                                        "GROUP BY\n" +
                                        "	tb_xls_f.kd_lokasi,\n" +
                                        "    tb_xls_f.kd_barang,\n" +
                                        "    tb_xls_f.kd_reg) as tbf\n" +
                                        "WHERE tbf.jlh='1') as qF\n" +
                                        "ON \n" +
                                        "	qF.kd_lokasi=qInv.kd_lokasi AND\n" +
                                        "	qF.kd_barang=qInv.kd_barang AND\n" +
                                        "    qF.kd_reg=qInv.kd_reg\n" +
                                        "    \n" +
                                        "                        \n" +
                                        "LEFT JOIN\n" +
                                        "\n" +
                                        "(SELECT tblainnya.*\n" +
                                        "FROM\n" +
                                        "(SELECT \n" +
                                        "	tb_xls_lainnya.kd_kib_lainnya,\n" +
                                        " 	tb_xls_lainnya.kd_lokasi,\n" +
                                        " 	tb_xls_lainnya.kd_barang,\n" +
                                        " 	tb_xls_lainnya.kd_reg,\n" +
                                        "    COUNT(tb_xls_lainnya.kd_kib_lainnya) as jlh\n" +
                                        "FROM tb_xls_lainnya\n" +
                                        "WHERE tb_xls_lainnya.manual='0'\n" +
                                        "GROUP BY\n" +
                                        "	tb_xls_lainnya.kd_lokasi,\n" +
                                        "    tb_xls_lainnya.kd_barang,\n" +
                                        "    tb_xls_lainnya.kd_reg) as tblainnya\n" +
                                        "WHERE tblainnya.jlh='1') as qLainnya\n" +
                                        "ON \n" +
                                        "	qLainnya.kd_lokasi=qInv.kd_lokasi AND\n" +
                                        "	qLainnya.kd_barang=qInv.kd_barang AND\n" +
                                        "    qLainnya.kd_reg=qInv.kd_reg";
    
    private static final String qDoubleRealInv="select * from (SELECT \n" +
                                                    " 	tb_inventaris.*,\n" +
                                                    " 	CONCAT(tb_inventaris.kd_lokasi,'.',tb_inventaris.kd_barang,'.',tb_inventaris.kd_reg) as kdDup\n" +
                                                    " from tb_inventaris) as inv";
    
    private static final String qDoubleA="SELECT xlsAQ.*\n" +
                                            "from\n" +
                                            "\n" +
                                            "(select \n" +
                                            "tba.kd_kib_a as kd_xls_a,\n" +
                                            "CONCAT(tba.kd_lokasi,'.',tba.kd_barang,'.',tba.kd_reg) as kdDup \n" +
                                            "from\n" +
                                            "(select * from\n" +
                                            "(SELECT \n" +
                                            "tbi.kd_inventaris as kd_inventaris,\n" +
                                            "tb_xls_a.*,\n" +
                                            "COUNT(tb_xls_a.kd_kib_a) as jlh\n" +
                                            "FROM tb_xls_a JOIN (select * from tb_inventaris where tb_inventaris.kd_xls_kib_a='') as tbi\n" +
                                            "ON\n" +
                                            "tbi.kd_lokasi=tb_xls_a.kd_lokasi AND\n" +
                                            "tbi.kd_barang=tb_xls_a.kd_barang AND\n" +
                                            "tbi.kd_reg=tb_xls_a.kd_reg\n" +
                                            "GROUP BY kd_lokasi,kd_barang,kd_reg) as kiba\n" +
                                            "where jlh>'1' and manual='0') as tba) as fltrdA\n" +
                                            "\n" +
                                            "JOIN\n" +
                                            "\n" +
                                            "(SELECT \n" +
                                            " 	tb_xls_a.*,\n" +
                                            " 	CONCAT(tb_xls_a.kd_lokasi,'.',tb_xls_a.kd_barang,'.',tb_xls_a.kd_reg) as kdDup\n" +
                                            " from tb_xls_a\n" +
                                            ")as xlsAQ\n" +
                                            "\n" +
                                            "ON\n" +
                                            "xlsAQ.kdDup=fltrdA.kdDup";
    
    private static final String qDoubleB="SELECT xlsBQ.*\n" +
                                            "from\n" +
                                            "\n" +
                                            "(select \n" +
                                            "tbb.kd_kib_b as kd_xls_b,\n" +
                                            "CONCAT(tbb.kd_lokasi,'.',tbb.kd_barang,'.',tbb.kd_reg) as kdDup\n" +
                                            "from\n" +
                                            "\n" +
                                            "(select * from\n" +
                                            "(SELECT \n" +
                                            "tbi.kd_inventaris as kd_inventaris,\n" +
                                            "tb_xls_b.*,\n" +
                                            "COUNT(tb_xls_b.kd_kib_b) as jlh\n" +
                                            "FROM tb_xls_b JOIN (select * from tb_inventaris where tb_inventaris.kd_xls_kib_b='') as tbi\n" +
                                            "ON\n" +
                                            "tbi.kd_lokasi=tb_xls_b.kd_lokasi AND\n" +
                                            "tbi.kd_barang=tb_xls_b.kd_barang AND\n" +
                                            "tbi.kd_reg=tb_xls_b.kd_reg\n" +
                                            "GROUP BY kd_lokasi,kd_barang,kd_reg) as kibb\n" +
                                            "where jlh>'1' and manual='0') as tbb) as fltrdB\n" +
                                            "\n" +
                                            "JOIN\n" +
                                            "\n" +
                                            "(SELECT \n" +
                                            " 	tb_xls_b.*,\n" +
                                            " 	CONCAT(tb_xls_b.kd_lokasi,'.',tb_xls_b.kd_barang,'.',tb_xls_b.kd_reg) as kdDup\n" +
                                            " from tb_xls_b\n" +
                                            ")as xlsBQ\n" +
                                            "\n" +
                                            "ON\n" +
                                            "xlsBQ.kdDup=fltrdB.kdDup";
    
    private static final String qDoubleC="SELECT xlsCQ.*\n" +
                                            "from\n" +
                                            "\n" +
                                            "(select \n" +
                                            "tbc.kd_kib_c as kd_xls_c,\n" +
                                            "CONCAT(tbc.kd_lokasi,'.',tbc.kd_barang,'.',tbc.kd_reg) as kdDup\n" +
                                            "from\n" +
                                            "\n" +
                                            "(select * from\n" +
                                            "(SELECT \n" +
                                            "tbi.kd_inventaris as kd_inventaris,\n" +
                                            "tb_xls_c.*,\n" +
                                            "COUNT(tb_xls_c.kd_kib_c) as jlh\n" +
                                            "FROM tb_xls_c JOIN (select * from tb_inventaris where tb_inventaris.kd_xls_kib_c='') as tbi\n" +
                                            "ON\n" +
                                            "tbi.kd_lokasi=tb_xls_c.kd_lokasi AND\n" +
                                            "tbi.kd_barang=tb_xls_c.kd_barang AND\n" +
                                            "tbi.kd_reg=tb_xls_c.kd_reg\n" +
                                            "GROUP BY kd_lokasi,kd_barang,kd_reg) as kibc\n" +
                                            "where jlh>'1' and manual='0') as tbc) as fltrdC\n" +
                                            "\n" +
                                            "JOIN\n" +
                                            "\n" +
                                            "(SELECT \n" +
                                            " 	tb_xls_c.*,\n" +
                                            " 	CONCAT(tb_xls_c.kd_lokasi,'.',tb_xls_c.kd_barang,'.',tb_xls_c.kd_reg) as kdDup\n" +
                                            " from tb_xls_c\n" +
                                            ")as xlsCQ\n" +
                                            "\n" +
                                            "ON\n" +
                                            "xlsCQ.kdDup=fltrdC.kdDup";
    
    private static final String qDoubleD="SELECT xlsDQ.*\n" +
                                            "from\n" +
                                            "\n" +
                                            "(select \n" +
                                            "tbd.kd_kib_d as kd_xls_d,\n" +
                                            "CONCAT(tbd.kd_lokasi,'.',tbd.kd_barang,'.',tbd.kd_reg) as kdDup\n" +
                                            "from\n" +
                                            "\n" +
                                            "(select * from\n" +
                                            "(SELECT \n" +
                                            "tbi.kd_inventaris as kd_inventaris,\n" +
                                            "tb_xls_d.*,\n" +
                                            "COUNT(tb_xls_d.kd_kib_d) as jlh\n" +
                                            "FROM tb_xls_d JOIN (select * from tb_inventaris where tb_inventaris.kd_xls_kib_d='') as tbi\n" +
                                            "ON\n" +
                                            "tbi.kd_lokasi=tb_xls_d.kd_lokasi AND\n" +
                                            "tbi.kd_barang=tb_xls_d.kd_barang AND\n" +
                                            "tbi.kd_reg=tb_xls_d.kd_reg\n" +
                                            "GROUP BY kd_lokasi,kd_barang,kd_reg) as kibd\n" +
                                            "where jlh>'1' and manual='0') as tbd) as fltrdD\n" +
                                            "\n" +
                                            "JOIN\n" +
                                            "\n" +
                                            "(SELECT \n" +
                                            " 	tb_xls_d.*,\n" +
                                            " 	CONCAT(tb_xls_d.kd_lokasi,'.',tb_xls_d.kd_barang,'.',tb_xls_d.kd_reg) as kdDup\n" +
                                            " from tb_xls_d\n" +
                                            ")as xlsDQ\n" +
                                            "\n" +
                                            "ON\n" +
                                            "xlsDQ.kdDup=fltrdD.kdDup";
    
    private static final String qDoubleE="SELECT xlsEQ.*\n" +
                                            "from\n" +
                                            "\n" +
                                            "(select \n" +
                                            "tbe.kd_kib_e as kd_xls_e,\n" +
                                            "CONCAT(tbe.kd_lokasi,'.',tbe.kd_barang,'.',tbe.kd_reg) as kdDup\n" +
                                            "from\n" +
                                            "(select * from\n" +
                                            "(SELECT \n" +
                                            "tbi.kd_inventaris as kd_inventaris,\n" +
                                            "tb_xls_e.*,\n" +
                                            "COUNT(tb_xls_e.kd_kib_e) as jlh\n" +
                                            "FROM tb_xls_e JOIN (select * from tb_inventaris where tb_inventaris.kd_xls_kib_e='') as tbi\n" +
                                            "ON\n" +
                                            "tbi.kd_lokasi=tb_xls_e.kd_lokasi AND\n" +
                                            "tbi.kd_barang=tb_xls_e.kd_barang AND\n" +
                                            "tbi.kd_reg=tb_xls_e.kd_reg\n" +
                                            "GROUP BY kd_lokasi,kd_barang,kd_reg) as kibe\n" +
                                            "where jlh>'1' and manual='0') as tbe) as fltrdE\n" +
                                            "\n" +
                                            "JOIN\n" +
                                            "\n" +
                                            "(SELECT \n" +
                                            " 	tb_xls_e.*,\n" +
                                            " 	CONCAT(tb_xls_e.kd_lokasi,'.',tb_xls_e.kd_barang,'.',tb_xls_e.kd_reg) as kdDup\n" +
                                            " from tb_xls_e\n" +
                                            ")as xlsEQ\n" +
                                            "\n" +
                                            "ON\n" +
                                            "xlsEQ.kdDup=fltrdE.kdDup";
    
    private static final String qDoubleF="SELECT xlsFQ.*\n" +
                                            "from\n" +
                                            "\n" +
                                            "(select \n" +
                                            "tbf.kd_kib_f as kd_xls_f,\n" +
                                            "CONCAT(tbf.kd_lokasi,'.',tbf.kd_barang,'.',tbf.kd_reg) as kdDup\n" +
                                            "from\n" +
                                            "(select * from\n" +
                                            "(SELECT \n" +
                                            "tbi.kd_inventaris as kd_inventaris,\n" +
                                            "tb_xls_f.*,\n" +
                                            "COUNT(tb_xls_f.kd_kib_f) as jlh\n" +
                                            "FROM tb_xls_f JOIN (select * from tb_inventaris where tb_inventaris.kd_xls_kib_f='') as tbi\n" +
                                            "ON\n" +
                                            "tbi.kd_lokasi=tb_xls_f.kd_lokasi AND\n" +
                                            "tbi.kd_barang=tb_xls_f.kd_barang AND\n" +
                                            "tbi.kd_reg=tb_xls_f.kd_reg\n" +
                                            "GROUP BY kd_lokasi,kd_barang,kd_reg) as kibf\n" +
                                            "where jlh>'1' and manual='0') as tbf) as fltrdF\n" +
                                            "\n" +
                                            "JOIN\n" +
                                            "\n" +
                                            "(SELECT \n" +
                                            " 	tb_xls_f.*,\n" +
                                            " 	CONCAT(tb_xls_f.kd_lokasi,'.',tb_xls_f.kd_barang,'.',tb_xls_f.kd_reg) as kdDup\n" +
                                            " from tb_xls_f\n" +
                                            ")as xlsFQ\n" +
                                            "\n" +
                                            "ON\n" +
                                            "xlsFQ.kdDup=fltrdF.kdDup";
    
    private static final String qDoubleLainnya="SELECT xlsLainnyaQ.*\n" +
                                                "from\n" +
                                                "\n" +
                                                "(select \n" +
                                                "tblainnya.kd_kib_lainnya as kd_xls_lainnya,\n" +
                                                "CONCAT(tblainnya.kd_lokasi,'.',tblainnya.kd_barang,'.',tblainnya.kd_reg) as kdDup\n" +
                                                "from\n" +
                                                "(select * from\n" +
                                                "(SELECT \n" +
                                                "tbi.kd_inventaris as kd_inventaris,\n" +
                                                "tb_xls_lainnya.*,\n" +
                                                "COUNT(tb_xls_lainnya.kd_kib_lainnya) as jlh\n" +
                                                "FROM tb_xls_lainnya JOIN (select * from tb_inventaris where tb_inventaris.kd_xls_kib_lainnya='') as tbi\n" +
                                                "ON\n" +
                                                "tbi.kd_lokasi=tb_xls_lainnya.kd_lokasi AND\n" +
                                                "tbi.kd_barang=tb_xls_lainnya.kd_barang AND\n" +
                                                "tbi.kd_reg=tb_xls_lainnya.kd_reg\n" +
                                                "GROUP BY kd_lokasi,kd_barang,kd_reg) as kiblainnya\n" +
                                                "where jlh>'1' and manual='0') as tblainnya) as fltrdLainnya\n" +
                                                "\n" +
                                                "JOIN\n" +
                                                "\n" +
                                                "(SELECT \n" +
                                                " 	tb_xls_lainnya.*,\n" +
                                                " 	CONCAT(tb_xls_lainnya.kd_lokasi,'.',tb_xls_lainnya.kd_barang,'.',tb_xls_lainnya.kd_reg) as kdDup\n" +
                                                " from tb_xls_lainnya\n" +
                                                ")as xlsLainnyaQ\n" +
                                                "\n" +
                                                "ON\n" +
                                                "xlsLainnyaQ.kdDup=fltrdLainnya.kdDup";
    
    private static final String qDoubleInv="SELECT xlsInvQ.*\n" +
                                            "from\n" +
                                            "\n" +
                                            "(select \n" +
                                            "tbinv.kd_inventaris_real as kd_app,\n" +
                                            "tbinv.kd_inventaris as kd_xls_inventaris,\n" +
                                            "CONCAT(tbinv.kd_lokasi,'.',tbinv.kd_barang,'.',tbinv.kd_reg) as kdDup,\n" +
                                            "CONCAT(tbinv.jenis,' (',tbinv.tahun,')',CHAR(10),tbinv.merk,CHAR\n" +
                                            "\n" +
                                            "(10),tbinv.no_SPCM) as uraian,\n" +
                                            "tbinv.harga as harga\n" +
                                            "from\n" +
                                            "\n" +
                                            "\n" +
                                            "(select * from\n" +
                                            "(SELECT \n" +
                                            "tbi.kd_inventaris as kd_inventaris_real,\n" +
                                            "tb_xls_inventaris.*,\n" +
                                            "COUNT(tb_xls_inventaris.kd_inventaris) as jlh\n" +
                                            "FROM tb_xls_inventaris JOIN (select * from tb_inventaris where tb_inventaris.kd_xls_inventaris='') as tbi\n" +
                                            "ON\n" +
                                            "tbi.kd_lokasi=tb_xls_inventaris.kd_lokasi AND\n" +
                                            "tbi.kd_barang=tb_xls_inventaris.kd_barang AND\n" +
                                            "tbi.kd_reg=tb_xls_inventaris.kd_reg\n" +
                                            "GROUP BY kd_lokasi,kd_barang,kd_reg) as inv\n" +
                                            "where jlh>'1' and manual='0') as tbinv) as fltrdInv\n" +
                                            "\n" +
                                            "JOIN\n" +
                                            "\n" +
                                            "(SELECT \n" +
                                            " 	tb_xls_inventaris.*,\n" +
                                            " 	CONCAT(tb_xls_inventaris.kd_lokasi,'.',tb_xls_inventaris.kd_barang,'.',tb_xls_inventaris.kd_reg) as kdDup\n" +
                                            " from tb_xls_inventaris\n" +
                                            ")as xlsInvQ\n" +
                                            "\n" +
                                            "ON\n" +
                                            "xlsInvQ.kdDup=fltrdInv.kdDup";
    
    private static final String qNoDouble="select \n" +
                                            "newInv.kd_app as kd_inventaris,\n" +
                                            "newInv.kd_xls_inventaris as kd_xls_inventaris,\n" +
                                            "newInv.kd_xls_a as kd_xls_kib_a,\n" +
                                            "newInv.kd_xls_b as kd_xls_kib_b,\n" +
                                            "newInv.kd_xls_c as kd_xls_kib_c,\n" +
                                            "newInv.kd_xls_d as kd_xls_kib_d,\n" +
                                            "newInv.kd_xls_e as kd_xls_kib_e,\n" +
                                            "newInv.kd_xls_f as kd_xls_kib_f,\n" +
                                            "newInv.kd_xls_lainnya as kd_xls_kib_lainnya,\n" +
                                            "tb_inventaris.kd_lokasi as kd_lokasi,\n" +
                                            "tb_inventaris.kd_barang as kd_barang,\n" +
                                            "tb_inventaris.kd_reg as kd_reg,\n" +
                                            "newInv.uraian as uraian,\n" +
                                            "newInv.harga as harga,\n" +
                                            "tb_inventaris.ket as ket,\n" +
                                            "tb_inventaris.manual as manual,\n" +
                                            "tb_inventaris.kd_urt_app as kd_urt_app\n" +
                                            "\n" +
                                            "from tb_inventaris JOIN\n" +
                                            "(select \n" +
                                            "tbinv.kd_inventaris_real as kd_app,\n" +
                                            "tbinv.kd_inventaris as kd_xls_inventaris,\n" +
                                            "tba.kd_kib_a as kd_xls_a,\n" +
                                            "tbb.kd_kib_b as kd_xls_b,\n" +
                                            "tbc.kd_kib_c as kd_xls_c,\n" +
                                            "tbd.kd_kib_d as kd_xls_d,\n" +
                                            "tbe.kd_kib_e as kd_xls_e,\n" +
                                            "tbf.kd_kib_f as kd_xls_f,\n" +
                                            "tblainnya.kd_kib_lainnya as kd_xls_lainnya,\n" +
                                            "CONCAT(tbinv.jenis,' (',tbinv.tahun,')',CHAR(10),tbinv.merk,CHAR\n" +
                                            "\n" +
                                            "(10),tbinv.no_SPCM) as uraian,\n" +
                                            "tbinv.harga as harga\n" +
                                            "from\n" +
                                            "\n" +
                                            "\n" +
                                            "(select * from\n" +
                                            "(SELECT \n" +
                                            "tb_inventaris.kd_inventaris as kd_inventaris_real,\n" +
                                            "tb_xls_inventaris.*,\n" +
                                            "COUNT(tb_xls_inventaris.kd_inventaris) as jlh\n" +
                                            "FROM tb_xls_inventaris JOIN tb_inventaris\n" +
                                            "ON\n" +
                                            "tb_inventaris.kd_lokasi=tb_xls_inventaris.kd_lokasi AND\n" +
                                            "tb_inventaris.kd_barang=tb_xls_inventaris.kd_barang AND\n" +
                                            "tb_inventaris.kd_reg=tb_xls_inventaris.kd_reg\n" +
                                            "GROUP BY kd_lokasi,kd_barang,kd_reg) as inv\n" +
                                            "where jlh='1' and manual='0') as tbinv\n" +
                                            "\n" +
                                            "LEFT JOIN\n" +
                                            "\n" +
                                            "(select * from\n" +
                                            "(SELECT \n" +
                                            "tb_inventaris.kd_inventaris as kd_inventaris,\n" +
                                            "tb_xls_a.*,\n" +
                                            "COUNT(tb_xls_a.kd_kib_a) as jlh\n" +
                                            "FROM tb_xls_a JOIN tb_inventaris\n" +
                                            "ON\n" +
                                            "tb_inventaris.kd_lokasi=tb_xls_a.kd_lokasi AND\n" +
                                            "tb_inventaris.kd_barang=tb_xls_a.kd_barang AND\n" +
                                            "tb_inventaris.kd_reg=tb_xls_a.kd_reg\n" +
                                            "GROUP BY kd_lokasi,kd_barang,kd_reg) as kiba\n" +
                                            "where jlh='1' and manual='0') as tba\n" +
                                            "ON tba.kd_inventaris=tbinv.kd_inventaris_real\n" +
                                            "\n" +
                                            "LEFT JOIN\n" +
                                            "\n" +
                                            "(select * from\n" +
                                            "(SELECT \n" +
                                            "tb_inventaris.kd_inventaris as kd_inventaris,\n" +
                                            "tb_xls_b.*,\n" +
                                            "COUNT(tb_xls_b.kd_kib_b) as jlh\n" +
                                            "FROM tb_xls_b JOIN tb_inventaris\n" +
                                            "ON\n" +
                                            "tb_inventaris.kd_lokasi=tb_xls_b.kd_lokasi AND\n" +
                                            "tb_inventaris.kd_barang=tb_xls_b.kd_barang AND\n" +
                                            "tb_inventaris.kd_reg=tb_xls_b.kd_reg\n" +
                                            "GROUP BY kd_lokasi,kd_barang,kd_reg) as kibb\n" +
                                            "where jlh='1' and manual='0') as tbb\n" +
                                            "ON tbb.kd_inventaris=tbinv.kd_inventaris_real\n" +
                                            "\n" +
                                            "\n" +
                                            "LEFT JOIN\n" +
                                            "\n" +
                                            "\n" +
                                            "(select * from\n" +
                                            "(SELECT \n" +
                                            "tb_inventaris.kd_inventaris as kd_inventaris,\n" +
                                            "tb_xls_c.*,\n" +
                                            "COUNT(tb_xls_c.kd_kib_c) as jlh\n" +
                                            "FROM tb_xls_c JOIN tb_inventaris\n" +
                                            "ON\n" +
                                            "tb_inventaris.kd_lokasi=tb_xls_c.kd_lokasi AND\n" +
                                            "tb_inventaris.kd_barang=tb_xls_c.kd_barang AND\n" +
                                            "tb_inventaris.kd_reg=tb_xls_c.kd_reg\n" +
                                            "GROUP BY kd_lokasi,kd_barang,kd_reg) as kibc\n" +
                                            "where jlh='1' and manual='0') as tbc\n" +
                                            "ON tbc.kd_inventaris=tbinv.kd_inventaris_real\n" +
                                            "\n" +
                                            "\n" +
                                            "LEFT JOIN\n" +
                                            "\n" +
                                            "\n" +
                                            "(select * from\n" +
                                            "(SELECT \n" +
                                            "tb_inventaris.kd_inventaris as kd_inventaris,\n" +
                                            "tb_xls_d.*,\n" +
                                            "COUNT(tb_xls_d.kd_kib_d) as jlh\n" +
                                            "FROM tb_xls_d JOIN tb_inventaris\n" +
                                            "ON\n" +
                                            "tb_inventaris.kd_lokasi=tb_xls_d.kd_lokasi AND\n" +
                                            "tb_inventaris.kd_barang=tb_xls_d.kd_barang AND\n" +
                                            "tb_inventaris.kd_reg=tb_xls_d.kd_reg\n" +
                                            "GROUP BY kd_lokasi,kd_barang,kd_reg) as kibd\n" +
                                            "where jlh='1' and manual='0') as tbd\n" +
                                            "ON tbd.kd_inventaris=tbinv.kd_inventaris_real\n" +
                                            "\n" +
                                            "\n" +
                                            "LEFT JOIN\n" +
                                            "\n" +
                                            "(select * from\n" +
                                            "(SELECT \n" +
                                            "tb_inventaris.kd_inventaris as kd_inventaris,\n" +
                                            "tb_xls_e.*,\n" +
                                            "COUNT(tb_xls_e.kd_kib_e) as jlh\n" +
                                            "FROM tb_xls_e JOIN tb_inventaris\n" +
                                            "ON\n" +
                                            "tb_inventaris.kd_lokasi=tb_xls_e.kd_lokasi AND\n" +
                                            "tb_inventaris.kd_barang=tb_xls_e.kd_barang AND\n" +
                                            "tb_inventaris.kd_reg=tb_xls_e.kd_reg\n" +
                                            "GROUP BY kd_lokasi,kd_barang,kd_reg) as kibe\n" +
                                            "where jlh='1' and manual='0') as tbe\n" +
                                            "ON tbe.kd_inventaris=tbinv.kd_inventaris_real\n" +
                                            "\n" +
                                            "\n" +
                                            "LEFT JOIN\n" +
                                            "\n" +
                                            "\n" +
                                            "(select * from\n" +
                                            "(SELECT \n" +
                                            "tb_inventaris.kd_inventaris as kd_inventaris,\n" +
                                            "tb_xls_f.*,\n" +
                                            "COUNT(tb_xls_f.kd_kib_f) as jlh\n" +
                                            "FROM tb_xls_f JOIN tb_inventaris\n" +
                                            "ON\n" +
                                            "tb_inventaris.kd_lokasi=tb_xls_f.kd_lokasi AND\n" +
                                            "tb_inventaris.kd_barang=tb_xls_f.kd_barang AND\n" +
                                            "tb_inventaris.kd_reg=tb_xls_f.kd_reg\n" +
                                            "GROUP BY kd_lokasi,kd_barang,kd_reg) as kibf\n" +
                                            "where jlh='1' and manual='0') as tbf\n" +
                                            "ON tbf.kd_inventaris=tbinv.kd_inventaris_real\n" +
                                            "\n" +
                                            "\n" +
                                            "LEFT JOIN\n" +
                                            "\n" +
                                            "\n" +
                                            "(select * from\n" +
                                            "(SELECT \n" +
                                            "tb_inventaris.kd_inventaris as kd_inventaris,\n" +
                                            "tb_xls_lainnya.*,\n" +
                                            "COUNT(tb_xls_lainnya.kd_kib_lainnya) as jlh\n" +
                                            "FROM tb_xls_lainnya JOIN tb_inventaris\n" +
                                            "ON\n" +
                                            "tb_inventaris.kd_lokasi=tb_xls_lainnya.kd_lokasi AND\n" +
                                            "tb_inventaris.kd_barang=tb_xls_lainnya.kd_barang AND\n" +
                                            "tb_inventaris.kd_reg=tb_xls_lainnya.kd_reg\n" +
                                            "GROUP BY kd_lokasi,kd_barang,kd_reg) as kiblainnya\n" +
                                            "where jlh='1' and manual='0') as tblainnya\n" +
                                            "ON tblainnya.kd_inventaris=tbinv.kd_inventaris_real) as newInv\n" +
                                            "\n" +
                                            "ON newInv.kd_app=tb_inventaris.kd_inventaris";
}
