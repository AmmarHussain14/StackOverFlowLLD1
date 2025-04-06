public class Comment {
    private final int id;
    private final String content;
    private final User author;
    private final String creationDate;

    public Comment(User author, String content, String date) {
        this.id = generateId();
        this.author = author;
        this.content = content;
        this.creationDate = date;
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    // Getters
    public int getId() { return id; }
    public User getAuthor() { return author; }
    public String getContent() { return content; }
    public String getCreationDate() { return creationDate; }
}
