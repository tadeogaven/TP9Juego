package com.example.tp9juego;

import android.util.Log;

import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCPoint;
import org.cocos2d.types.CCSize;

import java.util.ArrayList;

public class Juego {
    CCGLSurfaceView _JuegoVista;
    CCSize _Pantalla;
    ArrayList<Sprite> _Avatar = new ArrayList<>();
    int _spriteTouched = -1;


    public Juego(CCGLSurfaceView GameView) {
        Log.d("com.example.tp9juego.Juego", "com.example.tp9juego.Juego");
        _JuegoVista = GameView;
    }

    public void InicioJuego() {
        Log.d("com.example.tp9juego.Juego", "ARRANCA!");

        Director.sharedDirector().attachInView(_JuegoVista);

        _Pantalla = Director.sharedDirector().displaySize();
        Log.d("com.example.tp9juego.Juego", "Pantalla - Ancho: " + _Pantalla.getWidth() + " - Alto: " + _screen.getHeight());

        Scene scene = startScene();
        Director.sharedDirector().runWithScene(scene);
    }

    private Scene startScene() {
        Log.d("com.example.tp9juego.Juego", "Inicio");
        Scene returnScene;
        returnScene = Scene.node();

        Log.d("com.example.tp9juego.Juego", "InicioEscena");
        GameLayer aLayer = new GameLayer();
        returnScene.addChild(aLayer);

        return returnScene;
    }

    class GameLayer extends Layer {
        public GameLayer() {
            setIsTouchEnabled(true);
            place();
            place();
            while (intersectionWithSprites(_Avatar.get(0), _Avatar.get(1))) {
                CCPoint initialPos = new CCPoint();
                initialPos.x = (float) Math.random() * _Pantalla.getWidth();
                initialPos.y = (float) Math.random() * _Pantalla.getHeight();
                _Avatar.get(1).setPosition(initialPos.x, initialPos.y);
                Log.d("GameLayer", "Relocationg");
            }
        }
    }
    public void place() {
        Sprite avatar = Sprite.sprite("Avatar.png");
        CCPoint initialPos = new CCPoint();
        initialPos.x = (float) Math.random() * _Pantalla.getWidth();
        initialPos.y = (float) Math.random() * _Pantalla.getHeight();
        avatar.setPosition(initialPos.x, initialPos.y);
        _Avatar.add(avatar);

        super.addChild(avatar);
    }
}


