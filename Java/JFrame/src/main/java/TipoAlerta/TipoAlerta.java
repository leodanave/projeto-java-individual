package TipoAlerta;

/**
 *
 * @author vrp19
 */
public class TipoAlerta {
    
    private Integer id_tipo_alerta;
    private String tipo_alerta;

    public TipoAlerta(Integer id_tipo_alerta, String tipo_alerta) {
        this.id_tipo_alerta = id_tipo_alerta;
        this.tipo_alerta = tipo_alerta;
    }

    public TipoAlerta() {
    }
    
    public Integer getId_tipo_alerta() {
        return id_tipo_alerta;
    }

    public void setId_tipo_alerta(Integer id_tipo_alerta) {
        this.id_tipo_alerta = id_tipo_alerta;
    }

    public String getTipo_alerta() {
        return tipo_alerta;
    }

    public void setTipo_alerta(String tipo_alerta) {
        this.tipo_alerta = tipo_alerta;
    }
    
    public void sendToBD(){
    }
    
}
