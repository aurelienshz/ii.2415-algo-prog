package edu.isep.mazegame;

public class EnchantedMazeGame extends MazeGame {
    public Room makeRoom(int n) {
        return new EnchantedRoom(n);
    }


    public Wall makeWall() {
        return new SecretPassageWall();
    }


    public Door makeDoor(Room r1, Room r2) {
        return new DoorWithSpell(r1, r2);
    }
}
