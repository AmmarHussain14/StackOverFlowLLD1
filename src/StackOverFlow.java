import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class StackOverFlow {
    private final Map<Integer, User> users;
    private final Map<Integer, Question> questions;
    private final Map<Integer, Answer> answers;
    private final Map<String, Tag> tags;

    public StackOverFlow() {
        this.tags = new ConcurrentHashMap<>();
        this.users = new ConcurrentHashMap<>();
        this.answers = new ConcurrentHashMap<>();
        this.questions = new ConcurrentHashMap<>();
    }

    public User createUser(String name, String mail) {
        int id = users.size() + 1;
        User user = new User(id, name, mail);
        users.put(id, user);
        return user;
    }

    public Question askQuestion(User user, String title, String content, List<String> tags, String date) {
        Question question = new Question(user, content, title, tags, date);
        questions.put(question.getId(), question);
        for(Tag tag : question.getTags()) {
            this.tags.putIfAbsent(tag.getName(), tag);
        }
        return question;
    }

    public Answer answerQuestion (User user, Question question, String content, String date) {
        Answer answer = user.answerQuestion(question, content, date);
        answers.put(answer.getId(), answer);
        return answer;
    }

    public Comment addComment(User user, Commentable commentable, String content ,String date) {
        return user.addComment(commentable ,content, date);
    }

    public void voteAnswer(User user, Answer answer, int value) {
        answer.vote(user, value);
    }

    public void voteQuestion(User user, Question question, int value) {
        question.vote(user, value);
    }

    public List<Question> searchQuestions(String query) {
        return questions.values().stream()
                .filter(q -> q.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        q.getContent().toLowerCase().contains(query.toLowerCase()) ||
                        q.getTags().stream().anyMatch(t -> t.getName().equalsIgnoreCase(query)))
                .collect(Collectors.toList());
    }

    public List<Question> getQuestionsByUser(User user) {
        return user.getQuestions();
    }

    // Getters
    public User getUser(int id) { return users.get(id); }
    public Question getQuestion(int id) { return questions.get(id); }
    public Answer getAnswer(int id) { return answers.get(id); }
    public Tag getTag(String name) { return tags.get(name); }
}
