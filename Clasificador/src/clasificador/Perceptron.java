/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author Kathy
 */
public class Perceptron {
 
    double resultado;
    String polaridad="";
    //Código del clasificador
    public void perceptron_multicapa(){
        try
        {
            //INSTANCIAS PARA ENTRENAMIENTO DEL CLASIFICADOR
            ConverterUtils.DataSource converU =new ConverterUtils.DataSource("C:\\Users\\Kathy\\Documents\\tutorial perl\\libro.arff");
            Instances instancias=converU.getDataSet();
            instancias.setClassIndex(instancias.numAttributes()-1);
           
            //INSTANCIAS PARA EL TEST DEL MODELO 
            ConverterUtils.DataSource convertest = new ConverterUtils.DataSource("C:\\Users\\Kathy\\Documents\\tutorial perl\\libro5.arff");
            Instances testInstance = convertest.getDataSet();
            testInstance.setClassIndex(testInstance.numAttributes()-1);
            
            //CONTRUCCIÓN DEL CLASIFICADOR
            MultilayerPerceptron perceptron =new MultilayerPerceptron();
            perceptron.buildClassifier(instancias);
            //Evaluar las instancias
            Evaluation ev =new Evaluation(instancias);
            //EVALUAR MODELO DE ENTRENAMIENTO
            ev.evaluateModel(perceptron, instancias);
            //System.out.println(instancias);
            System.out.println("\n\nENTRENAMIENTO DEL MODELO PERCEPTRÓN MULTICAPA\n\n");
            System.out.println(ev.toSummaryString("_____RESULTADO_____", true));
            System.out.println(ev.toMatrixString("_____Matriz confusion___"));
            
            //EVALUACIÓN DEL MODELO
            ev.evaluateModel(perceptron, testInstance);
            //System.out.println(instancias);
            System.out.println("\n\nTEST DEL MODELO PERCEPTRÓN MULTICAPA\n\n");
            System.out.println(ev.toSummaryString("_____RESULTADO_____", true));
            System.out.println(ev.toMatrixString("_____Matriz confusion___"));
            
            //MOSTRAR VALORES 
             for (int i = 0; i < ev.evaluateModel(perceptron,testInstance).length; i++)
             {
                System.out.println("Se clasifica como:  "+ev.evaluateModel(perceptron,testInstance)[i]);                
             }  
            
           
       }
        
        catch(Exception ex){
            Logger.getLogger(Perceptron.class.getName()).log(Level.SEVERE, null, ex);
        }
        
  }   
    
  public void naive_Bayes(){
      try
        {
            //INSTANCIAS PARA ENTRENAMIENTO DEL CLASIFICADOR
            ConverterUtils.DataSource converU =new ConverterUtils.DataSource("C:\\Users\\Kathy\\Documents\\tutorial perl\\libro.arff");
            Instances instancias=converU.getDataSet();
            instancias.setClassIndex(instancias.numAttributes()-1);
           
            //INSTANCIAS PARA EL TEST DEL MODELO 
            ConverterUtils.DataSource convertest = new ConverterUtils.DataSource("C:\\Users\\Kathy\\Documents\\tutorial perl\\libro5.arff");
            Instances testInstance = convertest.getDataSet();
            testInstance.setClassIndex(testInstance.numAttributes()-1);
            
            //CONTRUCCIÓN DEL CLASIFICADOR
            NaiveBayes perceptron =new NaiveBayes();
            perceptron.buildClassifier(instancias);
            //Evaluar las instancias
            Evaluation ev =new Evaluation(instancias);
            //EVALUAR MODELO DE ENTRENAMIENTO
            ev.evaluateModel(perceptron, instancias);
            //System.out.println(instancias);
            System.out.println("\n\nENTRENAMIENTO DEL MODELO NAIVE BAYES\n\n");
            System.out.println(ev.toSummaryString("_____RESULTADO_____", true));
            System.out.println(ev.toMatrixString("_____Matriz confusion___"));
            
            //EVALUACIÓN DEL MODELO
            ev.evaluateModel(perceptron, testInstance);
            //System.out.println(instancias);
            System.out.println("\n\nTEST DEL MODELO NAIVE BAYES\n\n");
            System.out.println(ev.toSummaryString("_____RESULTADO_____", true));
            System.out.println(ev.toMatrixString("_____Matriz confusion___"));
            
            //MOSTRAR VALORES 
             for (int i = 0; i < ev.evaluateModel(perceptron,testInstance).length; i++)
             {
                System.out.println("Se clasifica como:  "+ev.evaluateModel(perceptron,testInstance)[i]);                
             }  
        }
        
        catch(Exception ex){
            Logger.getLogger(Perceptron.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
    
  public void J48(){
       try
        {
            //INSTANCIAS PARA ENTRENAMIENTO DEL CLASIFICADOR
            ConverterUtils.DataSource converU =new ConverterUtils.DataSource("C:\\Users\\Kathy\\Documents\\tutorial perl\\libro.arff");
            Instances instancias=converU.getDataSet();
            instancias.setClassIndex(instancias.numAttributes()-1);
           
            //INSTANCIAS PARA TEST DEL MODELO 
            ConverterUtils.DataSource convertest = new ConverterUtils.DataSource("C:\\Users\\Kathy\\Documents\\tutorial perl\\libro5.arff");
            Instances testInstance = convertest.getDataSet();
            testInstance.setClassIndex(testInstance.numAttributes()-1);
            //INSTANCIAS PARA PREDICCIÓN
            ConverterUtils.DataSource converPredict = new ConverterUtils.DataSource("C:\\Users\\Kathy\\Documents\\tutorial perl\\libro1.arff");
            Instances predictInstance = converPredict.getDataSet();
            predictInstance.setClassIndex(predictInstance.numAttributes()-1);
            //CONTRUCCIÓN DEL CLASIFICADOR
            J48 perceptron =new J48();
            perceptron.buildClassifier(instancias);
            //Evaluar las instancias
            Evaluation ev =new Evaluation(instancias);
            //EVALUAR MODELO DE ENTRENAMIENTO
            ev.evaluateModel(perceptron, instancias);
            //System.out.println(instancias);
            System.out.println("\n\nENTRENAMIENTO DEL MODELO ÁRBOL DE DECISIÓN J48\n\n");
            System.out.println(ev.toSummaryString("_____RESULTADO_____", true));
            System.out.println(ev.toMatrixString("_____Matriz confusion___"));
                     
            //PREDECIR CON EL MODELO
            Evaluation evPredict =new Evaluation(instancias);
            evPredict.evaluateModel(perceptron, predictInstance);
            
            //System.out.println(instancias);
            System.out.println("\n\nPREDICCIÓN DEL MODELO ÁRBOL DE DECISIÓN J48\n\n");
            System.out.println(evPredict.toSummaryString("_____RESULTADO_____", false));
            System.out.println(evPredict.toMatrixString("_____Matriz confusion___"));
            
            //MOSTRAR VALORES 
             for (int i = 0; i < evPredict.evaluateModel(perceptron,predictInstance).length; i++)
             {
                 resultado = evPredict.evaluateModel(perceptron,predictInstance)[i];
                 polaridad += polaridad(resultado)+"\n";
                 //System.out.println("Se clasifica como:  "+resultado + "que es: " + polaridad(resultado));                
             }  
             archivoResultados( polaridad);
                      
             //TEST DEL MODELO CON LOS DATOS DEL CLASIFICADOR
             Evaluation evtesting =new Evaluation(instancias);
            evtesting.evaluateModel(perceptron, testInstance);
            
            //System.out.println(instancias);
            System.out.println("\n\nTEST DEL MODELO ÁRBOL DE DECISIÓN J48\n\n");
            System.out.println(evtesting.toSummaryString("_____RESULTADO_____", false));
            System.out.println(evtesting.toMatrixString("_____Matriz confusion___"));
        }
        
        catch(Exception ex){
            Logger.getLogger(Perceptron.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
    
  public String polaridad(double resultado){
      
      if (resultado == 0.0){
          return "positive";
      }
      if (resultado == 1.0){
          return "negative";
      }
      if (resultado == 2.0){
          return "neutral";
      }
      
      return "error";
  }
  
  public void archivoResultados(String polaridad){
       try
    {
        System.out.println("\nEscritura de resultados\n");
        //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
        File archivo = new File("C:\\Users\\Kathy\\Documents\\tutorial perl\\resultados.txt");
        //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
        FileWriter escribir = new FileWriter(archivo,true);
        String encabezado;
        encabezado =  polaridad;
        escribir.write(encabezado);
        //Cerramos la conexion
        escribir.close();
        System.out.println("Archivo cerrado");
    }

    //Si existe un problema al escribir cae aqui
    catch(Exception e)
    {
    System.out.println("Error al escribir");
    }
       
  }
  
}
