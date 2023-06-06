import java.util.ArrayList;


public class MyBST<K extends Comparable<K>, V> {
    MyBSTNode<K, V> root = null;
    int size = 0;

    public int size() {
        return size;
    }

    /**
     * help insert key value
     * @param node current node
     * @param key given key
     * @param value given value
     */
    public void helpInsert(MyBSTNode<K,V> curNode,MyBSTNode<K,V> parentNode, K key, V value){
        if(curNode == null){
            curNode = new MyBSTNode<K,V>(key, value, parentNode);
        }
        if(key.compareTo(curNode.getKey()) > 0){
            helpInsert(curNode.getRight(),curNode, key, value);
        }
        else{
            helpInsert(curNode.getLeft(),curNode,key,value);
        }
    }

    /**
     * Insert a new node containing the arguments key and value 
     * into the binary search tree according to the binary search 
     * tree properties. Update the tree size accordingly.
     * @param key given key
     * @param value given value
     * @return the value replaced; null if no replacement happened
     */
    public V insert(K key, V value) {
        if(key == null){
            throw new NullPointerException();
        }
        if(search(key) != null){
            helpSearch(root, key).setValue(value);
            return search(key);
        }
        helpInsert(root,null, key, value);
        size ++;
        return null;
    }

    /**
     * help to search the key
     * @param node given node
     * @param key key to be searched
     * @return the node that has the key
     */
    public MyBSTNode<K,V> helpSearch(MyBSTNode<K,V> node, K key){
        if(node.getKey().compareTo(key) == 0){
            return node;
        }
        if(key.compareTo(node.getKey()) > 0){
            if(node.getRight() == null){
                return null;
            }
            return helpSearch(node.getRight(), key);
        }
        if(key.compareTo(node.getKey()) < 0){
            if(node.getLeft() == null){
                return null;
            }
            return helpSearch(node.getLeft(),key);
        }
        return null;
    }

    /**
     * Search for a node with key equal to key and return the 
     * value associated with that node. The tree structure 
     * should not be affected by this.
     * @param key the key to be searched
     * @return the value matching the key
     */
    public V search(K key) {
        if(key == null){
            return null;
        }
        MyBSTNode<K,V> result = helpSearch(root, key);
        if(result == null){
            return null;
        }
        else{
            return result.getValue();
        }
    }

    public V remove(K key) {
        // TODO
        return null;
    }

    public ArrayList<MyBSTNode<K, V>> inorder() {
        // TODO
        return null;
    }

    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode storing specified data
         *
         * @param key    the key the MyBSTNode will store
         * @param value  the data the MyBSTNode will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode
         *
         * @return the key stored in the MyBSTNode
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode
         *
         * @return the data stored in the MyBSTNode
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        /**
         * help find the smallest successor
         * @param node recurssive input right node
         * @return the node that has no left child
         */
        public MyBSTNode<K,V> helpFindSuccessor(MyBSTNode<K,V> node){
            if(getLeft() == null){
                return this;
            }
            return helpFindSuccessor(node.getRight());
        }

        /**
         * This method returns the node with the smallest key that is 
         * still larger than the key of the current node. If there is 
         * no larger key, return null.
         * @return successor MyBSTNode
         */
        public MyBSTNode<K, V> successor() {
            if(getParent() == null && getRight() == null){
                return null;
            }
            if(getRight() == null){
                if(this == getParent().getRight()){
                    return null;
                }
                else{
                    return getParent();
                }
            }
            return helpFindSuccessor(getRight());
        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null :
                    this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null :
                    this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
