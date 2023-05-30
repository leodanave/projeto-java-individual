package EspecificacaoComponenteMaquina;

/**
 *
 * @author vrp19
 */
public class EspecificacaoComponenteMaquina {
    
    private Integer id_especificacao;
    private String numero_serial;
    private Double uso_maximo;
    private Double freq_maxima;
    
    public EspecificacaoComponenteMaquina(String numero_serial, Double uso_maximo, Double freq_maxima) {
        this.numero_serial = numero_serial;
        this.uso_maximo = uso_maximo;
        this.freq_maxima = freq_maxima;
    }

    public EspecificacaoComponenteMaquina() {
        
    }

    public Integer getId_especificacao() {
        return id_especificacao;
    }

    public void setId_especificacao(Integer id_especificacao) {
        this.id_especificacao = id_especificacao;
    }

    public String getNumero_serial() {
        return numero_serial;
    }

    public void setNumero_serial(String numero_serial) {
        this.numero_serial = numero_serial;
    }

    public Double getUso_maximo() {
        return uso_maximo;
    }

    public void setUso_maximo(Double uso_maximo) {
        this.uso_maximo = uso_maximo;
    }

    public Double getFreq_maxima() {
        return freq_maxima;
    }

    public void setFreq_maxima(Double freq_maxima) {
        this.freq_maxima = freq_maxima;
    }
    
    public void sendToBD(){
    }
    
}
