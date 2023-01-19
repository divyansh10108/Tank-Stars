package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.extinct.tankstars.GameRes.Tank;
import com.extinct.tankstars.TankStars;

import java.security.Key;

public class PauseScreen implements Screen {
    TankStars game;
    Screen retScreen;
    private Stage st;
    Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

    private  TextButton pause,resume,exit,settings,save;
    public PauseScreen(TankStars game){
        this.st = new Stage(new ScreenViewport());
        this.game = game;
        this.buttonInitializer();
    }
    public PauseScreen(TankStars game,Screen s) {
        this.st = new Stage(new ScreenViewport());
        this.game = game;
        this.retScreen=s;
        this.buttonInitializer();
    }
    @Override
    public void show(){
        Gdx.input.setInputProcessor(st);
        buttonInitializer();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(new Texture("Canyon_terrein_tile_8.png"),0,0,1280,720);
        game.batch.end();
        st.draw();
        st.act();

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
        pause = new TextButton("------Pause------",mySkin,"small");
        pause.getLabel().setFontScale(2);
        pause.setPosition(380,610);
        pause.setSize(500,100);

        resume = new TextButton("Resume",mySkin,"default");
        resume.setPosition(480,500);
        resume.setSize(300,100);
        resume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(retScreen);
            }
        });

        exit  = new TextButton("Exit",mySkin,"default");
        exit.setPosition(480,350);
        exit.setSize(300,100);
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                //Set to exit screen
                game.setScreen(new ExitScreen(game));
            }
        });

        save =  new TextButton("Save",mySkin,"default");
        save.setPosition(480,200);
        save.setSize(300,100);
        save.addListener(new ClickListener(){
           @Override
           public void clicked(InputEvent event,float x,float y){
               game.setScreen(new SaveScreen(game));
           }
        });

        settings = new TextButton("Settings",mySkin,"default");
        //settings.getLabel().setFontScale(0.60f);
        settings.setPosition(480,50);
        settings.setSize(300,100);
        settings.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                Screen sr = new SettingScreen(game);
                game.setScreen(sr);
                sr.dispose();
            }
        });
        st.addActor(pause);
        st.addActor(resume);
        st.addActor(save);
        st.addActor(exit);
        st.addActor(settings);

    }
}
