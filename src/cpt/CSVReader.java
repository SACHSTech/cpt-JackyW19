package cpt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


import java.io.IOException;

/**
 * A class to read data from spi_global_rankings csv file 
 */
public class CSVReader {
    public ArrayList<DataBase> read(String fileName) {
        ArrayList<DataBase> data = new ArrayList<>();
        String line;
        // buffered reader
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
 
 
                String[] values = line.split(",");
                int rank = Integer.parseInt(values[0]);
                String name = values[1];
                Double off = Double.parseDouble(values[2]);
                Double def = Double.parseDouble(values[3]);
                Double spi = Double.parseDouble(values[4]);
 
 
                // Create new object and add to arraylist
                data.add(new DataBase(rank, name, off, def,  spi));
              
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
 
 
    }

 