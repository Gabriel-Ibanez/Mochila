import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.StrictMath.round;

public class CriaPopulacao {

    Scanner scanner2 = new Scanner(System.in);
    boolean fim = false; // essa variável vai indicar o fim do ciclo de reproduções
    ArrayList<Objeto> listaDeObjetos;
    ArrayList<Individuo> listaDeIndividuos = new ArrayList<>();
    ArrayList<Individuo> listaDeAprovados = new ArrayList<>();

    ArrayList<Integer[]> listaDeResultados = new ArrayList<Integer[]>();

    int capacidade = 0, contadorDeCruzamento = 0;
    Individuo melhorIndividuoDaPopulacao = new Individuo();
    Individuo melhorIndividuoAteAgora = new Individuo();
    int geracaoCampea = 0;


    public CriaPopulacao(ArrayList<Objeto> listaDeObjetos, int capacidade) {
        this.listaDeObjetos = listaDeObjetos;
        this.capacidade = capacidade;
    }

    private int funcaoAvaliacaoVolume(Individuo individuo1) {
        int volumeTotal = 0;
        for (int v = 0; v < listaDeObjetos.size(); v++) {
            volumeTotal = volumeTotal + individuo1.getVetorDeEscolha()[v] * listaDeObjetos.get(v).getVolume();
        }
        return volumeTotal;
    }

    private int funcaoAvaliacaoValor(Individuo individuo1) {
        int valorTotal = 0;
        for (int v = 0; v < listaDeObjetos.size(); v++) {
            valorTotal = valorTotal + individuo1.getVetorDeEscolha()[v] * listaDeObjetos.get(v).getValor();
        }
        return valorTotal;
    }
    Integer[] dadosPopulacao = new Integer[5];
    int id = 0;
    Random random = new Random();

    void criar(int numeroDeIndividuos) {

        for (int p = 0; p < numeroDeIndividuos; p++) {
            Individuo novoIndividuo = new Individuo();
            id++;
            novoIndividuo.setId(id);
            melhorIndividuoAteAgora.setValorTotal(0);
            int[] vetorAuxiliar = new int[listaDeObjetos.size()];
            for (int k = 0; k < listaDeObjetos.size(); k++) {
                vetorAuxiliar[k] = (random.nextInt(2));
            }
            novoIndividuo.setVetorDeEscolha(vetorAuxiliar);
            novoIndividuo.setVolumeTotal(funcaoAvaliacaoVolume(novoIndividuo));
            novoIndividuo.setValorTotal(funcaoAvaliacaoValor(novoIndividuo));
            listaDeIndividuos.add(novoIndividuo);
        }

        dadosPopulacao[0] = 0;
        dadosPopulacao[1] = 0;
        dadosPopulacao[2] = 0;
        dadosPopulacao[3] = 0;
        dadosPopulacao[4] = 0;
        listaDeResultados.add(dadosPopulacao);
    }

    void mostrarPopulacao() {
        contadorDeCruzamento++;
        // *****************
        System.out.print("\nDigite qualquer tecla para mostrar a população nº " + contadorDeCruzamento + ": ");
        int pausa = scanner2.nextInt();
        // *****************
        System.out.print("Nº\t\tID \t\tVOLUME\tVALOR \tOBJETOS SELECIONADOS:\n");
        int maiorValorDaPopulacao = 0;
        Individuo individuoAuxiliar = new Individuo();
        individuoAuxiliar.setValorTotal(0);
//        melhorIndividuoDaPopulacao.setId(-1);
//        melhorIndividuoDaPopulacao.setVolumeTotal(-1);
//        melhorIndividuoDaPopulacao.setValorTotal(-1);

        for (int l = 0; l < listaDeIndividuos.size(); l++) {
            System.out.print((l + 1) + "\t\t");
            System.out.print(listaDeIndividuos.get(l).getId() + "\t\t");
            System.out.print(listaDeIndividuos.get(l).getVolumeTotal() + "\t\t" + listaDeIndividuos.get(l).getValorTotal() + "\t\t");
            int[] vetorAuxiliar2 = new int[listaDeObjetos.size()];
            vetorAuxiliar2 = listaDeIndividuos.get(l).getVetorDeEscolha();
            for (int c = 0; c < listaDeObjetos.size(); c++) {
                System.out.print(vetorAuxiliar2[c] + "\t");
            }
            System.out.print("\n");
            if (l == round(listaDeIndividuos.size() / 2) - 1) {
                System.out.print("---------------------------%----------------------------\n");
            }
        }

        listaDeAprovados.clear();

        for (int k = 0; k < listaDeIndividuos.size(); k++) {
            if (listaDeIndividuos.get(k).getVolumeTotal() <= capacidade) {
                listaDeAprovados.add(listaDeIndividuos.get(k));
                if (listaDeIndividuos.get(k).getValorTotal() > melhorIndividuoDaPopulacao.getValorTotal()) { // verificador do melhor indivíduo da população
                    melhorIndividuoDaPopulacao = listaDeIndividuos.get(k);
                    if (melhorIndividuoDaPopulacao.getValorTotal() > melhorIndividuoAteAgora.getValorTotal()){
                        melhorIndividuoAteAgora.setId(melhorIndividuoDaPopulacao.getId());
                        melhorIndividuoAteAgora.setValorTotal(melhorIndividuoDaPopulacao.getValorTotal());
                        melhorIndividuoAteAgora.setVolumeTotal(melhorIndividuoDaPopulacao.getVolumeTotal());
                        melhorIndividuoAteAgora.setVetorDeEscolha(melhorIndividuoDaPopulacao.getVetorDeEscolha());
                        geracaoCampea = contadorDeCruzamento;
                    }

                }
            }
        }

        dadosPopulacao = new Integer[5];
        dadosPopulacao[0] = listaDeIndividuos.size();
        dadosPopulacao[1] = listaDeAprovados.size();
        dadosPopulacao[2] = melhorIndividuoDaPopulacao.getId();
        dadosPopulacao[3] = melhorIndividuoDaPopulacao.getVolumeTotal();
        dadosPopulacao[4] = melhorIndividuoDaPopulacao.getValorTotal();

        listaDeResultados.add(contadorDeCruzamento,dadosPopulacao);

        for (int k = 1; k < listaDeResultados.size(); k++) {
            if (melhorIndividuoDaPopulacao.getVolumeTotal() > listaDeIndividuos.get(0).getVolumeTotal()) {
                listaDeResultados.set(0, dadosPopulacao);
            }
        }

        listaDeAprovados = ordenar(listaDeAprovados);

        // *****************
        System.out.print("\nDigite qualquer tecla para mostrar os aprovadados da população nº "+ contadorDeCruzamento + " por ordem de descrescente de valor: ");
        Scanner scanner2 = new Scanner(System.in);
        String resposta2 = scanner2.nextLine();
        // *****************


        System.out.println("\nLista de Aprovados por ordem descrescente de valor:");
        System.out.print("Nº\t\tID \t\t\tVOLUME\tVALOR \tOBJETOS SELECIONADOS:\n");

        for (int l = 0; l < listaDeAprovados.size(); l++) {
            System.out.print((l+1) + "\t\t");
            System.out.print(listaDeAprovados.get(l).getId()+ "\t\t\t");
            System.out.print(listaDeAprovados.get(l).getVolumeTotal()+ "\t\t" + listaDeAprovados.get(l).getValorTotal()+ "\t\t");
            int[] vetorAuxiliar2 = new int[listaDeObjetos.size()];
            vetorAuxiliar2 = listaDeAprovados.get(l).getVetorDeEscolha();
            for (int c = 0; c < listaDeObjetos.size(); c++){
                System.out.print(vetorAuxiliar2[c]+"\t");
            }
            System.out.print("\n");
            if (l == round (listaDeAprovados.size()/2)-1){
                System.out.print("---------------------------%----------------------------\n");
            }
        }

        int contadorRepetidos = 0;
        System.out.println("\nRESULTADOS:");
        System.out.println("POPULAÇÃO \tQUANTIDADE \tAPROVADOS \tID_MELHOR \tVOLUME \tVALOR");
        for (int k = 1; k < listaDeResultados.size(); k++) {
            Integer[] dadosPopulacaoAux = new Integer[5];
            System.out.print((k)+"\t");
            dadosPopulacaoAux = listaDeResultados.get(k);
            System.out.print("\t\t" +dadosPopulacaoAux[0]+"\t\t\t");
            System.out.print(dadosPopulacaoAux[1]+"\t\t\t");
            System.out.print(dadosPopulacaoAux[2]+"\t\t\t");
            System.out.print(dadosPopulacaoAux[3]+"\t\t");
            System.out.print(dadosPopulacaoAux[4]+"\n");
            if (dadosPopulacaoAux[0] == dadosPopulacaoAux[1]){
                contadorRepetidos++;
            } else contadorRepetidos = 0;
            if (contadorRepetidos == 30) {   // aqui se define o critério de parada: número de gerações seguidas em que
                fim = true;                 // a quantidade de aprovados é igual a quantidade da população
            }
        }

        System.out.print("MELHOR INDIVÍDUO ATÉ O MOMENTO:");

        System.out.print("\t\t"+ melhorIndividuoAteAgora.getId()+ "\t\t\t");
        System.out.print(melhorIndividuoAteAgora.getVolumeTotal()+ "\t\t");
        System.out.print(melhorIndividuoAteAgora.getValorTotal()+ "\t\t");
        System.out.print("(Geração nº "+ geracaoCampea+")\t QTD = APR: "+ contadorRepetidos+" \n");
        if (fim) System.out.print("\t==FIM==");

    }

    ArrayList<Individuo> ordenar(ArrayList<Individuo> listaDeAprovados){
        Individuo individuoAuxiliar = new Individuo();
        for (int k = 0; k < listaDeAprovados.size() - 1; k++) {
            for (int t = 0; t < listaDeAprovados.size() - k - 1; t++) {
                if (listaDeAprovados.get(t).getValorTotal() < listaDeAprovados.get(t + 1).getValorTotal()) {
                    individuoAuxiliar = listaDeAprovados.get(t);
                    listaDeAprovados.set((t), listaDeAprovados.get(t + 1));
                    listaDeAprovados.set((t + 1), individuoAuxiliar);
                }
            }
        }
        return listaDeAprovados;
    }




    void cruzar() {
        int contMutacao = 0;
        Random random2 = new Random();
        int p1 = 1 + random2.nextInt(listaDeObjetos.size()-2);
        int p2 = 1 + random2.nextInt(listaDeObjetos.size() - p1-1);
        int p3 = listaDeObjetos.size()-p1-p2;

        if (fim){

        } else
        {

        System.out.println("\nCruzando...");
        System.out.println("Divisão sorteada para esse cruzamento : p1 = "+ p1 +"\t\tp2 = " + p2+ "\t\tp3 = " + p3);
        }

        int vet1[] = new int[p1];
        int vet2[] = new int[p2];
        int vet3[] = new int[p3];
        int vetTotalAux1[] = new int[p1+p2+p3];
        int vetTotalAux2[] = new int[p1+p2+p3];
        int vetTotal[] = new int[p1+p2+p3];


        int numeroDeAprovados  = listaDeAprovados.size();
        if (numeroDeAprovados%2 == 1) numeroDeAprovados--;

        ArrayList<Individuo> metade1 = new ArrayList<>();
        ArrayList<Individuo> metade2 = new ArrayList<>();

        ArrayList<Individuo> novaLista = new ArrayList<>();

        Individuo i1 = new Individuo();


        listaDeIndividuos.clear();

        for (int t = 0; t < numeroDeAprovados/2; t++) {
            metade1.add(listaDeAprovados.get(t));
            metade2.add(listaDeAprovados.get(t + (numeroDeAprovados / 2)));
        }

        for (int t = 0; t < numeroDeAprovados/2; t++){
            vetTotalAux1 = metade1.get(t).getVetorDeEscolha();
            vetTotalAux2 = metade2.get(t).getVetorDeEscolha();

            int mutacao = random2.nextInt(100);

            for (int t1 = 0; t1 < p1; t1++) {
                vet1[t1] = vetTotalAux2[t1];
                vetTotalAux2[t1] = vetTotalAux1[t1];
                vetTotalAux1[t1] = vet1[t1];

                if (mutacao < 5) {
                    contMutacao++;
                    vet1[t1] = vetTotalAux2[t1];
                    vetTotalAux2[t1] = vetTotalAux1[t1];
                    vetTotalAux1[t1] = vet1[t1];
                }
            }

            for (int t1 = 0; t1 < p3; t1++) {
                vet3[t1] = vetTotalAux2[t1 + p1 + p2];
                vetTotalAux2[t1 + p1 + p2] = vetTotalAux1[t1 + p1 + p2];
                vetTotalAux1[t1 + p1 + p2] = vet3[t1];
            }

            metade1.get(t).setVetorDeEscolha(vetTotalAux1);
            metade2.get(t).setVetorDeEscolha(vetTotalAux2);

        }

        for (int t = 0; t < numeroDeAprovados/2; t++){
            id++;
            metade1.get(t).setId(id);
            metade1.get(t).setVolumeTotal(funcaoAvaliacaoVolume(metade1.get(t)));
            metade1.get(t).setValorTotal(funcaoAvaliacaoValor(metade1.get(t)));
            listaDeIndividuos.add(metade1.get(t));
        }

        for (int t = 0; t < numeroDeAprovados/2; t++){
            id++;
            metade2.get(t).setId(id);
            metade2.get(t).setVolumeTotal(funcaoAvaliacaoVolume(metade2.get(t)));
            metade2.get(t).setValorTotal(funcaoAvaliacaoValor(metade2.get(t)));
            listaDeIndividuos.add(metade2.get(t));
        }
        if (listaDeIndividuos.size() == 0) {
            fim = true;
        } else {
            if (fim) {

            } else{
        System.out.println("\nFinalizado! " + listaDeIndividuos.size() + " novos indivíduos criados! Nº de mutações: "+ contMutacao);
        listaDeAprovados.clear();
            }
        }
     }
}

