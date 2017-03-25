package ass.kasimyur;


import ass.kasimyur.Interfaces.IMessageRepository;
import ass.kasimyur.Interfaces.IView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Presenter {


    public IMessageRepository msgRepo;
    private List<IView> view;


    public Presenter(IMessageRepository msgRepo) {
        this.msgRepo = msgRepo;
        restoreHistory();
        view = new ArrayList<>();
    }


    private boolean restoreHistory() {
        boolean historyRestored = false;
        try {
            historyRestored = msgRepo.restoreHistory();
        } catch (FileNotFoundException e) {
            File f = new File("messages.txt");
            try {
                historyRestored = f.createNewFile();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return historyRestored;


    }

    public void notifyAboutNewMessage(IView view) {
        String message = view.getNewMessageText();

        boolean msgAdded = msgRepo.add(message);
        if (msgAdded) {
            try {
                msgRepo.save();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.updateViews(message);
        }

    }

    private void updateViews(String message) {
        for (IView view : this.view) {
            view.setMessages(message);
        }
    }

    public String getHistoryMsgs() {
        return msgRepo.getMessages();
    }


    public void exit() {
        System.exit(0);
    }

    public void setView(List<IView> view) {
        this.view = view;
    }
}
