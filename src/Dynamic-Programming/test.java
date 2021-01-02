import java.util.Arrays;
import java.util.Comparator;

public class test {
    public static boolean isMatch(String s, String p) {
        // dp[r][c] is whether s is matching p or not when s[0...r] and p[0...c]
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        // initial dp:
        // s = " " p = " "
        dp[0][0] = true;
        // p = " "
        for(int r = 1; r <= s.length(); r++){
            dp[r][0] = false;
        }
        // s = " "
        for(int c = 1; c <= p.length(); c++){
            dp[0][c] = (p.charAt(c - 1) == '*') ? dp[0][c - 2] : false;
        }

        // traverse each elements in s
        for(int r = 1; r <= s.length(); r++){
            // traverse each elements in p
            for(int c = 1; c <= p.length(); c++){
                // the character at 'r' in s is as same as it at 'c' in p,
                // or the character at 'c' in p is '.' or '*'
                if(s.charAt(r - 1) == p.charAt(c - 1) || p.charAt(c - 1) == '.' || p.charAt(c - 1) == '*'){
                    dp[r][c] = dp[r - 1][c - 1];
                }else{
                    dp[r][c] = false;
                }
            }
        }
        return dp[s.length()][p.length()];
    }


}
