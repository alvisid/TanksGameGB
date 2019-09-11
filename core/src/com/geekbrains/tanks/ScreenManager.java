package com.geekbrains.tanks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenManager {
    private static ScreenManager ourInstance = new ScreenManager();
    private Game game;
    private GameScreen gameScreen;

    private ScreenManager() {

    }

    public static ScreenManager getInstance() {
        return ourInstance;
    }

    public void init(SpriteBatch batch) {
        this.game = game;
        this.gameScreen = new GameScreen(batch);
    }

    public void setScreen(ScreenType screenType) {
        Screen currentScreen = game.getScreen();
        switch (screenType) {
            case GAME:
                game.setScreen(gameScreen);
                break;
        }
        if (currentScreen != null) {
            currentScreen.dispose();
        }

    }

    public enum ScreenType {
        MENU, GAME
    }
}
