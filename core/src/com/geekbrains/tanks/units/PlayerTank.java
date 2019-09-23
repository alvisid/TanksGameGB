package com.geekbrains.tanks.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.geekbrains.tanks.GameScreen;
import com.geekbrains.tanks.Weapon;
import com.geekbrains.tanks.utils.Direction;
import com.geekbrains.tanks.utils.TankOwner;

public class PlayerTank extends Tank {
    int lives;
    int score;

    public PlayerTank(GameScreen game, TextureAtlas atlas) {
        super(game);
        this.ownerType = TankOwner.PLAYER;
        this.weapon = new Weapon(atlas);
        this.texture = atlas.findRegion("playerTankBase");
        this.textureHp = atlas.findRegion("bar");
        this.position = new Vector2(100.0f, 100.0f);
        this.speed = 100.0f;
        this.width = texture.getRegionWidth();
        this.height = texture.getRegionHeight();
        this.hpMax = 10;
//        this.hp = this.hpMax - 2;
        this.hp = this.hpMax;
        this.circle = new Circle(position.x, position.y, (width + height) / 2);
        this.lives = 5;
    }

    public void addScore(int amount) {
        score += amount;
    }

    @Override
    public void destroy() {
        lives--;
        hp = hpMax;
    }

    public void update(float dt) {
        checkMovement(dt);
        float mx = Gdx.input.getX();
        float my = Gdx.graphics.getHeight() - Gdx.input.getY();

        rotateTurretToPoint(mx, my, dt);

        if (Gdx.input.isTouched()) {
            fire();
        }

        super.update(dt);
    }

    public void renderHUD(SpriteBatch batch, BitmapFont font24) {
        font24.draw(batch, "Score: " + score + "\nLives: " + lives, 20, 700);
    }

    public void checkMovement(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//            position.x -= speed * dt;
//            angle = 180.0f;
            move(Direction.LEFT, dt);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            position.x += speed * dt;
//            angle = 0.0f;
            move(Direction.RIGHT, dt);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//            position.y += speed * dt;
//            angle = 90.0f;
            move(Direction.UP, dt);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//            position.y -= speed * dt;
//            angle = 270.0f;
            move(Direction.DOWN, dt);
        }
    }
}
