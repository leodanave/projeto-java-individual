package Alerta;

import java.sql.Date;

/**
 *
 * @author vrp19
 */
public class Alerta {
    
    private Integer id_alerta;
    private Date dt_alerta;
    private String texto_aviso;
    private Integer fk_metrica;
    private Integer fk_tipo_alerta;
    private Integer fk_situacao_alerta;

    public Alerta(Integer id_alerta, Date dt_alerta, String texto_aviso, Integer fk_metrica, Integer fk_tipo_alerta, Integer fk_situacao_alerta) {
        this.id_alerta = id_alerta;
        this.dt_alerta = dt_alerta;
        this.texto_aviso = texto_aviso;
        this.fk_metrica = fk_metrica;
        this.fk_tipo_alerta = fk_tipo_alerta;
        this.fk_situacao_alerta = fk_situacao_alerta;
    }

    public Alerta() {
    }
    
    public Integer getId_alerta() {
        return id_alerta;
    }

    public void setId_alerta(Integer id_alerta) {
        this.id_alerta = id_alerta;
    }

    public Date getDt_alerta() {
        return dt_alerta;
    }

    public void setDt_alerta(Date dt_alerta) {
        this.dt_alerta = dt_alerta;
    }

    public String getTexto_aviso() {
        return texto_aviso;
    }

    public void setTexto_aviso(String texto_aviso) {
        this.texto_aviso = texto_aviso;
    }

    public Integer getFk_metrica() {
        return fk_metrica;
    }

    public void setFk_metrica(Integer fk_metrica) {
        this.fk_metrica = fk_metrica;
    }

    public Integer getFk_tipo_alerta() {
        return fk_tipo_alerta;
    }

    public void setFk_tipo_alerta(Integer fk_tipo_alerta) {
        this.fk_tipo_alerta = fk_tipo_alerta;
    }

    public Integer getFk_situacao_alerta() {
        return fk_situacao_alerta;
    }

    public void setFk_situacao_alerta(Integer fk_situacao_alerta) {
        this.fk_situacao_alerta = fk_situacao_alerta;
    }
    
    public void sendToBD(){
    }
    
}
