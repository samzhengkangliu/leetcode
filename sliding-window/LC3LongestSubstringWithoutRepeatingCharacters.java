import java.util.*;

/**
 * Given a string s, find the length of the longest substring without repeating
 * characters.
 * 
 */

public class LC3LongestSubstringWithoutRepeatingCharacters {
    /**
     * 枚举这个字符串的所有子串；
     * 对于每一个子串都判断一下这个子串是否有重复字符；
     * 在从没有重复字符的所有子串中找出长度最长的那个，返回即可。
     * 
     * Time: O(N^3)
     * Space: O(N)
     */
    public int lengthOfLongestSubstringBF(String s) {
        int n = s.length();

        if (n <= 1)
            return n;

        int maxLen = 1;

        for (int left = 0; left < n; left++) {
            for (int right = left; right < n; right++) {
                if (isUniqueSubstring(s, left, right)) {
                    maxLen = Math.max(maxLen, right - left + 1);
                }
            }
        }

        return maxLen;
    }

    private boolean isUniqueSubstring(String s, int left, int right) {
        Set<Character> set = new HashSet<>();
        for (int i = left; i <= right; i++) {
            if (set.contains(s.charAt(left))) {
                return false;
            }
            set.add(s.charAt(left));
        }

        return true;
    }

    /**
     * 定义一个 map 数据结构存储 (k, v)，其中 key 值为字符，value 值为字符位置 +1，加 1 表示从字符位置后一个才开始不重复
     * 我们定义不重复子串的开始位置为 left，结束位置为 right
     * 随着 right 不断遍历向后，会遇到与 [left, right] 区间内字符相同的情况，此时将字符作为 key 值，获取其 value
     * 值，并更新left，此时 [left, right] 区间内不存在重复字符
     * 无论是否更新 left，都会更新其 map 数据结构和结果 ans。
     * 
     * Time: O(n)
     * Space: O(s) s is the length of the string
     */

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();

        if (n <= 1)
            return n;

        int res = 0;

        Map<Character, Integer> map = new HashMap<>();
        for (int right = 0, left = 0; right < n; right++) {
            char current = s.charAt(right);
            if (map.containsKey(current)) {
                // update left to current index + 1
                left = Math.max(left, map.get(current) + 1);
            }

            res = Math.max(res, right - left + 1);
            map.put(current, right);
        }

        return res;
    }
}