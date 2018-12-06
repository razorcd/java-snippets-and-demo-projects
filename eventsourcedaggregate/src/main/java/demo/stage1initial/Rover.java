package demo.stage1initial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
public class Rover {
    private final String id;
    private Location location;
    private Orientation orientation;

    public Rover(String id, Location location, Orientation orientation) {
        this.id = id;
        this.location = location;
        this.orientation = orientation;
    }

    public void moveForward() {
        this.location = this.location.forward(orientation);
    }

    public void turnLeft() {
        this.orientation = this.orientation.left();
    }

    public void turnRight() {
        this.orientation = this.orientation.right();
    }



    @Value
    @AllArgsConstructor
    public static class Location {
        private final double latitude; //N-S
        private final double longitude; //E-W

        private Location forward(Orientation orientation) {
            switch (orientation) {
                case N:
                    return new Location(this.getLatitude()+1, this.getLongitude());
                case E:
                    return new Location(this.getLatitude()+0, this.getLongitude()+1);
                case S:
                    return new Location(this.getLatitude()-1, this.getLongitude()+0);
                case W:
                    return new Location(this.getLatitude()+0, this.getLongitude()-1);
            }
            throw new IllegalStateException("Unprocessable orientation "+orientation);
        }

    }
    public static enum Orientation {
        N("W","E"),
        S("E","W"),
        E("N","S"),
        W("S","N");

        private String left;
        private String right;

        Orientation(String left, String right) {
            this.left = left;
            this.right = right;
        }

        public Orientation left() {
            return Orientation.valueOf(left);
        }

        public Orientation right() {
            return Orientation.valueOf(right);
        }
    }
}
