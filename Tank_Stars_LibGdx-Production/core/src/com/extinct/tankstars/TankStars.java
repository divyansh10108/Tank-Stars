package com.extinct.tankstars;

import Screens.TitleScreen;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.awt.*;

public class TankStars extends Game implements ApplicationListener {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public SpriteBatch batch;
	private Stage stage;
	private Table table;
	private TextButton buttonPlay,buttonExit;
	private Label head;
	private Skin skin;
	private BitmapFont white,black;
	private TextureAtlas atlas;
	private static final float SCALE = 2.0f;
	public static final float PIXEL_PER_METER = 32f;
	private OrthographicCamera orthographicCamera;

	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new TitleScreen(this));
	}



	@Override
	public void render () {
		batch = new SpriteBatch();
		super.render();


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
	public void dispose () {

	}
}
