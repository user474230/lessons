package lesson3;

class Cat implements Participiant {
    static int maxJumpHeight = 20;
    static int maxRunDistance = 30;

    @Override
    public boolean jump(int height) {
        if (height <= Cat.maxJumpHeight) {
            System.out.println("Кот успешно прыгнул");
            return true;
        }
        System.out.println("Кот не прыгнул");
        return false;
    }

    @Override
    public boolean run(int distance) {
        if (distance <= Cat.maxRunDistance) {
            System.out.println("Кот успешно пробежал");
            return true;
        }
        System.out.println("Кот не пробежал");
        return false;
    }
}
