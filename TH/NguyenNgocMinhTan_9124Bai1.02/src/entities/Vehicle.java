package entities;

public class Vehicle {
    protected String id;
    protected int year;
    protected int month;

    public Vehicle(String id, int year, int month) {
        this.id = id;
        this.year = year;
        this.month = month;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", year=" + year +
                ", month=" + month +
                '}';
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vehicle(String id) {
        this.id = id;
    }

    public Vehicle() {
    }

}
