package lesson3;

class Human implements Participiant {
    static int maxJumpHeight = 10;
    static int maxRunDistance = 20;

    @Override
    public boolean jump(int height) {
        if (height <= Human.maxJumpHeight) {
            System.out.println("Человек успешно прыгнул");
            return true;
        }
        System.out.println("Человек не прыгнул");
        return false;
    }

    @Override
    public boolean run(int distance) {
        if (distance <= Human.maxRunDistance) {
            System.out.println("Человек успешно пробежал");
            return true;
        }
        System.out.println("Человек не пробежал");
        return false;
    }
}