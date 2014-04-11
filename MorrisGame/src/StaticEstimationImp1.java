import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
public class StaticEstimationImp1 {
    // A static estimation for MidgameEndgame
    public static long getMidEndGameEst(GameNode node) {
        int numWhitePieces = node.mPositon.getNumWhitePieces();
        int numBlackPieces = node.mPositon.getNumBlackPieces();
        // the positions generated by a black move.
        List<BoardPosition> L = Generator.generateMovesMidgameEndgameBlack(node.mPositon);
        // the number of board positions in L
        int numBlackMoves = L.size();

        if (numBlackPieces <= 2) {
            return 10000;
        } else if (numWhitePieces <= 2) {
            return -10000;
        } else if (0 == numBlackMoves) {
            return 10000;
        } else {
            return (1000 * (numWhitePieces - numBlackPieces) - numBlackMoves);
        }
    }

    // A static estimation for Opening.
    public static long getOpeningEst(GameNode node) {
        int numWhitePieces = node.mPositon.getNumWhitePieces();
        int numBlackPieces = node.mPositon.getNumBlackPieces();
        return (numWhitePieces - numBlackPieces);
    }

    // A static estimation for MidgameEndgame
    public static long getMidEndGameEstImproved(GameNode node) {
        int numWhitePieces = node.mPositon.getNumWhitePieces();
        int numBlackPieces = node.mPositon.getNumBlackPieces();
        // the positions generated by a black move.
        List<BoardPosition> L = Generator.generateMovesMidgameEndgameBlack(node.mPositon);
        // the number of board positions in L
        int numBlackMoves = L.size();

        if (numBlackPieces <= 2) {
            return 10000;
        } else if (numWhitePieces <= 2) {
            return -10000;
        } else if (0 == numBlackMoves) {
            return 10000;
        } else {
            return (500 * (numWhitePieces - numBlackPieces)
                    + 500 * (node.mPositon.getNumWhiteMills()
                    - 500 * node.mPositon.getNumBlackMills()) - numBlackMoves);
        }
    }

    private static int getPointsForLocation(int j) {
        switch (j) {
            case 1:
            case 4:
                return 1000;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 15:
            case 18:
            case 21:
                return 2000;
            case 0:
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
            case 14:
            case 16:
            case 17:
            case 19:
            case 20:
            case 22:
                return 3000;
            default:
                return 0;
        }
    }

    // A static estimation for Opening.
    public static long getOpeningEstImproved(GameNode node) {
        long points = 0;
        for (int i = 0; i < BoardPosition.BOARD_SIZE; i++) {
            if (node.mPositon.isWhite(i)) {
                points += getPointsForLocation(i);
            } else if (node.mPositon.isBlack(i)) {
                points -= getPointsForLocation(i);
            } else {
                // empty location, no points
            }
        }
        return points;
    }
}