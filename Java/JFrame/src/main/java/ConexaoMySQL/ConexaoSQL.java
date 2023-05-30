package ConexaoMySQL;

import com.mycompany.tela.java.swing.v1.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author vrp19
 */
public class ConexaoSQL {
    
    private JdbcTemplate connection;
    
    public ConexaoSQL() {
        
        BasicDataSource dataSource = new BasicDataSource();
        
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        
        dataSource.setUrl("jdbc:mysql://localhost:3306/bibliotech");
        
        dataSource.setUsername("root");
        
        dataSource.setPassword("urubu100");
        
        this.connection = new JdbcTemplate(dataSource);
    }
    
    public JdbcTemplate getConnection() {
        return connection;
    }
    
}
