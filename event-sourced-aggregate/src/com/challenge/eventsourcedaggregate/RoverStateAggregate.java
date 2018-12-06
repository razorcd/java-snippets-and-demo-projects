package com.challenge.eventsourcedaggregate;

public class RoverStateAggregate {
    private String id;
    private Location location;
    private Direction direction;


    public static class Location {
        private double longitude;
        private double latitude;
    }

    public static enum Direction {
        N,S,E,W
        ;
    }
}
