/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BMDPC;

import com.thowo.jmjavaframework.JMFunctions;
import com.thowo.jmjavaframework.table.JMRow;
import com.thowo.jmjavaframework.table.JMTable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jimi
 */
public class DoubleFormCaller implements DoubleInterface{
    private List<String> tbInvKeys=new ArrayList();
    private List<String> tbXlsKeys=new ArrayList();
    private JMTable inv=null;
    private JMTable xls=null;
    
    
    private void showInterface(String xlsType){
        new FormDouble(null,true,this,xlsType).setVisible(true);
    }
    
    public void setResult(List<String> tbInvKeys, List<String> tbXlsKeys){
        this.tbInvKeys=tbInvKeys;
        this.tbXlsKeys=tbXlsKeys;
    }
    
    public JMTable getInvTable(){
        return this.inv;
    }
    
    public JMTable getXlsTable(){
        return this.xls;
    }

    @Override
    public boolean getSync(String tableName) {
        String q=QueryHelper.q02Double(tableName);
        if(q.equals(""))return false;
        this.xls=JMTable.create(q, JMTable.DBTYPE_MYSQL);
        if(!this.xls.isEmpty()){
            List<String> kdDups=new ArrayList();
            do{
                String kdDup=this.xls.getCurrentRow().getCells().get(this.xls.getCurrentRow().getCells().size()-1).getDBValue();
                if(!this.exist(kdDups, kdDup))kdDups.add(kdDup);
            }while(this.xls.nextRow(false)!=null);
            String fltr="";
            if(kdDups.size()>0){
                for(String kdDup:kdDups){
                    if(fltr.equals("")){
                        fltr="kdDup='"+kdDup+"'";
                    }else{
                        fltr+=" OR kdDup='"+kdDup+"'";
                    }
                }
            }
            if(!fltr.equals("")){
                String qInv=QueryHelper.q03DoubleInv(tableName, fltr);
                if(!qInv.equals("")){
                    this.inv=JMTable.create(qInv, JMTable.DBTYPE_MYSQL);
                    if(!this.inv.isEmpty()){
                        this.showInterface(tableName);
                        if(this.tbInvKeys.size()>0){
                            String qUpdate=QueryHelper.q04DoubleInvUpdate(tableName, this.tbInvKeys, this.tbXlsKeys);
                            if(!qUpdate.equals(""))return JMFunctions.getCurrentConnection().queryUpdateMySQL(qUpdate, false);
                        }
                    }else{
                        return true;
                    }
                }
            }
        }else{return true;}
        return false;
    }
    private boolean exist(List<String> list, String data){
        if(list==null)return false;
        for(String l:list){
            if(l.equals(data))return true;
        }
        return false;
    }
    
}
