package cpt;

/**
 * CSVReader class to extract data from the spi_global_rankings.csv file
 * 
 * @author J. Wang
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    /**
     * @param fileName
     * @return
     */
    public ArrayList<DataBase> read(String fileName) {
        // ArrayList to store data
        ArrayList<DataBase> data = new ArrayList<>();
        String line;
        
        // buffered reader
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

             // While loop checks whether the next line to be scanned exists
            while ((line = br.readLine()) != null) {

                // breaks value after every comma
                String[] values = line.split(",");

                int rank = Integer.parseInt(values[0]);
                String name = values[1];
                Double off = Double.parseDouble(values[2]);
                Double def = Double.parseDouble(values[3]);
                Double spi = Double.parseDouble(values[4]);
 
                // adds new objets to arraylist
                data.add(new DataBase(rank, name, off, def, spi));
              
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
 
 
    }

 