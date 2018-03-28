package fr.ul.cassebrique.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import fr.ul.cassebrique.dataFactories.TextureFactory;

/**
 * Created by Julien on 02/02/2018.
 */

public class BlueBrick extends Brick{
    public BlueBrick(Vector2 pos, int nbCoups, GameWorld gw) {
        super(pos, nbCoups, gw);
    }

    public String getType(){
        return "bleue";
    }

    public void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getTexBlueBrick(), pos.x, pos.y);
    }
}
