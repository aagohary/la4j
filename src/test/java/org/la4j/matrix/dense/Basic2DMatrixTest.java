/*
 * Copyright 2011-2013, by Vladimir Kostyukov and Contributors.
 * 
 * This file is part of la4j project (http://la4j.org)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Contributor(s): -
 * 
 */

package org.la4j.matrix.dense;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import org.la4j.Matrices;

public class Basic2DMatrixTest extends DenseMatrixTest<Basic2DMatrix> {

	public Basic2DMatrixTest() {
		super(Matrices.BASIC_2D);
	}

	@Test
	// added
	public void testDiagonal() {
		Basic2DMatrix m = Basic2DMatrix.diagonal(3, 10);
		double[][] arr = m.toArray();
		assertEquals(arr.length, 3);
		for (int i = 0; i < 3; i++) {
			assertEquals(arr[i].length, 3);
			for (int j = 0; j < 3; j++) {
				if (i == j) {
					assertTrue(arr[i][j] == 10);
				} else {
					assertTrue(arr[i][j] == 0);
				}
			}
		}
	}

	@Test
	// added
	public void testConstant() {
		Basic2DMatrix m = Basic2DMatrix.constant(2, 3, 20);
		double[][] arr = m.toArray();
		assertEquals(arr.length, 2);
		for (int i = 0; i < 2; i++) {
			assertEquals(arr[i].length, 3);
			for (int j = 0; j < 3; j++) {
				assertTrue(arr[i][j] == 20);
			}
		}
	}

	@Test
	// added
	public void testRandom() {
		Basic2DMatrix m = Basic2DMatrix.random(2, 3, new Random());
		double[][] arr = m.toArray();
		assertEquals(arr.length, 2);
		for (int i = 0; i < 2; i++) {
			assertEquals(arr[i].length, 3);
		}
	}

	@Test
	// added
	public void testFromToRandom() {
		Basic2DMatrix m = Basic2DMatrix.random(2, 3, new Random());
		Basic2DMatrix m2 = Basic2DMatrix.fromBinary(m.toBinary());
		assertTrue(m.equals(m2));
	}

	@Test
	// added
	public void testBlock() {
		// NOTE: the block method was wrongy implemented and I fixed it
		Basic2DMatrix m1 = new Basic2DMatrix(new double[][] { { 1, 2 },
				{ 3, 4 } });
		Basic2DMatrix m2 = new Basic2DMatrix(new double[][] { { 5, 6 },
				{ 7, 8 } });
		Basic2DMatrix m3 = new Basic2DMatrix(new double[][] { { 10, 20 },
				{ 30, 40 } });
		Basic2DMatrix m4 = new Basic2DMatrix(new double[][] { { 50, 60 },
				{ 70, 80 } });

		Basic2DMatrix m = Basic2DMatrix.block(m1, m2, m3, m4);
		Basic2DMatrix out = new Basic2DMatrix(new double[][] { { 1, 2, 5, 6 },
				{ 3, 4, 7, 8 }, { 10, 20, 50, 60 }, { 30, 40, 70, 80 } });

		assertTrue(m.equals(out));
	}

	// added
	@Test
	public void testRandomSymmetric() {
		Basic2DMatrix m = Basic2DMatrix.randomSymmetric(5, new Random());
		assertTrue(m.rows() == 5);
		assertTrue(m.columns() == 5);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				assertTrue(m.get(i, j) == m.get(j, i));
			}
		}
	}

	// added
	@Test
	public void testFrom1DArray() {
		Basic2DMatrix m = Basic2DMatrix.from1DArray(3, 2, new double[] { 1, 2,
				3, 4, 5, 6 });
		assertTrue(m.rows() == 3);
		assertTrue(m.columns() == 2);
		assertTrue(m.get(0,0) == 1);
		assertTrue(m.get(0,1) == 2);
		assertTrue(m.get(1,0) == 3);
		assertTrue(m.get(1,1) == 4);
		assertTrue(m.get(2,0) == 5);
		assertTrue(m.get(2,1) == 6);
		
	}
	
	//added
	@Test
	public void testToFromCSV(){
		Basic2DMatrix m1 = new Basic2DMatrix(new double[][] { { 1, 2 },
				{ 3, 4 } });
		Basic2DMatrix m2 = Basic2DMatrix.fromCSV(m1.toCSV());
		assertTrue(m1.equals(m2));
	}
}
