package com.mycompany.tela.java.swing.v1;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author viniciuspereira
 */
public class Conexao {
    
    private JdbcTemplate connection;
    
    public Conexao() {
        
        BasicDataSource dataSource = new BasicDataSource();
        
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        dataSource.setUrl("jdbc:sqlserver://svr-bibliotech.database.windows.net:1433;database=bibliotech;user=admin-bibliotech@svr-bibliotech;password={#Gfgrupo5};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        
        dataSource.setUsername("admin-bibliotech");
        
        dataSource.setPassword("#Gfgrupo5");
        
        this.connection = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConnection() {
        return connection;
    }

    void query(String format) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
