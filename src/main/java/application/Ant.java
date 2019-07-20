package application;

import application.interfaces.DirectionNames;
import lombok.Data;

import static application.interfaces.CanvasNames.HEIGHT;
import static application.interfaces.CanvasNames.WIDTH;

@Data
public class Ant implements DirectionNames {

    private static Ant ant;

    private Integer positionX;

    private Integer positionY;

    private Integer size;

    private Integer direction;

    private Ant() {}

    public static Ant getInstance() {
        if (ant == null) {
            ant = new Ant();
        }
        return ant;
    }

    private void run(Integer direction) {

        switch (direction) {
            case UP:
                this.positionY += size;
                break;

            case RIGHT:
                this.positionX += size;
                break;

            case LEFT:
                this.positionY -= size;
                break;

            case DOWN:
                this.positionX -= size;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }

    }

    public void turnLeft() {
        this.direction--;
        if (this.direction < 1) this.direction = 4;
        this.run(this.direction);
    }

    public void turnRight() {
        this.direction++;
        if (this.direction > 4) this.direction = 1;
        this.run(this.direction);
    }

    public void checkPosition() {

        if (this.getPositionX() >= WIDTH) {
            this.setPositionX(this.getSize());
        }
        if (this.getPositionX() < 0) {
            this.setPositionX(WIDTH - this.getSize());
        }
        if (this.getPositionY() >= HEIGHT) {
            this.setPositionY(this.getSize());
        }
        if (this.getPositionY() < 0) {
            this.setPositionY(WIDTH - this.getSize());
        }

    }

    public static final class Builder {

        private Integer positionX = 100;

        private Integer positionY = 100;

        private Integer size = 2;

        private Integer direction = 1;


        public Builder size(Integer size) {
            this.size = size;
            return this;
        }

        public Builder positionX(Integer positionX) {
            this.positionX = positionX;
            return this;
        }

        public Builder positionY(Integer positionY) {
            this.positionY = positionY;
            return this;
        }

        public Builder direction(Integer direction) {
            this.direction = direction;
            return this;
        }

        public static Builder builder() {
            return new Builder();
        }

        public Ant build() {

            if (positionX == null) {
                throw new IllegalStateException("PositionX is empty");
            }

            if (positionY == null) {
                throw new IllegalStateException("PositionY is empty");
            }

            if (size == null) {
                throw new IllegalStateException("Size is not defined");
            }

            if (direction == null) {
                throw new IllegalStateException("Direction is not defined");
            }

            Ant ant = new Ant();
            ant.setPositionX(positionX);
            ant.setPositionY(positionY);
            ant.setSize(size);
            ant.setDirection(direction);
            Ant.ant = ant;

            return ant;
        }
    }

}
