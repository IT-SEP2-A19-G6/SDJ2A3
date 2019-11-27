package kingdom.treasueroom;

import kingdom.catalog.Catalog;
import kingdom.valuables.Valuable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class TreasureRoom  implements AccessRight{
    private ArrayList<Valuable> valuables;
    private Queue<String> queue;
    private String writer = "Writer";
    private String reader = "Reader";
    private int activeReaders;
    private boolean isWriting;
    private int roomValue;

    public TreasureRoom() {
        this.valuables = new ArrayList<>();
        this.queue = new LinkedList<>();
        activeReaders = 0;
        isWriting = false;
    }


    @Override
    public synchronized void acquireRead(Object name) {
        Catalog c = Catalog.getInstance();
        queue.add(reader);
        c.write(this, name.getClass().getSimpleName() + " wants to look at the treasures");
        while (queue.element().equals(writer) || isWriting){
            try {
                c.write(this, name.getClass().getSimpleName() + " is waiting to enter the room...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        activeReaders++;
        queue.poll();
        c.write(this, name.getClass().getSimpleName() + " is given access to the room!");
    }

    @Override
    public synchronized void releaseRead(Object name) {
        Catalog c = Catalog.getInstance();
        c.write(this, name.getClass().getSimpleName() + " has left the treasures");
        activeReaders--;
        if (activeReaders == 0){
            c.write(this, name.getClass().getSimpleName() + " releases the room to updates");
            notifyAll();
        }
    }


    @Override
    public synchronized void acquireWrite(Object name) {
        Catalog c = Catalog.getInstance();
        queue.add(writer);
        c.write(this, "The " + name.getClass().getSimpleName() + " wants to enter the treasure room!");
        while (activeReaders > 0 || queue.element().equals(reader) || isWriting){
            try {
                c.write(this, name.getClass().getSimpleName() + " is waiting to enter the treasure room");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isWriting = true;
        queue.poll();
        c.write(this, name.getClass().getSimpleName() + " is now allowed to enter the treasure room.");
    }

    @Override
    public synchronized void releaseWrite(Object name) {
        Catalog c = Catalog.getInstance();
        c.write(this, name.getClass().getSimpleName() + " is leaving the treasure room.");
        c.write(this, "--------------------------------------------");
        c.write(this, "The treasure room now contains " + valuables.size() + " items worth " + roomValue);
        c.write(this, "--------------------------------------------");
        isWriting = false;
        notifyAll();
    }

    public void addValuable(Object name, Valuable valuable){
        roomValue += valuable.getValue();
        valuables.add(valuable);
        Catalog.getInstance().write(this, name.getClass().getSimpleName() + " added " + valuable.getType() + " to the treasure room. Total is now: " + roomValue);
    }

    public Valuable getValuable(Object name, Valuable valuable){
        Valuable valuableToReturn = null;
        if (valuables.size() > 0) {
            for (Valuable valuableInList : valuables){
                if (valuableInList.equals(valuable)){
                    valuableToReturn = valuableInList;
                    Catalog.getInstance().write(this, name.getClass().getSimpleName() + " found " + valuable.getType());
                    break;
                }
            }
        }
        return valuableToReturn;
    }

    public Valuable getRandomValueable(Object name) {
        if (valuables.size() > 0) {
            return removeValuable(name, valuables.get(new Random().nextInt(valuables.size())));
        }
        return null;
    }

    public int getValuableCount() {
        return valuables.size();
    }

    public Valuable removeValuable(Object name, Valuable valuable){
        Valuable valuableToReturn = null;
        if (valuables.size() > 0){
            for (Valuable valuableInList : valuables){
                if (valuableInList.equals(valuable)){
                    valuableToReturn = valuableInList;
                    Catalog.getInstance().write(this, name.getClass().getSimpleName() + " removed " + valuable.getType() + " from the treasure room");
                    break;
                }
            }
            roomValue -= valuableToReturn.getValue();
            valuables.remove(valuableToReturn);
        }
        return valuableToReturn;
    }

<<<<<<< HEAD
    public int getValueOfTreasureRoom(String name){
        System.out.println(name + " has calculated the inventory of " + valuables.size() + " items to a total value of " + roomValue);
=======
    public int getValueOfTreasureRoom(Object name){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Catalog c = Catalog.getInstance();
        c.write(this, name.getClass().getSimpleName() + " has calculated the inventory of " + valuables.size() + " items to a total value of " + roomValue);

>>>>>>> master
        return roomValue;
    }

    //For testing purposes
    public int getActiveReaders(){
        return activeReaders;
    }

    public  boolean getIsWriting(){
        return isWriting;
    }

}
