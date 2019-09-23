package com.geekbrains.tanks.units;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.geekbrains.tanks.GameScreen;
import com.geekbrains.tanks.Weapon;
import com.geekbrains.tanks.utils.Direction;
import com.geekbrains.tanks.utils.TankOwner;

public class BotTank extends Tank {
    Direction prefferedDirection;
    float aiTimer;
    float aiTimerTo;
    float pursuitRadius;
    boolean active;
    Vector3 lastPosition;

    public BotTank(GameScreen game, TextureAtlas atlas) {
        super(game);
        this.ownerType = TankOwner.AI;
        this.weapon = new Weapon(atlas);
        this.texture = atlas.findRegion("botTankBase");
        this.textureHp = atlas.findRegion("bar");
        this.position = new Vector2(500.0f, 500.0f);
        this.speed = 100.0f;
        this.width = texture.getRegionWidth();
        this.height = texture.getRegionHeight();
        this.hpMax = 3;
        this.hp = this.hpMax;
        this.aiTimerTo = 3.0f;
        this.pursuitRadius = 300.0f;
        this.prefferedDirection = Direction.UP;
        this.circle = new Circle(position.x, position.y, (width + height) / 2);
    }

    public boolean isActive() {
        return active;
    }

    public void activate(float x, float y) {
        hpMax = 3;
        hp = hpMax;
        prefferedDirection = Direction.values()[MathUtils.random(0, Direction.values().length - 1)];
        angle = prefferedDirection.getAngle();
        position.set(x, y);
        aiTimer = 0.0f;
        active = true;
    }

    @Override
    public void destroy() {
        active = false;
    }

    public void update(float dt) {
        aiTimer += dt;
        if (aiTimer >= aiTimerTo) {
            aiTimer = 0.0f;
            aiTimerTo = MathUtils.random(2.5f, 4.0f);
            prefferedDirection = Direction.values()[MathUtils.random(0, Direction.values().length - 1)];
            angle = prefferedDirection.getAngle();
        }
//        position.add(speed * prefferedDirection.getVx() * dt, speed * prefferedDirection.getVy() * dt);
        move(prefferedDirection, dt);
//        float dst = this.position.dst(game.getPlayer().getPosition());
        float dst = this.position.dst(gameScreen.getPlayer().getPosition());
        if (dst < pursuitRadius) {
//            rotateTurretToPoint(game.getPlayer().getPosition().x, game.getPlayer().getPosition().y, dt);
            rotateTurretToPoint(gameScreen.getPlayer().getPosition().x, gameScreen.getPlayer().getPosition().y, dt);
            fire();
        }

        if (Math.abs(position.x - lastPosition.x) < 0.5f && Math.abs(position.y - lastPosition.y) < 0.5f) {
            lastPosition.z += dt;
            if (lastPosition.z > 0.3f) {
                aiTimer += 10.0f;
            }
        } else {
            lastPosition.x = position.x;
            lastPosition.y = position.y;
            lastPosition.z = 0.6f;
        }
        super.update(dt);
    }
}
