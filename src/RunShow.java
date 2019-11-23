import kingdom.commoners.CommonerType;
import kingdom.commoners.Person;
import kingdom.king.King;
import kingdom.taxcollector.TaxCollector;
import treasueroom.TreasureRoom;
import valuables.Valuable;
import valuables.ValuableFactory;
import valuables.ValuableMaterials;

import java.util.Random;


public class RunShow {
    public static void main(String[] args) {



        // create the kingdom
        TreasureRoom treasureRoom = new TreasureRoom();



        // Create the tax collector and start collecting
        TaxCollector taxCollector = new TaxCollector(treasureRoom, 100);
        new Thread(taxCollector).start();

        // Create the king
        King king = new King(treasureRoom, taxCollector);
        new Thread(king).start();

        int maxDistance = 100;
        // create commoners to work for valuables
        for (int i = 0; i < 20; i++) {
            //maxDistance = i;
            Person p = new Person(CommonerType.values()[new Random().nextInt(9)], maxDistance);
            taxCollector.addCommoner(p);
            new Thread(p).start();
        }




//        TreasureRoom treasureRoom = new TreasureRoom();
//        ValuableMaterials materials = new ValuableMaterials();
//        String accountant = "accountant";
//        String king = "King";
//        String taxCollector = "tax collector";
//
//
//        // below is only for testing and showing some methods -- feel free to delete!!!!!!!!!!!!!!!!
//        new Thread(() -> {
//            for (int i = 0; i < 30; i++) {
//                int finalI = i;
//                new Thread(() -> {
//                    treasureRoom.acquireWrite(taxCollector + " " + finalI);
//                    for (int k = 0; k < 10; k++) {
//                        treasureRoom.addValuable(taxCollector + " " + finalI, ValuableFactory.getValuable(materials.getRandomValuable()));
//                        try {
//                            Thread.sleep(300);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    treasureRoom.releaseWrite(taxCollector + " " + finalI);
//                }).start();
//            }
//
//        }).start();
//
//
//        new Thread(() -> {
//            treasureRoom.acquireWrite(king);
//            for (int i = 0; i < 10; i++) {
//                try {
//                    Thread.sleep(500);
//                    treasureRoom.removeValuable(king, ValuableFactory.getValuable(materials.getRandomValuable()));
//                } catch (Exception e) {
//                    if (e instanceof NullPointerException){
//                        System.out.println("Valuable not found in the treasure room");
//                    }
//                }
//                treasureRoom.releaseWrite(king);
//            }
//        }).start();
//
//        for (int i = 0; i < 100; i++) {
//            int finalI = i;
//            new Thread(() -> {
//                treasureRoom.acquireRead(accountant + " " + finalI);
//                treasureRoom.getValueOfTreasureRoom(accountant + " " + finalI);
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                treasureRoom.releaseRead(accountant + " " + finalI);
//            }).start();
//        }



    }
}
