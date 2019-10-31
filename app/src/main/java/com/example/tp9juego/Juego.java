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
    Sprite _FlechaIz;
    Sprite _FlechaDe;
    ArrayList _listaPlataforma;

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
        public capaJuego() {
            Log.d("CapaJuego", "Comienza el constructor");

            Log.d("CapaJuego", "Ubico al fondo en posicion inicial");
            ponerImagenFondo();
            Log.d("CapaJuego", "Ubico al avatar en posicion inicial");
            ponerAvatar();
            Log.d("CapaJuego", "Ubico a la plataforma en posicion inicial");
            ponerPlataforma();
            Log.d("CapaJuego", "Ubico a la flecha izquierda en posicion inicial");
            ponerFlechaIzquierda();
            ;
            Log.d("CapaJuego", "Ubico a la flecha derecha en posicion inicial");
            ponerFlechaDerecha();
            ;

            Log.d("CapaJuego","Pongo una plataforma");
            _listaPlataforma = new ArrayList();
            super.schedule("ponerPlataforma",1.0f);
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

        void ponerFlechaIzquierda(){
            Log.d("PonerFlechaIz","Voy a armar el Sprite de la flecha izquierda");
            _FlechaIz=Sprite.sprite("flechaizquierda.png");

            Log.d("PonerFlechaIz","Determino posicion inical");
            CCPoint posicionInicialFlechaIzq;
            posicionInicialFlechaIzq=new CCPoint();
            posicionInicialFlechaIzq.x=_Pantalla.getWidth()/5;
            posicionInicialFlechaIzq.y=_Pantalla.getHeight()/7;

            Log.d("PonerFlechaIz","Ubico el Sprite");
            _FlechaIz.setPosition(posicionInicialFlechaIzq.x,posicionInicialFlechaIzq.y);


            Log.d("PonerFlechaIz","Lo agrego a la capa");
            super.addChild(_FlechaIz);
        }
        void ponerFlechaDerecha(){
            Log.d("PonerFlechaDer","Voy a armar el Sprite de la flecha derecha");
            _FlechaDe=Sprite.sprite("flechaderecha.png");

            Log.d("PonerFlechaDer","Determino posicion inical");
            CCPoint posicionInicialFlechaDer;
            posicionInicialFlechaDer=new CCPoint();
            posicionInicialFlechaDer.x=_Pantalla.getWidth()/1.2f;
            posicionInicialFlechaDer.y=_Pantalla.getHeight()/7;

            Log.d("PonerFlechaDer","Ubico el Sprite");
            _FlechaDe.setPosition(posicionInicialFlechaDer.x,posicionInicialFlechaDer.y);


            Log.d("PonerFlechaDer","Lo agrego a la capa");
            super.addChild(_FlechaDe);
        }

        public boolean IntereseccionEntreSprites(Sprite Sprite1, Sprite Sprite2) {
            Boolean HayInterseccion = false;

            Float Sp1Arriba, Sp1Abajo, Sp1Derecha, Sp1Izquierda, Sp2Arriba, Sp2Abajo, Sp2Derecha, Sp2Izquierda;

            Sp1Arriba=Sprite1.getPositionY() + Sprite1.getHeight()/2;
            Sp1Abajo=Sprite1.getPositionY() - Sprite1.getHeight()/2;
            Sp1Derecha=Sprite1.getPositionX() + Sprite1.getWidth()/2;
            Sp1Izquierda=Sprite1.getPositionX() - Sprite1.getWidth()/2;
            Sp2Arriba=Sprite2.getPositionY() + Sprite2.getHeight()/2;
            Sp2Abajo=Sprite2.getPositionY() - Sprite2.getHeight()/2;
            Sp2Derecha=Sprite2.getPositionX() + Sprite2.getWidth()/2;
            Sp2Izquierda=Sprite2.getPositionX() - Sprite2.getWidth()/2;


//Me fijo si el vértice superior derecho de Sp1 está dentro de Sp2
            if (Sp1Arriba>=Sp2Abajo && Sp1Arriba<=Sp2Arriba &&
            Sp1Derecha>=Sp2Izquierda && Sp1Derecha<=Sp2Derecha) {
                HayInterseccion=true;
            }
//Me fijo si el vértice superior izquierdo de Sp1 está dentro de Sp2
            if (Sp1Arriba>=Sp2Abajo && Sp1Arriba<=Sp2Arriba &&
            Sp1Izquierda>=Sp2Izquierda && Sp1Izquierda<=Sp2Derecha) {
                HayInterseccion=true;
            }
//Me fijo si el vértice inferior derecho de Sp1 está dentro de Sp2
            if (Sp1Abajo>=Sp2Abajo && Sp1Abajo<=Sp2Arriba &&
            Sp1Derecha>=Sp2Izquierda && Sp1Derecha<=Sp2Derecha) {
                HayInterseccion=true;

            }
//Me fijo si el vértice inferior izquierdo de Sp1 está dentro de Sp2
            if (Sp1Abajo>=Sp2Abajo && Sp1Abajo<=Sp2Arriba &&
            Sp1Izquierda>=Sp2Izquierda && Sp1Izquierda<=Sp2Derecha) {
                HayInterseccion=true;
            }
//Me fijo si el vértice superior derecho de Sp2 está dentro de Sp1
            if (Sp2Arriba>=Sp1Abajo && Sp2Arriba<=Sp1Arriba &&
            Sp2Derecha>=Sp1Izquierda && Sp2Derecha<=Sp1Derecha) {
                HayInterseccion=true;
            }

            return  HayInterseccion;
        }
    }
}


