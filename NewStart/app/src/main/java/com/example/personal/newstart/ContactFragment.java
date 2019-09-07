package com.example.personal.newstart;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class ContactFragment extends Fragment{

    int id ;
    EditText et1;
    Button B2;
    String name , num;
    int i=0;
    int id_to_remember ;
    ImageView IV;
    boolean decision ;
    TextView showContacts;
   // public static String name1,name2,name3,name4,name5;
    String custom_number;
    Cursor c;
    //public static String[] num = new String[20]; //Static because we have to use it in other classes
    //public static String[] numf = new String[5];
    //String phoneNumber;
    //String str_getMOBILE;
    //TextView n1,n2,n3,n4,n5;
    int choice;
    private final int REQUEST_CODE=99;
    public static DatabaseHelper myDb;
    public static Button btPick;
    @SuppressLint("ValidFragment")
    ContactFragment(){

        id = 1;
    }
    /*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toast.makeText(getContext() , "OnActivityCreated is called" , Toast.LENGTH_LONG).show();
        if (savedInstanceState!=null){

            Toast.makeText(getContext() , "INSIDE FINALLY",Toast.LENGTH_LONG).show();
            numf[0] = savedInstanceState.getString("num[0]");
            name1 = savedInstanceState.getString("name1");
            numf[1] = savedInstanceState.getString("num[1]");
            name2 = savedInstanceState.getString("name2");
            numf[2] = savedInstanceState.getString("num[2]");
            name3 = savedInstanceState.getString("name3");
            numf[3] = savedInstanceState.getString("num[3]");
            name4 = savedInstanceState.getString("name4");
            numf[4] = savedInstanceState.getString("num[4]");
            name5 = savedInstanceState.getString("name5");
            j = savedInstanceState.getInt("position");
        }
        else
        {

            j=0;

        }

    }
*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.accessing_contacts_final , container , false);

        //numf = null;
        //IMP
        //  id=1;
       //     Toast.makeText(getContext() , "The Value Of ID Is : "+id , Toast.LENGTH_SHORT).show();
          et1 = (EditText)view.findViewById(R.id.ET1);
         // showContacts = (TextView)view.findViewById(R.id.showContacts);
          myDb = new DatabaseHelper(getContext());
          myDb.createTables();
            choice = myDb.tellCount();
            Toast.makeText(getContext() , "CHOICE VALUE IS : " +choice , Toast.LENGTH_LONG).show();
    //    n1 = (TextView)view.findViewById(R.id.num1);
    //    n2 = (TextView)view.findViewById(R.id.num2);
    //    n3 = (TextView)view.findViewById(R.id.num3);
    //    n4 = (TextView)view.findViewById(R.id.num4);
    //    n5 = (TextView)view.findViewById(R.id.num5);
         btPick=(Button)view.findViewById(R.id.b1);
         btPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);

            /*    if(numf[0]!=null)
                {
                    name1 = getContactName(getContext()  , numf[0]);
                    showContacts.setText("Name 1 is" +name1);
                    j=1;
                    //        ContactViewFragment.initializing(0);
                }
                else
                {
                    name1=null;
                    j=0;
                }
                if(numf[1]!=null)
                {
                    name2 = getContactName(getContext()  , numf[1]);
                    showContacts.append("Name 2 is "+name2);
                    j=2;
                    //    ContactViewFragment.initializing(1);
                }
                else
                {
                    name2=null;
                    // j=1;
                }
                if(numf[2]!=null)
                {
                    name3 = getContactName(getContext()  , numf[2]);
                    showContacts.append("Name 3 is "+name3);
                    j=3;
                    //    ContactViewFragment.initializing(2);
                }

                else
                {
                    name3=null;
                    //j=2;
                }
                if(numf[3]!=null)
                {
                    name4 = getContactName(getContext()  , numf[3]);
                    showContacts.append("Name 4 is "+name4);
                    j=4;
                        ContactViewFragment.initializing(3);
                }

                else {

                    name4=null;
                    //j=3;
                }
                if(numf[4]!=null)
                {
                    name5 = getContactName(getContext()  , numf[4]);
                    showContacts.append("Name 5 is "+name5);
                    j=5;
                    //    ContactViewFragment.initializing(4);
                }
                else
                {
                    name5=null;
                    //j=4;
                }  */

            }
        });

        B2 = (Button)view.findViewById(R.id.b2);
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                custom_number=et1.getText().toString();
               // Toast.makeText(this , "Custom Number Picked As :  " + custom_number ,Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(), "Custom Num Picked As " + custom_number, Toast.LENGTH_SHORT).show();
            }
        });

        IV = view.findViewById(R.id.next);
        IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new MessageFragment()).commit();
                       MainActivity.navigationView.setCheckedItem(R.id.nav_message);
            }
        });

      /*  if(numf[0]!=null)
        {
            name1 = getContactName(getContext()  , numf[0]);
            showContacts.setText("Name 1 is" +name1);
            j=1;
            //        ContactViewFragment.initializing(0);
        }
        else
            {
                name1=null;
                j=0;
            }
        if(numf[1]!=null)
        {
            name2 = getContactName(getContext()  , numf[1]);
            showContacts.append("Name 2 is "+name2);
            j=2;
            //    ContactViewFragment.initializing(1);
        }
        else
        {
            name2=null;
           // j=1;
        }
        if(numf[2]!=null)
        {
            name3 = getContactName(getContext()  , numf[2]);
            showContacts.append("Name 3 is "+name3);
            j=3;
            //    ContactViewFragment.initializing(2);
        }

        else
        {
            name3=null;
            //j=2;
        }
        if(numf[3]!=null)
        {
            name4 = getContactName(getContext()  , numf[3]);
            showContacts.append("Name 4 is "+name4);
            j=4;
            //    ContactViewFragment.initializing(3);
        }

        else {

            name4=null;
            //j=3;
        }
        if(numf[4]!=null)
        {
            name5 = getContactName(getContext()  , numf[4]);
            showContacts.append("Name 5 is "+name5);
            j=5;
            //    ContactViewFragment.initializing(4);
        }
        else
        {
            name5=null;
            //j=4;
        }
        Toast.makeText(getContext(), "Number["+j+"] = "+numf[j], Toast.LENGTH_LONG).show();
        */


  /*      if (savedInstanceState!=null){

            Toast.makeText(getContext() , "INSIDE FINALLY",Toast.LENGTH_LONG).show();
            numf[0] = savedInstanceState.getString("num[0]");
            name1 = savedInstanceState.getString("name1");
            numf[1] = savedInstanceState.getString("num[1]");
            name2 = savedInstanceState.getString("name2");
            numf[2] = savedInstanceState.getString("num[2]");
            name3 = savedInstanceState.getString("name3");
            numf[3] = savedInstanceState.getString("num[3]");
            name4 = savedInstanceState.getString("name4");
            numf[4] = savedInstanceState.getString("num[4]");
            name5 = savedInstanceState.getString("name5");
            j = savedInstanceState.getInt("position");
        }
        else
        {

            Toast.makeText(getContext(),"INSIDE ELSE" , Toast.LENGTH_LONG).show();
            //j=0;
        } */

        return view;
    }
/*
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        Toast.makeText(getContext() , "INSIDE FINALLY",Toast.LENGTH_LONG).show();
        numf[0] = savedInstanceState.getString("num[0]");
        name1 = savedInstanceState.getString("name1");
        numf[1] = savedInstanceState.getString("num[1]");
        name2 = savedInstanceState.getString("name2");
        numf[2] = savedInstanceState.getString("num[2]");
        name3 = savedInstanceState.getString("name3");
        numf[3] = savedInstanceState.getString("num[3]");
        name4 = savedInstanceState.getString("name4");
        numf[4] = savedInstanceState.getString("num[4]");
        name5 = savedInstanceState.getString("name5");
        j = savedInstanceState.getInt("position");

    } */

    @Override
    public void onResume() {
        super.onResume();
      //  Toast.makeText(getContext() , "INSIDE onResume" , Toast.LENGTH_SHORT).show();
    }

/*    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //getActivity().getSupportFragmentManager().putFragment(outState , "ContactFragment" , ContactFragment);
        //getSupportFragmentManager().putFragment(outState, "myFragmentName", mContent);
        if (numf[0] != null)
        {
            outState.putString("num[0]" , num[0]);
            outState.putString("name1" , name1);
        }
        if (numf[1] != null)
        {
            outState.putString("num[1]" , num[1]);
            outState.putString("name2" , name2);
        }
        if (numf[2] != null)
        {
            outState.putString("num[2]" , num[2]);
            outState.putString("name3" , name3);
        }
        if (numf[3] != null)
        {
            outState.putString("num[3]" , num[3]);
            outState.putString("name4" , name4);
        }
        if (numf[4] != null)
        {
            outState.putString("num[4]" , num[4]);
            outState.putString("name5" , name5);
        }
        if(j!=0)
        {
            outState.putInt("position" , j);
        }

    } */

    @SuppressLint("SetTextI18n")
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
     /*   switch (reqCode) {
            case REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    if (data!=null){

                        Uri contactData = data.getData();
                        Cursor c = getActivity().getContentResolver().query(contactData, null, null, null, null);
                        if (c.moveToFirst()) {
                            String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                            String hasNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                            if (Integer.valueOf(hasNumber) == 1) {
                                Cursor numbers = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                                try {
                                    while (numbers.moveToNext()) {
                                        num[i] = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                        num[i] = num[i].replaceAll(" ","");
                                        //i++;
                                        if(num[i].startsWith("+"))
                                        {
                                            if(num[i].length()==13)
                                            {
                                                num[i]=num[i].substring(3);
                                                //   Toast.makeText(getContext(), "Number="+num[i], Toast.LENGTH_LONG).show();
                                                numf[j] = num[i];
                                                i++;


                                            }
                                            else if(num[i].length()==14) {
                                                num[i] = num[i].substring(4);
                                                //    Toast.makeText(getContext(), "Number="+num[i], Toast.LENGTH_LONG).show();
                                                numf[j] = num[i];
                                                i++;
                                            }


                                        }
                                        else{
                                            //Do Nothing
                                            //    Toast.makeText(getContext(), "Number="+num[i], Toast.LENGTH_LONG).show();
                                            numf[j] = num[i];
                                            i++;
                                        }
                                        //  Toast.makeText(accessingContactsNow.this, "Number="+num, Toast.LENGTH_SHORT).show();

                                    }

                                        if(numf[0]!=null)
                                        {
                                            name1 = getContactName(getContext()  , numf[0]);
                                            showContacts.setText("Name 1 is" +name1);
                                            //numf[0]=null;


                                            //  ContactViewFragment.initializing(0);
                                        }

                                        else name1=null;
                                        if(numf[1]!=null)
                                        {
                                            name2 = getContactName(getContext()  , numf[1]);
                                            showContacts.append("Name 2 is "+name2);

                              //              ContactViewFragment.initializing(1);
                                        }
                                        else name2=null;
                                        if(numf[2]!=null)
                                        {
                                            name3 = getContactName(getContext()  , numf[2]);
                                            showContacts.append("Name 3 is "+name3);
                             //               ContactViewFragment.initializing(2);

                                        }

                                        else name3=null;
                                        if(numf[3]!=null)
                                        {
                                            name4 = getContactName(getContext()  , numf[3]);
                                            showContacts.append("Name 4 is "+name4);
                              //              ContactViewFragment.initializing(3);

                                        }

                                        else name4=null;
                                        if(numf[4]!=null)
                                        {
                                            name5 = getContactName(getContext()  , numf[4]);
                                            showContacts.append("Name 5 is "+name5);
                              //              ContactViewFragment.initializing(4);

                                        }
                                        else name5=null;
                                       // Toast.makeText(getContext(), "Number["+j+"] = "+numf[j], Toast.LENGTH_LONG).show();


                                        //     Toast.makeText(getContext(), "name1 = "+name1, Toast.LENGTH_LONG).show();
                                        //     Toast.makeText(getContext(), "Name 2 = "+name2, Toast.LENGTH_LONG).show();
                                        //     Toast.makeText(getContext(), "Name3 = "+name3, Toast.LENGTH_LONG).show();
                                        //     Toast.makeText(getContext(), "Nane4="+name4, Toast.LENGTH_LONG).show();
                                        //     Toast.makeText(getContext(), "Name5="+name5, Toast.LENGTH_LONG).show();


                                    if (name1!=null)
                                    {

                                        boolean result =myDb.insertData(name1 , numf[0]);
                                        numf[0]=null;
                                        name1=null;
                                        if (result==true)
                                            Toast.makeText(getContext() , "Successfully Added" , Toast.LENGTH_LONG).show();
                                        else
                                            Toast.makeText(getContext() , "FAILED to Add" , Toast.LENGTH_LONG).show();
                                    }
                                    else
                                        Toast.makeText(getContext() , "Name is NULL " , Toast.LENGTH_SHORT).show();

                                    if (name2!=null)
                                    {
                                        numf[1]=null;
                                        name2=null;
                                        boolean result =myDb.insertData(name2 , numf[1]);
                                        if (result==true)
                                            Toast.makeText(getContext() , "Successfully Added" , Toast.LENGTH_LONG).show();
                                        else
                                            Toast.makeText(getContext() , "FAILED to Add" , Toast.LENGTH_LONG).show();
                                    }
                                    else
                                        Toast.makeText(getContext() , "Name is NULL " , Toast.LENGTH_SHORT).show();

                                    if (name3!=null)
                                    {
                                        numf[2]=null;
                                        name3=null;
                                        boolean result =myDb.insertData(name3 , numf[2]);
                                        if (result==true)
                                            Toast.makeText(getContext() , "Successfully Added" , Toast.LENGTH_LONG).show();
                                        else
                                            Toast.makeText(getContext() , "FAILED to Add" , Toast.LENGTH_LONG).show();
                                    }
                                    else
                                        Toast.makeText(getContext() , "Name is NULL " , Toast.LENGTH_SHORT).show();


                                    if (name4!=null)
                                    {
                                        numf[3]=null;
                                        name4=null;
                                        boolean result =myDb.insertData(name4 , numf[3]);
                                        if (result==true)
                                            Toast.makeText(getContext() , "Successfully Added" , Toast.LENGTH_LONG).show();
                                        else
                                            Toast.makeText(getContext() , "FAILED to Add" , Toast.LENGTH_LONG).show();
                                    }
                                    else
                                        Toast.makeText(getContext() , "Name is NULL " , Toast.LENGTH_SHORT).show();

                                    if (name1!=null)
                                    {
                                        numf[4]=null;
                                        name5=null;
                                        boolean result =myDb.insertData(name5 , numf[4]);
                                        if (result==true)
                                            Toast.makeText(getContext() , "Successfully Added" , Toast.LENGTH_LONG).show();
                                        else
                                            Toast.makeText(getContext() , "FAILED to Add" , Toast.LENGTH_LONG).show();
                                    }
                                    else
                                        Toast.makeText(getContext() , "Name is NULL " , Toast.LENGTH_SHORT).show();

                                    /*   if(numf[0]!=null)
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
//                                ContactViewFragment.justToTrigger();
                    //                j++; i=0 ;
                        /*        Toast.makeText(getContext(), "Number="+numf[0], Toast.LENGTH_LONG).show();
                                Toast.makeText(getContext(), "Number="+numf[1], Toast.LENGTH_LONG).show();
                                Toast.makeText(getContext(), "Number="+numf[2], Toast.LENGTH_LONG).show();
                                Toast.makeText(getContext(), "Number="+numf[3], Toast.LENGTH_LONG).show();
                                Toast.makeText(getContext(), "Number="+numf[4], Toast.LENGTH_LONG).show(); */
                  //              }catch (ArrayIndexOutOfBoundsException e){

                                    //Toast.makeText(getContext() , "You Cannot Select More Than 5 Contacts" , Toast.LENGTH_SHORT).show();
                    //                customDialog("Alert User" , "You Cannot Select More Than 5 Contacts" , "cancelMethod1" , "okMethod1");
                      //          }




                   /*         }
                        }
                        break;
                    }

                }
        }  */
       // n1.setText(numf[0]);

   /*     if(numf[0]!=null)
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

      //  n2.setText(numf[1]);
      //  n3.setText(numf[2]);
     //   n4.setText(numf[3]);
    //    n5.setText(numf[4]);
        //Add Conditions Above
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
                c = myDb.getId();
                if(c != null && c.moveToFirst())
                {
                    id_to_remember = c.getInt(0);
                }
                else
                    id_to_remember = -1;
                id = id_to_remember ;
                Toast.makeText(getContext() , "Value Returned In Cursor "+ id_to_remember,Toast.LENGTH_SHORT).show();
                if(id<6){

                    boolean result =ContactFragment.myDb.insertData(id , name , num);
                   id++;
                   decision = myDb.updateID(id);
                    Toast.makeText(getContext() , "Successfully Update"+decision , Toast.LENGTH_SHORT).show();
                    if (result==true)
                        Toast.makeText(getContext() , "Successfully Inserted" , Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext() , "FAILED" , Toast.LENGTH_SHORT).show();

                }
                else
                {
                    customDialog("Alert User" , "Can't Pick More Than 5 Contacts." , "cancelMethod2" , "okMethod2");
                }

        }


    }


    public void whnSkipClicked(View view) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                new ContactFragment()).commit();
        //navigationView.setCheckedItem(R.id.nav_message);
        customDialog("Alert User" , "Are You Sure You Don't Want To Pick A Contact Now." , "cancelMethod2" , "okMethod2");
    }

  /*  public void whnNextClicked(View view) {

        if (num[].length()>2){
            getActivity().setContentView(R.layout.activity_main);
        }
        else {

            customDialog("Alert User" , "You Haven't Picked A Number! It Is Suggested To Choose A Number Before You Proceed" , "cancelMethod1" , "okMethod1");
        }
    } */

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

    public void customDialog(String title , String message , final String cancelMethod , final String okMethod){

        final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(getContext());
        builderSingle.setIcon(R.mipmap.or_icon_round);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);

        builderSingle.setNegativeButton(
                "NO" ,
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (cancelMethod.equals("cancelMethod1")){
                            cancelMethod1();
                        }
                        else if (cancelMethod.equals("cancelMethod2")){
                            cancelMethod2();
                        }
                    }
                });

        builderSingle.setPositiveButton(
                "YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (okMethod.equals("okMethod1")){
                            okMethod1();
                        }
                        else if (okMethod.equals("okMethod2")){
                            okMethod2();
                        }
                    }
                }
        );

        builderSingle.show();
    }

    private void cancelMethod1() {

        Toast.makeText(getContext(), "Inside CancelMethod1" , Toast.LENGTH_SHORT).show();
       // getActivity().setContentView(R.layout.activity_main);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                new MessageFragment()).commit();
        MainActivity.navigationView.setCheckedItem(R.id.nav_message);
    }

    private void cancelMethod2() {

        Toast.makeText(getContext(), "Inside CancelMethod2" , Toast.LENGTH_SHORT).show();
       // getActivity().setContentView(R.layout.activity_main);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                new MessageFragment()).commit();
        MainActivity.navigationView.setCheckedItem(R.id.nav_message);
    }

    private void okMethod1() {

        Toast.makeText(getContext() , "Inside OKMethod1" , Toast.LENGTH_SHORT).show();
       // getActivity().setContentView(R.layout.activity_main);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                new MessageFragment()).commit();
        MainActivity.navigationView.setCheckedItem(R.id.nav_message);
    }

    private void okMethod2() {

        Toast.makeText(getContext(), "Inside OKMethod2" , Toast.LENGTH_SHORT).show();
        //getActivity().setContentView(R.layout.activity_main);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                new MessageFragment()).commit();
        MainActivity.navigationView.setCheckedItem(R.id.nav_message);
    }

}

