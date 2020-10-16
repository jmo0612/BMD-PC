/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BMDPC;

import com.thowo.jmjavaframework.db.JMConnection;
import com.thowo.jmjavaframework.db.JMDBMySQL;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Regina
 */
public class GitIgnoreDBConnection {
    public static JMDBMySQL getMySqlConDB(){
        //return new JMDBMySQL("remotemysql.com","3306","8vbZvACR5B","8vbZvACR5B","N01E4WgIFN");
        return new JMDBMySQL("192.168.1.26","3306","bmd","admin","puprino4J4");
    }
    public static File getSQLiteConDB(){
        //return new File("SQLITE_FILE_PATH");
        return null;
    }
    public static JMConnection mySQLConnection(){
        return new JMConnection(getMySqlConDB());
    }
    public static JMConnection mySQLiteConnection(){
        return new JMConnection(getSQLiteConDB());
    }
    
    public static List<Object> getDBs(){
        List<Object> ret= new ArrayList();
        ret.add(getSQLiteConDB());
        ret.add(getMySqlConDB());
        return ret;
    }
}