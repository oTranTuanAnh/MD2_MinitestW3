import src.CrispyFlour;
import src.Material;
import src.Meat;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Material> list = new ArrayList<>();

        Material flour1 = new CrispyFlour("12","fl1", LocalDate.of(2022,12, 10),135,5.5);
        Material flour2 = new CrispyFlour("15","fl2", LocalDate.of(2023,1, 25),128,7);
        Material flour3 = new CrispyFlour("22","fl3", LocalDate.of(2023,2, 12),80,15);
        Material flour4 = new CrispyFlour("18","fl4", LocalDate.of(2023,5, 25),100,10);
        Material flour5 = new CrispyFlour("25","fl5", LocalDate.of(2023,10, 28),95,8);

        Material meat1 = new Meat("50","m1",LocalDate.of(2023, 12, 15), 135,5);
        Material meat2 = new Meat("52","m2",LocalDate.of(2023, 12, 18), 120,8);
        Material meat3 = new Meat("55","m3",LocalDate.of(2023, 12, 20), 130,10);
        Material meat4 = new Meat("56","m4",LocalDate.of(2023, 12, 21), 125,20);
        Material meat5 = new Meat("58","m5",LocalDate.of(2023, 12, 14), 122,18);

        list.add(flour1);
        list.add(flour2);
        list.add(flour3);
        list.add(flour4);
        list.add(flour5);
        list.add(meat1);
        list.add(meat2);
        list.add(meat3);
        list.add(meat4);
        list.add(meat5);

        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while (choice != 0){
            System.out.println("------------------------------------------------------------------");
            System.out.println("MENU");
            System.out.println("1. Tinh tong tien vat lieu");
            System.out.println("2. Sap xep vat lieu theo gia");
            System.out.println("3. Chenh lech giua chiet khau va khong chiet khau ngay hom nay");
            System.out.println("0. Exit");
            System.out.print("NHAP LUA CHON CUA BAN: ");
            choice = sc.nextInt();

            switch (choice){
                case 1:
                    System.out.print("Tong tien cac vat lieu la: ");
                    System.out.printf("%.3f \n",getSumCostApplyDiscount(list));
                    break;
                case 2:
                    System.out.println("Sap xep vat lieu theo gia ");
                    arrangeListByCost(list);
                    showList(list);
                    break;
                case 3:
                    System.out.printf("%-50s%15.3f\n","Tong tien vat lieu khong tinh chiet khau hom nay: ",getSumCostNoDiscount(list));
                    System.out.printf("%-50s%15.3f\n","Tong tien vat lieu co chiet khau hom nay: ",getSumCostApplyDiscount(list));
                    System.out.printf("%-50s%15.3f\n","Chenh lech: ",getSumCostNoDiscount(list)-getSumCostApplyDiscount(list));
                    break;
                case 0:
                    System.exit(0);
            }
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

