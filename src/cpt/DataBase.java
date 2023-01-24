package cpt;

public class DataBase{
    // initializing variablesrank,name,confed,off,def,spi
    private int rank;
    private String name;
    private String confed;
    private double off;
    private double def;
    private double spi;
    private int addingTag;
    

    /**
     * starts of datapoint tag as 0
     */
    public DataBase() {
        this.addingTag = 0;
    }

    /**
     * adds value to properties based on previously added values
     * @param value
     */
    public void add(String value) {
        switch(addingTag) {
            case 0:
                this.rank = Integer.parseInt(value);
                break;
            case 1:
                this.name = value;
                break;
            case 2:
                this.confed = value;
                break;
            case 3:
                this.off = Double.parseDouble(value);
                break;
            case 4:
                this.def = Double.parseDouble(value);
                break;
            case 5:
                this.spi = Double.parseDouble(value);
                break;
            
        }

        this.addingTag++;
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

