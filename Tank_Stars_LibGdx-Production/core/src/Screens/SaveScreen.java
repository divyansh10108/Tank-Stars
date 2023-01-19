package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.extinct.tankstars.GameRes.Progress;
import com.extinct.tankstars.TankStars;

import java.io.IOException;

public class SaveScreen implements Screen {
    TankStars game;
    private Stage st;
    boolean isSaved= false;

    Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
    Label saveConf = new Label("   ",mySkin);
    private  TextButton confirm,yes,no,goBack;
    public SaveScreen(TankStars game){
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

        if(isSaved){
            no.removeListener(no.getClickListener());
            yes.removeListener(yes.getClickListener());
        }

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



        confirm = new TextButton("Save Game? ",mySkin,"small");
        confirm.getLabel().setFontScale(2);
        confirm.setPosition(480,500);
        confirm.setSize(300,100);


        yes  = new TextButton("YES",mySkin,"default");
        yes.setPosition(280,350);
        yes.setSize(300,100);
        yes.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                //SAVE
                Progress curr = new Progress();
                try {
                    curr.Serialize();

                    String out = "Saved Game at:"+curr.getStorageLocation();
                    isSaved=true;
                    saveConf = new Label(out,mySkin);
                    st.addActor(saveConf);
                    st.draw();
                    st.act();
                    System.out.println("SAVE CONF");

                    yes.removeListener(this);

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("SErialize Error");
                }
            }
        });

        no =  new TextButton("NO",mySkin,"default");
        no.setPosition(680,350);
        no.setSize(300,100);
        no.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                game.setScreen(new PauseScreen(game));
            }
        });

        goBack =  new TextButton("Go Back",mySkin,"default");
        goBack.setPosition(480,200);
        goBack.setSize(300,100);
        goBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                game.setScreen(new PauseScreen(game));
            }
        });


        st.addActor(confirm);
        st.addActor(yes);
        st.addActor(no);
        st.addActor(goBack);
        st.addActor(saveConf);

    }
}