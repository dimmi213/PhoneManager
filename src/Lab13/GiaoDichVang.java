package Lab13;

public class GiaoDichVang extends GiaoDichTT {
	//Khai báo biến chứa thông tin riêng của giao dịch vàng
	private String loaiVang;
	//Tạo constructor không tham số và đầy đủ tham số
	public GiaoDichVang() {
	}
	public GiaoDichVang(String loaiVang,int maGD, int soLuong, String ngayGD, double donGoa, double thanhTien) {
		super(maGD, soLuong, ngayGD, donGoa, thanhTien);
		this.loaiVang = loaiVang;
	}

	//Tạo các phương thức get/set
	public String getLoaiVang() {
		return loaiVang;
	}

	public void setLoaiVang(String loaiVang) {
		this.loaiVang = loaiVang;
	}
	//Tạo hàm tính tiền của một giao dịch vàng
	public double TTien() {
		return super.TTien();
	}
	//Tạo hàm nhập thông tin của giao dịch vàng
	public void nhap () {
		System.out.println("Loai vang: ");
		loaiVang = scanner.nextLine();
	}
	//Tạo hàm xuất thông tin của giao dịch vàng
	@Override
	public String toString() {
		return "GiaoDichVang {"+super.toString() + "loaiVang = " + loaiVang +'}';
	}
	
}
