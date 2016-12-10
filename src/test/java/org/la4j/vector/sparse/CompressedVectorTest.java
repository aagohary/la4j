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

package org.la4j.vector.sparse;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.la4j.Vectors;

public class CompressedVectorTest extends SparseVectorTest<CompressedVector> {

    public CompressedVectorTest() {
        super(Vectors.COMPRESSED);
    }
    
    @Test //added
    public void testRandom(){
    	Assert.assertEquals(CompressedVector.random(100, 0.5, new Random()).length(), 100);
    	Assert.assertTrue(CompressedVector.random(100, 0.5, new Random()).density()==.5);
   
    }
    
    @Test //added
    public void testFromArray(){
    	CompressedVector vec = CompressedVector.fromArray(new double[]{1,0,0,2,3});
    	Assert.assertTrue(vec.get(0)==1);
    	Assert.assertTrue(vec.get(1)==0);
    	Assert.assertTrue(vec.get(2)==0);
    	Assert.assertTrue(vec.get(3)==2);
    	Assert.assertTrue(vec.get(4)==3);
    }
    
    @Test //added
    public void testFromToBinary(){
    	CompressedVector cv = CompressedVector.random(100, 0.5, new Random());
    	CompressedVector cv2 = CompressedVector.fromBinary(cv.toBinary());
    	Assert.assertTrue(cv.equals(cv2));
    }
}
