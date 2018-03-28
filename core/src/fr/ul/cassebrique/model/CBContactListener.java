package fr.ul.cassebrique.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import fr.ul.cassebrique.dataFactories.SoundFactory;

/**
 * Created by Julien on 21/02/2018.
 */

public class CBContactListener implements ContactListener {
    private Wall wall;
    private GameWorld gw;

    public CBContactListener(Wall wall, GameWorld gw){
        this.wall = wall;
        this.gw = gw;
    }

    public void beginContact(Contact contact){
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        Vector2 result = new Vector2();
        float prodScal;
        if (bodyB.getType() == BodyDef.BodyType.DynamicBody){
            Vector2 vitesse = bodyB.getLinearVelocity();
            Vector2 normal = contact.getWorldManifold().getNormal();
            float restitution = bodyA.getFixtureList().get(0).getRestitution();
            prodScal =-2*(vitesse.x * normal.x + vitesse.y * normal.y);
            result.x = (prodScal * normal.x + vitesse.x) * restitution;
            result.y = (prodScal * normal.y + vitesse.y) * restitution;
            if (result.y < 0 && vitesse.y > 0) {
                float pourcent;
                pourcent = 1 * result.y / 100;
                result.y = pourcent + result.y;
            }
            if (bodyA.getUserData().equals("gauche") || bodyA.getUserData().equals("droite")) {
                result.x *= 1.5f;
            } else if (bodyA.getUserData() instanceof Brick) {
                Brick b = (Brick) bodyA.getUserData();
                wall.touche(b.getPos());
            }
            if (prodScal > 0) {
                if (bodyA.getUserData().equals("mid") || bodyA.getUserData().equals("gauche") | bodyA.getUserData().equals("droite")) {
                    SoundFactory.getSoundImpact().play();
                } else {
                    SoundFactory.getSoundCollision().play();
                }
                bodyB.setLinearVelocity(result);
                }
            }
        }


    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
