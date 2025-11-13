public class Game {
    Parser parser;
    Question[] questions;
    Chatbot[] chatbots;

    public Game() {
        parser = new Parser();
        questions = parser.GetQuestions("res/questions/questions.txt");
    }

    public void Start() {

    }
}
