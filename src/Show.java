/**
 * @Author Tristan P.-S.
 * @version 1.0
 * A class to represent an individual show's current watchtime + status
 */
public class Show {
    private String showName;
    private int epsWatched;
    private String showStatus;

    /**
     * Default Show Constructor
     */
    public Show(){
        this("NA", 10, "watching");
    }

    /**
     * Standard Show Constructor
     * @param showName name of the show
     * @param epsWatched episodes watched (starts from 1 by default, this is the right side value)
     * @param watchStatus current status of watching show (watched, waiting, complete, etc.)
     */
    public Show(String showName, int epsWatched, String showStatus) {
        this.showName = showName;
        this.epsWatched = epsWatched;
        this.showStatus = showStatus;
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
    public int getEpsWatched(){
        return epsWatched;
    }

    /**
     * Getter method for the watch status of a show
     * @return the status given to the show
     */
    public String getShowStatus(){
        return showStatus;
    }

    /**
     * Setter method for the show name
     * @param name name of the show
     */
    public void setShowName(String showName){
        this.showName = showName;
    }

    /**
     * Setter method for the last episode watched
     * @param ep episode watched
     */
    public void setEpsWatched(int epsWatched) {
        this.epsWatched = epsWatched;
    }

    /**
     * Setter method for the show status
     * @param status status of current show
     */
    public void setShowStatus(String showStatus) {
        this.showStatus = showStatus;
    }
}
