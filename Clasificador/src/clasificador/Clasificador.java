/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificador;

/**
 *
 * @author Kathy
 */
public class Clasificador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Perceptron pt = new Perceptron();
        RedNeuronal red = new RedNeuronal();
        //EVALUA UN ARCHIVO CON UN TEST Y POSTERIORMENTE PREDICE UN ARCHIVO SIN CLASIFICACIÓN
//        red.Entrenamiento(" -L 0.2 -M 0.2 -N 100 -V 2 -S 9 -E 50 -H 100");
//        System.out.println("\n\nTEST DEL MODELO DE PREDICCIÓN\n\n");
//        red.testing();
//        System.out.println("\n\nPREDICCIONES DE UN NUEVO ARCHIVO\n\n");
//        red.prediccion();
        //PRUEBA DE MODELOS CON DIFERENTES CLASIFICADORES
        //pt.perceptron_multicapa();
        //pt.naive_Bayes();
        pt.J48();
    }
    
}
