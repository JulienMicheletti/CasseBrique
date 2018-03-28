package fr.ul.cassebrique.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;

import fr.ul.cassebrique.dataFactories.TextureFactory;

/**
 * Created by Julien on 02/02/2018.
 */

public class Ball {
    private static int rayon = 12;
    private GameWorld gw;
    private BodyDef bdef;
    private Body body;
    private FixtureDef fixtureDef;
    private CircleShape circle;
    private TextureAtlas atlasBoule;
    private Vector2 vitesse;
    private int numIm;
    private long tEcoule;
    private Array<Sprite> imsBoule;
    private ModelInstance modelInstance;
    private Quaternion quaternion;
    private Environment environment;
    private ModelBatch modelBatch;


    public Ball(GameWorld gw, Vector2 pos) {
        //BodyDef param
        bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.bullet = true;
        bdef.fixedRotation = false;
        //Body param
        Vector2 posMeter = new Vector2(pos.x * gw.getPIXELS_TO_METERS(), pos.y * gw.getPIXELS_TO_METERS());
        body = gw.world.createBody(bdef);
        //FictureDef
        circle = new CircleShape();
        circle.setRadius(rayon * gw.getPIXELS_TO_METERS());
        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 1;
        fixtureDef.restitution = 1;
        fixtureDef.friction = 0;
        body.createFixture(fixtureDef);
        body.setTransform(posMeter, 0);
        circle.dispose();
       atlasBoule = new TextureAtlas(Gdx.files.internal("images/Boule.pack"));
        imsBoule = atlasBoule.createSprites("boule");
        this.gw = gw;
        tEcoule = System.currentTimeMillis();

        //Bille3D
        ModelBuilder modelBuilder = new ModelBuilder();
        Model model = modelBuilder.createSphere(2, 2, 2, 40, 40,
                                                new Material(TextureAttribute.createDiffuse(TextureFactory.getTexBadLogic())),
                                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal |
                                                VertexAttributes.Usage.TextureCoordinates);
        modelInstance = new ModelInstance(model);
        environment = new Environment();
        ColorAttribute ambiance = new ColorAttribute(ColorAttribute.AmbientLight, 0.5f, 0.5f, 0.5f, 1f);
       ColorAttribute speculaire =  new ColorAttribute(ColorAttribute.Specular, 0.0f, 0.7f, 0.0f, 1f);
        environment.set(ambiance, speculaire);
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, 1f, -0.2f));
        modelBatch = new ModelBatch();
    }

    public void calculerIm(){
        float dist;
        float rayon = Ball.rayon;
        dist = body.getLinearVelocity().len() * tEcoule;
        float angleRot = dist/rayon;
        double imsPar = angleRot * imsBoule.size / 2 * Math.PI;
        numIm = (int) (numIm + imsPar) % imsBoule.size;

    }

    public Vector2 getPos(){
        return body.getPosition();
    }

    public void setPos(int x, int y){
        Vector2 posMeter = new Vector2(x * gw.getPIXELS_TO_METERS(), y * gw.getPIXELS_TO_METERS());
        body.setTransform(posMeter.x, posMeter.y, 0);
    }


    public void setSpeed(Vector2 velocity){
        velocity.x *= gw.getPIXELS_TO_METERS();
        velocity.y *= gw.getPIXELS_TO_METERS();
        body.setLinearVelocity(velocity);
    }

    public void draw(SpriteBatch sb){
       sb.begin();
        long stopTime = System.currentTimeMillis();
        this.tEcoule = stopTime - this.tEcoule;
        Vector2 pos = body.getPosition();
        pos.x = pos.x * gw.getMETERS_TO_PIXELS();
        pos.y = pos.y * gw.getMETERS_TO_PIXELS();
        quaternion = new Quaternion();
        Quaternion rotLoc = new Quaternion();
        rotLoc.set(new Vector3(body.getLinearVelocity().x, -body.getLinearVelocity().y, 0).nor(), body.getLinearVelocity().len() * tEcoule / rayon * gw.getPIXELS_TO_METERS());
        calculerIm();
        quaternion.mulLeft(rotLoc);
        modelInstance.transform.set(new Vector3(pos.x, pos.y, 1), quaternion);
        modelInstance.transform.scale(rayon, rayon, rayon);
       /* modelBatch.begin(gw.getCamera());
        modelBatch.render(modelInstance, environment);
        modelBatch.end();*/
        tEcoule = System.currentTimeMillis();
        Sprite sp = imsBoule.get(numIm);
        sp.setOriginCenter();
        sp.setRotation(2);
        sp.setBounds(pos.x - rayon, pos.y - rayon, 2*rayon, 2*rayon);
        sp.setPosition(pos.x, pos.y);
      //  sb.draw(TextureFactory.getTexBall(), pos.x, pos.y);
        sp.draw(sb);
       sb.end();
    }

    public boolean isGone(){
       Vector2 pos = body.getPosition();
        return pos.y < 0 - TextureFactory.getTexBall().getHeight() * 2 * gw.getPIXELS_TO_METERS();
    }
}
