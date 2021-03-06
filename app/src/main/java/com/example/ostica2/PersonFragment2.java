package com.example.ostica2;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ostica2.data.Person;
import com.example.ostica2.data.PersonDao;
import com.example.ostica2.data.PersonDatabase;
import com.example.ostica2.dummy.DummyContent;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class PersonFragment2 extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    RecyclerView recyclerView;
    PersonDao personDao;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PersonFragment2() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PersonFragment2 newInstance(int columnCount) {
        PersonFragment2 fragment = new PersonFragment2();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
             recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PersonDatabase personDatabase= PersonDatabase.getInstance(getContext());
        personDao=personDatabase.personDao();

        PersonAsync personAsync= new PersonAsync();
        personAsync.execute();
    }

    private class PersonAsync extends AsyncTask<Void,Integer, List<Person>> {

        @Override
        protected List<Person> doInBackground(Void... voids) {
            return  personDao.getPersonList();


        }

        @Override
        protected void onPostExecute(List<Person> people) {
            super.onPostExecute(people);

            recyclerView.setAdapter(new MyPersonRecyclerViewAdapter(people));


        }
    }

}