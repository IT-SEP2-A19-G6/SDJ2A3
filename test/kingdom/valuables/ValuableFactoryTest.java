package kingdom.valuables;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValuableFactoryTest {

    @Test
    void getSameBronzeValuable() {
        Valuable bronze = ValuableFactory.getRandomBronzeValuable();
        Valuable bronzeToTest = ValuableFactory.getRandomBronzeValuable();
        assertEquals(bronze, bronzeToTest);
    }

    @Test
    void getSameBrassValuable() {
        Valuable brass = ValuableFactory.getRandomBrassValuable();
        Valuable brassToTest = ValuableFactory.getRandomBrassValuable();
        assertEquals(brass, brassToTest);
    }

    @Test
    void getSameCopperValuable() {
        Valuable copper = ValuableFactory.getRandomCopperValuable();
        Valuable copperToTest = ValuableFactory.getRandomCopperValuable();
        assertEquals(copper, copper);
    }

    @Test
    void getSameSilverValuable() {
        Valuable silver = ValuableFactory.getRandomSilverValuable();
        Valuable silverToTest = ValuableFactory.getRandomSilverValuable();
        assertEquals(silver, silverToTest);
    }

    @Test
    void getSameGoldValuable() {
        Valuable gold = ValuableFactory.getRandomGoldValuable();
        Valuable goldToTest = ValuableFactory.getRandomGoldValuable();
        assertEquals(gold, goldToTest);
    }

    @Test
    void getSameEmeraldValuable() {
        Valuable emerald = ValuableFactory.getRandomEmeraldValuable();
        Valuable emeraldToTest = ValuableFactory.getRandomEmeraldValuable();
        assertEquals(emerald, emeraldToTest);
    }

    @Test
    void getSameRubyValuable() {
        Valuable ruby = ValuableFactory.getRandomRubyValuable();
        Valuable rubyToTest = ValuableFactory.getRandomRubyValuable();
        assertEquals(ruby, rubyToTest);
    }

    @Test
    void getSameDiamondValuable() {
        Valuable diamond = ValuableFactory.getRandomDiamondValuable();
        Valuable diamondToTest = ValuableFactory.getRandomDiamondValuable();
        assertEquals(diamond, diamondToTest);
    }

    @Test
    void getRandomValuable(){
        Valuable valuable = ValuableFactory.getRandomValuable();
        Valuable valuableToTest = ValuableFactory.getRandomValuable();
        //There is a small chance that the same randomValuableType is returned
        while (valuableToTest.equals(valuable)){
            valuableToTest = ValuableFactory.getRandomValuable();
        }
        assertNotEquals(valuable, valuableToTest);
    }
}