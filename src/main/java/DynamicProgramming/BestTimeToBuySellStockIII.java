package DynamicProgramming;

/**
 * Problem: 123. Best Time to Buy and Sell Stock III
 * -------------------------------------------------
 * You are given an array prices where prices[i] is the price of a given stock on the i-th day.
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously
 * (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 * Input  : prices = [3,3,5,0,0,3,1,4]
 * Output : 6
 * Explanation: Buy on day 4 (price=0), sell on day 6 (price=3), profit=3.
 *              Buy on day 7 (price=1), sell on day 8 (price=4), profit=3.
 *              Total profit = 6.
 *
 * Example 2:
 * Input  : prices = [1,2,3,4,5]
 * Output : 4
 *
 * Example 3:
 * Input  : prices = [7,6,4,3,1]
 * Output : 0
 *
 * Constraints:
 * - 1 <= prices.length <= 10^5
 * - 0 <= prices[i] <= 10^5
 *
 * Approaches Implemented:
 * 1. Optimal Dynamic Programming Approach (O(n), O(1))
 * 2. Brute Force Approach (O(n^2), O(1))
 */
public class BestTimeToBuySellStockIII {

    /**
     * Algorithm 1: Optimal Dynamic Programming
     * ----------------------------------------
     * Idea:
     * - Track four states:
     *   1. firstBuy  = max profit after first buy
     *   2. firstSell = max profit after first sell
     *   3. secondBuy = max profit after second buy
     *   4. secondSell= max profit after second sell
     * - Update states as we iterate prices.
     *
     * Transitions:
     * - firstBuy  = max(firstBuy, -price)
     * - firstSell = max(firstSell, firstBuy + price)
     * - secondBuy = max(secondBuy, firstSell - price)
     * - secondSell= max(secondSell, secondBuy + price)
     *
     * Answer = secondSell
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: DP state compression.
     */
    public int maxProfitOptimal(int[] prices) {
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;

        for (int price : prices) {
            firstBuy = Math.max(firstBuy, -price);
            firstSell = Math.max(firstSell, firstBuy + price);
            secondBuy = Math.max(secondBuy, firstSell - price);
            secondSell = Math.max(secondSell, secondBuy + price);
        }
        return secondSell;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * - Split the array into two parts at every index.
     * - Compute max profit for one transaction in left part.
     * - Compute max profit for one transaction in right part.
     * - Take maximum sum across all splits.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * Concept: Try all possible split points.
     */
    public int maxProfitBrute(int[] prices) {
        int n = prices.length;
        int maxProfit = 0;

        for (int split = 0; split < n; split++) {
            int leftProfit = maxProfitOneTransaction(prices, 0, split);
            int rightProfit = maxProfitOneTransaction(prices, split + 1, n - 1);
            maxProfit = Math.max(maxProfit, leftProfit + rightProfit);
        }
        return maxProfit;
    }

    // Helper: max profit with one transaction in [start, end]
    private int maxProfitOneTransaction(int[] prices, int start, int end) {
        if (start >= end) return 0;
        int minPrice = prices[start];
        int maxProfit = 0;

        for (int i = start + 1; i <= end; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return maxProfit;
    }
}
