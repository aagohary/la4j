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

public class CRSMatrixTest extends SparseMatrixTest<CRSMatrix> {

	public CRSMatrixTest() {
		super(Matrices.CRS);
	}

	@Test
	// added
	public void testFromToBinary() {
		CRSMatrix m1 = CRSMatrix.from2DArray(new double[][] { { 1, 2 },
				{ 3, 4 } });
		CRSMatrix m2 = CRSMatrix.fromBinary(m1.toBinary());
		assertTrue(m1.equals(m2));
	}

	@Test
	// added
	public void testRandom() {
		CRSMatrix m1 = CRSMatrix.random(5, 3, 0.5, new Random());
		assertTrue(m1.rows() == 5);
		assertTrue(m1.columns() == 3);
	}

	@Test
	// added
	public void testRandomSymmetric() {
		CRSMatrix m1 = CRSMatrix.randomSymmetric(5, 0.5, new Random());
		assertTrue(m1.rows() == 5);
		assertTrue(m1.columns() == 5);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				assertTrue(m1.get(i, j) == m1.get(j, i));
			}
		}
	}

	@Test
	// added
	public void testFrom1DArray(){
		CRSMatrix m = CRSMatrix.from1DArray(2, 2, new double[]{1,2,3,4});
		assertTrue(m.get(0, 0)==1);
		assertTrue(m.get(0, 1)==2);
		assertTrue(m.get(1, 0)==3);
		assertTrue(m.get(1, 1)==4);
	}
	
	@Test
	// added
	public void testSetAll(){
		CRSMatrix m = CRSMatrix.from1DArray(2, 2, new double[]{1,2,3,4});
		m.setAll(20);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				assertTrue(m.get(i, j) == 20);
			}
		}
	}
	
	@Test
	// added
	public void testBlock() {
		CRSMatrix m1 = CRSMatrix.from2DArray(new double[][] { { 1, 2 },
				{ 3, 4 } });
		CRSMatrix m2 = CRSMatrix.from2DArray(new double[][] { { 5, 6 },
				{ 7, 8 } });
		CRSMatrix m3 = CRSMatrix.from2DArray(new double[][] { { 10, 20 },
				{ 30, 40 } });
		CRSMatrix m4 = CRSMatrix.from2DArray(new double[][] { { 50, 60 },
				{ 70, 80 } });

		CRSMatrix m = CRSMatrix.block(m1, m2, m3, m4);
		System.out.println(m);
		CRSMatrix out = CRSMatrix.from2DArray(new double[][] {
				{ 1, 2, 5, 6 }, { 3, 4, 7, 8 }, { 10, 20, 50, 60 },
				{ 30, 40, 70, 80 } });

		assertTrue(m.equals(out));
	}


}
