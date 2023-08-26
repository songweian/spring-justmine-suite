package pro.justmine.common;

import org.springframework.core.Ordered;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JustOrderUtils {
    private JustOrderUtils() {
    }

    /**
     * sort items of the list by order
     * <p>
     * get order from Ordered annotation or Order interface if the item have
     * sort by class name if the item have no order
     */

    public static <T> List<T> sort(List<T> items, Comparator<T> comparator) {
        return items.stream().sorted(comparator).collect(Collectors.toList());
    }

    public static <T> List<T> sort(List<T> items) {
        Comparator<T> comparator = (o1, o2) -> {
            int order1 = getOrder(o1);
            int order2 = getOrder(o2);
            if (order1 != order2) {
                return Integer.compare(order1, order2);
            } else {
                String name1 = o1.getClass().getSimpleName();
                String name2 = o2.getClass().getSimpleName();
                return name1.compareTo(name2);
            }
        };
        return items.stream().sorted(comparator).collect(Collectors.toList());
    }


    private static <T> int getOrder(T item) {
        if (item instanceof Ordered) {
            return ((Ordered) item).getOrder();
        } else if (item instanceof Order) {
            return ((Order) item).getOrder();
        } else {
            return 0;
        }
    }

}
