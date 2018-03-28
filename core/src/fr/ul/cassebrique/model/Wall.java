package fr.ul.cassebrique.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Julien on 26/01/2018.
 */

public class Wall {
    private Brick wall[][];
    private static Brick wallInit[][];
    private ArrayList<Brick> touche;
    private GameWorld gw;
    private int compteur;

    public Wall(GameWorld gw, boolean aleat){
        this.gw = gw;
        compteur = 0;
        touche = new ArrayList<Brick>();
        wall = new Brick[5][10];
        if (!aleat) {
             wallInit = new Brick[][]{{new BlueBrick(new Vector2(50, 480), gw), new GreenBrick(new Vector2(150, 480), 0, gw), //Mur 1
                    new BlueBrick(new Vector2(250, 480), gw), new GreenBrick(new Vector2(350, 480), 1, gw),
                    new BlueBrick(new Vector2(450, 480), gw), new BlueBrick(new Vector2(550, 480), gw),
                    new GreenBrick(new Vector2(650, 480), 1, gw), new BlueBrick(new Vector2(750, 480), gw),
                    new GreenBrick(new Vector2(850, 480), 0, gw), new BlueBrick(new Vector2(950, 480), gw)},{//Mur 2
                    new BlueBrick(new Vector2(50, 435), gw), new BlueBrick(new Vector2(150, 435), gw),
                    new GreenBrick(new Vector2(250, 435), 0, gw), new BlueBrick(new Vector2(350, 435), gw),
                    new GreenBrick(new Vector2(450, 435), 1, gw), new GreenBrick(new Vector2(550, 435), 1, gw),
                    new BlueBrick(new Vector2(650, 435), gw), new GreenBrick(new Vector2(750, 435), 0, gw),
                    new BlueBrick(new Vector2(850, 435), gw), new BlueBrick(new Vector2(950, 435), gw)}, { // Mur 3
                    new BlueBrick(new Vector2(50, 390), gw), new BlueBrick(new Vector2(150, 390), gw),
                    new BlueBrick(new Vector2(250, 390), gw), new GreenBrick(new Vector2(350, 390), 0, gw),
                    new BlueBrick(new Vector2(450, 390), gw), new BlueBrick(new Vector2(550, 390), gw),
                    new GreenBrick(new Vector2(650, 390), 0, gw), new BlueBrick(new Vector2(750, 390), gw),
                    new BlueBrick(new Vector2(850, 390), gw), new BlueBrick(new Vector2(950, 390), gw)}, { //Mur 4
                    new BlueBrick(new Vector2(50, 345), gw), new BlueBrick(new Vector2(150, 345), gw),
                    new BlueBrick(new Vector2(250, 345), gw), new BlueBrick(new Vector2(350, 345), gw),
                    new GreenBrick(new Vector2(450, 345), 1, gw), new GreenBrick(new Vector2(550, 345), 1, gw),
                    new BlueBrick(new Vector2(650, 345), gw), new BlueBrick(new Vector2(750, 345), gw),
                    new BlueBrick(new Vector2(850, 345), gw), new BlueBrick(new Vector2(950, 345), gw)}, { //Mur 5
                    null, new BlueBrick(new Vector2(150, 300), gw), null, null, new GreenBrick(new Vector2(450, 300), 0, gw),
                    new GreenBrick(new Vector2(550, 300), 0, gw), null, null, new BlueBrick(new Vector2(850, 300), gw), null}};
            setBricks(false);
        }else{
            setBricks(true);
        }
    }

    public void setBricks(boolean b) {
        if (!b) {
            wall = wallInit;
        } else if (b) {
            int posX = 50;
            int posY = 480;
            for (int i = 0; i < wall.length; i++) {
                for (int j = 0; j < wall[i].length; j++) {
                    double val = Math.random();
                    if (val < 0.1f) {
                        wall[i][j] = null;
                    } else if (val < 0.5f) {
                        wall[i][j] = new BlueBrick(new Vector2(posX, posY), gw);
                    } else if (val < 0.9f) {
                        wall[i][j] = new GreenBrick(new Vector2(posX, posY), 1, gw);
                    } else if (val <= 1f) {
                        wall[i][j] = new GreenBrick(new Vector2(posX, posY), 0, gw);
                    }
                    posX += 100;
                }
                posX = 50;
                posY -= 45;
            }
        }
        for (int i = 0; i < wall.length; i++) {
            for (int j = 0; j < wall[0].length; j++) {
                if (wall[i][j] != null) {
                    compteur++;
                }
            }
        }
    }

    public void touche(Vector2 pos){
        for (int i = 0; i < wall.length; i++){
            for (int j = 0; j < wall[0].length; j++){
               if (wall[i][j] != null && wall[i][j].getPos() == pos){
                    touche.add(wall[i][j]);

                }
            }
        }
    }

    public int toucheSize(){
        return touche.size();
    }

    public void clean() {
        for (int i = 0; i < wall.length; i++) {
            for (int j = 0; j < wall[0].length; j++) {
                if (wall[i][j] != null){
                    Brick b = wall[i][j];
                    b.getBody().setUserData(null);
                    b.getBody().setActive(false);
                    gw.getWorld().destroyBody(b.getBody());
                    wall[i][j] = null;
                }
            }
        }
    }

    public void maj() {
        for (int i = 0; i < wall.length; i++) {
            for (int j = 0; j < wall[0].length; j++) {
                for (int k = 0; k < touche.size(); k++) {
                    Brick br = wall[i][j];
                    if (wall[i][j] != null && wall[i][j].getPos() == touche.get(k).getPos()) {
                        if (br.getType().equals("vert") && br.getNbCoups() == 1 || br.getType().equals("bleue")) {
                            Brick b = touche.get(k);
                            b.getBody().setUserData(null);
                            b.getBody().setActive(false);
                           gw.getWorld().destroyBody(b.getBody());
                            wall[i][j] = null;
                            touche.remove(k);
                            compteur--;
                        }else{
                            br.setNbCoups();
                            touche.remove(k);
                        }
                    }
                }
            }
        }
    }

    public boolean isEmpty(){
        return compteur == 0;
    }

    public void draw(SpriteBatch sb){
        sb.begin();
        for (int i = wall.length - 1; i >= 0; i--){
            for (int j = wall[0].length - 1; j >= 0; j--){
                if (wall[i][j] != null){
                    wall[i][j].draw(sb);
                }
            }
        }
        sb.end();
    }
}
