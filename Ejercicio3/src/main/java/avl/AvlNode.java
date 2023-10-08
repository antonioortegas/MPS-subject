/*
Antonio Ortega Santaolalla
 */

package avl;

/**
 * Created with IntelliJ IDEA. User: Antonio J. Nebro Date: 08/07/13 Time: 15:46
 */
public class AvlNode<T> {

    private AvlNode<T> left;
    private AvlNode<T> right;
    private AvlNode<T> parent;

    private int height;

    private AvlNode<T> closestNode;

    private T item;

    /**
     * Constructor
     *
     * @param item
     */
    public AvlNode(T item) {
        this.left = null;
        this.right = null;
        this.parent = null;
        height = 0;
        closestNode = null;

        this.item = item;
    }

    public AvlNode<T> getLeft() {
        return left;
    }

    public void setLeft(AvlNode<T> left) {
        this.left = left;
    }

    public AvlNode<T> getParent() {
        return parent;
    }

    public void setParent(AvlNode<T> parent) {
        this.parent = parent;
    }

    public AvlNode<T> getRight() {
        return right;
    }

    public void setRight(AvlNode<T> right) {
        this.right = right;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void updateHeight() {
        if (!hasLeft() && !hasRight()) {
            height = 0;
        } else if (!hasRight()) {
            height = 1 + getLeft().getHeight();
        } else if (!hasLeft()) {
            height = 1 + getRight().getHeight();
        } else {
            height = 1 + Math.max(getLeft().getHeight(), getRight().getHeight());
        }
    }

    public AvlNode<T> getClosestNode() {
        return closestNode;
    }

    public void setClosestNode(AvlNode<T> closestNode) {
        this.closestNode = closestNode;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public boolean isLeaf() {

        return (!hasLeft() && !hasRight());
    }

    public boolean hasOnlyALeftChild() {
        return (hasLeft() && !hasRight());
    }

    public boolean hasOnlyARightChild() {
        return (hasRight() && !hasLeft());
    }
}
