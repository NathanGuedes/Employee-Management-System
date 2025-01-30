package entities;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;

    List<Worker> workerList = new ArrayList<>();

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addWorker(Worker worker) {
        workerList.add(worker);
    }

    public void removeWorker(Worker worker) {
        workerList.remove(worker);
    }
}
