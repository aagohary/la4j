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

package org.la4j.matrix.sparse;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import org.la4j.Matrices;
import org.la4j.matrix.dense.Basic2DMatrix;

public class CCSMatrixTest extends SparseMatrixTest<CCSMatrix> {

	public CCSMatrixTest() {
		super(Matrices.CCS);
	}

	// added
	@Test
	public void testDiagonal() {
		CCSMatrix m = CCSMatrix.diagonal(5, 10);
		assertTrue(m.rows() == 5);
		assertTrue(m.columns() == 5);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == j) {
					assertTrue(m.get(i, j) == 10);
				} else {
					assertTrue(m.get(i, j) == 0);
				}
			}
		}
	}

	// added
	@Test
	public void testRandom() {
		CCSMatrix m = CCSMatrix.random(10, 5, 0.5, new Random());
		assertEquals(m.rows(), 10);
		assertEquals(m.columns(), 5);
	}

	// added
	@Test
	public void testRandomSymmetric() {
		CCSMatrix m = CCSMatrix.randomSymmetric(5, 0.5, new Random());
		assertEquals(m.rows(), 5);
		assertEquals(m.columns(), 5);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				assertTrue(m.get(i, j) == m.get(j, i));
			}
		}
	}

	@Test
	// added
	public void testBlock() {
		// NOTE: the block method was wrongly implemented and I fixed it
		CCSMatrix m1 = CCSMatrix.from2DArray(new double[][] { { 1, 2 },
				{ 3, 4 } });
		CCSMatrix m2 = CCSMatrix.from2DArray(new double[][] { { 5, 6 },
				{ 7, 8 } });
		CCSMatrix m3 = CCSMatrix.from2DArray(new double[][] { { 10, 20 },
				{ 30, 40 } });
		CCSMatrix m4 = CCSMatrix.from2DArray(new double[][] { { 50, 60 },
				{ 70, 80 } });

		CCSMatrix m = CCSMatrix.block(m1, m2, m3, m4);
		CCSMatrix out = CCSMatrix.from2DArray(new double[][] {
				{ 1, 3, 10, 30 }, { 2, 4, 20, 40 }, { 5, 7, 50, 70 },
				{ 6, 8, 60, 80 } });

		assertTrue(m.equals(out));
	}

	@Test
	// added
	public void testToFromBinary() {
		CCSMatrix m1 = CCSMatrix.from2DArray(new double[][] { { 1, 2 },
				{ 3, 4 } });
		CCSMatrix m2 = CCSMatrix.fromBinary(m1.toBinary());
		assertTrue(m1.equals(m2));
	}
	
	@Test
	// added
	public void testSetAll() {
		CCSMatrix m1 = CCSMatrix.from2DArray(new double[][] { { 1, 2 },
				{ 3, 4 } });
		m1.setAll(20);
		System.out.println(m1);
		for (int i=0;i< 2; i++){
			for(int j=0;j<2;j++){
				assertTrue(m1.get(i, j)==20);
			}
		}
	}
	
	@Test
	// added
	public void testFrom1DArray(){
		CCSMatrix m = CCSMatrix.from1DArray(2, 2, new double[]{1,2,3,4});
		assertTrue(m.get(0, 0)==1);
		assertTrue(m.get(0, 1)==2);
		assertTrue(m.get(1, 0)==3);
		assertTrue(m.get(1, 1)==4);
	}
}
