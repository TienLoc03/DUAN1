package viewModels;

/**
 *
 * @author ASUS
 */
public class MonAnResponse {

    private int MaMonAn;
    private int MaLoaiMon;
    private String TenMonAn;
    private String DonVi;
    private double GiaTien;
    private String TrangThai;
    private String imageMonAn;
    private DanhMucResponse danhMucResponse;

    public MonAnResponse() {
    }

    public MonAnResponse(int MaMonAn, int MaLoaiMon, String TenMonAn, String DonVi, double GiaTien, String TrangThai, String imageMonAn, DanhMucResponse danhMucResponse) {
        this.MaMonAn = MaMonAn;
        this.MaLoaiMon = MaLoaiMon;
        this.TenMonAn = TenMonAn;
        this.DonVi = DonVi;
        this.GiaTien = GiaTien;
        this.TrangThai = TrangThai;
        this.imageMonAn = imageMonAn;
        this.danhMucResponse = danhMucResponse;
    }

    public MonAnResponse(int MaMonAn, String TenMonAn, DanhMucResponse danhMucResponse) {
        this.MaMonAn = MaMonAn;
        this.TenMonAn = TenMonAn;
        this.danhMucResponse = danhMucResponse;
    }

    public int getMaMonAn() {
        return MaMonAn;
    }

    public void setMaMonAn(int MaMonAn) {
        this.MaMonAn = MaMonAn;
    }

    public int getMaLoaiMon() {
        return MaLoaiMon;
    }

    public void setMaLoaiMon(int MaLoaiMon) {
        this.MaLoaiMon = MaLoaiMon;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String TenMonAn) {
        this.TenMonAn = TenMonAn;
    }

    public String getDonVi() {
        return DonVi;
    }

    public void setDonVi(String DonVi) {
        this.DonVi = DonVi;
    }

    public double getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(double GiaTien) {
        this.GiaTien = GiaTien;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getImageMonAn() {
        return imageMonAn;
    }

    public void setImageMonAn(String imageMonAn) {
        this.imageMonAn = imageMonAn;
    }

    public DanhMucResponse getDanhMucResponse() {
        return danhMucResponse;
    }

    public void setDanhMucResponse(DanhMucResponse danhMucResponse) {
        this.danhMucResponse = danhMucResponse;
    }

    public Object[] toDataRow() {
        return new Object[]{danhMucResponse.getTenLoaiMon(), TenMonAn, DonVi, TrangThai, GiaTien};
    }

    public Object[] toDataRow2() {
        return new Object[]{MaMonAn, TenMonAn, danhMucResponse.getTenLoaiMon()};
    }
}
