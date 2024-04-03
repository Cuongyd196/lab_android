package com.example.app02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

public class ListviewBasicActivity extends AppCompatActivity {
    private ArrayList<String> listOfTasks = new ArrayList<>();
    private ListView listView;

    private Button buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listview_basic);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Khởi tạo dữ liệu mẫu
        listOfTasks.add("Lập trình c");
        listOfTasks.add("Lập trình JS");
        listOfTasks.add("Đọc sách");
        listOfTasks.add("Xem phim");
        listOfTasks.add("Học tiếng anh");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfTasks);
        listView = findViewById(R.id.listviewBasic);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedTask = listOfTasks.get(position);
                Toast.makeText(getApplicationContext(), "Công việc được chọn: " + selectedTask, Toast.LENGTH_SHORT).show();
            }

        });

        buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListviewBasicActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close
            }
        });


    }
}