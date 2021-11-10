package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

import java.util.*;

public class RecombinationCycle<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    private int[] child1;
    private int[] child2;

    public RecombinationCycle(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        //TODO -- done
        int size = ind1.getNumGenes();//só para pegar o tamanho do pai
        this.child1 = new int[size];//tamanho do pai
        this.child2 = new int[size];
        this.fulfill(this.child1, ind2);//"tipo" clone
        this.fulfill(this.child2, ind1);
        Set<Integer> visitedIndices = new HashSet(size);//var aux: guardar os indices visitados
        List<Integer> indices = new ArrayList(size);//uma lista de inteiros com tamanho do pai
        int idx = GeneticAlgorithm.random.nextInt(size);//posicao aleatoria de onde tera o "corte" para a troca de alelos
        int var7 = 1;//var aux para controlar paridade

        while(visitedIndices.size() < size) { //while enquanto todos os indices não foram visitados
            indices.add(idx);//guarda indice da iteração
            int item = ind2.getGene(idx);//alelo do pai2 no indice idx -> onde inicia o corte para a troca

            for(idx = ind1.getIndexof(item); idx != indices.get(0); idx = ind1.getIndexof(item)) {
                indices.add(idx);
                item = ind2.getGene(idx);
            }//acrescentar na lista de indices, os indices que serao trocados entre os pais nos filhos

            int i;
            int aux;
            if (var7++ % 2 != 0) {//se for impar -> na primeira iteração, este seguinte for é pulado
                //for só a partir do segundo ciclo
                for(Iterator var9 = indices.iterator(); var9.hasNext(); this.child2[i] = aux) { //enquanto houver indices na lista dos indices que serao trocados entres os pais nos filhos
                    i = (Integer)var9.next(); //proximo indice (na primeira iteração: é o primeiro índice)
                    aux = this.child1[i]; //auxiliar é igual ao alelo na posição do índice
                    this.child1[i] = this.child2[i]; //alelo na posição i do primeiro filho = alelo na posição i do segundo filho
                } //no fim de cada iteração, o alelo na posição i do filho 2 é igual à variável aux
            } //basicamenta a troca dos alelos é feita aqui

            visitedIndices.addAll(indices); //indices de troca entre os pais nos filhos são acrescentados na HashSet de indices visitados
            idx = (indices.get(0) + 1) % size; //N TENHO CERTEZA: posição onde inicia os indices restantes a serem trocados

            while(visitedIndices.contains(idx) && visitedIndices.size() < size) { //encontrar indices não visitados -> enquanto idx não estiver nos indices visitados, fazer:
                ++idx; //incrementar indice
                if (idx >= size) { //se indice passou dos limites, iniciar do 0
                    idx = 0;
                }
            }

            indices.clear(); //limpar lista de indices para a próxima iteração
        }

        //pais ficam iguais aos filhos
        this.changeInd(this.child1, ind1);
        this.changeInd(this.child2, ind2);
    }

    public void fulfill(int[] child, I ind) {
        for(int i = 0; i < ind.getNumGenes(); ++i) {//pecorre alelos do pai
            child[i] = ind.getGene(i);//para cada posicao do filho, preenche com o alelo do pai
        }
    }

    public void changeInd(int[] child, I ind) {
        for(int i = 0; i < child.length; ++i) {
            ind.setGene(i, child[i]);
        }
    }

    @Override
    public String toString() {
        return "Cycle recombination (" + this.probability + ")";
    }
}