package cpt;

public class DataBase {
    // Instance variables
    private int rank;
    private String name;
    private String confed;
    private double off;
    private double def;
    private double spi;

    
    public DataBase(int rank, String name, String confed, Double off, Double def, Double spi) {
        this.rank = rank;
        this.name = name;
        this.confed = confed;
        this.off = off;
        this.def = def;
        this.spi = spi;
    }
 
    // getter methods
 
 
    public int getRank(){
        return this.rank;
    }
 
 
    public String getName(){
        return this.name;
    }
 
 
    public String getConfed(){
        return this.confed;
    }
 
 
    public double getOff(){
        return this.off;
    }
 
 
    public double getDef(){
        return this.def;
    }
 
 
    public double getSpi(){
        return this.spi;
    }
 
}
