import org.jetbrains.annotations.NotNull;

import static org.junit.Assert.assertEquals;

public class Palindrome<T> extends LinkedListDeque<T> {
    public Palindrome() {
        super();
    }

    public Deque<Character> wordToDeque(String word) {
        int i = 0;
        Deque<Character> a = new Palindrome<>();
        int length = word.length();
        for (i = 0; i < length; i++) {
            char added = word.charAt(i);//from stackoverflow
            a.addLast(added);
        }
        return a;
    }

    /**
     * To find whether the word passed in is palindrome.
     */
    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> wDeque = wordToDeque(word);
        String reversedWord = helper(wDeque, word);

        if (word.equals(reversedWord)) {//字符串不能直接用==比较
            return true;
        }
        return false;

    }

    private static String helper(Deque<Character> wD, String word) {
        String actual = "";
        for (int i = 0; i < word.length(); i++) {
            actual += wD.removeLast();
        }
        return actual;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wD = wordToDeque(word);
        String reversed = helper(wD, word);
        int i = 0;
        int middle = -1;
        if (word.length() % 2 != 0) {
            middle = word.length() / 2;
        }
        while (i < word.length()) {
            if (i == middle) {
                i++;
                continue;
            } else {
                if (cc.equalChars(word.charAt(i), reversed.charAt(i)) == false) {
                    return false;
                }
            }
            i++;
        }
        return true;
    }
}

