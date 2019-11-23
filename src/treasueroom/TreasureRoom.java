package treasueroom;

import valuables.Valuable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


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
    public synchronized void acquireRead(String name) {
        queue.add(reader);
        System.out.println(name + " wants to look at the treasures");
        if (queue.element().equals(writer) || isWriting){
            try {
                System.out.println(name + " is waiting to enter the room...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        activeReaders++;
        queue.poll();
        System.out.println(name + " is given access to the room!");
    }

    @Override
    public synchronized void releaseRead(String name) {
        System.out.println(name + " has left the treasures");
        activeReaders--;
        if (activeReaders == 0){
            System.out.println(name + " releases the room to updates");
            notifyAll();
        }
    }

    @Override
    public synchronized void acquireWrite(String name) {
        queue.add(writer);
        System.out.println(name + " wants change the contents af the treasure room!");
        if (activeReaders > 0 || queue.element().equals(reader) || isWriting){
            try {
                System.out.println(name + " is waiting for the doors to be opened");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isWriting = true;
        queue.poll();
        System.out.println(name + " is handling his business!!");
    }

    @Override
    public synchronized void releaseWrite(String name) {
        System.out.println(name + " has updated the inventory");
        isWriting = false;
        notifyAll();
    }

    public void addValuable(String name, Valuable valuable){
        System.out.println(name + " added " + valuable.getType());
        roomValue += valuable.getValue();
        valuables.add(valuable);
    }

    public Valuable getValuable(String name, Valuable valuable){
        Valuable valuableToReturn = null;
        if (valuables.size() > 0) {
            for (Valuable valuableInList : valuables){
                if (valuableInList.equals(valuable)){
                    valuableToReturn = valuableInList;
                    System.out.println(name + " found " + valuable.getType());
                    break;
                }
            }
        }
        return valuableToReturn;
    }

    public Valuable removeValuable(String name, Valuable valuable){
        Valuable valuableToReturn = null;
        if (valuables.size() > 0){
            for (Valuable valuableInList : valuables){
                if (valuableInList.equals(valuable)){
                    valuableToReturn = valuableInList;
                    System.out.println(name + " removed " + valuable.getType());
                    break;
                }
            }
            roomValue -= valuableToReturn.getValue();
            valuables.remove(valuableToReturn);
        }
        return valuableToReturn;
    }

    public int getValueOfTreasureRoom(String name){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " has calculated the inventory of " + valuables.size() + " items to a total value of " + roomValue);
        return roomValue;
    }

}
