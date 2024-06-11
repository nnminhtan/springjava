package entities;

public class Truck extends Vehicle{
    private int weight;

    @Override
    public String toString() {
        return "Truck{" +
                "weight=" + weight +
                ", id='" + id + '\'' +
                ", year=" + year +
                ", month=" + month +
                '}';
    }

    public Truck(int weight, String id, int year, int month) {
        super(id, year, month);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Truck() {
    }

}
