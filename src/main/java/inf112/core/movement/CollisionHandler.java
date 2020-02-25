package inf112.core.movement;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Direction;
import inf112.core.player.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollisionHandler {
    private GameBoard gameBoard;
    private List<Player> players;

    public CollisionHandler(GameBoard gameBoard, List<Player> players) {
        this.gameBoard = gameBoard;
        this.players = players;
    }

    public CollisionHandler() {
        this(new GameBoard(), new ArrayList<Player>());
    }

    public ArrayList<Player> playerCollide(Vector2 position, Direction direction, ArrayList<Player> playerList) {
        Vector2 newPosition = go(position, direction);
        for (Player player : players)
            if (player.getX() == newPosition.x && player.getY() == newPosition.y) {
                playerList.add(0, player);
                playerCollide(new Vector2(player.getX(), player.getY()), direction, playerList);
            }
        return playerList;
    }

    public static Vector2 go(Vector2 position, Direction direction) {
        switch (direction) {
            case NORTH:
                return new Vector2(position.x, position.y + 1);
            case EAST:
                return new Vector2(position.x + 1, position.y);
            case SOUTH:
                return new Vector2(position.x, position.y - 1);
            case WEST:
                return new Vector2(position.x - 1, position.y);
            default:
                throw new IllegalArgumentException("Illegal direction given.");
        }
    }
}
