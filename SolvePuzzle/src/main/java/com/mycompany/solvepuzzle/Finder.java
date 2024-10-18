//Johan Ahmed Chowdhury
//w1910565
package com.mycompany.solvepuzzle;

import java.util.*;

public class Finder {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static LinkedList<Point> findPath(MapPuzzle map) {  // to find the shortest path in the maze
        LinkedList<Point> queue = new LinkedList<>();
        Map<Point, Point> predecessors = new HashMap<>();
        Set<Point> visited = new HashSet<>();

        queue.add(map.getStart());
        visited.add(map.getStart());

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.equals(map.getFinish())) {
                return reconstructPath(predecessors, map.getFinish());
            }

            for (int[] direction : DIRECTIONS) {
                Point next = new Point(current.x + direction[0], current.y + direction[1]);
                if (!map.isValidLocation(next.x, next.y) || visited.contains(next) || map.isWall(next.x, next.y)) {
                    continue;
                }
                queue.add(next);
                visited.add(next);
                predecessors.put(next, current);
            }
        }
        return new LinkedList<>();
    }

    private static LinkedList<Point> reconstructPath(Map<Point, Point> predecessors, Point finish) { // Helper method to reconstruct the path from s to f
        LinkedList<Point> path = new LinkedList<>();
        for (Point at = finish; at != null; at = predecessors.get(at)) {
            path.addFirst(at);
        }
        return path;
    }
}
