package chapter3;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nanca on 11/4/2017.
 * 在可变对象基础上构建的不可变类
 */
public class ThreeStooges {
    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
