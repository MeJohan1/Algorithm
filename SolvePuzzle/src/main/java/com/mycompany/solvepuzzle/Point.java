//Johan Ahmed Chowdhury. 
//w1910565
package com.mycompany.solvepuzzle;

import java.util.*;

public class Point { //this class location of maze and x and y cordinates on grid. 

    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point)) {
            return false;
        }
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
