package LogsBibliotech;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ConexaoMySQL.*;
import Alerta.*;
import com.mycompany.tela.java.swing.v1.Hardware;
import com.mycompany.tela.java.swing.v1.Services;

public class LogsBibliotech {

    private static final Logger logger = Logger.getLogger(LogsBibliotech.class.getName());

    public LogsBibliotech() {
    }


    
   

        Services d = new Services();
        Hardware hardware = new Hardware();
        ConexaoSQL conexao = new ConexaoSQL();
        JdbcTemplate con = conexao.getConnection();

        String diretorio = "C:\\Logs";
        String nomeArquivo = "LogsAlertas.txt";

        public void gerarLogs() throws IOException,InterruptedException{
        // Criar o diretório, se não existir
        Path diretorioLogs = Paths.get(diretorio);
        if (!Files.exists(diretorioLogs)) {
            Files.createDirectories(diretorioLogs);
        }

        // Criar o arquivo no diretório
        File arquivo = new File(diretorio, nomeArquivo);
        arquivo.createNewFile();

        // Criar o FileOutputStream para o arquivo
        FileOutputStream arq = new FileOutputStream(arquivo);
        DataOutputStream gravarArq = new DataOutputStream(arq);

        Integer idAlerta = 1; // Exemplo de valor para idAlerta
        String situacaoAlerta = "Ativo"; // Exemplo de valor para nome
        Double pc = 123.45; // Exemplo de valor para pc
        Integer idSituacaoAlerta = 0; // Exemplo de valor para altura

        gravarArq.writeInt(idAlerta);
        gravarArq.writeUTF(situacaoAlerta);
        gravarArq.writeDouble(pc);
        gravarArq.writeInt(idSituacaoAlerta);

        try {
            Date dateTime = new Date();
            

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formattedDateTime = dateFormat.format(dateTime);

            gravarArq.writeUTF(formattedDateTime);

            
            FileWriter fileWriter = null;
            String alertaData = null;

            try {
                fileWriter = new FileWriter(arquivo);
                FileHandler fileHandler = new FileHandler(diretorio + "\\monitoramento.log");
                logger.addHandler(fileHandler);
                SimpleFormatter formatter = new SimpleFormatter();
                fileHandler.setFormatter(formatter);
                
                if(d.alerta.getFk_tipo_alerta().equals(1)){ 
                    
                    logger.info("Informação de alerta:" + d.alerta.getFk_tipo_alerta() + "(Ociosidade)");
                    
                 }else{ 
                    
                    logger.info("Informação de alerta: " + d.alerta.getFk_tipo_alerta() + " (Mal uso)");
                    
                }

             logger.info("Logs gravados com sucesso!");
            } catch (IOException e) {
                e.printStackTrace();
                logger.severe("Erro ao gravar logs: " + e.getMessage());
            } finally {
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        logger.severe("Erro ao fechar FileWriter: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Erro ao gravar logs: " + e.getMessage());
        } finally {
            if (gravarArq != null) {
                try {
                    gravarArq.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.severe("Erro ao fechar DataOutputStream: " + e.getMessage());
                }
            }
            if (arq != null) {
                try {
                    arq.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.severe("Erro ao fechar FileOutputStream: " + e.getMessage());
                }
            }
        }
    }
}


