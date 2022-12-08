package Lab13;

public class ChuyenXeNoiThanh extends ChuyenXe{

    private int soTuyen, soKM;

    public ChuyenXeNoiThanh(String maSo, String tenTaiXe, double doanhThu,
            int soTuyen, int soKM) {
        super(maSo, tenTaiXe, doanhThu);
        this.soTuyen = soTuyen;
        this.soKM = soKM;
    }

    public int getSoTuyen() {
        return soTuyen;
    }

    public void setSoTuyen(int soTuyen) {
        this.soTuyen = soTuyen;
    }

    public int getSoKM() {
        return soKM;
    }

    public void setSoKM(int soKM) {
        this.soKM = soKM;
    }
    
    @Override
    public String toString() {
        return super.toString() + "ChuyenNoiThanh [soTuyen=" + soTuyen +         ", soKM=" + soKM + "]";
    }
}