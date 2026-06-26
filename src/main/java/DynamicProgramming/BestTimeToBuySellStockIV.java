package DynamicProgramming;

import java.util.*;

/**
 * Problem: 188. Best Time to Buy and Sell Stock IV
 * ------------------------------------------------
 * You are given an integer array prices where prices[i] is the price of a given stock on the i-th day,
 * and an integer k. Find the maximum profit you can achieve. You may complete at most k transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously
 * (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 * Input  : k = 2, prices = [2,4,1]
 * Output : 2
 *
 * Example 2:
 * Input  : k = 2, prices = [3,2,6,5,0,3]
 * Output : 7
 *
 * Constraints:
 * - 1 <= k <= 100
 * - 1 <= prices.length <= 1000
 * - 0 <= prices[i] <= 1000
 *
 * Approaches Implemented:
 * 1. Optimal Dynamic Programming Approach (O(n*k), O(n*k))
 * 2. Brute Force Approach (O(n^2 * k), O(1))
 */
public class BestTimeToBuySellStockIV {

    /**
     * Algorithm 1: Optimal Dynamic Programming
     * ----------------------------------------
     * Idea:
     * - Use DP table where dp[t][d] = max profit up to day d with at most t transactions.
     * - Transition:
     *   dp[t][d] = max(dp[t][d-1], prices[d] + max_{m<d}(dp[t-1][m] - prices[m]))
     * - Optimize inner loop using a running maxDiff = max(dp[t-1][m] - prices[m]).
     *
     * Steps:
     * 1. Initialize dp[0][d] = 0 for all d.
     * 2. For each transaction t from 1..k:
     *    - Track maxDiff.
     *    - For each day d:
     *      dp[t][d] = max(dp[t][d-1], prices[d] + maxDiff).
     *      Update maxDiff = max(maxDiff, dp[t-1][d] - prices[d]).
     *
     * Time Complexity: O(n*k)
     * Space Complexity: O(n*k) (can be optimized to O(n))
     * Concept: DP with transaction states.
     */
    public int maxProfitOptimal(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0) return 0;

        // If k >= n/2, it's equivalent to unlimited transactions
        if (k >= n / 2) {
            int profit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }

        int[][] dp = new int[k + 1][n];
        for (int t = 1; t <= k; t++) {
            int maxDiff = -prices[0];
            for (int d = 1; d < n; d++) {
                dp[t][d] = Math.max(dp[t][d - 1], prices[d] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[t - 1][d] - prices[d]);
            }
        }
        return dp[k][n - 1];
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * - Try all possible split points for transactions.
     * - For each transaction count up to k, compute max profit by recursion.
     * - Very inefficient for large n.
     *
     * Time Complexity: O(n^2 * k)
     * Space Complexity: O(1)
     * Concept: Recursive enumeration of transactions.
     */
    public int maxProfitBrute(int k, int[] prices) {
        return helper(prices, 0, k);
    }

    private int helper(int[] prices, int start, int k) {
        if (k == 0 || start >= prices.length) return 0;
        int maxProfit = 0;
        for (int buy = start; buy < prices.length; buy++) {
            int maxSellProfit = 0;
            for (int sell = buy + 1; sell < prices.length; sell++) {
                if (prices[sell] > prices[buy]) {
                    int profit = prices[sell] - prices[buy] + helper(prices, sell + 1, k - 1);
                    maxSellProfit = Math.max(maxSellProfit, profit);
                }
            }
            maxProfit = Math.max(maxProfit, maxSellProfit);
        }
        return maxProfit;
    }
}
