/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package train_booking;

/**
 *
 * @author mahmo
 */
public class TrainInfo {
    private final String train_id;
    private int capacity;
    private String type;

    TrainInfo(String train_id, int capacity, String type) {
        this.train_id = train_id;
        this.capacity = capacity;
        this.type = type;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTrain_id() {
        return train_id;
    }
    public int getCapacity() {
        return capacity;
    }
    public String getType() {
        return type;
    }
}
