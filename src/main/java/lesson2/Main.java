package lesson2;

public class Main {
    public static void main(String[] args) {

        Group group = new Group("Nevosoft");
        group.add(new Employee("Иван", "ivan@ya.ru", 20, Post.Programmer));
        group.add(new Employee("Иван2", "ivan2@ya.ru", 21, Post.QA));
        group.add(new Employee("Иван3", "ivan3@ya.ru", 22, Post.Manager));

        System.out.println(group.toString());

        group.remove(1);

        System.out.println(group.toString());

        group.remove(1);

        System.out.println(group.toString());

        group.add(new Employee("Иван4", "ivan4@ya.ru", 24, Post.Lead));

        System.out.println(group.toString());

        for (int i=0; i < 20; ++i) {
            group.add(new Employee());
        }
    }
}
