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
import com.extinct.tankstars.GameRes.Progress;
import com.extinct.tankstars.TankStars;

import java.io.IOException;
import java.util.ArrayList;

public class LoadScreen implements Screen {
    TankStars game;
    private Stage st;
    Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
    private  TextButton lsg,lsg1,lsg2;
    public LoadScreen(TankStars game){
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

        lsg = new TextButton("--Load Save Game--",mySkin,"small");
        lsg.getLabel().setFontScale(2);
        lsg.setPosition(380,610);
        lsg.setSize(500,100);
        st.addActor(lsg);st.draw();
        st.act();

        lsg1 = new TextButton("There is no Saved Game Yet",mySkin,"small");
        lsg1.getLabel().setFontScale(2);
        lsg1.setPosition(380,310);
        lsg1.setSize(500,100);

        if(Progress.playerID<=0){
            st.addActor(lsg1);
            st.draw();st.act();
        }

        lsg2 = new TextButton("Go Back",mySkin,"default");
        lsg2.setPosition(380,110);
        lsg2.setSize(500,100);
        lsg2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TitleScreen(game));
            }
        });
        st.addActor(lsg2);
        st.act();st.draw();

        final ArrayList<TextButton> btnArr = new ArrayList<>();
        for(int i=0;i< Progress.playerID;i++) {
            TextButton btn = new TextButton(""+(i+1), mySkin, "default");
            btn.getLabel().setFontScale(2);
            btn.setPosition(480, 500 - (i) * 150);
            btn.setSize(300, 100);
            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Progress curr = new Progress();
                    Progress.playerID--;
                    try {
                        curr.DeSerialize(1);//Change ot idx
                        game.setScreen(new MainGamaBox(game));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            btnArr.add(btn);
            st.addActor(btn);
            st.draw();st.act();
        }
    }
}