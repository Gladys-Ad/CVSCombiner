
import java.util.ArrayList;

/**
 *
 * @author gladysadjei
 */

public class CVSFile {
    //temp list to store rows from cvsfile
    private ArrayList <String> rows = new ArrayList(); 
    
    private String name = null;
    
    //constructor
    public CVSFile(){
    }
    //get rows of data from the cvs file
    public ArrayList<String> getRows(){
        return rows;
    }
    //input read row from cvs file into the temp list
    public void addRow(String row){
        rows.add(row);
    }
    
    public void setFileName(String name){
        this.name = name;
    }
    
    public String getFileName(){
        return name;
    }
}
