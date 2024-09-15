package agendaContatos;

import java.util.Scanner;

// Classe que representa um nó na lista encadeada
class Contato {
    String nome;
    String endereco;
    String telefone;
    Contato proximo;

    Contato(String nome, String endereco, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.proximo = null;
    }
}

// Classe Agenda que contém as operações para gerenciar a lista de contatos
class Agenda {
    Contato primeiro;

    Agenda() {
        primeiro = null;
    }

    // Método para adicionar um novo contato
    void adicionarContato(String nome, String endereco, String telefone) {
        Contato novoContato = new Contato(nome, endereco, telefone);
        if (primeiro == null) {
            primeiro = novoContato;
        } else {
            Contato atual = primeiro;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novoContato;
        }
    }

    // Método para listar todos os contatos
    void listarContatos() {
        if (primeiro == null) {
            System.out.println("Agenda vazia.");
            return;
        }
        Contato atual = primeiro;
        int index = 0;
        while (atual != null) {
            System.out.println("Código: " + index + " Nome: " + atual.nome + " Endereço: " + atual.endereco + " Telefone: " + atual.telefone);
            atual = atual.proximo;
            index++;
        }
    }

    // Método para buscar um contato por nome
    Contato buscarPorNome(String nomePesquisa) {
        Contato atual = primeiro;
        while (atual != null) {
            if (atual.nome.equalsIgnoreCase(nomePesquisa)) {
                return atual;
            }
            atual = atual.proximo;
        }
        return null;
    }

    // Método para buscar um contato por código (posição na lista)
    Contato buscarPorCodigo(int codigo) {
        Contato atual = primeiro;
        int index = 0;
        while (atual != null) {
            if (index == codigo) {
                return atual;
            }
            atual = atual.proximo;
            index++;
        }
        return null;
    }

    // Método para remover um contato por nome
    void removerContato(String nomeExcluir) {
        if (primeiro == null) {
            System.out.println("Agenda vazia.");
            return;
        }

        if (primeiro.nome.equalsIgnoreCase(nomeExcluir)) {
            primeiro = primeiro.proximo;
            System.out.println("Contato removido com sucesso.");
            return;
        }

        Contato atual = primeiro;
        while (atual.proximo != null && !atual.proximo.nome.equalsIgnoreCase(nomeExcluir)) {
            atual = atual.proximo;
        }

        if (atual.proximo != null) {
            atual.proximo = atual.proximo.proximo;
            System.out.println("Contato removido com sucesso.");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Agenda agenda = new Agenda();

        int opcao;
        String continuar;

        do {
            System.out.println("Escolha a opção: 1-Incluir  2-Listar  3-Excluir  4-Sair");
            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    // Adicionar novo contato
                    do {
                        System.out.print("Digite o nome: ");
                        String nome = entrada.nextLine();

                        System.out.print("Digite o endereço: ");
                        String endereco = entrada.nextLine();

                        System.out.print("Digite o telefone: ");
                        String telefone = entrada.nextLine();

                        agenda.adicionarContato(nome, endereco, telefone);

                        System.out.print("Deseja continuar o cadastramento? 1-Sim  2-Não ");
                        continuar = entrada.nextLine();
                    } while (continuar.equals("1"));
                    break;

                case 2:
                    // Listar ou buscar contatos
                    System.out.println("Como deseja pesquisar? 1-Código  2-Nome  3-Todos");
                    int listarPor = entrada.nextInt();
                    entrada.nextLine();

                    switch (listarPor) {
                        case 1:
                            System.out.print("Digite o código para pesquisa: ");
                            int codigoPesquisa = entrada.nextInt();
                            entrada.nextLine();
                            Contato contatoCodigo = agenda.buscarPorCodigo(codigoPesquisa);
                            if (contatoCodigo != null) {
                                System.out.println("Nome: " + contatoCodigo.nome + " Endereço: " + contatoCodigo.endereco + " Telefone: " + contatoCodigo.telefone);
                            } else {
                                System.out.println("Código Inválido!");
                            }
                            break;

                        case 2:
                            System.out.print("Digite o nome para pesquisa: ");
                            String nomePesquisa = entrada.nextLine();
                            Contato contatoNome = agenda.buscarPorNome(nomePesquisa);
                            if (contatoNome != null) {
                                System.out.println("Nome: " + contatoNome.nome + " Endereço: " + contatoNome.endereco + " Telefone: " + contatoNome.telefone);
                            } else {
                                System.out.println("Contato não encontrado.");
                            }
                            break;

                        case 3:
                            // Listar todos os contatos
                            agenda.listarContatos();
                            break;

                        default:
                            System.out.println("Opção inválida! Escolha números de 1 a 3");
                            break;
                    }
                    break;

                case 3:
                    // Excluir contato
                    System.out.print("Digite o nome do contato a ser excluído: ");
                    String nomeExcluir = entrada.nextLine();
                    agenda.removerContato(nomeExcluir);
                    break;

                case 4:
                    // Sair do programa
                    System.out.println("Programa Finalizado.");
                    return;

                default:
                    System.out.println("Opção Inválida! Tente novamente.");
                    break;
            }
        } while (opcao != 4);

        entrada.close();
    }
}

