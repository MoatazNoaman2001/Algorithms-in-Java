package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

public class TreeIsomorphism {
    @Data
    @AllArgsConstructor
    public static class TreeNode{
        private int id;
        private TreeNode parent;
        private List<TreeNode> children;

        public TreeNode(int id){
            this.id = id;
            parent = null;
            children = new ArrayList<>();
        }

        public TreeNode(int id, TreeNode parent){
            this.id = id;
            this.parent = parent;
            children = new ArrayList<>();
        }

        public void addChildren(TreeNode ... nodes){
            children.addAll(Arrays.asList(nodes));
        }

    }


    public static TreeNode rootTree(List<List<Integer>> g , int rootId){
        TreeNode root = new TreeNode(rootId);
        return buildTree(g , root);
    }

    private static TreeNode buildTree(List<List<Integer>> g, TreeNode node) {
        for (int neighbor : g.get(node.getId())) {
            if (node.getParent() != null && neighbor == node.getParent().getId()) {
                continue;
            }

            TreeNode child = new TreeNode(neighbor, node);
            node.addChildren(child);

            buildTree(g, child);
        }
        return node;
    }

    public static String encode(TreeNode node) {
        if (node == null) {
            return "";
        }
        List<String> labels = new LinkedList<>();
        for (TreeNode child : node.getChildren()) {
            labels.add(encode(child));
        }
        Collections.sort(labels);
        String sb = String.join("", labels);
        return "(" + sb + ")";
    }
}
