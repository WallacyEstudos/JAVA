import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Classe Item
class Item {
    String nome;
    int quantidade;

    Item(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return nome + ": " + quantidade;
    }
}

public class SistemaEstoque_6 {
    private static final String ARQUIVO_ESTOQUE = "estoque.txt";
    private ArrayList<Item> estoque = new ArrayList<>();

    public SistemaEstoque_6() {
        carregarEstoque();
    }

    // Adicionar item
    public void adicionarItem(String nome, int quantidade) {
        estoque.add(new Item(nome, quantidade));
        salvarEstoque();
        System.out.println("Item adicionado com sucesso!");
    }

    // Remover item
    public void removerItem(String nome) {
        boolean removido = estoque.removeIf(item -> item.nome.equalsIgnoreCase(nome));
        if (removido) {
            salvarEstoque();
            System.out.println("Item removido com sucesso!");
        } else {
            System.out.println("Item não encontrado!");
        }
    }

    // Listar itens
    public void listarItens() {
        if (estoque.isEmpty()) {
            System.out.println("Nenhum item no estoque.");
        } else {
            for (Item item : estoque) {
                System.out.println(item);
            }
        }
    }

    // Carregar estoque de arquivo
    private void carregarEstoque() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ESTOQUE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(":");
                String nome = partes[0].trim();
                int quantidade = Integer.parseInt(partes[1].trim());
                estoque.add(new Item(nome, quantidade));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de estoque não encontrado. Um novo será criado.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar estoque: " + e.getMessage());
        }
    }

    // Salvar estoque em arquivo
    private void salvarEstoque() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ESTOQUE))) {
            for (Item item : estoque) {
                writer.write(item.nome + ": " + item.quantidade);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar estoque: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SistemaEstoque_6 sistema = new SistemaEstoque_6();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Adicionar Item");
            System.out.println("2. Remover Item");
            System.out.println("3. Listar Itens");
            System.out.println("4. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do item:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite a quantidade:");
                    int quantidade = scanner.nextInt();
                    sistema.adicionarItem(nome, quantidade);
                    break;
                case 2:
                    System.out.println("Digite o nome do item para remover:");
                    nome = scanner.nextLine();
                    sistema.removerItem(nome);
                    break;
                case 3:
                    sistema.listarItens();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
