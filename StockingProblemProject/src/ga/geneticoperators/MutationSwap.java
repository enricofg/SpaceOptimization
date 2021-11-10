package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

public class MutationSwap<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationSwap(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        //TODO--done
        int indSize = ind.getNumGenes();
        int posSwap1 = GeneticAlgorithm.random.nextInt(indSize);

        int posSwap2;
        do {
            posSwap2 = GeneticAlgorithm.random.nextInt(indSize);
        } while(posSwap1 == posSwap2);

        int aux = ind.getGene(posSwap1);//recebe o valor da primeira posicao
        ind.setGene(posSwap1, ind.getGene(posSwap2));//coloca na posicao 1 o valor da posicao 2
        ind.setGene(posSwap2, aux);//coloca na posicao 2 o valor da posicao 1
    }

    @Override
    public String toString(){
        return "Swap mutation (" + this.probability + ")";
    }
}