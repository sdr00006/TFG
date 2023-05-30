package com.example.sdr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Alarma extends AppCompatActivity {

    private DatabaseReference base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);

        base = FirebaseDatabase.getInstance().getReference();


        TextView aviso = findViewById(R.id.aviso);
        Button conectar = findViewById(R.id.conectar);
        Button desconectar = findViewById(R.id.desconectar);
        ImageView alarma = findViewById(R.id.alarma);
        ImageView alarmacon = findViewById(R.id.alarmacon);
        ImageView alarmades = findViewById(R.id.alarmades);
        TextView ualarma1 = findViewById(R.id.ualarma1);
        TextView ualarma2 = findViewById(R.id.ualarma2);
        TextView ualarma3 = findViewById(R.id.ualarma3);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        View fondoaviso = findViewById(R.id.constraintLayout3);


        /*base.child("alarmaconectada").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean a = (Boolean) dataSnapshot.getValue();

                if (a == false) {
                    aviso.setText("Alarma desconectada");
                    alarmades.setVisibility(View.VISIBLE);
                    alarmacon.setVisibility(View.INVISIBLE);
                    alarma.setVisibility(View.INVISIBLE);
                    fondoaviso.setBackgroundColor(Color.GRAY);
                    conectar.setOnClickListener(view -> {
                        base.child("alarmaconectada").setValue(true);
                        alarmades.setVisibility(View.INVISIBLE);
                        alarmacon.setVisibility(View.VISIBLE);
                        alarma.setVisibility(View.INVISIBLE);
                    });
                }else{
                    aviso.setText("Alarma conectada");
                    alarmades.setVisibility(View.INVISIBLE);
                    alarmacon.setVisibility(View.VISIBLE);
                    alarma.setVisibility(View.INVISIBLE);
                    fondoaviso.setBackgroundColor(Color.GREEN);
                    desconectar.setOnClickListener(view -> {
                        base.child("alarmaconectada").setValue(false);
                        base.child("alarma1").setValue(false);
                        base.child("alarma2").setValue(false);
                        alarmades.setVisibility(View.VISIBLE);
                        alarmacon.setVisibility(View.INVISIBLE);
                        alarma.setVisibility(View.INVISIBLE);
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });*/

        base.child("alarmaconectada").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean a = (Boolean) dataSnapshot.getValue();

                base.child("alarma1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Boolean a1 = (Boolean) dataSnapshot.getValue();

                        base.child("alarma2").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Boolean a2 = (Boolean) dataSnapshot.getValue();

                                String ua1 = (String) ualarma1.getText();
                                String ua2 = (String) ualarma2.getText();

                                if (a == true) {
                                    if(a1==false && a2==false) {
                                        aviso.setText("Alarma conectada");
                                        alarmades.setVisibility(View.INVISIBLE);
                                        alarmacon.setVisibility(View.VISIBLE);
                                        alarma.setVisibility(View.INVISIBLE);
                                        fondoaviso.setBackgroundColor(Color.GREEN);
                                    }
                                    if(a1==true && a2==true){
                                        aviso.setText("Todas las alarmas están sonando");
                                        alarmades.setVisibility(View.INVISIBLE);
                                        alarmacon.setVisibility(View.INVISIBLE);
                                        alarma.setVisibility(View.VISIBLE);
                                        fondoaviso.setBackgroundColor(Color.RED);
                                        createNotification("ALARMA","Están sonando todas las alarmas");
                                        String fecha = df.format(Calendar.getInstance().getTime());
                                        base.child("ultimaalarma3").setValue(ua2);
                                        base.child("ultimaalarma2").setValue(ua1);
                                        base.child("ultimaalarma1").setValue(fecha+" - TODAS");
                                    }
                                    if(a1==true && a2==false){
                                        aviso.setText("La alarma del salón está sonando");
                                        alarmades.setVisibility(View.INVISIBLE);
                                        alarmacon.setVisibility(View.INVISIBLE);
                                        alarma.setVisibility(View.VISIBLE);
                                        fondoaviso.setBackgroundColor(Color.RED);
                                        createNotification("ALARMA","La alarma del salón está sonando");
                                        String fecha = df.format(Calendar.getInstance().getTime());
                                        base.child("ultimaalarma3").setValue(ua2);
                                        base.child("ultimaalarma2").setValue(ua1);
                                        base.child("ultimaalarma1").setValue(fecha+" - SALÓN");
                                    }
                                    if(a1==false && a2==true){
                                        aviso.setText("La alarma del dormitorio está sonando");
                                        alarmades.setVisibility(View.INVISIBLE);
                                        alarmacon.setVisibility(View.INVISIBLE);
                                        alarma.setVisibility(View.VISIBLE);
                                        fondoaviso.setBackgroundColor(Color.RED);
                                        createNotification("ALARMA","La alarma del dormitorio está sonando");
                                        String fecha = df.format(Calendar.getInstance().getTime());
                                        base.child("ultimaalarma3").setValue(ua2);
                                        base.child("ultimaalarma2").setValue(ua1);
                                        base.child("ultimaalarma1").setValue(fecha+" - HABITACIÓN");
                                    }
                                    desconectar.setOnClickListener(view -> {
                                        base.child("alarmaconectada").setValue(false);
                                        base.child("alarma1").setValue(false);
                                        base.child("alarma2").setValue(false);
                                        alarmades.setVisibility(View.VISIBLE);
                                        alarmacon.setVisibility(View.INVISIBLE);
                                        alarma.setVisibility(View.INVISIBLE);
                                    });

                                }else{
                                    aviso.setText("Alarma desconectada");
                                    alarmades.setVisibility(View.VISIBLE);
                                    alarmacon.setVisibility(View.INVISIBLE);
                                    alarma.setVisibility(View.INVISIBLE);
                                    fondoaviso.setBackgroundColor(Color.GRAY);
                                    conectar.setOnClickListener(view -> {
                                        base.child("alarmaconectada").setValue(true);
                                        alarmades.setVisibility(View.INVISIBLE);
                                        alarmacon.setVisibility(View.VISIBLE);
                                        alarma.setVisibility(View.INVISIBLE);
                                    });
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                            }
                        });


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        /*conectar.setOnClickListener(view -> {
            base.child("alarmaconectada").setValue(true);
            alarmades.setVisibility(View.INVISIBLE);
            alarmacon.setVisibility(View.VISIBLE);
            alarma.setVisibility(View.INVISIBLE);
        });

        desconectar.setOnClickListener(view -> {
            base.child("alarmaconectada").setValue(false);
            base.child("alarma1").setValue(false);
            base.child("alarma2").setValue(false);
            alarmades.setVisibility(View.VISIBLE);
            alarmacon.setVisibility(View.INVISIBLE);
            alarma.setVisibility(View.INVISIBLE);
        });*/

        base.child("ultimaalarma1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String u1 = dataSnapshot.getValue().toString();
                ualarma1.setText(u1);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("ultimaalarma2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String u2 = dataSnapshot.getValue().toString();
                ualarma2.setText(u2);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("ultimaalarma3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String u3 = dataSnapshot.getValue().toString();
                ualarma3.setText(u3);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });



        Button botonresumen = findViewById(R.id.botonresumen);
        botonresumen.setOnClickListener(view -> {
            Intent i = new Intent(Alarma.this, MainActivity.class);
            startActivity(i);
        });
        Button botonsala = findViewById(R.id.botonsala);
        botonsala.setOnClickListener(view -> {
            Intent i = new Intent(Alarma.this, Salon.class);
            startActivity(i);
        });
        Button botongeneral = findViewById(R.id.botongeneral);
        botongeneral.setOnClickListener(view -> {
            Intent i = new Intent(Alarma.this, General.class);
            startActivity(i);
        });


    }

    private void createNotification(String title, String content) {

        String channedId = "ALARMA";
        int priorty = NotificationCompat.PRIORITY_HIGH;
        int notificationID = 100;

        Intent intent = new Intent(this, Alarma.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.setAction(Intent.ACTION_VIEW);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), notificationID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channedId)
                .setSmallIcon(R.drawable.ic_notifications_active)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setLights(Color.BLUE, 500, 500)
                .setVibrate(new long[]{500, 500, 500})
                .setPriority(priorty)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);


        // Since android Oreo notification channel is needed.
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Alarma.this);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channedId, channedId, NotificationManager.IMPORTANCE_HIGH);
            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(channel);
        }
        Notification notification = notificationBuilder.build();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(notificationID, notification);

        playNotificationSound();
    }

    public void playNotificationSound() {
        try {
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(Alarma.this, defaultSoundUri);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}