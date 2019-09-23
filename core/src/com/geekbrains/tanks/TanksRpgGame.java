package com.geekbrains.tanks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TanksRpgGame extends Game {
    //    private static final boolean FIRENDLY_FIRE = false;
    private SpriteBatch batch;
//    private Map map;
//    private PlayerTank player;
//    private BulletEmitter bulletEmitter;
//    private BotEmitter botEmitter;
//    private float gameTimer;

    // Идеи:
    // =======================
    // + (Кирилл) Поправить огонь
    // + Размер с 32х32 до 48х48
    // + Вектора/эмиттеры
    // + Завести константы
    // + Полоса хп
    // + Прицеливание мышкой
    // + Отделить башню от корпуса
    // + Не выходить за экран
    // +- Разрушаемые стены / карта (материалы стен) / вода
    // + Боты
    // Продумать добавление дружественного бота
    // Добавить опыт и повышение уровня
    // Штаб
    // Powerups
    // + Разные виды оружия
    // Добавить интерфейс (работа с кнопками)
    // + Ограничение радиуса стрельбы
    // Звуки
    // Анимацию
    // Старт/окончание игры
    // Простую систему экранов, с переходами
    // Защита от появление в стене ботов

//    public PlayerTank getPlayer() {
//        return player;
//    }
//
//    public BulletEmitter getBulletEmitter() {
//        return bulletEmitter;
//    }
//
//    @Override
//    public void create() {
//        TextureAtlas atlas = new TextureAtlas("game.pack");
//        batch = new SpriteBatch();
//        map = new Map(atlas);
//        player = new PlayerTank(this, atlas);
//        bulletEmitter = new BulletEmitter(atlas);
//        botEmitter = new BotEmitter(this, atlas);
//        botEmitter.activate(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight()));
//    }

//    @Override
//    public void render() {
//        float dt = Gdx.graphics.getDeltaTime();
//        update(dt);
//        Gdx.gl.glClearColor(0, 0.6f, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.begin();
//        map.render(batch);
//        player.render(batch);
//        botEmitter.render(batch);
//        bulletEmitter.render(batch);
//        batch.end();
//    }

//    public void update(float dt) {
//        gameTimer += dt;
//        if (gameTimer > 5.0f) {
//            gameTimer = 0.0f;
//            botEmitter.activate(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight()));
//        }
//        player.update(dt);
//        botEmitter.update(dt);
//        bulletEmitter.update(dt);
//        checkCollisions();
//    }

//    public void checkCollisions() {
//        for (int i = 0; i < bulletEmitter.getBullets().length; i++) {
//            Bullet bullet = bulletEmitter.getBullets()[i];
//            if (bullet.isActive()) {
//                for (int j = 0; j < botEmitter.getBots().length; j++) {
//                    BotTank bot = botEmitter.getBots()[j];
//                    if (bot.isActive()) {
//                        if (checkBulletAndTank(bot, bullet) && bot.getCircle().contains(bullet.getPosition())) {
//                            bullet.deactivate();
//                            bot.takeDamage(bullet.getDamage());
//                            break;
//                        }
//                    }
//                }
//
//                if (checkBulletAndTank(player, bullet) && player.getCircle().contains(bullet.getPosition())) {
//                    bullet.deactivate();
//                    player.takeDamage(bullet.getDamage());
//                }
//
//                map.checkWallAndBulletsCollision(bullet);
//            }
//        }
//    }
//
//    public boolean checkBulletAndTank(Tank tank, Bullet bullet) {
//        if (!FIRENDLY_FIRE) {
//            return tank.getOwnerType() != bullet.getOwner().getOwnerType();
//        } else {
//            return tank != bullet.getOwner();
//        }
//    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        ScreenManager.getInstance().init(this.batch);
        ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.GAME);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        getScreen().render(dt);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
