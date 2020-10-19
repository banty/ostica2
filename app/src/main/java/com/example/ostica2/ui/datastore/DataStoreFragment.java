package com.example.ostica2.ui.datastore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ostica2.R;
import com.example.ostica2.model.Article;
import com.example.ostica2.remote.ArticleInterface;
import com.example.ostica2.remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataStoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataStoreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArticleInterface articleInterface;
    TextView textId;
    TextView textUserId;
    TextView textTitle;
    TextView textBody;


    public DataStoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataStoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataStoreFragment newInstance(String param1, String param2) {
        DataStoreFragment fragment = new DataStoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_store, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



         textBody=view.findViewById(R.id.textBody);
         textId=view.findViewById(R.id.textId);
         textTitle=view.findViewById(R.id.textTitle);
         textUserId=view.findViewById(R.id.textUserId);

         articleInterface= RetrofitClient.getClient().create(ArticleInterface.class);

        Call<List<Article>> listCall=articleInterface.getArticles();

        listCall.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if(response.isSuccessful()){
                    List<Article> articles=response.body();


                    textBody.setText(articles.get(0).body);
                    textId.setText("ID:"+articles.get(0).id);
                   textTitle.setText(articles.get(0).title);
                   textUserId.setText("UserId:"+articles.get(0).userId);




                    Toast.makeText(getContext(),"No Articles:"+articles.size(),Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(),"No Articles:"+response.message(),Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Toast.makeText(getContext(),"No Articles:"+ t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });



    }
}