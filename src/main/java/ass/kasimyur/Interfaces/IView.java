package ass.kasimyur.Interfaces;

/**
 * Created by username on 3/12/17.
 */
public interface IView {

    void setMessages(String message);
    String getNewMessageText();
    String getMessages();
    void notifyObserver();


}
