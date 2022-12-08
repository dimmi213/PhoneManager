package Lab13_Main;

import java.util.ArrayList;
import java.util.Scanner;

import Lab13.SachGiaoKhoa;
import Lab13.SachThamKhao;
 
public class Main_ex2 {
     
    public static void main(String[] args) {
        ArrayList<SachGiaoKhoa> arrSachGiaoKhoa = new ArrayList<>();
        ArrayList<SachThamKhao> arrSachThamKhao = new ArrayList<>();
        int soSachGiaoKhoa, soSachThamKhao;
        double tongTienSachGiaoKhoa = 0, tongTienSachThamKhao = 0, tongDonGiaSachThamKhao = 0, 
            trungBinhCongDonGia = 0;
        Scanner scanner = new Scanner(System.in);
         
        System.out.print("Nhap so sach giao khoa: ");
        soSachGiaoKhoa = scanner.nextInt();
        System.out.print("Nhap so sach tham khao: ");
        soSachThamKhao = scanner.nextInt();
         
        System.out.println("Nhap thong tin cua sach giao khoa:");
        for (int i = 0; i < soSachGiaoKhoa; i++) {
            System.out.println("Nhap thong tin cuon sach thu " + (i + 1) + ":");
            SachGiaoKhoa sachGiaoKhoa = new SachGiaoKhoa();
            sachGiaoKhoa.nhapSach();
            arrSachGiaoKhoa.add(sachGiaoKhoa);
        }
         
        System.out.println("Nhap thong tin sach tham khao:");
        for (int i = 0; i < soSachThamKhao; i++) {
            System.out.println("Nhap thong tin cuon thu " + (i + 1) + ":");
            SachThamKhao sachThamKhao = new SachThamKhao();
            sachThamKhao.nhapSach();
            arrSachThamKhao.add(sachThamKhao);
        }
         
        for (int i = 0; i < arrSachGiaoKhoa.size(); i++) {
            if (arrSachGiaoKhoa.get(i).getNumber() == 0) {
                tongTienSachGiaoKhoa += arrSachGiaoKhoa.get(i).getSoLuong() * 
                    arrSachGiaoKhoa.get(i).getDonGia() * 50 / 100;
            } else if (arrSachGiaoKhoa.get(i).getNumber() == 1) {
                tongTienSachGiaoKhoa += arrSachGiaoKhoa.get(i).getSoLuong() * 
                    arrSachGiaoKhoa.get(i).getDonGia();
            }
        }
        System.out.println("Tong tien sach giao khoa = " + tongTienSachGiaoKhoa);
         
        for (int i = 0; i < arrSachThamKhao.size(); i++) {
            tongTienSachThamKhao += arrSachThamKhao.get(i).getSoLuong() * 
                arrSachThamKhao.get(i).getDonGia() + arrSachThamKhao.get(i).getThue();
        }
        System.out.println("Tong tien sach tham khao = " + tongTienSachThamKhao);
         
        System.out.println("-----Thong tin sach giao khoa-----");
        for (int i = 0; i < arrSachGiaoKhoa.size(); i++) {
            System.out.println(arrSachGiaoKhoa.get(i).toString());
        }
         
        System.out.println("-----Thong tin sach tham khao-----");
        for (int i = 0; i < arrSachThamKhao.size(); i++) {
            System.out.println(arrSachThamKhao.get(i).toString());
        }
         
        System.out.println("---Trung binh cong don gia cac sach giao khoa---");
        for (int i = 0; i < arrSachThamKhao.size(); i++) {
            tongDonGiaSachThamKhao += arrSachThamKhao.get(i).getDonGia();
            trungBinhCongDonGia = tongDonGiaSachThamKhao / arrSachThamKhao.size();
        }
        System.out.println("Trung binh cong don gia cac sach giao khoa = " + trungBinhCongDonGia);
         
        System.out.println("---Cac sach giao khoa cua nha xuat ban X---");
        for (int i = 0; i < arrSachGiaoKhoa.size(); i++) {
            if (arrSachGiaoKhoa.get(i).getNhaXuatBan().equalsIgnoreCase("X")) {
                System.out.println(arrSachGiaoKhoa.get(i).toString());
            }
        }
    }
 
}