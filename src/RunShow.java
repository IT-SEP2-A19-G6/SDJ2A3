import treasueroom.TreasureRoom;
import valuables.Valuable;
import valuables.ValuableFactory;
import valuables.ValuableMaterials;



public class RunShow {
    public static void main(String[] args) {
        TreasureRoom treasureRoom = new TreasureRoom();
        ValuableMaterials materials = new ValuableMaterials();
        String accountant = "accountant";
        String king = "King";
        String taxCollector = "tax collector";


        // below is only for testing and showing some methods -- feel free to delete!!!!!!!!!!!!!!!!
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                int finalI = i;
                new Thread(() -> {
                    treasureRoom.acquireWrite(taxCollector + " " + finalI);
                    for (int k = 0; k < 10; k++) {
                        treasureRoom.addValuable(taxCollector + " " + finalI, ValuableFactory.getValuable(materials.getRandomValuable()));
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    treasureRoom.releaseWrite(taxCollector + " " + finalI);
                }).start();
            }

        }).start();


        new Thread(() -> {
            treasureRoom.acquireWrite(king);
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    treasureRoom.removeValuable(king, ValuableFactory.getValuable(materials.getRandomValuable()));
                } catch (Exception e) {
                    if (e instanceof NullPointerException){
                        System.out.println("Valuable not found in the treasure room");
                    }
                }
                treasureRoom.releaseWrite(king);
            }
        }).start();

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                treasureRoom.acquireRead(accountant + " " + finalI);
                treasureRoom.getValueOfTreasureRoom(accountant + " " + finalI);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                treasureRoom.releaseRead(accountant + " " + finalI);
            }).start();
        }



    }
}
