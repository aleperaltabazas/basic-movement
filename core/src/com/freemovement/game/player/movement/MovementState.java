package com.freemovement.game.player.movement;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.freemovement.game.player.Player;

public interface MovementState {
    TextureRegion getFrame(MovementData data);

    void moveNorth(Player player);
    void moveSouth(Player player);
    void moveEast(Player player);
    void moveWest(Player player);
}
