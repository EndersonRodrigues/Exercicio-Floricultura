
package br.inatel.c07;

import java.util.List;

// Classe principal: executa os testes das funcionalidades.
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Floricultura (Java + MySQL) ===");

        PlantaDAO dao = new PlantaDAO();

        // Questão 1: listar plantas cadastradas inicialmente.
        mostrar("1. Plantas cadastradas (esperado: 5):", dao.listarPlantas());

        // Questão 2: inserir uma nova planta.
        boolean inseriu = dao.inserirPlanta(
                new Planta("Lavanda", "Lavanda", 15.00, 25)
        );

        System.out.println("\n2. Planta inserida? (esperado: true): " + inseriu);

        // Questão 3: buscar plantas pela espécie.
        mostrar("3. Plantas da espécie Rosa (esperado: 2):",
                dao.buscarPorEspecie("Rosa"));

        // Questão 4: atualizar o preço do Girassol.
        System.out.println("\n4. Preço do Girassol atualizado? (esperado: true): "
                + dao.atualizarPreco(3, 7.50));

        // Questão 5: remover uma planta.
        System.out.println("5. Samambaia removida? (esperado: true): "
                + dao.removerPlanta(5));

        // Testar a remoção de uma planta inexistente.
        System.out.println("6. Planta 99 removida? (esperado: false): "
                + dao.removerPlanta(99));

        // Questão 6: calcular o valor total do estoque.
        System.out.println("\n7. Valor total do estoque (esperado: 1705.0): "
                + dao.valorTotalEstoque());

        // Exibir a lista final.
        mostrar("8. Lista final (esperado: 5 plantas):",
                dao.listarPlantas());
    }

    // Método auxiliar para mostrar uma lista de plantas.
    private static void mostrar(String titulo, List<Planta> plantas) {

        System.out.println("\n" + titulo);

        for (Planta p : plantas) {
            System.out.println("  " + p);
        }
    }
}
