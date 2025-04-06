import java.util.ArrayList;
import java.util.List;

public class Question implements Votable, Commentable {
    private final int id;
    private final String title;
    private final String content;
    private final User author;
    private final String date;
    private final List<Answer> answers;
    private final List<Comment> comments;
    private final List<Tag> tags;
    private final List<Vote> votes;

    public Question(User author, String content, String title, List<String> tagNames, String date) {
        this.id = generateId();
        this.author = author;
        this.content = content;
        this.title = title;
        this.date = date;
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.votes = new ArrayList<>();
        for(String tag : tagNames) {
            this.tags.add(new Tag(tag));
        }

    }

    public void addAnswer(Answer answer) {
        if(!answers.contains(answer)) {
            answers.add(answer);
        }
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public void vote(User user, int value) {
        if(value != 1 && value != -1) {
            throw new IllegalArgumentException("Vote must be 1 or -1");
        }
        votes.removeIf(v -> v.getUser().equals(user));
        votes.add(new Vote(user, value));
        author.updateReputation(value * 5);
    }

    @Override
    public int getVoteCount() {
        //get total vote counts;
        //we can also use Loop
        return votes.stream().mapToInt(Vote::getValue).sum();
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    public int getId() {return id;}
    public User getAuthor() { return author;}
    public List<Answer> getAnswers() {return answers;}
    public String getTitle() {return title;}
    public List<Vote> getVotes() {return votes;}
    public List<Tag> getTags() {return new ArrayList<>(tags);}
    public String getDate() {return date;}
    public String getContent() {return content;}
}
