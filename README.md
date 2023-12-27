# Java Implementation of the Extended Kernighan-Lin Algorithm

## Overview
This project presents a Java implementation of the Kernighan-Lin algorithm, traditionally used for solving the minimum-weight k-cut problem by dividing a graph into two subsets. The Kernighan-Lin algorithm is a heuristic method for partitioning a graph into two subsets, aiming to minimize the edge cut between them. This implementation extends the classic approach to support partitioning into `k` groups rather than just two. It's particularly useful in scenarios requiring a balanced partitioning of nodes while minimizing the sum of the weights of the edges that are cut.

## Features
- **K-Group Partitioning**: Unlike the traditional implementation that limits partitioning to two subsets, this version extends the functionality to support partitioning into `k` groups.
- **Weighted Graph Support**: Fully supports graphs with weighted edges, optimizing for minimum-weight cuts.
- **Customizable Parameters**: Offers flexibility in setting parameters like the number of desired partitions and initial partition states.
- **Efficient Implementation**: Designed for performance, efficiently handling large graphs.

## Pseudocode

### k-Way Kernighan-Lin Algorithm

Pseudo-code:
```
Procedure Kernighan-Lin-k(G(V, E), k)
    Initialize a list S containing all vertices
    While length of S < k
        Identify the largest subset L in S
        Partition vertices in L into balanced sets A and B
        Repeat
            Compute D values for all a in A and b in B
            Let gv, av, and bv be empty lists
            For n := 1 to |L| / 2
                Find a in A and b in B to maximize g = D[a] + D[b] - 2 * c(a, b)
                Remove a and b from further consideration in this pass
                Add g to gv, a to av, and b to bv
                Update D values for the elements of A = A \ {a} and B = B \ {b}
            End For
            Find t which maximizes g_max, the sum of gv[1], ..., gv[t]
            If g_max > 0
                Exchange av[1], av[2], ..., av[t] with bv[1], bv[2], ..., bv[t]
            End If
        Until g_max <= 0
        Replace L in S with two new subsets A and B
    End While
    Return S (List of k subsets)
End Procedure
```


## Prerequisites
- Java JDK 8 or higher

## Installation
Clone the repository and run the KernighanLinProgram.java


