package com.example.ostica2;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ostica2.data.Person;
import com.example.ostica2.data.PersonDao;
import com.example.ostica2.data.PersonDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    PersonDao personDao;

    public PersonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonFragment newInstance(String param1, String param2) {
        PersonFragment fragment = new PersonFragment();
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
        return inflater.inflate(R.layout.fragment_person, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PersonDatabase personDatabase=PersonDatabase.getInstance(getContext());
        personDao=personDatabase.personDao();

        final EditText textName= view.findViewById(R.id.textName);
        final EditText textCity=view.findViewById(R.id.textCity);

        Button butonInsert= view.findViewById(R.id.btnInsert);
        butonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              PersonAsync personAsync= new PersonAsync();
              personAsync.execute(textName.getText().toString(),textCity.getText().toString());

            }
        });

    }

    private class PersonAsync extends AsyncTask<String,Integer,Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            Person person= new Person(params[0],params[1]);
            personDao.insertPerson(person);
            return 1;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            if(integer ==1)
                Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();


        }
    }


}