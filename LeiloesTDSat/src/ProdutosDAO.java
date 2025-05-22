
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe responsável por acessar e manipular dados da tabela produto no banco
 * de dados. Utiliza JDBC para operações de salvamento e listagem.
 */
public class ProdutosDAO {

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
        try (Connection conn = conectaDAO.connectDB(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        ArrayList<ProdutosDTO> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection conn = conectaDAO.connectDB(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                produtos.add(p);
            }
            if (produtos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum produto encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return produtos;
    }

    public void excluir(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido! Deve ser maior que zero");
        }
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = conectaDAO.connectDB(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        
    }
    public void atualizar(ProdutosDTO produto) throws SQLException {
        if (produto == null || produto.getId() <= 0 || produto.getNome() == null || produto.getNome().trim().isEmpty() ||
            produto.getValor() == null || produto.getValor() == null || produto.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("Dados do produto inválidos: Nome e valor são obrigatórios.");
        }
        String sql = "UPDATE produtos SET nome = ?, valor = ?, sttatus = ? WHERE id = ?";
        try (Connection conn = conectaDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            stmt.setInt(4, produto.getId());
            stmt.executeUpdate();
        }
    }

    void listarTodos() {
        List<ProdutosDTO> produtos = new ArrayList<>();
        String sql = "SELECT * FROM filmes";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor("valor");
                produto.setCategoria(rs.getString("categoria"));
                produtos.add(produto);
            }
        }
        return filmes;
    }
}


