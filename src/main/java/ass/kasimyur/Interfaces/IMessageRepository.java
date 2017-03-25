package ass.kasimyur.Interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by username on 3/13/17.
 */
public interface IMessageRepository {
    String getMessages();

    boolean add(String s);

    void save() throws IOException;

    boolean restoreHistory() throws FileNotFoundException;
}
