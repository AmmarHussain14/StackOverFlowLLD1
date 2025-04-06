import java.util.ArrayList;
import java.util.Arrays;

public class StackOverFlowMain {
    public static void main(String[] args) {

        StackOverFlow stack = new StackOverFlow();

        String date  = "06/06/2025";

        //Create user
        User ammar = stack.createUser("Ammar", "Amm123@gmail.com");
        User afreen = stack.createUser("Afreen", "Ifra007@gmail.com");
        User mohit = stack.createUser("Mohit", "mmt234@gmail.com");

        Question ammarQuestion  = stack.askQuestion(ammar, "DSA for Google", "SDE GFG sheet 190+ questions",
                Arrays.asList("Google", "DSA"), date);

        User alice = stack.createUser("Alice", "alice@example.com");
        User bob = stack.createUser("Bob", "bob@example.com");
        User charlie = stack.createUser("Charlie", "charlie@example.com");

        // Alice asks a question
        Question javaQuestion = stack.askQuestion(alice, "What is polymorphism in Java?",
                "Can someone explain polymorphism in Java with an example?",
                Arrays.asList("java", "oop"), date);

        // Bob answers Alice's question
        Answer bobAnswer = stack.answerQuestion(bob, javaQuestion,
                "Polymorphism in Java is the ability of an object to take on many forms...",date);

        // Charlie comments on the question
        stack.addComment(charlie, javaQuestion, "Great question! I'm also interested in learning about this.", date);

        // Alice comments on Bob's answer
        stack.addComment(alice, bobAnswer, "Thanks for the explanation! Could you provide a code example?", date);

        // Charlie votes on the question and answer
        stack.voteQuestion(charlie, javaQuestion, 1);  // Upvote
        stack.voteAnswer(charlie, bobAnswer, 1);  // Upvote

        // Alice accepts Bob's answer
        //stack.acceptAnswer(bobAnswer);

        // Bob asks another question
        Question pythonQuestion = stack.askQuestion(bob, "How to use list comprehensions in Python?",
                "I'm new to Python and I've heard about list comprehensions. Can someone explain how to use them?",
                Arrays.asList("python", "list-comprehension"),date);

        // Alice answers Bob's question
        Answer aliceAnswer = stack.answerQuestion(alice, pythonQuestion,
                "List comprehensions in Python provide a concise way to create lists...", date);

        // Charlie votes on Bob's question and Alice's answer
        stack.voteQuestion(charlie, pythonQuestion, 1);  // Upvote
        stack.voteAnswer(charlie, aliceAnswer, 1);  // Upvote

        // Print out the current state
        System.out.println("Question: " + javaQuestion.getTitle());
        System.out.println("Asked by: " + javaQuestion.getAuthor().getUsername());
        System.out.println("Tags: " + javaQuestion.getTags().stream().map(Tag::getName).reduce((a, b) -> a + ", " + b).orElse(""));
        System.out.println("Votes: " + javaQuestion.getVoteCount());
        System.out.println("Comments: " + javaQuestion.getComments().size());
        System.out.println("\nAnswer by " + bobAnswer.getAuthor().getUsername() + ":");
        System.out.println(bobAnswer.getContent());
        System.out.println("Votes: " + bobAnswer.getVoteCount());
        System.out.println("Accepted: " + bobAnswer.isAccepted());
        System.out.println("Comments: " + bobAnswer.getComments().size());

        System.out.println("\nUser Reputations:");
        System.out.println("Alice: " + alice.getReputation());
        System.out.println("Bob: " + bob.getReputation());
        System.out.println("Charlie: " + charlie.getReputation());
    }
}
