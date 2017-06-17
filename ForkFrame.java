/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forkjoinframework;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author ASORTEGA
 */
public class ForkFrame extends RecursiveTask<Integer> {

    /*  JAVA.UTIL.CONCURRENT.FORKJOINTASK<V>
     A fork join task represent a task to be excecuted 
     A task containd the code and data to be process, similar to Callable or Rubable
     A huge number of tasks has been created and processed by a small number of threads 
     in a forkjoin pool.
     A forkjointask usually creates more forkjointask instances until the data to prcess has
     been subdivide adequately.
     DEVELOPERS USUALLY USES THE FOLLOWING SUBCLASES
     RercusiveAction: when a task does not need to return aresult
     RecursiveTask: when a task needs to return a result.
    
     */
    //FIELDS
    private final int threshold;
    private final int myarray[];
    private int start;
    private int end;
    private Object data;

    //regresa una instancia de la misma clase
    //CONSTRUCTOR
    public ForkFrame(int[] myarray, int start, int end, int threshold) {
        this.threshold=threshold;
        this.myarray = myarray;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        //single thrad
        if (end - start < threshold) {
            int max = Integer.MIN_VALUE;
            for (int i = start; i <= end;i++){
            int n = myarray[i];
            if (n>max){
                max = n;
            }
        }
            return max;
        }else{
            //CREA DOS INTANCIAS, DIVIDE EL HILO A LA MITAD
           int midway = (end - start ) / 2 + start;
           ForkFrame a1 = new ForkFrame(myarray, start, midway, threshold);
           a1.fork();
           ForkFrame a2 = new ForkFrame (myarray, start, midway +1, threshold);
           return Math.max(a2.compute(), a1.join());
        }
        
        
       /* 
        //EXPLICACION DEL COMPUTO
        if DATA_SMALL_ENOUNGH {//cuando no necesitamos dividir los datos es cuando se determinaque los datos son pocos
            PROCESS_DATA;
            return RESULT;
        } else{
            //si los datos son mayores dividimos los datos en 2 tareas
            SPLIT_DATA_INTO_LEFT_AND_RIGHT_PARTS
            TASK t1 = new TASK(LEFT_DATA);
            t1.fork();//es lo mismo que el start del los threads
            TASK task2 = new TASK(RIGHT_DATA);
            return COMBINE(t2.compute(), t1.join());
            //t2 llama a compute otra vez "recursive task" 
        }*/
    }
    
    public void forkjoinexample(){
        ForkJoinPool pool = new ForkJoinPool();
        ForkFrame t1 =
                new ForkFrame(data, 0, data.length -1, data.length/16);
        //PARAMETROS: datos, punto de inicio, el total de los datos menos 1, en total de los datos entre 16
        Integer resutl = pool.invoke(t1);
        //con el invoke el metodo compute es automaticamente llamado
    }
    
}
