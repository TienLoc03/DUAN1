
package viewModels;

/**
 *
 * @author ASUS
 */
public class DanhMucResponse {
    private int MaLoaiMon;
    private String TenLoaiMon;

    public DanhMucResponse() {
    }

    public DanhMucResponse(int MaLoaiMon, String TenLoaiMon) {
        this.MaLoaiMon = MaLoaiMon;
        this.TenLoaiMon = TenLoaiMon;
    }

    

    public DanhMucResponse(String TenLoaiMon) {
        this.TenLoaiMon = TenLoaiMon;
    }

    public int getMaLoaiMon() {
        return MaLoaiMon;
    }

    public void setMaLoaiMon(int MaLoaiMon) {
        this.MaLoaiMon = MaLoaiMon;
    }
    
    

    public String getTenLoaiMon() {
        return TenLoaiMon;
    }

    public void setTenLoaiMon(String TenLoaiMon) {
        this.TenLoaiMon = TenLoaiMon;
    }
    public Object[] toDataRow(){
        return new Object[]{MaLoaiMon,TenLoaiMon};
    }
}
