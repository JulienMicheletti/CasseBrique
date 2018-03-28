package fr.ul.cassebrique.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.TextComponent;

import fr.ul.cassebrique.dataFactories.TextureFactory;

/**
 * Created by Julien on 02/02/2018.
 */

public abstract class Brick {
    protected Vector2 pos;
    protected int nbCoups;
    protected Body body;
    protected BodyDef bdef;

    public Brick(Vector2 pos, int nbCoups, GameWorld gw){
        this.pos = pos;
        this.nbCoups = nbCoups;
        Vector2[] vertices = new Vector2[4];
        bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;

        body = gw.world.createBody(bdef);
        FixtureDef fixture = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        fixture.shape = shape;
        fixture.density = 1;
        fixture.restitution = 1;
        fixture.friction = 0;

        vertices[0] = new Vector2((pos.x - TextureFactory.getTexBall().getWidth() / 2)* gw.getPIXELS_TO_METERS(), (pos.y - TextureFactory.getTexBall().getWidth() / 2) * gw.getPIXELS_TO_METERS());
        vertices[1] = new Vector2((pos.x - TextureFactory.getTexBall().getWidth() / 2)* gw.getPIXELS_TO_METERS(), (pos.y + TextureFactory.getTexBlueBrick().getHeight() - TextureFactory.getTexBall().getWidth() / 2) * gw.getPIXELS_TO_METERS());
        vertices[2] = new Vector2((pos.x + TextureFactory.getTextBlueBrick().getWidth() - TextureFactory.getTexBall().getWidth() / 2) * gw.getPIXELS_TO_METERS(), (pos.y - TextureFactory.getTexBall().getWidth() / 2)* gw.getPIXELS_TO_METERS());
        vertices[3] = new Vector2((pos.x + TextureFactory.getTextBlueBrick().getWidth() - TextureFactory.getTexBall().getWidth() / 2) * gw.getPIXELS_TO_METERS(), (pos.y + TextureFactory.getTexBlueBrick().getHeight() - TextureFactory.getTexBall().getWidth() / 2) * gw.getPIXELS_TO_METERS());
        shape.set(vertices);
        body.createFixture(fixture);
       shape.dispose();
       body.setUserData(this);
    }

    public Body getBody(){
        return body;
    }

    public abstract String getType();

    public int getNbCoups(){
        return nbCoups;
    }

    public void setNbCoups(){
        nbCoups += 1;
    }

    public Vector2 getPos(){
        return pos;
    }

    public abstract void draw(SpriteBatch sb);
}
