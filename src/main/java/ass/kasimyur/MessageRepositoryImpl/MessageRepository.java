package ass.kasimyur.MessageRepositoryImpl;

import ass.kasimyur.Interfaces.IMessageRepository;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


/**
 * @author sorry about it
 */
public class MessageRepository implements IMessageRepository {

    protected File file;

    // instance to update when new message is passed
    private List<String> messages = new LinkedList<>();

    public MessageRepository(File file) throws IOException {
        this.file = file;

    }


    public boolean restoreHistory() throws FileNotFoundException {
        if (file.exists()) {
            if (!file.canRead()) {
                return false;
            }
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line;
                while ((line = br.readLine()) != null) {
                    messages.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        } else {
            throw new FileNotFoundException();

        }
        return true;
    }

    @Override
    public String getMessages() {
        StringBuilder msgs = new StringBuilder();
        for (String message : messages) {
            msgs.append(message);
            msgs.append("\n");
        }
        return msgs.toString();

    }


    @Override
    public boolean add(String s) {

        return messages.add(s);
    }

    @Override
    public void save() throws IOException {
        PrintWriter pw = new PrintWriter(file);
        messages.forEach(pw::println);
        pw.close();
    }

}
