package domainModels;

/**
 *
 * @author ASUS
 */
public class MonAn {

    private int MaMonAn;
    private int MaLoaiMon;
    private String TenMonAn;
    private String DonVi;
    private double GiaTien;
    private String TrangThai;
    private String imageMonAn;
    private DanhMuc danhMuc;

    public MonAn() {
    }

    public MonAn(int MaMonAn, int MaLoaiMon, String TenMonAn, String DonVi, double GiaTien, String TrangThai, String imageMonAn, DanhMuc danhMuc) {
        this.MaMonAn = MaMonAn;
        this.MaLoaiMon = MaLoaiMon;
        this.TenMonAn = TenMonAn;
        this.DonVi = DonVi;
        this.GiaTien = GiaTien;
        this.TrangThai = TrangThai;
        this.imageMonAn = imageMonAn;
        this.danhMuc = danhMuc;
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

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

}
