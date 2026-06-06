import java.util.ArrayList;
import java.util.Scanner;

public class TrainSchedulingSystem {
    private static ArrayList<Train> trainSchedules = new ArrayList<>();
    private static ArrayList<Integer> occupiedPlatforms = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n----- PRASA Train Scheduling System ------");
            System.out.println("1. Add Train Schedule");
            System.out.println("2. Cancel Scheduled Train");
            System.out.println("3. View Train Schedules");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consuming newline

            switch (choice) {
                case 1:
                    addTrain(scanner);
                    break;
                case 2:
                    cancelTrain(scanner);
                    break;
                case 3:
                    viewSchedules();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addTrain(Scanner scanner) {
        System.out.print("Enter Train Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Platform Number: ");
        int platform = scanner.nextInt();
        scanner.nextLine();

        if (occupiedPlatforms.contains(platform)) {
            System.out.println("Platform " + platform + " is already occupied. Choose another platform.");
            return;
        }

        Train train = new Train(name, platform);
        trainSchedules.add(train);
        occupiedPlatforms.add(platform);
        train.start();

        System.out.println("Train scheduled successfully.");
    }

    private static void cancelTrain(Scanner scanner) {
        if (trainSchedules.isEmpty()) {
            System.out.println("No trains to cancel.");
            return;
        }

        System.out.println("Scheduled Trains:");
        for (int i = 0; i < trainSchedules.size(); i++) {
            Train t = trainSchedules.get(i);
            System.out.println((i + 1) + ". " + t.getTrainName() + " (Platform " + t.getPlatformNumber() + ")");
        }

        System.out.print("Enter train number to cancel: ");
        int cancelIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume leftover newline

        if (cancelIndex >= 0 && cancelIndex < trainSchedules.size()) {
            Train trainToCancel = trainSchedules.get(cancelIndex);

            trainToCancel.interrupt(); // immediately stop the trains thread
            occupiedPlatforms.remove(Integer.valueOf(trainToCancel.getPlatformNumber()));
            trainSchedules.remove(cancelIndex);

            System.out.println("Train cancelled successfully.");
        } else {
            System.out.println("Invalid train number.");
        }
    }

    private static void viewSchedules() {
        if (trainSchedules.isEmpty()) {
            System.out.println("No train schedules available.");
            return;
        }

        System.out.println("\nCurrent Train Schedules:");
        for (Train train : trainSchedules) {
            System.out.println("- " + train.getTrainName() + " (Platform " + train.getPlatformNumber() + ")"
                    + (train.isAlive() ? " [Running]" : " [Completed]"));
        }
    }
}

