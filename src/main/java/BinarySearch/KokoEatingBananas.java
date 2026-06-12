package BinarySearch;

/**
 * Program to find the minimum eating speed at which Koko can eat all bananas within h hours.
 * Implements:
 * 1. Optimal Binary Search Approach (O(n log(maxPile)))
 * 2. Brute Force Approach (O(n * maxPile))
 *
 * Problem:
 * Koko loves to eat bananas. There are n piles of bananas, the i-th pile has piles[i] bananas.
 * The guards have gone and will come back in h hours.
 * Koko can decide her eating speed of k bananas per hour.
 * Each hour, she chooses a pile and eats up to k bananas from that pile.
 * If the pile has fewer than k bananas, she eats all of them and does not eat more bananas that hour.
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 * Example:
 * Input  : piles = [3,6,7,11], h = 8
 * Output : 4
 */
public class KokoEatingBananas {

    public static void main(String[] args) {
        int piles[] = {3, 6, 7, 11};
        int h = 8;

        // ---------- Optimal Approach ----------
        int optimalSpeed = minEatingSpeedOptimal(piles, h);
        System.out.println("Minimum Eating Speed (Optimal): " + optimalSpeed);

        // ---------- Brute Force Approach ----------
        int bruteSpeed = minEatingSpeedBrute(piles, h);
        System.out.println("Minimum Eating Speed (Brute Force): " + bruteSpeed);
    }

    /**
     * Algorithm 1: Optimal Binary Search Approach
     * -------------------------------------------
     * Idea:
     * The minimum speed is between 1 and max(piles).
     * Use binary search to find the smallest speed that allows Koko to finish within h hours.
     *
     * Steps:
     * 1. Set left = 1, right = max(piles).
     * 2. While left < right:
     *      - mid = (left + right) / 2
     *      - If Koko can finish at speed mid → right = mid
     *      - Else → left = mid + 1
     * 3. Return left.
     *
     * Time Complexity: O(n log(maxPile))
     * Space Complexity: O(1)
     */
    public static int minEatingSpeedOptimal(int[] piles, int h) {
        // Find max pile size using a loop
        int maxPile = Integer.MIN_VALUE;
        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }

        int left = 1, right = maxPile;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(piles, h, mid)) {
                right = mid; // try smaller speed
            } else {
                left = mid + 1; // need faster speed
            }
        }
        return left;
    }

    private static boolean canFinish(int[] piles, int h, int speed) {
        int hours = 0;
        for (int pile : piles) {
            // ceil(pile/speed) using integer math //ceil = (a + b - 1) / b -> Formula ensures rounding up.
            //Ceil Division Trick
            hours += (pile + speed - 1) / speed;
        }
        return hours <= h;
    }

    /**
     * Algorithm 2: Brute Force Approach
     * ---------------------------------
     * Idea:
     * Try all possible speeds from 1 to max(piles).
     * Return the first speed that allows Koko to finish within h hours.
     *
     * Time Complexity: O(n * maxPile)
     * Space Complexity: O(1)
     */
    public static int minEatingSpeedBrute(int[] piles, int h) {
        // Find max pile size using a loop
        int maxPile = Integer.MIN_VALUE;
        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }

        for (int speed = 1; speed <= maxPile; speed++) {
            if (canFinish(piles, h, speed)) {
                return speed;
            }
        }
        return maxPile; // worst case
    }
}
