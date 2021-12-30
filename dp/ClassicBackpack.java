package dp;

public class ClassicBackpack {
    /**
     * 有 N 个重量和价值分别为w_i和v_i的物品。从这些物品中挑选总重量不超过W的物品，每个物品只有一件，求所有挑选方案中价值总和的最大值。
     * 
     * N = 4
     * w = [4, 3, 1, 1]
     * v = [300, 200, 150, 200]
     * W = 5
     * 
     * 
     * 1.dp[i][j] 表示下标区间 [0...i]内所有物品，且重量总和不超过j的情况下，背包能装下物品的最大价值总和
     * 2. 状态转移方程 dp[i][j] = max(dp[i-1][j], dp[i-1][j-w[i]] + v[i], i >= 1, j >=
     * w[i])
     * 
     * 对于下标为 i 的物品，有「选」和「不选」两种方案，比较这两种方案选出更好的。
     * 
     * 下标为 i 的物品没有被选择时，问题转化成求物品的下标区间 [0.. i - 1] 里，选出重量总和不超过 j 的最大价值总和，即
     * dp[i - 1][j] 为所求，这里 i >= 1；
     * 
     * 下标为 i 的物品被选择时，则问题转化成求物品的下标区间 [0.. i - 1] 里，选出重量总和不超过 j - w[i] 的最大价值总
     * 和，即 dp[i- 1][j - w[i]] + v[i] 为所求，这里 i >= 1，并且 j - w[i] >= 0 即 w[i] <=
     * j，也就是说，下标
     * 为 i 的物品的重量 w(i) 要 小于等于 当前考虑的背包承重 j ，状态转移才能发生。
     * 
     * Time: O(NW)
     * Space: O(NW)
     */

    public static int backpack01(int W, int[] weights, int[] values) {
        int n = weights.length;
        if (n == 0)
            return 0;

        int[][] dp = new int[n][W + 1];

        // Initialize with the first item
        for (int j = 1; j <= W; j++) {
            if (weights[0] <= j) {
                dp[0][j] = values[0];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= W; j++) {
                dp[i][j] = dp[i - 1][j];
                if (weights[i] <= j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weights[i]] + values[i]);
                }
            }
        }

        return dp[n - 1][W];
    }

    public static void main(String[] args) {
        int res = backpack01(5, new int[] { 4, 3, 1, 1 }, new int[] { 300, 200, 150, 200 });

        System.out.println(res);
    }
}
