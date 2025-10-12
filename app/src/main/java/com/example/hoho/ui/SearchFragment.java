package com.example.hoho.ui;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hoho.MainActivity2;
import com.example.hoho.R;
import com.example.hoho.data.contract.DatabaseContract;
import com.example.hoho.data.entities.Item;
import com.example.hoho.data.repository.ItemRepository;
import com.example.hoho.ui.adapter.MiniListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    EditText editText;
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
    ItemRepository repository;
    ArrayList<Item> items;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /// ТЯП ЛЯП ЛЕНЬ ДЕЛАТЬ ПОТОМ ДОДЕЛАЮ
        TextView textView1 = view.findViewById(R.id.textView1);
        editText = view.findViewById(R.id.editText);
        repository = new ItemRepository(requireContext());


        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Map<DatabaseContract, String> filters = new HashMap<>();
                filters.put(DatabaseContract.WORLD, String.valueOf(editText.getText()));
                filters.put(DatabaseContract.TRANS, String.valueOf(editText.getText()));

                items = (ArrayList<Item>) repository.search(filters);


                if(!items.isEmpty()) {
                    MiniListAdapter stateAdapter = new MiniListAdapter(requireContext(), R.layout.grid_mini_list, items);
                    ListView countriesList = view.findViewById(R.id.list);
                    countriesList.setAdapter(stateAdapter);
                    AdapterView.OnItemClickListener itemListener = getOnItemClickListener();
                    countriesList.setOnItemClickListener(itemListener);
                    textView1.setVisibility(GONE);
                }else {
                    textView1.setVisibility(VISIBLE);
                }
            }
        });
        //////ADAPTER\\\\\\


    }

    private AdapterView.OnItemClickListener getOnItemClickListener() {
        Intent intent = new Intent(requireContext(), MainActivity2.class);
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Item item = (Item) parent.getItemAtPosition(position);
                intent.putExtra("QUERY", item.getWorld());
                startActivity(intent);
            }
        };
        return itemListener;
    }
}