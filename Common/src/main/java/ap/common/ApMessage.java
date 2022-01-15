package ap.common;

import java.io.Serializable;
import java.util.UUID;

public class ApMessage implements Serializable {
    private ApMessageEnumType type;
    private UUID uuid;
    private String login;
    private String password;
    private String chatChannelText;
    private String chatChannelName;

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID localUuid) {
        this.uuid = localUuid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChatChannelText() {
        return chatChannelText;
    }

    public void setChatChannelText(String chatChannelText) {
        this.chatChannelText = chatChannelText;
    }

    public String getChatChannelName() {
        return chatChannelName;
    }

    public void setChatChannelName(String chatChannelName) {
        this.chatChannelName = chatChannelName;
    }

    public ApMessageEnumType getType() {
        return type;
    }

    public void setType(ApMessageEnumType type) {
        this.type = type;
    }
}
