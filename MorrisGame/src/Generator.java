import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jason Huang
 */
public class Generator {
    public static List<BoardPosition> generateMovesOpening(BoardPosition b) {
        return generateAdd(b);
    }

    public static List<BoardPosition> generateMovesMidgameEndgame(BoardPosition b) {
        if (3 == b.getNumWhitePieces()) {
            return generateHopping(b);
        } else {
            return generateMove(b);
        }
    }

    // the MidgameEndgame positions generated from b by a black move.
    public static List<BoardPosition> generateMovesOpeningBlack(BoardPosition b) {
        List<BoardPosition> L = new ArrayList<BoardPosition>();
        List<BoardPosition> tmp = Generator.generateMovesOpening(b.swap());
        for (BoardPosition b2 : tmp) {
            L.add(b2.swap());
        }

        return L;
    }

    // the MidgameEndgame positions generated from b by a black move.
    public static List<BoardPosition> generateMovesMidgameEndgameBlack(BoardPosition b) {
        List<BoardPosition> L = new ArrayList<BoardPosition>();
        List<BoardPosition> tmp = Generator.generateMovesMidgameEndgame(b.swap());
        for (BoardPosition b2 : tmp) {
            L.add(b2.swap());
        }

        return L;
    }

    public static List<BoardPosition> generateAdd(BoardPosition b) {
        List<BoardPosition> L = new ArrayList<BoardPosition>();
        for (int i = 0; i < BoardPosition.BOARD_SIZE; i++) {
            if (b.isEmpty(i)) {
                BoardPosition bCopy = new BoardPosition(b);
                bCopy.setWhiteAt(i);
                if (bCopy.closeMill(i)) {
                    generateRemove(bCopy, L);
                } else {
                    L.add(bCopy);
                }
            }
        }

        return L;
    }

    public static List<BoardPosition> generateHopping(BoardPosition b) {
        List<BoardPosition> L = new ArrayList<BoardPosition>();
        for (int i = 0; i < BoardPosition.BOARD_SIZE; i++) {
            if (b.isWhite(i)) {
                for (int j = 0; j < BoardPosition.BOARD_SIZE; j++) {
                    if (b.isEmpty(j)) {
                        BoardPosition bCopy = new BoardPosition(b);
                        bCopy.setEmptyAt(i);
                        bCopy.setWhiteAt(j);
                        if (bCopy.closeMill(j)) {
                            generateRemove(bCopy, L);
                        } else {
                            L.add(bCopy);
                        }
                    }
                }
            }
        }

        return L;
    }

    public static List<BoardPosition> generateMove(BoardPosition b) {
        List<BoardPosition> L = new ArrayList<BoardPosition>();
        for (int i = 0; i < BoardPosition.BOARD_SIZE; i++) {
            if (b.isWhite(i)) {
                List<Integer> neighbors = BoardPosition.neighbors(i);
                for (Integer j : neighbors) {
                    if (b.isEmpty(j)) {
                        BoardPosition bCopy = new BoardPosition(b);
                        bCopy.setEmptyAt(i);
                        bCopy.setWhiteAt(j);
                        if (bCopy.closeMill(j)) {
                            generateRemove(bCopy, L);
                        } else {
                            L.add(bCopy);
                        }
                    }
                }
            }
        }

        return L;
    }

    public static void generateRemove(BoardPosition b, List<BoardPosition> L) {
        // Could we remove a black piece from the board?
        boolean isBlackRemoved = false;
        for (int i = 0; i < BoardPosition.BOARD_SIZE; i++) {
            if (b.isBlack(i)) {
                if (!b.closeMill(i)) {
                    isBlackRemoved = true;
                    BoardPosition bCopy = new BoardPosition(b);
                    bCopy.setEmptyAt(i);
                    L.add(bCopy);
                }
            }
        }

        if (!isBlackRemoved) {
            L.add(b);
        }
    }
}
