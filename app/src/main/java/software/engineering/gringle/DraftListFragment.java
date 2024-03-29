package software.engineering.gringle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kevin on 4/11/15.
 */

/**
 * Name: Kevin Cao
 * Course: CSC 415
 * Semester: Spring 2015
 * Instructor: Dr. Pulimood
 * Project name: Gringle
 * Description: Gringle is a delayed text messaging mobile app primarily intended
 * for the use of reminders
 * Filename: DraftListFragment.java
 * Description: Interacts with DraftHolder and DraftListActivity to display a list of drafts
 *Last modified on: 4/11/15
 */

public class DraftListFragment extends ListFragment {
    private static final String TAG = "DraftListFragment";
    private ArrayList<Message> mDrafts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.drafts_title);
        mDrafts = DraftHolder.get(getActivity()).getDrafts();

        DraftAdapter adapter = new DraftAdapter(mDrafts);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Message c = ((DraftAdapter)getListAdapter()).getItem(position);

        Intent i = new Intent(getActivity(), DraftPagerActivity.class);
        i.putExtra(DraftFragment.EXTRA_DRAFT_ID, c.getId());
        startActivity(i);
    }

    public class DraftAdapter extends ArrayAdapter<Message> {
        public DraftAdapter(ArrayList<Message> drafts) {
            super(getActivity(), 0, drafts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_draft, null);
            }

            //Configure the view for this draft
            Message c = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.draft_list_item_recipientTextView);
            titleTextView.setText(c.getRecipient());

            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.draft_list_item_createDateTextView);
            dateTextView.setText(c.getCreationDate().toString());

            TextView contentTextView =
                    (TextView)convertView.findViewById(R.id.draft_list_item_contentTextView);
            contentTextView.setText(c.getContent());

            return convertView;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((DraftAdapter)getListAdapter()).notifyDataSetChanged();
    }
}