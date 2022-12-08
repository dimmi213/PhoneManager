package Lab13;

public class GiaoDichDat extends GiaoDich {
    private String loaiDat;
 
    public GiaoDichDat() {
        super();
    }
 
    public GiaoDichDat(String loaiDat) {
        super();
        this.loaiDat = loaiDat;
    }
 
    public String getLoaiDat() {
        return loaiDat;
    }
 
    public void setLoaiDat(String loaiDat) {
        this.loaiDat = loaiDat;
    }
     
    public void nhap() {
        super.nhap();
        System.out.print("Nhap loai dat (A/B/C): ");
        loaiDat = scanner.nextLine();
    }

	@Override
	public String toString() {
		return "GiaoDichDat [loaiDat=" + loaiDat + "]";
	}
}
