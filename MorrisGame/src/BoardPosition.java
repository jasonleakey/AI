import java.util.Arrays;
import java.util.List;

/**
 * Author: Jason Huang
 */
// Present a Morris Board.
public class BoardPosition {
    char[] mBoard = new char[BOARD_SIZE];
    // 23 pieces.
    // a0, d0, g0, b1, d1, f1, c2, e2, a3, b3, c3, e3, f3, g3, c4, d4, e4, b5, d5, f5, a6, d6, g6
    public static final int BOARD_SIZE = 23;

    public BoardPosition(char[] b) throws IllegalArgumentException {
        validateBoard(b);
        for (int i = 0; i < BOARD_SIZE; i++) {
            this.mBoard[i] = b[i];
        }
    }

    public BoardPosition(String b) throws IllegalArgumentException {
        validateBoard(b.toCharArray());
        for (int i = 0; i < BOARD_SIZE; i++) {
            this.mBoard[i] = b.charAt(i);
        }
    }

    public BoardPosition(BoardPosition b) {
        validateBoard(b.mBoard);
        for (int i = 0; i < BOARD_SIZE; i++) {
            this.mBoard[i] = b.mBoard[i];
        }
    }

    private void validateBoard(char[] b) {
        if (null == b || b.length != BOARD_SIZE) {
            throw new IllegalArgumentException("The board length should be 23!");
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            char c = b[i];
            if (c != 'x' && c != 'W' && c != 'B') {
                throw new IllegalArgumentException("The board has invalid letters: " + c);
            }
        }
    }

    public BoardPosition swap() {
        BoardPosition bCopy = new BoardPosition(this);
        for (int i = 0; i < BOARD_SIZE; i++) {
            char c = bCopy.mBoard[i];
            if ('W' == c) {
                bCopy.mBoard[i] = 'B';
            } else if ('B' == c) {
                bCopy.mBoard[i] = 'W';
            }
        }

        return bCopy;
    }

    public int getNumWhitePieces() {
        int count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            char c = mBoard[i];
            if ('W' == c) {
                count++;
            }
        }

        return count;
    }

    public int getNumBlackPieces() {
        int count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            char c = mBoard[i];
            if ('B' == c) {
                count++;
            }
        }

        return count;
    }

    public int getNumWhiteMills() {
        int count = 0;
        count += ((isWhite(0) && mill(0, 1, 2)) ? 1 : 0);
        count += ((isWhite(3) && mill(3, 4, 5)) ? 1 : 0);
        count += ((isWhite(8) && mill(8, 9, 10)) ? 1 : 0);
        count += ((isWhite(11) && mill(11, 12, 13)) ? 1 : 0);
        count += ((isWhite(14) && mill(14, 15, 16)) ? 1 : 0);
        count += ((isWhite(17) && mill(17, 18, 19)) ? 1 : 0);
        count += ((isWhite(20) && mill(20, 21, 22)) ? 1 : 0);

        count += ((isWhite(0) && mill(0, 8, 20)) ? 1 : 0);
        count += ((isWhite(3) && mill(3, 9, 17)) ? 1 : 0);
        count += ((isWhite(6) && mill(6, 10, 14)) ? 1 : 0);
        count += ((isWhite(15) && mill(15, 18, 21)) ? 1 : 0);
        count += ((isWhite(7) && mill(7, 11, 16)) ? 1 : 0);
        count += ((isWhite(5) && mill(5, 12, 19)) ? 1 : 0);
        count += ((isWhite(2) && mill(2, 13, 22)) ? 1 : 0);

        count += ((isWhite(0) && mill(0, 3, 6)) ? 1 : 0);
        count += ((isWhite(2) && mill(2, 5, 7)) ? 1 : 0);
        count += ((isWhite(14) && mill(14, 17, 20)) ? 1 : 0);
        count += ((isWhite(16) && mill(16, 19, 22)) ? 1 : 0);

        return count;
    }

    public int getNumBlackMills() {
        int count = 0;
        count += ((isBlack(0) && mill(0, 1, 2)) ? 1 : 0);
        count += ((isBlack(3) && mill(3, 4, 5)) ? 1 : 0);
        count += ((isBlack(8) && mill(8, 9, 10)) ? 1 : 0);
        count += ((isBlack(11) && mill(11, 12, 13)) ? 1 : 0);
        count += ((isBlack(14) && mill(14, 15, 16)) ? 1 : 0);
        count += ((isBlack(17) && mill(17, 18, 19)) ? 1 : 0);
        count += ((isBlack(20) && mill(20, 21, 22)) ? 1 : 0);

        count += ((isBlack(0) && mill(0, 8, 20)) ? 1 : 0);
        count += ((isBlack(3) && mill(3, 9, 17)) ? 1 : 0);
        count += ((isBlack(6) && mill(6, 10, 14)) ? 1 : 0);
        count += ((isBlack(15) && mill(15, 18, 21)) ? 1 : 0);
        count += ((isBlack(7) && mill(7, 11, 16)) ? 1 : 0);
        count += ((isBlack(5) && mill(5, 12, 19)) ? 1 : 0);
        count += ((isBlack(2) && mill(2, 13, 22)) ? 1 : 0);

        count += ((isBlack(0) && mill(0, 3, 6)) ? 1 : 0);
        count += ((isBlack(2) && mill(2, 5, 7)) ? 1 : 0);
        count += ((isBlack(14) && mill(14, 17, 20)) ? 1 : 0);
        count += ((isBlack(16) && mill(16, 19, 22)) ? 1 : 0);

        return count;
    }

    public boolean isEmpty(int j) {
        return (mBoard[j] == 'x');
    }

    public boolean isWhite(int j) {
        return (mBoard[j] == 'W');
    }

    public boolean isBlack(int j) {
        return (mBoard[j] == 'B');
    }

    public char get(int j) {
        return mBoard[j];
    }

    public void setWhiteAt(int j) {
        mBoard[j] = 'W';
    }

    public void setBlackAt(int j) {
        mBoard[j] = 'B';
    }

    public void setEmptyAt(int j) {
        mBoard[j] = 'x';
    }

    public void disp() {
        System.out.println(mBoard[20] + "-----" + mBoard[21] + "-----" + mBoard[22]);
        System.out.println("|\\    |    /|");
        System.out.println("| " + mBoard[17] + "---" + mBoard[18] + "---" + mBoard[19] + " |");
        System.out.println("| |\\  |  /| |");
        System.out.println("| | " + mBoard[14] + "-" + mBoard[15] + "-" + mBoard[16] + " | |");
        System.out.println("| | |   | | |");
        System.out.println(mBoard[8] + "-" + mBoard[9] + "-" + mBoard[10] + "   " + mBoard[11] + "-"
                         + mBoard[12] + "-" + mBoard[13]);
        System.out.println("| | |   | | |");
        System.out.println("| | " + mBoard[6] + "---" + mBoard[7] + " | |");
        System.out.println("| |/     \\| |");
        System.out.println("| " + mBoard[3] + "---" + mBoard[4] + "---" + mBoard[5] + " |");
        System.out.println("|/    |    \\|");
        System.out.println(mBoard[0] + "-----" + mBoard[1] + "-----" + mBoard[2]);
    }

    @Override
    public String toString() {
        return String.valueOf(mBoard);
    }

    public static List<Integer> neighbors(int j) throws IllegalArgumentException {
        switch (j) {
            case 0:
                return Arrays.asList(1, 3, 8);
            case 1:
                return Arrays.asList(0, 2, 4);
            case 2:
                return Arrays.asList(1, 5, 13);
            case 3:
                return Arrays.asList(0, 4, 6, 9);
            case 4:
                return Arrays.asList(1, 3, 5);
            case 5:
                return Arrays.asList(2, 4, 7, 12);
            case 6:
                return Arrays.asList(3, 7, 10);
            case 7:
                return Arrays.asList(5, 6, 11);
            case 8:
                return Arrays.asList(0, 9, 20);
            case 9:
                return Arrays.asList(3, 8, 10, 17);
            case 10:
                return Arrays.asList(6, 9, 14);
            case 11:
                return Arrays.asList(7, 12, 16);
            case 12:
                return Arrays.asList(5, 11, 13, 19);
            case 13:
                return Arrays.asList(2, 12, 22);
            case 14:
                return Arrays.asList(10, 15, 17);
            case 15:
                return Arrays.asList(14, 16, 18);
            case 16:
                return Arrays.asList(11, 15, 19);
            case 17:
                return Arrays.asList(9, 14, 18, 20);
            case 18:
                return Arrays.asList(15, 17, 19, 21);
            case 19:
                return Arrays.asList(12, 16, 18, 22);
            case 20:
                return Arrays.asList(8, 17, 21);
            case 21:
                return Arrays.asList(18, 20, 22);
            case 22:
                return Arrays.asList(13, 19, 21);
            default:
                throw new IllegalArgumentException("Invalid location: " + j);
        }
    }

    public boolean closeMill(int j) {
        if (isEmpty(j)) {
            throw new IllegalArgumentException("The mill cannot have x");
        }
        switch (j) {
            case 0:
                return (mill(j, 1, 2) || mill(j, 3, 6) || mill(j, 8, 20));
            case 1:
                return (mill(j, 0, 2));
            case 2:
                return (mill(j, 0, 1) || mill(j, 5, 7) || mill(j, 13, 22));
            case 3:
                return (mill(j, 0, 6) || mill(j, 4, 5) || mill(j, 9, 17));
            case 4:
                return (mill(j, 3, 5));
            case 5:
                return (mill(j, 2, 7) || mill(j, 3, 4) || mill(j, 12, 19));
            case 6:
                return (mill(j, 0, 3) || mill(j, 10, 14));
            case 7:
                return (mill(j, 2, 5) || mill(j, 11, 16));
            case 8:
                return (mill(j, 0, 20) || mill(j, 9, 10));
            case 9:
                return (mill(j, 3, 17) || mill(j, 8, 10));
            case 10:
                return (mill(j, 6, 14) || mill(j, 8, 9));
            case 11:
                return (mill(j, 12, 13) || mill(j, 7, 16));
            case 12:
                return (mill(j, 5, 19) || mill(j, 11, 13));
            case 13:
                return (mill(j, 2, 22) || mill(j, 11, 12));
            case 14:
                return (mill(j, 6, 10) || mill(j, 15, 16) || mill(j, 17, 20));
            case 15:
                return (mill(j, 14, 16) || mill(j, 18, 21));
            case 16:
                return (mill(j, 14, 15) || mill(j, 7, 11) || mill(j, 19, 22));
            case 17:
                return (mill(j, 3, 9) || mill(j, 18, 19) || mill(j, 14, 20));
            case 18:
                return (mill(j, 15, 21) || mill(j, 17, 19));
            case 19:
                return (mill(j, 5, 12) || mill(j, 17, 18) || mill(j, 16, 22));
            case 20:
                return (mill(j, 0, 8) || mill(j, 14, 17) || mill(j, 21, 22));
            case 21:
                return (mill(j, 15, 18) || mill(j, 20, 22));
            case 22:
                return (mill(j, 2, 13) || mill(j, 20, 21) || mill(j, 16, 19));
            default:
                throw new IllegalArgumentException("Invalid location: " + j);
        }
    }

    private boolean mill(int i, int j, int k) {
        return mBoard[i] == mBoard[j] && mBoard[i] == mBoard[k];
    }
}
