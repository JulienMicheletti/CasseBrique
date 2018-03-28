package fr.ul.cassebrique.controls;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import fr.ul.cassebrique.model.GameState;
import fr.ul.cassebrique.views.GameScreen;

/**
 * Created by Julien on 09/03/2018.
 */

public class Listener implements InputProcessor {
    private GameScreen gs;
    public Listener(GameScreen gs){
        this.gs = gs;
    }
    @Override
    public boolean keyDown(int keycode) {
       if (keycode == Input.Keys.SPACE) {
           if (gs.getState().equals(GameState.State.Pause)) {
               System.out.println("pause");
               gs.setState(GameState.State.Running);
           } else if (gs.getState().equals(GameState.State.Running)) {
               System.out.println("run");
               gs.setState(GameState.State.Pause);
           }
       }
       else if (keycode == Input.Keys.ESCAPE){
           gs.setState(GameState.State.Quit);
       }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
