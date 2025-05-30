
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    
    //Classe de conexão com o banco de dados uc11
    public static Connection connectDB() {
        try {
            //Instância de conexão.
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/uc11", "root" ,"CAmysql2025DU");
            //A partir do DriverManager, ele recebe a senha e user e estabelece uma conexão estável com banco.
            System.out.println("CONECTADO!");
            return conn;
        } catch (SQLException erro) {
            //Captura de erro, gerando uma imagem de erro mais amigável e compreensível.
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Falha na conexão com o banco de dados", erro);
        }
    }
}
