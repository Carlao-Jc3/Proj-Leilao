
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    public static Connection connectDB() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?user=root&password=CAmysql2025DU");
            System.out.println("CONECTADO!");
            return conn;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Falha na conexão com o banco de dados", erro);
        }
    }
}
