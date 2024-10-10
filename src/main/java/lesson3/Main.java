package lesson3;

public class Main {
    public static void main(String[] args) {
        Participiant[] competitors = new Participiant[]{new Human(), new Robot(), new Cat()};
        Obstacle[] obstacles = new Obstacle[]{new JumpObstacle(5), new RunObstacle(10), new JumpObstacle(15), new RunObstacle(25)};

        for (Participiant p : competitors) {
            for (Obstacle o : obstacles) {
                if (!o.tryPass(p)) {
                    break;
                }
            }
        }
    }
}
