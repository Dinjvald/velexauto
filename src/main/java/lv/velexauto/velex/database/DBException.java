package lv.velexauto.velex.database;

/**
 * Created by Dinjvald on 23/03/15.
 */
public class DBException extends Exception {


    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(Throwable cause) {
        super(cause);
    }

}
