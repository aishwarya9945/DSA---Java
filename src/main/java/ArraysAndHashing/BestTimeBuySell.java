package ArraysAndHashing;

/**
 * Program to calculate the maximum profit from buying and selling stock
 * exactly once, using both Optimal (Single Pass) and Brute Force approaches.
 *
 * Problem:
 * Given an array where each element is the stock price on a given day,
 * find the maximum profit that can be achieved by buying on one day
 * and selling on another day after the buy.
 * (You must buy before you sell. Only one buy and one sell allowed.)
 *
 * Example:
 * Input  : [7, 1, 5, 3, 6, 4]
 * Output : 5   // Buy on day 1 (price=1), sell on day 4 (price=6)
 */
public class BestTimeBuySell {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};

        // ---------- Optimal Approach ----------
        int optimalProfit = maxProfitOptimal(prices);
        System.out.println("Maximum Profit (Optimal): " + optimalProfit);

        // ---------- Brute Force Approach ----------
        int bruteProfit = maxProfitBrute(prices);
        System.out.println("Maximum Profit (Brute Force): " + bruteProfit);
    }

    /**
     * Algorithm 1: Optimal Approach (Single Pass)
     * -------------------------------------------
     * Idea:
     * Iterate over prices while tracking the minimum price so far and the
     * maximum profit achievable by selling at each price.
     *
     * Steps:
     * - Initialize min to the first price.
     * - Initialize profit to 0.
     * - Loop through each day's price:
     *      - If current price < min, update min.
     *      - Otherwise, calculate profit = current price - min.
     *      - Update max profit if new profit is greater.
     * - Return the maximum profit found.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Concept: Greedy single pass, dynamic tracking of min and max profit.
     */
    public static int maxProfitOptimal(int[] prices) {
        int min = prices[0];   // Tracks the minimum price to buy so far
        int profit = 0;        // Tracks the maximum profit found

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];  // Update min if current price is lower
            }

            // Calculate profit for selling at today’s price and update max if higher
            profit = Math.max(profit, prices[i] - min);
        }
        return profit;  // Return the best profit found
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Try every possible buy-sell pair where buy comes before sell,
     * and track the maximum profit obtained.
     *
     * Steps:
     * - Initialize maxProfit to 0.
     * - For every buy day i:
     *      - For every sell day j > i:
     *          - Compute profit = prices[j] - prices[i].
     *          - Update maxProfit if this profit is higher.
     * - Return maxProfit.
     *
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     * Concept: Exhaustive pair comparison, no optimization.
     */
    public static int maxProfitBrute(int[] prices) {
        int maxProfit = 0;  // Stores the best profit

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];      // Profit if we buy at i and sell at j
                if (profit > maxProfit) {
                    maxProfit = profit;                  // Update with better profit if found
                }
            }
        }
        return maxProfit;   // Return the maximum profit possible
    }
}
