

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author gladysadjei
 */

/*
Write a command line program that takes several CSV files as arguments. 
Each CSV file will have the same columns. 
Your script should output a new CSV file to stdout that contains the rows 
from each of the inputs along with an additional column that has the filename 
from which the row came. Use "filename" as the header for the additional column.
*/
public class CVSCombiner {

//queue to hold files that have been read
private Queue <CVSFile> cvsFileQueue = new LinkedList();

public Queue getFileQueue(){
    return cvsFileQueue;
}

public void insertFile(CVSFile file){
    cvsFileQueue.add(file);
}

public CVSFile getFile(){
    return cvsFileQueue.poll();
}

//constructor for CVSCombiner
public CVSCombiner(){
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        //create an instance of a CVSCombiner
        CVSCombiner cvsCombiner = new CVSCombiner();
        //initiate reading files
        BufferedReader buffer = null;
        String fileRow = null;
        
        //go through all the cvs files passed in as arguments
        for(int i = 0; i<args.length;i++){
            //create instance of cvsfile
            CVSFile cvsFile = new CVSFile();
            try {
                File cvs = new File(args[i]);
                
                //getfile name for new column
                cvsFile.setFileName(cvs.getName());
                buffer = new BufferedReader(new FileReader(args[i]));
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
            
            
            
            //read file contents as long as there is content left to be read
            while((fileRow = buffer.readLine()) != null){
                cvsFile.addRow(fileRow);
            }
            
            //insert cvs file into the queue once it has been read
            cvsCombiner.insertFile(cvsFile);
        }
        
        if(!(cvsCombiner.getFileQueue().isEmpty())){
            CVSFile columnName = (CVSFile)cvsCombiner.getFileQueue().peek();
            System.out.println(columnName.getRows().get(0)+",\"filename\"");
        }
        
        //output new cvs file
        while(!(cvsCombiner.getFileQueue().isEmpty())){
            
            //get each file from the queue
            CVSFile newCVS = cvsCombiner.getFile();
            
            //for each file, get all the rows
            for(int i =1;i<newCVS.getRows().size();i++){
                System.out.println(newCVS.getRows().get(i)+",\""+newCVS.getFileName() +"\"");
            }
        }
        
        
    }
    
}
