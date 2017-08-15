package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.kayakinganimal.coden.CatHop;

import states.PlayState;

/**
 * Created by david on 7/6/2016.
 */
public class Cat {

    //the cats speed
    private static final int CAT_MOVEMENT = 120;
    private static final int SPIKE_LENGTH = 8;
    private boolean alive;
    //the cats position
    private Vector3 position;
    private int combo;

    private Animation catAnimation;
    private Texture catTexture;
    public static int yinc;
    private Rectangle bounds;
    private boolean jumping, recoil;
    private Sound dogDead;


    //The starting position for the cat
    public Cat(int x, int y){
        dogDead = Gdx.audio.newSound(Gdx.files.internal("dogdead.ogg"));
        alive = true;

        yinc = 15;
        position = new Vector3(x, y, 0);
        catTexture = new Texture("catanimation.png");
        //texture region number of frames, time per each frame
        catAnimation = new Animation(new TextureRegion(catTexture), 4, 9, 0.5f);
        bounds = new Rectangle(getPosition().x, getPosition().y,getCat().getRegionWidth(),getCat().getRegionHeight());
    }
    public void update(float dt, int ground){
        catAnimation.update(dt, PlayState.DIRECTION, this);

        if(alive) {

            recoilCheck();
            if (jumping || recoil) {
                position.add(0, yinc, 0);
                yinc -= 1;
            }

            if (getPosition().y < ground) {
                position.y = ground;
                combo = 0;
                if (jumping) {
                    jumping = false;
                    yinc = 15;
                }
            }


            if (PlayState.DIRECTION == 1) {
                position.add(CAT_MOVEMENT * dt, 0, 0);
            } else if (PlayState.DIRECTION == 2) {
                position.add(-(CAT_MOVEMENT * dt), 0, 0);
            }
            if (position.x >= CatHop.WIDTH / 2 - catAnimation.getFrame().getRegionWidth() - SPIKE_LENGTH) {
                position.x = CatHop.WIDTH / 2 - catAnimation.getFrame().getRegionWidth() - SPIKE_LENGTH;
            }
            if (position.x <= SPIKE_LENGTH) {
                position.x = SPIKE_LENGTH;
            }
            bounds.setPosition(position.x, position.y);
            //taking care of platform positioning
        }

    }

    public TextureRegion getCat(){
        return catAnimation.getFrame();
    }
    public Vector3 getPosition(){
        return position;
    }
    public void dispose(){
        catTexture.dispose();
    }
    public Rectangle getBounds(){
        return bounds;
    }

    public void jump() {
        yinc = 15;

    }
    public void setJumping(boolean j){
        jumping = j;
    }

    public boolean getJumping(){
        return jumping;
    }
    public void setRecoil(boolean r){
        recoil = r;
    }
    public boolean getAlive(){return alive;}
    public void setAlive(boolean a){alive = a;}
    public boolean getRecoil(){
        return recoil;
    }
    public void recoilCheck(){
        if(recoil){

            combo ++;

            dogDead.play(0.5f);
            yinc = 17;
            recoil = false;
        }
    }


    public int getCombo() {
        return combo;
    }
}
