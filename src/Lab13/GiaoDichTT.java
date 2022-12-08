package Lab13;

import java.util.Scanner;

public class GiaoDichTT {
	protected int MaGD, SoLuong;
	protected String NgayGD;
	protected double DonGoa, ThanhTien;
	
	Scanner scanner = new Scanner(System.in);
	
	public GiaoDichTT() {
		
	}

	public GiaoDichTT(int maGD, int soLuong, String ngayGD, double donGoa, double thanhTien) {
		super();
		this.MaGD = maGD;
		this.SoLuong = soLuong;
		this.NgayGD = ngayGD;
		this.DonGoa = donGoa;
		this.ThanhTien = thanhTien;
	}

	public int getMaGD() {
		return MaGD;
	}

	public void setMaGD(int maGD) {
		MaGD = maGD;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}

	public String getNgayGD() {
		return NgayGD;
	}

	public void setNgayGD(String ngayGD) {
		NgayGD = ngayGD;
	}

	public double getDonGoa() {
		return DonGoa;
	}

	public void setDonGoa(double donGoa) {
		DonGoa = donGoa;
	}

	public double getThanhTien() {
		return ThanhTien;
	}

	public void setThanhTien(double thanhTien) {
		ThanhTien = thanhTien;
	}
	
	public double TTien() {
		return this.ThanhTien = this.DonGoa * this.SoLuong;
	}
	
	public void nhap() {
		System.out.println("Ma giao dich: ");
		MaGD = scanner.nextInt();
		System.out.println("Ngay giao dich: ");
		NgayGD = scanner.nextLine();
		System.out.println("Don Gia: ");
		DonGoa = scanner.nextDouble();
		System.out.println("So luong: ");
		SoLuong = scanner.nextInt();
	}

	@Override
	public String toString() {
		return "GiaoDichTT [MaGD=" + MaGD + ", SoLuong=" + SoLuong + ", NgayGD=" + NgayGD + ", DonGoa=" + DonGoa
				+ ", ThanhTien=" + ThanhTien + "]";
	}
	
}