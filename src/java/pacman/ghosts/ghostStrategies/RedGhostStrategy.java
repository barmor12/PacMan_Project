package pacman.ghosts.ghostStrategies;

import pacman.game.GameSession;
import pacman.game.GameplayPanel;

public class RedGhostStrategy implements GhostStrategy {

    @Override
    public int[] getChaseTargetPosition() {
        int[] targetPosition = new int[2];
        targetPosition[0] = GameSession.getPacmanEntity().getxPos();
        targetPosition[1] = GameSession.getPacmanEntity().getyPos();
        return targetPosition;
    }

    @Override
    public GhostStrategy clone() {
        return new RedGhostStrategy();
    }
    @Override
    public int[] getScatterTargetPosition() {
        int[] scatterPosition = new int[2];
        scatterPosition[0] = GameplayPanel.gameWidth;
        scatterPosition[1] = 0;
        return scatterPosition;
    }
}