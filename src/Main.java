import cli.CliInterface;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var cli = new CliInterface();
        cli.greet();

        while (true) {
            var isRunning = cli.menuSelection();
            if (!isRunning) break;
        }
    }
}
