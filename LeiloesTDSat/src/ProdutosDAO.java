
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 * Classe responsável por acessar e manipular dados da tabela produto no banco
 * de dados. Utiliza JDBC para operações de salvamento e listagem.
 */
public class ProdutosDAO {

    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) throws SQLException {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo!");
        }
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio!");
        }
        if (produto.getValor() == null || produto.getValor() <= 0) {
            throw new IllegalArgumentException("O valor do produto deve ser maior que zero!");
        }
        if (produto.getStatus() == null || produto.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("O status do produto não pode ser vazio!");
        }
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        try (Connection conn = conectaDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        ProdutosDTO p = new ProdutosDTO(10, "RTX 3090", 5000, "A venda");
        listagem.add(p);
        return listagem;
    }

}
