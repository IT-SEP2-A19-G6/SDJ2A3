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

            // Acquire read access
            treasureRoom.acquireRead(this);

            // Simulate calculating takes time
            sleep(5000, 18000);

            int totalSum = treasureRoom.getValueOfTreasureRoom(this);
            catalog.write(this, "The total sum of valuables: " + totalSum);
            displayChestTotalValue();

            // Release read access
            treasureRoom.releaseRead(this);
            sleep(5000, 10000);

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
        String message = "" +
                "         __________\n" +
                "        /\\____;;___\\\n" +
                "       | /         /\n" +
                "       `. ())oo() .\n" +
                "        |\\(%()*^^()^\\\n" +
                "       %| |-%-------|\n" +
                "      % \\ | %  ))   |\n" +
                "      %  \\|%________|\n" +
                "       %%%%\n";

        System.out.println(message);

    }
}