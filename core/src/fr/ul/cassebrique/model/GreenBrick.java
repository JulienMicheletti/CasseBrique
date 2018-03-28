package fr.ul.cassebrique.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import fr.ul.cassebrique.dataFactories.TextureFactory;

/**
 * Created by Julien on 02/02/2018.
 */

public class GreenBrick extends Brick{
    private Animation<TextureRegion> animation;
    private float tempAnim;
        public GreenBrick(Vector2 pos, int nbCoups, GameWorld gw) {
            super(pos, nbCoups, gw);
            tempAnim = 0;
            TextureAtlas atlasBrique = new TextureAtlas(Gdx.files.internal("images/Anim2Ca.pack"));
            Array<TextureAtlas.AtlasRegion> listeIms = atlasBrique.findRegions("Anim2Ca");
            animation = new Animation(0.1f, listeIms, Animation.PlayMode.LOOP);
        }

        public String getType(){
            return "vert";
        }

        public void draw(SpriteBatch sb) {
            tempAnim += Gdx.graphics.getDeltaTime();
            if (nbCoups == 1) {
                sb.draw(TextureFactory.getTexGreenBrickB(), pos.x, pos.y);
            }else{
                TextureRegion im = animation.getKeyFrame(tempAnim);
                sb.draw(im, pos.x, pos.y);
        }
    }
}
