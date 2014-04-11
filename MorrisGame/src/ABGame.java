import java.util.List;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
public class ABGame {
    private int depthLimit = 0;
    private int numPositionsEval = 0;

    public long miniMax(GameNode node, long alpha, long beta) {
        // game will never be over in Opening.
        if (depthLimit == node.mDepth || node.isLeaf()) {
            numPositionsEval++;
            node.mValue = StaticEstimationImp1.getMidEndGameEst(node);
            return node.mValue;
        }
        if (GameNode.MAX_PLAYER == node.mPlayer) {
            List<BoardPosition> BPchildren = Generator.generateMovesMidgameEndgame(node.mPositon);
            for (BoardPosition BPchild : BPchildren) {
                GameNode childNode = new GameNode();
                childNode.deriveFromParent(node);
                childNode.mPositon = BPchild;
                long tmp = miniMax(childNode, alpha, beta);
                if (tmp > alpha) {
                    alpha = tmp;
                    node.mBestMove = childNode;
                    node.mValue = tmp;
                }

                if (beta <= alpha) {
                    break;
                }
            }

            return alpha;
        } else {
            // this is a MIN player
            List<BoardPosition> BPchildren = Generator.generateMovesMidgameEndgameBlack(node.mPositon);
            for (BoardPosition BPchild : BPchildren) {
                GameNode childNode = new GameNode();
                childNode.deriveFromParent(node);
                childNode.mPositon = BPchild;
                long tmp = miniMax(childNode, alpha, beta);
                if (tmp < beta) {
                    beta = tmp;
                    node.mBestMove = childNode;
                    node.mValue = tmp;
                }

                if (beta <= alpha) {
                    break;
                }
            }

            return beta;
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
        node.mPositon = inputBoard;
        node.mDepth = 0;
        node.mPlayer = GameNode.MAX_PLAYER;
        ABGame abGame = new ABGame();
        abGame.depthLimit = Integer.parseInt(args[2]);
        abGame.numPositionsEval = 0;
        long est = abGame.miniMax(node, Long.MIN_VALUE, Long.MAX_VALUE);

        IOHelper.cout(node.mBestMove.mPositon, abGame.numPositionsEval, est);

        if (IOHelper.DEBUG_DISP) {
            inputBoard.disp();
            node.mBestMove.mPositon.disp();
        }

        IOHelper.writeBoard(args[1], node.mBestMove.mPositon);
    }
}
