package com.example.listycitylab3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {
    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;
    int whichCityisSelected = -2;

    @Override
    public void refreshthescreen(){
        cityAdapter.notifyDataSetChanged();
    }
    @Override
    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] cities = { "Edmonton", "Vancouver", "Toronto" };
        String[] provinces = { "AB", "BC", "ON" };
        dataList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }
        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);
        FloatingActionButton fab = findViewById(R.id.button_add_city);

        fab.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                whichCityisSelected= position;
                if (whichCityisSelected != -2) {
                    City cityinformation = dataList.get(whichCityisSelected);
                    AddCityFragment editcityfragment = AddCityFragment.newInstance(whichCityisSelected, cityinformation);
                    whichCityisSelected = -2;
                    editcityfragment.show(getSupportFragmentManager(), "Editing City");
                }
            }});


    }

}
