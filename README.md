#ElementRanking
1) Find Largest Element in an Array
Approach: use a linear scan to compare each element with the current maximum.
- Initialize a variable largest with the first element of the array.
- Iterate through the array starting from the second element.
- Compare each element with largest:
- If the current element is greater, update largest.
- After the loop ends, largest holds the maximum value.
- Return or print the result.

Time Complexity: O(n) — we visit each element once.
Space Complexity: O(1) — no extra space used beyond a few variables.

Why This Is Efficient:
- No sorting or extra data structures.
- Works for any size of array.
- Handles negative numbers and duplicates.


