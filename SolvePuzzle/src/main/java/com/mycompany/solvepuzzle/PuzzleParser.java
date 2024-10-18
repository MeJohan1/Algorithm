//Johan Ahmed Chowdhury
//w1910565
package com.mycompany.solvepuzzle;

import java.nio.file.*;
import java.util.*;

public class PuzzleParser {

    public static MapPuzzle parseFile(String filePath) throws Exception {   //parseFile method is for read a maze file 

        List<String> lines = Files.readAllLines(Paths.get(filePath));   // readall lines from the file 

        if (lines.isEmpty()) {
            throw new IllegalArgumentException("The maze file is empty."); // check if the file is empty this line show
        }

        lines.removeIf(String::isEmpty);

        int height = lines.size();
        int width = lines.get(0).length();

        for (int i = 0; i < lines.size(); i++) {   // ensuring all lines are of the same length. throw an exception if any line is not
            String line = lines.get(i);
            if (line.length() != width) {
                throw new IllegalArgumentException("Line " + (i + 1) + " length: " + line.length() + " (Expected: " + width + ")");
            }
        }

        MapPuzzle map = new MapPuzzle(width, height);

        for (int y = 0; y < height; y++) {   // each character within the lines to set the maze structures
            String line = lines.get(y);
            for (int x = 0; x < width; x++) {
                char ch = line.charAt(x);
                switch (ch) {
                    case 'S': // Start position
                        map.setStart(x, y);
                        break;
                    case 'F': // finish position
                        map.setFinish(x, y);
                        break;
                    case '0': // wall position
                        map.addWall(x, y);
                        break;
                    default: // treat anyother character as a road area
                        break;
                }
            }
        }
        return map; //return  fully initialized 
    }
}
