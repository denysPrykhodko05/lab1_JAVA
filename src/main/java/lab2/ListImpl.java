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

public class ListImpl<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[] innerArray;
    private int size = INTEGER_ZERO;

    public ListImpl() {
        innerArray = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == INTEGER_ZERO;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) > INTEGER_MINUS_ONE;
    }

    @Override
    public Iterator<E> iterator() {
        return iterator(t -> true);
    }

    public Iterator<E> iterator(Predicate<E> predicate) {
        return new IteratorImpl(predicate);

    }

    @Override
    public Object[] toArray() {
        return Arrays.stream(innerArray).toArray();
    }

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

    @Override
    public boolean add(E o) {
        add(size, o);
        return true;
    }

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

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        addAll(size, collection);
        return true;
    }

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

    @Override
    public E set(int i, E o) {
        checkIndex(i);
        E oldElement = innerArray[i];
        innerArray[i] = o;
        return oldElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(innerArray[i], o)) {
                return i;
            }
        }
        return -1;
    }

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

    @Override
    public List<E> subList(int i, int i1) {
        List<E> tempList = new ArrayList<>();

        for (int j = i; j < i1; j++) {
            tempList.add(innerArray[j]);
        }

        return tempList;
    }

    private void checkIndex(int i) {
        if (i < INTEGER_ZERO || i > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    class IteratorImpl implements Iterator<E> {

        boolean result = false;
        private int size;
        private int currentIndex = 0;
        private Predicate<E> predicate;

        public IteratorImpl(Predicate<E> predicate) {
            this.predicate = (Predicate<E>) predicate;
            this.size = size();
        }

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

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            result = false;
            return (E) innerArray[currentIndex++];
        }

        @Override
        public void remove() {
            E temp = next();
            if (temp != null) {
                ListImpl.this.remove(temp);
            }
        }
    }
}
