package com.mycompany.tela.java.swing.v1;


import Alerta.Alerta;
import ComponenteMaquina.*;
import ConexaoMySQL.ConexaoSQL;
import EspecificacaoComponenteMaquina.EspecificacaoComponenteMaquina;
import LogsBibliotech.LogsBibliotech;
import Maquina.Maquina;
import Metrica.Metrica;
import SituacaoAlerta.SituacaoAlerta;
import TipoAlerta.TipoAlerta;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import com.mycompany.tela.java.swing.v1.Conexao;
import com.mycompany.tela.java.swing.v1.Services;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author wesley
 */
public class Iniciar {

    private String webHookUrl = "https://hooks.slack.com/services/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";
    private String oAuthUrl = "xoxb-5093777488852-5347584044833-rXCGMQtLQb13bfP1BAm08m8q";
    private String slackChannelUrl = "monitoramento-de-máquinas";
    
    public static void main(String[] args) {
        
        Boolean validate = false;

        Looca looca = new Looca();
        Services d = new Services();
        Conexao conexao = new Conexao();
        Maquina maquina = new Maquina();
        Sistema sistema = looca.getSistema();
        Alerta alerta = new Alerta();
        LogsBibliotech log = new LogsBibliotech();
        Integer contador = 0;

        
        Scanner sc = new Scanner(System.in);
        


        System.out.println("Sistema bibliotech inicializando...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}

        System.out.println("---".repeat(15));
        System.out.println("\n             Seja bem-vindo(a)! \n");
        System.out.println("---".repeat(15) + "\n");

        System.out.println("Digite o seu login");
        String loginDigitado = sc.nextLine();
        System.out.println("Digite sua senha");
        String senhaDigitada = sc.nextLine();
    // Conexões necessárias
        ConexaoSQL conexaoMySQL = new ConexaoSQL();
        JdbcTemplate con = conexao.getConnection();
        JdbcTemplate conMysql = conexaoMySQL.getConnection();

        
        // Buscando login e senha digitados no JFrame
        List<Maquina> searchLogin = con.query("select id_maquina,sistema_operacional,setor,login,senha,fk_biblioteca from maquina where login = ? and senha = ?;", new BeanPropertyRowMapper(Maquina.class), loginDigitado, senhaDigitada);

         if (searchLogin.size() > 0) {
            Maquina result = searchLogin.get(0);
            System.out.println("Login efetuado com sucesso \n");
           // sendToSlack("Maquina com id " + result.getId_maquina() + " Iniciada!");
            
            // Inserts na tabela componente_maquina
            ComponenteMaquina componente1 = new ComponenteMaquina("Processador", d.processador.getNome(), d.processador.getFabricante());
            ComponenteMaquina componente2 = new ComponenteMaquina("Memoria ram", (d.memoria.getTotal().doubleValue() / 1048576), "null");
            ComponenteMaquina componente3 = new ComponenteMaquina("Disco", d.disco.getModelo(), "null");
            
            System.out.println("Inserindo dados dos componentes na tabela componente maquina \n");
            con.update(String.format("insert into componente_maquina (tipo,descricao,fabricante) values ('%s','%s','%s')",
                    componente1.getTipo(), componente1.getDescricao(), componente1.getFabricante()));

            DecimalFormat df = new DecimalFormat("0.00");
            Double numero = Double.valueOf(componente2.getDescricao());
            String saida = df.format(numero);

            con.update(String.format("insert into componente_maquina (tipo,descricao,fabricante) values ('%s','%s','%s')",
                    componente2.getTipo(), saida, componente2.getFabricante()));

            con.update(String.format("insert into componente_maquina (tipo,descricao,fabricante) values ('%s','%s','%s')",
                    componente3.getTipo(), componente3.getDescricao(), componente3.getFabricante()));

            // Pegando os últimos 3 componentes da lista de componentes para usar como fk
            List<ComponenteMaquina> comp = con.query("select id_componente_maquina from componente_maquina order by id_componente_maquina desc;", new BeanPropertyRowMapper(ComponenteMaquina.class));
            ComponenteMaquina resultComp = comp.get(0);
            ComponenteMaquina resultComp1 = comp.get(1);
            ComponenteMaquina resultComp2 = comp.get(2);

            // Coletando os dados das especificações dos componentes
            EspecificacaoComponenteMaquina spec1 = new EspecificacaoComponenteMaquina(d.processador.getId(), d.processador.getUso(), ((d.processador.getFrequencia().doubleValue()) / 1048576));
            EspecificacaoComponenteMaquina spec2 = new EspecificacaoComponenteMaquina("null", (d.memoria.getTotal().doubleValue() / 1048576), null);
            EspecificacaoComponenteMaquina spec3 = new EspecificacaoComponenteMaquina(d.disco.getSerial(), (d.discoGroup.getTamanhoTotal().doubleValue() / 1000000000), null);

            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            DecimalFormatSymbols symbols = decimalFormat.getDecimalFormatSymbols();

            // Inserts de processador
            
            String processadorUsoFormatado = df.format(spec1.getUso_maximo());  
            String processadorFrequenciaFormatada = df.format(spec1.getFreq_maxima());

             System.out.println("Inserindo dados das especificações de componente \n");
            con.update(String.format("insert into especificacao_componente_maquina (fk_componente_maquina ,fk_maquina, numero_serial, uso_maximo, freq_maxima) values (%d, %d, '%s','%s','%s')",
                    resultComp2.getId_componente_maquina(), result.getId_maquina(), spec1.getNumero_serial(), processadorUsoFormatado.replace(',','.'),processadorFrequenciaFormatada.replace(',','.') ));
            // memoria
            double usoMaximoRam = spec2.getUso_maximo();
            String usoMaximoFormatado2 = String.format("%.2f",usoMaximoRam); 
            con.update(String.format("insert into especificacao_componente_maquina (fk_componente_maquina ,fk_maquina, numero_serial, uso_maximo, freq_maxima) values (%d, %d, '%s','%s', null)",
                    resultComp1.getId_componente_maquina(), result.getId_maquina(), spec2.getNumero_serial(), usoMaximoFormatado2.replace(',','.')));
 
            // Disco
            double usoMaximo3 = spec3.getUso_maximo();
            symbols.setDecimalSeparator('.');
            decimalFormat.setDecimalFormatSymbols(symbols);
            String usoMaximoFormatado3 = decimalFormat.format(usoMaximo3);
            // Verificar se o valor formatado é um número válido
            try {
                Double.parseDouble(usoMaximoFormatado3);
            } catch (NumberFormatException e) {
                // Tratar o caso em que o valor não é um número válido
                // Por exemplo, mostrar uma mensagem de erro ou definir um valor padrão
                usoMaximoFormatado3 = "0.00"; // Valor padrão
            }

            con.update(String.format("insert into especificacao_componente_maquina (fk_componente_maquina, fk_maquina, numero_serial, uso_maximo, freq_maxima) values (%d, %d, '%s', '%s', null)",
        resultComp.getId_componente_maquina(), result.getId_maquina(), spec3.getNumero_serial(), usoMaximoFormatado3));

            
            // Pegando as últimas 3 especificações da lista para utilizar como fk
            List<EspecificacaoComponenteMaquina> spec = con.query("select id_especificacao from especificacao_componente_maquina order by id_especificacao desc;",
                    new BeanPropertyRowMapper(EspecificacaoComponenteMaquina.class));
            EspecificacaoComponenteMaquina resultSpec = spec.get(0);
            EspecificacaoComponenteMaquina resultSpec1 = spec.get(1);
            EspecificacaoComponenteMaquina resultSpec2 = spec.get(2);

            Boolean validar = true;

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            do {

                Hardware hardware = new Hardware();

                try {
                    hardware = d.enviarDados();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("Inserindo metricas");
                String convertToString = String.format("%.2f", d.metricaRede.getVelocidade_download()).replace(",", ".");
                String convertToString1 = String.format("%.2f", d.metricaRede.getVelocidade_upload()).replace(",", ".");

                con.update(String.format("INSERT INTO metrica (uso, frequencia, fk_especificacao, fk_componente_maquina, fk_maquina, total_processos, tempo_de_sessão) VALUES (%s, %s, %d, %d, %d, %s, %d)",
                        hardware.getUsoCPU(), hardware.getFrequenciaCPU(), resultSpec2.getId_especificacao(), resultComp2.getId_componente_maquina(), result.getId_maquina(), hardware.getTotal_processos(), sistema.getTempoDeAtividade()));

                con.update(String.format("INSERT INTO metrica (uso, frequencia, fk_especificacao, fk_componente_maquina, fk_maquina, total_processos) VALUES (%s, null, %d, %d, %d, %s)",
                        (hardware.getUsoRAM()), resultSpec1.getId_especificacao(), resultComp1.getId_componente_maquina(), result.getId_maquina(), hardware.getTotal_processos()));

                con.update(String.format("INSERT INTO metrica (uso, frequencia, fk_especificacao, fk_componente_maquina, fk_maquina, total_processos) VALUES ('%s', '%s', %d, %d, %d, %s)",
                        d.getUsoDisco(), d.getFreqDisco(), resultSpec.getId_especificacao(), resultComp.getId_componente_maquina(), result.getId_maquina(), hardware.getTotal_processos()));

                con.update(String.format("INSERT INTO metrica_rede (velocidade_download, velocidade_upload, fk_maquina) values ('%s', '%s', %d)", convertToString,
                        convertToString1, result.getId_maquina()));
                
                d.Alerta();

                List<Metrica> metricaList = con.query("select id_metrica from metrica order by id_metrica desc", new BeanPropertyRowMapper(Metrica.class));
                Metrica metrica = metricaList.get(0);

                List<SituacaoAlerta> situacaoList = con.query("select id_situacao_alerta from situacao_alerta order by id_situacao_alerta desc", new BeanPropertyRowMapper(SituacaoAlerta.class));
                SituacaoAlerta situacao = situacaoList.get(0);
                SituacaoAlerta situacao1 = situacaoList.get(1);
                SituacaoAlerta situacao2 = situacaoList.get(2);
                SituacaoAlerta situacao3 = situacaoList.get(3);

                List<TipoAlerta> tipoList = con.query("select id_tipo_alerta from tipo_alerta order by id_tipo_alerta desc", new BeanPropertyRowMapper(TipoAlerta.class));
                TipoAlerta tipo = tipoList.get(0);
                TipoAlerta tipo1 = tipoList.get(1);

                if (d.processador.getUso() >= 90.0) {
                    System.out.println("Alerta enviado por uso critico");
//                    sendToSlack("Maquina com id " + result.getId_maquina() + " localizada no setor " + result.getSetor() + " está com uso de CPU acima de 90%! (CRITICO)");
                    con.update(String.format("INSERT INTO alerta (texto_aviso, fk_metrica, fk_tipo_alerta, fk_situacao_alerta) values ('Alerta crítico. Uso muito acima do esperado.', %d, %d, %d)", metrica.getId_metrica(),
                            tipo1.getId_tipo_alerta(), situacao3.getId_situacao_alerta()));
                    
                    try {
                        log.gerarLogs();
                    } catch (IOException ex) {
                        Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (d.processador.getUso() >= 70.0) {
                    System.out.println("Alerta enviado por uso alto");
//                     sendToSlack("Maquina com id " + result.getId_maquina() + " localizada no setor " + result.getSetor() + " está com uso de CPU acima de 70% (Risco alto)");
                    con.update(String.format("INSERT INTO alerta (texto_aviso, fk_metrica, fk_tipo_alerta, fk_situacao_alerta) values ('Risco alto. Uso acima do esperado.', %d, %d, %d)", metrica.getId_metrica(),
                            tipo1.getId_tipo_alerta(), situacao2.getId_situacao_alerta()));
                    
                      try {
                        log.gerarLogs();
                    } catch (IOException ex) {
                        Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                      
                } else if (d.processador.getUso() >= 50.0) {
                    System.out.println("Alerta enviado por uso acima do esperado");  
                   // sendToSlack("Maquina com id " + result.getId_maquina() + " localizada no setor " + result.getSetor() + " está com uso de CPU acima de 50% (Risco moderado)");
                   con.update(String.format("INSERT INTO alerta (texto_aviso, fk_metrica, fk_tipo_alerta, fk_situacao_alerta) values ('Risco moderado. Uso um pouco acima do esperado.', %d, %d, %d)", metrica.getId_metrica(),
                            tipo1.getId_tipo_alerta(), situacao1.getId_situacao_alerta()));
                    
                      try {
                        log.gerarLogs();
                    } catch (IOException ex) {
                        Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                      
                } else {

                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                
                if(d.processador.getUso() < 4.0){
                    contador++;
                }else{
                    contador = 0;
                }
                
                if (contador >= 10) {
                    System.out.println("Alerta enviado por ociosidade");
//                    sendToSlack("Maquina com id " + result.getId_maquina() + " localizada no setor" + result.getSetor() +  " está sendo bloqueada por ociosidade (Ocioso)");
                    con.update(String.format("INSERT INTO alerta (texto_aviso, fk_metrica, fk_tipo_alerta, fk_situacao_alerta) values ('Máquina ociosa.', %d, %d, %d)", metrica.getId_metrica(),
                            1,situacao.getId_situacao_alerta()));
                    ProcessBuilder Alertar = new ProcessBuilder("/bin/bash", "-c", "notify-send ALERTA 'Tela está sendo bloqueada por inatividade'");
                    ProcessBuilder bloquearTela;
                    if (sistema.getSistemaOperacional().equalsIgnoreCase("Windows")) {
                        bloquearTela = new ProcessBuilder("cmd.exe", "-c", "rundll32.exe user32.dll,LockWorkStation");
                    } else {
                        bloquearTela = new ProcessBuilder("/bin/bash", "-c", "xdg-screensaver lock");
                    }
                    try {
                        Process alertar = Alertar.start();
                    } catch (IOException ex) {
                        Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        Thread.sleep(5000);
                        Process bloquear = bloquearTela.start();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    validar = false;
                }
            } while (validar == true);
         }
         
         else{
             
             System.out.println("Login e senha incorretos, inicie novamente a aplicação e tente novamente");
         }
         
    }
    
   }
        

     
         
