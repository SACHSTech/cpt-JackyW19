package cpt;

public class GlobalRanking {
    // initializing variablesrank,name,confed,off,def,spi
    private int rank;
    private String name;
    private String confed;
    private int off;
    private int def;
    private int spi;
    
    // constructor
    public GlobalRanking(int rank, String name, String confed, int off, int def, int spi){
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

    public int getOff(){
        return this.off;
    }

    public int getDef(){
        return this.def;
    }

    public int getSpi(){
        return this.spi;
    }    

}

