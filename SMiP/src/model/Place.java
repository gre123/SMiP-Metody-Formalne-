package model;

public class Place extends MyVertex {

    boolean isAlive;
    int resources;
    int capacity;
    int boundary;

    public Place(int id) {
        super(id, "Place");
        resources = 0;
        capacity = -1;
    }

    public Place(int id, String desc) {
        super(id, desc);
        resources = 0;
        capacity = -1;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        if (resources >= 0) {
            this.resources = resources;
        } else {
            this.resources = -1;
        }
    }

    public void incResources() {
        if (resources == -1) {
            return;
        }
        if (capacity == -1 || resources + 1 <= capacity) {
            resources++;
        }
    }

    public void incResources(int value) {
        if (resources == -1) {
            return;
        }
        if (capacity == -1 || resources + value <= capacity) {
            resources += value;
        }
    }

    public void decResources() {
        if (resources == -1) {
            return;
        }
        if (resources > 0) {
            resources--;
        }
    }

    public void decResources(int value) {
        if (resources == -1) {
            return;
        }
        if (resources - value >= 0) {
            resources -= value;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity >= 1) {
            this.capacity = capacity;
        } else {
            System.out.println("Ustawiam nieskończoną pojemność dla" + this.toString());
            this.capacity = -1;
        }
    }

    public int getBoundary() {
        return boundary;
    }

    public void setBoundary(int boundedness) {
        this.boundary = boundedness;
    }

    @Override
    public String toString() {
        return "P" + id + ":" + resources + "/" + ((capacity == -1) ? "∞ " : capacity);
    }

}
