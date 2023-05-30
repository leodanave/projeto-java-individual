package SituacaoAlerta;

/**
 *
 * @author vrp19
 */
public class SituacaoAlerta {
    
    private Integer id_situacao_alerta;
    private String situacao_alerta;

    public SituacaoAlerta(Integer id_situacao_alerta, String situacao_alerta) {
        this.id_situacao_alerta = id_situacao_alerta;
        this.situacao_alerta = situacao_alerta;
    }

    public SituacaoAlerta() {
    }
    
    public Integer getId_situacao_alerta() {
        return id_situacao_alerta;
    }

    public void setId_situacao_alerta(Integer id_situacao_alerta) {
        this.id_situacao_alerta = id_situacao_alerta;
    }

    public String getSituacao_alerta() {
        return situacao_alerta;
    }

    public void setSituacao_alerta(String situacao_alerta) {
        this.situacao_alerta = situacao_alerta;
    }
    
    public void sendToBD(){
    }
    
}
