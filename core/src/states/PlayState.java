package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.kayakinganimal.coden.CatHop;

import scene.Hud;
import sprites.Cat;
import sprites.Dog;

/**
 * Created by david on 7/6/2016.
 */
public class PlayState extends State{
    private static final int SCORE_INCREASE = 30;
    private Cat cat;
    public static int DIRECTION = 0;
    private float yTilt;
    private Array<Dog> dogs;
    private boolean createStartingDogs, scoreSent;
    private int numberOfDogs = 10;
    private Texture ground ,spikeLeft, spikeRight;
    private int groundYPos;
    private Hud hud;
    private float timePassed;




    protected PlayState(GameStateManager gsm) {
        super(gsm);
        CatHop.currentScore = 0;
        hud = new Hud(true, false);
        createStartingDogs = true;
        dogs = new Array<Dog>();
        cam.setToOrtho(false, CatHop.WIDTH/2, CatHop.HEIGHT/2);

        spikeLeft = new Texture("spikeleft.png");
        spikeRight = new Texture("spikeright.png");
        ground = new Texture("ground.png");
        groundYPos = ground.getHeight();

        yTilt = 0;
        cat = new Cat(40,0);

        for(int i = 0; i<numberOfDogs; i++){

            dogs.add(new Dog(groundYPos, createStartingDogs));
        }
        //after creating starting dogs, stop
        createStartingDogs = false;
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if(!cat.getJumping()) {
                cat.setJumping(true);
                cat.jump();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        cat.update(dt, groundYPos);
        hud.update(dt, cat.getCombo(), cat.getAlive());


        yTilt = Gdx.input.getAccelerometerY();

        if(yTilt > 0.3){
            DIRECTION = 1;
        }else if(yTilt < -0.3){
            DIRECTION = 2;
        }

        for(int i = 0; i<dogs.size; i++){
            dogs.get(i).update(dt, cat.getAlive());

            if(dogs.get(i).collides(cat.getBounds(), cat)){
                cat.setAlive(false);

                //cat is dead
            }



            if(!dogs.get(i).getAlive()){ //dog is dead

                if(dogs.get(i).getScoreAdd()){
                    hud.scoreUpdate((cat.getCombo()+1)*SCORE_INCREASE);
                }

                if(dogs.get(i).getDeleteFromArray()) {

                    dogs.removeIndex(i);


                }


            }
        }

        if(!cat.getAlive()){//if cat is dead, wait some seconds and go to game over screen
            timePassed +=dt;

            if(timePassed >= 2){
                CatHop.gameOver = true;
                CatHop.adCount++;

                timePassed = 0;
                gsm.set(new GameOverState(gsm, hud.getScore()));

            }
        }

        //if our cat gets spooked by the left spike
        if(cat.getPosition().x <= spikeLeft.getWidth()){
            cat.setAlive(false);
        }else if(cat.getPosition().x+cat.getCat().getRegionWidth() >= CatHop.WIDTH/2-spikeRight.getWidth()){
            cat.setAlive(false);
        }

        //if our precious dog numbers are decreasing
        if(dogs.size < 10){
            for(int i = 0; i<numberOfDogs-dogs.size; i++){
                dogs.add(new Dog(groundYPos, createStartingDogs));
            }
        }


        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();


        for(Dog dog: dogs) {
            sb.draw(dog.getDog(), dog.getPosition().x, dog.getPosition().y);
        }
        sb.draw(cat.getCat(), cat.getPosition().x, cat.getPosition().y);
        sb.draw(ground,0,0);
        sb.draw(spikeLeft,0,groundYPos);
        sb.draw(spikeRight,(CatHop.WIDTH/2)-spikeRight.getWidth(),groundYPos);
        sb.end();
        hud.stage.act();
        hud.stage.draw();

    }

    @Override
    public void dispose() {

    }
}
