import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
public class IOHelper {
    public static boolean DEBUG_DISP = false;

    public static boolean validateParams(String[] args) {
        if (args.length != 3) {
            System.out.println("USAGE: "
                    + "<program_name>"
                    + " <input_board> <output_board> <depth>");
            return false;
        }

        // check if the depth param is numeric.
        try {
            Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            return false;
        }


        return true;
    }

    public static BoardPosition readBoard(String boardFilename) {
        // Read input board.
        String inputBoardString;
        try {
            inputBoardString = FileUtils.readLines(new File(boardFilename), "UTF-8").get(0);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new BoardPosition(inputBoardString);
    }

    public static boolean writeBoard(String boardFilename, BoardPosition b) {
        // write back the output board
        try {
            FileUtils.writeStringToFile(new File(boardFilename), b.toString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static void cout(BoardPosition b, int numPostions, long est) {
        System.out.println("Board Position: " + b.toString());
        System.out.println("Positions evaluated by static estimation: " + numPostions);
        System.out.println("MINIMAX estimate: " + est);
    }
}
