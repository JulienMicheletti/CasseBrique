package fr.ul.cassebrique.model;

import java.util.ArrayList;

import fr.ul.cassebrique.dataFactories.TextureFactory;
import fr.ul.cassebrique.views.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Julien on 26/01/2018.
 */

public class GameWorld {
    private GameScreen gs;
    private BackGround backGround;
    private Racket racket;
    private Wall wall;
    private ArrayList<Ball> bille;
    private Ball billeAct;
    protected World world;
    private final float METERS_TO_PIXELS = 250;
    private final float PIXELS_TO_METERS = 1/METERS_TO_PIXELS;
    private boolean clean;

    public GameWorld(GameScreen gs) {
        clean = false;
        world = new World(new Vector2(0, 0), true);
        this.gs = gs;
        this.racket = new Racket(this);
        wall = new Wall(this, false);
        world.setContactListener(new CBContactListener(wall, this));
        backGround = new BackGround(this);
        positionBille();
    }

    public void positionBille(){
        this.bille = new ArrayList<Ball>();
        Vector2 posBille1 = new Vector2();
        Vector2 posBille2 = new Vector2();
        Vector2 posBille3 = new Vector2();

        posBille1.x = TextureFactory.getTexBack().getWidth() / 2 - TextureFactory.getTexBall().getWidth() / 2 - TextureFactory.getTexBorder().getWidth() / 2;
        posBille1.y = 55 + racket.getLength();
        bille.add(new Ball(this, posBille1));
        bille.get(0).setSpeed(new Vector2(50,-300));
        posBille2.x = TextureFactory.getTexBack().getWidth() - TextureFactory.getTexBorder().getWidth() / 2 - TextureFactory.getTexBall().getWidth() / 2;
        posBille2.y = TextureFactory.getTexBorder().getWidth() / 2 - TextureFactory.getTexBall().getWidth() / 2;
        bille.add(new Ball(this, posBille2));
        posBille3.x = TextureFactory.getTexBack().getWidth() - TextureFactory.getTexBorder().getWidth() / 2 - TextureFactory.getTexBall().getWidth() / 2;
        posBille3.y = TextureFactory.getTexBorder().getWidth() / 2 - TextureFactory.getTexBall().getWidth() / 2 + TextureFactory.getTexBorder().getWidth();
        bille.add(new Ball(this, posBille3));
        billeAct = bille.get(0);
    }

    public World getWorld(){
        return world;
    }

    public float getMETERS_TO_PIXELS() {
        return METERS_TO_PIXELS;
    }

    public float getPIXELS_TO_METERS() {
        return PIXELS_TO_METERS;
    }

    public void update(){
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        if (wall.toucheSize() > 0){
            wall.maj();
        }
        if (clean == true){
            wall.clean();
        }

    }

    public boolean endBall(){
        if (bille.size() == 1 && billeAct.isGone()){
            return true;
        }
        return false;
    }

    public boolean endWall(){
        if (wall.isEmpty()){
            return true;
        }
        return false;
    }

    public boolean isGone(){
        if (billeAct.isGone() && bille.size() > 1){
            return true;
        }
        return false;
    }


    public void reboot(GameState.State etat){
        if (etat.equals(GameState.State.BallLoss)){
            bille.remove(billeAct);
            if (bille.size() > 0){
                billeAct = bille.get(bille.size() - 1);
                billeAct.setPos(TextureFactory.getTexBack().getWidth() / 2 - TextureFactory.getTexBall().getWidth() / 2 - TextureFactory.getTexBorder().getWidth() / 2, 55  + racket.getLength());
                billeAct.setSpeed(new Vector2(50,-300));
                getRacket().resetPos();
            }
        }else if (etat.equals(GameState.State.Won)){
            wall = new Wall(this, true);
            world.setContactListener(new CBContactListener(wall, this));
            billeAct.setPos(TextureFactory.getTexBack().getWidth() / 2 - TextureFactory.getTexBall().getWidth() / 2 - TextureFactory.getTexBorder().getWidth() / 2, 55 + racket.getLength());
            billeAct.setSpeed(new Vector2(50,-300));
            Vector2 posBille = new Vector2();
            Ball lastBille =  bille.get(bille.size() - 1);
            if (bille.size() - 1 == 0){
                posBille.x = TextureFactory.getTexBack().getWidth() - TextureFactory.getTexBorder().getWidth() / 2 - TextureFactory.getTexBall().getWidth() / 2;
                posBille.y = TextureFactory.getTexBorder().getWidth() / 2 - TextureFactory.getTexBall().getWidth() / 2;
            }else if (bille.size() - 1 == 1){
                posBille.x = TextureFactory.getTexBack().getWidth() - TextureFactory.getTexBorder().getWidth() / 2 - TextureFactory.getTexBall().getWidth() / 2;
                posBille.y = TextureFactory.getTexBorder().getWidth() / 2 - TextureFactory.getTexBall().getWidth() / 2 + TextureFactory.getTexBorder().getWidth();
            }else{
                posBille.x = TextureFactory.getTexBack().getWidth() - TextureFactory.getTexBorder().getWidth() / 2 - TextureFactory.getTexBall().getWidth() / 2;
                posBille.y = lastBille.getPos().y * getMETERS_TO_PIXELS() + 50;
            }
            bille.add(new Ball(this, posBille));
            getRacket().resetPos();
        }else if (etat.equals(GameState.State.GameOver)){
            wall.clean();
            wall = new Wall(this, false);
            world.setContactListener(new CBContactListener(wall, this));
            getRacket().resetPos();
            positionBille();
        }
    }

    public Vector2 getPosBall(){
        return billeAct.getPos();
    }

    public void draw(SpriteBatch sb){
        backGround.draw(sb);
        wall.draw(sb);
        racket.draw(sb);
        for (int i = 0; i < bille.size(); i++){
            bille.get(i).draw(sb);
        }
    }


    public OrthographicCamera getCamera(){
        return gs.getCamera();
    }

    public Racket getRacket() {
        return racket;
    }


}
