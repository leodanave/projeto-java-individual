package MetricaRede;

/**
 *
 * @author viniciuspereira
 */
public class MetricaRede {
    
    private Double velocidade_download;
    private Double velocidade_upload;

    public MetricaRede(Double velocidade_download, Double velocidade_upload) {
        this.velocidade_download = velocidade_download;
        this.velocidade_upload = velocidade_upload;
    }

    public MetricaRede() {
    }

    public Double getVelocidade_download() {
        return velocidade_download;
    }

    public void setVelocidade_download(Double velocidade_download) {
        this.velocidade_download = velocidade_download;
    }

    public Double getVelocidade_upload() {
        return velocidade_upload;
    }

    public void setVelocidade_upload(Double velocidade_upload) {
        this.velocidade_upload = velocidade_upload;
    }
    
}
