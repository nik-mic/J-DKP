package entities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LiteralScanner implements ObjectScanner {

    @Override
    public List<Object> scan(Object o) {
        List<Object> tree = new ArrayList<>();
        Arrays.stream(o.getClass()
                .getDeclaredFields())
                .forEach(handleField(o, tree));
        return tree;
    }

    private Consumer<Field> handleField(Object o, List<Object> tree) {
        return f -> {
            try {
                if (f.getType().isPrimitive() || f.getType().equals(String.class)) {
                    tree.add(String.valueOf(f.get(o)));
                } else {
                    tree.add(scan(f.get(o)));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        };
    }
}
