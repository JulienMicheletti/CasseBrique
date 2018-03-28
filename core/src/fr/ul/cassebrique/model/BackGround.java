package fr.ul.cassebrique.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import fr.ul.cassebrique.dataFactories.TextureFactory;

/**
 * Created by Julien on 02/02/2018.
 */

public class BackGround {
    private Body body;
    private BodyDef bdef;
    private GameWorld gw;

    public BackGround(GameWorld gw) {
        this.gw = gw;
        bdef = new BodyDef();
        body = gw.world.createBody(bdef);
        FixtureDef fixture = new FixtureDef();
        ChainShape shape = new ChainShape();
        fixture.shape = shape;
        fixture.density = 1;
        fixture.restitution = 1;
        fixture.friction = 0;

        Vector2 posBG = new Vector2(TextureFactory.getTexBorder().getHeight() - TextureFactory.getTexBall().getWidth() / 2, 0);
        Vector2 posHG = new Vector2(TextureFactory.getTexBorder().getHeight() - TextureFactory.getTexBall().getWidth() / 2, TextureFactory.getTexBack().getHeight() - TextureFactory.getTexBorder().getHeight() - TextureFactory.getTexBall().getWidth() / 2);
        Vector2 posHD = new Vector2(TextureFactory.getTexBack().getWidth() - TextureFactory.getTexBorder().getHeight()*2  - TextureFactory.getTexBall().getWidth() / 2, TextureFactory.getTexBack().getHeight() - TextureFactory.getTexBorder().getHeight() - TextureFactory.getTexBall().getWidth() / 2);
        Vector2 posBD = new Vector2(TextureFactory.getTexBack().getWidth() - TextureFactory.getTexBorder().getHeight()*2  - TextureFactory.getTexBall().getWidth() / 2, 0);
        float pts[] = {posBG.x * gw.getPIXELS_TO_METERS(), posBG.y * gw.getPIXELS_TO_METERS(), posHG.x * gw.getPIXELS_TO_METERS(), posHG.y * gw.getPIXELS_TO_METERS(), posHD.x * gw.getPIXELS_TO_METERS(), posHD.y * gw.getPIXELS_TO_METERS(), posBD.x * gw.getPIXELS_TO_METERS(), posBD.y * gw.getPIXELS_TO_METERS()};
        //float pts[] = {50, 0, 50, 630, 1050, 630, 1050, 0};
       // float pts[] = {35 * gw.getPIXELS_TO_METERS(), 0 * gw.getPIXELS_TO_METERS(), 35 * gw.getPIXELS_TO_METERS(), 640 * gw.getPIXELS_TO_METERS(), 1100 * gw.getPIXELS_TO_METERS(), 640 * gw.getPIXELS_TO_METERS(),1100 * gw.getPIXELS_TO_METERS(), 0 * gw.getPIXELS_TO_METERS()};

        shape.createChain(pts);
        body.createFixture(fixture);
        shape.dispose();
        body.setUserData("back");
    }

    public void draw(SpriteBatch sb){
        sb.begin();
        sb.draw(TextureFactory.getTexBack(), 0, 0);
        sb.end();
    }


}
