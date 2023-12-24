import src.CrispyFlour;
import src.Material;
import src.Meat;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Material> list = new ArrayList<>();
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

            switch (choice){
                case 1:
                    System.out.print("Nhap vat lieu can them  <F> flour hay <M> meat: ");
                    Scanner sc = new Scanner(System.in);
                    String input = sc.nextLine();
                    if (input.equals("F")){
                        createNewMaterial result = getCreateNewMaterial();
                        System.out.print("Nhap so luong(kg): ");
                        double quantity = sc.nextDouble();
                        Material m = new CrispyFlour(result.id, result.name, result.manufacturingDate, result.cost, quantity);
                        list.add(m);
                    } else if (input.equals("M")){
                        createNewMaterial result = getCreateNewMaterial();
                        System.out.print("Nhap so luong(kg): ");
                        double weight = sc.nextDouble();
                        Material m = new Meat(result.id, result.name, result.manufacturingDate, result.cost, weight);
                        list.add(m);
                    }else {
                        System.out.println("Nhap sai. Moi nhap lai");
                        break;
                    }
                    break;
                case 2:
                    System.out.print("Nhap ma vat lieu can sua: ");
                    Scanner sc2 = new Scanner(System.in);
                    String id2 = sc2.nextLine();
                    for (Material m: list){
                        if (m.getId().equals(id2)){
                            if (m instanceof CrispyFlour){
                                editMaterial(m);
                                System.out.print("Sua so luong(kg): ");
                                double quantity = sc2.nextDouble();
                                ((CrispyFlour) m).setQuantity(quantity);
                            } else {
                                editMaterial(m);
                                System.out.print("Sua so luong(kg): ");
                                double weight = sc2.nextDouble();
                                ((Meat) m).setWeight(weight);
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.print("Nhap ma vat lieu can xoa: ");
                    Scanner sc3 = new Scanner(System.in);
                    String id = sc3.nextLine();
                    for (Material m: list){
                        if (m.getId().equals(id)){
                            boolean isRemoved = list.remove(m);
                            if (isRemoved){
                                System.out.println("xoa thanh cong!");
                            } else {
                                System.out.println("Chua xoa duoc!");
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("Danh sach vat lie hien co: ");
                    showList(list);
                    break;
                case 5:
                    System.out.print("Tong tien cac vat lieu da gom chiet khau la: ");
                    System.out.printf("%.3f \n",getSumCostApplyDiscount(list));
                    break;
                case 6:
                    System.out.println("Sap xep vat lieu theo gia ");
                    arrangeListByCost(list);
                    showList(list);
                    break;
                case 7:
                    System.out.printf("%-50s%15.3f\n","Tong tien vat lieu khong tinh chiet khau hom nay: ",getSumCostNoDiscount(list));
                    System.out.printf("%-50s%15.3f\n","Tong tien vat lieu co chiet khau hom nay: ",getSumCostApplyDiscount(list));
                    System.out.printf("%-50s%15.3f\n","Chenh lech: ",getSumCostNoDiscount(list)-getSumCostApplyDiscount(list));
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    private static void editMaterial(Material m) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Sua ma vat lieu: ");
        String id = sc.nextLine();
        m.setId(id);
        System.out.print("Sua ten vat lieu: ");
        String name = sc.nextLine();
        m.setName(name);
        System.out.print("Sua ngay san xuat: ");
        int day = sc.nextInt();
        System.out.print("Sua thang san xuat: ");
        int month = sc.nextInt();
        System.out.print("Sua nam san xuat: ");
        int year = sc.nextInt();
        LocalDate manufacturingDate  = LocalDate.of(year, month ,day);
        m.setManufacturingDate(manufacturingDate);
        System.out.print("Sua gia vat lieu(VND): ");
        int cost = sc.nextInt();
        m.setCost(cost);
    }

    private static createNewMaterial getCreateNewMaterial() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma vat lieu: ");
        String id = sc.nextLine();
        System.out.print("Nhap ten vat lieu: ");
        String name = sc.nextLine();
        System.out.print("Nhap ngay san xuat: ");
        int day = sc.nextInt();
        System.out.print("Nhap thang san xuat: ");
        int month = sc.nextInt();
        System.out.print("Nhap nam san xuat: ");
        int year = sc.nextInt();
        LocalDate manufacturingDate  = LocalDate.of(year, month ,day);
        System.out.print("Nhap gia vat lieu(VND): ");
        int cost = sc.nextInt();
        createNewMaterial result = new createNewMaterial(id, name, manufacturingDate, cost);
        return result;
    }

    private static class createNewMaterial {
        public final String id;
        public final String name;
        public final LocalDate manufacturingDate;
        public final int cost;

        public createNewMaterial(String id, String name, LocalDate manufacturingDate, int cost) {
            this.id = id;
            this.name = name;
            this.manufacturingDate = manufacturingDate;
            this.cost = cost;
        }
    }

    private static void showList(ArrayList<Material> list) {
        for (Material e: list){
            System.out.println(e);
        }
    }

    private static void arrangeListByCost(ArrayList<Material> list) {
        Comparator<Material> comparator = new Comparator<Material>() {
            @Override
            public int compare(Material o1, Material o2) {
                return (o1.getCost()-o2.getCost());
            }
        };
        Collections.sort(list, comparator);
    }

    private static double getSumCostApplyDiscount(ArrayList<Material> list) {
        double total = 0;
        for (Material e: list){
            if (e instanceof CrispyFlour){
                CrispyFlour flours = (CrispyFlour) e;
                total += flours.getRealMoney();
            }
            if (e instanceof Meat){
                Meat meats = (Meat) e;
                total += meats.getRealMoney();
            }
        }
        return total;
    }
    private static double getSumCostNoDiscount(ArrayList<Material> list) {
        double total = 0;
        for (Material e: list){
            total += e.getAmount();
        }
        return total;
    }
}

