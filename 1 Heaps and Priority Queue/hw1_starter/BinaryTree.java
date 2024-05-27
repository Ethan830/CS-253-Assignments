/**
 * An interface for a binary tree, in which each node has at most two children
 */
public interface BinaryTree<E> extends Tree<E> {

    /**
     * Returns the Position of p's left child (or null if no child exists)
     * @param p - input Position
     * @return the left child of p in the binary tree if the child exists, or null if it does not
     * @throws IllegalArgumentException
     */
    Position<E> left(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the Position of p's right child (or null if no child exists)
     * @param p - input Position
     * @return the right child of p in the binary tree if the child exists, or null if it does not
     * @throws IllegalArgumentException
     */
    Position<E> right(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the Position of p's sibling (or null if no sibling exists)
     * @param p - input Position
     * @return the Position of the sibling of p, if it exists, or null if no sibling exists
     * @throws IllegalArgumentException
     */
    Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}