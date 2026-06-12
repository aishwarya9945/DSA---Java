package BinarySearch;

import java.util.*;

/**
 * Program to design a Time Based Key-Value Store.
 * Implements:
 * 1. Optimal Binary Search Approach for get() (O(log n))
 * 2. Brute Force Linear Search Approach for get() (O(n))
 *
 * Problem:
 * Design a data structure that supports:
 *  - set(key, value, timestamp): Stores the key with the value at the given timestamp.
 *  - get(key, timestamp): Returns a value such that set(key, value, t) was called previously,
 *    with t <= timestamp, and returns the value associated with the largest such t.
 *    If there is no such value, return "".
 *
 * Example:
 * Input:
 * TimeMap kv = new TimeMap();
 * kv.set("foo", "bar", 1);
 * kv.get("foo", 1); // "bar"
 * kv.get("foo", 3); // "bar"
 * kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // "bar2"
 * kv.get("foo", 5); // "bar2"
 */
public class TimeBasedKeyValueStore {

    public static void main(String[] args) {
        TimeMap kv = new TimeMap();

        // ---------- Example Usage ----------
        kv.set("foo", "bar", 1);
        System.out.println("Get at t=1 (Optimal): " + kv.getOptimal("foo", 1)); // bar
        System.out.println("Get at t=3 (Optimal): " + kv.getOptimal("foo", 3)); // bar

        kv.set("foo", "bar2", 4);
        System.out.println("Get at t=4 (Optimal): " + kv.getOptimal("foo", 4)); // bar2
        System.out.println("Get at t=5 (Optimal): " + kv.getOptimal("foo", 5)); // bar2

        // Brute Force demonstration
        System.out.println("Get at t=5 (Brute Force): " + kv.getBrute("foo", 5)); // bar2
    }
}

class TimeMap {
    private Map<String, List<Pair>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList<>()); // ensures timeline exists.
        map.get(key).add(new Pair(value, timestamp)); // append.
    }

    /**
     * Algorithm 1: Optimal Binary Search Approach
     * -------------------------------------------
     * Idea:
     * Each key stores a list of (value, timestamp) pairs in increasing timestamp order.
     * Use binary search to find the largest timestamp <= given timestamp.
     *
     * Time Complexity: O(log n) per get
     * Space Complexity: O(n)
     */
    public String getOptimal(String key, int timestamp) {
        if (!map.containsKey(key)) return "";// “If this key doesn’t exist in the dictionary,
                                            // there’s nothing to return. Just give back an empty string.”

        List<Pair> list = map.get(key); // “Otherwise, fetch the timeline (list of value/timestamp pairs) for this key.”
        int left = 0, right = list.size() - 1;
        String result = "";

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).timestamp <= timestamp) { // “Is this timestamp valid (not greater than the one we’re asking for)?”
                result = list.get(mid).value; // “Yes, it’s valid. Save this value as the best candidate so far.”
                left = mid + 1; // search right for closer timestamp
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    /**
     * Algorithm 2: Brute Force Linear Search
     * --------------------------------------
     * Idea:
     * Traverse the list of pairs for the key and find the latest timestamp <= given timestamp.
     *
     * Time Complexity: O(n) per get
     * Space Complexity: O(n)
     */
    public String getBrute(String key, int timestamp) {
        if (!map.containsKey(key)) return "";

        List<Pair> list = map.get(key);
        String result = "";
        for (Pair p : list) {
            if (p.timestamp <= timestamp) {
                result = p.value;
            } else {
                break;
            }
        }
        return result;
    }
}

class Pair {
    String value;
    int timestamp;

    public Pair(String value, int timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }
}
