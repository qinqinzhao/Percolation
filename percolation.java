package Percolation_New;

import java.util.Random;

public class Percolation {
	public static void main(String args[]) {
		Percolation percolation = new Percolation();
		int[] nValues = {50, 100, 200};
		for (int n : nValues) {
			System.out.println("When n = " + n + ", p = " + percolation.findProbability(n));
		}
	}

	/**
	 * Returns an array of n arrays of integer, where each array is of length n, and 
	 * each integer has probability p of being a sand grain, (1-p) of being empty.
	 */
	int[][] ground(int n, double p) {
		int[][] ground = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				double random_number = new Random().nextDouble();
				if (random_number <= p) {
					ground[i][j] = 1; // sand grain
				} else {
					ground[i][j] = 0; // empty space
				}
			}
		}
		return ground;
	}
	
	/** Causes water to flow from row into row+1, modifying the array. */
	void seep(int[][] ground, int row) {
		// Seep from row down to row+1
		for (int j = 0; j < ground.length; j++) {
			if (ground[row][j] == 2 && ground[row + 1][j] == 0) {
				ground[row + 1][j] = 2;
			}
		}
		// Seep horizontally in row+1 
		for (int j = 0; j < ground.length; j++) {
		    if (ground[row + 1][j] == 2) {
		    	while (j - 1 >= 0) { 
		    		if (ground[row + 1][j - 1] == 1) {
		    			break;
		    		}
		    		ground[row + 1][j - 1] = 2;
		    		j -= 1;
		    	}
		    	while (j + 1 < ground.length) {
		    		if (ground[row + 1][j + 1] == 1) {
		    			break;
		    		}
		    		ground[row + 1][j + 1] = 2;
		    		j += 1;
		    	}
		    }
		}
	}

	/**
	 * Returns true if, after water has "seeped" as far as it can, water has
	 * reached the bottom row, and false otherwise.
	 */
	boolean percolate(int[][] ground) {
		// Seep the first row
		for (int j = 0; j < ground.length; j++) {
			if (ground[0][j] == 0) {
				ground[0][j] = 2;
			}
		}
		// Seep following rows
		for (int i = 0; i < ground.length - 1; i++) {
			seep(ground, i);
		}
		// Whether water reached the bottom row
		for (int j = 0; j < ground.length; j++) {
			if (ground[ground.length - 1][j] == 2) {
				return true;
			}
		}
		return false;
	}

	/**
	 * For an n by n array, determines the packing probability p that causes the
	 * array to have a 50% probability of water seeping all the way to the bottom.
	 */
	double findProbability(int n) {
		double p = 0.5, delta = 0.05;
		while (true) {
			double count = 0.0;
			for (int i = 0; i < 100; i++) {
				if (percolate(ground(n, p))) {
					count += 1.0;
				}
			}
			double waterSeepP = (double) Math.round((count / 100) * 1000.0) / 1000.0;
			if (waterSeepP > 0.5) {
				p += delta;
				delta *= 0.95;
			} else if (waterSeepP < 0.5) {
				p -= delta;
				delta *= 0.95;
			} else {
				break;
			}
		}
		return p;
	}
}
