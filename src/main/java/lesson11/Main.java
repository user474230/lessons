package lesson11;

import java.lang.reflect.InvocationTargetException;

public class Main {

    static class MyTest1 {
    }

    @Test
    static class MyTest2 {

    }

    @Test
    static class MyTest3 {
        @Test(priority = 1)
        public static void case1() {
            System.out.println("MyTest3::case1()");
        }
        @Test(priority = 3)
        public static void case2() {
            System.out.println("MyTest3::case2()");
        }
        @Test(priority = 2)
        public static void case3() {
            System.out.println("MyTest3::case3()");
        }
        @Test(priority = 1)
        public static void case4() {
            System.out.println("MyTest3::case4()");
        }
    }

    @Test
    static class MyTest4 {
        @BeforeSuite
        public static void before() {
            System.out.println("MyTest4::before()");
        }
        @Test(priority = 1)
        public static void case1() {
            System.out.println("MyTest4::case1()");
        }
        @Test(priority = 3)
        public static void case2() {
            System.out.println("MyTest4::case2()");
        }
        @Test(priority = 2)
        public static void case3() {
            System.out.println("MyTest4::case3()");
        }
        @Test(priority = 1)
        public static void case4() {
            System.out.println("MyTest4::case4()");
        }
        @AfterSuite
        public static void after() {
            System.out.println("MyTest4::after()");
        }
    }

    @Test
    static class MyTest5 {
        @BeforeSuite
        public static void before() {
            System.out.println("MyTest5::before()");
        }
        @Test(priority = 1)
        public static void case1() {
            System.out.println("MyTest5::case1()");
        }
        @Test
        @AfterSuite
        public static void after() {
            System.out.println("MyTest5::after()");
        }
    }

    @Test
    static class MyTest6 {
        @BeforeSuite
        public static void before() {
            System.out.println("MyTest6::before()");
        }
        @BeforeSuite
        public static void before_2() {
            System.out.println("MyTest6::before_2()");
        }
        @Test(priority = 1)
        public static void case1() {
            System.out.println("MyTest6::case1()");
        }
        @Test
        @AfterSuite
        public static void after() {
            System.out.println("MyTest6::after()");
        }
    }
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
//        TestRunner.start(MyTest1.class);
//        TestRunner.start(MyTest2.class);
//        TestRunner.start(MyTest3.class);
        TestRunner.start(MyTest4.class);
//        TestRunner.start(MyTest5.class);
//        TestRunner.start(MyTest6.class);
    }
}
