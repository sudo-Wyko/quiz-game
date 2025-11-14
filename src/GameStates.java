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
        ClearTerminal();
        String guide = """
                ▄▖  ▘ ▌
                ▌ ▌▌▌▛▌█▌
                ▙▌▙▌▌▙▌▙▖

                Below is all you need to know to start playing the game. Read carefully and enjoy!

                ▖▖              ▜
                ▌▌▛▌▌▌▛▘  ▛▌▛▌▀▌▐
                ▐ ▙▌▙▌▌   ▙▌▙▌█▌▐▖
                          ▄▌
                The goal is prove you are smarter than an AI!
                - Answer all 14 questions correctly to win.
                - The game has 7 subjects, with 2 questions each.

                ▄▖▌         ▜
                ▐ ▛▌█▌  ▛▘▌▌▐ █▌▛▘
                ▐ ▌▌▙▖  ▌ ▙▌▐▖▙▖▄▌

                - Winning: Answer all 14 questions to achieve a 100% score and win the game.
                - Losing: Get one answer wrong, and the game ends immediately! (Unless the Save command is available).

                ▄▖         ▜       ▐▘▜
                ▌ ▀▌▛▛▌█▌▛▌▐ ▀▌▌▌  ▜▘▐ ▛▌▌▌▌
                ▙▌█▌▌▌▌▙▖▙▌▐▖█▌▙▌  ▐ ▐▖▙▌▚▚▘
                          ▌    ▄▌
                For each odd round, you will:
                - Choose a Chatbot: Pick one of the 7 AI assistants.
                - Choose a Subject: Select a subject from the board.
                - Specialty Revealed: The chatbot's strength or weakness for that subject is now revealed.
                - Answer: Answer the question or use a command.

                ▄▖▌   ▗ ▌   ▗
                ▌ ▛▌▀▌▜▘▛▌▛▌▜▘▛▘
                ▙▖▌▌█▌▐▖▙▌▙▌▐▖▄▌

                Each of the 7 chatbots has a hidden specialization:
                - Strength (90% Accurate): Each bot is an expert in 1 subject.
                - Weakness (10% Accurate): Each bot is a novice in 1 subject.
                - Neutral (50% Accurate): For the other 5 subjects, it's a coin flip.

                ▄▖          ▌
                ▌ ▛▌▛▛▌▀▌▛▌▛▌▛▘
                ▙▖▙▌▌▌▌█▌▌▌▙▌▄▌

                You have 3 powerful commands. Each can be used only ONCE per game!
                - Ask: See your chatbot's answer before you choose your own. You must still answer.
                - Copy Paste: Automatically lock in your chatbot's answer (without seeing it). You pass or fail based on its choice.
                - Save: This command is used automatically on your first wrong answer. If your chatbot's secret answer was correct, you are saved! If not, the game is over.

                Once all 3 commands are used, you're on your own! Good luck.
                """;
        System.out.println(guide);
        System.out.println("");
        System.out.println("Press the Enter key to continue...");
        scanner.nextLine();
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
