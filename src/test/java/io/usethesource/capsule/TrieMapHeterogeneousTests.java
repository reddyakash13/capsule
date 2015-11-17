/*******************************************************************************
 * Copyright (c) 2015 CWI All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *
 * * Michael Steindorfer - Michael.Steindorfer@cwi.nl - CWI
 *******************************************************************************/
package io.usethesource.capsule;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Random;

import org.junit.Test;

public class TrieMapHeterogeneousTests {

  // final static int size = 2097152;
  final static int size = (int) Math.pow(2, 10);

  @Test
  public void testPrintStatsRandomSmallAndBigIntegers() {
    TrieMap_Heterogeneous_BleedingEdge map =
        (TrieMap_Heterogeneous_BleedingEdge) TrieMap_Heterogeneous_BleedingEdge.of();
    long smallCount = 0;
    long bigCount = 0;

    Random rand = new Random(13);

    for (int i = size; i > 0; i--) {
      final int j = rand.nextInt();
      // System.out.println(j);

      final BigInteger bigJ = BigInteger.valueOf(j).multiply(BigInteger.valueOf(j));
      // System.out.println(bigJ);

      final Integer boxedJ = Integer.valueOf(j);
      // System.out.println(boxedJ);

      if (i % 20 == 0) { // earlier: bigJ.bitLength() > 31
        // System.out.println("BIG");
        bigCount++;

        // TrieMap_Heterogeneous_BleedingEdge res =
        // (TrieMap_Heterogeneous_BleedingEdge) map.__put(bigJ, bigJ);
        // assert res.containsKey(bigJ);

        TrieMap_Heterogeneous_BleedingEdge res =
            (TrieMap_Heterogeneous_BleedingEdge) map.__put(boxedJ, boxedJ);

        // assertTrue(res.containsKey(boxedJ));
        // because of non-overlap constraint
        assertTrue(res.containsKey(j) || res.containsKey(boxedJ));
        map = res;
      } else {
        // System.out.println("SMALL");
        smallCount++;
        TrieMap_Heterogeneous_BleedingEdge res =
            (TrieMap_Heterogeneous_BleedingEdge) map.__put(j, j);

        // assertTrue(res.containsKey(j));
        // because of non-overlap constraint
        assertTrue(res.containsKey(j) || res.containsKey(boxedJ));
        map = res;
      }
    }

    // map.printStatistics();
    // System.out.println(map);

    System.out.println();
    System.out.println(String.format("PRIMITIVE: %10d (%.2f percent)", smallCount,
        100. * smallCount / (smallCount + bigCount)));
    System.out.println(String.format("BIG_INTEGER: %10d (%.2f percent)", bigCount,
        100. * bigCount / (smallCount + bigCount)));
    System.out.println(String.format("UNIQUE: %10d (%.2f percent)", map.size(),
        100. * map.size() / (smallCount + bigCount)));
    System.out.println();
  }

}
