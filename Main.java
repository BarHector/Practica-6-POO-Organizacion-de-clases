import java.util.ArrayList;
import java.util.Scanner;
import practica6.*;

public class Main {
    public static void main(String[] args){
        Scanner nao = new Scanner(System.in);
        
        String grade, pol, value="", ext="", ext2="", escalar="";
        ArrayList <Polynomial> pols = new ArrayList<>();

        Polynomial p1;

        while(nao.hasNextLine()){
            ext = nao.nextLine();
            ext2 = nao.nextLine();
            
            if(!ext.contains(" ") && !ext2.contains(" ")){
                value = ext; //VALOR PARA LA SUMA Y RESTA
                escalar = ext2; //ESCALAR
            }else{
                grade = ext; //GRADO DEL POLINOMIO
                pol = ext2; //COEFICIENTES POLINOMIO
                p1 = new Polynomial(Integer.parseInt(grade), pol);
                pols.add(p1); 
                p1.createList();
                p1.printP(p1.getCoeff()); //IMPRESION 1 , 2
            }
        }

        Polynomial R = new Polynomial();

        R.add(pols.get(0), pols.get(1));
        R.substract(pols.get(0), pols.get(1));
        
        R.printP(R.getResultA()); //IMPRESION 3
        R.printP(R.getResultS()); //IMPRESION 4

        if(Integer.parseInt(escalar) != 0){
            R.multiplication(pols.get(0), Integer.parseInt(escalar)); //IMPRESION 5
            R.multiplication(pols.get(1), Integer.parseInt(escalar)); //IMPRESION 6
        }else{
            System.out.println("0\n0");
        }
        
        R.evaluacionAS(R.getResultA(), Integer.parseInt(value)); //IMPRESION 7
        R.evaluacionAS(R.getResultS(), Integer.parseInt(value)); //IMPRESION 8
        
        nao.close();
    }
}