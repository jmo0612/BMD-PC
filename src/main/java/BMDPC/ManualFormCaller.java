/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BMDPC;

import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.table.JMTable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jimi
 */
public class ManualFormCaller implements DoubleInterface{
    private List<String> tbAutoKeys=new ArrayList();
    private List<String> tbManualKeys=new ArrayList();
    private JMTable auto=null;
    private JMTable manual=null;
    
    
    private void showInterface(String xlsType){
        new FormManual(null,true,this,xlsType).setVisible(true);
    }
    
    public void setResult(List<String> tbAutoKeys, List<String> tbManualKeys){
        this.tbAutoKeys=tbAutoKeys;
        this.tbManualKeys=tbManualKeys;
    }
    
    public JMTable getAutoTable(){
        return this.auto;
    }
    
    public JMTable getManualTable(){
        return this.manual;
    }

    @Override
    public boolean getSync(String tableName) {
        String q="select tb_inventaris.*,tb_xls_inventaris.tahun as tahun \n" +
                    "from tb_inventaris JOIN tb_xls_inventaris\n" +
                    "ON tb_inventaris.kd_xls_inventaris=tb_xls_inventaris.kd_inventaris\n" +
                    "where tb_xls_inventaris.manual='1'\n" +
                    "order by tahun desc, uraian asc";
        if(q.equals(""))return false;
        this.manual=JMTable.create(q, JMTable.DBTYPE_MYSQL);
        if(!this.manual.isEmpty()){
            List<Integer> thns=new ArrayList();
            do{
                Integer thn=this.manual.getCurrentRow().getCells().get(this.manual.getCurrentRow().getCells().size()-1).getValueInteger();
                if(!this.exist(thns, thn))thns.add(thn);
            }while(this.manual.nextRow(false)!=null);
            String fltr="";
            if(thns.size()>0){
                for(Integer thn:thns){
                    if(fltr.equals("")){
                        fltr="tahun='"+thn+"'";
                    }else{
                        fltr+=" OR tahun='"+thn+"'";
                    }
                }
            }
            if(!fltr.equals("")){
                String qAuto="select tb_inventaris.*,tb_xls_inventaris.tahun as tahun \n" +
                            "from tb_inventaris JOIN tb_xls_inventaris\n" +
                            "ON tb_inventaris.kd_xls_inventaris=tb_xls_inventaris.kd_inventaris\n" +
                            "where tb_xls_inventaris.manual='0'\n" +
                            " AND ("+fltr+")\n" +
                            "order by tahun desc, uraian asc";
                JMFunctions.trace(qAuto);
                if(!qAuto.equals("")){
                    this.auto=JMTable.create(qAuto, JMTable.DBTYPE_MYSQL);
                    if(!this.auto.isEmpty()){
                        this.showInterface(tableName);
                        if(this.tbAutoKeys.size()>0){
                            List<String> qUpdates=QueryHelper.q06ManualUpdate(this.tbAutoKeys, this.tbManualKeys);
                            boolean success=true;
                            for(String qUpdate:qUpdates){
                                if(success){
                                    success=JMFunctions.getCurrentConnection().queryUpdateMySQL(qUpdate, false);
                                    //JMFunctions.trace(qUpdate+"\n\n\n");
                                    //success=true;
                                }else{
                                    return false;
                                }
                            }
                            return success;
                        }
                    }else{
                        return true;
                    }
                }
            }
        }else{return true;}
        return false;
    }
    private boolean exist(List<Integer> list, Integer data){
        if(list==null)return false;
        for(Integer l:list){
            if(l.intValue()==data.intValue()){
                JMFunctions.trace(l+","+data+" : SAMA");
                return true;
            }else{
                JMFunctions.trace(l+","+data+" : BEDA");
            }
        }
        return false;
    }
}
