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

public class Cocina extends AppCompatActivity {

    private DatabaseReference base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocina);

        base = FirebaseDatabase.getInstance().getReference();

        TextView tempcocina = findViewById(R.id.tempcocina);
        TextView humcocina = findViewById(R.id.humcocina);
        TextView tempdcocina = findViewById(R.id.tempdcocina);
        Button botonmas= (Button)findViewById(R.id.botonmas);
        Button botonborrar= (Button)findViewById(R.id.botonborrar);
        Button botonmenos= (Button)findViewById(R.id.botonmenos);
        Button botoncalefaccion= (Button)findViewById(R.id.botoncalefaccion);
        Button botonaire= (Button)findViewById(R.id.botonaire);
        Button botonluz= (Button)findViewById(R.id.botonluz);
        Button botonhorno= (Button)findViewById(R.id.botonhorno);
        ImageView calefaccionon = (ImageView) findViewById(R.id.calefaccionon);
        ImageView calefaccionoff = (ImageView) findViewById(R.id.calefaccionoff);
        ImageView aireon = (ImageView) findViewById(R.id.aireon);
        ImageView aireoff = (ImageView) findViewById(R.id.aireoff);
        ImageView luzon = (ImageView) findViewById(R.id.luzon);
        ImageView luzoff = (ImageView) findViewById(R.id.luzoff);
        ImageView hornoon = (ImageView) findViewById(R.id.hornoon);
        ImageView hornooff = (ImageView) findViewById(R.id.hornooff);

        double cero = 0.0000000001;


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

        base.child("temp_d_cocina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double temp = (Double) dataSnapshot.getValue();
                if(temp != cero){
                    double temp2 = (temp - cero);
                    String t = String.valueOf((double) temp2);
                    tempdcocina.setText(t);
                    botonmenos.setOnClickListener(view -> {
                        double temp3 = (temp - 0.5);
                        base.child("temp_d_cocina").setValue(temp3);
                    });
                    botonborrar.setOnClickListener(view -> {
                        base.child("temp_d_cocina").setValue(cero);
                        base.child("calefaccion_cocina").setValue(false);
                        base.child("aire_cocina").setValue(false);
                    });
                    botonmas.setOnClickListener(view -> {
                        double temp3 = (temp + 0.5);
                        base.child("temp_d_cocina").setValue(temp3);
                    });

                    base.child("temp_cocina").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Double te = (Double) dataSnapshot.getValue();
                            tempcocina.setText(te.toString());
                            if (te > (temp+0.5)) {
                                base.child("calefaccion_cocina").setValue(false);
                                base.child("aire_cocina").setValue(true);
                            }
                            else if (te < (temp-0.5)) {
                                base.child("calefaccion_cocina").setValue(true);
                                base.child("aire_cocina").setValue(false);
                            }
                            else {
                                base.child("calefaccion_cocina").setValue(false);
                                base.child("aire_cocina").setValue(false);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                        }
                    });


                }else{
                    tempdcocina.setText("N/A");
                    base.child("temp_cocina").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Double te = (Double) dataSnapshot.getValue();

                            botonmenos.setOnClickListener(view -> {
                                double tem=Math.floor(te)+cero;
                                if(tem>(te-0.5)){
                                    base.child("temp_d_cocina").setValue(tem);
                                }
                                else{
                                    base.child("temp_d_cocina").setValue(tem+0.5);
                                }
                            });
                            botonmas.setOnClickListener(view -> {
                                double tem=Math.ceil(te)+cero;
                                if(tem<(te+0.5)){
                                    base.child("temp_d_cocina").setValue(tem);
                                }
                                else{
                                    base.child("temp_d_cocina").setValue(tem-0.5);
                                }
                            });
                            botonborrar.setOnClickListener(view -> {
                                base.child("temp_d_cocina").setValue(cero);
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

        base.child("luz_cocina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean luz = (Boolean) dataSnapshot.getValue();
                if (luz == false) {
                    botonluz.setText("ENCENDER");
                    luzoff.setVisibility(View.VISIBLE);
                    luzon.setVisibility(View.INVISIBLE);
                    botonluz.setOnClickListener(view -> {
                        base.child("luz_cocina").setValue(true);
                    });
                    luzoff.setOnClickListener(view -> {
                        base.child("luz_cocina").setValue(true);
                    });
                }else{
                    botonluz.setText("APAGAR");
                    luzoff.setVisibility(View.INVISIBLE);
                    luzon.setVisibility(View.VISIBLE);
                    botonluz.setOnClickListener(view -> {
                        base.child("luz_cocina").setValue(false);
                    });
                    luzon.setOnClickListener(view -> {
                        base.child("luz_cocina").setValue(false);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("calefaccion_cocina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean cal = (Boolean) dataSnapshot.getValue();
                if (cal == false) {
                    botoncalefaccion.setText("ENCENDER");
                    calefaccionoff.setVisibility(View.VISIBLE);
                    calefaccionon.setVisibility(View.INVISIBLE);
                    botoncalefaccion.setOnClickListener(view -> {
                        base.child("calefaccion_cocina").setValue(true);
                        base.child("aire_cocina").setValue(false);
                        base.child("temp_d_cocina").setValue(cero);

                    });
                    calefaccionoff.setOnClickListener(view -> {
                        base.child("calefaccion_cocina").setValue(true);
                        base.child("aire_cocina").setValue(false);
                        base.child("temp_d_cocina").setValue(cero);
                    });
                }else{
                    botoncalefaccion.setText("APAGAR");
                    calefaccionoff.setVisibility(View.INVISIBLE);
                    calefaccionon.setVisibility(View.VISIBLE);
                    botoncalefaccion.setOnClickListener(view -> {
                        base.child("calefaccion_cocina").setValue(false);
                        base.child("temp_d_cocina").setValue(cero);
                    });
                    calefaccionon.setOnClickListener(view -> {
                        base.child("calefaccion_cocina").setValue(false);
                        base.child("temp_d_cocina").setValue(cero);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("aire_cocina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean air = (Boolean) dataSnapshot.getValue();
                if (air == false) {
                    botonaire.setText("ENCENDER");
                    aireoff.setVisibility(View.VISIBLE);
                    aireon.setVisibility(View.INVISIBLE);
                    botonaire.setOnClickListener(view -> {
                        base.child("aire_cocina").setValue(true);
                        base.child("calefaccion_cocina").setValue(false);
                        base.child("temp_d_cocina").setValue(cero);
                    });
                    aireoff.setOnClickListener(view -> {
                        base.child("aire_cocina").setValue(true);
                        base.child("calefaccion_cocina").setValue(false);
                        base.child("temp_d_cocina").setValue(cero);
                    });
                }else{
                    botonaire.setText("APAGAR");
                    aireoff.setVisibility(View.INVISIBLE);
                    aireon.setVisibility(View.VISIBLE);
                    botonaire.setOnClickListener(view -> {
                        base.child("aire_cocina").setValue(false);
                        base.child("temp_d_cocina").setValue(cero);
                    });
                    aireon.setOnClickListener(view -> {
                        base.child("aire_cocina").setValue(false);
                        base.child("temp_d_cocina").setValue(cero);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("horno").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean t = (Boolean) dataSnapshot.getValue();
                if (t == false) {
                    botonhorno.setText("ENCENDER");
                    hornooff.setVisibility(View.VISIBLE);
                    hornoon.setVisibility(View.INVISIBLE);
                    botonhorno.setOnClickListener(view -> {
                        base.child("horno").setValue(true);
                    });
                    hornooff.setOnClickListener(view -> {
                        base.child("horno").setValue(true);
                    });
                }else{
                    botonhorno.setText("APAGAR");
                    hornooff.setVisibility(View.INVISIBLE);
                    hornoon.setVisibility(View.VISIBLE);
                    botonhorno.setOnClickListener(view -> {
                        base.child("horno").setValue(false);
                    });
                    hornoon.setOnClickListener(view -> {
                        base.child("horno").setValue(false);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });








        Button botonresumen=findViewById(R.id.botonresumen);
        botonresumen.setOnClickListener(view -> {
            Intent i = new Intent(Cocina.this,MainActivity.class);
            startActivity(i);
        });
        Button botongeneral=findViewById(R.id.botongeneral);
        botongeneral.setOnClickListener(view -> {
            Intent i = new Intent(Cocina.this,General.class);
            startActivity(i);
        });
        Button botonalarma=findViewById(R.id.botonalarma);
        botonalarma.setOnClickListener(view -> {
            Intent i = new Intent(Cocina.this,Alarma.class);
            startActivity(i);
        });
        Button botonsalon=findViewById(R.id.botonsalon);
        botonsalon.setOnClickListener(view -> {
            Intent i = new Intent(Cocina.this,Salon.class);
            startActivity(i);
        });
        Button botonbano=findViewById(R.id.botonbano);
        botonbano.setOnClickListener(view -> {
            Intent i = new Intent(Cocina.this,Bano.class);
            startActivity(i);
        });
        Button botonhab1=findViewById(R.id.botonhab1);
        botonhab1.setOnClickListener(view -> {
            Intent i = new Intent(Cocina.this,Habitacion1.class);
            startActivity(i);
        });
        Button botonhab2=findViewById(R.id.botonhab2);
        botonhab2.setOnClickListener(view -> {
            Intent i = new Intent(Cocina.this,Habitacion2.class);
            startActivity(i);
        });

    }
}