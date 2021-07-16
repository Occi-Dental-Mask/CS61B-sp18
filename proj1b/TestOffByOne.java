import org.junit.Test;
import static org.junit.Assert.*;
public class TestOffByOne {

    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars(){
        assertTrue(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('a', 'z'));
        assertFalse(offByOne.equalChars('a', 'a'));
    }
}
