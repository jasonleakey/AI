import java.util.List;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
public class MiniMaxGameBlack {
    private int depthLimit = 0;
    private int numPositionsEval = 0;

    public long miniMax(GameNode node) {
        if (depthLimit == node.mDepth || node.isLeaf()) {
            numPositionsEval++;
            node.mValue = StaticEstimationImp1.getMidEndGameEst(node);
            return node.mValue;
        }
        if (GameNode.MAX_PLAYER == node.mPlayer) {
            // infinitesimal
            long v = Long.MIN_VALUE;
            List<BoardPosition> BPchildren = Generator.generateMovesMidgameEndgame(node.mPositon);
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
            List<BoardPosition> BPchildren = Generator.generateMovesMidgameEndgameBlack(node.mPositon);
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

        // TODO: paly a move in the midgame-endgame phase of the game.
        GameNode node = new GameNode();
        node.mPositon = inputBoard.swap();
        node.mDepth = 0;
        node.mPlayer = GameNode.MAX_PLAYER;
        MiniMaxGameBlack miniMaxGameBlack = new MiniMaxGameBlack();
        miniMaxGameBlack.depthLimit = Integer.parseInt(args[2]);
        miniMaxGameBlack.numPositionsEval = 0;
        long est = miniMaxGameBlack.miniMax(node);

        IOHelper.cout(node.mBestMove.mPositon.swap(), miniMaxGameBlack.numPositionsEval, est);

        if (IOHelper.DEBUG_DISP) {
            inputBoard.disp();
            node.mBestMove.mPositon.swap().disp();
        }

        IOHelper.writeBoard(args[1], node.mBestMove.mPositon);
    }
}
