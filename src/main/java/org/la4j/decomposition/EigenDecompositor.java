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

package org.la4j.decomposition;

import org.la4j.factory.Factory;
import org.la4j.matrix.Matrices;
import org.la4j.matrix.Matrix;
import org.la4j.vector.Vector;

public class EigenDecompositor implements MatrixDecompositor {

    public static final int MAX_ITERATIONS = 100000;

    @Override
    public Matrix[] decompose(Matrix matrix, Factory factory) {

        // TODO: improve this code

        if (!matrix.is(Matrices.SYMMETRIC_MATRIX)) {
            throw new IllegalArgumentException("Matrix isn't symmetric.");
        }

        Matrix d = matrix.copy();
        Vector r = generateR(d, factory);

        Matrix v = factory.createIdentityMatrix(matrix.rows());

        int iteration = 0;

        do {

            int k = findMax(r, -1);
            int l = findMax(d.getRow(k), k);

            Matrix u = generateU(d, factory, k, l);

            v = v.multiply(u);
            d = u.transpose().multiply(d.multiply(u));

            r.set(k, generateRi(d.getRow(k), k));

            r.set(l, generateRi(d.getRow(l), l));

            iteration++;

        } while (r.norm() > Matrix.EPS && iteration < MAX_ITERATIONS);

        if (iteration > MAX_ITERATIONS) {
            throw new IllegalArgumentException("Can't decompose this matrix.");
        }

        return new Matrix[] { v, d };
    }

    private int findMax(Vector vector, int exl) {

        int result = exl == 0 ? 1 : 0;
        for (int i = 0; i < vector.length(); i++) {
            if (i != exl
                    && Math.abs(vector.get(result)) < Math.abs(vector.get(i)))
                result = i;
        }

        return result;
    }

    private Vector generateR(Matrix matrix, Factory factory) {

        Vector result = factory.createVector(matrix.rows());

        for (int i = 0; i < matrix.rows(); i++) {
            result.set(i, generateRi(matrix.getRow(i), i));
        }

        return result;
    }

    private double generateRi(Vector vector, int position) {

        double summand = 0;
        for (int i = 0; i < vector.length(); i++) {
            if (i != position)
                summand += vector.get(i) * vector.get(i);
        }

        return summand;
    }

    private Matrix generateU(Matrix matrix, Factory factory, int k, int l) {

        Matrix result = factory.createIdentityMatrix(matrix.rows());

        double alpha = 0.0, beta = 0.0;

        if ((matrix.get(k, k) - matrix.get(l, l)) < Matrix.EPS) {
            alpha = beta = Math.sqrt(0.5);
        } else {
            double mu = 2 * matrix.get(k, l)
                    / (matrix.get(k, k) - matrix.get(l, l));
            mu = 1 / Math.sqrt(1 + mu * mu);
            alpha = Math.sqrt(0.5 * (1 + mu));
            beta = Math.signum(mu) * Math.sqrt(0.5 * (1 - mu));
        }

        result.set(k, k, alpha);
        result.set(l, l, alpha);
        result.set(k, l, -beta);
        result.set(l, k, beta);

        return result;
    }
}