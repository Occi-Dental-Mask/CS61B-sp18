import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    /** Test if a word is palindrome
     *  palindrome is an object based on Palindrome class
     */
    @Test
    public void testIsPalindrome() {
        assertEquals(false, palindrome.isPalindrome("cat"));
        assertEquals(true, palindrome.isPalindrome("noon"));
        assertEquals(true, palindrome.isPalindrome(""));
        assertEquals(true, palindrome.isPalindrome("a"));

        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertFalse(palindrome.isPalindrome("noon", obo));
        assertTrue(palindrome.isPalindrome("n", obo));

    }



}
