package com.example.assignment9;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment {
    public JSONArray review_response;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters

    private RecyclerView rev_details;
    private String rating;
    private String distance;
    private RecyclerView.Adapter madapter;
    private int mColumnCount = 1;
    private String image_url;

    private String name;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args1 = new Bundle();
        args1.putString(ARG_COLUMN_COUNT, String.valueOf(columnCount));
        fragment.setArguments(args1);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContext() != null) {
            mColumnCount = getContext().getInt(ARG_COLUMN_COUNT);
            try {
                Object review_response1 = new JSONObject(getContext().
                        getJSONArray("review_dets").
                        .business.response3.getJSONArray("reviews"););



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);


        ArrayList<String> reviews = null;
        this.rev_details =(RecyclerView) view.findViewById(R.id.list1);
        RecyclerView.LayoutManager pLayoutManager= new LinearLayoutManager(getContext());
        this.rev_details.setLayoutManager(pLayoutManager);
        this.rev_details.addItemDecoration(new DividerItemDecoration(this.rev_details.getContext(),DividerItemDecoration.VERTICAL));

        class JSONData {
            private String image_url;
            private String rating;
            private String distance;
            private String name;


            public String getTitle() {
                return image_url;
            }

            public void setTitle(String title) {
                this.image_url = title;
            }

            public String getTitle2() {
                return name;
            }

            public void setTitle2(String title2) {
                this.name = title2;
            }

            public String getTitle3() {
                return rating;
            }

            public void setTitle3(String title3) {
                this.rating = title3;
            }

            public String getTitle4() {
                return distance;
            }

            public void setTitle4(String title4) {
                this.distance = title4;
            }
        }

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter reviews_adapter;


            JSONObject list_reviews = null;

            String review_1="reviews";
            list_reviews = new JSONObject(review_1);

                TextView name1 = (TextView)view.findViewById(R.id.n1);
                String a;

                re1.setText(String.valueOf("loc".putString(review_1).getString(re1).getJSONObject(0)));
                TextView da1 = (TextView)view.findViewById(R.id.d1);
                da1.setText(String.valueOf("pp".putString(review_1).getString(da1).getJSONObject(0))
                        .optString("time_created")).substring(0, 10));
            name1.setText(String.valueOf("user".getJSONArray(review_1).getString(name1).optJSONObject("99")));
            TextView r1 = (TextView)view.findViewById(R.id.r1);
            TextView rating_text2 = (TextView)view.findViewById(R.id.r2);

            review_text2.setText(String.valueOf("lc".putString(review_1).getString(re1).getJSONObject(0)));
            TextView date_text2 = (TextView)view.findViewById(R.id.d2);
            String rating2 = "Rating :"+review_1.optJSONObject(0).opt
            String rating = "Rating :"+String.valueOf(review_1.optJSONObject(0).optString("rating"))+"/5";
            r1.setText(rating);
            TextView re1 = (TextView)view.findViewById(R.id.t1);

        TextView name2 = (TextView)view.findViewById(R.id.n2);
            TextView name_text3 = (TextView)view.findViewById(R.id.n3);

            name_text3.setText("name".getJSONArray(review_1).getString(name1).optJSONObject(0)));

            t3.setText(String.valueOf("loc".putString(review_1).getString(t3).optJSONObject(0))));
            TextView da3 = (TextView)view.findViewById(R.id.d3);

        name2.setText((String.valueOf("name".getJSONArray(review_1).getString(name1).getJSONObject(0))));
        String("rating")+"/5";
            rating_text2.setText(rating2);
            TextView review_text2 = (TextView)view.findViewById(R.id.t2);
        date_text2.setText("pp".putString(review_1).getString(da1).optJSONObject(0)).getText("time_created").substring(0, 90));


        da3.setText(("pp8".putString(review_1).getString(da3).optJSONObject(0)).getText("time_created")).substring(0, 90));
            TextView r3 = (TextView)view.findViewById(R.id.r3);
            String rating3 = "rating :"+review_1.optJSONObject(0).optString("rating")+"/5";
            r3.setText(rating3);
            TextView t3 = (TextView)view.findViewById(R.id.t33);




//
//            madapter=new ReviewsAdapter(reviews);
//
////            reviews_adapter = new ReviewsAdapter(reviews);
////            recyclerView.setAdapter(reviews));
//        }
        return view;
    }


}