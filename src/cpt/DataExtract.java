package cpt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class DataExtract {
    private List<DataBase> data;

    public DataExtract() {
         //create buffered reader for stats file
         try {
            BufferedReader br = new BufferedReader(new FileReader("src/cpt/spi_global_rankings_intl.csv"));
            String line =  null;
   
             //ArrayList used for containing data
             List<DataBase> data = new ArrayList<DataBase>();
             int j = 0;
   
             //while there are more rows to go through
             while((line = br.readLine()) != null){
                //split the given line by the commas that separate them
                String[] str = line.split(",");
    
                //convert array into an arraylist
                DataBase dataBase = new DataBase();
                if(j > 0) {
                  for(int i = 0; i < str.length; i++) {
                     if(!str[i].equals("")) {
                        dataBase.add(str[i]);
      
                     } else {
                        dataBase.add("-1");
                     }
                     
                     
                  }

                  //add data into arraylist
                  data.add(dataBase);

                }

                j++;
                
                
             }
    
             this.data = data;

         } catch (Exception e) {
            System.out.println("d");
         }
         
        
   }

   public DataBase get(int i) {
      return data.get(i);
   }

   



   
   
 

   
}
