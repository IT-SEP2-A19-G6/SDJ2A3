package kingdom.treasueroom;

import kingdom.valuables.Valuable;
import kingdom.valuables.ValuableFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TreasureRoomTest {
    private TreasureRoom treasureRoom;
    private String reader;
    private String writer;

    @BeforeEach
    void setUp() {
        treasureRoom = new TreasureRoom();
        reader = "reader";
        writer = "writer";
    }


    @Test
    void getReaderAccess(){
        assertEquals(0, treasureRoom.getActiveReaders());
        treasureRoom.acquireRead(reader);
        assertEquals(1, treasureRoom.getActiveReaders());
    }

    @Test
    void removeReaderAccess(){
        assertEquals(0, treasureRoom.getActiveReaders());
        treasureRoom.acquireRead(reader);
        assertEquals(1, treasureRoom.getActiveReaders());
        treasureRoom.releaseRead(reader);
        assertEquals(0, treasureRoom.getActiveReaders());
    }


    @Test
    void multiReaderAccess(){
        int nrOfReaders = 5;
        assertEquals(0, treasureRoom.getActiveReaders());
        for (int i = 0; i < nrOfReaders; i++) {
            treasureRoom.acquireRead(reader);
        }
        assertEquals(nrOfReaders, treasureRoom.getActiveReaders());
        for (int i = 0; i < nrOfReaders; i++) {
            treasureRoom.releaseRead(reader);
        }
        assertEquals(0, treasureRoom.getActiveReaders());
    }

    @Test
    void getWriterAccess(){
        assertFalse(treasureRoom.getIsWriting());
        treasureRoom.acquireWrite(writer);
        assertTrue(treasureRoom.getIsWriting());
    }

    @Test
    void removeWriterAccess(){
        assertFalse(treasureRoom.getIsWriting());
        treasureRoom.acquireWrite(writer);
        assertTrue(treasureRoom.getIsWriting());
        treasureRoom.releaseWrite(writer);
        assertFalse(treasureRoom.getIsWriting());
    }

    @Test
    void addOneValuableToTreasureRoom(){
        Valuable valuable = ValuableFactory.getRandomValuable();
        assertEquals(0, treasureRoom.getValuableCount());
        treasureRoom.addValuable(writer, valuable);
        assertEquals(1, treasureRoom.getValuableCount());
        assertEquals(valuable, treasureRoom.getValuable(reader, valuable));
    }


    @Test
    void addNoOfValuableToTreasureRoom(){
        int nrOfValuables = 5;
        int totalValue = 0;
        for (int i = 0; i < nrOfValuables; i++) {
            Valuable valuable = ValuableFactory.getRandomValuable();
            totalValue += valuable.getValue();
            treasureRoom.addValuable(writer, valuable);
        }
        assertEquals(nrOfValuables, treasureRoom.getValuableCount());
    }

    @Test
    void getValueOfTreasureRoom(){
        int nrOfValuables = 5;
        int totalValue = 0;
        for (int i = 0; i < nrOfValuables; i++) {
            Valuable valuable = ValuableFactory.getRandomValuable();
            totalValue += valuable.getValue();
            treasureRoom.addValuable(writer, valuable);
        }
        assertEquals(nrOfValuables, treasureRoom.getValuableCount());
        assertEquals(totalValue, treasureRoom.getValueOfTreasureRoom(reader));
    }


    @Test
    void removeValuable(){
        Valuable valuable = ValuableFactory.getRandomValuable();
        treasureRoom.addValuable(writer, valuable);
        Valuable valuableFromRoom = treasureRoom.removeValuable(writer, valuable);
        assertEquals(valuable, valuableFromRoom);
    }

}