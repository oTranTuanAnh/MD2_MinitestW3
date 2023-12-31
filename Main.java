import src.CrispyFlour;
import src.Material;
import src.MaterialManager;
import src.Meat;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Material> list = new ArrayList<>();
        int choice = -1;
        Scanner in = new Scanner(System.in);

        while (choice != 0){
            System.out.println("------------------------------------------------------------------");
            System.out.println("MENU");
            System.out.println("1. Them vat lieu vao danh sach");
            System.out.println("2. Sua vat lieu trong danh sach");
            System.out.println("3. Xoa vat lieu theo ma");
            System.out.println("4. Hien thi danh sach vat lieu hien co");
            System.out.println("5. Tinh tong tien vat lieu");
            System.out.println("6. Sap xep vat lieu theo gia");
            System.out.println("7. Chenh lech giua chiet khau va khong chiet khau ngay hom nay");
            System.out.println("0. Exit");
            System.out.print("NHAP LUA CHON CUA BAN: ");
            choice = in.nextInt();
            MaterialManager manager = new MaterialManager();


            switch (choice){
                case 1:
                    System.out.print("Nhap vat lieu can them  <F> flour hay <M> meat: ");
                    String input = manager.stringInput();
                    Material newMaterial = null;
                    list = manager.readData("data.txt");
                    if (input.equals("F")){
                        newMaterial = manager.createNewCrispyFlour();
                    } else if (input.equals("M")){
                        newMaterial = manager.createNewMeat();
                    }else {
                        System.out.println("Nhap sai. Moi nhap lai");
                        break;
                    }
                    boolean check = true;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getId().equals(newMaterial.getId())){
                            check = false;
                        }
                    }
                    if (check){
                        list.add(newMaterial);
                        manager.writeData("data.txt",list);
                    } else {
                        System.out.println("ID BI TRUNG VOI SAN PHAM DA CO. MOI NHAP LAI!!!");
                    }
                    break;
                case 2:
                    System.out.print("Nhap ma vat lieu can sua: ");
                    String idTemp = manager.stringInput();
                    list = manager.readData("data.txt");
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getId().equals(idTemp)){
                            Material materialTemp = list.get(i);
                            if (materialTemp instanceof CrispyFlour){
                                manager.editMaterialFlour((CrispyFlour) materialTemp);
                            }else if (materialTemp instanceof Meat){
                                manager.editMaterialMeat((Meat) materialTemp);
                            }
                            manager.writeData("data.txt",list);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Nhap ma vat lieu can xoa: ");
                    String idTemp3 = manager.stringInput();
                    list = manager.readData("data.txt");

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getId().equals(idTemp3)){
                            list.remove(list.get(i));
                            System.out.println("xoa thanh cong!");
                        }
                    }
                    manager.writeData("data.txt",list);
                    break;
                case 4:
                    System.out.println("Danh sach vat lieu hien co: ");
                    list = manager.readData("data.txt");
                    manager.showList(list);
                    break;
                case 5:
                    System.out.print("Tong tien cac vat lieu da gom chiet khau la: ");
                    list = manager.readData("data.txt");
                    System.out.printf("%.2f \n",manager.getSumCostApplyDiscount(list));
                    break;
                case 6:
                    list = manager.readData("data.txt");
                    manager.sortListByCost(list);
                    manager.writeData("data.txt",list);
                    System.out.println("Sap xep thanh cong!! ");
                    break;
                case 7:
                    list = manager.readData("data.txt");
                    System.out.printf("%-50s%15.2f\n","Tong tien vat lieu khong tinh chiet khau hom nay: ",manager.getSumCostNoDiscount(list));
                    System.out.printf("%-50s%15.2f\n","Tong tien vat lieu co chiet khau hom nay: ",manager.getSumCostApplyDiscount(list));
                    System.out.printf("%-50s%15.2f\n","Chenh lech: ",manager.getSumCostNoDiscount(list)- manager.getSumCostApplyDiscount(list));
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

}

