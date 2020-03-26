package lab2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ListImplTest {

    @Test
    void sizeShouldBeZero() {
        ListImpl list = new ListImpl();

        int expected = 0;
        int actual = list.size();

        assertEquals(expected, actual);
    }

    @Test
    void listShouldBeEmpty() {
        ListImpl list = new ListImpl();

        boolean expected = true;
        boolean actual = list.isEmpty();

        assertEquals(expected, actual);
    }

    @Test
    void listShouldContainTelephone() {
        ListImpl list = new ListImpl();
        list.add(1);

        boolean expected = true;
        boolean actual = list.contains(1);

        assertEquals(expected, actual);
    }

    @Test
    void hasNextShouldReturnFalse() {
        ListImpl list = new ListImpl();

        boolean expected = false;
        boolean actual = list.iterator().hasNext();

        assertEquals(expected, actual);
    }

    @Test
    void nextShouldRetrunTelephone() {
        ListImpl<Integer> list = new ListImpl();
        list.add(1);

        Integer expected = 1;
        Integer actual = list.iterator().next();

        assertEquals(expected, actual);
    }

    @Test
    void removeShouldRemoveOneElement() {
        ListImpl list = new ListImpl();
        list.add(1);
        Iterator it = list.iterator();
        it.remove();

        int expected = 0;
        int actual = list.size();

        assertEquals(expected, actual);
    }

    @Test
    void shouldAddTelephone() {
        ListImpl list = new ListImpl();

        boolean actual = list.add(1);
        boolean expected = true;

        assertEquals(actual, expected);
    }

    @Test
    void shouldAddNoteBookIntoSpecifiedPosition() {
        ListImpl list = new ListImpl();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1, 4);

        int actual = list.size();
        int expected = 4;

        assertEquals(actual, expected);
    }

    @Test
    void listShouldIncreaseHisSize() {
        ListImpl list = new ListImpl();
        for (int i = 0; i < 9; i++) {
            list.add(1);
        }
        list.add(1, 2);

        int actual = list.size();
        int expected = 10;

        assertEquals(actual, expected);

    }

    @Test
    void removeShouldReturnTrue() {
        ListImpl list = new ListImpl();
        list.add(1);

        boolean expected = true;
        boolean actual = list.remove(new Integer(1));

        assertEquals(expected, actual);
    }

    @Test
    void removeShouldReturnTelephoneFromSpecifiedPosition() {
        ListImpl<Integer> list = new ListImpl();
        list.add(1);
        list.add(2);

        Integer expected = 1;
        Integer actual = list.remove(0);

        assertEquals(expected, actual);
    }

    @Test
    void getShouldReturnIndexOutOfBoundsException() {
        ListImpl list = new ListImpl();
        list.add(1);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(3);
        });
    }

    @Test
    void toArrayShouldReturnTrue() {
        ListImpl list = new ListImpl();

        int actual = list.toArray().length;
        int expected = 10;

        assertEquals(actual, expected);
    }

    @Test
    void containsAllShouldReturnTrue() {
        ListImpl list = new ListImpl();
        ListImpl list2 = new ListImpl();
        list.add(1);
        list.add(1);
        list.add(2);

        list2.add(1);
        list2.add(1);
        list2.add(2);

        boolean actual = list.containsAll(list2);
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    void addAllShouldAddAll() {
        ListImpl list = new ListImpl();
        ListImpl list2 = new ListImpl();
        list.add(1);
        list.add(1);
        list.add(2);

        list2.add(1);
        list2.add(1);
        list2.add(2);

        list.addAll(list2);

        int actual = list.size();
        int expected = 6;

        assertEquals(actual, expected);

    }

    @Test
    void addAllShouldAddAllToSpecifiedPosition() {
        ListImpl list = new ListImpl();
        ListImpl list2 = new ListImpl();
        list.add(1);
        list.add(1);
        list.add(2);

        list2.add(1);
        list2.add(1);
        list2.add(2);

        list.addAll(2, list2);

        int actual = list.size();
        int expected = 6;

        assertEquals(expected, actual);

    }

    @Test
    void removeAll() {
        ListImpl list = new ListImpl();
        ListImpl list2 = new ListImpl();
        list.add(1);
        list.add(1);
        list.add(2);

        list2.add(1);

        list.removeAll(list2);

        int actual = list.size();
        int expected = 1;

        assertEquals(expected, actual);
    }

    @Test
    void retainAll() {
        ListImpl list = new ListImpl();
        ListImpl list2 = new ListImpl();
        list.add(1);
        list.add(1);
        list.add(2);

        list2.add(1);

        list.retainAll(list2);

        int actual = list.size();
        int expected = 2;

        assertEquals(expected, actual);
    }

    @Test
    void clear() {
        List<Integer> list = new ListImpl<>();
        list.add(1);
        list.add(1);

        list.clear();

        int actual = list.size();
        int expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    void set(){
        List<Integer> list = new ListImpl<>();
        list.add(1);
        list.add(2);

        list.set(1,3);

        int actual = list.get(1);
        int expected = 3;

        assertEquals(expected,actual);
    }

}