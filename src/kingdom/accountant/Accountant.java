package kingdom.accountant;

import kingdom.catalog.Catalog;
import kingdom.treasueroom.TreasureRoom;

import java.util.Random;

public class Accountant implements Runnable {

    private TreasureRoom treasureRoom;

    public Accountant(TreasureRoom treasureRoom) {
        this.treasureRoom = treasureRoom;
    }

    @Override
    public void run() {
        while(true) {
            Catalog catalog = Catalog.getInstance();

            sleep(10000, 25000);
            // Acquire read access
            treasureRoom.acquireRead(this);

            // Simulate calculating takes time
            sleep(2000, 4000);

            int totalSum = treasureRoom.getValueOfTreasureRoom(this);
            catalog.write(this, "has counted the total sum of valuables, which is: " + totalSum);
            displayChestTotalValue();

            // Release read access
            treasureRoom.releaseRead(this);

        }
    }

    private void sleep(int minSleep, int maxSleep) {
        try {
            Thread.sleep(new Random().nextInt(maxSleep) + minSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void displayChestTotalValue() {
        String message = "\n" +
                "         __________\n" +
                "        /\\____;;___\\\n" +
                "       | /         /\n" +
                "       `. ())oo() .\n" +
                "        |\\(%()*^^()^\\\n" +
                "       %| |-%-------|\n" +
                "      % \\ | %  ))   |\n" +
                "      %  \\|%________|\n" +
                "       %%%%\n";

        Catalog.getInstance().write(this, message);

    }
}