# BubbleShellSort

This project implements a linear-time sorting algorithm by combining the concepts of Shellsort and Bubblesort on a linked list. The goal is to efficiently sort a linked list using a variant of the Bubblesort algorithm, making use of the Shellsort method for optimization.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Algorithm Explanation](#algorithm-explanation)
- [Usage](#usage)
- [Input Format](#input-format)
- [Output](#output)
- [Performance](#performance)
- [Technologies Used](#technologies-used)
- [How to Run](#how-to-run)
- [License](#license)

## Introduction

The BubbleShellSort project is an implementation of a sorting algorithm that merges Shellsort and Bubblesort techniques to efficiently sort elements stored in a linked list. This implementation aims to provide a deeper understanding of sorting algorithms applied in non-linear data structures like linked lists.

## Features
- **Linked List Implementation:** Uses a custom linked list to store and sort elements.
- **Hybrid Sorting Algorithm:** Combines Shellsort and Bubblesort to achieve efficient sorting.
- **Performance Metrics:** Tracks the number of comparisons and swaps performed during sorting.
- **Console Input and Output:** Accepts input from the user via the console and displays the sorted list and performance metrics.

## Algorithm Explanation

### Shellsort
Shellsort is an optimization of the insertion sort that allows the exchange of items that are far apart. It works by first sorting pairs of elements far apart from each other, then progressively reducing the gap between elements to be compared.

### Bubblesort
Bubblesort is a simple sorting algorithm that repeatedly steps through the list, compares adjacent elements, and swaps them if they are in the wrong order. This process is repeated until the list is sorted.

### Combined Approach
- The algorithm first performs Shellsort on the linked list to partially sort elements using a gap sequence.
- After the initial pass with Shellsort, the list is further sorted using a recursive Bubblesort, ensuring that all elements are in the correct order.
- The combination of Shellsort and Bubblesort allows for efficient handling of the linked list structure.

## Usage

1. **Compile the Program:** Ensure you have Java installed on your machine and compile the `ShellSortLinkedList.java` file.
2. **Run the Program:** Execute the compiled program and provide input through the console.
3. **Input:** Enter the list of integers to be sorted.
4. **Output:** The program outputs the sorted list, the number of comparisons, and the number of swaps performed.

## Input Format

The program accepts a sequence of integers separated by spaces or newline characters.

**Example input:** 

99 98 97 96 95 94 93 92 91 90 89 88 87 86 85 84 83 82 81 80 79 78 77 76 75 74 73 72 71 70 69 68 67 66 65 64 63 62 61 60 59 58 57 56 55 54 53 52 51 50 49 48 47 46 45 44 43 42 41 40 39 38 37 36 35 34 33 32 31 30 29 28 27 26 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0

-----------------------------------------

## Output

The program prints the sorted list and displays the number of comparisons (`cmp`) and swaps (`exch`) performed during the sorting process.

**Example output:**

         ------------------------
         h       cmp          exch            
         40      80             80
         13      115            35
         4       133            40
         1       294            75
         -------------------------
         Total  622            230
         -------------------------
         Time:   0.033000 seconds

## Performance

- The algorithm prints performance metrics for each value of the gap (`h`) used in Shellsort.
- The total number of comparisons and swaps are calculated and displayed along with the time taken to complete the sorting process.

## Technologies Used

- **Java:** The entire project is implemented in Java using standard libraries.

## How to Run

1. **Clone the Repository:**
    ```bash
    git clone https://github.com/yourusername/BubbleShellSort.git
    ```
2. **Navigate to the Project Directory:**
    ```bash
    cd BubbleShellSort
    ```
3. **Compile the Java File:**
    ```bash
    javac ShellSortLinkedList.java
    ```
4. **Run the Program:**
    ```bash
    java ShellSortLinkedList
    ```
5. **Provide Input:**
    Enter or select a file with a sequence of integers to be sorted.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
