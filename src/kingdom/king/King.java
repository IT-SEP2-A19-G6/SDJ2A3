package kingdom.king;

import kingdom.catalog.Catalog;
import kingdom.treasueroom.TreasureRoom;
import kingdom.valuables.Valuable;

import java.util.ArrayList;
import java.util.Random;

public class King implements Runnable {

    private TreasureRoom treasureRoom;

    public King(TreasureRoom treasureRoom) {
        this.treasureRoom = treasureRoom;
    }

    @Override
    public void run() {

        while(true) {

            try {
                Catalog c = Catalog.getInstance();
                Thread.sleep(new Random().nextInt(10000) + 2000 ); // min 2 sec max 12 sec

                ArrayList<Valuable> valuables = new ArrayList<>();
                int valuableSum = 0;
                int partyBudget = new Random().nextInt(100) + 100;
                c.write(this, "the kings new party budget is whole " + partyBudget);



                treasureRoom.acquireWrite(this);

                for (int i = 0; i < treasureRoom.getValuableCount(); i++) {
                    Valuable valuable = treasureRoom.getRandomValuable(this);
                    Thread.sleep(500);
                    valuables.add(valuable);
                    valuableSum += valuable.getValue();
                    if (valuableSum >= partyBudget) {
                        break;
                    }
                }

                if (valuableSum >= partyBudget) {
                    c.write(this, "The king collected enough kingdom.valuables to hold a party.");
                    displayParty();
                    c.write(this, "The king has the best party in the entire kingdom!!!!!!");
                } else  {
                    // party canceled
                    c.write(this, "The king did NOT have enough kingdom.valuables(" + treasureRoom.getValuableCount() + " items with the total worth of " + valuableSum + ") in the treasure room to hold the party worth " + partyBudget + ". The king is sad.");

                    if (valuables.size() > 0) {
                        // putting the stuff back.
                        c.write(this, "The king is putting all the items back in the treasure room because its not party time anyway.");
                        for (Valuable v : valuables) {
                            treasureRoom.addValuable(this, v);
                            Thread.sleep(100);
                        }
                    }
                }

                treasureRoom.releaseWrite(this);

                c.write(this, "Maybe the king can hold a party tomorrow. The king goes to sleep.... ZzzZzzZZzzZZ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void displayParty() {
        Catalog c = Catalog.getInstance();
        c.write(this, "---------------------------------------------------------");
        c.write(this, "------------------ THE KING PARTY HARD ------------------");
        c.write(this, "---------------------------------------------------------");
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

        c.write(this, msg);
        c.write(this, "---------------------------------------------------------");
        c.write(this, "---------------------------------------------------------");
    }
}
