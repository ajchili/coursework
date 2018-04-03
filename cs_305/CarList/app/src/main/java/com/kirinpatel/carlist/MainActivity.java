package com.kirinpatel.carlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.kirinpatel.carlist.adapters.CarAdapter;
import com.kirinpatel.carlist.utils.Car;
import com.kirinpatel.carlist.utils.Cars;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUi();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_car, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_car_create:
                Intent intent = CarActivity.newIntent(MainActivity.this, null);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUi() {
        List<Car> cars = Cars.get(MainActivity.this).getCars();

        carAdapter = new CarAdapter(cars);
        recyclerView.setAdapter(carAdapter);
    }
}
