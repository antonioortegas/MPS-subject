package avl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

/*
 * Implemented tests
 * From an empty tree
 * 1. Check properties of a node when it is the root and has no children
 * 2. Check properties of a node when it is the root and has a left child
 * 3. Check properties of a node when it is the root and has a right child
 * 4. Check properties of a node when it is the root and has both children
 * 5. Update height of a node
 * 6. Update parent of a node
 * 7. Update item of a node
 * 8. Update closest node of a node
 */


class AvlNodeTest {

    private AvlNode<Integer> node;

    @BeforeEach
    void setUp() {
        node = new AvlNode<Integer>(2);
    }

    @AfterEach
    public void tearDown() {
        node = null;
    }

    @Test
    @DisplayName("Only root")
    public void shouldFunctionsReturnCorrectValuesWhenNodeIsRootWithNoChild() {
        node.updateHeight();

        assertThat(node.getParent())
                .isNull();
        assertThat(node.hasParent())
                .isFalse();
        assertThat(node.getHeight())
                .isEqualTo(0);
        assertThat(node.isLeaf())
                .isTrue();
    }

    @Test
    @DisplayName("Set left")
    public void shouldHasLeftReturnTrueAndHeightBeCorrectlyUpdatedWhenDefiningALeftNode() {
        AvlNode<Integer> left = new AvlNode<Integer>(1);
        node.setLeft(left);
        node.updateHeight();

        assertThat(node.getHeight())
                .isEqualTo(1);
        assertThat(node.hasRight())
                .isFalse();
        assertThat(node.hasOnlyARightChild())
                .isFalse();
        assertThat(node.hasLeft())
                .isTrue();
        assertThat(node.hasOnlyALeftChild())
                .isTrue();
    }

    @Test
    @DisplayName("Set right")
    public void shouldHasRightReturnTrueAndHeightBeCorrectlyUpdatedWhenDefiningARightNode() {
        AvlNode<Integer> right = new AvlNode<Integer>(3);
        node.setRight(right);
        node.updateHeight();

        assertThat(node.getHeight())
                .isEqualTo(1);
        assertThat(node.hasRight())
                .isTrue();
        assertThat(node.hasOnlyARightChild())
                .isTrue();
        assertThat(node.hasLeft())
                .isFalse();
        assertThat(node.hasOnlyALeftChild())
                .isFalse();
    }

    @Test
    @DisplayName("Set both left and right")
    public void shouldHasLeftAndHasRightReturnTrueAndHeightBeCorrectlyUpdatedWhenDefiningBothLeftAndRightNodes() {
        AvlNode<Integer> left = new AvlNode<Integer>(1);
        AvlNode<Integer> right = new AvlNode<Integer>(3);
        node.setLeft(left);
        node.setRight(right);
        node.updateHeight();

        assertThat(node.getHeight())
                .isEqualTo(1);
        assertThat(node.hasRight())
                .isTrue();
        assertThat(node.hasOnlyARightChild())
                .isFalse();
        assertThat(node.hasLeft())
                .isTrue();
        assertThat(node.hasOnlyALeftChild())
                .isFalse();
    }




    @Test
    @DisplayName("Set height")
    public void shouldGetHeightReturn8WhenSettingHeightTo8() {
        int expectedHeight =  8;
        node.setHeight(expectedHeight);

        assertThat(node.getHeight())
                .isEqualTo(expectedHeight);
    }

    @Test
    @DisplayName("Set item")
    public void shouldGetItemReturn8WhenChangingItemTo8() {
        int expectedItem =  8;
        node.setItem(expectedItem);

        assertThat(node.getItem())
                .isEqualTo(expectedItem);
    }

    @Test
    @DisplayName("Set parent")
    public void shouldParentBeCorrectlySetAndReturnedWhenAssigned() {
        AvlNode<Integer> parent = new AvlNode<Integer>(1);
        node.setParent(parent);

        assertThat(node.getParent())
                .isEqualTo(parent);
    }

    @Test
    @DisplayName("Set closest node")
    public void shouldClosestNodeBeCorrectlySetAndReturnedWhenAssigned() {
        AvlNode<Integer> closestNode = new AvlNode<Integer>(1);
        node.setClosestNode(closestNode);

        assertThat(node.getClosestNode())
                .isEqualTo(closestNode);
    }
}