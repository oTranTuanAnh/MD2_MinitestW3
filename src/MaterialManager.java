package src;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class MaterialManager {
    public int intInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
    public double doubleInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextDouble();
    }
    public String stringInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public double getSumCostNoDiscount(List<Material> list) {
        double totalCost = 0;
        for (Material e: list){
            totalCost += e.getAmount();
        }
        return totalCost;
    }
    public void sortListByCost(List<Material> list) {
        Comparator<Material> comparator = new Comparator<Material>() {
            @Override
            public int compare(Material o1, Material o2) {
                return (o1.getCost()-o2.getCost());
            }
        };
        Collections.sort(list,comparator);
    }
    public double getSumCostApplyDiscount(List<Material> list) {
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
    public void showList(List<Material> list) {
        for (Material material: list){
            System.out.println(material);
        }
    }
    public void editMaterialFlour(CrispyFlour m) {
        System.out.print("Sua ma vat lieu: ");
        String id = stringInput();
        m.setId(id);
        System.out.print("Sua ten vat lieu: ");
        String name = stringInput();
        m.setName(name);
        System.out.print("Sua ngay san xuat: ");
        int day = intInput();
        System.out.print("Sua thang san xuat: ");
        int month = intInput();
        System.out.print("Sua nam san xuat: ");
        int year = intInput();
        LocalDate manufacturingDate  = LocalDate.of(year, month ,day);
        m.setManufacturingDate(manufacturingDate);
        System.out.print("Sua gia vat lieu(VND): ");
        int cost = intInput();
        m.setCost(cost);
        System.out.print("Sua so luong(kg): ");
        double quantity = doubleInput();
        m.setQuantity(quantity);

    }
    public void editMaterialMeat(Meat m) {
        System.out.print("Sua ma vat lieu: ");
        String id = stringInput();
        m.setId(id);
        System.out.print("Sua ten vat lieu: ");
        String name = stringInput();
        m.setName(name);
        System.out.print("Sua ngay san xuat: ");
        int day = intInput();
        System.out.print("Sua thang san xuat: ");
        int month = intInput();
        System.out.print("Sua nam san xuat: ");
        int year = intInput();
        LocalDate manufacturingDate  = LocalDate.of(year, month ,day);
        m.setManufacturingDate(manufacturingDate);
        System.out.print("Sua gia vat lieu(VND): ");
        int cost = intInput();
        m.setCost(cost);
        System.out.print("Sua so luong(kg): ");
        double weight = doubleInput();
        m.setWeight(weight);

    }

    public Material createNewMeat(){
        System.out.print("Nhap ma vat lieu: ");
        String id = stringInput();
        System.out.print("Nhap ten vat lieu: ");
        String name = stringInput();
        System.out.print("Nhap ngay san xuat: ");
        int day = intInput();
        System.out.print("Nhap thang san xuat: ");
        int month = intInput();
        System.out.print("Nhap nam san xuat: ");
        int year = intInput();
        LocalDate manufacturingDate  = LocalDate.of(year, month ,day);
        System.out.print("Nhap gia vat lieu(VND): ");
        int cost = intInput();
        System.out.print("Nhap so luong(kg): ");
        double weight = doubleInput();
        return new Meat(id, name, manufacturingDate, cost,weight);
    }
    public Material createNewCrispyFlour(){
        System.out.print("Nhap ma vat lieu: ");
        String id = stringInput();
        System.out.print("Nhap ten vat lieu: ");
        String name = stringInput();
        System.out.print("Nhap ngay san xuat: ");
        int day = intInput();
        System.out.print("Nhap thang san xuat: ");
        int month = intInput();
        System.out.print("Nhap nam san xuat: ");
        int year = intInput();
        LocalDate manufacturingDate  = LocalDate.of(year, month ,day);
        System.out.print("Nhap gia vat lieu(VND): ");
        int cost = intInput();
        System.out.print("Nhap so luong(kg): ");
        double quantity = doubleInput();
        return new CrispyFlour(id, name, manufacturingDate, cost, quantity);
    }
    public List<Material> readData(String path){
        FileInputStream file = null;
        ObjectInputStream ois = null;
        List<Material> list = new ArrayList<>();
        try {
            file = new FileInputStream(path);
            ois = new ObjectInputStream(file);
            list = (List<Material>) ois.readObject();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }
    public void writeData(String path, List<Material> list){
        try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(list);
            oos.close();
            file.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
