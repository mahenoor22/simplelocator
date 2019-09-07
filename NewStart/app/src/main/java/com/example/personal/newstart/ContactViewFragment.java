package com.example.personal.newstart;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class ContactViewFragment extends Fragment {

    public static TextView n1, n2, n3, n4, n5; //no1, no2, no3, no4, no5;
    public static Button c1, c2, c3, c4, c5;
    private final int REQUEST_CODE = 99;
    String num;
    String name;
    int id;
    Cursor res;
    int id_to_remember ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_contacts, container, false);

        n1 = view.findViewById(R.id.Name1);
      //  no1 = view.findViewById(R.id.Num1);
        c1 = view.findViewById(R.id.change1);
        n2 = view.findViewById(R.id.name2);
     //   no2 = view.findViewById(R.id.num2);
        c2 = view.findViewById(R.id.change2);
        n3 = view.findViewById(R.id.name3);
     //   no3 = view.findViewById(R.id.num3);
        c3 = view.findViewById(R.id.change3);
        n4 = view.findViewById(R.id.name4);
     //   no4 = view.findViewById(R.id.num4);
        c4 = view.findViewById(R.id.change4);
        n5 = view.findViewById(R.id.name5);
     //   no5 = view.findViewById(R.id.num5);
        c5 = view.findViewById(R.id.change5);

        n1.setText("No Contacts Selected Yet");
        n1.setTextSize(50);
        //    n1.setVisibility(View.INVISIBLE);
     //   no1.setVisibility(View.INVISIBLE);
        c1.setVisibility(View.INVISIBLE);
        n2.setVisibility(View.INVISIBLE);
     //   no2.setVisibility(View.INVISIBLE);
        c2.setVisibility(View.INVISIBLE);
        n3.setVisibility(View.INVISIBLE);
     //   no3.setVisibility(View.INVISIBLE);
        c3.setVisibility(View.INVISIBLE);
        n4.setVisibility(View.INVISIBLE);
    ///    no4.setVisibility(View.INVISIBLE);
        c4.setVisibility(View.INVISIBLE);
        n5.setVisibility(View.INVISIBLE);
   //     no5.setVisibility(View.INVISIBLE);
        c5.setVisibility(View.INVISIBLE);

        displayAllContents();


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ContactFragment.numf[0] = null;
                //ContactFragment.btPick.setOnClickListener(this);
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
                id = 1;
                displayAllContents();
                //Toast.makeText(getContext() , "It comes back in the function" , Toast.LENGTH_SHORT).show();


            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
                id = 2;
                displayAllContents();
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
                id = 3;
                displayAllContents();
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
                id = 4;
                displayAllContents();
            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
                id = 5;
                displayAllContents();
            }
        });

        return view;
    }

    private void displayAllContents() {

        if (ContactFragment.myDb.getAllData(1) != null) {
            res = ContactFragment.myDb.getAllData(1);
            n1.setTextSize(14);
            //     no1.setVisibility(View.VISIBLE);
            c1.setVisibility(View.VISIBLE);
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("ID : " + res.getString(0) + "\n");
                buffer.append("NAME: " + res.getString(1) + "\n");
                buffer.append("NUMBER : " + res.getString(2) + "\n");
            }
            //no1.setVisibility(View.VISIBLE);
            //no1.append(buffer);
            n1.setText(buffer);
        }
        if (ContactFragment.myDb.getAllData(2) != null) {
            res = ContactFragment.myDb.getAllData(2);
            n2.setVisibility(View.VISIBLE);
            //no2.setVisibility(View.VISIBLE);
            c2.setVisibility(View.VISIBLE);
            // no2.setText(ContactFragment.numf[1]);
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("ID : " + res.getString(0) + "\n");
                buffer.append("NAME: " + res.getString(1) + "\n");
                buffer.append("NUMBER : " + res.getString(2) + "\n");
            }

            n2.setText(buffer);
        }
        if (ContactFragment.myDb.getAllData(3) != null) {
            res = ContactFragment.myDb.getAllData(3);
            n3.setVisibility(View.VISIBLE);
            //   no3.setVisibility(View.VISIBLE);
            c3.setVisibility(View.VISIBLE);
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("ID : " + res.getString(0) + "\n");
                buffer.append("NAME: " + res.getString(1) + "\n");
                buffer.append("NUMBER : " + res.getString(2) + "\n");
            }
            //   no3.setText(ContactFragment.numf[2]);
            n3.setText(buffer);
        }
        if (ContactFragment.myDb.getAllData(4) != null) {
            res = ContactFragment.myDb.getAllData(4);
            n4.setVisibility(View.VISIBLE);
            //   no4.setVisibility(View.VISIBLE);
            c4.setVisibility(View.VISIBLE);
            //   no4.setText(ContactFragment.numf[3]);
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("ID : " + res.getString(0) + "\n");
                buffer.append("NAME: " + res.getString(1) + "\n");
                buffer.append("NUMBER : " + res.getString(2) + "\n");
            }
            n4.setText(buffer);
        }
        if (ContactFragment.myDb.getAllData(5) != null) {
            res = ContactFragment.myDb.getAllData(5);
            n5.setVisibility(View.VISIBLE);
            //    no5.setVisibility(View.VISIBLE);
            c5.setVisibility(View.VISIBLE);
            //  no5.setText(ContactFragment.numf[4]);
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("ID : " + res.getString(0) + "\n");
                buffer.append("NAME: " + res.getString(1) + "\n");
                buffer.append("NUMBER : " + res.getString(2) + "\n");
            }
            n5.setText(buffer);
        }

    }

  /*  public static void initializing(int index) {
        switch (index) {
            case 0:
//                no1.setVisibility(View.VISIBLE);
                no1.setText(ContactFragment.numf[0]);
//                n1.setVisibility(View.VISIBLE);
                n1.setText(ContactFragment.name1);
//                c1.setVisibility(View.VISIBLE);
                break;
            case 1:
//                no2.setVisibility(View.VISIBLE);
                no2.setText(ContactFragment.numf[1]);
                //              n2.setVisibility(View.VISIBLE);
                n2.setText(ContactFragment.name2);
                //            c2.setVisibility(View.VISIBLE);
                break;
            case 2:
                //          no3.setVisibility(View.VISIBLE);
                no3.setText(ContactFragment.numf[2]);
                //        n3.setVisibility(View.VISIBLE);
                n3.setText(ContactFragment.name3);
                //      c3.setVisibility(View.VISIBLE);
                break;
            case 3:
                //    no4.setVisibility(View.VISIBLE);
                no4.setText(ContactFragment.numf[3]);
                //  n4.setVisibility(View.VISIBLE);
                n4.setText(ContactFragment.name4);
                //c4.setVisibility(View.VISIBLE);
                break;
            case 4:
                //no5.setVisibility(View.VISIBLE);
                no5.setText(ContactFragment.numf[4]);
                // n5.setVisibility(View.VISIBLE);
                n5.setText(ContactFragment.name5);
                //  c5.setVisibility(View.VISIBLE);
                break;
        }

    }

    public static void justToTrigger(int Index) {

        initializing(Index);
       /* if (ContactFragment.name1!=null)
        {
            n1.setVisibility(View.VISIBLE);
            no1.setVisibility(View.VISIBLE);
            c1.setVisibility(View.VISIBLE);
            n1.setText(ContactFragment.name1);
            no1.setText(ContactFragment.numf[0]);
        }

        if (ContactFragment.name2!=null)
        {
            n2.setVisibility(View.VISIBLE);
            no2.setVisibility(View.VISIBLE);
            c2.setVisibility(View.VISIBLE);
            n2.setText(ContactFragment.name2);
            no2.setText(ContactFragment.numf[1]);
        }
        if (ContactFragment.name3!=null)
        {
            n3.setVisibility(View.VISIBLE);
            no3.setVisibility(View.VISIBLE);
            c3.setVisibility(View.VISIBLE);
            n3.setText(ContactFragment.name3);
            no3.setText(ContactFragment.numf[2]);
        }

        if (ContactFragment.name4!=null)
        {
            n4.setVisibility(View.VISIBLE);
            no4.setVisibility(View.VISIBLE);
            c4.setVisibility(View.VISIBLE);
            n4.setText(ContactFragment.name4);
            no4.setText(ContactFragment.numf[3]);
        }
        if (ContactFragment.name5!=null)
        {
            n1.setVisibility(View.VISIBLE);
            no1.setVisibility(View.VISIBLE);
            c1.setVisibility(View.VISIBLE);
            n1.setText(ContactFragment.name5);
            no1.setText(ContactFragment.numf[4]);
        }


    } */
  //  }

    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

    /*    if(numf[0]!=null)
            name1 = getContactName(getContext()  , numf[0]);

        else name1=null;
        if(numf[1]!=null)
            name2 = getContactName(getContext()  , numf[1]);
        else name2=null;
        if(numf[2]!=null)
            name3 = getContactName(getContext()  , numf[2]);
        else name3=null;
        if(numf[3]!=null)
            name4 = getContactName(getContext()  , numf[3]);
        else name4=null;
        if(numf[4]!=null)
            name5 = getContactName(getContext()  , numf[4]);
        else name5=null; */

        //     ContactViewFragment.justToTrigger();
        switch (reqCode) {
            case REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {

                        Uri contactData = data.getData();
                        Cursor c = getActivity().getContentResolver().query(contactData, null, null, null, null);
                        if (c.moveToFirst()) {
                            String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                            String hasNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                            if (Integer.valueOf(hasNumber) == 1) {
                                Cursor numbers = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                                try {
                                    while (numbers.moveToNext()) {
                                        num = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                        num = num.replaceAll(" ", "");
                                        //i++;
                                        if (num.startsWith("+")) {
                                            if (num.length() == 13) {
                                                num = num.substring(3);
                                                //   Toast.makeText(getContext(), "Number="+num[i], Toast.LENGTH_LONG).show();
                                                break;


                                            } else if (num.length() == 14) {
                                                num = num.substring(4);
                                                break;
                                            }


                                        }

                                    }

                                    if (num!= null) {
                                        name = getContactName(getContext(), num);

                                    }

                                }catch (ArrayIndexOutOfBoundsException e){

                                    Toast.makeText(getContext() , "ERROR" , Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    }

                }
                boolean result =ContactFragment.myDb.updateData(id , name , num);
                if (result==true)
                    Toast.makeText(getContext() , "Successfully Updated" , Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext() , "FAILED" , Toast.LENGTH_SHORT).show();

                res = ContactFragment.myDb.getId();
                if(res != null && res.moveToFirst())
                {
                    id_to_remember = res.getInt(0);
                }
                else
                    id_to_remember = -1;
                id = id_to_remember ;
                Toast.makeText(getContext() , "Value Returned In Cursor "+ id_to_remember,Toast.LENGTH_SHORT).show();
                displayAllContents();


        }

    }

    public static String getContactName(Context context, String phoneNumber) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
        if (cursor == null) {
            return null;
        }
        String contactName = null;
        if(cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
        }

        if(cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return contactName;
    }

}