import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Command {
    Ask, CopyPaste, Save
};

public class Game {
    Scanner scanner;
    GameStates game_state;
    Parser parser;
    List<Question> available_questions;
    Chatbot[] chatbots = new Chatbot[7];
    List<Lesson> available_lesson;
    List<Command> available_commands;
    Lesson selected_lesson;
    Chatbot selected_chatbot;
    char player_answer;

    public Game() {
        scanner = new Scanner(System.in);
        game_state = new GameStates(this);
        parser = new Parser();
        available_questions = parser.GetQuestions("res/questions/questions.txt");
        available_lesson = new ArrayList<>(List.of(Lesson.values()));
        available_commands = new ArrayList<>(List.of(Command.values()));
        chatbots[0] = new Chatbot("ChatGPT");
        chatbots[1] = new Chatbot("Gemini");
        chatbots[2] = new Chatbot("Grok");
        chatbots[3] = new Chatbot("Copilot");
        chatbots[4] = new Chatbot("Claude");
        chatbots[5] = new Chatbot("Deepseek");
        chatbots[6] = new Chatbot("Perplexity");
        player_answer = ' ';
    }

    public void Start() {
        game_state.Play();
        char choice = scanner.nextLine().toUpperCase().charAt(0);
        if (choice == 'p')
            game_state.Play();
    }

    public void CheckIfLose() {

    }
}
