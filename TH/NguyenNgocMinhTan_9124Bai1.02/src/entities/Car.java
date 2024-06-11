package entities;

public class Car extends Vehicle{
    private int seats;
    private boolean license;

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isLicense() {
        return license;
    }

    public void setLicense(boolean license) {
        this.license = license;
    }

    public Car(int seats, boolean license, String id, int year, int month) {
        super(id, year, month);
        this.seats = seats;
        this.license = license;
    }

    public Car() {
    }

    @Override
    public String toString() {
        return "Car{" +
                "seats=" + seats +
                ", license=" + license +
                ", id='" + id + '\'' +
                ", year=" + year +
                ", month=" + month +
                '}';
    }

    public void output(){
        System.out.println(toString());
    }

}
