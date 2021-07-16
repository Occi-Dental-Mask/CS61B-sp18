import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    CharacterComparator obn3 = new OffByN(3);
    CharacterComparator obn0 = new OffByN(0);
    CharacterComparator obn_1 = new OffByN(-1);

    @Test
    public void testEqualChars(){
        assertFalse(obn3.equalChars('a', 'b'));
        assertTrue(obn3.equalChars('a', 'd'));
        assertTrue(obn0.equalChars('a', 'a'));
        assertTrue(obn_1.equalChars('y', 'z'));
    }
}
