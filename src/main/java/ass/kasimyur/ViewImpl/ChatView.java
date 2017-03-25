package ass.kasimyur.ViewImpl;

import ass.kasimyur.Interfaces.IView;
import ass.kasimyur.Presenter;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ChatView implements IView {

    protected final JTextArea messages;
    protected final JTextField newMessageInput;
    protected final JButton btnSend;
    private final Presenter presenter;
    protected JFrame frame = new JFrame();
    protected JPanel vertical = new JPanel();

    public ChatView(Presenter presenter) {
        this.presenter = presenter;
        messages = createMessages();
        newMessageInput = new JTextField(80);
        btnSend = createBtnSend();
        JPanel horizontal = createHorizontal();
        configureVertical(horizontal);
        configureMainFrame();

    }

    private void configureVertical(JPanel horizontal) {
        vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));
        vertical.add(messages);
        vertical.add(horizontal);
    }

    private JPanel createHorizontal() {
        JPanel horizontal = new JPanel();
        horizontal.setLayout(new BoxLayout(horizontal, BoxLayout.X_AXIS));

        horizontal.add(newMessageInput);
        horizontal.add(btnSend);
        return horizontal;
    }

    private void configureMainFrame() {
        frame.setTitle("Chat window");
        frame.add(vertical);
        frame.setVisible(true);

        // adjusts window to size of components
        frame.pack();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                e.getWindow().setVisible(false);
                presenter.exit();
            }
        });
    }

    private JTextArea createMessages() {
        JTextArea messages = new JTextArea(15, 80);
        messages.setEditable(false);
        messages.setText(presenter.getHistoryMsgs());
        return messages;
    }


    private JButton createBtnSend() {
        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(e -> {
            this.notifyObserver();
            cleanInputWindow();

        });
        return btnSend;
    }


    public void notifyObserver() {
        presenter.notifyAboutNewMessage(this);
    }

    private void cleanInputWindow() {
        newMessageInput.setText("");
    }

    @Override
    public String getNewMessageText() {
        return newMessageInput.getText();
    }

    @Override
    public String getMessages() {
        return messages.getText();
    }

    @Override
    public void setMessages(String message) {
        messages.setText(messages.getText() + message + "\n");
    }


}

