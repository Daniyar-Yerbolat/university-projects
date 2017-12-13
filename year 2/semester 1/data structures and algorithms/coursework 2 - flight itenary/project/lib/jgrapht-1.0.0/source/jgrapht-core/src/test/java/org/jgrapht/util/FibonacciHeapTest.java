/*
 * (C) Copyright 2008-2016, by John V Sichi and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
package org.jgrapht.util;

import java.util.*;

import junit.framework.*;

public class FibonacciHeapTest
    extends TestCase
{
    // ~ Methods ----------------------------------------------------------------

    // in honor of sf.net bug #1845376
    public void testAddRemoveOne()
    {
        String s = "A";
        FibonacciHeapNode<String> n = new FibonacciHeapNode<>(s);
        FibonacciHeap<String> h = new FibonacciHeap<>();
        assertTrue(h.isEmpty());
        h.insert(n, 1.0);
        assertFalse(h.isEmpty());
        FibonacciHeapNode<String> n2 = h.removeMin();
        assertEquals(s, n2.getData());
        assertTrue(h.isEmpty());
    }

    public void testGrowReplaceShrink()
    {
        Random r = new Random();
        int k = 10000;
        String s = "A";
        double t = 0;
        FibonacciHeap<String> h = new FibonacciHeap<>();
        for (int i = 0; i < (k * 3); ++i) {
            // during first two-thirds, insert
            if (i < (k * 2)) {
                double d = r.nextDouble();
                t += d;
                FibonacciHeapNode<String> n = new FibonacciHeapNode<>(s);
                h.insert(n, d);
            }

            // during last two-thirds, delete (so during middle
            // third, we'll do both insert and delete, interleaved)
            if (i >= k) {
                FibonacciHeapNode<String> n2 = h.removeMin();
                t -= n2.getKey();
            }
        }
        assertTrue(h.isEmpty());

        // tally should come back down to zero, or thereabouts (due to roundoff)
        assertEquals(0.0, t, 0.00001);
    }
}

// End FibonacciHeapTest.java
