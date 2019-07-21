package application;

import lombok.Builder;
import lombok.Data;

import static application.utility.CanvasProperty.*;
import static application.utility.DirectionNames.*;

@Builder
@Data
public class Ant {

    private Integer positionX;

    private Integer positionY;

    private Integer size;

    private Integer direction;

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

}
