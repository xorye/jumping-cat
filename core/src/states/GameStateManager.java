package states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;


/**
 * Created by david on 7/6/2016.
 */
public class GameStateManager {
    //create stack of states to maange
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }
    //setting a state instantly after popping
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }
    //render top state
    public void render (SpriteBatch sb){
        states.peek().render(sb);
    }


}
