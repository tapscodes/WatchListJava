/**
 * @Author Tristan P.-S.
 * @version 1.0
 * A class to represent an individual show's current watchtime + status
 */
public class Show {
    private String showName;
    private int watched;
    private String status;

    /**
     * Default Show Constructor
     */
    public Show(){
        this("NA", 1, "watching");
    }

    /**
     * Standard Show Constructor
     * @param showName name of the show
     * @param epsWatched episodes watched (starts from 1 by default, this is the right side value)
     * @param watchStatus current status of watching show (watched, waiting, complete, etc.)
     */
    public Show(String showName, int epsWatched, String watchStatus) {
        this.showName = showName;
        this.watched = epsWatched;
        this.status = watchStatus;
    }

    /**
     * Getter method for the shows name
     * @return the show's name
     */
    public String getShowName(){
        return showName;
    }

    /**
     * Getter method for the current episode watched count
     * @return last episode that was watched
     */
    public int getEpisodesWatched(){
        return watched;
    }

    /**
     * Getter method for the watch status of a show
     * @return the status given to the show
     */
    public String getWatchStatus(){
        return status;
    }

    /**
     * Setter method for the show name
     * @param name name of the show
     */
    public void setShowName(String name){
        showName = name;
    }

    /**
     * Setter method for the last episode watched
     * @param ep episode watched
     */
    public void setEpisodesWatched(int ep) {
        watched = ep;
    }

    /**
     * 
     * @param status
     */
    public void setWatchStatus(String status) {
        this.status = status;
    }
}
