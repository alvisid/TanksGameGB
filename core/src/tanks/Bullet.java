package tanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet {

private Vector2 position;
private Vector2 velocity;
private int damage;
private boolean active;

public boolean isActive() {
    return active;
}

public int getDamage() {
    return damage;
}

public Vector2 getPosition() {
    return position;
}

public Bullet() {
    this.position = new Vector2();
    this.velocity = new Vector2();
    this.active = false;
    this.damage = 0;
}

public void activate(float x, float y, float vx, float vy, int damage) {
    this.active = true;
    this.position.set(x, y);
    this.velocity.set(vx, vy);
    this.damage = damage;
}

public void deactivate() {
    active = false;
}

public void update(float dt) {
    position.mulAdd(velocity, dt);
    if (position.x < 0.0f || position.x > Gdx.graphics.getWidth() || position.y < 0.0f || position.y > Gdx.graphics.getHeight()) {
        deactivate();
    }
}



/*    private Texture texture;
    private float x;
    private float y;
    private float vx;
    private float vy;
    private boolean active;

    public boolean isActive(){
        return active;
    }

    public Bullet() {
        this.texture = new Texture("projectile.png");
        this.x = 0.0f;
        this.y = 0.0f;
        this.vx = 0.0f;
        this.vy = 0.0f;
        this.active = false;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x - 8, y - 8);
    }

    public void activate(float x, float y, float vx, float vy) {
        this.active = true;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    public void deactivate() {
        active = false;
    }

    public void update(float dt){
        x += vx * dt;
        y += vy * dt;
        if (x < 0.0f || x > Gdx.graphics.getWidth() || y < 0.0f || y > Gdx.graphics.getWidth()){
            deactivate();
        }
    }*/
}
