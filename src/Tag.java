public class Tag {
    private final int id;
    private final String name;

    public Tag (String name) {
        this.name = name;
        this.id = generateId();
    }

    private int generateId() {
        return (int) (Math.random() *10000);
    }
    public int getId(){
        return this.id;
    }
    public String getName() {
        return this.name;
    }
}
