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
    private final int capacity;
    private final int type;

    TrainInfo(String train_id, int capacity, int type) {
        this.train_id = train_id;
        this.capacity = capacity;
        this.type = type;
    }

    public String getTrain_id() {
        return train_id;
    }
    public int getCapacity() {
        return capacity;
    }
    public int getType() {
        return type;
    }
}
