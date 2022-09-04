import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class JavaSolution {

    @Test
    public void testCase01() {
        String input = "ababcbacadefegdehijhklij";

    }

    //Function to find the smallest window in the string s consisting
    //of all the characters of string p.
    public static String smallestWindow(String s, String p)
    {
        // Your code here
        int[] count = new int[128];
        int required = p.length();
        int bestLeft = -1;
        int minLength = s.length() + 1;

        for (final char c : p.toCharArray()) {
            ++count[c];
        }

        for (int l = 0, r = 0; r < s.length(); ++r) {
            if (--count[s.charAt(r)] >= 0)
                --required;
            while (required == 0) {
                if (r - l + 1 < minLength) {
                    bestLeft = l;
                    minLength = r - l + 1;
                }
                if (++count[s.charAt(l++)] > 0)
                    ++required;
            }
        }

        return bestLeft == -1 ? "-1" : s.substring(bestLeft, bestLeft + minLength);
    }
}