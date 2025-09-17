package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.listycitylab3.City;
import com.example.listycitylab3.R;

public class AddCityFragment extends DialogFragment {
    int whichcityPosition=-2;
    private City city;
    private CityArrayAdapter thisAdapter;
    interface AddCityDialogListener {
        void addCity(City city);
        void refreshthescreen();

    }
    private AddCityDialogListener listener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }
    public static AddCityFragment newInstance(int whichcityPosition, City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view =
                LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        Bundle editcityinformation = getArguments();



        if(editcityinformation !=null) {
            city = (City) editcityinformation.getSerializable("city");
            editCityName.setText(city.getName());
            editProvinceName.setText(city.getProvince());
            return builder
                    .setView(view)
                    .setTitle("Edit city")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("OK", (dialog, which) -> {
                        String cityName = editCityName.getText().toString();
                        String provinceName = editProvinceName.getText().toString();
                        this.city.setProvince(provinceName);
                        this.city.setName(cityName);
                        listener.refreshthescreen();
                        whichcityPosition = -2;
                    })
                    .create();}
        else{
            return builder
                    .setView(view)
                    .setTitle("Add a city")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Add", (dialog, which) -> {
                        String cityName = editCityName.getText().toString();
                        String provinceName = editProvinceName.getText().toString();
                        listener.addCity(new City(cityName, provinceName));
                    })
                    .create();}
        }
        }


