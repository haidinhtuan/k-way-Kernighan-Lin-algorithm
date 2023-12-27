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
\```latex
\begin{algorithm}
\caption{k-Way Kernighan-Lin Algorithm}\label{kla:cap}
\begin{algorithmic}[1]
\Procedure{Kernighan-Lin-k}{$G(V, E)$, $k$}
    \State Initialize a list $S$ containing all vertices
    \While{length of $S$ $< k$}
        \State Identify the largest subset $L$ in $S$
        \State Partition vertices in $L$ into balanced sets $A$ and $B$
        \Repeat
            \State Compute $D$ values for all $a \in A$ and $b \in B$
            \State Let $gv$, $av$, and $bv$ be empty lists
            \For{$n := 1$ \textbf{to} $|L| / 2$}
                \State Find $a \in A$ and $b \in B$ to maximize $g = D[a] + D[b] - 2 \times c(a, b)$
                \State Remove $a$ and $b$ from further consideration in this pass
                \State Add $g$ to $gv$, $a$ to $av$, and $b$ to $bv$
                \State Update $D$ values for the elements of $A = A \setminus \{a\}$ and $B = B \setminus \{b\}$
            \EndFor
            \State Find $t$ which maximizes $g_{\text{max}}$, the sum of $gv[1], \ldots, gv[t]$
            \If{$g_{\text{max}} > 0$}
                \State Exchange $av[1], av[2], \ldots, av[t]$ with $bv[1], bv[2], \ldots, bv[t]$
            \EndIf
        \Until{$g_{\text{max}} \leq 0$}
        \State Replace $L$ in $S$ with two new subsets $A$ and $B$
    \EndWhile
    \State \Return $S$ \Comment{List of k subsets}
\EndProcedure
\end{algorithmic}
\end{algorithm}
\```

## Getting Started

### Prerequisites
- Java JDK 8 or higher

### Installation
1. Clone the repository:
   ```bash
   git clone [repository URL]

