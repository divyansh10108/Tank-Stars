package com.extinct.tankstars.GameRes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Progress implements Serializable {
    public static int playerID=0;
    Tank playerTank;
    Tank enemyTank;
    Level currentLevel;
    String storageLocation;
    public Progress(){
        this.playerTank=new Tank();
        this.enemyTank=new Tank();
        this.currentLevel=new Level();
        playerID++;
    }
    public String getStorageLocation(){
        return storageLocation;
    }
    public void Serialize() throws IOException {
        storageLocation = "assets/storage/"+playerID+".txt";
        ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream((storageLocation)));
        file.writeObject(this);
    }
    public Progress DeSerialize(int PlID) throws IOException, ClassNotFoundException {
        ObjectInputStream file = new ObjectInputStream(new FileInputStream(("assets/storage/"+PlID+".txt")));
        return (Progress) file.readObject();
    }
}