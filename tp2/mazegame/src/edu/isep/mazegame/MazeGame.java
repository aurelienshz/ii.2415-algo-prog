package edu.isep.mazegame;

public class MazeGame {
    public Room makeRoom(int n) {
        return new Room(n);
    }

    public Maze makeMaze() {
        return new Maze();
    }

    public Wall makeWall() {
        return new Wall();
    }

    public Door makeDoor(Room r1, Room r2) {
        return new Door(r1, r2);
    }

    public Maze createMaze() {
        Maze aMaze = makeMaze();
        Room r1 = makeRoom(1);
        Room r2 = makeRoom(2);
        Door theDoor = makeDoor(r1, r2);
        aMaze.addRoom(r1);
        aMaze.addRoom(r2);
        r1.setSide(Direction.North, makeWall());
        r1.setSide(Direction.East, theDoor);
        r1.setSide(Direction.South, makeWall());
        r1.setSide(Direction.West, makeWall());
        r2.setSide(Direction.North, makeWall());
        r2.setSide(Direction.East, makeWall());
        r2.setSide(Direction.South, makeWall());
        r2.setSide(Direction.West, theDoor);
        return aMaze;
    }
}
