
package br.inatel.c07;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Classe responsável pelas operações no banco de dados.
public class PlantaDAO extends ConnectionDAO {

    // Questão 1: inserir uma planta.
    public boolean inserirPlanta(Planta planta) {
        connectToDb();

        String sql = "INSERT INTO planta (nome, especie, preco, estoque) VALUES (?, ?, ?, ?)";

        try {
            if (connection == null) {
                return false;
            }

            pst = connection.prepareStatement(sql);

            pst.setString(1, planta.getNome());
            pst.setString(2, planta.getEspecie());
            pst.setDouble(3, planta.getPreco());
            pst.setInt(4, planta.getEstoque());

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir planta: " + e.getMessage());
            return false;

        } finally {
            fecharRecursos();
        }
    }

    // Questão 2: listar todas as plantas, ordenadas pelo id.
    public List<Planta> listarPlantas() {
        List<Planta> plantas = new ArrayList<>();

        connectToDb();

        String sql = "SELECT * FROM planta ORDER BY id";

        try {
            if (connection == null) {
                return plantas;
            }

            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                plantas.add(new Planta(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especie"),
                        rs.getDouble("preco"),
                        rs.getInt("estoque")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar plantas: " + e.getMessage());

        } finally {
            fecharRecursos();
        }

        return plantas;
    }

    // Questão 3: buscar plantas de uma determinada espécie.
    public List<Planta> buscarPorEspecie(String especie) {
        List<Planta> plantas = new ArrayList<>();

        connectToDb();

        String sql = "SELECT * FROM planta WHERE especie = ?";

        try {
            if (connection == null) {
                return plantas;
            }

            pst = connection.prepareStatement(sql);
            pst.setString(1, especie);

            rs = pst.executeQuery();

            while (rs.next()) {
                plantas.add(new Planta(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especie"),
                        rs.getDouble("preco"),
                        rs.getInt("estoque")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar planta: " + e.getMessage());

        } finally {
            fecharRecursos();
        }

        return plantas;
    }

    // Questão 4: atualizar o preço de uma planta pelo id.
    public boolean atualizarPreco(int id, double novoPreco) {
        connectToDb();

        String sql = "UPDATE planta SET preco = ? WHERE id = ?";

        try {
            if (connection == null) {
                return false;
            }

            pst = connection.prepareStatement(sql);

            pst.setDouble(1, novoPreco);
            pst.setInt(2, id);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar preço: " + e.getMessage());
            return false;

        } finally {
            fecharRecursos();
        }
    }

    // Questão 5: remover uma planta pelo id.
    public boolean removerPlanta(int id) {
        connectToDb();

        String sql = "DELETE FROM planta WHERE id = ?";

        try {
            if (connection == null) {
                return false;
            }

            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao remover planta: " + e.getMessage());
            return false;

        } finally {
            fecharRecursos();
        }
    }

    // Questão 6: calcular o valor total do estoque.
    public double valorTotalEstoque() {
        connectToDb();

        String sql = "SELECT COALESCE(SUM(preco * estoque), 0) AS total FROM planta";

        try {
            if (connection == null) {
                return 0.0;
            }

            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao calcular valor do estoque: " + e.getMessage());

        } finally {
            fecharRecursos();
        }

        return 0.0;
    }

    // Fecha os recursos utilizados nas operações.
    private void fecharRecursos() {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (st != null) st.close();
            if (connection != null) connection.close();

        } catch (SQLException e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }

        rs = null;
        pst = null;
        st = null;
    }
}
