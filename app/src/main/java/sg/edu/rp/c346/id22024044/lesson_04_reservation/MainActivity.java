package sg.edu.rp.c346.id22024044.lesson_04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView nameDisplay;
    EditText nameInput;
    TextView phoneDisplay;
    EditText phoneInput;
    TextView sizeDisplay;
    EditText sizeInput;

    TextView dateView;
    DatePicker datePicker;
    TextView timeView;
    TimePicker timePicker;

    RadioGroup rgSmoking;

    CheckBox cbConfirm;

    Button btnSubmit;
    Button btnReset;

    TextView finalName;
    TextView finalPhone;
    TextView finalSize;
    TextView finalDate;
    TextView finalTime;
    TextView finalArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameDisplay=findViewById(R.id.viewName);
        nameInput=findViewById(R.id.editName);
        phoneDisplay=findViewById(R.id.viewMobileNumber);
        phoneInput=findViewById(R.id.editMobileNumber);
        sizeDisplay=findViewById(R.id.viewGroupSize);
        sizeInput=findViewById(R.id.editGroupSize);

        dateView=findViewById(R.id.viewDate);
        datePicker=findViewById(R.id.datePicker);
        timeView=findViewById(R.id.viewTime);
        timePicker=findViewById(R.id.timePicker);

        rgSmoking=findViewById(R.id.radioGroupSmoking);

        cbConfirm=findViewById(R.id.checkBoxConfirmation);

        btnSubmit=findViewById(R.id.buttonSubmit);
        btnReset=findViewById(R.id.buttonReset);

        finalName=findViewById(R.id.submitName);
        finalPhone=findViewById(R.id.submitPhone);
        finalSize=findViewById(R.id.submitSize);
        finalDate=findViewById(R.id.submitDate);
        finalTime=findViewById(R.id.submitTime);
        finalArea=findViewById(R.id.submitArea);

        cbConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString();
                String phone = phoneInput.getText().toString();
                String size = sizeInput.getText().toString();

                Integer bkDay = datePicker.getDayOfMonth();
                Integer bkMonth = datePicker.getMonth() + 1;
                Integer bkYear = datePicker.getYear();

                String date = bkDay + "/" + bkMonth + "/" + bkYear;

                Integer bkHour = timePicker.getCurrentHour();
                Integer bkMinute = timePicker.getCurrentMinute();

                String time;
                if (bkHour < 10 && bkMinute < 10) {
                    time = "0" + bkHour + ":0" + bkMinute;
                } else if (bkHour < 10) {
                    time = "0" + bkHour + ":" + bkMinute;
                } else if (bkMinute < 10) {
                    time = bkHour + ":" + bkMinute;
                } else {
                    time = bkHour + ":" + bkMinute;
                }

                int checkedRadioId = rgSmoking.getCheckedRadioButtonId();

                String area;
                if (checkedRadioId == R.id.radioButtonSmoking) {
                    area = "Smoking Area";
                } else {
                    area = "Non-Smoking Area";
                }

                String confirmedCheck = "Reservation Information\n"+ "\nName: " + name + "\nPhone Number: " + phone + "\nGroup Size: " + size + "" +
                        "\nDate: " + date + "\nTime (24h): " + time + "\nReservation Table: " + area;

                if (cbConfirm.isChecked()) {
                    Toast confirmation = Toast.makeText(MainActivity.this, confirmedCheck, Toast.LENGTH_SHORT);
                    confirmation.show();
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cbConfirm.isChecked()) {
                    Toast confirmation = Toast.makeText(MainActivity.this, "Please Tick the Checkbox to Proceed", Toast.LENGTH_SHORT);
                    confirmation.show();
                } else {
                    Toast confirmed = Toast.makeText(MainActivity.this, "Reservation Created (see below)", Toast.LENGTH_SHORT);
                    confirmed.show();

                    finalName.setText("Name: " + nameInput.getText().toString());
                    finalPhone.setText("Phone Number: " + phoneInput.getText().toString());
                    finalSize.setText("Group Size: " + sizeInput.getText().toString());

                    Integer bkDay = datePicker.getDayOfMonth();
                    Integer bkMonth = datePicker.getMonth() + 1;
                    Integer bkYear = datePicker.getYear();

                    finalDate.setText("Date: " + bkDay + "/" + bkMonth + "/" + bkYear);

                    Integer bkHour = timePicker.getCurrentHour();
                    Integer bkMinute = timePicker.getCurrentMinute();

                    if (bkHour < 10 && bkMinute < 10) {
                        finalTime.setText("Time (24h): 0" + bkHour + ":0" + bkMinute);
                    } else if (bkHour < 10) {
                        finalTime.setText("Time (24h): 0" + bkHour + ":" + bkMinute);
                    } else if (bkMinute < 10) {
                        finalTime.setText("Time (24h): " + bkHour + ":0" + bkMinute);
                    } else {
                        finalTime.setText("Time (24h): " + bkHour + ":" + bkMinute);
                    }

                    int checkedRadioId = rgSmoking.getCheckedRadioButtonId();

                    String area;
                    if (checkedRadioId == R.id.radioButtonSmoking) {
                        area = "Smoking Area";
                    } else {
                        area = "Non-Smoking Area";
                    }

                    finalArea.setText("Reservation Table: " + area);
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameInput.setText(null);
                phoneInput.setText(null);
                sizeInput.setText(null);

                datePicker.updateDate(2020, (1-1), 1);
                timePicker.setCurrentHour(19);
                timePicker.setCurrentMinute(30);

                rgSmoking.clearCheck();

                cbConfirm.setChecked(false);
            }
        });
    }
}