/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.misc.SerializedClassifier;
import weka.classifiers.trees.J48;
import weka.core.Debug;
import weka.core.Instances;
import weka.core.Utils;

/**
 *
 * @author Kathy
 */
public class RedNeuronal {
     //metodo para hacer el entrenamiento 

    public void Entrenamiento(String paramNN){
        try {
            //aqui va a anetrenar la red neuronal con parametros para la red
            FileReader trainReader = new FileReader(new File(System.getProperty("user.dir")+"\\src\\clasificador\\archivos\\libro.arff"));
            //FileReader trainReader = new FileReader("aqui va la ruta");
            //intancias
            //lo que vamoas a hacer en agarrar ese objeto y cargarlo dentro de nuestra clase instancias
            Instances trainInstance= new Instances(trainReader);
            trainInstance.setClassIndex(trainInstance.numAttributes()-1);//esta fijando las etiquetas en el archivo las clases estan en el final es decir el total -1 esto es xk es un ambiento controlado 
            
            //construccion de la red perceptron multicapa 
            MultilayerPerceptron mlp = new MultilayerPerceptron(); // creo un objeto de  perceptron multicapaa
            mlp.setOptions(Utils.splitOptions(paramNN));
//fijar los parametros de la red perceptron util es para q reciba toda la confiuguracion es proipio de weka
            mlp.buildClassifier(trainInstance);// la construccion se hace ya basadao en los parametron configurado 
            
            
            //Guardar el mlp en un archivo 
            Debug.saveToFile("TrainMLP.train", mlp);
            //evaluacion del entrenamiento despies solo se ocupa el trainMLp
            SerializedClassifier sc = new SerializedClassifier();
            sc.setModelFile(new File("TrainMLP.train"));
            Evaluation evaluarEntrenamiento= new Evaluation(trainInstance);
            evaluarEntrenamiento.evaluateModel(mlp, trainInstance);//evaluando el modelo
            System.out.println(evaluarEntrenamiento.toSummaryString("resultado",false));
            System.out.println(evaluarEntrenamiento.toMatrixString("*****************Matriz de confusion*******"));
            trainReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public  void testing(){
        try {
            FileReader testReader = new FileReader(new File(System.getProperty("user.dir")+"\\src\\clasificador\\archivos\\librotest.arff"));
            Instances testInstance = new Instances(testReader);
            testInstance.setClassIndex(testInstance.numAttributes()-1);
            Evaluation evalTest = new Evaluation(testInstance);
            SerializedClassifier clasificador = new SerializedClassifier();
            clasificador.setModelFile(new File("TrainMLP.train"));
            //CLASIFICADOR ESTANDAR
            Classifier clasificadorEstandar = clasificador.getCurrentModel();
            evalTest.evaluateModel(clasificadorEstandar, testInstance);
            
            System.out.println(evalTest.toSummaryString("resultado:",false));
            System.out.println(evalTest.toMatrixString("*****************Matriz de confusion*******"));
            
            //vamos a predecir el numero q voy a usar       
           // evalTest.toMatrixString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void prediccion(){
        
         FileReader testReader = null;
        try {
            testReader = new FileReader(new File(System.getProperty("user.dir")+"\\src\\clasificador\\archivos\\libro1.arff"));
            Instances testInstance = new Instances(testReader);
            testInstance.setClassIndex(testInstance.numAttributes()-1);
            Evaluation evalTest = new Evaluation(testInstance);
            SerializedClassifier clasificador = new SerializedClassifier();
            clasificador.setModelFile(new File("TrainMLP.train"));
            //CLASIFICADOR ESTANDAR
            Classifier clasificadorEstandar = clasificador.getCurrentModel();
            evalTest.evaluateModel(clasificadorEstandar, testInstance);
           
		 double []valores = evalTest.evaluateModel(clasificadorEstandar, testInstance);
           
		 for (int i = 0; i < valores.length ; i++) {
                
                System.out.println("se predice:     "+valores[i]+"\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                testReader.close();
            } catch (IOException ex) {
                Logger.getLogger(RedNeuronal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
          
    }
}
