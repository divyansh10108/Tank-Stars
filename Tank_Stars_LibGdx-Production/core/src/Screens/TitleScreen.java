package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.extinct.tankstars.GameRes.Tank;
import com.extinct.tankstars.TankStars;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;


public class TitleScreen implements Screen {
    TankStars game;
    Texture settingsButton;
    private Stage stage;

    Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
    Texture img;
    Texture playBtn;
    Texture resumeBtn;

    Texture rewardsButton;
    TextureAtlas textureAtlas;
    Texture section;
    Texture titleRes1;
    Texture titleRes2;
    Texture titleRes3;

    Texture buttonMenu;
//    Texture anim;
//    TextureRegion[] anime;
//    Animation tanks;
    float time;

    ArrayList<Tank> tankList = Tank.tankList;
    Texture TankPlaceholder;
    private  TextButton play,exit,load,chage;
    public TitleScreen(TankStars game) {
        this.stage = new Stage(new ScreenViewport());
        this.game = game;
        SettingScreen.bkgMusic.play();
        //rewardsButton = new Texture("coin_stroke.png");
        //img = new Texture("loader_2688x1242.png");//Start Screen IMG
        playBtn =  new Texture("Play-button-icon-in-yellow-color-on-transparent-background-PNG.png");
        titleRes1 = new Texture("tile000.png");
        section = new Texture("tile002.png");

        //buttonMenu =  new Texture("tile001.png");
        Tank.addTanks();
        TankPlaceholder = Tank.tankList.get(Tank.currentTank).getTankTexture();
//        anim = new Texture("output-onlinegiftools.png");
//        tryi  =  new Texture("b2886663613143.5ab66519f3003.gif");
//        anime = new TextureRegion[34];
//        int index = 0;
//        TextureRegion[][] frams = TextureRegion.split(anim,10,1);
//        for (int i = 0; i <34 ; i++) {
//
//                anime[index++] = frams[0][i];
//
//
//        }
//        tanks =  new Animation<>(1f/30f);

        this.buttonInitializer();


    }
//    @Override
//    public void create(){
//        Gdx.input.setInputProcessor(stage);
//    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);
        buttonInitializer();

    }

    @Override
    public void render(float delta) {
        time += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();
        update(delta);
        game.batch.draw(new Texture("Canyon_terrein_tile_8.png"),0,0,1280,720);
        //game.batch.draw(img,0,0,1280,720);
        game.batch.draw(titleRes1,200,550);
        //game.batch.draw(section,700,0);
        stage.draw();
        stage.act();
        game.batch.draw(TankPlaceholder,720,320,557,400);
        /*
        game.batch.draw(buttonMenu,200,400);
        game.batch.draw(buttonMenu,200,300);
        game.batch.draw(buttonMenu,200,200);
        */

        game.batch.end();



    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.batch.dispose();

    }
    private void buttonInitializer(){
        play = new TextButton("Play",mySkin,"default");
        play.setPosition(200,400);
        play.setSize(300,100);
//        play.addListener(new ClickListener(){
//           @Override
//           public void clicked(InputEvent event,float x,float y){
//               try {
//                   game.setScreen(new GameScreen(game));
//               } catch (IOException e) {
//                   e.printStackTrace();
//               }
//           }
//        });
        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                game.setScreen(new MainGamaBox(game));
            }
        });

        exit  = new TextButton("Quit",mySkin,"default");
        exit.setPosition(200,250);
        exit.setSize(300,100);
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                //Set to exit screen
                System.exit(0);
            }
        });

        load =  new TextButton("Load",mySkin,"default");
        load.setPosition(200,100);
        load.setSize(300,100);
        load.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                //Temp
                game.setScreen(new LoadScreen(game));
            }
        });

        chage = new TextButton("Change Tank",mySkin,"default");
        chage.getLabel().setFontScale(0.60f);
        chage.setPosition(825,200);
        chage.setSize(300,100);
        chage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                Tank.currentTank++;
                Tank.currentTank=Tank.currentTank%3;
                TankPlaceholder = Tank.tankList.get(Tank.currentTank).getTankTexture();
            }
        });

        stage.addActor(play);
        stage.addActor(load);
        stage.addActor(exit);
        stage.addActor(chage);

    }
    private void update(float delta){
        stage.act(delta);
    }
}
