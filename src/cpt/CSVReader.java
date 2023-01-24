package cpt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static List<String[]> readCSV(String fileName) {
        List<String[]> data = new ArrayList<>();
        String line;
        // buffered reader 
        try (BufferedReader br = new BufferedReader(new FileReader("src/cpt/DataBase.java"))) {
            while ((line = br.readLine()) != null) {
                String[] lineData = line.split(",");
                data.add(lineData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}

