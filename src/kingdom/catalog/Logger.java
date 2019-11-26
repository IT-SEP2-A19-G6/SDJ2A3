package kingdom.catalog;

import kingdom.valuables.Valuable;

import java.util.ArrayList;

public class Logger implements Runnable{
/*The last class is logging functionality, to keep track of the income and outcome of the kingdom. Use the Singleton pattern here.
Use this logger-class to log out to the console, what happens in your program.
E.g. when and how much a TaxCollector adds to the TreasureRoom,
when the King holds a party or cancels it, when the Accountants has calculated the wealth
in the TreasureRoom. You may also include logs when one of them are waiting to enter the TreasureRoom*/

    private String logEntry;
    private ArrayList<Valuable> valuables;

    public Logger(String logEntry){
        this.logEntry=logEntry;
        valuables = new ArrayList<>();

        Catalog.getInstance().write("Tax collector has");

    }

    public String getLogEntry() {
        return logEntry;
    }

    public void run(){
        try{
            Thread.sleep(3000);
            while(true){
                Catalog catalog = Catalog.getInstance();
                catalog.write("Tax collector has received " + valuables.get(0).getMaterial() +
                        " with value of: " + valuables.get(0).getValue());
                takeValuables();
            }
        }catch (InterruptedException e) {
         e.printStackTrace();
        }
    }

    public ArrayList<Valuable> takeValuables() {
        Catalog c = Catalog.getInstance();
        c.write( valuables.size() + " owned valuables which is being taken by the tax collector");
        valuables.clear();

        return valuables;
    }

    public void kingSpends() {
        Catalog catalog = Catalog.getInstance();
        catalog.write("King has taken all valuables from treasury");

        valuables.clear();
    }

}
