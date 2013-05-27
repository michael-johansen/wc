package no.wc;

public class Message {
    private final String user;
    private final String text;

    public Message(String user, String text) {
        this.text = text;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }
}
