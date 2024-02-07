package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.TreeIsomorphism.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static org.example.TreeIsomorphism.encode;
import static org.example.TreeIsomorphism.rootTree;


public class TreeAlgorithms {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Node {
        private int value;
        private Node left;
        private Node right;
    }


    public static int leavesSum(Node root) {
        int sum = 0;
        if (root == null)
            return 0;
        if (!hasChildren(root)) {
            return root.value;
        }
        sum += leavesSum(root.left);
        sum += leavesSum(root.right);

        return sum;
    }

    public static int TreeHeight(Node root) {
        if (root == null) return -1;
        if (!hasChildren(root)) return 0;
        return max(TreeHeight(root.getRight()), TreeHeight(root.getLeft())) + 1;
    }

    public static List<Integer> TreeCenter(List<List<Integer>> g){
        if (g == null || g.isEmpty()) throw new IllegalArgumentException("should provide adjacency list");

        int[] degree = new int[g.size()];
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < g.size(); i++) {
            degree[i] = g.get(i).size() <= 1? 0 : g.get(i).size();
            if (degree[i] == 0)
                leaves.add(i);
        }

        int count = leaves.size();
        while (count < g.size()){
            ArrayList<Integer> newLeaves = new ArrayList<>();
            for (Integer leaf : leaves) {
                for (Integer i : g.get(leaf)) {
                    if (--degree[i] == 1)
                        newLeaves.add(i);
                }
                degree[leaf] = 0;
            }
            count += newLeaves.size();
            leaves.clear();
            leaves.addAll(newLeaves);
        }
        return leaves;
    }

    public static boolean treesAreIsomorphic(List<List<Integer>> tree1, List<List<Integer>> tree2) {
        if (tree1.isEmpty() || tree2.isEmpty()) {
            throw new IllegalArgumentException("Empty tree input");
        }

        List<Integer> centers1 = TreeCenter(tree1);
        List<Integer> centers2 = TreeCenter(tree2);

        TreeNode rootedTree1 = rootTree(tree1, centers1.get(0));
        String tree1Encoding = encode(rootedTree1);

        for (int center : centers2) {
            TreeNode rootedTree2 = rootTree(tree2, center);
            String tree2Encoding = encode(rootedTree2);

            if (tree1Encoding.equals(tree2Encoding)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasChildren(Node root) {
        return !(root.getLeft() == null || root.getRight() == null);
    }
}
