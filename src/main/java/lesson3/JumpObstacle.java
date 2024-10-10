package lesson3;

class JumpObstacle implements Obstacle {
    private int height;

    public JumpObstacle(int height) {
        this.height = height;
    }

    @Override
    public boolean tryPass(Participiant p) {
        return p.jump(height);
    }
}
