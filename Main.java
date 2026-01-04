import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EpisodeService service = new Service();
        Scanner sc = new Scanner(System.in);
        while(true){
        System.out.println("Podcast Episode Manager");
        System.out.println("1. Add Episode");
        System.out.println("2. View Episodes");
        System.out.println("3. Update Episode");
        System.out.println("4. Delete Episode");
        System.out.println("5. Sort by Episode ID");
        System.out.println("6. Sort by Title");
        System.out.println("7. Exit");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        try {
            switch (choice) {
                case 1:
                    System.out.print("Episode ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Duration: ");
                    int duration = sc.nextInt();
                    service.addEpisode(new Episode(id, title, duration));
                    break;
                case 2:
                    List<Episode> list = service.viewAllEpisodes();
                    if (list.isEmpty())
                        System.out.println("No episodes found.");
                    else
                        list.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Episode ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Title: ");
                    title = sc.nextLine();
                    System.out.print("New Duration: ");
                    duration = sc.nextInt();
                    service.updateEpisode(new Episode(id, title, duration));
                    break;
                case 4:
                    System.out.print("Episode ID: ");
                    id = sc.nextInt();
                    service.deleteEpisode(id);
                    break;
                case 5:
                    service.sortByEpisodeId();
                    break;

                case 6:
                    service.sortByTitle();
                    break;
                case 7:
                    System.out.println("Watch more podcasts tommorow!");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        } catch (InvalidDurationException e) {
                System.out.println("Sorry: " + e.getMessage());
        }
    }
    }
}