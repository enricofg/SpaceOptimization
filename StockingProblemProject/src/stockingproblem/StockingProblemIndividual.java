package stockingproblem;

import algorithms.IntVectorIndividual;
import ga.GeneticAlgorithm;

import java.util.Arrays;

public class StockingProblemIndividual extends IntVectorIndividual<StockingProblem, StockingProblemIndividual> {
    private int quantidadeCortes;
    int[][] material;
    int tamanhoColunas = 0;

    public StockingProblemIndividual(StockingProblem problem, int size) {
        super(problem, size);

        Integer tamanhoGene = genome.length;

        for (int i = 0; i < tamanhoGene; i++) {
            int verificar;
            do {
                verificar = GeneticAlgorithm.random.nextInt(size);
            } while (this.genome[verificar] != 0);

            genome[verificar] = i;
            //assim foi criado varios individuos diferentes
        }
        System.out.println(Arrays.toString(genome));
    }

    public StockingProblemIndividual(StockingProblemIndividual original) {
        super(original);
        //fazer o clone das variaveis do problem
        this.quantidadeCortes = original.quantidadeCortes;
        this.material = original.material;
        this.tamanhoColunas = original.tamanhoColunas;
    }

    @Override
    public double computeFitness() {//quando eu crio a população inicial passar por aqui 1x
        this.fitness = 0.0;
        this.quantidadeCortes = 0;
        //int penalizacao = 0;
        this.tamanhoColunas = 0;

        material = new int[problem.getMaterialHeight()][problem.getMaterialWidth()];//dimensionar
        int materialWidth = problem.getMaterialWidth();
        int tamanhoLinhas = problem.getMaterialHeight();
        boolean existeEspaço = false;

        for (int g = 0; g < genome.length; g++) {//for para o genoma

            for (int c = 0; c < materialWidth; c++) {
                for (int l = 0; l < tamanhoLinhas; l++) {
                    existeEspaço = checkValidPlacement(problem.getItems().get(genome[g]), l, c);
                    if (existeEspaço == true) {
                        placement(problem.getItems().get(genome[g]), l, c);
                        break;
                    }
                }

                if (existeEspaço == true) {
                    break;
                }

            }
        }

        //corte -> comparar peças, se não forem iguais, faz-se o corte
        tamanhoColunas++;
        //corte vertical
        for (int i = 0; i < tamanhoLinhas; i++) {
            for (int j = 0; j < tamanhoColunas - 1; j++) {
                if (material[i][j] != material[i][j + 1])
                    quantidadeCortes++;
            }
        }

        //corte horizontal
        for (int i = 0; i < tamanhoLinhas - 1; i++) {
            for (int j = 0; j < tamanhoColunas; j++) {
                if (material[i][j] != material[i + 1][j])
                    quantidadeCortes++;
            }
        }

        System.out.println("Cortes: " + quantidadeCortes);
        quantidadeCortes += tamanhoLinhas;
        this.fitness = tamanhoColunas * 0.7 + quantidadeCortes * 0.3;
        return this.fitness;
    }


    private boolean checkValidPlacement(Item item, int lineIndex, int columnIndex) {
        int[][] itemMatrix = item.getMatrix();
        for (int i = 0; i < itemMatrix.length; i++) {
            for (int j = 0; j < itemMatrix[i].length; j++) {
                if (itemMatrix[i][j] != 0) {
                    if ((lineIndex + i) >= material.length
                            || (columnIndex + j) >= material[0].length
                            || material[lineIndex + i][columnIndex + j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private int[][] placement(Item item, int lineIndex, int columnIndex) {
        int[][] itemMatrix = item.getMatrix();
        for (int i = 0; i < itemMatrix.length; i++) {package stockingproblem;

import algorithms.IntVectorIndividual;
import ga.GeneticAlgorithm;

import java.util.Arrays;

            public class StockingProblemIndividual extends IntVectorIndividual<StockingProblem, StockingProblemIndividual> {
                //TODO - DONE
                private int quantidadeCortes;
                int[][] material;
                int tamanhoColunas = 0;

                public StockingProblemIndividual(StockingProblem problem, int size) {
                    super(problem, size);
                    //TODO - DONE
                    Integer tamanhoGene = genome.length;

                    for (int i = 0; i < tamanhoGene; i++) {
                        int verificar;
                        do {
                            verificar = GeneticAlgorithm.random.nextInt(size);
                        } while (this.genome[verificar] != 0);

                        genome[verificar] = i;
                        //assim foi criado varios individuos diferentes
                    }
                    System.out.println(Arrays.toString(genome));
                }

                public StockingProblemIndividual(StockingProblemIndividual original) {
                    super(original);
                    //TODO - DONE
                    //fazer o clone das variaveis do problem
                    this.quantidadeCortes = original.quantidadeCortes;
                    this.material = original.material;
                    this.tamanhoColunas = original.tamanhoColunas;
                }

                @Override
                public double computeFitness() {//quando eu crio a população inicial passar por aqui 1x
                    //TODO - DONE
                    this.fitness = 0.0;
                    this.quantidadeCortes = 0;
                    //int penalizacao = 0;
                    this.tamanhoColunas = 0;

                    material = new int[problem.getMaterialHeight()][problem.getMaterialWidth()];//dimensionar
                    int materialWidth = problem.getMaterialWidth();//largura
                    int tamanhoLinhas = problem.getMaterialHeight();//altura
                    boolean existeEspaço = false; //var axiliar para validar as posições

                    for (int g = 0; g < genome.length; g++) {//for para o genoma

                        for (int c = 0; c < materialWidth; c++) {//coluna
                            for (int l = 0; l < tamanhoLinhas; l++) {//linha
                                existeEspaço = checkValidPlacement(problem.getItems().get(genome[g]), l, c);
                                if (existeEspaço == true) {
                                    placement(problem.getItems().get(genome[g]), l, c);
                                    break;
                                }
                            }

                            if (existeEspaço == true) {
                                break;
                            }

                        }
                    }

                    //corte -> comparar peças, se não forem iguais, faz-se o corte
                    tamanhoColunas++;
                    //corte vertical
                    for (int i = 0; i < tamanhoLinhas; i++) {
                        for (int j = 0; j < tamanhoColunas - 1; j++) {
                            if (material[i][j] != material[i][j + 1])
                                quantidadeCortes++;
                        }
                    }

                    //corte horizontal
                    for (int i = 0; i < tamanhoLinhas - 1; i++) {
                        for (int j = 0; j < tamanhoColunas; j++) {
                            if (material[i][j] != material[i + 1][j])
                                quantidadeCortes++;
                        }
                    }

                    quantidadeCortes += tamanhoLinhas; //corte final: um corte no fim de cada linha
                    System.out.println("Cortes: " + quantidadeCortes);
                    this.fitness = tamanhoColunas * 0.7 + quantidadeCortes * 0.3;
                    return this.fitness;
                }


                private boolean checkValidPlacement(Item item, int lineIndex, int columnIndex) {
                    int[][] itemMatrix = item.getMatrix();
                    for (int i = 0; i < itemMatrix.length; i++) {
                        for (int j = 0; j < itemMatrix[i].length; j++) {
                            if (itemMatrix[i][j] != 0) {
                                if ((lineIndex + i) >= material.length
                                        || (columnIndex + j) >= material[0].length
                                        || material[lineIndex + i][columnIndex + j] != 0) {
                                    return false;
                                }
                            }
                        }
                    }
                    return true;
                }

                private int[][] placement(Item item, int lineIndex, int columnIndex) {
                    //TODO - DONE
                    int[][] itemMatrix = item.getMatrix();
                    for (int i = 0; i < itemMatrix.length; i++) {
                        for (int j = 0; j < itemMatrix[i].length; j++) {
                            if (itemMatrix[i][j] != 0) {
                                material[lineIndex + i][columnIndex + j] = item.getRepresentation();//colocar o caracter na matriz do material
                                if (tamanhoColunas < columnIndex + j) {//se estiver dentro dos limites o tamanho da coluna é igual ao indice da coluna da matriz + j = tamanho da peça
                                    tamanhoColunas = columnIndex + j;//equivale a ultima coluna disponivel
                                }
                            }
                        }
                    }
                    return itemMatrix;
                }

                @Override
                public String toString() {
                    StringBuilder sb = new StringBuilder();
                    sb.append("fitness: ");
                    sb.append(fitness);
                    sb.append("\n");

                    for (int i = 0; i < this.genome.length; ++i) {
                        sb.append(this.genome[i]).append(" ");
                    }
                    sb.append("\n");

                    for (int i = 0; i < problem.getMaterialHeight(); i++) {
                        for (int j = 0; j < problem.getMaterialWidth(); j++) {

                            if (material[i][j] != 0) {
                                sb.append((char) material[i][j]);
                            } else {
                                sb.append(" ");
                            }
                        }
                        sb.append("\n");
                    }

                    sb.append("Número de colunas: " + tamanhoColunas);
                    sb.append("\n");
                    sb.append("Número de cortes: " + this.quantidadeCortes);

                    return sb.toString();
                }

                /**
                 * @param i
                 * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
                 * 0, otherwise.
                 */
                @Override
                public int compareTo(StockingProblemIndividual i) {
                    return (this.fitness == i.getFitness()) ? 0 : (this.fitness < i.getFitness()) ? 1 : -1;
                }

                @Override
                public StockingProblemIndividual clone() {
                    return new StockingProblemIndividual(this);

                }
            }
            for (int j = 0; j < itemMatrix[i].length; j++) {
                if (itemMatrix[i][j] != 0) {
                    material[lineIndex + i][columnIndex + j] = item.getRepresentation();
                    if (tamanhoColunas < columnIndex + j) {
                        tamanhoColunas = columnIndex + j;
                    }
                }
            }
        }
        return itemMatrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fitness: ");
        sb.append(fitness);
        sb.append("\n");

        for (int i = 0; i < this.genome.length; ++i) {
            sb.append(this.genome[i]).append(" ");
        }
        sb.append("\n");

        for (int i = 0; i < problem.getMaterialHeight(); i++) {
            for (int j = 0; j < problem.getMaterialWidth(); j++) {

                if (material[i][j] != 0) {
                    sb.append((char) material[i][j]);
                } else {
                    sb.append(" ");
                }
/*
               if(j+1 <= problem.getMaterialWidth()-1){
                    if(material[i][j] != material[i][j+1]){
                        sb.append("|");
                    }
                }

                if(i+1 <= problem.getMaterialHeight()-1){
                    if(material[i][j] != material[i+1][j]){
                        sb.append("-");
                    }
                }*/

            }
            sb.append("\n");
        }

        sb.append("Número de colunas: " + tamanhoColunas);
        //System.out.println("Coiso: "+problem.getItems().size());
        sb.append("\n");
        sb.append("Número de cortes: " + this.quantidadeCortes);

        return sb.toString();
    }

    /**
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(StockingProblemIndividual i) {
        return (this.fitness == i.getFitness()) ? 0 : (this.fitness < i.getFitness()) ? 1 : -1;
    }

    @Override
    public StockingProblemIndividual clone() {
        return new StockingProblemIndividual(this);

    }
}