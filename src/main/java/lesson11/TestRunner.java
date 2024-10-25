package lesson11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class TestRunner {
    public static <T> void start(Class<T> testClass) throws InvocationTargetException, IllegalAccessException {
        if (!testClass.isAnnotationPresent(Test.class)) {
            throw new RuntimeException("Класс не помечен как @Test");
        }

        Method[] methods = testClass.getDeclaredMethods();
        List<Method> before = new ArrayList<>();
        List<Method> tests = new ArrayList<>();
        List<Method> after = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                tests.add(method);
            }
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                before.add(method);
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                after.add(method);
            }
        }
        if (after.size() > 1) {
            throw new RuntimeException("В классе более 1 метода @AfterSuite");
        }
        if (before.size() > 1) {
            throw new RuntimeException("В классе более 1 метода @BeforeSuite");
        }
        if (!after.isEmpty()) {
            if (after.get(0).isAnnotationPresent(Test.class)) {
                throw new RuntimeException("У метода @AfterSuite присутствует аннотация @Test");
            }
        }
        if (!before.isEmpty()) {
            if (before.get(0).isAnnotationPresent(Test.class)) {
                throw new RuntimeException("У метода @BeforeSuite присутствует аннотация @Test");
            }
        }

        tests.sort((o1, o2) -> {
            int p1 = o1.getAnnotation(Test.class).priority();
            int p2 = o2.getAnnotation(Test.class).priority();
            return p1 - p2;
        });

        if (!before.isEmpty()) {
            before.get(0).invoke(null);
        }
        for (Method test : tests) {
            test.invoke(null);
        }
        if (!after.isEmpty()) {
            after.get(0).invoke(null);
        }

    }
}