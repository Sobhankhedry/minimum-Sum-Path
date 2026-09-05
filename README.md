# 🔺 Minimum Sum Path

A Java algorithmic project that finds the **minimum-sum path in a triangle of numbers** using a **recursive dynamic programming approach**.

The project not only calculates the minimum possible sum from the top of the triangle to the bottom, but also records the decisions made along the way and prints the corresponding selected path.

---

## 📌 Overview

The problem is based on a triangle of integers where, starting from the top element, at each level you can move to one of the two adjacent elements in the next row.

For example:

```text
        2
       / \
      3   4
     / \ / \
    6  5 7
   / \/ \/ \
  4  1  8  3
```

The goal is to find the path from the top to the bottom with the **minimum possible sum**.

The input triangle used by the project is:

```java
static int triangle[][] = {
    {2},
    {3, 4},
    {6, 5, 7},
    {4, 1, 8, 3}
};
```

The program calculates the minimum sum and then prints the elements belonging to the selected path.

---

# 🎯 Problem Definition

Given a triangular arrangement of numbers, start at:

```text
triangle[0][0]
```

At every row, there are two possible moves:

* Move to the element directly below.
* Move to the adjacent element to the right in the next row.

Formally, from:

```text
(i, j)
```

the two possible next positions are:

```text
(i + 1, j)
(i + 1, j + 1)
```

The objective is to find:

```text
Minimum Path Sum
```

from the top of the triangle to the bottom.

---

# 🧮 Example

For the triangle:

```text
        2
       / \
      3   4
     / \ / \
    6   5   7
   / \ / \ / \
  4   1  8  3
```

One minimum path is:

```text
2 → 3 → 5 → 1
```

Its sum is:

```text
2 + 3 + 5 + 1 = 11
```

Therefore:

```text
Minimum Sum = 11
```

---

# 🧠 Approach

The main algorithm is implemented in:

```java
MinimumSumPath()
```

with the following signature:

```java
public static int MinimumSumPath(
    int[][] triangle,
    int n,
    int i,
    int j
)
```

The algorithm uses **recursion combined with memoization**.

At each position, it recursively calculates the minimum path sum of the two possible children:

```java
int q = MinimumSumPath(triangle, n, i + 1, j);
int p = MinimumSumPath(triangle, n, i + 1, j + 1);
```

The smaller result is then selected.

Conceptually:

```text
                   Current Node
                       │
              ┌────────┴────────┐
              ▼                 ▼
          Down Path        Diagonal Path
              │                 │
              ▼                 ▼
          Calculate           Calculate
            Sum                 Sum
              │                 │
              └────────┬────────┘
                       ▼
                 Choose Minimum
                       │
                       ▼
                  Store Result
```

---

# 🔄 Dynamic Programming

The project uses the array:

```java
static int r[][] = new int[4][4];
```

to store previously calculated results.

Before recursively calculating a state, the program checks:

```java
if(selected[i][j] != 0)
{
    return r[i][j];
}
```

This allows already-computed states to be reused instead of recalculating them.

The general idea is:

```text
Recursive Problem
       │
       ▼
Calculate Subproblems
       │
       ▼
Store Results
       │
       ▼
Reuse Stored Results
       │
       ▼
Minimum Path Sum
```

This is the key dynamic-programming concept demonstrated by the project.

---

# 🏁 Base Case

The recursion reaches the final row when:

```java
if (i == n - 1)
```

At that point there are no more decisions to make.

The current triangle value is therefore returned directly:

```java
selected[i][j] = 10;
return triangle[i][j];
```

The value:

```text
10
```

is used in the `selected` matrix as a marker indicating that the current element is the final element of the selected path.

---

# 🔎 Choosing the Minimum

After calculating the two possible paths:

```java
int q = MinimumSumPath(triangle, n, i + 1, j);
int p = MinimumSumPath(triangle, n, i + 1, j + 1);
```

the algorithm compares them:

```java
if (p > q)
```

If the left/down path has the smaller sum, the current node is marked with:

```java
selected[i][j] = -1;
```

Otherwise:

```java
selected[i][j] = 1;
```

The corresponding value is then added to the current path:

```java
r[i][j] = q + triangle[i][j];
```

or:

```java
r[i][j] = p + triangle[i][j];
```

---

# 🗺️ Path Tracking

One of the interesting aspects of this implementation is that it stores not only the minimum sums but also the decisions required to reconstruct the path.

The matrix:

```java
static int selected[][] = new int[5][5];
```

is used to store these decisions.

The project uses three important markers:

| Value | Meaning                            |
| ----: | ---------------------------------- |
|  `-1` | Continue to `(i + 1, j)`           |
|   `1` | Continue to `(i + 1, j + 1)`       |
|  `10` | Final element of the selected path |

This allows the program to reconstruct the path after calculating the minimum sum.

---

# 🖨️ Path Reconstruction

The method:

```java
PrintSelected()
```

is responsible for printing the selected path.

Its signature is:

```java
private static void PrintSelected(
    int[][] selected,
    int i,
    int j
)
```

If the current marker is:

```java
-1
```

the program prints the current value and continues with:

```java
i + 1, j
```

If the marker is:

```java
1
```

it continues with:

```java
i + 1, j + 1
```

Finally, when the marker is:

```java
10
```

the current value is printed and the recursion terminates.

This produces the actual minimum path rather than only the final sum.

---

# 🔄 Complete Algorithm Flow

```text
              Start
                │
                ▼
          Read Triangle
                │
                ▼
       Start at (0, 0)
                │
                ▼
       Reach Last Row?
          /          \
        No            Yes
        │              │
        ▼              ▼
 Calculate Two       Return
 Possible Paths      Current Value
        │
        ▼
 Compare Path Sums
        │
        ▼
 Choose Smaller Path
        │
        ▼
 Store Minimum in r
        │
        ▼
 Store Decision in selected
        │
        ▼
 Return Minimum Sum
        │
        ▼
 Reconstruct Selected Path
        │
        ▼
       Output
```

---

# 💻 Program Structure

The project is intentionally compact.

The main components are:

### `main()`

Responsible for:

* Defining the input triangle
* Determining its size
* Calling `MinimumSumPath()`
* Printing the minimum sum
* Calling `PrintSelected()` to display the path

### `MinimumSumPath()`

Responsible for:

* Recursive traversal
* Calculating subproblem results
* Comparing the two possible paths
* Memoizing calculated values
* Recording path decisions

### `PrintSelected()`

Responsible for:

* Reading the stored decisions
* Following the selected path
* Printing the values along that path

---

# 🛠️ Technology Stack

| Technology              | Usage                                   |
| ----------------------- | --------------------------------------- |
| **Java**                | Main programming language               |
| **2D Arrays**           | Triangle, memoization and path tracking |
| **Recursion**           | Traversing the triangle                 |
| **Dynamic Programming** | Reusing calculated subproblems          |
| **Console Output**      | Displaying the result and selected path |

---

# 📚 Concepts Demonstrated

This project is primarily focused on algorithm design and dynamic programming.

### Java Concepts

* Two-dimensional arrays
* Static methods
* Recursive methods
* Conditional statements
* Console output
* Primitive data types

### Algorithmic Concepts

* Dynamic Programming
* Memoization
* Recursive problem decomposition
* Minimum path problems
* State representation
* Path reconstruction

---

# ⏱️ Complexity

Let:

```text
n = number of rows in the triangle
```

The number of states in a triangular DP table is proportional to:

```text
1 + 2 + 3 + ... + n
```

which is:

```text
O(n²)
```

Each state performs a constant amount of work after its child states have been computed.

Therefore, the intended dynamic-programming approach has approximately:

### Time Complexity

```text
O(n²)
```

### Space Complexity

The implementation maintains:

* `r` for memoized results
* `selected` for path decisions
* Recursive call stack

Therefore the overall auxiliary storage is approximately:

```text
O(n²)
```

with an additional recursion-stack component.

---

# 🧩 Data Structures

The implementation uses three main two-dimensional arrays.

### 1. Input Triangle

```java
int[][] triangle
```

Stores the original problem data.

Example:

```text
2
3 4
6 5 7
4 1 8 3
```

---

### 2. Memoization Table

```java
int[][] r
```

Stores minimum path sums that have already been calculated.

Conceptually:

```text
r[i][j]
```

represents the best path sum obtainable from the current position.

---

### 3. Selection Table

```java
int[][] selected
```

Stores the decision used for path reconstruction.

```text
-1 → move to (i + 1, j)
 1 → move to (i + 1, j + 1)
10 → end of path
```

---

# 🧪 Example Execution

For the input:

```text
        2
       / \
      3   4
     / \ / \
    6   5   7
   / \ / \ / \
  4   1  8  3
```

The program prints:

```text
Min Sum Path is :
11

The selected path
2
3
5
1
```

The exact console formatting is controlled by the current implementation.

---

# 📁 Project Structure

The repository has a compact Java/IntelliJ structure:

```text
minimum-Sum-Path/
│
├── .idea/
│
├── src/
│   └── Main.java
│
├── MinimumSumPath.iml
│
└── README.md
```

The complete algorithm is currently contained in:

```text
src/Main.java
```

The repository currently contains **4 commits**.

---

# 🚀 Getting Started

## Prerequisites

You need:

* Java JDK
* IntelliJ IDEA or another Java IDE

---

## Clone the Repository

```bash
git clone https://github.com/Sobhankhedry/minimum-Sum-Path.git
```

Navigate to the project:

```bash
cd minimum-Sum-Path
```

Open the project in IntelliJ IDEA.

Then run:

```text
src/Main.java
```

The minimum path sum and selected path will be printed in the console.

---

# 🔧 Possible Improvements

The current project is a focused algorithmic exercise. Several improvements could make it more robust and reusable.

* [ ] Accept triangle values from user input
* [ ] Support arbitrary triangle sizes
* [ ] Dynamically allocate the DP arrays based on input size
* [ ] Replace magic values such as `10`, `1`, and `-1` with named constants
* [ ] Separate the algorithm from the `Main` class
* [ ] Add automated test cases
* [ ] Add validation for invalid or empty triangles
* [ ] Improve path reconstruction using a dedicated data structure
* [ ] Compare recursive memoization with bottom-up dynamic programming
* [ ] Add unit tests for edge cases
* [ ] Allow the triangle to be loaded from a file

---

# 🎯 Learning Objectives

The main learning goals of this project are:

* Understanding recursive problem solving
* Learning the fundamentals of dynamic programming
* Applying memoization
* Working with two-dimensional arrays
* Finding minimum-cost paths
* Recording decisions during optimization
* Reconstructing an optimal path
* Understanding the relationship between recursion and DP

---

# 📌 Project Status

**Status:** Educational / Algorithmic Project

This repository is a compact implementation of the **Minimum Sum Path in a Triangle** problem.

It is designed primarily for practicing **recursion, dynamic programming, memoization, two-dimensional arrays, and path reconstruction**, rather than serving as a production-ready library or application.

---

# 👨‍💻 Author

**Sobhan Khedry**

Computer Engineering Graduate Student
Backend Development Enthusiast

GitHub: [@Sobhankhedry](https://github.com/Sobhankhedry)

---

# ⭐ Key Takeaways

This project demonstrates a complete optimization workflow:

```text
Triangle
   │
   ▼
Recursive Subproblems
   │
   ▼
Compare Two Possible Paths
   │
   ▼
Choose Minimum
   │
   ├──────────────► Store Minimum Sum
   │
   └──────────────► Store Decision
                         │
                         ▼
                  Reconstruct Path
                         │
                         ▼
                       Result
```

The main idea is simple:

> At every triangle position, choose the better of the two possible paths while storing previously calculated results.

The result is a **dynamic-programming solution that calculates the minimum path sum and reconstructs the corresponding path**.
