package Percolation_New;

import static org.junit.Assert.*;

import org.junit.Test;

public class PercolationTest {
	Percolation percolation = new Percolation();

	@Test
	public void testGround() {
		int[][] ground = percolation.ground(50, 0.5);
		int countSand = 0;
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				if (ground[i][j] == 1) {
					countSand += 1;
				}
			}
		}
		double sandProportion = (double) countSand / (50 * 50);
		assertEquals(0.5, sandProportion, 0.1);
	}
	
	@Test
	public void testSeep() {
		int[][] ground = {{2, 2, 1, 1, 2, 2, 1, 2},
				          {1, 0, 0, 0, 0, 1, 0, 0},
				          {0, 1, 0, 0, 1, 0, 1, 1},
				          {0, 0, 0, 1, 0, 0, 0, 0},
				          {1, 1, 0, 0, 0, 0, 1, 0},
				          {0, 1, 0, 0, 1, 0, 0, 0},
				          {1, 0, 1, 0, 0, 0, 1, 0},
				          {0, 1, 0, 0, 0, 1, 0, 0}};
	    int[][] newGround = {{2, 2, 1, 1, 2, 2, 1, 2},
		                     {1, 2, 2, 2, 2, 1, 2, 2},
		                     {0, 1, 0, 0, 1, 0, 1, 1},
		                     {0, 0, 0, 1, 0, 0, 0, 0},
		                     {1, 1, 0, 0, 0, 0, 1, 0},
		                     {0, 1, 0, 0, 1, 0, 0, 0},
		                     {1, 0, 1, 0, 0, 0, 1, 0},
		                     {0, 1, 0, 0, 0, 1, 0, 0}};
		percolation.seep(ground, 0);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				assertEquals(newGround[i][j], ground[i][j]);
			}
		}
		int[][] ground1 = {{2, 2, 1, 1, 2, 2, 1, 2},
				           {1, 2, 2, 2, 2, 1, 2, 2},
		                   {0, 1, 2, 2, 1, 0, 1, 1},
		                   {0, 0, 0, 1, 0, 0, 0, 0},
		                   {1, 1, 0, 0, 0, 0, 1, 0},
		                   {0, 1, 0, 0, 1, 0, 0, 0},
		                   {1, 0, 1, 0, 0, 0, 1, 0},
		                   {0, 1, 0, 0, 0, 1, 0, 0}};
		int[][] newGround1 = {{2, 2, 1, 1, 2, 2, 1, 2},
		                      {1, 2, 2, 2, 2, 1, 2, 2},
                              {0, 1, 2, 2, 1, 0, 1, 1},
                              {2, 2, 2, 1, 0, 0, 0, 0},
                              {1, 1, 0, 0, 0, 0, 1, 0},
                              {0, 1, 0, 0, 1, 0, 0, 0},
                              {1, 0, 1, 0, 0, 0, 1, 0},
                              {0, 1, 0, 0, 0, 1, 0, 0}};
		percolation.seep(ground1, 2);
		for (int m = 0; m < 8; m++) {
			for (int n = 0; n < 8; n++) {
				assertEquals(newGround1[m][n], ground1[m][n]);
			}
		}
	}
    
	@Test
	public void testPercolate() {
		int[][] ground = {{0, 0, 1, 1, 0, 0, 1, 0},
		                  {1, 0, 0, 0, 0, 1, 0, 0},
		                  {0, 1, 0, 0, 1, 0, 1, 1},
		                  {0, 0, 0, 1, 0, 0, 0, 0},
		                  {1, 1, 0, 0, 0, 0, 1, 0},
		                  {0, 1, 0, 0, 1, 0, 0, 0},
		                  {1, 0, 1, 0, 0, 0, 1, 0},
		                  {0, 1, 0, 0, 0, 1, 0, 0}};
		assertTrue(percolation.percolate(ground));
		int[][] ground1 = {{0, 0, 1, 1, 0, 0, 1, 0},
                           {1, 0, 0, 0, 0, 1, 0, 0},
                           {0, 1, 0, 0, 1, 0, 1, 1},
                           {0, 0, 0, 1, 0, 0, 0, 0},
                           {1, 1, 0, 0, 0, 0, 1, 0},
                           {0, 1, 0, 1, 1, 0, 0, 0},
                           {1, 0, 1, 0, 1, 0, 1, 0},
                           {0, 1, 0, 0, 0, 1, 0, 1}};
		assertFalse(percolation.percolate(ground1));
	}
}
