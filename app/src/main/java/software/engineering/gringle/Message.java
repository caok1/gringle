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

    //Unique identifier of the message
    private UUID mId;
    //Date of creation of the message
    private Date mCreationDate;
    //Recipient of the message
    private String mRecipient;
    //Send time of the message
    private String mTime;
    //Content of the message to be sent
    private String mContent;

    /*Constructs the message object giving it an unique identifier
     *and assigns it the current date and time
     */
    public Message() {
        mId = UUID.randomUUID();
        mCreationDate = new Date();
    }

    //Gets the unique identifier of the message object
    public UUID getId() {
        return mId;
    }

    //Gets the creation date of the message object
    public Date getCreationDate() {
        return mCreationDate;
    }

    //remove when no longer needed? Temporarily using creation date instead of send time (mTime)
    public void setCreationDate(Date creationDate) {
        mCreationDate = creationDate;
    }

    //Gets the recipient of the message
    public String getRecipient() {
        return mRecipient;
    }

    //Sets the recipient of the message
    public void setRecipient(String recipient) {
        mRecipient = recipient;
    }

    //Gets the send time of the message
    public String getTime() {
        return mTime;
    }

    //Sets the send time of the message
    public void setTime(String time) {
        mTime = time;
    }

    //Gets the content of the message
    public String getContent() {
        return mContent;
    }

    //Sets the content of the message
    public void setContent(String content) {
        mContent = content;
    }

    //Returns the recipient as a string
    @Override
    public String toString() {
        return mRecipient;
    }
}
