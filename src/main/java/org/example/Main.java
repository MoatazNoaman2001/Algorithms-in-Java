package org.example;

import org.example.TreeAlgorithms.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.example.TreeAlgorithms.TreeCenter;
import static org.example.TreeAlgorithms.TreeCenter;
import static org.example.TreeAlgorithms.leavesSum;
import static org.example.TreeAlgorithms.treesAreIsomorphic;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Node root = new Node(
                8,
                new Node(5, null, null),
                new Node(10,
                        new Node(7, null, null),
                        new Node(12, new Node(3, null, null), new Node(13, null, null))
                )
        );
        int res = leavesSum(root);
        System.out.println(res);

        List<List<Integer>> graph = createEmptyTree(9);
        addUndirectedEdge(graph, 0, 1);
        addUndirectedEdge(graph, 2, 1);
        addUndirectedEdge(graph, 2, 3);
        addUndirectedEdge(graph, 3, 4);
        addUndirectedEdge(graph, 5, 3);
        addUndirectedEdge(graph, 2, 6);
        addUndirectedEdge(graph, 6, 7);
        addUndirectedEdge(graph, 6, 8);

        System.out.println(TreeCenter(graph)); // should print 2

        List<List<Integer>> tree1 = createEmptyTree(5);
        addUndirectedEdge(tree1, 2, 0);
        addUndirectedEdge(tree1, 3, 4);
        addUndirectedEdge(tree1, 2, 1);
        addUndirectedEdge(tree1, 2, 3);

        List<List<Integer>> tree2 = createEmptyTree(5);
        addUndirectedEdge(tree2, 1, 0);
        addUndirectedEdge(tree2, 2, 4);
        addUndirectedEdge(tree2, 1, 3);
        addUndirectedEdge(tree2, 1, 2);

        if (!treesAreIsomorphic(tree1, tree2)) {
            System.out.println("Oops, these tree should be isomorphic!");
        }else{
            System.out.println("It's, Isomorphic! ");
        }
    }

    public static List<List<Integer>> createEmptyTree(int n) {
        List<List<Integer>> tree = new ArrayList<>(n);
        for (int i = 0; i < n; i++) tree.add(new LinkedList<>());
        return tree;
    }

    public static void addUndirectedEdge(List<List<Integer>> tree, int from, int to) {
        tree.get(from).add(to);
        tree.get(to).add(from);
    }

}