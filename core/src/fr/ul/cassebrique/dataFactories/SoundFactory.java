package fr.ul.cassebrique.dataFactories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Julien on 09/03/2018.
 */

public class SoundFactory {
    private static final Sound soundCollision = Gdx.audio.newSound(Gdx.files.internal("sounds/collision.wav"));
    private static final Sound soundImpact = Gdx.audio.newSound(Gdx.files.internal("sounds/impact.mp3"));
    private static final Sound soundPerte = Gdx.audio.newSound(Gdx.files.internal("sounds/perte.mp3"));
    private static final Sound soundPerteBalle = Gdx.audio.newSound(Gdx.files.internal("sounds/perteBalle.wav"));
    private static final Sound soundVictoire = Gdx.audio.newSound(Gdx.files.internal("sounds/victoire.mp3"));

    public void playCollision(int vol){
        soundCollision.play(vol);
    }
    public void playImpact(int vol){
        soundImpact.play(vol);
    }

    public static Sound getSoundCollision() {
        return soundCollision;
    }

    public static Sound getSoundImpact() {
        return soundImpact;
    }

    public static Sound getSoundPerte() {
        return soundPerte;
    }

    public static Sound getSoundPerteBalle() {
        return soundPerteBalle;
    }

    public static Sound getSoundVictoire() {
        return soundVictoire;
    }

    public void playPerte(int vol){
        soundPerte.play(vol);
    }
    public void playPerteBalle(int vol){
        soundPerteBalle.play(vol);
    }
    public void playVictoire(int vol){
        soundVictoire.play(vol);
    }
}
