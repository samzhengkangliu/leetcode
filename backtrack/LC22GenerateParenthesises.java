package backtrack;

import java.util.*;

public class LC22GenerateParenthesises {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();

        if (n == 0) {
            return res;
        }

        dfs("", n, n, res);

        return res;
    }

    /**
     * 
     * @param current current string result
     * @param left    number of left brackets remained
     * @param right   number of right brackets remained
     * @param res     result
     */
    private void dfs(String current, int left, int right, List<String> res) {
        // used up all the brackets, add the current result to final result list
        if (left == 0 && right == 0) {
            res.add(current);
            return;
        }

        // prune the division when remaining right bracket is smaller than left ones
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(current + "(", left - 1, right, res);
        }

        if (right > 0) {
            dfs(current + ")", left, right - 1, res);
        }
    }
}
