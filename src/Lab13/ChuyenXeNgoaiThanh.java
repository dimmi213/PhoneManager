package Lab13;

public class ChuyenXeNgoaiThanh extends ChuyenXe {

    private String noiDen;
    private int soNgayDiDuoc;
    public ChuyenXeNgoaiThanh(String maSo, String tenTaiXe,double doanhThu,String noiDen, int soNgayDiDuoc) {
        super(maSo, tenTaiXe, doanhThu);
        this.noiDen = noiDen;
        this.soNgayDiDuoc = soNgayDiDuoc;
    }
    public String getNoiDen() {
        return noiDen;
    }
    public void setNoiDen(String noiDen) {
        this.noiDen = noiDen;
    }
    public int getSoNgayDiDuoc() {
        return soNgayDiDuoc;
    }
    public void setSoNgayDiDuoc(int soNgayDiDuoc) {
        this.soNgayDiDuoc = soNgayDiDuoc;
    }
    @Override
    public String toString() {
        return super.toString()+ "ChuyenNgoaiThanh [noiDen=" + noiDen +            ", soNgayDiDuoc="+ soNgayDiDuoc + "]";
    }
        
}