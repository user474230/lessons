package lesson3;

class Robot implements Participiant {
    static int maxJumpHeight = 30;
    static int maxRunDistance = 40;

    @Override
    public boolean jump(int height) {
        if (height <= Robot.maxJumpHeight) {
            System.out.println("Робот успешно прыгнул");
            return true;
        }
        System.out.println("Робот не прыгнул");
        return false;
    }

    @Override
    public boolean run(int distance) {
        if (distance <= Robot.maxRunDistance) {
            System.out.println("Робот успешно пробежал");
            return true;
        }
        System.out.println("Робот не пробежал");
        return false;
    }
}