package lesson6;

import java.util.*;

public class Dictionary {
    private final Map<String, Set<String>> map = new HashMap<>();
    public void add(String phone, String name) {
        Set<String> record = map.computeIfAbsent(name, k -> new HashSet<String>());
        record.add(phone);
    }

    public Set<String> get(String name) {
        return map.get(name);
    }
}
