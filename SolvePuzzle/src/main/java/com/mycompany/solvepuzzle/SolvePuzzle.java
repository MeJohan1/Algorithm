//Johan Ahmed Chowdhury
//w1910565
package com.mycompany.solvepuzzle;

import java.util.*;

public class SolvePuzzle {

    public static void main(String[] args) {
        try {
            MapPuzzle map = PuzzleParser.parseFile("./benchmark_series/puzzle_10.txt"); // Load the maze from file
            printInitialGrid(map); // Show the initial maze from the file 
            long start = System.currentTimeMillis();
            LinkedList<Point> path = Finder.findPath(map); // Use this to solve maze use BFS
            long now = System.currentTimeMillis();
            double elapsed = (now - start) / 1000.0;// calculation time taken to solve maze
            printGrid(map, path); // Show the solve the maze after initial maze
            printPath(path, map); // Print sll the steps path solution for maze
            System.out.println("Elapsed time = " + elapsed + " seconds");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // print initial maze but no path
    public static void printInitialGrid(MapPuzzle map) {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Initial Puzzle from:");
        printGridContents(map, null); // Display grid without the path
    }

    // this one for the print solution the maze
    public static void printGrid(MapPuzzle map, LinkedList<Point> path) {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Puzzle Solution:");
        printGridContents(map, path);
        System.out.println("-------------------------------------------------------------");
    }

    // Both initial and solved grid printing use this helpful method to print the contents of the grid.
    private static void printGridContents(MapPuzzle map, LinkedList<Point> path) {
        char[][] grid = new char[map.getHeight()][map.getWidth()];

        // Initializesd grid with roads and walls
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                grid[y][x] = map.isWall(x, y) ? '0' : '.';
            }
        }

        // Mark the way to easily identify.
        if (path != null) {
            for (Point p : path) {
                if (!p.equals(map.getStart()) && !p.equals(map.getFinish())) {
                    grid[p.y][p.x] = '&'; // Marking the path with '*'
                }
            }
        }

        // To make sure the Start and finish point is correct
        grid[map.getStart().y][map.getStart().x] = 'S';
        grid[map.getFinish().y][map.getFinish().x] = 'F';

        // grid print
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }

    // This one for steps the solution of maze
    public static void printPath(LinkedList<Point> path, MapPuzzle map) {
        System.out.println("All the steps of the puzzle solution: ");
        if (path == null || path.isEmpty()) {
            System.out.println("Path is not found.");
            return;
        }

        int stepNumber = 1;
        Point previous = path.get(0);
        System.out.println(stepNumber + ". Start at (" + (previous.x + 1) + "," + (previous.y + 1) + ")"); // start from the maze "S"
        stepNumber++;

        for (int i = 1; i < path.size(); i++) {
            Point current = path.get(i);
            String direction = getDirection(previous, current);
            System.out.println(stepNumber + ". Move " + direction + " to (" + (current.x + 1) + "," + (current.y + 1) + ")");
            previous = current;
            stepNumber++;
        }
        System.out.println(stepNumber + ". Finally done the solution.");// after reach the "F" in maze
        System.out.println("-------------------------------------------------------------");
    }

    //this on dirrection of the reaching the finish line form start.
    private static String getDirection(Point from, Point to) {
        if (from.x == to.x) {
            return (from.y < to.y) ? "right" : "left";
        } else {
            return (from.x < to.x) ? "down" : "up";
        }
    }
}
