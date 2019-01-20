package com.freemovement.game.screen;

import com.freemovement.game.FreeMovementGame;

public class FreeMovementScreen extends AbstractScreen {
    public FreeMovementScreen(FreeMovementGame game) {
        super(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.getBatch().begin();
        game.getBatch().end();
    }
}
