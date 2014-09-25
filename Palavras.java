Computacao-Evolucionaria - Trabalho II
========================

import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author And
 */
public class Palavras {
    
    public static void main (String [] args) throws IOException{
        
        MersenneTwisterFast r;
     
        final long seed = System.currentTimeMillis();
    
        //double PalavraAlvo[] = {0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.6};
     
        float [] populacao = new float[60];
        
        float [] gene = new float[10];
        
        double [] populafit = new double [60];
        
        PrintWriter escritor = new PrintWriter(
                new FileWriter("saida.txt"));
        
        //FileWriter myWriter = new FileWriter ( "DadosPop.csv");
        
        double fitness = 0;
        
        double mediager = 0;
        
        String pav;
        
        int geracao = 1;
        
        int entradageracao = 0;
        
        r = new MersenneTwisterFast (Math.abs(seed)); 
        
     //#########################################################################  
        //----------------------------------------------------------------------
        entradageracao = Integer.parseInt(JOptionPane.showInputDialog("Número de gerações"));
        double [] mediapop = new double [entradageracao+1];
        while( geracao <= entradageracao){
            
            //Se for a primeira geração, ele cria 10 genes aleatórios
            //para cada um dos 60 individuos.
            if (geracao == 1){
            //Cria os 60 individuos da população
                for (int k=0; k<60; k++){
                    //Cria os 10 genes de um individuo aleatoriamente
                    //System.out.println("Individuo "+(k+1)+"  ");
                    for ( int i=0; i<10; i++){  
                        gene[i] = r.nextFloat();
                        fitness = fitness + Math.abs(gene[i]-0.6);
                        //System.out.print("Gene "+(i+1)+": ");
                        //System.out.println(gene[i]);
                        //populacao[k]= gene[i];
                    }
                    populafit[k] = fitness;
                    mediapop[1] = mediapop[1] + fitness;
                    //System.out.println("Fitness: "+fitness);          
                    //System.out.println("");
                    fitness = 0;
                }
                mediapop[1] = mediapop[1]/60;
                System.out.println("");System.out.println("");System.out.println("");
        
                //ordenar 
                for (int x = 0; x < populafit.length; x++) {
                    for (int y = x+1; y < populafit.length; y++) {
                        if(populafit[x] > populafit[y] ){
                            double aux = populafit[x];
                            populafit[x] = populafit[y];
                            populafit[y] = aux;
                        }
                    }
                }
            }
            //a partir da segunda geração, com o vetor já ordenador
            //ele sobrepõe 30 novos individuos nos 30 com piores fitness
            else{  
                for (int w=30; w<60; w++){
                    //Cria os 10 genes de um individuo aleatoriamente
                    //System.out.println("Individuo "+(w+1)+"  ");
                    for ( int z=0; z<10; z++){  
                        gene[z] = r.nextFloat();
                        fitness = fitness + Math.abs(gene[z]-0.6);
                        //System.out.print("Gene "+(z+1)+": ");
                        //System.out.println(gene[z]);
                        //populacao[w]= gene[z];
                    }
                    populafit[w] = fitness;
                    //System.out.println("Fitness: "+fitness);          
                    //System.out.println("");
                    fitness = 0;
                }    
            }
            //ordenar 
            for (int x = 0; x < populafit.length; x++) {
                for (int y = x+1; y < populafit.length; y++) {
                    if(populafit[x] > populafit[y] ){
                        double aux = populafit[x];
                        populafit[x] = populafit[y];
                        populafit[y] = aux;
                    }
                }                
            }
            for(int p=0; p<60;p++){
                mediapop[geracao] = mediapop[geracao] + populafit[p];
            }
            mediapop[geracao] = mediapop[geracao]/60;
            
            
            //try {
                
            pav = String.valueOf(populafit[0]);
            escritor.print(pav);
            escritor.print(";");
            escritor.print(mediapop[geracao]);
            escritor.println(";");
            
            escritor.flush();
            //}
                
            //}catch (FileNotFoundException ex) {
            //System.out.println("Erro " + ex);
            //}
            geracao++;
        }    
        //----------------------------------------------------------------------
        
        
        //Imprime a população ordenada pelo fitness
        System.out.println("População Ordeanada pelo Fitness");
        for (int s = 0; s < populafit.length; s++) {
            System.out.println("Individuo "+(s+1)+"  ");
            for ( int h=0; h<10; h++){  
                        
                System.out.print("Gene "+(h+1)+": ");
                System.out.println(gene[h]);
                populacao[s]= gene[h];
            }
            System.out.println("Fitness: "+populafit[s]);
            System.out.println("");
        }  
        
        escritor.close();
    }
}
