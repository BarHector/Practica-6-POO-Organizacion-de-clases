package practica6;

import java.util.ArrayList;

/**
* Esta clase crea un polinomio apartir de un String el cual lo guarda en un String, además deja realizar suma y resta de polinomios, así como la multiplicacion
* por un escalar.
* @author: Barriguete Rodríguez Héctor Alejandro
* @version: 12/10/2021
*/

public class Polynomial {

    //Campos de la clase
    private int degree;
    private int value;
    private ArrayList<Double> coeff;
    private ArrayList<Double> resultA;
    private ArrayList<Double> resultS;
    private ArrayList<Double> resultM;
    private String vals;
    
    /**
    * Constructor vacio de la clase 
    */
    public Polynomial(){
        degree = 0;
        vals="";
    }

    /**
    * Constructor con parametros de la clase 
    * @param degree define el grado de un polinomio
    * @param vals contienes todos los coeficientes del polinomio separados por un espacio
    */
    public Polynomial(int degree, String vals){
        this.degree = degree;
        this.vals = vals;
    }

    /**
    * Creacion de las ArrayList a partir de un String
    */
    public void createList(){
        coeff = new ArrayList<>(); //INICIALIZANDO EL ARRAYLIST

        for (int i = 0; i <= degree; i++){ //CREANDO ESPACIOS
            coeff.add(0.0);
        }

        //AÑADIENDO NUMEROS AL ARRAYLIST
        for(int i=0; i < vals.length(); i++){
            if(!vals.substring(i,i+1).equals(" ") && !vals.substring(i,i+1).equals(",")){
                if(vals.substring(i,i+1).equals("-") && Double.parseDouble(vals.substring(i+1,i+2)) >= 0){ //REVISANDO SI ES NEGATIVO
                    coeff.set(degree, Double.parseDouble(vals.substring(i, i+2))); 
                    i=i+2;
                }else if(Double.parseDouble(vals.substring(i,i+1)) >= 0){//REVISANDO SI ES POSITIVO
                    coeff.set(degree, Double.parseDouble(vals.substring(i,i+1)));
                }

                degree--;
            }
        }
    }

    /**
    * Suma de dos ArrayList apartir de sus objetos
    * @param lista1 es el objeto que contiene un polinomio
    * @param lista2 es el objeto que contiene otro polinomio(es un objeto distinto al anterior)
    */
    public void add(Polynomial lista1, Polynomial lista2){
        double suma=0.0;

        resultA = new ArrayList<>();

        if(lista1.getCoeff().size() < lista2.getCoeff().size()){
            for (int i = lista1.getCoeff().size(); i < lista2.getCoeff().size(); i++) {
                lista1.getCoeff().add(0.0);
            }
        }else if(lista1.getCoeff().size() > lista2.getCoeff().size()){
            for (int i = lista2.getCoeff().size(); i < lista1.getCoeff().size(); i++) {
                lista2.getCoeff().add(0.0);
            }
        }

        for (int i = 0; i < lista1.getCoeff().size(); i++) {
            suma = lista1.getCoeff().get(i) + lista2.getCoeff().get(i);
            resultA.add(suma);
        }
    }

    /**
    * Resta de dos ArrayList apartir de sus objetos
    * @param lista1 es el objeto que contiene un polinomio
    * @param lista2 es el objeto que contiene otro polinomio(es un objeto distinto al anterior)
    */
    public void substract(Polynomial lista1, Polynomial lista2){
        double resta=0.0;

        resultS = new ArrayList<>();
        
        if(lista1.getCoeff().size() < lista2.getCoeff().size()){
            for (int i = lista1.getCoeff().size(); i < lista2.getCoeff().size(); i++) {
                lista1.getCoeff().add(0.0);
            }
        }else if(lista1.getCoeff().size() > lista2.getCoeff().size()){
            for (int i = lista2.getCoeff().size(); i < lista1.getCoeff().size(); i++) {
                lista2.getCoeff().add(0.0);
            }
        }

        for (int i = 0; i < lista1.getCoeff().size(); i++) {
            resta = lista1.getCoeff().get(i) - lista2.getCoeff().get(i);
            resultS.add(resta);
        }
    }

    /**
    * Multiplicacion de la lista de un objeto con un escalar
    * @param lista1 es el objeto que contiene un polinomio
    * @param escalar es el valor por el cual se van a multiplicar todos los coeficientes del polinomio
    */
    public void multiplication(Polynomial po, int escalar){
        resultM = new ArrayList<>();
        
        for (int i = 0; i < po.getCoeff().size(); i++) {
            resultM.add(po.getCoeff().get(i) * escalar);
        }

        printP(resultM);
    }

    /**
    * Impresion del polinomio mostrando 'x' y potencia
    * @param polinomioR es la lista que contiene todos los coeficientes de un polinomio
    */
    public void printP(ArrayList<Double> polinomioR){
        String finalR="";
        int converI=0;
        double valorD=0.0;

        for (int i = polinomioR.size()-1; i >= 0; i--) {
            valorD = polinomioR.get(i);

            if(valorD != 0){
                if(valorD%1==0){
                    converI = (int)valorD;
                    
                    if(converI == 1 && i>0){
                        finalR = finalR.concat("");
                    }else if(converI == -1 && i > 0){
                        finalR = finalR.concat("-");
                    }else if(converI != 1 && converI != -1){
                        finalR = finalR.concat(String.valueOf(converI));
                    }else if(i==0){
                        finalR = finalR.concat(String.valueOf(converI));
                    }
                    
                    if(i >= 2){
                        finalR = finalR.concat("x").concat("^").concat(String.valueOf(i));
                    }else if(i == 1){
                        finalR = finalR.concat("x");
                    }
                }
            }

            if(i-1 >= 0 && polinomioR.get(i-1) > 0 && finalR.length() > 0){
                finalR = finalR.concat("+");
            }
        }

        //IMPRESION DEL POLINOMIO
        System.out.println(finalR);
    }

    /**
    * Evaluacion de los ArrayList del medotos 'add' y 'substract' apartir de un valor para X
    *@param polinomioE es la lista con los coeficientes de un polinomio
    *@param valX es el valor por el cual se va a evaluar el polinomio mandado anteriormente
    */
    public void evaluacionAS(ArrayList<Double> polinomioE, int valX){
        double resultado=0.0;
        int converI=0;

        for (int i = 0; i < polinomioE.size(); i++){
            if(i==0){
                resultado = polinomioE.get(i);
            }else{
                resultado = resultado + polinomioE.get(i) * Math.pow(valX, i);
            }
        }

        if (resultado%1==0) {
            converI = (int)resultado;
            System.out.println(converI);
        }else{
            System.out.println(resultado);
        }
    }

    /**
    * Metodo que devuelve el grado de un polinomio 
    * @return grado del polinomio
    * @Deprecated Este mtodo no se utiliza.
    */
    public int getDegree(){
        return degree;
    } 

    /**
    * Metodo que devuelve el valor con el que se va a evaluar un polinomio 
    * @return valor con el que se va a evaluar el polinomio
    * @Deprecated Este metodo no se utiliza.
    */
    public int getValue(){
        return value;
    }
    
    /**
    * Metodo que devuelve la lsita de coeficientes resultantes de la resta de dos polinomios 
    * @return Lista de coeficientes resultantes de la suma de polinomios
    */
    public ArrayList<Double> getResultA(){
        return resultA;
    }
    
    /**
    * Metodo que devuelve la lista de coeficientes resultantes de la resta de dos polinomios 
    * @return Lista de coeficientes resultantes de la resta de polinomios
    */
    public ArrayList<Double> getResultS(){
        return resultS;
    }
    
    /**
    * Metodo que devuelve la lista de coeficientes de un polinomio 
    * @return Lista de coeficientes de un polinomio
    */
    public ArrayList<Double> getCoeff(){
        return coeff;
    }

    /**
    * Envio del grado para un polinomio
    * @param degree grado del polinomio
    * @Deprecated Este metodo no se utiliza.
    */
    public void setDegree(int degree){
        this.degree = degree;
    }

    /**
    * Envio del valor con el que se va a evaluar un polinomio
    * @param value valor por el que se va a evaluar a un polinomio
    * @Deprecated Este metodo no se utiliza.
    */
    public void setValue(int value){
        this.value = value;
    }
}