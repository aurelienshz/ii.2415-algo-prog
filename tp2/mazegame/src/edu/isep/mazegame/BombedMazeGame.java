package edu.isep.mazegame;

import java.util.Observer;

public class BombedMazeGame extends MazeGame {
    public Room makeRoom(int n) {
        return new RoomWithBomb(n);
    }


    public Wall makeWall() {
        return new BombedWall();
    }


    public Door makeDoor(Room r1, Room r2) {
        return new IronDoor(r1, r2);
    }
}
