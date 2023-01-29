package cpt;

/**
 * DataBase class is to store information on each data point
 * 
 * @author J. Wang
 */

public class DataBase {
    // Instance variables
    private int rank;
    private String name;
    private double off;
    private double def;
    private double spi;

    /**
     * Constructer
     * @param rank rank of the team
     * @param name name of the team
     * @param off offensive rating of the team
     * @param def defensive rating of the team
     * @param spi spi rating of the team
     */
    public DataBase(int rank, String name, Double off, Double def, Double spi) {
        this.rank = rank;
        this.name = name;
        this.off = off;
        this.def = def;
        this.spi = spi;
    }
 
    /**
     * Getter method for the rank variable
     * @return rank
     */
    public int getRank(){
        return rank;
    }
 

    /**
     * Getter method for the name variable
     * @return name
     */
    public String getName(){
        return name;
    }
 
    /**
     * Getter method for the off variable
     * @return off
     */
    public double getOff(){
        return off;
    }
 
 
    /**
     * Getter method for the def variable
     * @return def
     */
    public double getDef(){
        return def;
    }
 
 
    /**
     * Getter method for the spi variable
     * @return spi
     */
    public double getSpi(){
        return spi;
    }
    
 
}
