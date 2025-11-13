public class Game {
    Parser parser;
    Question[] questions;
    Chatbot chatgpt;

    public Game() {
        parser = new Parser();
        questions = parser.GetQuestions("res/questions/questions.txt");
        chatgpt = new Chatbot("ChatGPT", Lesson.OOP, Lesson.IntroToProgrammingParadigms);
    }

    public void Start() {

    }
}
