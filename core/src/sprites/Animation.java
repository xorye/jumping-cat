package sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import states.PlayState;

/**
 * Created by david on 7/6/2016.
 */
public class Animation {
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

    public Animation(TextureRegion region, int frameCount, int totalFrames, float cycleTime){
        frames = new Array<TextureRegion>();

        //declare the width of the frame so that we know where to cut the TextureRegions for each frame
        int frameWidth = region.getRegionWidth()/totalFrames;
        System.out.println("frameWidth of cat" +frameWidth);

        for(int i = 0; i < totalFrames; i++){
            frames.add(new TextureRegion(region,i*frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        this.totalFrames = totalFrames;
        maxFrameTime = cycleTime/frameCount;
        if(PlayState.DIRECTION == 1 || PlayState.DIRECTION == 0) {
            frame = 0;
        }else{
            frame = 4;

        }
    }

    //update method to change frames
    public void update(float dt, int direction, Cat cat){
        currentFrameTime += dt;

        if(cat.getAlive()) {
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

                if (frame == totalFrames - 1 || frame < 5) {
                    frame = 4;
                }
            }
        }else{//if cat is dead
            frame = 8;
        }



    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
