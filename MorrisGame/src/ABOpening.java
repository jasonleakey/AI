import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Author: Jason Huang (yetianhuang.cs@gmail.com)
 */
public class ABOpening {
    private int depthLimit = 0;
    private int numPositionsEval = 0;

    public long miniMax(GameNode node, long alpha, long beta) {
        // game will never be over in Opening.
        if (depthLimit == node.mDepth /*|| node.isLeaf()*/) {
            numPositionsEval++;
            node.mValue = StaticEstimationImp1.getOpeningEst(node);
            return node.mValue;
        }
        if (GameNode.MAX_PLAYER == node.mPlayer) {
            List<BoardPosition> BPchildren = Generator.generateMovesOpening(node.mPositon);
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
            List<BoardPosition> BPchildren = Generator.generateMovesOpeningBlack(node.mPositon);
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

        // TODO: play a move in the opening phase of the game with alpha-beta-pruning.
        GameNode node = new GameNode();
        node.mPositon = inputBoard;
        node.mDepth = 0;
        node.mPlayer = GameNode.MAX_PLAYER;
        ABOpening abOpening = new ABOpening();
        abOpening.depthLimit = Integer.parseInt(args[2]);
        abOpening.numPositionsEval = 0;
        long est = abOpening.miniMax(node, Long.MIN_VALUE, Long.MAX_VALUE);

        IOHelper.cout(node.mBestMove.mPositon, abOpening.numPositionsEval, est);

        if (IOHelper.DEBUG_DISP) {
            inputBoard.disp();
            node.mBestMove.mPositon.disp();
        }

        IOHelper.writeBoard(args[1], node.mBestMove.mPositon);
    }
}
