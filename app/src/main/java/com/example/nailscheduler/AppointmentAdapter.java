package com.example.nailscheduler;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.example.nailscheduler.enums.AppointmentStatus;
import com.example.nailscheduler.models.Appointment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AppointmentAdapter extends ArrayAdapter<Appointment> {
    private Context context;
    private ArrayList<Appointment> appointments ;

    public AppointmentAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull ArrayList<Appointment> appointments) {
        super(context, resource, textViewResourceId, appointments);
        this.context=context;
        this.appointments=appointments;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.appointment_item, parent, false);
        }
        // Get the {@link Appointment} object located at this position in the list
        Appointment currentAppointment = getItem(position);
        if(currentAppointment!=null) {
            TextView clientNameTextView = (TextView) listItemView.findViewById(R.id.client_name);
            clientNameTextView.setText(currentAppointment.getClientName());

            TextView appointmentDateTextView = (TextView) listItemView.findViewById(R.id.appointment_date);
            appointmentDateTextView.setText(currentAppointment.getDate());

            TextView appointmentStartTimeTextView = (TextView) listItemView.findViewById(R.id.appointment_start_time);
            appointmentStartTimeTextView.setText(String.valueOf(currentAppointment.getStartTime()));


            TextView appointmentEndTimeTextView = (TextView) listItemView.findViewById(R.id.appointment_end_time);
            appointmentEndTimeTextView.setText(String.valueOf(currentAppointment.getEndTime()));

            Button appointmentStatus = (Button) listItemView.findViewById(R.id.appointment_status);
            AppointmentStatus status = currentAppointment.getStatus();
            switch (status) {

                case NEW_REQUEST: //0
                    appointmentStatus.setText("בקשה לתור");
                    appointmentStatus.setBackgroundColor(Color.WHITE);
                    break;
                case APPROVED: //1
                    appointmentStatus.setText("התור אושר");
                    break;
                case CANCELED: //2
                    appointmentStatus.setText("התור בוטל");
                    break;

                default:
                    break;

            }
            appointmentStatus.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    if (appointmentStatus.getText().equals("בקשה לתור")) {
                        appointmentStatus.setText("התור אושר");
                        appointmentStatus.setBackgroundColor(Color.TRANSPARENT);
                        appointments.get(position).setStatus(AppointmentStatus.APPROVED);
                    }
                }
            });
            listItemView.setClickable(true);
        }
        return listItemView;
    }


}
