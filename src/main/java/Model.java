public class Model {

    private String category;
    private String make;
    private String model;

    public Model() {
    }

    public Model(String category, String make, String model) {
        this.category = category;
        this.make = make;
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Model {" +
                "category='" + category + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
