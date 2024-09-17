import java.util.Scanner;  // Importa a classe Scanner para capturar entrada do usuário

public class SistemaAprovacao_1 {
    public static void main(String[] args) {
        // Cria um objeto Scanner para capturar a entrada do usuário
        Scanner scanner = new Scanner(System.in);
        
        // Cria um array de 4 posições para armazenar as notas
        double[] notas = new double[4];
        
        // Variável para acumular a soma das notas
        double soma = 0.0;

        // Coleta as notas de 4 disciplinas
        for (int i = 0; i < notas.length; i++) {
            System.out.println("Digite a nota da disciplina " + (i + 1) + ": ");
            notas[i] = scanner.nextDouble();  // Captura a nota inserida pelo usuário
            soma += notas[i];  // Adiciona a nota à soma total
        }

        // Calcula a média das notas
        double media = soma / notas.length;

        // Verifica se o aluno tem direito ao bônus (todas as notas acima de 9)
        boolean todasAcimaDeNove = true;  // Presume inicialmente que todas as notas são acima de 9
        for (double nota : notas) {
            // Se alguma nota for menor ou igual a 9, define que nem todas são acima de 9
            if (nota <= 9) {
                todasAcimaDeNove = false;
                break;  // Sai do loop, pois já encontrou uma nota menor ou igual a 9
            }
        }

        // Se todas as notas forem acima de 9, aplica o bônus de 10% na média
        if (todasAcimaDeNove) {
            media += media * 0.1;  // Aumenta a média em 10%
        }

        // Exibe o resultado da aprovação ou reprovação com base na média final
        if (media >= 7) {
            System.out.println("Aprovado! Média: " + media);  // Aluno aprovado
        } else if (media >= 5) {
            System.out.println("Recuperação. Média: " + media);  // Aluno em recuperação
        } else {
            System.out.println("Reprovado. Média: " + media);  // Aluno reprovado
        }

        // Fecha o objeto Scanner para liberar os recursos
        scanner.close();
    }
}
