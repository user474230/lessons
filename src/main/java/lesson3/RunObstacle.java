package lesson3;

class RunObstacle implements Obstacle {
    private int distance;

    public RunObstacle(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean tryPass(Participiant p) {
        return p.run(distance);
    }
}
