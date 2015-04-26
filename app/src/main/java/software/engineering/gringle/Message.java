package software.engineering.gringle;

import java.util.Date;
import java.util.UUID;

/**
 * Possibly missing recipient #. Probably use RecipientTitle as phone number
 *
 * Created by kevin on 4/4/15.
 */

/**
 * Name: Kevin Cao
 * Course: CSC 415
 * Semester: Spring 2015
 * Instructor: Dr. Pulimood
 * Project name: Gringle
 * Description: Gringle is a delayed text messaging mobile app primarily intended
 * for the use of reminders
 * Filename: Message.java
 * Description: The message object where all the text message information is stored
 *Last modified on: 4/4/15
 */

public class Message {

    private UUID mId;
    private Date mCreationDate;
    private String mRecipientTitle;
    private String mTime;
    private String mContent;

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

    //remove when no longer needed? Temporarily using creation date instead of send time (mTime)
    public void setCreationDate(Date creationDate) {
        mCreationDate = creationDate;
    }

    public String getRecipientTitle() {
        return mRecipientTitle;
    }

    public void setRecipientTitle(String recipientTitle) {
        mRecipientTitle = recipientTitle;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    @Override
    public String toString() {
        return mRecipientTitle;
    }
}
