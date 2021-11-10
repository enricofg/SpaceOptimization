package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class RecombinationOrdered<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    public RecombinationOrdered(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        //TODO -- done
        int size = ind1.getNumGenes();
        ArrayList<Integer> child1 = new ArrayList(size); //filhos são listas
        ArrayList<Integer> child2 = new ArrayList(size);
        Set<Integer> child1Set = new HashSet(size); //auxiliares para controlar os alelos que estão sendo colocados
        Set<Integer> child2Set = new HashSet(size);
        int cut1 = GeneticAlgorithm.random.nextInt(size); //posição escolhida aleatoriamente

        int cut2;
        do {
            cut2 = GeneticAlgorithm.random.nextInt(size);
        } while(cut1 == cut2); //cut1 deve ser diferente de cut2

        int i;
        if (cut1 > cut2) {
            i = cut1;
            cut1 = cut2;
            cut2 = i;
        } //se cut1 estiver depois do cut2, inverte-se os valores

        //preencher filho 1 com sublista dos alelos do pai 1, dos indíces cut1 até cut2+1
        child1.addAll(ind1.subList(cut1, cut2 + 1));
        //preencher auxiliar do filho 1 com os mesmos valores
        child1Set.addAll(child1);
        //preencher filho 2 com sublista dos alelos do pai 2, dos indíces cut1 até cut2+1
        child2.addAll(ind2.subList(cut1, cut2 + 1));
        //preencher auxiliar do filho 2 com os mesmos valores
        child2Set.addAll(child2);

        for(i = 1; i <= size; ++i) { //onde se itera para preencher os filhos (enquanto for menor que tamanho do pai)
            int idx = (cut2 + i) % size; //pegar indice onde começam os alelos que não estão dentro da sublista de corte
            int item1 = ind1.getGene(idx); //alelo do pai 1 na posição idx
            int item2 = ind2.getGene(idx);//alelo do pai 2 na posição idx
            if (!child1Set.contains(item2)) { //verificar se sublista do filho 1 já contém o item do pai 2 na posição idx, se não contém:
                child1.add(item2); //acrescenta item do pai 2
                child1Set.add(item2); //acrescenta na lista auxiliar
            }

            //mesma coisa para filho 2, utilizando o pai 1
            if (!child2Set.contains(item1)) {
                child2.add(item1);
                child2Set.add(item1);
            }
        }

        Collections.rotate(child1, cut1);//grau de recombinacao a mais
        Collections.rotate(child2, cut1);
        this.changeInd(child1, ind1);
        this.changeInd(child2, ind2);
    }

    public void changeInd(ArrayList<Integer> child, I ind) {
        for(int i = 0; i < child.size(); ++i) {
            ind.setGene(i, (Integer)child.get(i));
        }

    }

    @Override
    public String toString(){
        return "Ordered recombination (" + this.probability + ")";
    }
}