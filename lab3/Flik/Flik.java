import org.junit.Test;

import static org.junit.Assert.assertTrue;

/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        return a == b;
    }
    @Test
    public void equalTest() {
        Boolean Bool_1 = isSameNumber(1,1);
        Boolean bool2 = isSameNumber(129,129);
        assertTrue(Bool_1);
        assertTrue(bool2);
    }

}
