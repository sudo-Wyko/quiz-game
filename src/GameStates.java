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

        return;
    }

    public void Play() {
        ChooseChatbot();
        ChooseLesson();
        AskQuestion();

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
        for (int i = 0; i < game.available_chatbots.size(); i++) {
            System.out.println("[" + i + "] " + game.available_chatbots.get(i).GetName());
        }
        System.out.println("");

        int choice = scanner.nextInt();
        scanner.nextLine();
        game.selected_chatbot = game.available_chatbots.get(choice);
        game.available_chatbots.remove(choice);
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
            System.out.println("[" + i + "] " + game.available_lesson.get(i));
        }
        System.out.println("");

        int choice = scanner.nextInt();
        scanner.nextLine();
        game.selected_lesson = game.available_lesson.get(choice);

        game.available_lesson.remove(choice);

        ClearTerminal();
        heading = """
                ▄█████ ▄▄ ▄▄  ▄▄▄ ▄▄▄▄▄▄ ▄▄▄▄   ▄▄▄ ▄▄▄▄▄▄   ▄█████ ▄▄▄▄▄▄ ▄▄▄ ▄▄▄▄▄▄ ▄▄▄▄
                ██     ██▄██ ██▀██  ██   ██▄██ ██▀██  ██     ▀▀▀▄▄▄   ██  ██▀██  ██  ███▄▄
                ▀█████ ██ ██ ██▀██  ██   ██▄█▀ ▀███▀  ██     █████▀   ██  ██▀██  ██  ▄▄██▀
                """;

        System.out.println(heading);
        System.out.println("------------- ------------------ -------------");

        System.out.println("");
        game.selected_chatbot.PrintStats();
        System.out.println("");

        System.out.println("Press the Enter key to continue...");
        scanner.nextLine();
    }

    public void AskQuestion() {
        ClearTerminal();

        List<Question> valid_questions = new ArrayList<>();

        for (Question q : game.available_questions) {
            if (q.GetLesson() == game.selected_lesson)
                valid_questions.add(q);
        }

        int index = (int) (Math.random() * valid_questions.size());
        game.selected_question = valid_questions.get(index);
        game.available_questions.remove(game.selected_question);

        game.selected_question.Print();
        game.selected_chatbot.Answer(game.selected_lesson, game.selected_question);

        System.out.println("What do you want to do?");
        System.out.println("[0] Give Answer");
        System.out.println("[1] Use Command");
        System.out.println("");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 0) {
            System.out.println("");
            System.out.print("Answer: ");
            game.player_answer = scanner.next().toUpperCase().charAt(0);
        } else if (choice == 1) {
            System.out.println("");
            System.out.println("Choose your command: ");
            for (int i = 0; i < game.available_commands.size(); i++) {
                System.out.println("[" + i + "] " + game.available_commands.get(i));
            }

            System.out.println("");
            int command_choice = scanner.nextInt();
            System.out.println("");

            if (game.available_commands.get(command_choice) == Command.Ask)
                game.player_answer = game.selected_chatbot.CommandAsk();
            else if (game.available_commands.get(command_choice) == Command.CopyPaste)
                game.player_answer = game.selected_chatbot.CommandCopyPaste();
            else if (game.available_commands.get(command_choice) == Command.Save)
                game.player_answer = game.selected_chatbot.CommandSave();
            else {
                System.out.println("Invalid input.");
                System.out.println("");
            }

            game.available_commands.remove(command_choice);
        }

    }

    public void LoseScreen() {
        ClearTerminal();
        String heading = """
                ▓██   ██▓ ▒█████   █    ██     ▄▄▄       ██▀███  ▓█████    ▓█████▄  █    ██  ███▄ ▄███▓ ▄▄▄▄   ▓█████  ██▀███   ▐██▌
                 ▒██  ██▒▒██▒  ██▒ ██  ▓██▒   ▒████▄    ▓██ ▒ ██▒▓█   ▀    ▒██▀ ██▌ ██  ▓██▒▓██▒▀█▀ ██▒▓█████▄ ▓█   ▀ ▓██ ▒ ██▒ ▐██▌
                  ▒██ ██░▒██░  ██▒▓██  ▒██░   ▒██  ▀█▄  ▓██ ░▄█ ▒▒███      ░██   █▌▓██  ▒██░▓██    ▓██░▒██▒ ▄██▒███   ▓██ ░▄█ ▒ ▐██▌
                  ░ ▐██▓░▒██   ██░▓▓█  ░██░   ░██▄▄▄▄██ ▒██▀▀█▄  ▒▓█  ▄    ░▓█▄   ▌▓▓█  ░██░▒██    ▒██ ▒██░█▀  ▒▓█  ▄ ▒██▀▀█▄   ▓██▒
                  ░ ██▒▓░░ ████▓▒░▒▒█████▓     ▓█   ▓██▒░██▓ ▒██▒░▒████▒   ░▒████▓ ▒▒█████▓ ▒██▒   ░██▒░▓█  ▀█▓░▒████▒░██▓ ▒██▒ ▒▄▄
                   ██▒▒▒ ░ ▒░▒░▒░ ░▒▓▒ ▒ ▒     ▒▒   ▓▒█░░ ▒▓ ░▒▓░░░ ▒░ ░    ▒▒▓  ▒ ░▒▓▒ ▒ ▒ ░ ▒░   ░  ░░▒▓███▀▒░░ ▒░ ░░ ▒▓ ░▒▓░ ░▀▀▒
                 ▓██ ░▒░   ░ ▒ ▒░ ░░▒░ ░ ░      ▒   ▒▒ ░  ░▒ ░ ▒░ ░ ░  ░    ░ ▒  ▒ ░░▒░ ░ ░ ░  ░      ░▒░▒   ░  ░ ░  ░  ░▒ ░ ▒░ ░  ░
                 ▒ ▒ ░░  ░ ░ ░ ▒   ░░░ ░ ░      ░   ▒     ░░   ░    ░       ░ ░  ░  ░░░ ░ ░ ░      ░    ░    ░    ░     ░░   ░     ░
                 ░ ░         ░ ░     ░              ░  ░   ░        ░  ░      ░       ░            ░    ░         ░  ░   ░      ░
                 ░ ░                                                        ░                                ░
                                        """;

        System.out.println(heading);

        System.out.println("");
        scanner.nextLine();
        System.out.println("Press the Enter key to continue...");
        scanner.nextLine();
    }

    public void WinScreen() {
        ClearTerminal();
        String heading = """
                ██╗   ██╗ ██████╗ ██╗   ██╗     █████╗ ██████╗ ███████╗    ███████╗███╗   ███╗ █████╗ ██████╗ ████████╗███████╗██████╗
                ╚██╗ ██╔╝██╔═══██╗██║   ██║    ██╔══██╗██╔══██╗██╔════╝    ██╔════╝████╗ ████║██╔══██╗██╔══██╗╚══██╔══╝██╔════╝██╔══██╗
                 ╚████╔╝ ██║   ██║██║   ██║    ███████║██████╔╝█████╗      ███████╗██╔████╔██║███████║██████╔╝   ██║   █████╗  ██████╔╝
                  ╚██╔╝  ██║   ██║██║   ██║    ██╔══██║██╔══██╗██╔══╝      ╚════██║██║╚██╔╝██║██╔══██║██╔══██╗   ██║   ██╔══╝  ██╔══██╗
                   ██║   ╚██████╔╝╚██████╔╝    ██║  ██║██║  ██║███████╗    ███████║██║ ╚═╝ ██║██║  ██║██║  ██║   ██║   ███████╗██║  ██║
                   ╚═╝    ╚═════╝  ╚═════╝     ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝    ╚══════╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝

                                        """;

        System.out.println(heading);

        System.out.println("");

        scanner.nextLine();
        System.out.println("Press the Enter key to continue...");
        scanner.nextLine();
    }
}
