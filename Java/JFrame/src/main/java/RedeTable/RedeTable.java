package RedeTable;

import java.util.List;

/**
 *
 * @author viniciuspereira
 */
public class RedeTable {
    
    private Integer id_rede;
    private String ipv4;
    private String ipv6;

    public RedeTable(String ipv4, String ipv6) {
        this.ipv4 = ipv4;
        this.ipv6 = ipv6;
    }

    public RedeTable() {
    }

    public Integer getId_rede() {
        return id_rede;
    }

    public void setId_rede(Integer id_rede) {
        this.id_rede = id_rede;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }
       
}
