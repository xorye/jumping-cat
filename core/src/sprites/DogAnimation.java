package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import states.PlayState;

/**
 * Created by david on 7/6/2016.
 */
public class DogAnimation {
    private Array<TextureRegion> frames;
    //max time the cat stays in one frame
    private float maxFrameTime;
    //the time the cat stayed on the current frame
    private float currentFrameTime;
    //number of frames in the animation (4)
    private int frameCount;
    //total frames in image (8)
    private int totalFrames;
    //the index of the current frame in the array
    private int frame;
    private TextureRegion region, rip;

    public DogAnimation(int frameCount, int totalFrames, float cycleTime){
        region = new TextureRegion(new Texture("doganimationedit.png"));

        frames = new Array<TextureRegion>();

        //declare the width of the frame so that we know where to cut the TextureRegions for each frame
        int frameWidth = region.getRegionWidth()/totalFrames;


        for(int i = 0; i < totalFrames; i++){
            frames.add(new TextureRegion(region,i*frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        this.totalFrames = totalFrames;
        maxFrameTime = cycleTime/frameCount;
        if(PlayState.DIRECTION == 1 || PlayState.DIRECTION == 0) {
            frame = 0;
        }else{
            frame = 2;

        }

    }

    //update method to change frames
    public void update(float dt, int direction, Dog dog) {
        currentFrameTime += dt;

        if (dog.getAlive()){

                if (direction == 1) {

                    if (currentFrameTime >= maxFrameTime) {
                        frame++;
                        currentFrameTime = 0;
                    }

                    if (frame >= frameCount) {
                        frame = 0;
                    }
                } else if (direction == 2) {

                    if (currentFrameTime >= maxFrameTime) {
                        frame++;
                        currentFrameTime = 0;
                    }

                    if (frame == totalFrames - 1 || frame < 2) {
                        frame = 2;
                    }
                }

    }else{
            frame = 4;
            if(currentFrameTime >= 1){
                dog.setDeleteFromArray(true);
            }
        }



    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
