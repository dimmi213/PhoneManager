package Lab13;

public class ListGD {
	//Tạo mảng giao dịch với kích thước là 100
	GiaoDichTT gd[] = new GiaoDichTT[100];
	private int CountGD, sum1 = 0, sum2 = 0, dem=0, sumtien = 0;
	//Tạo hàm tạo mới giao dịch trong từng vị trí của mảng
	public ListGD() {
		CountGD = 0;
		for (int i = 0; i<10; i++) {
			gd[i] = new GiaoDichTT();
		}
	}
	//Tao ham them giao dich va tinh tong so luong giao dich
	public void ThemGD(int temp) {
		if(CountGD > 100) {
			System.out.println("Bo nho day, khong the them moi");
		}else {
			if (temp == 1) {
				gd[CountGD] = new GiaoDichVang();
				GiaoDichVang gdv = new GiaoDichVang();
				gdv.nhap();
				gdv.ThanhTien = gdv.TTien();
				gd[CountGD] = gdv;
				sum1 += gdv.SoLuong;
			}else {
				gd[CountGD] = new GiaoDichTienTe();
				GiaoDichTienTe gdtt = new GiaoDichTienTe();
				gdtt.nhap();
				gdtt.ThanhTien = gdtt.TTien();
				gd[CountGD] = gdtt;
				sum2 += gdtt.SoLuong;
			}
			dem++;
		}
		CountGD++;
	}
	//Tao ham hien thi danh sach giao dich
	public void HienThiDS() {
		for(int i = 0; i < CountGD; i++) {
			System.out.println("\n -----SoTT:" + (i+1));
			System.out.println(gd[i].toString());
		}
	}
	//Tao ham tinh tong so luong giao dich
	public void TongSL() {
		System.out.println("Tong so luong cua giao dich vang: "+sum1);
		System.err.println("Tong so luong cua giao dich tien te: "+sum2);
	}
	
	//Xuat trung binh tien cua giao dich tien te
	public void TrungBinhGDTT() {
		System.out.println("Trung binh thanh tien cua giao dich tien te: "+ (sumtien/dem));
	}
	//Xuat danh sach giao dich tren 1 ty
	public void GD1ty() {
		int d = 0;
		for (int i = 0; i< CountGD; i++) {
			if(gd[i].DonGoa > 1000) {
				System.out.println(gd[i].toString());
				d++;
			}
		}
		if (d==0) {
			System.out.println("Khong co thong tin");
		}
	}
}
