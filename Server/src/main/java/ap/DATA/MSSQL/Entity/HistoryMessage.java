package ap.DATA.MSSQL.Entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "history_message")
public class HistoryMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;
    private Date dateTime;
    @ManyToOne
    @JoinColumn(name = "userId")
    private SprUser sprUser;
    private String message;

    public HistoryMessage() {
    }

    public HistoryMessage(Date dateTime, SprUser sprUser, String message) {
        this.dateTime = dateTime;
        this.sprUser = sprUser;
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(java.sql.Timestamp dateTime) {
        this.dateTime = dateTime;
    }


    public SprUser getUser() {
        return sprUser;
    }

    public void setUser(SprUser sprUser) {
        this.sprUser = sprUser;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.getMessage();
    }

}
