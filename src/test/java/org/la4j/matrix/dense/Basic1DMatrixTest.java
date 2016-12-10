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

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.la4j.Matrices;

public class Basic1DMatrixTest extends DenseMatrixTest<Basic1DMatrix> {

	public Basic1DMatrixTest() {
		super(Matrices.BASIC_1D);
	}

	@Test
	// added
	public void testBlock() {
		// NOTE: the block method was wrongy implemented and I fixed it
		Basic1DMatrix m1 = Basic1DMatrix.from1DArray(2, 2, new double[] { 1, 2,
				3, 4 });
		Basic1DMatrix m2 = Basic1DMatrix.from1DArray(2, 2, new double[] { 5, 6,
				7, 8 });
		Basic1DMatrix m3 = Basic1DMatrix.from1DArray(2, 2, new double[] { 9,
				10, 11, 12 });
		Basic1DMatrix m4 = Basic1DMatrix.from1DArray(2, 2, new double[] { 13,
				14, 15, 16 });

		Basic1DMatrix m = Basic1DMatrix.block(m1, m2, m3, m4);
		Basic1DMatrix out = new Basic1DMatrix(4, 4, new double[] { 1, 2, 5, 6,
				3, 4, 7, 8, 9, 10, 13, 14, 11, 12, 15, 16 });

		assertTrue(m.equals(out));
	}

	@Test
	// added
	public void testFromToBinary() {
		Basic1DMatrix m1 = new Basic1DMatrix(4, 4, new double[] { 1, 2, 5, 6,
				3, 4, 7, 8, 9, 10, 13, 14, 11, 12, 15, 16 });
		Basic1DMatrix m2 = Basic1DMatrix.fromBinary(m1.toBinary());
		assertTrue(m1.equals(m2));
	}

	@Test
	// added
	public void testFromTo2D() {
		double[][] arr = new double[][] { { 1, 2 }, { 5, 6 } };
		Basic1DMatrix m = Basic1DMatrix.from2DArray(arr);
		assertTrue(m.get(0, 0)==1);
		assertTrue(m.get(0, 1)==2);
		assertTrue(m.get(1, 0)==5);
		assertTrue(m.get(1, 1)==6);
		

	}

}
