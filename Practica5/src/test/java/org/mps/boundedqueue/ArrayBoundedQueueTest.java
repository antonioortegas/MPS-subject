package org.mps.boundedqueue;

import org.junit.jupiter.api.*;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

/*

    === TESTS ===

    * This class contains the tests for the ArrayBoundedQueue class.
    * The tests are divided in nested classes, each one containing tests for a specific method.
    * There are five tipe of tests, each with the following cases:

    + constructor tests
     - size less than 1 rises exception
     - size greater than 0 creates a queue with all elements null
    + put tests
     - putting an element in a queue with size 0
     - putting an element in a full queue rises exception
     - putting null rises exception
    + get tests
     - getting an element from an empty queue rises exception
     - getting an element from a queue with size 3
    + first item tests
     - first item field updates correctly when going from position n-1 to 0
     - first item field updates correctly when is not going from position n-1 to 0
    + iterator tests
     - iterator always updates correctly, even when going from position n-1 to 0
     - iterator throws exception when there are no more elements

    === ===

 */

class ArrayBoundedQueueTest {

    ArrayBoundedQueue<Integer> queue;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Nested
    @DisplayName("contructor tests")
    class constructor{

        @Test
        @DisplayName("size less than 1 rises exception")
        void shouldCreatingAQueueOfSizeLessThan1RiseAnException() {

            assertThatThrownBy(() -> new ArrayBoundedQueue<Integer>(0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("ArrayBoundedException: capacity must be positive");

            assertThatThrownBy(() -> new ArrayBoundedQueue<Integer>(-5))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("ArrayBoundedException: capacity must be positive");
        }

        @Test
        @DisplayName("size greater than 0 creates a queue with all elements null")
        void shouldCreatingAQueueOfSizeGreaterThan0CreatesAQueueWithAllElementsNull() {

            queue = new ArrayBoundedQueue<Integer>(5);
            Boolean valid = false;

            Object[] buffer = (Object[]) ReflectionTestUtils.getField(queue, "buffer");

            for (Object o : buffer) {
                if(o == null) {
                    valid = true;
                }
            }

            assertThat(valid).isTrue();
            assertThat(queue.size()).isEqualTo(0);
            assertThat(queue.isEmpty()).isTrue();
        }

    }

    @Nested
    @DisplayName("put tests")
    class put{

        @Test
        @DisplayName("putting null rises exception")
        void shouldPuttingNullRiseAnException() {

            queue = new ArrayBoundedQueue<Integer>(5);

            assertThatThrownBy(() -> queue.put(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("put: element cannot be null");
        }

        @Test
        @DisplayName("putting an element in a queue with size 0")
        void shouldPuttingAnElementInAQueueWithSize0() {

            queue = new ArrayBoundedQueue<Integer>(5);

            queue.put(1);

            assertThat(queue.size()).isEqualTo(1);
            assertThat(queue.isEmpty()).isFalse();
        }

        @Test
        @DisplayName("putting an element in a full queue rises exception")
        void shouldPuttingAnElementInAQueueWithSize5RiseAnException() {

            queue = new ArrayBoundedQueue<Integer>(5);

            queue.put(1);
            queue.put(2);
            queue.put(3);
            queue.put(4);
            queue.put(5);

            assertThatThrownBy(() -> queue.put(6))
                    .isInstanceOf(FullBoundedQueueException.class)
                    .hasMessageContaining("put: full bounded queue");
        }

    }

    @Nested
    @DisplayName("get tests")
    class get{

        @Test
        @DisplayName("getting an element from an empty queue rises exception")
        void shouldGettingAnElementFromAnEmptyQueueRiseAnException() {

            queue = new ArrayBoundedQueue<Integer>(5);

            assertThatThrownBy(() -> queue.get())
                    .isInstanceOf(EmptyBoundedQueueException.class)
                    .hasMessageContaining("get: empty bounded queue");
        }

        @Test
        @DisplayName("getting an element from a queue with size 3")
        void shouldGettingAnElementFromAQueueWithSize3ReturnTheCorrectElementAndDecreaseSizeBy1() {

            queue = new ArrayBoundedQueue<Integer>(5);
            Integer[] buffer = {1, 2, 3, null, null};
            ReflectionTestUtils.setField(queue, "buffer", buffer);
            ReflectionTestUtils.setField(queue, "size", 3);

            Integer expectedElement = 1;
            Integer expectedSize = 2;

            Integer actualElement = queue.get();
            Integer actualSize = queue.size();

            assertThat(actualElement).isEqualTo(expectedElement);
            assertThat(actualSize).isEqualTo(expectedSize);
        }

    }

    @Nested
    @DisplayName("first item tests")
    class firstItem{

        @Test
        @DisplayName("first item field updates correctly when going from position n-1 to 0")
        void shouldFirstItemFieldUpdateCorrectlyWhenGoingFromPositionNMinus1To0() {

            queue = new ArrayBoundedQueue<Integer>(3);
            Integer[] buffer = {1, null, 3};
            ReflectionTestUtils.setField(queue, "buffer", buffer);
            ReflectionTestUtils.setField(queue, "first", 2); // position n-1
            ReflectionTestUtils.setField(queue, "size", 2);
            ReflectionTestUtils.setField(queue, "nextFree", 1);

            queue.get();

            Integer expectedFirstPosition = 0;
            Integer actualFirstPosition = (Integer) ReflectionTestUtils.getField(queue, "first");

            assertThat(actualFirstPosition).isEqualTo(expectedFirstPosition);
        }

        @Test
        @DisplayName("first item field updates correctly when is not going from position n-1 to 0")
        void shouldFirstItemFieldUpdateCorrectlyWhenIsNotGoingFromPositionNMinus1To0() {

            queue = new ArrayBoundedQueue<Integer>(3);
            Integer[] buffer = {1, 2, null};
            ReflectionTestUtils.setField(queue, "buffer", buffer);
            ReflectionTestUtils.setField(queue, "first", 0);
            ReflectionTestUtils.setField(queue, "size", 2);
            ReflectionTestUtils.setField(queue, "nextFree", 2);

            queue.get();

            Integer expectedFirstPosition = 1;
            Integer actualFirstPosition = (Integer) ReflectionTestUtils.getField(queue, "first");

            assertThat(actualFirstPosition).isEqualTo(expectedFirstPosition);
        }
    }

    @Nested
    @DisplayName("iterator tests")
    class iterator{

        @Test
        @DisplayName("iterator always updates correctly, even when going from position n-1 to 0")
        void shouldIteratorReturnTheCorrectElements() {

            queue = new ArrayBoundedQueue<Integer>(5);
            queue.put(1);
            queue.put(2);
            queue.put(3);
            queue.put(4);
            queue.put(5);
            queue.get();
            queue.get();
            queue.put(6); // queue is now {6, null, 3, 4, 5}

            Integer[] expectedElements = {3, 4, 5, 6};
            Integer[] actualElements = new Integer[4];
            Object[] buffer = (Object[]) ReflectionTestUtils.getField(queue, "buffer");
            int current = (Integer) ReflectionTestUtils.getField(queue.iterator(), "current");

            for (int i = 0; i < 4; i++) {
                actualElements[i] = (Integer) buffer[current];
                current = (current + 1) % buffer.length;
            }

            assertThat(actualElements).isEqualTo(expectedElements);
        }

        @Test
        @DisplayName("iterator next method rises exception when there are no more elements")
        void shouldIteratorNextMethodRiseAnExceptionWhenThereAreNoMoreElements() {

            queue = new ArrayBoundedQueue<Integer>(5);
            queue.put(1);
            queue.put(2);
            queue.put(3);
            queue.put(4);
            queue.put(5);
            queue.get();
            queue.get();
            queue.put(6); // queue is now {6, null, 3, 4, 5}

            Iterator<Integer> iterator = queue.iterator();

            iterator.next();
            iterator.next();
            iterator.next();
            iterator.next();

            assertThatThrownBy(() -> iterator.next())
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessageContaining("iterator exhausted");
        }

    }

}