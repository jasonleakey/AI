import java.util.List;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
public class MiniMaxOpeningBlack {
    private int depthLimit = 0;
    private int numPositionsEval = 0;

    public long miniMax(GameNode node) {
        // game will never be over in Opening.
        if (depthLimit == node.mDepth /*|| node.isLeaf()*/) {
            numPositionsEval++;
            node.mValue = StaticEstimationImp1.getOpeningEst(node);
            return node.mValue;
        }
        if (GameNode.MAX_PLAYER == node.mPlayer) {
            // infinitesimal
            long v = Long.MIN_VALUE;
            List<BoardPosition> BPchildren = Generator.generateMovesOpening(node.mPositon);
            for (BoardPosition BPchild : BPchildren) {
                GameNode childNode = new GameNode();
                childNode.deriveFromParent(node);
                childNode.mPositon = BPchild;
                long tmp = miniMax(childNode);
                if (tmp > v) {
                    v = tmp;
                    node.mBestMove = childNode;
                    node.mValue = tmp;
                }
            }

            return node.mValue;
        } else {
            // this is a MIN player
            // infinity
            long v = Long.MAX_VALUE;
            List<BoardPosition> BPchildren = Generator.generateMovesOpeningBlack(node.mPositon);
            for (BoardPosition BPchild : BPchildren) {
                GameNode childNode = new GameNode();
                childNode.deriveFromParent(node);
                childNode.mPositon = BPchild;
                long tmp = miniMax(childNode);
                if (tmp < v) {
                    v = tmp;
                    node.mBestMove = childNode;
                    node.mValue = tmp;
                }
            }

            return node.mValue;
        }
    }

    public static void main(String[] args) {
        if (!IOHelper.validateParams(args)) {
            return;
        }

        BoardPosition inputBoard = IOHelper.readBoard(args[0]);
        if (null == inputBoard) {
            return;
        }

        // TODO: play a move in the opening phase of the game.
        GameNode node = new GameNode();
        node.mPositon = inputBoard.swap();
        node.mDepth = 0;
        node.mPlayer = GameNode.MAX_PLAYER;
        MiniMaxOpeningBlack miniMaxOpeningBlack = new MiniMaxOpeningBlack();
        miniMaxOpeningBlack.depthLimit = Integer.parseInt(args[2]);
        miniMaxOpeningBlack.numPositionsEval = 0;
        long est = miniMaxOpeningBlack.miniMax(node);

        IOHelper.cout(node.mBestMove.mPositon.swap(), miniMaxOpeningBlack.numPositionsEval, est);

        if (IOHelper.DEBUG_DISP) {
            inputBoard.disp();
            node.mBestMove.mPositon.swap().disp();
        }

        IOHelper.writeBoard(args[1], node.mBestMove.mPositon);
    }
}
