package lab2;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_TWO;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author Denys Prykhodko, Burtsev Vladyslav
 * @param <E> type of elements in list
 */
public class ListImpl<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[] innerArray;
    private int size = INTEGER_ZERO;

    public ListImpl() {
        innerArray = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * calculate amount of elements in list
     * @return amount of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * check list on empty
     * @return return true if list empty, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return size == INTEGER_ZERO;
    }

    /**
     * check if list contains element
     * @param o element for check
     * @return true if contains, otherwise false
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) > INTEGER_MINUS_ONE;
    }

    /**
     * return iterator of elements in proper sequence
     * @return return iterator of elements in proper sequence
     */
    @Override
    public Iterator<E> iterator() {
        return iterator(t -> true);
    }

    /**
     * return iterator of elements in proper sequence, with functional interface Predicate
     * @param predicate functional interface
     * @return return iterator of elements in proper sequence, with functional interface Predicate
     */
    public Iterator<E> iterator(Predicate<E> predicate) {
        return new IteratorImpl(predicate);

    }

    /**
     * convert list to array with elements in proper sequence
     * @return array of elements from list in proper sequence
     */
    @Override
    public Object[] toArray() {
        return Arrays.stream(innerArray).toArray();
    }

    /**
     * convert to array of define type with elements in proper sequence
     * @param objects array where elements should be copy
     * @return array of define tpye with elements from list in proper sequence
     */
    @Override
    public Object[] toArray(Object[] objects) {

        if (!(objects instanceof Integer[])) {
            throw new ArrayStoreException();
        }

        if (objects.length < size) {
            objects = new Object[size];
            return Arrays.copyOf(innerArray, size, objects.getClass());
        }

        return Arrays.copyOf(innerArray, size, objects.getClass());
    }

    /**
     * add element to the list
     * @param o element that should be added
     * @return true if element was added, otherwise false
     */
    @Override
    public boolean add(E o) {
        add(size, o);
        return true;
    }

    /**
     * add element to the specified position and move elements right of this position on one position
     * @param i position where element should be added
     * @param o element that should be added
     */
    @Override
    public void add(int i, E o) {
        checkIndex(i);

        if (size >= innerArray.length * 0.8) {
            E[] tempArr = Arrays.copyOf(innerArray, size);
            innerArray = (E[]) new Object[size + size / INTEGER_TWO + INTEGER_ONE];
            innerArray = Arrays.copyOf(tempArr, innerArray.length);
        }

        innerArray[size++] = o;
    }

    /**
     * remove the specified element
     * and move elements from the the right to the left on the one position relatively this element
     * @param o element that should be removed
     * @return true if removing was success, otherwise false
     */
    @Override
    public boolean remove(Object o) {
        int position = indexOf(o);

        if (position == INTEGER_MINUS_ONE) {
            return false;
        }

        while (position > INTEGER_MINUS_ONE) {
            remove(position);
            position = indexOf(o);
        }
        return true;
    }

    /**
     *remove the element from the specified position
     * and move elements from the the right to the left on the one position relatively this posistion
     * @param i
     * @return
     */
    @Override
    public E remove(int i) {
        Object removableInteger;
        checkIndex(i);

        for (int j = i; j < size - 1; j++) {
            E temp = innerArray[j];
            innerArray[j] = innerArray[j + INTEGER_ONE];
            innerArray[j + INTEGER_ONE] = temp;
        }

        removableInteger = innerArray[size - INTEGER_ONE];
        innerArray[size - INTEGER_ONE] = null;
        size--;

        return (E) removableInteger;
    }

    /**
     * check contains all elements from specified collection
     * @param collection that sholud be checked
     * @return true if contains all elements of collection
     * @throws NullPointerException if collection element equal null
     */
    @Override
    public boolean containsAll(Collection collection) {
        for (Object o : collection) {
            try {
                if (!contains(o)) {
                    return false;
                }
            } catch (NullPointerException ex) {
                break;
            }
        }
        return true;
    }

    /**
     * add all elements of specified collection to the end
     * @param collection that should be added
     * @return true if collection was added successfully
     */
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        addAll(size, collection);
        return true;
    }

    /**
     * add all elements of specified collection relativity specified posittion
     * @param i that of start adding collecting
     * @param collection that should be added to the list
     * @return true if all elements was added
     */
    @Override
    public boolean addAll(int i, Collection<? extends E> collection) {
        checkIndex(i);

        E[] tempArray = (E[]) new Object[innerArray.length + collection.size()];
        System.arraycopy(innerArray, 0, tempArray, 0, i);
        System.arraycopy(collection.toArray(), 0, tempArray, i, collection.size());
        System.arraycopy(innerArray, i, tempArray, i + collection.size(), size);
        innerArray = tempArray;
        size = size + collection.size();
        return true;
    }

    /**
     * remove all elements of specified collection from list
     * @param collection contains elements taht should be added
     * @return true if all elemets was added, otherwise false
     */
    @Override
    public boolean removeAll(Collection collection) {
        int oldSize = size;

        for (Object o : collection) {
            remove(o);
        }

        if (oldSize != size) {
            return true;
        }
        return false;
    }

    /**
     * retain all elements from specified collection
     * @param collection contains elements that should be retain
     * @return true if list was modified
     */
    @Override
    public boolean retainAll(Collection collection) {
        final Object[] elementData = innerArray;
        int r = 0, w = 0;
        boolean modified = false;

        try {

            for (; r < size; r++) {
                if (collection.contains(elementData[r]) == true) {
                    elementData[w++] = elementData[r];
                }
            }

        } finally {

            if (r != size) {
                System.arraycopy(elementData, r,
                    elementData, w,
                    size - r);
                w += size - r;
            }

            if (w != size) {
                for (int i = w; i < size; i++) {
                    elementData[i] = null;
                }
                size = w;
                modified = true;
            }
        }
        return modified;
    }

    /**
     * clear list
     */
    @Override
    public void clear() {
        innerArray = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public E get(int i) {
        checkIndex(i);
        return (E) innerArray[i];
    }

    /**
     * set element to the specified position and replace element on this position
     * @param i position where element should be added
     * @param o element that should be added
     * @return previous element on this position
     */
    @Override
    public E set(int i, E o) {
        checkIndex(i);
        E oldElement = innerArray[i];
        innerArray[i] = o;
        return oldElement;
    }

    /**
     * return index of specified element
     * @param o element that should be found in list
     * @return position of element
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(innerArray[i], o)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * search last position of element in the list
     * @param o element for search
     * @return position of element
     */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i != 0; i--) {
            if (innerArray[i].equals(o)) {
                return i;
            }
        }

        return INTEGER_MINUS_ONE;
    }
    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        throw new UnsupportedOperationException();
    }

    /**
     * return sublist of list n=by the specified positions
     * @param i start position
     * @param i1 end position
     * @return sublist of list n=by the specified positions
     */
    @Override
    public List<E> subList(int i, int i1) {
        List<E> tempList = new ArrayList<>();

        for (int j = i; j < i1; j++) {
            tempList.add(innerArray[j]);
        }

        return tempList;
    }

    /**
     * check if included in list range
     * @param i element that should be checked
     * @throws IndexOutOfBoundsException if index out of range
     */
    private void checkIndex(int i) {
        if (i < INTEGER_ZERO || i > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * @author Denys Prykhodko, Vladyslav Burtsev
     */
    class IteratorImpl implements Iterator<E> {

        boolean result = false;
        private int size;
        private int currentIndex = 0;
        private Predicate<E> predicate;

        public IteratorImpl(Predicate<E> predicate) {
            this.predicate = predicate;
            this.size = size();
        }

        /**
         * check if iterator has next element
         * @return true if operator has next element, otherwise false
         */
        @Override
        public boolean hasNext() {
            while (currentIndex < size) {
                if (predicate.test((E) innerArray[currentIndex])) {
                    result = true;
                    break;
                }
                currentIndex++;
            }
            return result;
        }

        /**
         * return next element in iterator
         * @return return next element in iterator
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            result = false;
            return innerArray[currentIndex++];
        }

        /**
         * remove current element from iterator
         */
        @Override
        public void remove() {
            E temp = next();
            if (temp != null) {
                ListImpl.this.remove(temp);
            }
        }
    }
}
