//Johan Ahmed Chowdhury
//w1910565
package com.mycompany.solvepuzzle;

public class MapPuzzle {

    private int width;
    private int height;
    private Point start, finish;
    private boolean[][] walls; // grid represent maze where true indicate a wall

    public MapPuzzle(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls = new boolean[height][width];
    }

    // Constructor 
    public void setStart(int x, int y) {
        this.start = new Point(x, y);
    }

    public void setFinish(int x, int y) {
        this.finish = new Point(x, y);
    }

    public void addWall(int x, int y) {
        walls[y][x] = true;
    }

    public boolean isFree(int x, int y) {
        return isValidLocation(x, y) && !walls[y][x];
    }

    public Point getStart() {
        return start;
    }

    public Point getFinish() {
        return finish;
    }

    public boolean isWall(int x, int y) {
        return isValidLocation(x, y) && walls[y][x];
    }

    public boolean isStart(int x, int y) {
        return x == start.x && y == start.y;
    }

    public boolean isFinish(int x, int y) {
        return x == finish.x && y == finish.y;
    }

    public boolean isValidLocation(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
