package com.example.sdr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Bano extends AppCompatActivity {

    private DatabaseReference base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bano);

        base = FirebaseDatabase.getInstance().getReference();

        TextView tempbano = findViewById(R.id.tempbano);
        TextView humbano = findViewById(R.id.humbano);
        TextView tempdbano = findViewById(R.id.tempdbano);
        Button botonmas= (Button)findViewById(R.id.botonmas);
        Button botonborrar= (Button)findViewById(R.id.botonborrar);
        Button botonmenos= (Button)findViewById(R.id.botonmenos);
        Button botoncalefaccion= (Button)findViewById(R.id.botoncalefaccion);
        Button botonaire= (Button)findViewById(R.id.botonaire);
        Button botonluz= (Button)findViewById(R.id.botonluz);
        Button botonextractor= (Button)findViewById(R.id.botonextractor);
        ImageView calefaccionon = (ImageView) findViewById(R.id.calefaccionon);
        ImageView calefaccionoff = (ImageView) findViewById(R.id.calefaccionoff);
        ImageView aireon = (ImageView) findViewById(R.id.aireon);
        ImageView aireoff = (ImageView) findViewById(R.id.aireoff);
        ImageView luzon = (ImageView) findViewById(R.id.luzon);
        ImageView luzoff = (ImageView) findViewById(R.id.luzoff);
        ImageView extractoron = (ImageView) findViewById(R.id.extractoron);
        ImageView extractoroff = (ImageView) findViewById(R.id.extractoroff);

        double cero = 0.0000000001;


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

        base.child("temp_d_bano").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double temp = (Double) dataSnapshot.getValue();
                if(temp != cero){
                    double temp2 = (temp - cero);
                    String t = String.valueOf((double) temp2);
                    tempdbano.setText(t);
                    botonmenos.setOnClickListener(view -> {
                        double temp3 = (temp - 0.5);
                        base.child("temp_d_bano").setValue(temp3);
                    });
                    botonborrar.setOnClickListener(view -> {
                        base.child("temp_d_bano").setValue(cero);
                        base.child("calefaccion_bano").setValue(false);
                        base.child("aire_bano").setValue(false);
                    });
                    botonmas.setOnClickListener(view -> {
                        double temp3 = (temp + 0.5);
                        base.child("temp_d_bano").setValue(temp3);
                    });

                    base.child("temp_bano").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Double te = (Double) dataSnapshot.getValue();
                            tempbano.setText(te.toString());
                            if (te > (temp+0.5)) {
                                base.child("calefaccion_bano").setValue(false);
                                base.child("aire_bano").setValue(true);
                            }
                            else if (te < (temp-0.5)) {
                                base.child("calefaccion_bano").setValue(true);
                                base.child("aire_bano").setValue(false);
                            }
                            else {
                                base.child("calefaccion_bano").setValue(false);
                                base.child("aire_bano").setValue(false);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                        }
                    });


                }else{
                    tempdbano.setText("N/A");
                    base.child("temp_bano").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Double te = (Double) dataSnapshot.getValue();

                            botonmenos.setOnClickListener(view -> {
                                double tem=Math.floor(te)+cero;
                                if(tem>(te-0.5)){
                                    base.child("temp_d_bano").setValue(tem);
                                }
                                else{
                                    base.child("temp_d_bano").setValue(tem+0.5);
                                }
                            });
                            botonmas.setOnClickListener(view -> {
                                double tem=Math.ceil(te)+cero;
                                if(tem<(te+0.5)){
                                    base.child("temp_d_bano").setValue(tem);
                                }
                                else{
                                    base.child("temp_d_bano").setValue(tem-0.5);
                                }
                            });
                            botonborrar.setOnClickListener(view -> {
                                base.child("temp_d_bano").setValue(cero);
                            });

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                        }
                    });
                }


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
                    botonluz.setText("ENCENDER");
                    luzoff.setVisibility(View.VISIBLE);
                    luzon.setVisibility(View.INVISIBLE);
                    botonluz.setOnClickListener(view -> {
                        base.child("luz_bano").setValue(true);
                    });
                    luzoff.setOnClickListener(view -> {
                        base.child("luz_bano").setValue(true);
                    });
                }else{
                    botonluz.setText("APAGAR");
                    luzoff.setVisibility(View.INVISIBLE);
                    luzon.setVisibility(View.VISIBLE);
                    botonluz.setOnClickListener(view -> {
                        base.child("luz_bano").setValue(false);
                    });
                    luzon.setOnClickListener(view -> {
                        base.child("luz_bano").setValue(false);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("calefaccion_bano").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean cal = (Boolean) dataSnapshot.getValue();
                if (cal == false) {
                    botoncalefaccion.setText("ENCENDER");
                    calefaccionoff.setVisibility(View.VISIBLE);
                    calefaccionon.setVisibility(View.INVISIBLE);
                    botoncalefaccion.setOnClickListener(view -> {
                        base.child("calefaccion_bano").setValue(true);
                        base.child("aire_bano").setValue(false);
                        base.child("temp_d_bano").setValue(cero);

                    });
                    calefaccionoff.setOnClickListener(view -> {
                        base.child("calefaccion_bano").setValue(true);
                        base.child("aire_bano").setValue(false);
                        base.child("temp_d_bano").setValue(cero);
                    });
                }else{
                    botoncalefaccion.setText("APAGAR");
                    calefaccionoff.setVisibility(View.INVISIBLE);
                    calefaccionon.setVisibility(View.VISIBLE);
                    botoncalefaccion.setOnClickListener(view -> {
                        base.child("calefaccion_bano").setValue(false);
                        base.child("temp_d_bano").setValue(cero);
                    });
                    calefaccionon.setOnClickListener(view -> {
                        base.child("calefaccion_bano").setValue(false);
                        base.child("temp_d_bano").setValue(cero);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("aire_bano").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean air = (Boolean) dataSnapshot.getValue();
                if (air == false) {
                    botonaire.setText("ENCENDER");
                    aireoff.setVisibility(View.VISIBLE);
                    aireon.setVisibility(View.INVISIBLE);
                    botonaire.setOnClickListener(view -> {
                        base.child("aire_bano").setValue(true);
                        base.child("calefaccion_bano").setValue(false);
                        base.child("temp_d_bano").setValue(cero);
                    });
                    aireoff.setOnClickListener(view -> {
                        base.child("aire_bano").setValue(true);
                        base.child("calefaccion_bano").setValue(false);
                        base.child("temp_d_bano").setValue(cero);
                    });
                }else{
                    botonaire.setText("APAGAR");
                    aireoff.setVisibility(View.INVISIBLE);
                    aireon.setVisibility(View.VISIBLE);
                    botonaire.setOnClickListener(view -> {
                        base.child("aire_bano").setValue(false);
                        base.child("temp_d_bano").setValue(cero);
                    });
                    aireon.setOnClickListener(view -> {
                        base.child("aire_bano").setValue(false);
                        base.child("temp_d_bano").setValue(cero);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("extractor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean t = (Boolean) dataSnapshot.getValue();
                if (t == false) {
                    botonextractor.setText("ENCENDER");
                    extractoroff.setVisibility(View.VISIBLE);
                    extractoron.setVisibility(View.INVISIBLE);
                    botonextractor.setOnClickListener(view -> {
                        base.child("extractor").setValue(true);
                    });
                    extractoroff.setOnClickListener(view -> {
                        base.child("extractor").setValue(true);
                    });
                }else{
                    botonextractor.setText("APAGAR");
                    extractoroff.setVisibility(View.INVISIBLE);
                    extractoron.setVisibility(View.VISIBLE);
                    botonextractor.setOnClickListener(view -> {
                        base.child("extractor").setValue(false);
                    });
                    extractoron.setOnClickListener(view -> {
                        base.child("extractor").setValue(false);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });



        Button botonresumen=findViewById(R.id.botonresumen);
        botonresumen.setOnClickListener(view -> {
            Intent i = new Intent(Bano.this,MainActivity.class);
            startActivity(i);
        });
        Button botongeneral=findViewById(R.id.botongeneral);
        botongeneral.setOnClickListener(view -> {
            Intent i = new Intent(Bano.this,General.class);
            startActivity(i);
        });
        Button botonalarma=findViewById(R.id.botonalarma);
        botonalarma.setOnClickListener(view -> {
            Intent i = new Intent(Bano.this,Alarma.class);
            startActivity(i);
        });
        Button botonsalon=findViewById(R.id.botonsalon);
        botonsalon.setOnClickListener(view -> {
            Intent i = new Intent(Bano.this,Salon.class);
            startActivity(i);
        });
        Button botoncocina=findViewById(R.id.botoncocina);
        botoncocina.setOnClickListener(view -> {
            Intent i = new Intent(Bano.this,Cocina.class);
            startActivity(i);
        });
        Button botonhab1=findViewById(R.id.botonhab1);
        botonhab1.setOnClickListener(view -> {
            Intent i = new Intent(Bano.this,Habitacion1.class);
            startActivity(i);
        });
        Button botonhab2=findViewById(R.id.botonhab2);
        botonhab2.setOnClickListener(view -> {
            Intent i = new Intent(Bano.this,Habitacion2.class);
            startActivity(i);
        });
    }
}