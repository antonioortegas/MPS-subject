package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
/*
  TESTS:
  1- Deque is empty
      --- Primera Entrega
      1.1 - Size should be 0
      1.2 - First and last nodes should be null
      1.3 - Prepend adds the first node
      1.4 - Append adds the last node
      1.5 - First element can't be deleted
      1.6 - Last element can't be deleted
      --- Segunda Entrega
      1.7 - Cannot get an element
      1.8 - Remove element returns same size
      1.9 - Sorting empty queue returns empty queue
  2- List is not empty
      --- Primera Entrega
      2.1 - Prepend adds first node
      2.2 - Append adds last node
      2.3 - Delete the first element only
      2.4 - Delete the last element only
      2.5 - Delete the first element if list has one node returns empty deque
      2.6 - Delete the last element if list has one node returns empty deque
      --- Segunda Entrega
      2.7 - Contains the first value in the deque
      2.8 - Contains the last value in the deque
      2.9 - Contains non-terminal values in the deque
      2.10 - Doesnt contain a value not in the deque
      2.11 - Get throws exception if index is out of bounds
      2.12 - Get throws exception if index is negative
      2.13 - Get 0 returns the first element
      2.14 - Get size-1 returns the last element
      2.15 - Removing the last and only element leaves the deque empty
      2.16 - Removing the first element change first element
      2.16 - Removing the last element change last element
      2.17 - Sort arranges nodes in ascending order
      2.18 - Sort arranges nodes in descending order
 */

/**
 * @author Antonio Ortega Santaolalla
 * @author Teodoro Hidalgo Guerrero
 * @author Ignacio Jose Garcia Garcia
 */
@DisplayName("Deque")
class DoublyLinkedListDequeTest {

    private DoublyLinkedListDeque<Integer> deque;

    @BeforeEach
    void setUp() {
        deque = new DoublyLinkedListDeque<>();
    }

    @AfterEach
    void tearDown() {
        deque = null;
    }

    @Nested
    @DisplayName("is empty")
    class EmptyDeque {
//        LAST

        @Test
        @DisplayName("size should be 0")
        void sizeShouldBeZero() {
            int expectedValue = 0;
            int actualValue = deque.size();

            assertThat(actualValue).isEqualTo(expectedValue);
        }

//        FIRST AND LAST

        @Test
        @DisplayName("first and last should be null")
        void firstAndLastShouldBeNull() {
            assertThrows(DoubleEndedQueueException.class, () -> deque.first());
            assertThrows(DoubleEndedQueueException.class, () -> deque.last());
        }

//        DELETE


        @Test
        @DisplayName("the first element cannot be deleted")
        void deleteFirstRisesAnExceptionIfEmpty() {
            assertThrows(DoubleEndedQueueException.class, () -> deque.deleteFirst());
        }

        @Test
        @DisplayName("the last element cannot be deleted")
        void deleteLastRisesAnExceptionIfEmpty() {
            assertThrows(DoubleEndedQueueException.class, () -> deque.deleteLast());
        }

//        PREPEND AND APPEND

        @ParameterizedTest(name = "prepend adds the first node with value {0}")
        @DisplayName("prepend adds the first node")
        @ValueSource(ints = {1, 2, 3, 4, 5})
        void prependAddsTheFirstNodeToDequeWhenDequeIsEmpty(int expectedValue) {
            int expectedSizeValue = 1;

            deque.prepend(expectedValue);

            assertThat(deque.first()).isEqualTo(expectedValue);
            assertThat(deque.last()).isEqualTo(expectedValue);
            assertThat(deque.size()).isEqualTo(expectedSizeValue);
        }

        @ParameterizedTest(name = "append adds the first node with value {0}")
        @DisplayName("append adds the last node")
        @ValueSource(ints = {1, 2, 3, 4, 5})
        void appendAddsTheFirstNodeToDequeWhenDequeIsEmpty(int expectedValue) {
            int expectedSizeValue = 1;

            deque.append(expectedValue);

            assertThat(deque.first()).isEqualTo(expectedValue);
            assertThat(deque.last()).isEqualTo(expectedValue);
            assertThat(deque.size()).isEqualTo(expectedSizeValue);
        }

//        GET

        @Test
        @DisplayName("cannot get an element")
        void getRisesAnExceptionIfEmpty() {
            assertThrows(IndexOutOfBoundsException.class, () -> deque.get(-1));
            assertThrows(IndexOutOfBoundsException.class, () -> deque.get(0));
            assertThrows(IndexOutOfBoundsException.class, () -> deque.get(1));
            assertThrows(IndexOutOfBoundsException.class, () -> deque.get(5));
        }

//        REMOVE

        @Test
        @DisplayName("remove returns same size")
        void removeReturnsSameSizeIfEmpty() {
            deque.remove(5);

            assertThat(deque.size()).isEqualTo(0);
        }
        @Test
        @DisplayName("sorting empty queue returns empty queue")
        void sortingEmptyqueueReturnsEmptyQueue(){
            deque.sort(Integer::compareTo);
            assertEquals(0,deque.size());
        }

        @Nested
        @DisplayName("after adding some elements")
        class NotEmptyDeque {
        
//            PREPEND AND APPEND

            @ParameterizedTest(name = "prepend add a node with value {0}")
            @DisplayName("prepend adds first node")
            @ValueSource(ints = {1, 2, 3, 4, 5})
            void prependAddsANodeAtHeadOfDeque(int expectedValue) {
                addDummiesToDeque(3);
                int expectedSizeValue = deque.size() + 1;

                deque.prepend(expectedValue);

                assertThat(deque.first()).isEqualTo(expectedValue);
                assertThat(deque.last()).isEqualTo(deque.last());
                assertThat(deque.size()).isEqualTo(expectedSizeValue);
            }

            @ParameterizedTest(name = "append add a node with value {0}")
            @DisplayName("append adds last node")
            @ValueSource(ints = {1, 2, 3, 4, 5})
            void appendAddsANodeAtTailOfDeque(int expectedValue) {
                addDummiesToDeque(3);
                int expectedSizeValue = deque.size() + 1;

                deque.append(expectedValue);

                assertThat(deque.first()).isEqualTo(deque.first());
                assertThat(deque.last()).isEqualTo(expectedValue);
                assertThat(deque.size()).isEqualTo(expectedSizeValue);
            }

//            DELETE

            @Test
            @DisplayName("delete the first element then it should be empty")
            void deleteFirstDeletesTheFirstAndOnlyNodeWhenDequeHasOneNode() {
                addDummiesToDeque(1);
                int expectedSize = 0;

                deque.deleteFirst();
                int actualSize = deque.size();

                assertThat(actualSize).isEqualTo(expectedSize);
                assertThrows(DoubleEndedQueueException.class, () -> deque.first());
                assertThrows(DoubleEndedQueueException.class, () -> deque.last());
            }

            @Test
            @DisplayName("delete the last element then it should be empty")
            void deleteLastDeletesTheLastAndOnlyNodeWhenDequeHasOneNode() {
                addDummiesToDeque(1);
                int expectedSize = 0;

                deque.deleteLast();
                int actualSize = deque.size();

                assertThat(actualSize).isEqualTo(expectedSize);
                assertThrows(DoubleEndedQueueException.class, () -> deque.first());
                assertThrows(DoubleEndedQueueException.class, () -> deque.last());
            }

            @Test
            @DisplayName("delete the first element only")
            void deleteFirstDeletesTheFirstNodeWhenDequeHasMoreThanOneNode() {
                addDummiesToDeque(3);
                int expectedSize = deque.size() - 1;

                deque.deleteFirst();
                int actualSize = deque.size();

                assertThat(actualSize).isEqualTo(expectedSize);
                assertEquals(deque.first(), 2);
                assertEquals(deque.last(), 3);
            }

            @Test
            @DisplayName("delete the last element only")
            void deleteLastDeletesTheLastNodeWhenDequeHasMoreThanOneNode() {
                addDummiesToDeque(3);
                int expectedSize = deque.size() - 1;

                deque.deleteLast();
                int actualSize = deque.size();

                assertThat(actualSize).isEqualTo(expectedSize);
                assertEquals(deque.first(), 1);
                assertEquals(deque.last(), 2);
            }

//            CONTAINS

            @ParameterizedTest(name = "doesnt contain value {0} when its not in the deque")
            @DisplayName("doesnt contain values not in the deque")
            @ValueSource(ints = {4, 5, 6, 7})
            void checkContainReturnFalseIfNotFoundInTheDeque(int n) {
                addDummiesToDeque(3);

                assertThat(deque.contains(n)).isFalse();
            }

            @ParameterizedTest(name = "contain value {0} appended to the deque")
            @DisplayName("contain the last value in the deque")
            @ValueSource(ints = {4, 5, 6, 7})
            void containsAppendedValueToTheDeque(int n) {
                addDummiesToDeque(3);

                deque.append(n);

                assertThat(deque.contains(n)).isTrue();
                assertThat(deque.last()).isEqualTo(n);
            }

            @ParameterizedTest(name = "contains value {0} preppended to the deque")
            @DisplayName("contain the first value in the deque")
            @ValueSource(ints = {4, 5, 6, 7})
            void containsPreppendedValueToTheDeque(int n) {
                addDummiesToDeque(3);
                deque.prepend(n);

                assertThat(deque.contains(n)).isTrue();
                assertThat(deque.first()).isEqualTo(n);
            }

            @ParameterizedTest(name = "contains value {0} in the deque")
            @DisplayName("contain non-terminal values in the deque")
            @ValueSource(ints = {4, 5, 6, 7})
            void checkContainReturnTrueIfFoundInTheDeque(int n) {
                addDummiesToDeque(3);
                deque.append(n);
                addDummiesToDeque(3);

                assertThat(deque.contains(n)).isTrue();
                assertThat(deque.first()).isEqualTo(1);
                assertThat(deque.last()).isEqualTo(3);
            }

//            GET

            @Test
            @DisplayName("get throws exception if index is out of queue range")
            void getWithIndexOutOfQueueThrowsException() {
                addDummiesToDeque(2);

                assertThatThrownBy(() -> deque.get(deque.size()))
                        .isInstanceOf(IndexOutOfBoundsException.class);
            }

            @Test
            @DisplayName("get throws exception if negative index")
            void getNegativeIndexThrowsException() {
                int i = -1;
                assertThrows(IndexOutOfBoundsException.class, () -> deque.get(i));
            }

            @Test
            @DisplayName("get 0 returns first element")
            void getZeroShowsFirstElement() {
                addDummiesToDeque(2);

                assertThat(deque.get(0)).isEqualTo(deque.first());
            }

            @Test
            @DisplayName("get size-1 returns last element")
            void getSizeMinusOneReturnsLastElement() {
                addDummiesToDeque(3);

                assertThat(deque.get(deque.size() - 1)).isEqualTo(3);
            }

//            REMOVE

            @Test
            @DisplayName("removing the last and only element leaves an empty queue")
            void removingTheLastElementLeavesAnEmptyQueue() {
                addDummiesToDeque(1);
                deque.remove(1);

                assertThat(deque.size()).isEqualTo(0);
            }

            @Test
            @DisplayName("removing first element change first element")
            void removingTheFirstElementReducesSizeByOneAndModifiesTheFirstElement() {
                addDummiesToDeque(4);
                deque.remove(1);

                assertThat(deque.size()).isEqualTo(3);
                assertThat(deque.first()).isEqualTo(2);
            }

            @Test
            @DisplayName("removing the last element change last element")
            void removingLastElementReducesSizeByOneAndModifiesTheFirstElement() {
                addDummiesToDeque(4);

                deque.remove(4);

                assertThat(deque.first()).isEqualTo(1);
                assertThat(deque.last()).isEqualTo(3);
            }

//            SORT

            @ParameterizedTest(name = "sort [{0},{1},{2},{3}] in ascending order")
            @DisplayName("sort arranges nodes in ascending order")
            @CsvSource({"1,2,3,4", "4,3,2,1", "1,3,2,4", "4,2,3,1"})
            void sortArrangesNodesCorrectlyInAscendingOrder(int n, int n1, int n2, int n3) {
                addToDeque(n, n1, n2, n3);

                deque.sort(Integer::compareTo);

                assertThat(deque.first()).isEqualTo(1);
                assertThat(deque.last()).isEqualTo(4);
            }

            @ParameterizedTest(name = "sort [{0},{1},{2},{3}] in descending order")
            @DisplayName("sort arranges nodes in descending order")
            @CsvSource({"1,2,3,4", "4,3,2,1", "1,3,2,4", "4,2,3,1"})
            void sortArrangesNodesCorrectlyInDescendingOrder(int n, int n1, int n2, int n3) {
                addToDeque(n, n1, n2, n3);

                deque.sort(Comparator.reverseOrder());

                assertThat(deque.first()).isEqualTo(4);
                assertThat(deque.last()).isEqualTo(1);
            }

            /**
             * Adds a number of nodes to the deque
             * starting from 1 to numberOfNodes
             *
             * @param numberOfNodes
             */
            private void addDummiesToDeque(int numberOfNodes) {
                for (int i = 1; i <= numberOfNodes; i++) {
                    deque.append(i);
                }
            }

            /**
             * Appends numbers to the deque
             *
             * @param numbers
             */
            private void addToDeque(int... numbers) {
                Arrays.stream(numbers).forEach(deque::append);
            }
        }
    }

}