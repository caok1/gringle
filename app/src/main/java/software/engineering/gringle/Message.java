package software.engineering.gringle;

import java.util.Date;
import java.util.UUID;

/**
 * Created by kevin on 4/4/15.
 */
public class Message {

    private UUID mId;
    private Date mCreationDate;
    private String mRecipientTitle;
    private String mTimeTitle;
    private String mContentTitle;

    public Message() {
        //Generate unique identifier
        mId = UUID.randomUUID();
        mCreationDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public Date getCreationDate() {
        return mCreationDate;
    }

    public String getRecipientTitle() {
        return mRecipientTitle;
    }

    public void setRecipientTitle(String recipientTitle) {
        mRecipientTitle = recipientTitle;
    }

    public String getTimeTitle() {
        return mTimeTitle;
    }

    public void setTimeTitle(String timeTitle) {
        mTimeTitle = timeTitle;
    }

    public String getContentTitle() {
        return mContentTitle;
    }

    public void setContentTitle(String contentTitle) {
        mContentTitle = contentTitle;
    }
}