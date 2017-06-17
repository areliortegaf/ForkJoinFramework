/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forkjoinframework;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author ASORTEGA
 */
public class SingleThread {
    //Ejemplo de una tarea de un solo hilo
    public  static void main (String ... args){
        int [] data = new int[1024 * 1024 * 256]; //sostiene 1G de datos
        for (int i = 0; i<= data.length ; i++){
            data[i] = ThreadLocalRandom.current().nextInt(); //ThreadLocalRandom es una clase segura para crear
              //  *current llena el array con los valores    //un numero random seguro
            //este for tambien se puede haces en paralelo
        }
        
        int max = Integer.MIN_VALUE;
        for (int value : data){
            if(value >max){
                max = value;
            }
        }
        
        System.out.println("VALOR MAXIMO ENCONTRADO: " + max);
    }
    
}
