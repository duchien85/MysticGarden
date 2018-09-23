package com.quillraven.game.core.gamestate;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.quillraven.game.core.input.InputManager;
import com.quillraven.game.core.input.KeyInputListener;
import com.quillraven.game.core.ui.HUD;
import com.quillraven.game.core.ui.TTFSkin;

public abstract class GameState<T extends Table> implements Disposable, KeyInputListener {
    private final EGameState type;
    protected final HUD hud;
    protected final T gameStateHUD;

    protected GameState(final EGameState type, final HUD hud) {
        this.type = type;
        this.hud = hud;
        gameStateHUD = createHUD(hud, hud.getSkin());
        hud.addGameStateHUD(gameStateHUD);
        gameStateHUD.setVisible(false);
    }

    protected abstract T createHUD(final HUD hud, final TTFSkin skin);

    public EGameState getType() {
        return type;
    }

    public void activate() {
        InputManager.INSTANCE.addKeyInputListener(this);
        gameStateHUD.setVisible(true);
    }

    public void deactivate() {
        InputManager.INSTANCE.removeKeyInputListener(this);
        gameStateHUD.setVisible(false);
    }

    public abstract void step(final float fixedTimeStep);

    public abstract void render(final float alpha);

    public abstract void resize(final int width, final int height);
}