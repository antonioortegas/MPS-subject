package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/*
    TESTS:
    1- Node with neither next node nor previous node
        1.1 - Node returns the same item value
        1.2 - Node is first node
        1.3 - Node is last node
        1.4 - Node returns the changed item value
        1.5 - Node is terminal (No previous nor next node)
        2- Node with next or previous node
            2.1 - Node with next Node returns next node (Getter)
            2.2 - Node with previous Node returns previous node (Getter)
            2.3 - Node is not terminal with previous node
            2.4 - Node is not terminal with next node
            3- Node with both next and previous node
                3.1 - Node next node setter changes next node, not previous node (Setter)
                3.2 - Node previous node setter changes previous node, not next node (Setter)
                3.3 - Node is not terminal (Has both next and previous node)

 */

/**
 * @author Antonio Ortega Santaolalla
 * @author Teodoro Hidalgo Guerrero
 * @author Ignacio Jose Garcia Garcia
 */
class DequeNodeTest {

    private DequeNode<String> node;

    @BeforeEach
    void setUp() {
        node = new DequeNode<>("test", null, null);
    }

    @AfterEach
    void tearDown() {
        node = null;
    }

    @Nested
    @DisplayName("Neither with next node nor previous node")
    class EmptyNode {

        @Test
        @DisplayName("getItem returns the node's item")
        void checkNodeReturnsSameItem() {
            assertEquals("test", node.getItem());
        }

        @Test
        @DisplayName("getItem returns the correct value after a change")
        void checkNodeRefreshItemValue() {
            node.setItem("changedItemValue");

            assertEquals("changedItemValue", node.getItem());
        }

        @Test
        @DisplayName("First returns true because it is the first node")
        void checkFirstNodeReturnsTrueIfFirst() {
            assertTrue(node.isFirstNode());
        }

        @Test
        @DisplayName("Last returns true because it is the last node")
        void checkLastNodeReturnsTrueIfLast() {
            assertTrue(node.isLastNode());
        }

        @Test
        @DisplayName("Terminal returns false")
        void checkTerminalIfHasntPreviousNorNextNode() {
            DequeNode<String> nextNode = new DequeNode<>("next", null, null);

            assertFalse(nextNode.isNotATerminalNode());
        }


        @Nested
        @DisplayName("With previous node or next node")
        class NodeWithNeighbour {

            @Test
            @DisplayName("getNext returns the next node if there is one")
            void checkNextNodeGetterReturnsNextNode() {
                DequeNode<String> previousnode = new DequeNode<>("previous", null, node);

                assertEquals(node, previousnode.getNext());
            }

            @Test
            @DisplayName("getPrevious returns the previous node if there is one")
            void checkPreviousNodeGetterReturnsPreviousNode() {
                DequeNode<String> nextnode = new DequeNode<>("next", node, null);

                assertEquals(node, nextnode.getPrevious());
            }

            @Test
            @DisplayName("node is not terminal if it has a next node")
            void checkTerminalIfHasNextNode() {
                DequeNode<String> previousNode = new DequeNode<>("previous", null, node);

                assertFalse(previousNode.isNotATerminalNode());
            }

            @Test
            @DisplayName("node is not terminal if it has a previous node")
            void checkTerminalIfHasPreviousNode() {
                DequeNode<String> nextNode = new DequeNode<>("next", node, null);

                assertFalse(nextNode.isNotATerminalNode());
            }

            @Test
            @DisplayName("node is not terminal because it has both a previous and a next node")
            void checkNotTerminalIfHasPreviousAndNextNode() {
                DequeNode<String> nextNode = new DequeNode<>("middle", node, node);

                assertTrue(nextNode.isNotATerminalNode());
            }


            @Nested
            @DisplayName("With both next and previous nodes")
            class FullNeighbourNode {

                @Test
                @DisplayName("next node is changed correctly after setNext")
                void checkSetterChangesNextNode() {
                    DequeNode<String> newNode = new DequeNode<>("middle", node, node);
                    DequeNode<String> nextNode = new DequeNode<>("next", null, null);

                    assertEquals(newNode.getNext(), node);

                    newNode.setNext(nextNode);

                    assertEquals(nextNode, newNode.getNext());
                    assertEquals(node, newNode.getPrevious());
                }

                @Test
                @DisplayName("previous node is changed correctly after setPrevious")
                void checkSetterChangesPreviousNode() {
                    DequeNode<String> newNode = new DequeNode<>("middle", node, node);
                    DequeNode<String> previousNode = new DequeNode<>("previous", null, null);

                    assertEquals(newNode.getPrevious(), node);

                    newNode.setPrevious(previousNode);

                    assertEquals(previousNode, newNode.getPrevious());
                    assertEquals(node, newNode.getNext());
                }



            }
        }

    }

}