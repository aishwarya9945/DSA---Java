package Greedy;

import java.util.*;

/**
 * Problem: 122. Best Time to Buy and Sell Stock II
 * ------------------------------------------------
 * You are given an integer array prices where prices[i] is the price of a given stock on the i-th day.
 *
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
 * However, you can sell and buy the stock multiple times on the same day, ensuring you never hold more than one share.
 *
 * Return the maximum profit you can achieve.
 *
 * Example 1:
 * Input  : prices = [7,1,5,3,6,4]
 * Output : 7
 * Explanation: Buy on day 2 (price=1), sell on day 3 (price=5), profit=4.
 *              Buy on day 4 (price=3), sell on day 5 (price=6), profit=3.
 *              Total profit = 7.
 *
 * Example 2:
 * Input  : prices = [1,2,3,4,5]
 * Output : 4
 * Explanation: Buy on day 1, sell on day 5, profit=4.
 *
 * Example 3:
 * Input  : prices = [7,6,4,3,1]
 * Output : 0
 * Explanation: No profitable transactions.
 *
 * Constraints:
 * - 1 <= prices.length <= 3 * 10^4
 * - 0 <= prices[i] <= 10^4
 *
 * Approaches Implemented:
 * 1. Optimal Greedy Approach (O(n), O(1))
 * 2. Brute Force Approach (O(n^2), O(1))
 */
public class BestTimeToBuySellStockII {

    /**
     * Algorithm 1: Optimal Greedy Approach
     * ------------------------------------
     * Idea:
     * - Profit can be accumulated by summing all positive differences between consecutive days.
     * - If prices[i+1] > prices[i], we "buy at i and sell at i+1".
     * - This works because multiple transactions are allowed.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Greedy accumulation of local profits.
     */
    public int maxProfitOptimal(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * - Try all possible buy/sell combinations.
     * - Track maximum profit achievable.
     * - Inefficient for large arrays.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * Concept: Simple enumeration of transactions.
     */
    public int maxProfitBrute(int[] prices) {
        int n = prices.length;
        int maxProfit = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (prices[j] > prices[i]) {
                    maxProfit = Math.max(maxProfit, prices[j] - prices[i] + maxProfitBrute(Arrays.copyOfRange(prices, j + 1, n)));
                }
            }
        }
        return maxProfit;
    }
}
