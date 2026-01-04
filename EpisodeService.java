import java.util.List;

public interface EpisodeService {
    void addEpisode(Episode episode);
    void updateEpisode(Episode episode);
    void deleteEpisode(int episodeId);
    List<Episode> viewAllEpisodes();
    void sortByEpisodeId();
    void sortByTitle();
}