package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kayakinganimal.coden.CatHop;

import scene.Hud;

/**
 * Created by david on 7/6/2016.
 */
public class MenuState extends State{

    private Texture title;
    private Hud hud;
    private boolean play;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        play = false;
        cam.setToOrtho(false, CatHop.WIDTH/2, CatHop.HEIGHT/2);
        //playButton = new Texture("playbutton.png");
        //title = new Texture("title.png");
        hud = new Hud(false,false);
        //hud.getPlayButton().setPosition(cam.viewportWidth/2 -hud.getPlayButton().getWidth()/2, cam.viewportHeight/2 - hud.getPlayButton().getHeight());


        //System.out.println("Width: "+(cam.viewportWidth/2 -hud.getPlayButton().getWidth()/2)+" Height: "+(cam.viewportHeight/2 - hud.getPlayButton().getHeight()));
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
        if(play){
            gsm.set(new PlayState(gsm));
        }
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //sb.draw(playButton, cam.viewportWidth/2 - playButton.getWidth()/2, cam.viewportHeight/2 - playButton.getHeight());
        //sb.draw(title, cam.viewportWidth/2 - title.getWidth()/2, cam.viewportHeight - 20);
        sb.end();
        hud.stage.act();
        hud.stage.draw();
    }

    @Override
    public void dispose() {

    }
}
