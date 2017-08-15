package sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.kayakinganimal.coden.CatHop;

import java.util.Random;

/**
 * Created by david on 7/6/2016.
 */
public class Dog{

    //the cats speed
    private static final int DOG_MOVEMENT_MINIMUM = 80;
    private static final int DOG_MOVEMENT_MAXIMUM = 200;
    private static final int SPIKE_LENGTH = 8;
    private int dogMovement;
    private boolean passedWall;

    //the cats position
    private Vector3 position;

    private DogAnimation dogAnimation;
    private Random rand;
    private int direction; //1 is right, 2 is left
    private Rectangle bounds;
    private boolean alive, deleteFromArray, scoreAdd;


    public boolean getScoreAdd() {
        return scoreAdd;
    }



    //The starting position for the cat
    public Dog(int y, boolean starting){
        alive = true;
        deleteFromArray = false;
        rand = new Random();
        passedWall = false;
        scoreAdd = false;
/*        leftWall = new Rectangle(0,0,1,1);
        rightWall = new Rectangle(399,0,1,1);*/




        //texture region number of frames, time per each frame
        dogAnimation = new DogAnimation(2, 5, 0.5f);

        if(starting){
            direction = 2;
            position = new Vector3(rand.nextInt((CatHop.WIDTH/2)+100- CatHop.WIDTH/2+1)+ CatHop.WIDTH/2, y, 0);
        }else{

            direction = rand.nextInt(2) + 1;
            if(direction == 2){
                position = new Vector3(rand.nextInt((CatHop.WIDTH/2)+100- CatHop.WIDTH/2+1)+ CatHop.WIDTH/2, y, 0);
            }else if(direction == 1){
                position = new Vector3(rand.nextInt(101)-100, y, 0);
            }
        }
        //direction = rand.nextInt(2) + 1;
        //position = new Vector3(rand.nextInt(CatHop.WIDTH/2 - dogAnimation.getFrame().getRegionWidth()), y, 0);
        dogMovement = rand.nextInt((DOG_MOVEMENT_MAXIMUM - DOG_MOVEMENT_MINIMUM)+1) + DOG_MOVEMENT_MINIMUM;
        bounds = new Rectangle(getPosition().x, getPosition().y, getDog().getRegionWidth(), getDog().getRegionHeight());

    }
    public void update(float dt, boolean catAlive){

        dogAnimation.update(dt, direction, this);


        if(alive && catAlive) {


            if (direction == 1) {
                position.add(dogMovement * dt, 0, 0);
            } else if (direction == 2) {
                position.add(-(dogMovement * dt), 0, 0);
            }


            if (position.x >= CatHop.WIDTH / 2 - dogAnimation.getFrame().getRegionWidth() - SPIKE_LENGTH) {
                direction = 2;
            }
            if (position.x <= SPIKE_LENGTH) {
                direction = 1;
            }

            bounds.setPosition(position.x, position.y);
        }
    }

    public boolean collides(Rectangle player, Cat cat){
        if(scoreAdd){
            scoreAdd = false;
        }
        if(alive) {
            if (player.overlaps(bounds) && cat.getPosition().y >= position.y + getDog().getRegionHeight()+ - 7) {
                //dog got jumped
                cat.setRecoil(true);
                alive = false;
                scoreAdd = true;

                return false;
            } else {
                return player.overlaps(bounds);

            }
        }
        return false;
    }

    public TextureRegion getDog(){
        return dogAnimation.getFrame();
    }
    public Vector3 getPosition(){
        return position;
    }
    public boolean getAlive(){return alive;}
    public boolean getDeleteFromArray(){return deleteFromArray;}
    public void setDeleteFromArray(boolean d){deleteFromArray = d;}



}
