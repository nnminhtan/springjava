import entities.Car;
import entities.Truck;
import entities.Vehicle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        List<Vehicle> vehicleList = new ArrayList<>();
        List<Car> carList = new ArrayList<>();
        List<Truck> truckList = new ArrayList<>();
        long money = 0;

        var scanner = new Scanner(System.in);
        var msg = """



Chương trình quản lý Xe
1. Thêm xe hoi
2. Them xe tai
3. Xuất thông tin tất cả các xe
4. Xuat thong tin xe ra file
5. Tìm kiếm xe co nhieu cho nhat
6. Lấy xe tai theo trong tai cao nhat
7. Tìm kiếm xe co bien so dep
8. So tien dang kiem dinh ky
9. Thoi gian dang kiem dinh ky
10. Tong so tien da dang kiem
0. Thoát
Chọn chức năng:""";
        int choice;
        do {
            System.out.print(msg);
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    Vehicle vehicle1 = new Vehicle("60AA-66666", 2020, 6);
                    Car car1 = new Car(6,true,
                            vehicle1.getId(),vehicle1.getYear(),vehicle1.getMonth());

                    Vehicle vehicle2 = new Vehicle("60AA-77877", 2016, 9);
                    Car car2 = new Car(8,false,
                            vehicle2.getId(),vehicle2.getYear(),vehicle2.getMonth());
                    Vehicle vehicle5 = new Vehicle("60AA-67589", 2016, 6);
                    Car car3 = new Car(6,false,
                            vehicle5.getId(),vehicle5.getYear(),vehicle5.getMonth());


                    // Create an ArrayList to store the books

                    // Add preset books to the ArrayList
                    vehicleList.add(vehicle1);
                    vehicleList.add(vehicle2);
                    vehicleList.add(vehicle5);
                    carList.add(car1);
                    carList.add(car2);
                    carList.add(car3);
                }
                case 2 -> {

                    Vehicle vehicle3 = new Vehicle("60AA-76451", 2020, 9);
                    Truck truck1 = new Truck(21,
                            vehicle3.getId(),vehicle3.getYear(),vehicle3.getMonth());

                    Vehicle vehicle4 = new Vehicle("60AA-59697", 2024, 3);
                    Truck truck2 = new Truck(8,
                            vehicle4.getId(),vehicle4.getYear(),vehicle4.getMonth());

                    Vehicle vehicle6 = new Vehicle("60AA-88888", 2019, 6);
                    Truck truck3 = new Truck(30,
                            vehicle6.getId(),vehicle6.getYear(),vehicle6.getMonth());

                    vehicleList.add(vehicle3);
                    vehicleList.add(vehicle4);
                    vehicleList.add(vehicle6);
                    truckList.add(truck1);
                    truckList.add(truck2);
                    truckList.add(truck3);

                }
                case 3 -> {
                    System.out.println("Thông tin tất cả xe: ");
                    carList.forEach(System.out::println);
                    truckList.forEach(System.out::println);

                }
                case 4 -> {
                    String filename = "vehicle_output.txt";

                    try {
                        FileWriter writer = new FileWriter(filename);

                        // Write Car list elements
                        writer.write("Cars:\n"); // Add a heading for Car list
                        for (Car car : carList) {
                            writer.write(car.toString() + "\n");
                        }

                        // Write Truck list elements
                        writer.write("\nTrucks:\n"); // Add a heading for Truck list
                        for (Truck truck : truckList) {
                            writer.write(truck.toString() + "\n");
                        }

                        writer.close();
                        System.out.println("Vehicle information written to file: " + filename);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                case 5 -> {
                    System.out.println("thong tin xe co nhieu cho ngoi nhat:");
                    carList.stream()
                            .max(Comparator.comparingInt(Car::getSeats))
                            .ifPresent(System.out::println);
                }
                case 6 -> {
                    System.out.println("sap xep danh sach xe tai theo trong tai tang dan:");
                    truckList.stream()
                            .sorted(Comparator.comparingInt(Truck::getWeight))
                            .forEach(System.out::println);

                }
                case 7 -> {
                    System.out.println("xuat danh sach cac bien so xe dep:");
                    Pattern pattern = Pattern.compile(".*([0-9])\\1{3}.*");

                    carList.stream()
                            .map(Car::getId)
                            .filter(plate -> pattern.matcher(plate.substring(plate.length() - 5)).matches())
                            .forEach(System.out::println);

                    truckList.stream()
                            .map(Truck::getId)
                            .filter(plate -> pattern.matcher(plate.substring(plate.length() - 5)).matches())
                            .forEach(System.out::println);
                }
                case 8 -> {
                    carList.forEach(car -> {
                        int seats = car.getSeats();
                        int moneyPerCar = calculateMoneyPerSeat(seats);
                        System.out.println("Car with " + seats + " seats: " + moneyPerCar + "000");
                    });

                    truckList.forEach(truck -> {
                        int weight = truck.getWeight();
                        int moneyPerTruck = calculateMoneyPerTon(weight);
                        System.out.println("Truck with " + weight + " tons: " + moneyPerTruck + "000");
                    });
                }
                case 9 -> {
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    //System.out.println(currentYear);
                    System.out.println("Danh sach xe o to va thoi gian kiem tra dinh ki:");
                    carList.stream().forEach(car -> {
                        int yearsInProduction = currentYear - car.getYear(); // nam san xuat
                        int inspectionYear; // nam xay ra kiem tra dinh ki
                        int inspectionMonth; // thang xay ra kiem tra dinh ki

                        if (yearsInProduction <= 7) {
                            if (car.getSeats() <= 9) {
                                if (car.isLicense()) {
                                    inspectionYear = currentYear + 1;
                                    inspectionMonth = car.getMonth();
                                } else {
                                    inspectionYear = currentYear + 2;
                                    inspectionMonth = car.getMonth();
                                }
                            } else {
                                inspectionYear = currentYear + 1;
                                inspectionMonth = car.getMonth();
                            }
                        } else {
                            inspectionYear = currentYear;
                            inspectionMonth = car.getMonth() + 6;
                            if (inspectionMonth > 12) {
                                inspectionMonth -= 12;
                                inspectionYear += 1;
                            }
                        }
                        System.out.println("Thoi gian kiem tra dinh ki cua " + car + ": " + inspectionMonth + "/" + inspectionYear);
                    });

                    System.out.println("Danh sach xe tai va thoi gian kiem tra dinh ki:");
                    truckList.stream().forEach(truck -> {
                        int yearsInProduction = currentYear - truck.getYear();
                        int inspectionYear = currentYear;
                        int inspectionMonth = truck.getMonth();

                        if (yearsInProduction <= 20) {
                            inspectionMonth += 3;
                        } else {
                            inspectionMonth += 6;
                        }

                        if (inspectionMonth > 12) {
                            inspectionMonth -= 12;
                            inspectionYear += 1;
                        }

                        System.out.println("Thoi gian kiem tra dinh ki cua " + truck + ": " + inspectionMonth + "/" + inspectionYear);
                    });
                }
                case 10 -> {
                    AtomicInteger allmoney = new AtomicInteger();
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
                    //System.out.println(currentYear);
                    System.out.println("Danh sach xe o to va tong so tien da kiem:");
                    carList.stream().forEach(car -> {
                        int yearsInProduction = currentYear - car.getYear(); // nam san xuat
                        int inspectionYear; // nam xay ra kiem tra dinh ki
                        int inspectionMonth; // thang xay ra kiem tra dinh ki
                        int totalInspections = 0; // so klan dang kiem
                        int feePerInspection = 0;

                        if (yearsInProduction <= 7) {
                            if (car.getSeats() <= 9) {
                                if (car.isLicense()) {
                                    inspectionYear = currentYear + 1;
                                    inspectionMonth = car.getMonth();
                                    totalInspections = (yearsInProduction * 12 + currentMonth - car.getMonth()) / 12;
                                } else {
                                    inspectionYear = currentYear + 2;
                                    inspectionMonth = car.getMonth();
                                    totalInspections = (yearsInProduction * 12 + currentMonth - car.getMonth()) / 24;
                                }
                            } else {
                                inspectionYear = currentYear + 1;
                                inspectionMonth = car.getMonth();
                                totalInspections = (yearsInProduction * 12 + currentMonth - car.getMonth()) / 12;
                            }
                        } else {
                            inspectionYear = currentYear;
                            inspectionMonth = car.getMonth() + 6;
                            if (inspectionMonth > 12) {
                                inspectionMonth -= 12;
                                inspectionYear += 1;
                            }
                            totalInspections = yearsInProduction * 2;
                        }
                        int totalMoney = (car.getSeats() > 10) ? totalInspections * 320000 : totalInspections * 240000;
                        System.out.println("Tong so lan dang kiem cua " + car + ": " + totalInspections
                                + " so tien: " + totalMoney);
                        allmoney.addAndGet(totalMoney);

                    });

                    System.out.println("Danh sach xe tai va tong so tien da kiem:");
                    truckList.stream().forEach(truck -> {
                        int yearsInProduction = currentYear - truck.getYear();
                        int inspectionYear = currentYear;
                        int inspectionMonth = truck.getMonth();
                        int totalInspections = 0;
                        int feePerTruckInspection = 0;

                        if (yearsInProduction <= 20) {
                            inspectionMonth += 3;
                            totalInspections = (yearsInProduction * 12 + currentMonth - truck.getMonth()) / 3;
                        } else {
                            inspectionMonth += 6;
                            totalInspections = yearsInProduction * 2; // Every 6 months
                        }

                        if (inspectionMonth > 12) {
                            inspectionMonth -= 12;
                            inspectionYear += 1;
                        }

                        if (truck.getWeight() > 20) {
                            feePerTruckInspection = 560000;
                        } else if (truck.getWeight() >= 7 && truck.getWeight() <= 20) {
                            feePerTruckInspection = 350000;
                        } else {
                            feePerTruckInspection = 320000;
                        }

                        int totalMoney = totalInspections * feePerTruckInspection;
                        System.out.println("Tong so lan dang kiem cua " + truck + ": " + totalInspections
                                + " so tien: " + totalMoney);
                        allmoney.addAndGet(totalMoney);

                    });
                    System.out.println("total money = " + allmoney);
                }
                case 0 -> System.out.println("Đã thoát");
                default -> throw new IllegalStateException();
            }
        } while (choice != 0);
    }

    private static int calculateMoneyPerTon(int weight) {
        if(weight > 20) {
            int price = 560;
            return weight * price;
        }else if(weight <= 20 && weight >= 7){
            int price = 350;
            return  weight * price;
        }else {
            int price = 320;
            return weight * price;
        }

    }

    private static int calculateMoneyPerSeat(int seats) {
        if(seats <= 10){
            int price = 240; // Replace with your actual price
            return seats * price;
        }
        else{
            int price = 320; // Replace with your actual price
            return seats * price;
        }
    }


}
