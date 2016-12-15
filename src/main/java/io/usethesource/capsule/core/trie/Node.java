/**
 * Copyright (c) Michael Steindorfer <Centrum Wiskunde & Informatica> and Contributors.
 * All rights reserved.
 *
 * This file is licensed under the BSD 2-Clause License, which accompanies this project
 * and is available under https://opensource.org/licenses/BSD-2-Clause.
 */
package io.usethesource.capsule.core.trie;

public interface Node {

  // boolean hasNodes();
  //
  // int nodeArity();
  //
  // INode getNode(final int index);
  //
  // default void setNode(final AtomicReference<Thread> mutator, final int index, final INode node)
  // {
  // throw new UnsupportedOperationException("Immutable.");
  // }

  ArrayView<? extends Node> nodeArray();

}