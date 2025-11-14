import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Command {
    Ask, CopyPaste, Save
};

public class Game {
    Chatbot[] chatbots = { new Chatbot("ChatGPT"), new Chatbot("Gemini"),
            new Chatbot("Grok"), new Chatbot("Copilot"),
            new Chatbot("Claude"), new Chatbot("Deepseek"),
            new Chatbot("Perplexity") };
    List<Question> available_questions;
    List<Chatbot> available_chatbots;
    List<Lesson> available_lesson;
    List<Command> available_commands;
    Question selected_question;
    Lesson selected_lesson;
    Chatbot selected_chatbot;
    char player_answer;
    boolean hasLost;
    int round;

    Scanner scanner;
    GameStates game_state;
    Parser parser;

    public Game() {
        scanner = new Scanner(System.in);
        game_state = new GameStates(this);
        parser = new Parser();
        available_questions = parser.GetQuestions("res/questions/questions.txt");
        available_chatbots = new ArrayList<>(List.of(chatbots));
        available_lesson = new ArrayList<>(List.of(Lesson.values()));
        available_commands = new ArrayList<>(List.of(Command.values()));
        player_answer = ' ';
        hasLost = false;
        round = 1;
    }

    public void Start() {
        while (true) {
            game_state.TitleScreen();
            System.out.print("Input: ");
            char input = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            switch (input) {
                case 'P':
                    available_questions = parser.GetQuestions("res/questions/questions.txt");
                    available_chatbots = new ArrayList<>(List.of(chatbots));
                    available_lesson = new ArrayList<>(List.of(Lesson.values()));
                    available_commands = new ArrayList<>(List.of(Command.values()));
                    player_answer = ' ';
                    hasLost = false;
                    round = 1;

                    while (hasLost == false) {
                        if (round % 2 != 0) {
                            game_state.ChooseChatbot();
                            game_state.ChooseLesson();
                        }
                        game_state.AskQuestion();
                        CheckIfLose();

                        if (hasLost == true) {
                            game_state.LoseScreen();
                            break;
                        }

                        game_state.AskQuestion();
                        CheckIfLose();

                        if (hasLost == true) {
                            game_state.LoseScreen();
                            break;
                        }

                        if (round == 15) {
                            game_state.WinScreen();
                            break;
                        }

                    }
                    break;
                case 'G':
                    break;
                case 'Q':
                    System.out.println("Exiting the program...");
                    return;
            }
        }

    }

    public void CheckIfLose() {
        System.out.println("");
        System.out.println("Your answer was: " + player_answer);

        if (player_answer != selected_question.GetAnswer()) {
            if (available_commands.contains(Command.Save)) {
                System.out.println("Your answer was wrong! Using Save Command.");
                player_answer = selected_chatbot.CommandSave();
                available_commands.remove(Command.Save);
                CheckIfLose();
            } else {
                System.out.println("Your answer was wrong! You lose.");
                System.out.println("");
                hasLost = true;

                scanner.nextLine();
                System.out.println("Press the Enter key to continue...");
                scanner.nextLine();
            }

        } else {
            System.out.println("Your answer is correct! You move on the next round.");
            System.out.println("");

            scanner.nextLine();
            System.out.println("Press the Enter key to continue...");
            scanner.nextLine();

            round++;
        }
    }
}
