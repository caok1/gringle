package software.engineering.gringle;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Name: Kevin Cao
 * Course: CSC 415
 * Semester: Spring 2015
 * Instructor: Dr. Pulimood
 * Project name: Gringle
 * Description: Gringle is a delayed text messaging mobile app primarily intended
 * for the use of reminders
 * Filename: CreateActivity.java
 * Description: Allows the user to create a new delayed message to be saved
 * as a draft or queued
 *Last modified on: 4/26/15
 */

public class CreateFragment extends Fragment {
    private static final String DIALOG_DATE = "date";
    private static final String DIALOG_TIME = "time";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;

    private Message mMessage;

    private Button mDateDelayButton;
    private Button mTimeDelayButton;
    private EditText mContentField;

    private Button mSaveButton;
    private Button mQueueButton;

    //Attempt for autocomplete
    private ArrayList<Map<String, String>> mPeopleList;
    private SimpleAdapter mAdapter;
    private AutoCompleteTextView mTxtPhoneNo;
    private String mNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessage = new Message();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create, parent, false);

        //Attempt for autocomplete
        mPeopleList = new ArrayList<>();
        PopulatePeopleList();

        mTxtPhoneNo = (AutoCompleteTextView) v.findViewById(R.id.recipient);
        mTxtPhoneNo.setText(mMessage.getRecipient());
        mTxtPhoneNo.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                mMessage.setRecipient(c.toString());
            }

            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                //Intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                //This one too
            }
        });

        mAdapter = new SimpleAdapter(getActivity(), mPeopleList, R.layout.custcontview,
                new String[] { "Name", "Phone", "Type" }, new int[] {
                R.id.ccontName, R.id.ccontNo, R.id.ccontType });
        mTxtPhoneNo.setAdapter(mAdapter);

        mTxtPhoneNo.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View arg1, int index,
                                    long arg3) {
                Map<String, String> map = (Map<String, String>) av.getItemAtPosition(index);

                String name  = map.get("Name");
                mNumber = map.get("Phone");
                mTxtPhoneNo.setText(name);

            }
        });
        //end of attempt

        mContentField = (EditText)v.findViewById(R.id.message_content);
        mContentField.setText(mMessage.getContent());
        mContentField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                mMessage.setContent(c.toString());
            }

            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                //Intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                //This one too
            }

        });

        mDateDelayButton = (Button) v.findViewById(R.id.date_delay);
        updateDate();
        mDateDelayButton.setOnClickListener(new View.OnClickListener() {
            //DatePicker Dialog is displayed prompting the user to enter a date
            public void onClick(View v) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mMessage.getCreationDate());
                dialog.setTargetFragment(CreateFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });

        mTimeDelayButton = (Button) v.findViewById(R.id.time_delay);
        updateTime();
        mTimeDelayButton.setOnClickListener(new View.OnClickListener() {

            @Override
            //TimePicker Dialog is displayed prompting the user to enter a time
            public void onClick(View v) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment
                        .newInstance(mMessage.getCreationDate());
                dialog.setTargetFragment(CreateFragment.this, REQUEST_TIME);
                dialog.show(fm, DIALOG_TIME);
            }
        });

        mSaveButton = (Button)v.findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    DraftHolder.get(getActivity()).addDraft(mMessage);
                    getActivity().onBackPressed();
                    Toast.makeText(getActivity(),"Message Saved!",
                            Toast.LENGTH_LONG).show();
                    getActivity().onBackPressed();
                } catch (Exception e) {
                    Toast.makeText(getActivity(),"Save failed, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });

        mQueueButton = (Button)v.findViewById(R.id.queue_button);
     //   mQueueButton.setEnabled(false);
        //Temporary implementation. Testing if sending text messages works.
        mQueueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String phoneNo = mTxtPhoneNo.getText().toString();
                String sms = mContentField.getText().toString();

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(getActivity(),"SMS Sent!",
                            Toast.LENGTH_LONG).show();
                    getActivity().onBackPressed();
                } catch (Exception e) {
                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(mNumber, null, sms, null, null);
                        Toast.makeText(getActivity(),"SMS Sent!",
                                Toast.LENGTH_LONG).show();
                        getActivity().onBackPressed();
                    } catch (Exception ex) {
                        Toast.makeText(getActivity(),"SMS failed, please try again later!",
                                Toast.LENGTH_LONG).show();
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                }

            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mMessage.setCreationDate(date);
            updateDate();
        } else if (requestCode == REQUEST_TIME) {
            Date date = (Date) data
                    .getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            mMessage.setCreationDate(date);
            updateTime();
        }
    }
    // Updates the Date of the Message object and displays the date on the date button
    public void updateDate() {
        Date date = mMessage.getCreationDate();
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
        mDateDelayButton.setText(sdf.format(date).toString());
    }

    // Updates the Time of the Message object and displays the time on the time button
    public void updateTime() {
        Date date = mMessage.getCreationDate();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        mTimeDelayButton.setText(sdf.format(date).toString());
    }

    //Fetches contacts from phones contact list
    public void PopulatePeopleList() {
        mPeopleList.clear();
        Cursor people = getActivity().getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (people.moveToNext()) {
            String contactName = people.getString(people
                    .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String contactId = people.getString(people
                    .getColumnIndex(ContactsContract.Contacts._ID));
            String hasPhone = people
                    .getString(people
                            .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

            if ((Integer.parseInt(hasPhone) > 0)){
                //Numbers are retrieved and now they are queried
                Cursor phones = getActivity().getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                        null, null);
                while (phones.moveToNext()){
                    //store numbers and display a dialog letting the user select which.
                    String phoneNumber = phones.getString(
                            phones.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String numberType = phones.getString(phones.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.TYPE));
                    Map<String, String> NamePhoneType = new HashMap<>();
                    NamePhoneType.put("Name", contactName);
                    NamePhoneType.put("Phone", phoneNumber);
                    if(numberType.equals("0"))
                        NamePhoneType.put("Type", "Work");
                    else
                    if(numberType.equals("1"))
                        NamePhoneType.put("Type", "Home");
                    else if(numberType.equals("2"))
                        NamePhoneType.put("Type",  "Mobile");
                    else
                        NamePhoneType.put("Type", "Other");
                    //Then add this map to the list.
                    mPeopleList.add(NamePhoneType);
                }
                phones.close();
            }
        }
        people.close();
        getActivity().startManagingCursor(people);
    }
}