package Metrica;

/**
 *
 * @author vrp19
 */
public class Metrica {
    
    private Integer id_metrica;
    private Double uso;
    private Double frequencia;
    private Integer fk_especificacao;
    private Integer fk_componente_maquina;
    private Integer fk_maquina;

    public Integer getId_metrica() {
        return id_metrica;
    }

    public void setId_metrica(Integer id_metrica) {
        this.id_metrica = id_metrica;
    }

    public Double getUso() {
        return uso;
    }

    public void setUso(Double uso) {
        this.uso = uso;
    }

    public Double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Double frequencia) {
        this.frequencia = frequencia;
    }

    public Integer getFk_especificacao() {
        return fk_especificacao;
    }

    public void setFk_especificacao(Integer fk_especificacao) {
        this.fk_especificacao = fk_especificacao;
    }

    public Integer getFk_componente_maquina() {
        return fk_componente_maquina;
    }

    public void setFk_componente_maquina(Integer fk_componente_maquina) {
        this.fk_componente_maquina = fk_componente_maquina;
    }

    public Integer getFk_maquina() {
        return fk_maquina;
    }

    public void setFk_maquina(Integer fk_maquina) {
        this.fk_maquina = fk_maquina;
    }
    
    public void sendToBD(){
    }
    
}
