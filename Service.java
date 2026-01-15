import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Service implements EpisodeService {
    private File data;
    List<Episode> episodeList;
    public static Pattern DUR_REGEX =Pattern.compile("^[1-9][0-9]*$");

    public Service() {
        data = new File("episode.doc");
        if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void validateDuration(int duration) {
        if (!DUR_REGEX.matcher(String.valueOf(duration)).matches()) {
            throw new InvalidDurationException("Invalid Duration! Duration must be positive."
            );
        }
    }
    public void readData() {
        try (FileInputStream fis = new FileInputStream(data)) {
            if (data.length() == 0) {
                episodeList = new ArrayList<>();
            } else {
                ObjectInputStream ois = new ObjectInputStream(fis);
                episodeList = (List<Episode>) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            episodeList = new ArrayList<>();
        }
    }
    public void writeData() {
        try (ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream(data))) {
            oos.writeObject(episodeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addEpisode(Episode episode) {
        validateDuration(episode.getDurationMinutes());
        readData();
        episodeList.add(episode);
        writeData();
        System.out.println("Episode added successfully.");
    }

    @Override
    public void updateEpisode(Episode episode) {
        validateDuration(episode.getDurationMinutes());
        readData();

        for (Episode e : episodeList) {
            if (e.getEpisodeId() == episode.getEpisodeId()) {
                e.setTitle(episode.getTitle());
                e.setDurationMinutes(episode.getDurationMinutes());
                writeData();
                System.out.println("Episode updated successfully.");
                return;
            }
        }
        System.out.println("Episode not found.");
    }

    @Override
    public void deleteEpisode(int episodeId) {
        readData();
        Iterator<Episode> it = episodeList.iterator();
        while (it.hasNext()) {
            if (it.next().getEpisodeId() == episodeId) {
                it.remove();
                writeData();
                System.out.println("Episode deleted successfully.");
                return;
            }
        }
        System.out.println("Episode not found.");
    }

    @Override
    public List<Episode> viewAllEpisodes() {
        readData();
        return episodeList;
    }

    @Override
    public void sortByEpisodeId() {
        readData();
        Collections.sort(episodeList);
        writeData();
        System.out.println("Sorted by Episode ID");
    }

    @Override
    public void sortByTitle() {
        readData();
        Collections.sort(episodeList, new Episode.TitleComparator());
        writeData();
        System.out.println("Sorted by Title");
    }
}
class InvalidDurationException extends RuntimeException {
    public InvalidDurationException(String message) {
        super(message);
    }
}