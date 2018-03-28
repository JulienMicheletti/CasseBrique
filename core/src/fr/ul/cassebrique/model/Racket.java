package fr.ul.cassebrique.model;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import fr.ul.cassebrique.dataFactories.TextureFactory;

/**
 * Created by Julien on 02/02/2018.
 */

public class Racket {
    private GameWorld gw;
    private Vector2 pos;
    private int length;
    private int width;
    private Body body;
    private Body bgauche;
    private Body bdroite;


    public Racket(GameWorld gw) {
        this.gw = gw;
        this.pos = new Vector2();
        this.length = TextureFactory.getTexRacket().getHeight();
        this.width = TextureFactory.getTexRacket().getWidth();
        pos.x = TextureFactory.getTexBack().getWidth() / 2 - width / 2 - TextureFactory.getTexBorder().getWidth() / 2;
        pos.y = 50;

        Vector2[] vertices = new Vector2[4];
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        body = gw.world.createBody(bdef);
        bdroite = gw.world.createBody(bdef);
        bgauche = gw.world.createBody(bdef);

        //bmilieu
        body.setTransform((pos.x + length / 2) * gw.getPIXELS_TO_METERS(),(pos.y - length / 2)* gw.getPIXELS_TO_METERS(), 0);
        FixtureDef fixture = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        fixture.shape = shape;
        fixture.density = 1;
        fixture.restitution = 1;
        fixture.friction = 0;
        vertices[0] = new Vector2(0, 0);
        vertices[1] = new Vector2(0, (TextureFactory.getTexRacket().getHeight()) * gw.getPIXELS_TO_METERS());
        vertices[2] = new Vector2((TextureFactory.getTexRacket().getWidth() - 2 * length) * gw.getPIXELS_TO_METERS(), 0);
        vertices[3] = new Vector2((TextureFactory.getTexRacket().getWidth() - 2 *  length) * gw.getPIXELS_TO_METERS(), (TextureFactory.getTexRacket().getHeight()) * gw.getPIXELS_TO_METERS());
        shape.set(vertices);
        body.createFixture(fixture);
        shape.dispose();


        //bgauche
        bgauche.setTransform(pos.x * gw.getPIXELS_TO_METERS(),pos.y * gw.getPIXELS_TO_METERS(), 0);
        FixtureDef fixtureCG = new FixtureDef();
        CircleShape circleG = new CircleShape();
        fixtureCG.shape = circleG;
        fixtureCG.density = 1;
        fixtureCG.restitution = 1;
        fixtureCG.friction = 0;
        circleG.setRadius((length/2)*gw.getPIXELS_TO_METERS());
        bgauche.createFixture(fixtureCG);
        circleG.dispose();

        //bdroite
        bdroite.setTransform((pos.x + width - length) * gw.getPIXELS_TO_METERS(), (pos.y + length)* gw.getPIXELS_TO_METERS(), 0);
        FixtureDef fixtureCD = new FixtureDef();
        CircleShape circleD = new CircleShape();
        fixtureCD.shape = circleD;
        fixtureCD.density = 1;
        fixtureCD.restitution = 1;
        fixtureCD.friction = 0;
        circleD.setRadius((length/2)*gw.getPIXELS_TO_METERS());
        bdroite.createFixture(fixtureCD);
        circleD.dispose();
        body.setUserData("mid");
        bgauche.setUserData("gauche");
        bdroite.setUserData("droite");
    }

    public void draw(SpriteBatch sb){
        body.setTransform((pos.x + length / 2) * gw.getPIXELS_TO_METERS(),(pos.y - length / 2)* gw.getPIXELS_TO_METERS(), 0);
        bdroite.setTransform((pos.x + width - length) * gw.getPIXELS_TO_METERS(), pos.y * gw.getPIXELS_TO_METERS(), 0);
        bgauche.setTransform(pos.x * gw.getPIXELS_TO_METERS(), pos.y * gw.getPIXELS_TO_METERS(), 0);
        sb.begin();
        sb.draw(TextureFactory.getTexRacket(), pos.x, pos.y);
        sb.end();
    }

    public void resetPos(){
        pos.x = TextureFactory.getTexBack().getWidth() / 2 - width / 2 - TextureFactory.getTexBorder().getWidth() / 2;
        pos.y = 50;
    }

    public void goLeft(){
        pos.x -= 10;
    }

    public void goRight(){
        pos.x += 10;
    }

    public float getPosAbs() {
        return pos.x;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
