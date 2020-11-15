import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("\nIESB - IA2 - PROGRAMA MOCHILA - GABRIEL IBAÑEZ - 1812130004\n");
        Scanner scanner1 = new Scanner(System.in);

        System.out.println("APRESENTAÇÃO DO PROBLEMA DA MOCHILA:");
        System.out.println("\tHá uma população com p indivíduos e cada um tem uma mochila com capacidade máxima de L litros.");
        System.out.println("\tExistem à diposição N objetos, cada um com seu Volume V(N) e seu preço P(N).");
        System.out.println("\tRegra 1: cada indivíduo escolhe o que irá colocar em sua mochila conforme sua preferência.");
        System.out.println("\tRegra 2: a preferência de cada indivíduo é determinada aleatoriamente.");
        System.out.println("\tRegra 3: cada indivíduo pode pegar nenhum ou todos os objetos (sem repetição).");
        System.out.println("\tRegra 4: não é permitido ultrapassar a capacidade máxima da mochila.");
        System.out.println("\tRegra 5: é permitido o cruzamento entre os indivídos, criando indivíduos com novas preferências.");
        System.out.println("\tOBJETIVO: Encontrar o indivíduo ou descendente que carregue a mochila com o maior valor total P.");

        System.out.println("\nSobre o método utilizado para a reprodução:");
        System.out.println("\tEtapa 1) Aplica-se a função avaliação (relacionado ao volume) para definir os aprovados");
        System.out.println("\tEtapa 2) Dividir o grupo dos aprovados em 2 dois grupos: A e B");
        System.out.println("\t\t (Se o nº de indivíduos for ímpar, desconsiderar o último)");
        System.out.println("\tEtapa 3) Cruzar o 1º elemento do grupo A com o 1º elemento do grupo B");
        System.out.println("\t\t\t Cruzar o 2º elemento do grupo A com o 2º elemento do grupo B");
        System.out.println("\t\t\t Para cruzar, divide-se igualmente os cromossomos dos 2 indivíduos aleatoriamente em 3 partes: p1, p2 e p3");
        System.out.println("\t\t\t\t Troca-se p1 do indivíduo 1 com p1 do indivíduo 2 e");
        System.out.println("\t\t\t\t Troca-se p3 do indivíduo 1 com p3 do indivíduo 2");
        System.out.println("\t\t\t\t (Seguir até todos os indivíduos se cruzarem)");
        System.out.println("\tObservações:");
        System.out.println("\t\t- A nova população gerada terá o mesmo nº de indivíduos da população que foi cruzada.");
        System.out.println("\t\t- À essa nova população, aplica-se a função de avaliação, e ordenamento");
        System.out.println("\t\t- Os indiviíduos aprovados é que participarão da nova reprodução.");

//        System.out.println("\n\tPara o exercício em questão, podemos definir cada uma das variáveis ou usar os valores abaixo:");
        System.out.println("\n\tPara o exercício em questão, iremos usar os valores abaixo:");
        System.out.println("\tCapacidade da mochila: L = 130 litros.");

//        System.out.print("\nDeseja definir o valor das variáveis (1) ou usar os valores apresentados acima (2): ");
//        int opcao1 = scanner1.nextInt();
    int opcao1 = 2;

        Objeto o;
        ArrayList<Objeto> listaDeObjetos = new ArrayList<>();
        int capacidade = 0;

        if (opcao1 == 1) {
            System.out.print("\nDigite a capacidade da mochila (em litros): ");
            capacidade = scanner1.nextInt();
            System.out.print("Digite o número de objetos disponíveis:: ");
            int objetosDisponiveis = scanner1.nextInt();
            for (int i = 0; i < objetosDisponiveis; i++) {
                o = new Objeto();
                System.out.print("\nDigite o nome do " + (i + 1) + "º objeto: ");
                String nome = scanner1.next();
                o.setNome(nome);

                System.out.print("Digite o volume do " + (i + 1) + "º objeto: ");
                int volumeObjeto = scanner1.nextInt();
                o.setVolume(volumeObjeto);

                System.out.print("Digite o valor do " + (i + 1) + "º objeto (em R$ inteiros): ");
                int valorObjeto = scanner1.nextInt();
                o.setValor(valorObjeto);

                listaDeObjetos.add(o);
            }

            System.out.print("\n\nLISTA DE OBJETOS: ");
            for (int i = 0; i < listaDeObjetos.size(); i++) {

                System.out.print("\nObjeto nº " + (i + 1) + ": " + listaDeObjetos.get(i).getNome() + "\t Volume: " +
                        listaDeObjetos.get(i).getVolume() + "\t Valor:  R$" + listaDeObjetos.get(i).getValor()+",00");

            }
                System.out.print("\n\nEstá correto? sim(1) \t não(2): ");
                int opcao2 = 1; //scanner1.nextInt();

        } else {
            if (opcao1 == 2) {
                capacidade = 130;
                //capacidade = 100;
                //capacidade = 80;
                o = new Objeto();

                o.setNome("A");
                o.setVolume(10);
                o.setValor(15);
                listaDeObjetos.add(o);

                o = new Objeto();
                o.setNome("B");
                o.setVolume(40);
                o.setValor(90);
                listaDeObjetos.add(o);

                o = new Objeto();
                o.setNome("C");
                o.setVolume(26);
                o.setValor(5);
                listaDeObjetos.add(o);

                o = new Objeto();
                o.setNome("D");
                o.setVolume(32);
                o.setValor(60);
                listaDeObjetos.add(o);

                o = new Objeto();
                o.setNome("E");
                o.setVolume(18);
                o.setValor(12);
                listaDeObjetos.add(o);

                o = new Objeto();
                o.setNome("F");
                o.setVolume(4);
                o.setValor(179);
                listaDeObjetos.add(o);

                o = new Objeto();
                o.setNome("G");
                o.setVolume(12);
                o.setValor(33);
                listaDeObjetos.add(o);

                o = new Objeto();
                o.setNome("H");
                o.setVolume(21);
                o.setValor(6);
                listaDeObjetos.add(o);

                o = new Objeto();
                o.setNome("I");
                o.setVolume(4);
                o.setValor(6);
                listaDeObjetos.add(o);


                o = new Objeto();
                o.setNome("J");
                o.setVolume(7);
                o.setValor(19);
                listaDeObjetos.add(o);

                o = new Objeto();
                o.setNome("L");
                o.setVolume(4);
                o.setValor(2);
                listaDeObjetos.add(o);

                o = new Objeto();
                o.setNome("L");
                o.setVolume(2);
                o.setValor(6);
                listaDeObjetos.add(o);

                o = new Objeto();
                o.setNome("M");
                o.setVolume(18);
                o.setValor(31);
                listaDeObjetos.add(o);


                o = new Objeto();
                o.setNome("N");
                o.setVolume(1);
                o.setValor(1);
                listaDeObjetos.add(o);

                for (int i = 0; i < listaDeObjetos.size(); i++) {
                    System.out.println("\tObjeto nº " + (i + 1) + ": \t" + listaDeObjetos.get(i).getNome() + "\t Volume: " +
                            listaDeObjetos.get(i).getVolume() + "\t Valor: " + listaDeObjetos.get(i).getValor());
                }
//                System.out.print("\n\nEstá correto? sim(1) \t não(2): ");
//                //int opcao2 = scanner1.nextInt();
//                int opcao2 = 1;
            }
        }

        System.out.print("\n\nDigite o número de indivíduos da população inicial: ");
        int numeroDeIndividuos = scanner1.nextInt();
        CriaPopulacao p1 = new CriaPopulacao(listaDeObjetos,capacidade);
        p1.criar(numeroDeIndividuos);


        do {
        p1.mostrarPopulacao();
        p1.cruzar();

        }
        while (p1.fim == false);




        System.err.print("\n\nFim!");

    }
}