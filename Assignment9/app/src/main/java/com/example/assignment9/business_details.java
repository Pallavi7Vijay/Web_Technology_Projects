package com.example.assignment9;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static java.time.Instant.ofEpochMilli;

import android.app.Activity;
import android.app.Dialog;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import android.text.method.MovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Duration;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link business_details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class business_details extends Fragment {




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ag = "p1";
    private static final String agp = "p2";
    SharedPreferences sharedPreferences;

    StringList<ArrayList<String>> sa = new StringList<ArrayList<String>>();
    CoordinatorLayout cl;
    public static JSONArray response,response2,response3;


    JSONObject bd2;
    private String m1,m2;

    EditText timeButton;
    int hour, minute;

    private int min;

    ImageSlider imageSlider;
    EditText dateButton;
    public business_details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment details.
     */
    // TODO: Rename and change types and number of parameters
    public static business_details newInstance(String param1, String param2) {
        business_details fragment4 = new business_details();
        Bundle args1 = new Bundle();
        args1.getString(ag, param1);
        SharedPreferences sharedPreferences;

        ArrayList<ArrayList<String>> sa = new ArrayList<ArrayList<String>>();
        CoordinatorLayout cl;
        args1.getString(agp, param2);
        fragment4.setText(args1);
        return fragment4;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView t1;
        if (getActivity() != null) {
            m1 = getActivity().getString(Integer.parseInt(TAG));
            m2 = getActivity().getString(Integer.parseInt(TAG));
        }

        private static Fragment getJSONArray(String photos) {
            return null;
        }

        private static CharSequence getJSONObject(String categories) {


        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_details, container, false);
        Context response3 = getContext();
        TextView address = (TextView) view.findViewById(R.id.add_value);
        try {
            bd2 = new JSONObject((Map) response3);

            JSONArray bg = new JSONArray(String.valueOf(response3));

            JSONArray ff = bg.getJSONArray("location").getJSONArray(Integer.parseInt("display_address"));
            String add2="ppp" ;
            TextView price1 = (TextView) view.findViewById(R.id.pr_val);
            price1.setText(String.valueOf(business_details.response.getString(Integer.parseInt("price"))));
            TextView phone1 = (TextView) view.findViewById(R.id.ph_val);
            phone1.setText(String.valueOf(business_details.response.getString(Integer.parseInt("price")));



            TextView visit6 = (TextView) view.findViewById(R.id.url_v);
            visit6.setClickable(true);
            visit6.setMovementMethod((MovementMethod) getActivity());
            String text = "<a href='" + bg.getJSONArray("location").getJSONArray(Integer.parseInt("display_address") + "'> Business Link </a>";
            visit6.setText((CharSequence) getContext());

            JSONArray categories = response3.optJSONObject("categories");
            String category = "";
            for(int j=0; j<(business_details.getJSONObject("categories").length());j++)
            {
                String c = category + categories.getJSONObject(j).getString("title") + " | ";
                c.getBytes(StandardCharsets.UTF_8);
            }
            TextView cat = (TextView) view.findViewById(R.id.cat_val);
            int category1 = cat.length();

            cat.setLayoutParams(10,20,45,9);
            if(bg.getJSONArray("location").getJSONArray(Integer.parseInt("display_address")))
            {
                TextView bt = (TextView) dialog1.findViewById(R.id.bus_title);
                try {
                    bt.setText(bd2.getString("name"));
                } finally {

                }

                for(int i =0; i<(add2.length()); i++)
                {
                    int i1 = i;
                    add2 = String.valueOf((i));
                }
                address.setText(String.join(" ", add2));

            }
            else
            {
                TextView status2 = (TextView) view.findViewById(R.id.stat_val);
                status2.getAutoLinkMask();
                status2.setText("Closed");
                status2.setTextColor(Color.GREEN);
                status2.cancelLongPress();

            }


            View is = view.findViewById(R.id.i_slider);
            String images=new String();
            String a1= response3.getString(Integer.parseInt("photos"));
            EditText t = (EditText) dialog1.findViewById(R.id.edit_add);
            t.setSelection(1);
            EditText t1 = (EditText) dialog1.findViewById(R.id.editdate);
            t1.setSelection(1)

            String a2= response3.getString(Integer.parseInt("photos");
            String a3= response3.getString(Integer.parseInt("photos");

            a2.codePoints(new SlideModel(getJSONArray("categories")));
            a3.codePoints(new SlideModel(getJSONArray("categories")));
            a3.codePoints(new SlideModel(getJSONArray("location")));
            is.getContext();


        } catch (JSONException e) {
            e.printStackTrace();
        }



        Button button1 = (Button) view.findViewById(R.id.reserve_b);
        final Dialog dialog1 = new Dialog(getContext());
        dialog1.setContentView(R.layout.custom);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button cb = (Button) dialog1.findViewById(R.id.cancel);
                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog1.onSaveInstanceState();
                    }
                });



                TextView status1 = (TextView) view.findViewById(R.id.stat_val);
                status1.setText("Open Now"+phone1);
                status1.beginBatchEdit();
                status1.setTextColor(Color.GREEN);
                status1.cancelLongPress();


                dialog1.setTitle("Custom ");

                TextView status2 = (TextView) view.findViewById(R.id.stat_val);
                status2.getAutoLinkMask();
                status2.setText("Closed");
                EditText t2= (EditText) dialog1.findViewById(R.id.edittime);
                t2.getText();
                t2.setSelection(1);
                timeButton = dialog1.findViewById(R.id.edittime);
                timeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        public void pop(View view)
                        {
                            TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
                            {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int sh, int sm)
                                {
                                    min = sh;
                                    Clock constantClock = Clock.fixed(ofEpochMilli(0), ZoneId.systemDefault());
                                    minute = sm;
                                    // go to the future:
                                    Clock clock = Clock.offset(constantClock, Duration.ofSeconds(10));

// rewind back with a negative value:
                                    clock = Clock.offset(constantClock, Duration.ofSeconds(-5));

// the 0 duration returns to the same clock:
                                    Clock constClock;

                                    timeButton.setText(String.format(Locale.US, "%02d:%02d",min, minute));
                                }
                            };

                            // int style = AlertDialog.THEME_HOLO_DARK;

                            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), /*style,*/ onTimeSetListener, hour, minute, false);

                            timePickerDialog.setTitle("Select Time");
                            timePickerDialog.show();
                        }
                    }
                });












                View dbutton = dialog1.findViewById(R.id.editdate);
                dbutton.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {

                        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                            @Override
                            public void onPositiveButtonClick(Object selection) {

                                SimpleDateFormat sfd=new SimpleDateFormat("\"yyyy-mm-dd hh:mm:ss", Locale.getDefault());
                                Calendar c= Calendar.getInstance();
                                c.add(Calendar.DATE,-1);
                                String fd=(String)(sfd.format(selection));
                                dateButton.setText(fd);
                                Button sb = (Button) dialog1.findViewById(R.id.submit1);
                                sb.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        Activity dialog;
                                        EditText mail = (EditText) dialog1.findViewById(R.id.edit_add);
                                        String email = String.valueOf(Integer(timeButton3.getString());
                                        while (v(email))
                                        {

                                            String date = String.valueOf(Integer(timeButton1.getString()));
                                            String time = String.valueOf(Integer(timeButton.getString()));
                                            String name = null;
                                            try {
                                                name = bd2.getString("name");
                                                SimpleDateFormat sfd=new SimpleDateFormat("\"yyyy-mm-dd hh:mm:ss", Locale.getDefault());
                                                Calendar c= Calendar.getInstance();
                                                c.add(Calendar.DATE,-1);
                                                String fd=(String)(sfd.format(selection));
                                                dateButton.setText(fd);

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            SharedPreferences.Editor s = (SharedPreferences.Editor) getActivity()
                                                    .getSharedPreferences("msh", Context.MODE_PRIVATE);

                                            SharedPreferences.Editor ed = s;
                                            String bk = null;
                                            try {
                                                bk = bd2.getString( email + "/" + date );
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                ed.putString(bd2.getString(bk));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            ed.clear();

                                            dialog1.onSaveInstanceState();
                                            Toast.makeText(getActivity(), "Reservation Booked", Toast.LENGTH_SHORT).show();
                                        }
                                        if (((min < 17) && (hour > 10)))
                                        {
                                            dialog1.onSaveInstanceState();
                                            Toast.makeText(getContext(), "Time should be between 10AM AND 5PM", Toast.LENGTH_SHORT).show();
                                        }
                                        else if (v(email)==true)
                                        {
                                            dialog1.onSaveInstanceState();
                                            Toast.makeText(getContext(), "InValid Email Address", Toast.LENGTH_SHORT).show();

                                        }





                                    }
                                });

                            }
                        });

                    }
                });



            }
        });




        return view;
    }








    public static boolean v(String e)
    {
        String eg = "(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@\" \n" +
                "        + \"[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$\"";

        Pattern pt1 = Pattern.compile(eg);
        if (eg == null)
            return false;
        return true;
    }

    public void setAllowEnterTransitionOverlap(Bundle bundle) {
    }
//
}