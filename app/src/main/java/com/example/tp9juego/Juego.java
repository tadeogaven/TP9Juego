package com.example.tp9juego;

import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.actions.interval.IntervalAction;
import org.cocos2d.actions.interval.MoveBy;
import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.actions.interval.Sequence;
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
    Sprite _Avatar;
    Sprite _Plataforma;

    public Juego(CCGLSurfaceView vistaAUsar){
        _JuegoVista=vistaAUsar;
    }

    public void InicioJuego() {

        Log.d("InicioJuego", "ARRANCA!");
        Director.sharedDirector().attachInView(_JuegoVista);

        _Pantalla = Director.sharedDirector().displaySize();
        Log.d("InicioJuego", "Pantalla - Ancho: " + _Pantalla.getWidth() + " - Alto: " + _Pantalla.getHeight());

        Log.d("InicioJuego","Declaro e instancio la escena");
        Scene escenaAUsar;
        escenaAUsar=EscenaComienzo();

        Log.d("InicioJuego","Le digo al director que inicie la escena");
        Director.sharedDirector().runWithScene(escenaAUsar);
    }

    private Scene EscenaComienzo(){
        Log.d("EscenaComienzo","Comienza");
        Scene escenaADevolver;
        escenaADevolver=Scene.node();

        capaJuego capa = new capaJuego();
        escenaADevolver.addChild(capa);

        Log.d("EscenaComienzo","Devuelvo la escena creada");
        return escenaADevolver;
    }

    class capaJuego extends Layer{
        public capaJuego(){
            Log.d("CapaJuego","Comienza el constructor");

            Log.d("CapaJuego","Ubico al fondo en posicion inicial");
            ponerImagenFondo();
            Log.d("CapaJuego","Ubico al avatar en posicion inicial");
            ponerAvatar();
            Log.d("CapaJuego","Ubico a la plataforma en posicion inicial");
            ponerPlataforma();

        }

        void ponerImagenFondo(){
            Sprite imagenFondo;
            Log.d("Poner Imagen", "Asigno imagen grafica al Sprite del avatar");
            imagenFondo=Sprite.sprite("Fondo.jpg");

            Log.d("Poner Imagen", "Lo ubico");
            imagenFondo.setPosition(_Pantalla.getWidth()/2,_Pantalla.getHeight()/2);


            float factorAncho, factorAlto;
            factorAncho=_Pantalla.getWidth()/imagenFondo.getWidth();
            factorAlto=_Pantalla.getHeight()/imagenFondo.getHeight();
            Log.d("Poner Imagen", "Lo escalo");
            imagenFondo.runAction(ScaleBy.action(0.01f,3,3));

            Log.d("Poner Imagen", "Lo agrego a la capa");
            super.addChild(imagenFondo);

        }

        void ponerAvatar(){
            Log.d("Poner Jugador", "Asigno imagen grafica al Sprite del avatar");
            _Avatar=Sprite.sprite("Avatar.png");

            Log.d("Poner Jugador", "Comienzo a ubicar al avatar");
            CCPoint posicionInicialAvatar;
            posicionInicialAvatar=new CCPoint();
            posicionInicialAvatar.x=_Pantalla.getWidth()/2;
            posicionInicialAvatar.y=_Pantalla.getHeight();
            _Avatar.setPosition(posicionInicialAvatar.x,posicionInicialAvatar.y);


            CCPoint posicionFinalAvatar;
            posicionFinalAvatar=new CCPoint();
            posicionFinalAvatar.x=posicionInicialAvatar.x;
            posicionFinalAvatar.y=0;

             Log.d("Poner avatar","Inicio el movimiento");
            _Avatar.runAction(MoveTo.action(3,posicionFinalAvatar.x,posicionFinalAvatar.y));


            Log.d("Poner Jugador","Lo agrego a la capa");
            super.addChild(_Avatar);
        }

        void ponerPlataforma(){
            Log.d("Poner plataforma","Voy a armar el Sprite de la plataforma");
            _Plataforma=Sprite.sprite("Plataforma.png");

            Log.d("Poner plataforma","Determino posicion inical");
            CCPoint posicionInicialPlataforma;
            posicionInicialPlataforma=new CCPoint();
            posicionInicialPlataforma.x=_Pantalla.getWidth()/2;
            posicionInicialPlataforma.y=_Pantalla.getHeight()/2;

            Log.d("Poner plataforma","Ubico el Sprite");
            _Plataforma.setPosition(posicionInicialPlataforma.x,posicionInicialPlataforma.y);


            Log.d("Poner plataforma","Lo agrego a la capa");
            super.addChild(_Plataforma,7);


        }

        void Salto(){
            
        }
    }
}


