package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

public class MutationInversion<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationInversion(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        //TODO --done
        int cut1 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());

        int cut2;
        do {
            cut2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        } while(cut1 == cut2);

        int mid;
        if (cut1 > cut2) {
            mid = cut1;
            cut1 = cut2;
            cut2 = mid;
        }

        mid = cut1 + (cut2 + 1 - cut1) / 2;//ponto medio entre os dois cortes sempre
        int endCount = cut2; //variável auxiliar que representa a posição do corte final

        for(int i = cut1; i < mid; ++i) {
            int aux = ind.getGene(i);
            ind.setGene(i, ind.getGene(endCount));
            ind.setGene(endCount, aux);
            --endCount;
        }
    }

    @Override
    public String toString(){
        return "Inversion mutation (" + this.probability + ")";
    }
}