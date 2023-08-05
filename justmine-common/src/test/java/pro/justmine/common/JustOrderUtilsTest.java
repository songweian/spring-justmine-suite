package pro.justmine.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JustOrderUtilsTest {
    @Test
    public void sort() {
        List<MyObject> items = new ArrayList<>();
        items.add(new MyObject("B", 2));
        items.add(new MyObject("A", 1));
        items.add(new MyObject("C", 3));
        items.add(new MyObject("D", 0));

        List<MyObject> sortedItems = JustOrderUtils.sort(items);
        for (MyObject item : sortedItems) {
            System.out.println(item.getName() + " " + item.getOrder());
        }
        assertEquals(4, sortedItems.size());
        assertEquals("D", sortedItems.get(0).getName());
        assertEquals("A", sortedItems.get(1).getName());
        assertEquals("B", sortedItems.get(2).getName());
        assertEquals("C", sortedItems.get(3).getName());
    }

    private static class MyObject implements Order {
        private String name;
        private int order;

        public MyObject(String name, int order) {
            this.name = name;
            this.order = order;
        }

        public String getName() {
            return name;
        }

        @Override
        public int getOrder() {
            return order;
        }
    }

}