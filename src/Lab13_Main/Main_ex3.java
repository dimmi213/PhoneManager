package Lab13_Main;


import java.util.Scanner;

import Lab13.ListGD;

public class Main_ex3 {
	public static void main(String[] args) {
		ListGD listGD = new ListGD();
		Scanner scanner = new Scanner(System.in);
		int x;
		
		do {
			System.out.println("1.Them giao dich vang");
			System.out.println("2.Them giao dich tien te");
			System.out.println("3.Hien thi danh sach giao dich");
			System.out.println("4. Xem tong so luong cua cac giao dich");
			System.out.println("5.Xem trung binh cua giao dich tien te");
			System.out.println("6. Hien thi danh sach co don gia > 1 ty");
			System.out.println("0. Thoat");
			x = scanner.nextInt();
			switch (x) {
			case 1:
				listGD.ThemGD(1);
				break;

			case 2:
				listGD.ThemGD(2);
				break;
			case 3:
				listGD.HienThiDS();
				break;
			case 4:
				listGD.TongSL();
				break;
			case 5:
				listGD.TrungBinhGDTT();
				break;	
			case 6:
				listGD.GD1ty();
				break;	
			}
		} while (x!=0);
	}
}

