package tanks.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import tanks.TanksRpgGame;
import tanks.Weapon;
import tanks.utils.Utils;

public abstract class Tank {
    TanksRpgGame game;
    Weapon weapon;
    Texture texture;
    Vector2 position;

    int hp;
    int hpMax;

    float speed;
    float angle;

    float turretAngle;
    float fireTimer;

    int width;
    int height;

    public Tank(TanksRpgGame game) {
        this.game = game;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - width / 2, position.y - height / 2, width / 2, height / 2, width, height, 1, 1, angle, 0, 0, width, height, false, false);
        batch.draw(weapon.getTexture(), position.x - width / 2, position.y - height / 2, width / 2, height / 2, width, height, 1, 1, turretAngle, 0, 0, width, height, false, false);
    }

    public abstract void update(float dt);

    public void rotateTurretToPoint(float pointX, float pointY, float dt) {
        float angleTo = Utils.getAngle(position.x, position.y, pointX, pointY);
        turretAngle = Utils.makeRotation(turretAngle, angleTo, 270.0f, dt);
        turretAngle = Utils.angleToFromNegPiToPosPi(turretAngle);
    }

    public void fire(float dt) {
        fireTimer += dt;
        if (fireTimer >= weapon.getFirePeriod()) {
            fireTimer = 0.0f;
            float angleRad = (float) Math.toRadians(turretAngle);
            game.getBulletEmitter().activate(position.x, position.y, 320.0f * (float) Math.cos(angleRad), 320.0f * (float) Math.sin(angleRad), weapon.getDamage());
        }
    }


 /*   private TanksRpgGame game;
    private Texture texture;
    private float x;
    private float y;
    private float speed;
    private float angle;

    public Tank(TanksRpgGame game) {
        this.game = game;
        this.texture = new Texture("Tank.png");
        this.x = 100.0f;
        this.y = 100.0f;
        this.speed = 100.0f;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x - 16, y - 16, 16, 16, 32, 32,1, 1, angle, 0, 0, 32, 32, false, false);
    }

    public void update(float dt) {
        checkMovement(dt);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            fire();
        }
    }

    private void checkMovement(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x -= speed * dt;
            angle = 180.0f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x += speed * dt;
            angle = 0.0f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            y += speed * dt;
            angle = 90.0f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y -= speed * dt;
            angle = 270.0f;
        }
    }

    public void fire() {
        if (!game.getBullet().isActive()) {
            float angleRad = (float) Math.toRadians(angle);
            game.getBullet().activate(x, y, 320.0f * (float) Math.cos(angleRad), 320.0f * (float) Math.sin(angleRad));

        }
    }*/
}
