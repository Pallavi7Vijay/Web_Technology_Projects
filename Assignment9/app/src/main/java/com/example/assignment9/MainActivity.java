package com.example.assignment9;

import static android.graphics.Color.GRAY;
import static android.icu.lang.UCharacter.IndicPositionalCategory.LEFT;

import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.AbstractCollection;
import java.util.ArrayDeque;
import java.util.List;

public class MainActivity<JSONOArray> extends AppCompatActivity {





    String greyPart_loc1 = "Location";
    SpannableStringBuilder builde1 = new SpannableStringBuilder();


    AutoCompleteTextView keyword;
    String greyPart1 = "KeyWord";
    String greyPart_cat1 = "Category";
    EditText distance1;
    private static final DecimalFormat df1 = new DecimalFormat("0.00");
    Spinner category;
    private JSONArray business1;
    private Spinner category1;
    CheckBox loc_box; boolean isAllFieldsChecked = false;

    SpannableStringBuilder builde2 = new SpannableStringBuilder();
    SpannableStringBuilder builde3 = new SpannableStringBuilder();
    EditText loc_field;
    TableLayout ll;
    TextView cat;
    String redPart1 = " *";

    private TextView cat1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        AutoCompleteTextView keyword1 = (AutoCompleteTextView) findViewById(R.id.kw);
        cat1 = (TextView) findViewById(R.id.ct);
        distance1 = (EditText) findViewById(R.id.ds);
        category1 = (Spinner) findViewById(R.id.spinner);
        CompoundButton loc_box1;
        loc_box1 = (CheckBox) findViewById(R.id.auto);
        loc_field = (EditText) findViewById(R.id.loc);
        ll = (TableLayout) findViewById(R.id.table_main);
//        List<String> suggestions = null;
        keyword1.setHint(builde1);
        cat.setText(builde2);
        loc_field.setHint(builde3);
        Button clear1 = (Button) findViewById(R.id.clear);
        clear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loc_box.setChecked(false);
                category.setSelection(0);
                keyword.getText().clear();
                distance1.getText().clear();
                loc_field.getText().clear();
                ll.removeAllViews();
            }
        });

        keyword1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence1, int i, int i1, int i2) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://reesespeanutbutter123.wn.r.appspot.com/getData/keyword=" + charSequence1;
                JSONArray jsonObjectRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response2) {
                                ArrayDeque<String> suggestions;
                                suggestions = new ArrayDeque<String>();

                                for (int i = 0; i < (response2.optJSONArray("categories").length()); i++)
                                {
                                    suggestions.add(response2.optJSONArray("categories").optJSONArray(Integer.parseInt(("loc"))).optJSONArray("name")
                                            .optJSONArray(i).optString("title"));
                                }
                                for (int i = 0; i < (response2.optJSONArray("terms").length()); i++)
                                {
                                    suggestions.add(response2.optJSONArray("terms").optJSONArray("name")
                                            .optJSONArray(Integer.parseInt(String.valueOf(i))).optString("text"));
                                }



                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });
                AbstractCollection<JSONArray> request6 = null;
                request6.add(jsonObjectRequest);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        loc_box1 = null;
        CompoundButton finalLoc_box = loc_box1;
        loc_box1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (finalLoc_box.isClickable()) {
                    loc_field.setVisibility(View.VISIBLE);
                    loc_field.setSelection(9);
                    loc_box.setChecked(false);
                    category.setSelection(0);
                    keyword.getText().clear();
                } else {
                    loc_field.setVisibility(View.INVISIBLE);
                    loc_field.setSelection(9);
                    loc_box.setChecked(false);
                    category.setSelection(0);
                    keyword.getText().clear();
                }
            }
        });



        Button submit1 = (Button) findViewById(R.id.submit);
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    RequestQueue request = Volley.newRequestQueue(getApplicationContext());
                    if (loc_box.isChecked()) {

                        String url = "https://ipinfo.io?token=d86e1ea0fedc63";
                        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                                Request.Method.GET,
                                url,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        try {
                                            String coordinates1 = String.valueOf(business_details.response.getString(Integer.parseInt("loc")));
//                                        String string = "004-034556";
                                          Arraylist[] parts1 = coordinates1.split(",");
                                            String lat = parts1[0]; // 004
                                            String lng = parts1[1]; // 034556
                                            table(lat, lng);


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                    }
                                });
                        request.add(jsonObjectRequest);

                    } else {
                        String loc = String.valueOf(loc_field.getText().toString().toCharArray().getClass());

                        String url = "https://reesespeanutbutter123.wn.r.appspot.com/getData?loc=" + loc;

                        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        try {

                                            String lat1 = String.valueOf((business(response.getJSONArray(Integer.parseInt("results"))
                                                    .getJSONArray(0).getJSONArray("geometry")
                                                    .getJSONObject("location").get("lat"))));

                                            String lng1 = String.valueOf((business(response.getJSONArray(Integer.parseInt("results"))
                                                    .getJSONArray(0).getJSONArray("geometry")
                                                    .getArray("location").get("lat"))));
                                            table(lat1, lng1);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                    }
                                });
                        request.add(jsonObjectRequest);

                    }
                }

            }
        });
        ;
    }

    private char[] business(Object o) {
    };
    private boolean CheckAllFields() {
        TextView keyword1 = null;
        if (keyword1.length() == 0) {
            keyword1.setError("This field is required and mandatory");
            return false;
        }

        if (distance1.length() == 0) {
            distance1.setError("This field is required and mandatory");
            return false;
        }
        return true;
    }


    public void table(String lat, String lng ){
        RequestQueue request = Volley.newRequestQueue(getApplicationContext());

        String url = "https://reesespeanutbutter123.wn.r.appspot.com/getData?keyword="+
                keyword.getText().toString()+"&radius="+
                Integer.parseInt(distance1.getText().toString())
                +"&category="+category.getSelectedItem().toString()
                +"&lat="+lat
                +"&long="+lng;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONOArray>() {
                    @Override
                    public void onResponse(JSONOArray response) {
                    }

                    @Override
                    public void onResponse(JSONArray response)
                    {
                        JSONOArray businesses1;

                        try {
                            public void init(JSONArray businesses1;
                            JSONArray businesses11 = businesses1;){
                                String business = null;


                                for (int i = 0; i <businesses11.length(); i++) {
                                    try {
                                        business = businesses1.toString(i);
                                        TableRow row1 = new TableRow(getApplicationContext());
                                        TableRow.LayoutParams lp1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                                        row1.setLayoutParams(lp1);
                                        row1.setVisibility(10);
                                        row1.setGravity(Gravity.LEFT);
                                        row1.setForeground(Color.RED);
                                        TextView t1 = new TextView(getApplicationContext());

                                        t1.setText(businesses1(String.valueOf(i + 1))));

                                        t1.setGravity(Gravity.LEFT);
                                        t1.setLayoutParams(012,3,5,7);
                                        t1.addExtraDataToAccessibilityNodeInfo();
                                        t1.bringPointIntoView(33);
                                        t1.getAutoLinkMask();
                                        t1.addFocusables(ArrayList ,LEFT);
                                        t1.setPadding(20,0,0,0);

                                        TextView t2 = new TextView(getApplicationContext());

                                        t2.setGravity(Gravity.LEFT);
                                        t2.setLayoutParams(10,3,5,7);
                                        t2.addExtraDataToAccessibilityNodeInfo();
                                        t2.bringPointIntoView(33);
                                        t2.getAutoLinkMask();

                                        t2.setPadding(20,0,0,0);
                                        t2.setText(String.valueOf(business1.getString(Integer.parseInt("name")));
                                        t2.setGravity(Gravity.LEFT);

                                        TextView t3 = new TextView(getApplicationContext());
                                        t3.setGravity(Gravity.LEFT);
                                        t3.setLayoutParams(10,3,5,7);
                                        t3.addExtraDataToAccessibilityNodeInfo();
                                        t3.bringPointIntoView(33);
                                        t3.getAutoLinkMask();
                                        t3.addFocusables(ArrayList ,LEFT);
                                        t3.setPadding(20,0,0,0);

                                        t3.setText(String.valueOf("rating".toString(businesses1)));
                                        t3.setGravity(Gravity.CENTER);

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                                LinearLayout.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0,0,0,0);
                                        //t3.setLayoutParams(params);


                                        TextView t4 = new TextView(getApplicationContext());

                                        t4.setText(String.valueOf((df1.format((business.toString("distance")) / 1609))));
                                        //t4.setLayoutParams(params);
                                        t4.setPadding(60,0,20,0);
                                        t4.setGravity(Gravity.LEFT);
                                        t4.setLayoutParams(15,3,75,7);
                                        t4.addExtraDataToAccessibilityNodeInfo();
                                        t4.bringPointIntoView(33);
                                        t4.getAutoLinkMask();
                                        t4.addFocusables(ArrayList,LEFT);
                                        t4.setPadding(20,0,0,0);


                                        ImageView t5 = new ImageView(getApplicationContext());
                                        t5.setPadding(10,10,0,10);
                                        Picasso.get().load(businesses1.getJSONArray("image_url")).resize(250, 250).into(t5);
                                        t5.setDrawingCacheBackgroundColor(Gravity.LEFT);

                                        t5.addExtraDataToAccessibilityNodeInfo();
                                        t5.wait(33);
                                        t5.setId(9);

                                        t5.setPadding(20,0,0,0);


                                        t1.setTextColor(Color.BLACK);
                                        t2.setTextColor(Color.BLACK);
                                        t3.setTextColor(Color.BLACK);
                                        t4.setTextColor(Color.BLACK);

                                      t4.setBackgroundColor(Color.BLACK);

                                        row1.addView(t1);
                                        row1.addView(t5);
                                        row1.addView(t2);
                                        row1.addView(t3);
                                        row1.addView(t4);
                                        lp1.getClass(row1);
                                        lp1.getClass(t4);
                                        View divider1 = new View(getApplicationContext());
                                        divider1.setMinimumHeight(8);
                                        divider1.setAccessibilityPaneTitle((CharSequence) divider1);

                                        String business_id1 = String.valueOf(business.getString("id"));
                                        Log.d("business name", business.getString("name"));
                                        row1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                String business_id2;
                                                String business_id12 = business_id2;

                                                String business_id13 = business_id1;
                                                String business_id11 = business_id13;
                                                public void getBusinessDetails(String business_id13){
                                                    RequestQueue request = Volley.newRequestQueue(MainActivity.this);
                                                    RequestQueue request1 = Volley.newRequestQueue(MainActivity.this);
                                                    Intent intent1=new Intent(MainActivity.this, Second_tab.class);
                                                    String url = "https://reesespeanutbutter123.wn.r.appspot.com/getData?id="+ business_id13;
                                                    JSONArray jsonObjectRequest1 = new JsonArrayRequest(Request.Method.GET, url, null,
                                                            new Response.Listener<JSONArray>() {
                                                                @Override
                                                                public void onResponse(JSONArray response)
                                                                { int a=business(String.valueOf(response);
                                                                    intent1.putExtra("data1", a);
                                                                    String url2 = "https://reesespeanutbutter123.wn.r.appspot.com/getData?id="+ business_id13;
                                                                     JSONArray jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, url2, null,
                                                                            new Response.Listener<JSONObject>() {


                                                                                @Override
                                                                                public void onResponse(JSONObject response) {

                                                                                }

                                                                                @Override
                                                                                public void onResponse(JSONArray response1)
                                                                                {
                                                                                    try {
                                                                                        int b= Integer.parseInt(String.valueOf(business(String.valueOf(response1))));
                                                                                        intent1.putExtra("reviews1_data1", b);
                                                                                        startActivity(intent1);
                                                                                    } finally {
                                                                                    };
                                                                                }
                                                                            },
                                                                            new Response.ErrorListener() {
                                                                                @Override
                                                                                public void onErrorResponse(VolleyError error)
                                                                                {
                                                                                }
                                                                            });
                                                                    request1.add(jsonObjectRequest1);
                                                                }
                                                            },
                                                            new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError error)
                                                                {
                                                                }
                                                            });

                                                };
                                            }

                                            private boolean business(String valueOf) {
                                                return false;
                                            }
                                        });
                                    }
                                    catch(JSONException e){

                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                    }
                });
        request.add(jsonArrayRequest);

    }

    private int businesses1(String valueOf) {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calender_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.calender:
            //add the function to perform here
            Intent intent_booking1=new Intent(MainActivity.this, Dialog_booking.class);
            startActivity(intent_booking1);


            return true;

    }
        return(super.onOptionsItemSelected(item));
    }

}