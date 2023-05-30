/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maquina;

/**
 *
 * @author wesley
 */
public class Maquina {
    private Integer id_maquina;
    private String sistema_operacional;
    private String setor;
    private String login;
    private String senha;
    private Integer fk_biblioteca;

    public Integer getId_maquina() {
        return id_maquina;
    }

    public void setId_maquina(Integer id_maquina) {
        this.id_maquina = id_maquina;
    }

    public String getSistema_operacional() {
        return sistema_operacional;
    }

    public void setSistema_operacional(String sistema_operacional) {
        this.sistema_operacional = sistema_operacional;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getFk_biblioteca() {
        return fk_biblioteca;
    }

    public void setFk_biblioteca(Integer fk_biblioteca) {
        this.fk_biblioteca = fk_biblioteca;
    }
    
    
    
    
}
