package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.extinct.tankstars.TankStars;

public class VictoryScreen implements Screen {
    TankStars game;
    private Stage st;
    Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
    private  TextButton lsg,lsg1,lsg2;
    String vic;
    public VictoryScreen(TankStars game,String victor){
        this.st = new Stage(new ScreenViewport());
        this.game = game;
        this.buttonInitializer();
        this.vic=victor;
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

        lsg = new TextButton("Victory",mySkin,"small");
        lsg.getLabel().setFontScale(3);
        lsg.setPosition(380,610);
        lsg.setSize(500,100);
        st.addActor(lsg);st.draw();
        st.act();

        lsg1 = new TextButton("Player: "+ vic +" won!!!",mySkin,"small");
        lsg1.getLabel().setFontScale(2);
        lsg1.setPosition(380,410);
        lsg1.setSize(500,100);
        st.addActor(lsg1);
        st.draw();st.act();

        lsg2 = new TextButton("ReStart",mySkin,"default");
//        lsg2.getLabel().setFontScale(0.2f);
        lsg2.setPosition(280,250);
        lsg2.setSize(300,100);
        lsg2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainGamaBox(game));
            }
        });
        st.addActor(lsg2);
        st.act();st.draw();

        lsg2 = new TextButton("Exit to Main Menu",mySkin,"default");
        lsg2.getLabel().setFontScale(0.5f);
        lsg2.setPosition(680,250);
        lsg2.setSize(300,100);
        lsg2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TitleScreen(game));
                game.dispose();
            }
        });
        st.addActor(lsg2);
        st.act();st.draw();

    }
}
