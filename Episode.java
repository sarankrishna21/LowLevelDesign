import java.io.Serializable;
import java.util.Comparator;

public class Episode implements Serializable, Comparable<Episode>{
    private int episodeId;
    private String title;
    private int durationMinutes;

    public Episode(int episodeId, String title, int durationMinutes) {
        this.episodeId = episodeId;
        this.title = title;
        this.durationMinutes = durationMinutes;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
    
    @Override
    public int compareTo(Episode e) {
        return this.episodeId - e.episodeId;
    }

    @Override
    public String toString() {
        return episodeId + "->" + title + "->" + durationMinutes + " mins";
    }
    public static class TitleComparator implements Comparator<Episode> {
        @Override
        public int compare(Episode e1, Episode e2) {
            return e1.getTitle().compareToIgnoreCase(e2.getTitle());
        }
    }
}