package sport;

public class Training {

    private String trainingName;

    public Training() {
    }

    public Training(String trainingName) {
        this.trainingName = trainingName;
    }

    @Override
    public String toString() {
        return "Training:" + trainingName;
    }
}