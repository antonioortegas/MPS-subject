package avl;

import org.junit.jupiter.api.*;

import java.util.Comparator;

import static org.assertj.core.api.AssertionsForClassTypes.*;

/*
    * Implemented tests
    * From an empty tree
    * 1. Check properties of empty tree
    * 2. Insert a node at the top
    * 3. Insert a deleted node at the top and see if it is correctly updated
    * From a tree with a node at the top
    * 4. Insert a node left of the top
    * 5. Insert a node right of the top
    * 6. Insert a node left and right of the top
    * Rebalancing tests
    * 7. Inserting a node left twice
    * 8. Inserting a node right twice
    * 9. Inserting a node left and right
 */

@Nested
@DisplayName("Starting with an empty tree")
class AvlTreeTest {

    private AvlTree<Integer> tree;
    private Comparator comparator;

    @BeforeEach
    void setUp() {
        comparator = comparator = Comparator.comparingInt((Integer o) -> o);
        tree = new AvlTree<Integer>(comparator);
    }

    @AfterEach
    public void tearDown() {
        tree = null;
        comparator = null;
    }



    @Test
    @DisplayName("Should be empty when created")
    public void shouldTreeBeEmptyWhenANewTreeIsCreated() {
        assertThat(tree.avlIsEmpty())
                .isTrue();
    }

    @Test
    @DisplayName("Should not be empty when a node is inserted")
    public void shouldTreeNotBeEmptyWhenANodeIsInserted() {
        AvlNode<Integer> node = new AvlNode<Integer>(1);
        tree.insertAvlNode(node);
        String expected = " | 1";

        assertThat(tree.avlIsEmpty())
                .isFalse();
        assertThat(tree.getTop())
                .isEqualTo(node);
        assertThat(tree.height(tree.getTop()))
                .isEqualTo(0);
        assertThat(tree.toString())
                .isEqualTo(expected);
        assertThat(node.isLeaf())
                .isTrue();
    }

    @Test
    @DisplayName("Should be empty when a node is inserted and removed")
    public void shouldTreeBeEmptyWhenANodeIsInsertedAndRemoved() {
        tree.insert(1);
        tree.delete(1);

        assertThat(tree.avlIsEmpty())
                .isTrue();
    }

    @Nested
    @DisplayName("When a node has already been inserted")
    class nodeInserted{

        @Test
        @DisplayName("Inserting a node left")
        public void shouldTheCorrectTreeBeGeneratedWhenInsertedANodeLeft() {
            AvlNode<Integer> node = new AvlNode<Integer>(2);
            tree.insertAvlNode(node);

            AvlNode<Integer> left = new AvlNode<Integer>(1);
            tree.insertAvlNode(left);

            assertThat(node.hasOnlyALeftChild())
                    .isTrue();
            assertThat(node.isLeaf())
                    .isFalse();
            assertThat(left.isLeaf())
                    .isTrue();
        }

        @Test
        @DisplayName("Inserting a node right")
        public void shouldTheCorrectTreeBeGeneratedWhenInsertedANodeRight() {
            AvlNode<Integer> node = new AvlNode<Integer>(2);
            tree.insertAvlNode(node);

            AvlNode<Integer> right = new AvlNode<Integer>(3);
            tree.insertAvlNode(right);

            assertThat(node.hasOnlyARightChild())
                    .isTrue();
            assertThat(node.isLeaf())
                    .isFalse();
            assertThat(right.isLeaf())
                    .isTrue();
        }

        @Test
        @DisplayName("Inserting nodes left and right of the root results in the correct tree")
        public void shouldInsertingNodesLeftAndRightOfTheRootResultInTheCorrectTree() {
            AvlNode<Integer> node = new AvlNode<Integer>(2);
            tree.insertAvlNode(node);

            AvlNode<Integer> left = new AvlNode<Integer>(1);
            AvlNode<Integer> right = new AvlNode<Integer>(3);

            //Check Equilibrium Factor before insertion
            assertThat(tree.searchClosestNode(left))
                    .isEqualTo(-1);
            assertThat(tree.searchClosestNode(right))
                    .isEqualTo(1);
            assertThat(tree.searchClosestNode(node))
                    .isEqualTo(0);

            assertThat(node.hasOnlyALeftChild())
                    .isFalse();
            assertThat(node.hasOnlyARightChild())
                    .isFalse();

            //Insert nodes
            tree.insertAvlNode(left);
            tree.insertAvlNode(right);

            String expected = " | 2 | 1 | 3";

            assertThat(tree.getTop())
                    .isEqualTo(node);
            assertThat(tree.avlIsEmpty())
                    .isFalse();
            assertThat(tree.height(tree.getTop()))
                    .isEqualTo(1);
            assertThat(tree.toString())
                    .isEqualTo(expected);

            assertThat(node.hasLeft()).isTrue();
            assertThat(node.hasRight()).isTrue();
            assertThat(node.getLeft()).isEqualTo(left);
            assertThat(node.getRight()).isEqualTo(right);
        }

        @Nested
        @DisplayName("Rebalancing the tree")
        class rebalancingTheTree {

            @Test
            @DisplayName("Inserting nodes left twice")
            public void shouldBalanceTheTreeWhenInsertingLeftLeft() {
                AvlNode<Integer> node = new AvlNode<Integer>(3);
                AvlNode<Integer> node3 = new AvlNode<Integer>(3);
                AvlNode<Integer> node2 = new AvlNode<Integer>(2);
                AvlNode<Integer> node1 = new AvlNode<Integer>(1);
                tree.insertAvlNode(node1);
                tree.insertAvlNode(node2);
                tree.insertAvlNode(node3);

                node = tree.search(1);
                assertThat(tree.findSuccessor(node))
                        .isEqualTo(tree.search(2));

                node = tree.search(2);
                assertThat(tree.findSuccessor(node))
                        .isEqualTo(tree.search(3));

                node = tree.search(3);
                assertThat(tree.findSuccessor(node))
                        .isNull();

                String expected = " | 2 | 1 | 3";
                assertThat(tree.toString())
                        .isEqualTo(expected);


            }

            @Test
            @DisplayName("Inserting nodes right twice")
            public void shouldBalanceTheTreeWhenInsertingRightRight() {
                AvlNode<Integer> node = new AvlNode<Integer>(1);
                AvlNode<Integer> node1 = new AvlNode<Integer>(2);
                AvlNode<Integer> node2 = new AvlNode<Integer>(3);
                tree.insertAvlNode(node);
                tree.insertAvlNode(node1);
                tree.insertAvlNode(node2);

                node = tree.search(1);
                assertThat(tree.findSuccessor(node))
                        .isEqualTo(tree.search(2));

                node = tree.search(2);
                assertThat(tree.findSuccessor(node))
                        .isEqualTo(tree.search(3));

                node = tree.search(3);
                assertThat(tree.findSuccessor(node))
                        .isNull();

                String expected = " | 2 | 1 | 3";
                assertThat(tree.toString())
                        .isEqualTo(expected);
            }

            @Test
            @DisplayName("Rebalancing the tree when inserting left and right")
            public void testInsertingLeftRightNodeAndRebalance() throws Exception {
                AvlNode<Integer> node1, node2, node3;

                node1 = new AvlNode<Integer>(7);
                tree.insertAvlNode(node1);

                node2 = new AvlNode<Integer>(2);
                tree.insertAvlNode(node2);

                node3 = new AvlNode<Integer>(3);
                tree.insertAvlNode(node3);

                assertThat(node3).isEqualTo(tree.getTop());
                assertThat(node2).isEqualTo(node3.getLeft());
                assertThat(node1).isEqualTo(node3.getRight());

                assertThat(1).isEqualTo(tree.getTop().getHeight());
                assertThat(0).isEqualTo(tree.getTop().getLeft().getHeight());
                assertThat(0).isEqualTo(tree.getTop().getRight().getHeight());
                assertThat(-1).isEqualTo(tree.height(node2.getLeft()));
                assertThat(-1).isEqualTo(tree.height(node2.getRight()));
                assertThat(-1).isEqualTo(tree.height(node1.getLeft()));
                assertThat(-1).isEqualTo(tree.height(node1.getRight()));

                String tree = " | 3 | 2 | 7";
                assertThat(tree).isEqualTo(tree.toString());
            }
        }
    }
}