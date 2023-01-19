package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.extinct.tankstars.TankStars;

public class SettingScreen implements Screen {
    TankStars game;
    private Stage st;
    Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
    public static Music bkgMusic = Gdx.audio.newMusic(Gdx.files.internal("Tanks_res/01. Town Theme 1.mp3"));
    private  TextButton lsg,lsg1,lsg2,lsg3,lsg4;
    public SettingScreen(TankStars game){
        bkgMusic.setLooping(true);
        this.st = new Stage(new ScreenViewport());
        this.game = game;
        this.buttonInitializer();
    }
    @Override
    public void show() {
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

    }
    private void buttonInitializer(){

        lsg = new TextButton("--Settings--",mySkin,"small");
        lsg.getLabel().setFontScale(2);
        lsg.setPosition(380,610);
        lsg.setSize(500,100);
        st.addActor(lsg);st.draw();
        st.act();


        lsg2 = new TextButton("Music Enable",mySkin,"default");
        lsg2.getLabel().setFontScale(0.4f);
        lsg2.setPosition(180,250);
        lsg2.setSize(300,100);
        lsg2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Enable Musics
                bkgMusic.play();
            }
        });
        st.addActor(lsg2);
        st.act();st.draw();

        lsg3 = new TextButton("Music Disable",mySkin,"default");
        lsg3.getLabel().setFontScale(0.4f);
        lsg3.setPosition(630,250);
        lsg3.setSize(300,100);
        lsg3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                bkgMusic.stop();
            }
        });
        st.addActor(lsg3);
        st.act();st.draw();

        lsg4 = new TextButton("Day Screen",mySkin,"default");
        lsg4.getLabel().setFontScale(0.4f);
        lsg4.setPosition(180,450);
        lsg4.setSize(300,100);
        lsg4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen sr = new MainGamaBox(game,255,255,255);
                game.setScreen(sr);

            }
        });
        st.addActor(lsg4);
        st.act();st.draw();

        lsg2 = new TextButton("Night Screen",mySkin,"default");
        lsg2.getLabel().setFontScale(0.4f);
        lsg2.setPosition(630,450);
        lsg2.setSize(300,100);
        lsg2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen sr = new MainGamaBox(game,0,0,0);
                game.setScreen(sr);

            }
        });
        st.addActor(lsg2);
        st.act();st.draw();

        lsg2 = new TextButton("Go Back",mySkin,"default");
        lsg2.setPosition(400,100);
        lsg2.setSize(300,100);
        lsg2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PauseScreen(game));
                game.dispose();
            }
        });
        st.addActor(lsg2);
        st.act();st.draw();

    }
}
