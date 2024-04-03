package com.example.app02;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PersonalInfoActivity extends AppCompatActivity {

    private EditText editTextFullName;
    private EditText editTextDateOfBirth;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private Switch switchIsStudent;
    private TextView textViewResult;
    private Button buttonDisplay;
    private Button buttonBack;
    private Spinner spinnerDepartment;
    private String selectedDepartment;
    private CheckBox checkboxC, checkboxJs, checkboxCSharp;
    public String messageCheckbox = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextDateOfBirth = findViewById(R.id.editTextDateOfBirth);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        switchIsStudent = findViewById(R.id.switchIsStudent);
        buttonDisplay = findViewById(R.id.buttonDisplay);
        buttonBack = findViewById(R.id.buttonBack);
        spinnerDepartment = findViewById(R.id.spinnerDepartment);
        textViewResult = findViewById(R.id.textViewResult);
        checkboxC = findViewById(R.id.checkboxC);
        checkboxJs = findViewById(R.id.checkboxJs);
        checkboxCSharp = findViewById(R.id.checkboxCSharp);

        String[] departments = { "CNTT-TT", "KTCN" };
        // Create the instance of ArrayAdapter
        // having the list of departments
        ArrayAdapter adapter  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, departments);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerDepartment.setAdapter(adapter);

        //
        spinnerDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected item from the spinner
                selectedDepartment = (String) parentView.getItemAtPosition(position);
                // Do something with the selected item, such as displaying it in a Toast
//                Toast.makeText(getApplicationContext(), "Selected Department: " + selectedDepartment, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle case when nothing is selected
            }
        });

        // Thiết lập lắng nghe sự kiện thay đổi trạng thái của CheckBox
        CompoundButton.OnCheckedChangeListener checkBoxListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                        messageCheckbox += buttonView.getText().toString() + " ,";
                }
            }
        };
        // Thiết lập lắng nghe sự kiện cho mỗi CheckBox
        checkboxC.setOnCheckedChangeListener(checkBoxListener);
        checkboxJs.setOnCheckedChangeListener(checkBoxListener);
        checkboxCSharp.setOnCheckedChangeListener(checkBoxListener);
        // Set click listener for display button
        buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String fullName = editTextFullName.getText().toString();
                String dateOfBirth = editTextDateOfBirth.getText().toString();
                String gender = radioButtonMale.isChecked() ? "Nam" : "Nữ";
                boolean isStudent = switchIsStudent.isChecked();

                // Display information
                String result = "Thông tin đã nhập: " +  "\n"
                        + "Họ tên: " + fullName + "\n"
                        + "Ngày sinh: " + dateOfBirth + "\n"
                        + "Giới tính: " + gender + "\n"
                        + "Là sinh viên: " + (isStudent ? "Có" : "Không") + "\n"
                        + "Khoa: " + selectedDepartment + "\n"
                        + "Khả năng lập trình: " + messageCheckbox;
                //textViewResult.setText(result);
                //Toast.makeText(PersonalInfoActivity.this, result, Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(PersonalInfoActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage(result);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Xử lý khi người dùng bấm nút Đồng ý
                        dialog.cancel(); // Đóng AlertDialog
                    }
                });
                builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Xử lý khi người dùng bấm nút Hủy bỏ
                        dialog.cancel(); // Đóng AlertDialog
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalInfoActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close
            }
        });
    }
}