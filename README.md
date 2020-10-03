# Percolation
A monte carlo simulation in java to estimate the percolation threshold of a MxN grid using the Union algorithm to connect the nodes.

# What is Percolation?
A Percolation system consists of a grid with MxN sites that have a probability p of being open and a probability 1-p of being closed.
If the site is open, it will connect with its neighbors that are also open. We say that the system percolates if the first row of 
the grid is connected to the last row.

PS: In this algorithm the diagonals of a site are not considered to be its neighbors.
