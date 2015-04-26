package software.engineering.gringle;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Name: Kevin Cao
 * Course: CSC 415
 * Semester: Spring 2015
 * Instructor: Dr. Pulimood
 * Project name: Gringle
 * Description: Gringle is a delayed text messaging mobile app primarily intended
 * for the use of reminders
 * Filename: DraftHolder.java
 * Description: Centralized data stash that stores Message objects that are drafts
 *Last modified on: 4/11/15
 */

public class DraftHolder {
    private ArrayList<Message> mDrafts;

    private static DraftHolder sDraftHolder;
    private Context mAppContext;

    private DraftHolder(Context appContext) {
        mAppContext = appContext;
        mDrafts = new ArrayList<Message>();
    }

    public static DraftHolder get(Context c) {
        if (sDraftHolder == null) {
            sDraftHolder = new DraftHolder(c.getApplicationContext());
        }
        return sDraftHolder;
    }

    public void addDraft(Message c) {
        mDrafts.add(c);
    }

    public ArrayList<Message> getDrafts() {
        return mDrafts;
    }

    public Message getMessage(UUID id) {
        for (Message c : mDrafts) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }
}