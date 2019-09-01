package tanks.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import tanks.TanksRpgGame;
import tanks.Weapon;
import tanks.utils.Direction;

public class BotTank extends Tank {

    Direction preferedDirection;
    float aiTimer;
    float aiTimerTo;
    boolean active;

    public boolean isActive() {
        return active;
    }

    public BotTank(TanksRpgGame game) {
        super(game);
        this.weapon = new Weapon();
        this.texture = new Texture("bot_tank_base.png");
        this.position = new Vector2(500.0f, 500.0f);
        this.speed = 100.0f;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.hpMax = 3;
        this.hp = this.hpMax;
        this.aiTimerTo = 3.0f;
        this.preferedDirection = Direction.UP;
    }

    public void activate(float x, float y) {
        hpMax = 3;
        hp = hpMax;
        preferedDirection = Direction.values()[MathUtils.random(0, Direction.values().length - 1)];
        angle = preferedDirection.getAngle();
        position.set(x, y);
        aiTimer = 0.0f;
        active = true;
    }

    @Override
    public void update(float dt) {
        aiTimer += dt;
        if (aiTimer >= aiTimerTo) {
            aiTimer = 0.0f;
            aiTimerTo = MathUtils.random(2.5f, 4.0f);
            preferedDirection = Direction.values()[MathUtils.random(0, Direction.values().length - 1)];
            angle = preferedDirection.getAngle();
        }
        position.add(speed * preferedDirection.getVx() * dt, speed * preferedDirection.getVy() * dt);
    }
}
