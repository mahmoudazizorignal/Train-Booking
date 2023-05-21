public class TrainInfo {
    private String train_id;
    private int capacity;
    private int type;

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
