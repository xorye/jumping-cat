package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kayakinganimal.coden.CatHop;
import com.kayakinganimal.coden.HighScoreSave;

import scene.Hud;

/**
 * Created by david on 7/6/2016.
 */
public class GameOverState extends State{


    private Hud hud;
    private boolean play;
    private HighScoreSave highScore;
    private boolean showCurrentScore;








    public GameOverState(GameStateManager gsm, int playerScore) {
        super(gsm);


        showCurrentScore = true;
        highScore = new HighScoreSave();
        highScore.highScoreUpdate(playerScore);

        //CatHop.gameOver = true;
        play = false;
        cam.setToOrtho(false, CatHop.WIDTH/2, CatHop.HEIGHT/2);
        //playButton = new Texture("playbutton.png");

        hud = new Hud(false, showCurrentScore);
        hud.getPlayButton().setPosition(cam.viewportWidth/2 -hud.getPlayButton().getWidth()/2, cam.viewportHeight/2 - hud.getPlayButton().getHeight());
    }

    @Override
    public void handleInput() {

        Gdx.input.setInputProcessor(hud.stage);
        hud.getPlayButton().addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                play = true;
            }
        });

    }

    @Override
    public void update(float dt) {
        if(CatHop.wifi) {
            hud.update(dt);
            if (!CatHop.gameOver) {
                hud.update(dt);
                handleInput();
            }
            CatHop.adToBeDisplayed = false;
            CatHop.wifi = false;
        }else{
            hud.update(dt);
            handleInput();

        }
        if (play) {
            gsm.set(new PlayState(gsm));

        }


    }


    @Override
    public void render(SpriteBatch sb) {


        /*System.out.println(prefs.getInteger("highScore"));*/
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //sb.draw(playButton, cam.viewportWidth/2 - playButton.getWidth()/2, cam.viewportHeight/2 - playButton.getHeight());
        //sb.draw(gameOver, cam.viewportWidth/2 - gameOver.getWidth()/2, cam.viewportHeight - 20);
        sb.end();
        hud.stage.act();
        hud.stage.draw();
    }

    @Override
    public void dispose() {

    }
}
