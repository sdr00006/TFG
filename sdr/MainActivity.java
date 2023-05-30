package com.example.sdr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        base = FirebaseDatabase.getInstance().getReference();

        Button botonluzsalon= (Button)findViewById(R.id.botonluzsalon);
        ImageView bombilla0salon = (ImageView) findViewById(R.id.bombilla0salon);
        ImageView bombilla1salon = (ImageView) findViewById(R.id.bombilla1salon);
        ImageView fondosalon = (ImageView) findViewById(R.id.fondosalon);
        TextView tempsalon = findViewById(R.id.tempsalon);
        TextView humsalon = findViewById(R.id.humsalon);

        Button botonluzcocina= (Button)findViewById(R.id.botonluzcocina);
        ImageView bombilla0cocina = (ImageView) findViewById(R.id.bombilla0cocina);
        ImageView bombilla1cocina = (ImageView) findViewById(R.id.bombilla1cocina);
        ImageView fondococina = (ImageView) findViewById(R.id.fondococina);
        TextView tempcocina = findViewById(R.id.tempcocina);
        TextView humcocina = findViewById(R.id.humcocina);

        Button botonluzbano= (Button)findViewById(R.id.botonluzbano);
        ImageView bombilla0bano = (ImageView) findViewById(R.id.bombilla0bano);
        ImageView bombilla1bano = (ImageView) findViewById(R.id.bombilla1bano);
        ImageView fondobano = (ImageView) findViewById(R.id.fondobano);
        TextView tempbano = findViewById(R.id.tempbano);
        TextView humbano = findViewById(R.id.humbano);

        Button botonluzhab1= (Button)findViewById(R.id.botonluzhab1);
        ImageView bombilla0hab1 = (ImageView) findViewById(R.id.bombilla0hab1);
        ImageView bombilla1hab1 = (ImageView) findViewById(R.id.bombilla1hab1);
        ImageView fondohab1 = (ImageView) findViewById(R.id.fondohab1);
        TextView temphab1 = findViewById(R.id.temphab1);
        TextView humhab1 = findViewById(R.id.humhab1);

        Button botonluzhab2= (Button)findViewById(R.id.botonluzhab2);
        ImageView bombilla0hab2 = (ImageView) findViewById(R.id.bombilla0hab2);
        ImageView bombilla1hab2 = (ImageView) findViewById(R.id.bombilla1hab2);
        ImageView fondohab2 = (ImageView) findViewById(R.id.fondohab2);
        TextView temphab2 = findViewById(R.id.temphab2);
        TextView humhab2 = findViewById(R.id.humhab2);

        ImageView fondojardin = (ImageView) findViewById(R.id.fondojardin);
        TextView tempjardin = findViewById(R.id.tempjardin);
        TextView humjardin = findViewById(R.id.humjardin);

        base.child("luz_salon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean luz = (Boolean) dataSnapshot.getValue();
                if (luz == false) {
                    botonluzsalon.setText("ENCENDER");
                    bombilla0salon.setVisibility(View.VISIBLE);
                    bombilla1salon.setVisibility(View.INVISIBLE);
                    botonluzsalon.setOnClickListener(view -> {
                            base.child("luz_salon").setValue(true);
                    });
                    bombilla0salon.setOnClickListener(view -> {
                        base.child("luz_salon").setValue(true);
                    });
                }else{
                    botonluzsalon.setText("APAGAR");
                    bombilla0salon.setVisibility(View.INVISIBLE);
                    bombilla1salon.setVisibility(View.VISIBLE);
                    botonluzsalon.setOnClickListener(view -> {
                        base.child("luz_salon").setValue(false);
                    });
                    bombilla1salon.setOnClickListener(view -> {
                        base.child("luz_salon").setValue(false);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("temp_salon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double temp = (Double) dataSnapshot.getValue();
                tempsalon.setText(temp.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("hum_salon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String hum = dataSnapshot.getValue().toString();
                humsalon.setText(hum);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("luz_cocina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean luz = (Boolean) dataSnapshot.getValue();
                if (luz == false) {
                    botonluzcocina.setText("ENCENDER");
                    bombilla0cocina.setVisibility(View.VISIBLE);
                    bombilla1cocina.setVisibility(View.INVISIBLE);
                    botonluzcocina.setOnClickListener(view -> {
                        base.child("luz_cocina").setValue(true);
                    });
                    bombilla0cocina.setOnClickListener(view -> {
                        base.child("luz_cocina").setValue(true);
                    });
                }else{
                    botonluzcocina.setText("APAGAR");
                    bombilla0cocina.setVisibility(View.INVISIBLE);
                    bombilla1cocina.setVisibility(View.VISIBLE);
                    botonluzcocina.setOnClickListener(view -> {
                        base.child("luz_cocina").setValue(false);
                    });
                    bombilla1cocina.setOnClickListener(view -> {
                        base.child("luz_cocina").setValue(false);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("temp_cocina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double temp = (Double) dataSnapshot.getValue();
                tempcocina.setText(temp.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("hum_cocina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String hum = dataSnapshot.getValue().toString();
                humcocina.setText(hum);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("luz_bano").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean luz = (Boolean) dataSnapshot.getValue();
                if (luz == false) {
                    botonluzbano.setText("ENCENDER");
                    bombilla0bano.setVisibility(View.VISIBLE);
                    bombilla1bano.setVisibility(View.INVISIBLE);
                    botonluzbano.setOnClickListener(view -> {
                        base.child("luz_bano").setValue(true);
                    });
                    bombilla0bano.setOnClickListener(view -> {
                        base.child("luz_bano").setValue(true);
                    });
                }else{
                    botonluzbano.setText("APAGAR");
                    bombilla0bano.setVisibility(View.INVISIBLE);
                    bombilla1bano.setVisibility(View.VISIBLE);
                    botonluzbano.setOnClickListener(view -> {
                        base.child("luz_bano").setValue(false);
                    });
                    bombilla1bano.setOnClickListener(view -> {
                        base.child("luz_bano").setValue(false);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("temp_bano").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double temp = (Double) dataSnapshot.getValue();
                tempbano.setText(temp.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("hum_bano").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String hum = dataSnapshot.getValue().toString();
                humbano.setText(hum);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("luz_hab1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean luz = (Boolean) dataSnapshot.getValue();
                if (luz == false) {
                    botonluzhab1.setText("ENCENDER");
                    bombilla0hab1.setVisibility(View.VISIBLE);
                    bombilla1hab1.setVisibility(View.INVISIBLE);
                    botonluzhab1.setOnClickListener(view -> {
                        base.child("luz_hab1").setValue(true);
                    });
                    bombilla0hab1.setOnClickListener(view -> {
                        base.child("luz_hab1").setValue(true);
                    });
                }else{
                    botonluzhab1.setText("APAGAR");
                    bombilla0hab1.setVisibility(View.INVISIBLE);
                    bombilla1hab1.setVisibility(View.VISIBLE);
                    botonluzhab1.setOnClickListener(view -> {
                        base.child("luz_hab1").setValue(false);
                    });
                    bombilla1hab1.setOnClickListener(view -> {
                        base.child("luz_hab1").setValue(false);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("temp_hab1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double temp = (Double) dataSnapshot.getValue();
                temphab1.setText(temp.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("hum_hab1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String hum = dataSnapshot.getValue().toString();
                humhab1.setText(hum);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("luz_hab2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean luz = (Boolean) dataSnapshot.getValue();
                if (luz == false) {
                    botonluzhab2.setText("ENCENDER");
                    bombilla0hab2.setVisibility(View.VISIBLE);
                    bombilla1hab2.setVisibility(View.INVISIBLE);
                    botonluzhab2.setOnClickListener(view -> {
                        base.child("luz_hab2").setValue(true);
                    });
                    bombilla0hab2.setOnClickListener(view -> {
                        base.child("luz_hab2").setValue(true);
                    });
                }else{
                    botonluzhab2.setText("APAGAR");
                    bombilla0hab2.setVisibility(View.INVISIBLE);
                    bombilla1hab2.setVisibility(View.VISIBLE);
                    botonluzhab2.setOnClickListener(view -> {
                        base.child("luz_hab2").setValue(false);
                    });
                    bombilla1hab2.setOnClickListener(view -> {
                        base.child("luz_hab2").setValue(false);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("temp_hab2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double temp = (Double) dataSnapshot.getValue();
                temphab2.setText(temp.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("hum_hab2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String hum = dataSnapshot.getValue().toString();
                humhab2.setText(hum);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("temp_jardin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double temp = (Double) dataSnapshot.getValue();
                tempjardin.setText(temp.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("hum_jardin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String hum = dataSnapshot.getValue().toString();
                humjardin.setText(hum);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });


        Button botongeneral=findViewById(R.id.botongeneral);
        botongeneral.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,General.class);
            startActivity(i);
        });

        Button botonsala=findViewById(R.id.botonsala);
        botonsala.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,Salon.class);
            startActivity(i);
        });

        Button botonalarma=findViewById(R.id.botonalarma);
        botonalarma.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,Alarma.class);
            startActivity(i);
        });

        fondosalon.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,Salon.class);
            startActivity(i);
        });

        fondococina.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,Cocina.class);
            startActivity(i);
        });

        fondobano.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,Bano.class);
            startActivity(i);
        });

        fondohab1.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,Habitacion1.class);
            startActivity(i);
        });

        fondohab2.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,Habitacion2.class);
            startActivity(i);
        });


    }
}