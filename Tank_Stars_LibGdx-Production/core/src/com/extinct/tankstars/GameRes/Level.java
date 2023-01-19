package com.extinct.tankstars.GameRes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable {
    public static int levelNumber=1;
    public static ArrayList<Level> gameLevels = new ArrayList<>();
    String levelStagePath;
    String levelBackgroundPath;
    int levelCnt ;
    public Level(){
        this.levelStagePath = ("levelStageRes/Terrain.png");
        this.levelBackgroundPath = ("levelBKGRes/Broken_Buildings.png");
        this.levelCnt=levelNumber;
        levelNumber++;
    }
    public static void addInitLevel(){
        Level.gameLevels.add(new Level());
    }
    public Texture getLevelStage() {
        return new Texture(Gdx.files.internal(this.levelStagePath));
    }

    public void setLevelStage(String levelStagePath) {
        this.levelStagePath = levelStagePath;
    }

    public Texture getLevelBackground() {
        return new Texture(Gdx.files.internal(this.levelBackgroundPath));
    }

    public void setLevelBackground(String levelBackgroundPath) {
        this.levelBackgroundPath = levelBackgroundPath;
    }
}
