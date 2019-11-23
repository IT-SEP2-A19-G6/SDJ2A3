package kingdom.king;

import kingdom.taxcollector.TaxCollector;
import treasueroom.TreasureRoom;
import valuables.Valuable;

import java.util.ArrayList;
import java.util.Random;

public class King implements Runnable {

    private TreasureRoom treasureRoom;
    private TaxCollector taxCollector;

    public King(TreasureRoom treasureRoom, TaxCollector taxCollector) {
        this.treasureRoom = treasureRoom;
        this.taxCollector = taxCollector;
    }

    @Override
    public void run() {
        while(true) {

            try {

                Thread.sleep(new Random().nextInt(10000) + 2000 ); // min 2 sec max 12 sec

                ArrayList<Valuable> valuables = new ArrayList<>();
                int valuableSum = 0;
                int partyBudget = new Random().nextInt(100) + 100;
                System.out.println("the kings new party budget is whole " + partyBudget);



                treasureRoom.acquireWrite(getClass().getSimpleName());

                for (int i = 0; i < treasureRoom.getValuableCount(); i++) {
                    Valuable valuable = treasureRoom.getRandomValueable(getClass().getSimpleName());
                    Thread.sleep(500);
                    valuables.add(valuable);
                    valuableSum += valuable.getValue();
                    if (valuableSum >= partyBudget) {
                        break;
                    }
                }

                if (valuableSum >= partyBudget) {
                    System.out.println("The king collected enough valuables to hold a party.");
                    displayParty();
                    System.out.println("The king has the best party in the entire kingdom!!!!!!");
                } else  {
                    // party canceled
                    System.out.println("The king did NOT have enough valuables(" + treasureRoom.getValuableCount() + " items with the total worth of " + valuableSum + ") in the treasure room to hold the party worth " + partyBudget + ". The king is sad.");
                    if (valuables.size() > 0) {
                        // putting the stuff back.
                        System.out.println("The king is putting all the items back in the treasure room because its not party time anyway.");
                        for (Valuable v : valuables) {
                            treasureRoom.addValuable(getClass().getSimpleName(), v);
                            Thread.sleep(100);
                        }
                    }
                }

                treasureRoom.releaseWrite(getClass().getSimpleName());

                System.out.println("Maybe the king can hold a party tomorrow. The king goes to sleep.... ZzzZzzZZzzZZ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void displayParty() {
        System.out.println("---------------------------------------------------------");
        System.out.println("------------------ THE KING PARTY HARD ------------------");
        System.out.println("---------------------------------------------------------");
        String msg = "" +
                "                                                   (\"\\\n" +
                "           /)                                       ) \\\n" +
                "          (^)                                      /   \\\n" +
                "          ,i.                                     (  ^  )\n" +
                "        __|_|__              ^                     \\/_\\/\n" +
                "       '_______'             (^)             ,---.__  |\n" +
                "        ,%y^\\\\`             ,i.             |      `-^-.__,-.\n" +
                "       (/// _\\\\)            | |             |    ,%y^\\\\`    |\n" +
                "      (((( -  )))           | |             |   (/// .\\\\))  |\n" +
                "      ((|)_*_/(((      _____|_|_____        |  (((( ^ ))))  |\n" +
                "      ))(/) (\\((|)    \".___________.\"       | (((|)_*_/((() |\n" +
                "     ((((\\___/))(\\       \\y ,--.y/          |  )))))  ()))) |\n" +
                "    / ,-)     (-. \\      /,---. )\\          |/  ,   |   .  \\|\n" +
                "   ( ( ( _, ._ ) ) )    / (((\\\\)\\ \\         /  (*   ^   *)  \\\n" +
                "    \\ \\ )     ( / /     \\ \\\\-_/ / /        /  /|`--\" `--\"|\\  \\\n" +
                "     ) y       y (       \\ i   i /        / .\" ,--. . ,--. \". \\\n" +
                "     \\(         )/        (_)=(_)      __/ /'-/(   \\ /   )\\-'\\ \\__\n" +
                "       \\_______/           ) . (      /--.,  (  \\   y   /  )  `.,--\\\n" +
                "        \\|/|  /           /\\---/\\             \\  \"./  ,\"  /\n" +
                "         /-^-/           /  )-(  \\             `--/  /\\--\"\n" +
                "        (   X           /  /   \\  \\              /  _) \\\n" +
                "         \\  \\          / ,\"     \". \\            mm/  (_ \\ we want a\n" +
                "          \\  y-._     / /         \\ \\                  \\_b  `69\n" +
                "          |\\,' X-'   /-)           (-\\\n" +
                "        _,T-)  /    / ^!           !^ \\\n" +
                "       (__,-%_/";

        System.out.println(msg);
        System.out.println("---------------------------------------------------------");
        System.out.println("-------------- Scroll up to see his party ---------------");
        System.out.println("---------------------------------------------------------");
    }
}
