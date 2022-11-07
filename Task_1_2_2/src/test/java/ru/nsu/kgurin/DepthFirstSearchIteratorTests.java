package ru.nsu.kgurin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for my DFS algorithm.
 */
public class DepthFirstSearchIteratorTests {

    @Test
    public void hasNextTest() {
        Node<Integer> root = new Node<>(1);
        Node<Integer> rootA = new Node<>(2);
        root.add(rootA);
        DepthFirstSearchIterator<Integer> dfs = new DepthFirstSearchIterator<>(root);

        Assertions.assertTrue(dfs.hasNext());
    }

    @Test
    public void nextTest() {
        Node<Integer> root = new Node<>(1);
        Node<Integer> rootA = new Node<>(2);
        root.add(rootA);
        DepthFirstSearchIterator<Integer> dfs = new DepthFirstSearchIterator<>(root);
        dfs.next();

        Assertions.assertEquals(dfs.next().getValue(), rootA.getValue());
    }

    @Test
    public void modCountTest() {
        Node<Integer> root = new Node<>(1);
        Node<Integer> rootA = new Node<>(2);
        root.add(rootA);
        DepthFirstSearchIterator<Integer> dfs = new DepthFirstSearchIterator<>(root);
        dfs.next();
        Node<Integer> problemNode = new Node<>(3);
        rootA.add(problemNode);

        ConcurrentModificationException thrown =
                Assertions.assertThrows(ConcurrentModificationException.class, () -> {
                    dfs.next();
                }, "Changing structure while iterating");

        Assertions.assertEquals("Changing structure while iterating", thrown.getMessage());
    }

    @Test
    public void dfsAlgorithmTest() {
        //actual
        Node<Integer> root = new Node<>(1);
        Node<Integer> rootA = new Node<>(2);
        root.add(rootA);
        Node<Integer> rootAa = new Node<>(22);
        rootA.add(rootAa);
        Node<Integer> rootB = new Node<>(3);
        root.add(rootB);
        Node<Integer> rootBb = new Node<>(33);
        rootB.add(rootBb);
        Node<Integer> rootBbb = new Node<>(333);
        rootBb.add(rootBbb);

        //expected
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 3, 33, 333, 2, 22));

        DepthFirstSearchIterator<Integer> dfs = new DepthFirstSearchIterator<>(root);
        List<Integer> actual = new ArrayList<>();

        while (dfs.hasNext()) {
            actual.add(dfs.next().getValue());
        }

        //asserts
        Assertions.assertEquals(expected, actual);
    }
}

