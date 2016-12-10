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

package org.la4j.vector.dense;

import static org.junit.Assert.*;

import org.junit.Test;
import org.la4j.Matrix;
import org.la4j.Vectors;

public class BasicVectorTest extends DenseVectorTest<BasicVector> {

    public BasicVectorTest() {
        super(Vectors.BASIC);
    }
    
    //added
    @Test
    public void testToRowMatrix(){
    	BasicVector v = new BasicVector(new double[]{1,2,3,4});
    	Matrix  m = v.toRowMatrix();
    	assertTrue(m.rows()==1);
    	assertTrue(m.columns()==4);
    	assertTrue(m.get(0, 0)==1);
    	assertTrue(m.get(0, 1)==2);
    	assertTrue(m.get(0, 2)==3);
    	assertTrue(m.get(0, 3)==4);
    }
    
  //added
    @Test
    public void testToColumnMatrix(){
    	BasicVector v = new BasicVector(new double[]{1,2,3,4});
    	Matrix  m = v.toColumnMatrix();
    	assertTrue(m.rows()==4);
    	assertTrue(m.columns()==1);
    	assertTrue(m.get(0, 0)==1);
    	assertTrue(m.get(1, 0)==2);
    	assertTrue(m.get(2, 0)==3);
    	assertTrue(m.get(3, 0)==4);
    }

    //added
      @Test
      public void testToDiagonalMatrix(){
      	BasicVector v = new BasicVector(new double[]{1,2,3,4});
      	Matrix  m = v.toDiagonalMatrix();
      	assertTrue(m.rows()==4);
      	assertTrue(m.columns()==4);
      	assertTrue(m.get(0, 0)==1);
      	assertTrue(m.get(1, 1)==2);
      	assertTrue(m.get(2, 2)==3);
      	assertTrue(m.get(3, 3)==4);
      }
    
      //added
      @Test
      public void testToFromBinary(){
    	  BasicVector v = new BasicVector(new double[]{1,2,3,4});
    	  BasicVector v2 = BasicVector.fromBinary(v.toBinary());
    	  assertTrue(v.equals(v2));
      }
      
    //added
      @Test
      public void testToFromMatrixMarket(){
    	  BasicVector v = new BasicVector(new double[]{1,2,3,4});
    	  BasicVector v2 = BasicVector.fromMatrixMarket(v.toMatrixMarket());
    	  assertTrue(v.equals(v2));
      }
      
}
