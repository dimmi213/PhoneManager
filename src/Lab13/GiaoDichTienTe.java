package Lab13;

public class GiaoDichTienTe extends GiaoDichTT {
	private float TiGia;
	private int LoaiTienTe;
	
	public GiaoDichTienTe() {
		
	}

	public GiaoDichTienTe(float tiGia, int loaiTienTe,int maGD, int soLuong, String ngayGD, double donGoa, double thanhTien) {
		super(maGD, soLuong, ngayGD, donGoa, thanhTien);
		TiGia = tiGia;
		LoaiTienTe = loaiTienTe;
	}

	public float getTiGia() {
		return TiGia;
	}

	public void setTiGia(float tiGia) {
		TiGia = tiGia;
	}

	public int getLoaiTienTe() {
		return LoaiTienTe;
	}

	public void setLoaiTienTe(int loaiTienTe) {
		LoaiTienTe = loaiTienTe;
	}
	
	public double TTien() {
		if(LoaiTienTe == 1) {
			return super.TTien();
		} else {
			return super.TTien()*this.TiGia;
		}
	}
	
	public void nhap() {
		super.nhap();
		System.out.println("Ti gia: ");
		TiGia = scanner.nextFloat();
		System.out.println("Loai tien te: ");
		LoaiTienTe = scanner.nextInt();
	}

	@Override
	public String toString() {
		return "GiaoDichTienTe [TiGia=" + TiGia + ", LoaiTienTe=" + LoaiTienTe + "]";
	}
}
