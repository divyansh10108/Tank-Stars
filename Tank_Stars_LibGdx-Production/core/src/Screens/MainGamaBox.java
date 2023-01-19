package Screens;


import static java.lang.Thread.sleep;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.extinct.tankstars.GameRes.Tank;
import com.extinct.tankstars.TankStars;

import java.io.Serializable;

import Scenes.Hud;

public class MainGamaBox implements Screen, Serializable {
    TankStars game;
    Screen currScreen;
    private Hud hud;
    public World world;
    boolean isFin=false;
    public Box2DDebugRenderer debug;
    public OrthographicCamera camera;
    public static float tankAHealth;
    private String bullet1 = "Normal Bullet";
    private String bullet2= "Normal Bullet";
    public static float tankBHealth;
    public static boolean turnA=true;
    public static boolean turnB=false;
    private String vic;
    private boolean displayExp=false;

    Body te;
    boolean isPaused;
    boolean isEnabled;
    boolean isHit = false;
    public int r_t,g_t,b_t;
    int l=0;
    Body tankHolder;
    Body tank2Holder;
    CircleShape s;
    CircleShape s2;
    BodyDef bull;
    BodyDef bull2;
    FixtureDef bullFix = null;
    FixtureDef bullFix2 = null;
    Body drop;
    Body toDelete;
    Body toForce;
    Vector2 movement = new Vector2();
    Vector2 movement3 = new Vector2();
    int velx1=-15;
    int vely1=5;
    int velx2=15;
    int vely2=5;
    public Body b;
    private Body splBody;
    public Body b2;
    public Body grnd;
    private Texture splBull ;
    private Texture exp = new Texture(Gdx.files.internal("levelStageRes/FX_explode_5.png"));

//    ChainShape ground;
    ChainShape ground;
    Vector2 movement2 = new Vector2();
//    Texture gTex =  new Texture("C:\\Users\\91987\\Desktop\\.vscode\\AP Project\\assets\\assets\\Canyon_terrein_tile_8.png");
    Texture gTex = new Texture(Gdx.files.internal("Canyon_terrein_tile_8.png"));
    float[] vertices = {
            -4200, 3,
            1000, 4.2f
    };
    short[] triangles;





    TextureRegion GroundReg = new TextureRegion(gTex);
    PolygonRegion pg  = new PolygonRegion(GroundReg,vertices,triangles);
    BodyDef tank2;
    BodyDef tank1;
    Texture tex;
    SpriteBatch batch;
    Sprite sp;
    public static Tank tankA;
    public static Tank tankB;
    ListenerClass onCollisionListener;
    Texture stageTex;
    PolygonRegion polygonRegion;
    boolean isLoaded;
//    float[] vertices = {-4200, 2, 3, 3, 57, 42, 25, 100};
//    short[] triangles = {0, 1, 2, 2, 3, 0};
//
//    PolygonRegion polygonRegion = new PolygonRegion(GroundReg, vertices, triangles);
//
//    // Create a polygon sprite from the polygon region
//    PolygonSprite sprite = new PolygonSprite(polygonRegion);
//
//    // Create a polygon sprite batch
//    PolygonSpriteBatch batch = new PolygonSpriteBatch();
//
//    SpriteBatch batchGround = new SpriteBatch();


    public MainGamaBox(TankStars game){
        hud= new Hud(game.batch,"Normal bullets","Normal bullets");
        this.game  = game;
        batch = new SpriteBatch();
        Tank.addTanks();
        onCollisionListener = new ListenerClass();
        stageTex = new Texture(Gdx.files.internal("levelStageRes/T1.png"));
        SettingScreen.bkgMusic.play();

    }
    public MainGamaBox(TankStars game,int r_t,int g_t,int b_t){
        bullet1="Normal Bullet";
        bullet2="Normal Bullet";
        hud= new Hud(game.batch,"Normal bullets","Normal bullets");
        this.game  = game;
        batch = new SpriteBatch();
        Tank.addTanks();
        this.r_t=r_t;
        this.g_t=g_t;
        this.b_t=b_t;
        splBull = new Texture(Gdx.files.internal("levelStageRes/missile.png"));

        onCollisionListener = new ListenerClass();
        stageTex = new Texture(Gdx.files.internal("levelStageRes/T1.png"));
        SettingScreen.bkgMusic.play();

    }

    public MainGamaBox(TankStars game,Tank A,Tank B){
        hud= new Hud(game.batch,"Normal bullets","Normal bullets");
        this.game  = game;
        batch = new SpriteBatch();
        Tank.addTanks();
        splBull = new Texture(Gdx.files.internal("levelStageRes/missile.png"));
        onCollisionListener = new ListenerClass();
        stageTex = new Texture(Gdx.files.internal("levelStageRes/T1.png"));
        tankA = A;
        tankB = B;
        isLoaded=true;

    }
    public void createGround(){
        ground  = new ChainShape();
//        ground = new PolygonShape();
//        ground.setAsBox(200/40,150/40);
        BodyDef ball  = new BodyDef();
//        ball.position.set(-2,5.5f); // 1 meter not pixel
        ball.position.set(100/40,100/40);
        FixtureDef fex = new FixtureDef();
        fex.density = 2.5f;
        fex.friction = 0.45f;
        fex.restitution = 0.22f;

        ball.type = BodyDef.BodyType.StaticBody;

        fex.friction = .5f;
        fex.restitution = 0;
        Vector2 vert  = new Vector2();
        vert.add(new Vector2(-4200,3));
        ground.createChain(new Vector2[]{
                new Vector2(-4200,3),
//                new Vector2(-10.8f,4f),
//                new Vector2(-10.4f,3.6f),
//                new Vector2(-10.1f,3.4f),
//                new Vector2(-9.9f,3.8f),
//
//                new Vector2(-8.9f,4f),
//                new Vector2(-8.6f,3.6f),
//                new Vector2(-8.0f,3.4f),
//                new Vector2(-7.6f,3.8f),
//
//                new Vector2(-7.2f,4f),
//                new Vector2(-6.8f,3.6f),
//                new Vector2(-6.5f,3.4f),
//                new Vector2(-5.9f,3.8f),
//
//                new Vector2(-5.6f,4f),
//                new Vector2(-5.2f,3.6f),
//                new Vector2(-4.7f,3.4f),
//                new Vector2(-4.6f,3.8f),
//
//                new Vector2(-4.2f,4f),
//                new Vector2(-4.0f,3.6f),
//                new Vector2(-3.6f,3.2f),
//
//
//                new Vector2(-3.7f,4f),
//                new Vector2(-3.5f,3.6f),
//                new Vector2(-3.2f,3.4f),
//                new Vector2(-2.9f,3.8f),
//
//                new Vector2(-2.4f,4f),
//                new Vector2(-2f,3.6f),
//                new Vector2(-1.6f,3.4f),
//                new Vector2(-1.2f,3.8f),
//
//                new Vector2(-1f,4f),
//                new Vector2(0f,3.6f),
//                new Vector2(0.2f,3.4f),
//                new Vector2(0.6f,3.8f),
//                new Vector2(0.9f,3.5f),

                new Vector2(-1.8f,0.6f),
                new Vector2(-1.4f,0.8f),
                new Vector2(-1.2f,0.85f),
                new Vector2(-0.9f,0.9f),

                new Vector2(2.3f,1.0f),


                new Vector2(2.8f,1.7f),
                new Vector2(3.0f,2.1f),
                new Vector2(3.8f,2.45f),
                new Vector2(4.2f,2.49f),


                new Vector2(4.8f,3.1f),
                new Vector2(5.3f,3.2f),
                new Vector2(5.6f,3.4f),
                new Vector2(6.2f,3.37f),


                new Vector2(6.8f,3.39f),
                new Vector2(14.9f,3.4f),

                new Vector2(15.2f,3.38f),
                new Vector2(15.8f,3.25f),
                new Vector2(16.3f,2.9f),
                new Vector2(16.8f,2.8f),

                new Vector2(17.2f,2.7f),
                new Vector2(17.4f,2.6f),
                new Vector2(17.9f,2.4f),
                new Vector2(18.9f,2.1f),

                new Vector2(19.2f,1.9f),
                new Vector2(19.8f,1.9f),
                new Vector2(20.2f,1.95f),
                new Vector2(20.5f,1.98f),

                new Vector2(21.6f,2.1f),
                new Vector2(22.1f,2.15f),
                new Vector2(22.5f,2.5f),
                new Vector2(23.6f,2.8f),

                new Vector2(24.0f,2.85f),
                new Vector2(24.3f,2.8f),
                new Vector2(24.5f,2.6f),
                new Vector2(24.8f,2.6f),

                new Vector2(25.2f,2.4f),
                new Vector2(25.8f,2.6f),
                new Vector2(26.3f,2.4f),
                new Vector2(26.8f,2.4f),
                new Vector2(28.1f,1.9f),
                new Vector2(30.1f,1.8f),
                new Vector2(30.5f,1.79f),

                new Vector2(30.9f,1.9f),
                new Vector2(42.3f,1.9f),
                new Vector2(42.8f,3.4f),
                new Vector2(44.1f,3.8f),
                new Vector2(44.6f,4f),
                new Vector2(44.9f,3.6f),
                new Vector2(45.2f,3.4f),
                new Vector2(45.7f,3.8f),

                new Vector2(45.8f,4f),
                new Vector2(47.3f,3.6f),
                new Vector2(47.7f,3.4f),
                new Vector2(49.7f,3.8f),

                new Vector2(51.6f,4f),
                new Vector2(51.9f,3.6f),
                new Vector2(52.2f,3.4f),
                new Vector2(53.6f,3.8f),


                new Vector2(1000,4.2f)
        });


        fex.shape =  ground;
        grnd = world.createBody(ball);
        grnd.createFixture(fex);
        grnd.setUserData("ground");

    }

    public void tank1(){
//        tank1  = new BodyDef();
//        tank1.type = BodyDef.BodyType.DynamicBody;
//        BodyDef tempTank = new BodyDef();
//        tempTank.type = BodyDef.BodyType.StaticBody;
//        tempTank.position.set(300/40,350/40);
//        CircleShape tankShape = new CircleShape();
//        FixtureDef tankFixture = new FixtureDef();
////        tankA.tankShape.setAsBox(.5f,1);
//        tankShape.setRadius(50/40);
//        tankFixture.shape=tankShape;
//
//        Body tankBody = world.createBody(tempTank);
//        tankBody.createFixture(tankFixture);
//        tankA.tankSprite = new Sprite(tankA.getTankTexture());
//
//        tankA.tankSprite.setSize(tankA.tankBody.getPosition().x,tankA.tankBody.getPosition().y);
//        tankA.tankBody.setUserData("TankA");




//        //OOPS
        tankA = Tank.tankList.get(0);
        tankA.tankBodyDef = new BodyDef();
        tankA.tankBodyDef.type = BodyDef.BodyType.DynamicBody;
        tankA.tankBodyDef.position.set(5,10);
        tankA.tankShape = new CircleShape();
        tankA.tankFixture = new FixtureDef();
//        tankA.tankShape.setAsBox(.5f,1);
        tankA.tankShape.setRadius(0.6f);
        tankA.tankFixture.shape=tankA.tankShape;
        tankA.tankFixture.friction=2f;
        tankA.tankFixture.restitution=.1f;
        tankA.tankFixture.density=6;
        tankA.tankBody = world.createBody(tankA.tankBodyDef);
        tankA.tankBody.createFixture(tankA.tankFixture);
        tankA.tankSprite = new Sprite(tankA.getTankTexture());

        tankA.tankSprite.setSize(tankA.tankBody.getPosition().x,tankA.tankBody.getPosition().y);
        tankA.tankBody.setUserData("TankA");
        MainGamaBox.tankAHealth=tankA.tankHealth;

        //END OOPS

//        tank1.position.set(8.5f,10);
//        final PolygonShape tank = new PolygonShape();
//        FixtureDef fex = new FixtureDef();
//
//
//        tank.setAsBox(.5f,1);
//        tex = new Texture(Gdx.files.internal("Tanks_res/Tank_Res1.png"));
//        fex.shape =  tank;
//        fex.friction = .75f;
//        fex.restitution = .1f;
//        fex.density = 6;
//        tankHolder =world.createBody(tank1);
//        tank1.type = BodyDef.BodyType.DynamicBody;
//        tankHolder.createFixture(fex);
//
//        sp = new Sprite(tex);
//        sp.setSize(tankHolder.getPosition().x,tankHolder.getPosition().y);
//        ListenerClass cl = new ListenerClass();
//        world.setContactListener(cl);


//        Gdx.input.setInputProcessor(new InputController() {
//            @Override
//            public boolean keyDown(int keycode) {
//                    if(keycode == Input.Keys.A){
//                        tankHolder.setLinearVelocity(-10,0);
//                    }else if(keycode == Input.Keys.D){
//                        tankHolder.setLinearVelocity(10,0);
//                    }
//                    if(keycode == Input.Keys.F){
//                        Body b = world.createBody(bull);
//                            b.createFixture(bullFix);
//                            //b.applyForceToCenter(new Vector2(100,0),true);
//                            b.setLinearVelocity(new Vector2(-20, 5));
//                            te = b;
//                    }
//
//                return true;
//            }
//
//            @Override
//            public boolean keyUp(int keycode){
//                if(keycode == Input.Keys.A){
//                    tankHolder.setLinearVelocity(0,0);
//                }else if(keycode == Input.Keys.D){
//                    tankHolder.setLinearVelocity(0,0);
//                }
//                return true;
//
//            }
//
//        });

//        tank.dispose();


    }
    public void tank2(){

        tankB = Tank.tankList.get(1);
        tankB.tankBodyDef = new BodyDef();
        tankB.tankBodyDef.type = BodyDef.BodyType.DynamicBody;
        tankB.tankBodyDef.position.set(30,10);
        tankB.tankShape = new CircleShape();
        tankB.tankFixture = new FixtureDef();
//        tankB.tankShape.setAsBox(.5f,1);
        tankB.tankShape.setRadius(0.6f);
        tankB.tankFixture.shape=tankB.tankShape;
        tankB.tankFixture.friction=2f;
        tankB.tankFixture.restitution=.1f;
        tankB.tankFixture.density=6;
        tankB.tankBody = world.createBody(tankB.tankBodyDef);
        tankB.tankBody.createFixture(tankB.tankFixture);
        tankB.tankSprite = new Sprite(tankB.getTankTexture());
        tankB.tankSprite.setSize(tankB.tankBody.getPosition().x,tankB.tankBody.getPosition().y);
        tankB.tankBody.setUserData("TankB");
        MainGamaBox.tankBHealth=tankB.tankHealth;

    }

    @Override
    public void show() {
        this.world =  new World(new Vector2(0,-9.8f),true);// vector   2    is gravity vector
        world.setContactListener(onCollisionListener);
        debug =  new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1340,720);
        createGround();
        if(!isLoaded) {
            tank1();
            tank2();
        }
        tankB.tankSprite.flip(true,false);



        bull2 =  new BodyDef();
        bull2.type = BodyDef.BodyType.DynamicBody;
        s2 =  new CircleShape();
        s2.setRadius(0.20f);
        bullFix2 = new FixtureDef();
        bullFix2.shape = s2;

        bullFix2.density = 0.01f;

        bullFix2.restitution = 1.0f;
        bull =  new BodyDef();
        bull.type = BodyDef.BodyType.DynamicBody;
        s =  new CircleShape();
        s.setRadius(0.20f);
        bullFix = new FixtureDef();
        bullFix.shape = s;

        bullFix.density = 0.01f;

        bullFix.restitution = 1.0f;

        final BodyDef dropbox = new BodyDef();
        dropbox.type =BodyDef.BodyType.DynamicBody;
        CircleShape aimer = new CircleShape();
        aimer.setRadius((float) (50/40));
        aimer.setPosition(new Vector2(15,100));
        final FixtureDef aimFix = new FixtureDef();
        aimFix.shape = aimer;
        aimFix.density = 2f;
        aimFix.restitution = 0f;
        Body j = world.createBody(dropbox);
        j.createFixture(aimFix);
        drop = j;
        j.setUserData("DropBox");

        Gdx.input.setInputProcessor(new InputController() {
            @Override
            public boolean keyDown(int keycode) {
                if(keycode == Input.Keys.LEFT){
                    if(turnA)
                        tankB.tankBody.setLinearVelocity(-10,0);

                }else if(keycode == Input.Keys.RIGHT){
                    if(turnA)
                        tankB.tankBody.setLinearVelocity(10,0);
                }
                else if(keycode == Input.Keys.Y){


                    if(isEnabled) {
                        for (int i = 0; i < 3; i++) {
                            final BodyDef aim = new BodyDef();
                            aim.type = BodyDef.BodyType.DynamicBody;
                            CircleShape aimer = new CircleShape();
                            aimer.setRadius((float) (0.05 / 1000f));
                            final FixtureDef aimFix = new FixtureDef();
                            aimFix.shape = s2;

                            aimFix.density = 0.01f;

                            aimFix.restitution = 1.0f;
                            Body j = world.createBody(aim);
                            j.setUserData("bulletFromTankB");
                            j.createFixture(aimFix);
                            j.setLinearVelocity(velx1, vely1);
                            splBody = j;
                            if (j.getPosition().x > tankA.tankBody.getPosition().x + 10) {
                                world.destroyBody(j);
                            }
                        }

                    }

                }
                else if(keycode == Input.Keys.H){
                    if(turnB) {
                        b2 = world.createBody(bull2);
                        b2.createFixture(bullFix2);
                        b2.setUserData("bulletFromTankB");
                        //b.applyForceToCenter(new Vector2(100,0),true);

                        b2.setLinearVelocity(new Vector2(velx2, vely2));
                        turnB=false;
                        turnA=true;
                    }
                }
                else if(keycode == Input.Keys.SPACE){
                    velx1+=0.5f;
                    velx2+=0.5f;
                }
                else if(keycode == Input.Keys.A){
                    if(turnB)
                        tankA.tankBody.setLinearVelocity(-10,0);
                }else if(keycode == Input.Keys.D){
                    if(turnB)
                        tankA.tankBody.setLinearVelocity(10,0);
                }
                else if(keycode == Input.Keys.F){
                    if(turnA) {
                        b = world.createBody(bull);
                        b.createFixture(bullFix);
                        b.setUserData("bulletFromTankA");
                        //b.applyForceToCenter(new Vector2(100,0),true);
                        b.setLinearVelocity(new Vector2(velx1, vely1));
                        //x velocity y velocity
                        te = b;
                        turnA=false;
                        turnB=true;
                    }
                }else if(keycode == Input.Keys.ESCAPE){
                    isPaused=true;
                    pause();
                }
                else if(keycode == Input.Keys.W){
                    vely1+=1;
                }else if(keycode == Input.Keys.S){
                    vely1-=1;
                }else if(keycode == Input.Keys.UP){
                    vely2+=1;
                }else if(keycode == Input.Keys.DOWN){
                    vely2-=1;
                }else if(keycode == Input.Keys.Z){
                    isEnabled=true;

                }


                return true;
            }

            @Override
            public boolean keyUp(int keycode){
                if(keycode == Input.Keys.LEFT){
                    tankB.tankBody.setLinearVelocity(0,0);
                }else if(keycode == Input.Keys.RIGHT){
                    tankB.tankBody.setLinearVelocity(0,0);
                }else if(keycode == Input.Keys.A){
                    tankA.tankBody.setLinearVelocity(0,0);
                }else if(keycode == Input.Keys.D){
                    tankA.tankBody.setLinearVelocity(0,0);
                }
                return true;
            }
        });
////        final BodyDef bull =  new BodyDef();
////        bull.type = BodyDef.BodyType.DynamicBody;
////        s =  new CircleShape();
////        s.setRadius(0.20f);
////        final FixtureDef bullFix = new FixtureDef();
////        bullFix.shape = s;
////        bullFix.density = 0.01f;
////
////        bullFix.restitution = 1.0f;
////        Gdx.input.setInputProcessor(new InputController(){
////            @Override
////            public boolean keyDown(int keycode){
////                switch (keycode){
////                    case Input.Keys.D:
////                        movement.x = 100;
//////
////                    case Input.Keys.A:
////                        movement.x = -100;
////
//////
//////                    case Input.Keys.F :
//////
//////                            Body b = world.createBody(bull);
//////                            b.createFixture(bullFix);
//////                            //b.applyForceToCenter(new Vector2(100,0),true);
//////                            b.setLinearVelocity(new Vector2(-20, 5));
//////                            te = b;
//////                            l += 1;
//////
////
////                    case Input.Keys.S:
////                        movement.x = 0;
////                }
////                return true;
////            }
////
////            @Override
////            public boolean keyUp(int keycode) {
////                switch(keycode){
////                    case Input.Keys.D:
////                        movement.x = 0;
////                    case Input.Keys.A:
////                        movement.x = 0;
////                    case Input.Keys.F:
////                        movement2.x = -10;
////
////
////                }
////                return true;
////            }
////        });
////        BodyDef ball  = new BodyDef();
////        BodyDef tank2 = new BodyDef();
////
////
////        ball.type =  BodyDef.BodyType.DynamicBody;
////        ball.position.set(-2,5.5f); // 1 meter not pixel
////        FixtureDef fex = new FixtureDef();
////        fex.density = 2.5f;
////        fex.friction = 0.45f;
////        fex.restitution = 0.22f;
////
////        CircleShape shape =  new CircleShape();
////        shape.setRadius(0.25f);
////        fex.shape =  shape;
////
////        shape.dispose();
////
////        // Ground
////        ball.type = BodyDef.BodyType.StaticBody;
////        ball.position.set(0,0);
////        fex.friction = .5f;
////        fex.restitution = 0;
////
////        ground =  new ChainShape();
////
////        ground.createChain(new Vector2[] {new Vector2(-100,2),new Vector2(4200,3)});
////        GroundReg.setRegion(0,0,gTex.getWidth(),gTex.getHeight());
////        //,new Vector2(3,3),new Vector2(4,4),new Vector2(5,3),new Vector2(6,3),new Vector2(8,4),new Vector2(4000f,3.5f)});
////        fex.shape =  ground;
////        world.createBody(ball).createFixture(fex);
////        ground.dispose();
////
////        ball.type = BodyDef.BodyType.DynamicBody;
////        ball.position.set(5.5f,10);
////        PolygonShape tank = new PolygonShape();
////        PolygonShape tankr = new PolygonShape();
////        tank.setAsBox(.5f,1);
////        tankr.setAsBox(.5f,1);
////        fex.shape =  tank;
////        fex.friction = .75f;
////        fex.restitution = .1f;
////        fex.density = 6;
////        tankHolder =world.createBody(ball);
////        tank2.type = BodyDef.BodyType.DynamicBody;
////        tank2.position.set(2.5f,10);
////        tank2Holder = world.createBody(tank2);
////        tankHolder.createFixture(fex);
////        tank2Holder.createFixture(fex);
////
////        tank.dispose();
    }

    @Override
    public void render(float delta) {



        Gdx.gl.glClearColor(this.r_t,this.g_t,this.b_t,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        System.out.println("DEB:"+this.r_t+" "+this.g_t+" "+this.b_t);
        ScreenUtils.clear(this.r_t,this.g_t,this.b_t,1);
        camera.update();
        //Add bkg texture here

        world.step(1/60f,6,2);
        if(toDelete!=null){
            world.destroyBody(toDelete);
            toDelete=null;
            if(toForce!=null) {
                float val = 20f;
                if (toForce.getUserData() == "TankA") {
                    val=-val;
                }
                toForce.applyLinearImpulse(val, 0f, toForce.getPosition().x, toForce.getPosition().y, true);
                toForce = null;
            }
        }
        //        tankHolder.applyForceToCenter(movement,true);
//        tankHolder.applyLinearImpulse(movement2,new Vector2(tankHolder.getPosition().x,tankHolder.getPosition().y),true);

//        tank2Holder.applyForceToCenter(movement3,true);
//        tank2Holder.applyLinearImpulse(movement2,new Vector2(tank2Holder.getPosition().x +2,tank2Holder.getPosition().y+2),true);
//        l=0;
//        if(l==1) {
            //camera.position.set(tankHolder.getPosition().x, tankHolder.getPosition().y, 0);
//
//        }
//        else{
//            camera.position.set(tank2Holder.getPosition().x, tank2Holder.getPosition().y, 0);
//
//        }
        s.setPosition(new Vector2(tankA.tankBody.getPosition().x,tankA.tankBody.getPosition().y));
        s2.setPosition(new Vector2(tankB.tankBody.getPosition().x-50/40,tankB.tankBody.getPosition().y));
        game.batch.setProjectionMatrix(hud.st.getCamera().combined);
        hud.st.draw();
//
//
//        movement2.x = 0;
       //camera.update();
        if(isFin){
            game.setScreen(new VictoryScreen(game,vic));
            game.dispose();
        }

        debug.render(world,camera.combined.scl(40));

        batch.begin();

        tankA.tankSprite.draw(batch);
        Vector2 posA = tankA.tankBody.getPosition();
        tankA.tankSprite.setSize(250,150);
        tankA.tankSprite.setPosition(posA.x*40 -100,posA.y*40 -70);

        tankB.tankSprite.draw(batch);
        Vector2 posB = tankB.tankBody.getPosition();
        tankB.tankSprite.setSize(250,150);

        if(displayExp){
            Sprite s = new Sprite(exp);
            s.setPosition(posA.x,posA.y);
            s.draw(batch);
            try {
                sleep(2,20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        tankB.tankSprite.setPosition(posB.x*40 -150,posB.y*40 -70);

        Sprite s = new Sprite(new Texture(Gdx.files.internal("levelStageRes/dropbox.png")));
        s.setPosition(drop.getPosition().x*40 -150,drop.getPosition().y*4-70);
        s.setSize(100,100);
        s.draw(batch);


//        Sprite splsp = new Sprite(splBull);
//        splsp.draw(batch);
//
//        Vector2 posBull = splBody.getPosition();
//        splsp.setSize(250,150);
//        splsp.setPosition(posBull.x*40 -100,posBull.y*40 -70);

//        System.out.println(tankA.tankBody.getPosition().x*40+ " " + tankA.tankBody.getPosition().y*40);
//        tankA.tankSprite.setPosition(pos.x*40+620,pos.y*40+215);

//        batch.draw(tankA.tankSprite,pos.x+640,pos.y+180);
        batch.draw(stageTex,0,0,1280,220);

//        Label turn;
        if(isEnabled){
            bullet2="Speacial Bomb";
        }
        BitmapFont font = new BitmapFont();
        font.draw(batch,bullet1,300,600,1000,12,true);
        font.draw(batch,bullet2,900,600,1000,12,true);


        if(MainGamaBox.turnA){
            font.draw(batch,"PLAYER 1's Turn",600,600);
        }else{
            font.draw(batch,"PLAYER 2's Turn",600,600);
        }
        batch.end();

        //Saving
        tankA.setCurrentX(posA.x*40 -100);
        tankA.setCurrentY(posA.y*40 -70);

        tankB.setCurrentX(posB.x*40 -150);
        tankB.setCurrentY(posB.y*40 -70);

    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        game.setScreen(new PauseScreen(game,this));
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        if(!isPaused) {
            world.dispose();
            debug.dispose();
        }
    }
    class ListenerClass implements ContactListener {
        @Override
        public void endContact(Contact contact) {
            Fixture fixtureA = contact.getFixtureA();
            Fixture fixtureB = contact.getFixtureB();

//            Gdx.app.log("ENDContact", "between " + fixtureA.toString() + " and " + fixtureB.toString());

        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {

        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {

        }

        @Override
        public void beginContact(Contact contact) {
            Body BodyA = contact.getFixtureA().getBody();
            Body BodyB = contact.getFixtureB().getBody();

            if(BodyB.getUserData()==("TankA") && BodyA.getUserData()==("TankB") ||
                    BodyA.getUserData()==("TankA") && BodyB.getUserData()==("TankB")){
                System.out.println("Collide");
            }
            if(BodyA.getUserData()=="ground" && BodyB.getUserData()=="bulletFromTankA" ||
                    BodyB.getUserData()=="ground" && BodyA.getUserData()=="bulletFromTankA"){
                if(BodyA.getUserData()=="bulletFromTankA"){
                    toDelete=BodyA;
                }else {
                    toDelete = BodyB;
                }
            }

            if(BodyA.getUserData()=="ground" && BodyB.getUserData()=="bulletFromTankB" ||
                    BodyB.getUserData()=="ground" && BodyA.getUserData()=="bulletFromTankB"){
                if(BodyA.getUserData()=="bulletFromTankB"){
                    toDelete=BodyA;
                }else {
                    toDelete = BodyB;
                }
            }

            if(BodyA.getUserData()=="TankB" && BodyB.getUserData()=="bulletFromTankA" ||
                    BodyB.getUserData()=="TankB" && BodyA.getUserData()=="bulletFromTankA"){
                if(BodyA.getUserData()=="bulletFromTankA"){
                    toDelete=BodyA;
                    toForce = BodyB;
                    tankB.tankHealth-=tankA.getCurrentBulletDamage();
                    MainGamaBox.tankBHealth=tankB.tankHealth;
                    displayExp=true;
                    float w = hud.getP2HealthBar().getWidth();
                    w-=10;
                    if(w<=0){
                        isFin=true;
                        vic="Player 2";
                    }else
                        hud.getP2HealthBar().setWidth(w);
                    displayExp=false;
                }else {
                    toDelete = BodyB;
                    toForce=BodyA;
                    tankB.tankHealth-=tankA.getCurrentBulletDamage();
                    MainGamaBox.tankBHealth=tankB.tankHealth;
                    displayExp=true;
                    float w = hud.getP2HealthBar().getWidth();
                    w-=10;
                    if(w<=0){
                        isFin=true;
                        vic="Player 2";
                    }else
                        hud.getP2HealthBar().setWidth(w);

                    displayExp=false;
                }
            }

            if(BodyA.getUserData()=="TankA" && BodyB.getUserData()=="bulletFromTankB" ||
                    BodyB.getUserData()=="TankA" && BodyA.getUserData()=="bulletFromTankB"){
                if(BodyA.getUserData()=="bulletFromTankB"){
                    toDelete=BodyA;
                    toForce = BodyB;
                    tankA.tankHealth-=tankB.getCurrentBulletDamage();
                    MainGamaBox.tankAHealth=tankA.tankHealth;
                    displayExp=true;

                    float w = hud.getP1HealthBar().getWidth();
                    w-=10;
                    if(w<=0){
                        isFin=true;
                        vic="Player 1";
                    }else
                        hud.getP1HealthBar().setWidth(w);

                    displayExp=false;
                }else {
                    toDelete = BodyB;
                    toForce=BodyA;
                    tankA.tankHealth-=tankB.getCurrentBulletDamage();
                    MainGamaBox.tankAHealth=tankA.tankHealth;
                    displayExp=true;
                    float w = hud.getP1HealthBar().getWidth();
                    w-=10;
                    if(w<=0){
                        isFin=true;
                        vic="Player 1";
                    }else
                        hud.getP1HealthBar().setWidth(w);

                    displayExp=false;
                }
            }

            if(BodyA.getUserData()=="TankA" && BodyB.getUserData()=="DropBox" ||
                    BodyB.getUserData()=="TankA" && BodyA.getUserData()=="DropBox") {
                if (BodyA.getUserData() == "TankA") {
                    isEnabled=true;
                }
            }
            if(BodyA.getUserData()=="TankB" && BodyB.getUserData()=="DropBox" ||
                    BodyB.getUserData()=="TankB" && BodyA.getUserData()=="DropBox") {
                if (BodyA.getUserData() == "TankB") {
                    isEnabled=true;
                    toDelete=drop;
                }
            }
        }
    };
}

