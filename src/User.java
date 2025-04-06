import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private final String username;
    private final String email;
    private int reputation;
    private final List<Question> questions;
    private final List<Answer> answers;
    private final List<Comment> comments;

    private static final int QUESTION_REPUTATION = 5;
    private static final int ANSWER_REPUTATION = 10;
    private static final int COMMENT_REPUTATION = 2;

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.comments = new ArrayList<>();
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
    }

    public Question askQuestion(String title, String content, List<String> tags, String date) {
        Question question = new Question(this, content, title, tags, date);
        questions.add(question);
        updateReputation(QUESTION_REPUTATION);
        return question;
    }

    public Answer answerQuestion(Question question, String content, String date) {
        Answer answer = new Answer(this, question, content, date);
        answers.add(answer);
        question.addAnswer(answer);
        updateReputation(ANSWER_REPUTATION);
        return answer;
    }

    public Comment addComment(Commentable commentable, String content, String date) {
        Comment comment = new Comment(this, content, date);
        comments.add(comment);
        commentable.addComment(comment);
        updateReputation(COMMENT_REPUTATION);
        return comment;
    }


    public void updateReputation(int value)  {
        this.reputation+=value;
        if(this.reputation < 0) {
            this.reputation = 0;
        }
    }

    // Getters
    public int getId() { return id; }
    public String getUsername() { return username; }
    public int getReputation() { return reputation; }
    public List<Question> getQuestions() { return new ArrayList<>(questions); }
    public List<Answer> getAnswers() { return new ArrayList<>(answers); }
    public List<Comment> getComments() { return new ArrayList<>(comments); }

}
