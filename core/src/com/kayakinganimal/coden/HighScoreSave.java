package com.kayakinganimal.coden;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by david on 7/16/2016.
 */
public class HighScoreSave {
    private int currentHighScore;
    Preferences prefs = Gdx.app.getPreferences("PreferenceName");

    public HighScoreSave(){
        currentHighScore = prefs.getInteger("hScore");
/*        prefs.putInteger("hScore", 0);
        prefs.flush();*/
    }

    public void highScoreUpdate(int playerScore){

        System.out.println("kcjc currentscore"+currentHighScore);

        if(playerScore > currentHighScore){
            System.out.println("kcjcinside the if method");
            prefs.putInteger("hScore", playerScore);
            prefs.flush();
        }

        System.out.println("kcjcHIGHSCORE "+prefs.getInteger("hScore"));
    }

    public int getHighscore(){
        return prefs.getInteger("hScore");
    }
}
