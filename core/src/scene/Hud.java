package scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.kayakinganimal.coden.CatHop;
import com.kayakinganimal.coden.HighScoreSave;

/**
 * Created by david on 7/10/2016.
 */
public class Hud {
    public Stage stage;
    private Integer worldTimer, score;
    private float timeCount;

    private BitmapFont font;
    Label countUpLabel, scoreLabel, comboLabel, titleLabel, highScoreLabel;
    HighScoreSave highScore;
    Label.LabelStyle style;
    TextureAtlas buttonAtlas;
    TextButton.TextButtonStyle buttonStyle;
    TextButton playButton;
    Skin skin;

    public Integer getScore() {
        return score;
    }

    public Hud(boolean duringGame, boolean currentScore){//second booleanshow current score?
        score = 0;
        worldTimer = 0;
        timeCount = 0;
        stage = new Stage(new ExtendViewport(400,240));
        font = new BitmapFont(Gdx.files.internal("davidfont.fnt"), false);
        //style = new Label.LabelStyle(font, Color.WHITE);
        style = new Label.LabelStyle(font, new Color(0.188f,0.204f,0.188f,1));
        //style = new Label.LabelStyle(font, new Color(0.741f,0.761f,0.722f,1));
        highScore = new HighScoreSave();
        //creating table
        Table table = new Table();

        //makes the table the size of the stage
        table.setFillParent(true);

        if(!duringGame){//if you want the menu/gameover hud,

            skin = new Skin();

            if(CatHop.gameOver){
                titleLabel = new Label("GAME OVER", style);
            }else{
                titleLabel = new Label("CODEN THE CAT", style);
            }
            //System.out.println("qqst "+Integer.toString(highScore.getHighscore()));
            highScoreLabel = new Label("HIGH SCORE: "+Integer.toString(highScore.getHighscore()), style);

            buttonAtlas = new TextureAtlas("buttons/playbutton.pack");
            skin.addRegions(buttonAtlas);
            buttonStyle = new TextButton.TextButtonStyle();
            buttonStyle.up = skin.getDrawable("playbutton");
            buttonStyle.over = skin.getDrawable("playbuttonpressed");
            buttonStyle.down = skin.getDrawable("playbuttonpressed");
            buttonStyle.font = font;

            playButton = new TextButton("PLAY", buttonStyle);
            //playButton.setPosition(167.5f, 90.0f);

            highScoreLabel.setFontScale(0.5f);
            highScoreLabel.setPosition(10, 20);

            if(currentScore){
                scoreLabel = new Label("SCORE: "+ Integer.toString(CatHop.currentScore), style);
                scoreLabel.setFontScale(0.5f);
                scoreLabel.setPosition(10, 40);
                stage.addActor(scoreLabel);
            }



            //add the play button the the table
            table.center();

            table.add(titleLabel);
            table.row();
            table.add(playButton).padBottom(70).padTop(70);

            stage.addActor(highScoreLabel);
            stage.addActor(table);
            //stage.addActor(playButton);


        }else{//you want the game hud


            //countUpLabel = new Label("TIMER: "+Integer.toString(worldTimer), style);
            comboLabel = new Label("", style);
            scoreLabel = new Label("SCORE: "+ Integer.toString(score), style);

            comboLabel.setFontScale(0.6f);
            //comboLabel.setPosition(160,220);
/*          countUpLabel.setFontScale(0.5f);
            countUpLabel.setPosition(30, 210);*/
            scoreLabel.setFontScale(0.5f);
            scoreLabel.setPosition(30, 210);
            //stage.addActor(countUpLabel);
            table.center();
            table.add(comboLabel).padBottom(215);

            //stage.addActor(comboLabel);
            stage.addActor(table);
            stage.addActor(scoreLabel);
        }

    }

    public void update(float dt, int combo, boolean catAlive){
/*        if(catAlive) {
            timeCount += dt;
            if (timeCount >= 1) {
                worldTimer++;
                countUpLabel.setText("TIMER: " + Integer.toString(worldTimer));
                timeCount = 0;
            }
        }*/
        if(combo>=3){

            comboLabel.setText("COMBO X"+combo);

        }
        if(combo == 1){
            comboLabel.setText("");
        }

    }
    public void update(float dt){
        if(CatHop.adToBeDisplayed){
            playButton.setText("WAIT");
        }
        if(!CatHop.gameOver){
            playButton.setText("PLAY");

        }
    }
    public void scoreUpdate(int add){
        score += add;
        scoreLabel.setText("SCORE: " + Integer.toString(score));
        CatHop.currentScore = score;
    }



    public TextButton getPlayButton() {
        return playButton;
    }

}
