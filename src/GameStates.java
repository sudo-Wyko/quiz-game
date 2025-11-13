import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class GameStates {
    Game game;
    Scanner scanner = new Scanner(System.in);

    public GameStates(Game game) {
        this.game = game;
    }

    public static void ClearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void TitleScreen() {
        ClearTerminal();
        String title = """
                   ▄████████    ▄████████     ███        ▄████████  ▄████████
                  ███    ███   ███    ███ ▀█████████▄   ███    ███ ███    ███
                  ███    ███   ███    █▀     ▀███▀▀██   ███    ███ ███    █▀
                  ███    ███   ███            ███   ▀   ███    ███ ███
                ▀███████████ ▀███████████     ███     ▀███████████ ███
                  ███    ███          ███     ███       ███    ███ ███    █▄
                  ███    ███    ▄█    ███     ███       ███    ███ ███    ███
                  ███    █▀   ▄████████▀     ▄████▀     ███    █▀  ████████▀

                       """;

        System.out.println(title);
        System.out.println("------------- Are You Smarter Than A Chatbot? -------------");

        System.out.println("");
        System.out.println("                        [P]lay");
        System.out.println("                        [G]uide");
        System.out.println("                        [Q]uit");
        System.out.println("");

        System.out.print("Input: ");
        return;
    }

    public void Play() {
        ChooseChatbot();
        ChooseLesson();
        AskQuestions();

    }

    public void Guide() {

    }

    public void Quit() {

    }

    public void ChooseChatbot() {
        ClearTerminal();
        String heading = """
                ▄█████ ▄▄ ▄▄  ▄▄▄   ▄▄▄   ▄▄▄▄ ▄▄▄▄▄   ▄▄ ▄▄  ▄▄▄  ▄▄ ▄▄ ▄▄▄▄    ▄█████ ▄▄ ▄▄  ▄▄▄ ▄▄▄▄▄▄ ▄▄▄▄   ▄▄▄ ▄▄▄▄▄▄
                ██     ██▄██ ██▀██ ██▀██ ███▄▄ ██▄▄    ▀███▀ ██▀██ ██ ██ ██▄█▄   ██     ██▄██ ██▀██  ██   ██▄██ ██▀██  ██
                ▀█████ ██ ██ ▀███▀ ▀███▀ ▄▄██▀ ██▄▄▄     █   ▀███▀ ▀███▀ ██ ██   ▀█████ ██ ██ ██▀██  ██   ██▄█▀ ▀███▀  ██
                        """;

        System.out.println(heading);
        System.out.println("------------- ------------------ -------------");

        System.out.println("");
        for (int i = 0; i < game.chatbots.length; i++) {
            if (game.chatbots[i].GetAvailability() == true)
                System.out.println("[" + (i + 1) + "] " + game.chatbots[i].GetName());
            else
                continue;
        }
        System.out.println("");

        int choice = scanner.nextInt();
        if (game.chatbots[choice].GetAvailability() == false)
            System.out.println("Chatbot is no longer available. Choose another");
        else
            game.selected_chatbot = game.chatbots[choice];

        game.chatbots[choice].SetAvailability(false);
    }

    public void ChooseLesson() {
        ClearTerminal();
        String heading = """
                ▄█████ ▄▄ ▄▄  ▄▄▄   ▄▄▄   ▄▄▄▄ ▄▄▄▄▄    ▄▄▄    ██     ▄▄▄▄▄  ▄▄▄▄  ▄▄▄▄  ▄▄▄  ▄▄  ▄▄
                ██     ██▄██ ██▀██ ██▀██ ███▄▄ ██▄▄    ██▀██   ██     ██▄▄  ███▄▄ ███▄▄ ██▀██ ███▄██
                ▀█████ ██ ██ ▀███▀ ▀███▀ ▄▄██▀ ██▄▄▄   ██▀██   ██████ ██▄▄▄ ▄▄██▀ ▄▄██▀ ▀███▀ ██ ▀██
                        """;

        System.out.println(heading);
        System.out.println("------------- ------------------ -------------");

        System.out.println("");
        for (int i = 0; i < game.available_lesson.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + game.available_lesson.get(i));
        }
        System.out.println("");

        int choice = scanner.nextInt();
        game.selected_lesson = game.available_lesson.get(choice);

        game.available_lesson.remove(choice);
    }

    public void AskQuestions() {
        ClearTerminal();
        List<Question> valid_questions = new ArrayList<>();

        for (Question q : game.available_questions) {
            if (q.GetLesson() == game.selected_lesson)
                valid_questions.add(q);
        }

        // question 1.
        int index = (int) (Math.random() * valid_questions.size());
        Question chosen_question = valid_questions.get(index);
        game.available_questions.remove(chosen_question);

        chosen_question.Print();
        game.selected_chatbot.Answer(game.selected_lesson, chosen_question);

        System.out.println("What do you want to do?");
        System.out.println("1. Give Answer");
        System.out.println("2. Use Command");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Answer: ");
            game.player_answer = scanner.next().toLowerCase().charAt(0);
            game.CheckIfLose();
        } else if (choice == 2) {
            System.out.println("Choose your command: ");
            for (int i = 0; i < game.available_commands.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + game.available_commands.get(i));
            }

            int command_choice = scanner.nextInt();
            switch (command_choice) {
                case 1:
                    game.selected_chatbot.CommandAsk();
                    break;
                case 2:
                    game.selected_chatbot.CommandCopyPaste();
                    break;
                case 3:
                    game.selected_chatbot.CommandSave();
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
            game.available_commands.remove(command_choice - 1);
            game.CheckIfLose();

        }

        // question 2.
        index = (int) (Math.random() * valid_questions.size());
        chosen_question = valid_questions.get(index);
        game.available_questions.remove(chosen_question);

        chosen_question.Print();
        game.selected_chatbot.Answer(game.selected_lesson, chosen_question);

        System.out.println("What do you want to do?");
        System.out.println("1. Give Answer");
        System.out.println("2. Use Command");
        choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Answer: ");
            game.player_answer = scanner.next().toLowerCase().charAt(0);
            game.CheckIfLose();
        } else if (choice == 2) {
            System.out.println("Choose your command: ");
            for (int i = 0; i < game.available_commands.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + game.available_commands.get(i));
            }

            int command_choice = scanner.nextInt();
            switch (command_choice) {
                case 1:
                    game.selected_chatbot.CommandAsk();
                    break;
                case 2:
                    game.selected_chatbot.CommandCopyPaste();
                    break;
                case 3:
                    game.selected_chatbot.CommandSave();
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
            game.CheckIfLose();

        }

    }
}
