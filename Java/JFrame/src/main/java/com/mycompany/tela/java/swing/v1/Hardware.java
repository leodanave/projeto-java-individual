package com.mycompany.tela.java.swing.v1;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author viniciuspereira
 */
public class Hardware {
     Conexao conexao = new Conexao();
     JdbcTemplate con = conexao.getConnection();

    private Double usoCPU;
    private Double frequenciaCPU;
    
    private Double usoRAM;
    
    private String usoDISCO;
    private String frequenciaDISCO;
    
    private Integer total_processos;

    
    public void setUsoCPU(Double uso) {
        this.usoCPU = uso;
    }
    public Double getUsoCPU() {
        return usoCPU;
    }

    public void setFrequenciaCPU(Double frequencia) {
        this.frequenciaCPU = frequencia;
    }

    public Double getFrequenciaCPU() {
        return frequenciaCPU;
    }

    public Double getUsoRAM() {
        return usoRAM;
    }

    public void setUsoRAM(Double usoRAM) {
        this.usoRAM = usoRAM;
    }

    public String getUsoDISCO() {
        return usoDISCO;
    }

    public void setUsoDISCO(String usoDISCO) {
        this.usoDISCO = usoDISCO;
    }

    public String getFrequenciaDISCO() {
        return frequenciaDISCO;
    }

    public void setFrequenciaDISCO(String frequenciaDISCO) {
        this.frequenciaDISCO = frequenciaDISCO;
    }

    

    public Integer getTotal_processos() {
        return total_processos;
    }

    public void setTotal_processos(Integer total_processos) {
        this.total_processos = total_processos;
    }

}
