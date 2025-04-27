package models;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;
    
    private class Node {
        private K key;
        private V val;
        private Node left, right;
        
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    
    public BST() {
        this.root = null;
        this.size = 0;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void put(K key, V val) {
        root = put(root, key, val);
    }
    
    private Node put(Node node, K key, V val) {
        if (node == null) {
            size++;
            return new Node(key, val);
        }
        
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val;
        }
        
        return node;
    }
    
    public V get(K key) {
        Node node = get(root, key);
        return node == null ? null : node.val;
    }
    
    private Node get(Node node, K key) {
        if (node == null) {
            return null;
        }
        
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }
    
    public void delete(K key) {
        root = delete(root, key);
    }
    
    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }
        
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            size--;

            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        
        return node;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    public class Entry {
        private K key;
        private V value;
        
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public K getKey() {
            return key;
        }
        
        public V getValue() {
            return value;
        }
        
        @Override
        public String toString() {
            return key + ": " + value;
        }
    }

    public Iterable<Entry> iterator() {
        return new Iterable<Entry>() {
            @Override
            public Iterator<Entry> iterator() {
                return new InOrderIterator();
            }
        };
    }
    
    private class InOrderIterator implements Iterator<Entry> {
        private Stack<Node> stack;
        private Node current;
        
        public InOrderIterator() {
            stack = new Stack<>();
            current = root;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }
        
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        
        @Override
        public Entry next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            Node node = stack.pop();
            Entry entry = new Entry(node.key, node.val);
            
            current = node.right;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            return entry;
        }
    }
}