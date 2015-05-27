package model;

/**
 * @author Elpidiusz
 */
public class Place extends MyVertex {

    boolean isAlive;
    int resources;
    int capacity;
    int boundary;

    public Place(int id) {
        super(id, "Place");
        resources = 0;
        capacity = Integer.MAX_VALUE;
    }

    public Place(int id, String desc) {
        super(id, desc);
        resources = 0;
        capacity = Integer.MAX_VALUE;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        if (resources >= 0) {
            this.resources = resources;
        } else {
            throw new IllegalArgumentException("nie można ustawić ujemnej ilości znaczników");
        }
    }

    public void incResources() {
        if (resources + 1 <= capacity) {
            resources++;
        }
    }

    public void incResources(int value) {
        if (resources + value <= capacity) {
            resources += value;
        }
    }

    public void decResources() {
        if (resources > 0) {
            resources--;
        }
    }

    public void decResources(int value) {
        if (resources - value >= 0) {
            resources -= value;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity >= 0) {
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException("nie można ustawić ujemnej pojemności");
        }
    }

    public int getBoundary() {
        return boundary;
    }

    public void setBoundary(int boundedness) {
        this.boundary = boundedness;
    }

    public String toString() {
        return "P" + id + ":" + resources;
    }

}
