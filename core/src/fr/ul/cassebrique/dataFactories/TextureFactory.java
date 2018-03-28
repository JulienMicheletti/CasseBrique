package fr.ul.cassebrique.dataFactories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Julien on 26/01/2018.
 */

public class TextureFactory {
    private static final Texture texGreenBrickA = new Texture(Gdx.files.internal("images/Brique2Ca.png"));
    private static final Texture texBlueBrick = new Texture(Gdx.files.internal("images/Brique1C.png"));
    private static final Texture texGreenBrickB = new Texture(Gdx.files.internal("images/Brique2Cb.png"));
    private static final Texture texBack = new Texture(Gdx.files.internal("images/Fond.png"));
    private static final Texture texBall = new Texture(Gdx.files.internal("images/Bille.png"));
    private static final Texture texBorder = new Texture(Gdx.files.internal("images/Contour.png"));
    private static final Texture texRacket = new Texture(Gdx.files.internal("images/Barre.png"));
    private static final Texture texLossBall = new Texture(Gdx.files.internal("images/PerteBalle.bmp"));
    private static final Texture texGameOver = new Texture(Gdx.files.internal("images/Perte.bmp"));
    private static final Texture texWin = new Texture(Gdx.files.internal("images/Bravo.bmp"));
    private static final Texture texBadLogic = new Texture(Gdx.files.internal("images/badlogic.jpg"));
    public static Texture getTexBadLogic() {
        return texBadLogic;
    }

    public static Texture getTexLossBall() {
        return texLossBall;
    }

    public static Texture getTexGameOver() {
        return texGameOver;
    }

    public static Texture getTexWin() {
        return texWin;
    }

    public static Texture getTexBlueBrick() {
        return texBlueBrick;
    }

    public static Texture getTexBack() {
        return texBack;
    }

    public static Texture getTexBall() {
        return texBall;
    }

    public static Texture getTexBorder() {
        return texBorder;
    }

    public static Texture getTexRacket() {
        return texRacket;
    }



    public static Texture getTexGreenBrickA(){
        return texGreenBrickA;
    }

    public static Texture getTexGreenBrickB(){
        return texGreenBrickB;
    }

    public static Texture getTextBlueBrick(){
        return texBlueBrick;
    }
}
