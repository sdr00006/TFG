package com.example.sdr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Salon extends AppCompatActivity {

    private DatabaseReference base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon);

        base = FirebaseDatabase.getInstance().getReference();

        TextView tempsalon = findViewById(R.id.tempsalon);
        TextView humsalon = findViewById(R.id.humsalon);
        TextView tempdsalon = findViewById(R.id.tempdsalon);
        Button botonmas= (Button)findViewById(R.id.botonmas);
        Button botonborrar= (Button)findViewById(R.id.botonborrar);
        Button botonmenos= (Button)findViewById(R.id.botonmenos);
        Button botoncalefaccion= (Button)findViewById(R.id.botoncalefaccion);
        Button botonaire= (Button)findViewById(R.id.botonaire);
        Button botonluz= (Button)findViewById(R.id.botonluz);
        Button botontv= (Button)findViewById(R.id.botontv);
        ImageView calefaccionon = (ImageView) findViewById(R.id.calefaccionon);
        ImageView calefaccionoff = (ImageView) findViewById(R.id.calefaccionoff);
        ImageView aireon = (ImageView) findViewById(R.id.aireon);
        ImageView aireoff = (ImageView) findViewById(R.id.aireoff);
        ImageView luzon = (ImageView) findViewById(R.id.luzon);
        ImageView luzoff = (ImageView) findViewById(R.id.luzoff);
        ImageView tvon = (ImageView) findViewById(R.id.tvon);
        ImageView tvoff = (ImageView) findViewById(R.id.tvoff);


        double cero = 0.0000000001;


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

        base.child("temp_d_salon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double temp = (Double) dataSnapshot.getValue();
                if(temp != cero){
                    double temp2 = (temp - cero);
                    String t = String.valueOf((double) temp2);
                    tempdsalon.setText(t);
                    botonmenos.setOnClickListener(view -> {
                        double temp3 = (temp - 0.5);
                        base.child("temp_d_salon").setValue(temp3);
                    });
                    botonborrar.setOnClickListener(view -> {
                        base.child("temp_d_salon").setValue(cero);
                        base.child("calefaccion_salon").setValue(false);
                        base.child("aire_salon").setValue(false);
                    });
                    botonmas.setOnClickListener(view -> {
                        double temp3 = (temp + 0.5);
                        base.child("temp_d_salon").setValue(temp3);
                    });

                    base.child("temp_salon").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Double te = (Double) dataSnapshot.getValue();
                            tempsalon.setText(te.toString());
                            if (te > (temp+0.5)) {
                                base.child("calefaccion_salon").setValue(false);
                                base.child("aire_salon").setValue(true);
                            }
                            else if (te < (temp-0.5)) {
                                base.child("calefaccion_salon").setValue(true);
                                base.child("aire_salon").setValue(false);
                            }
                            else {
                                base.child("calefaccion_salon").setValue(false);
                                base.child("aire_salon").setValue(false);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                        }
                    });


                }else{
                    tempdsalon.setText("N/A");
                    base.child("temp_salon").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Double te = (Double) dataSnapshot.getValue();

                            botonmenos.setOnClickListener(view -> {
                                double tem=Math.floor(te)+cero;
                                if(tem>(te-0.5)){
                                    base.child("temp_d_salon").setValue(tem);
                                }
                                else{
                                    base.child("temp_d_salon").setValue(tem+0.5);
                                }
                            });
                            botonmas.setOnClickListener(view -> {
                                double tem=Math.ceil(te)+cero;
                                if(tem<(te+0.5)){
                                    base.child("temp_d_salon").setValue(tem);
                                }
                                else{
                                    base.child("temp_d_salon").setValue(tem-0.5);
                                }
                            });
                            botonborrar.setOnClickListener(view -> {
                                base.child("temp_d_salon").setValue(cero);
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

        base.child("luz_salon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean luz = (Boolean) dataSnapshot.getValue();
                if (luz == false) {
                    botonluz.setText("ENCENDER");
                    luzoff.setVisibility(View.VISIBLE);
                    luzon.setVisibility(View.INVISIBLE);
                    botonluz.setOnClickListener(view -> {
                        base.child("luz_salon").setValue(true);
                    });
                    luzoff.setOnClickListener(view -> {
                        base.child("luz_salon").setValue(true);
                    });
                }else{
                    botonluz.setText("APAGAR");
                    luzoff.setVisibility(View.INVISIBLE);
                    luzon.setVisibility(View.VISIBLE);
                    botonluz.setOnClickListener(view -> {
                        base.child("luz_salon").setValue(false);
                    });
                    luzon.setOnClickListener(view -> {
                        base.child("luz_salon").setValue(false);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("calefaccion_salon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean cal = (Boolean) dataSnapshot.getValue();
                if (cal == false) {
                    botoncalefaccion.setText("ENCENDER");
                    calefaccionoff.setVisibility(View.VISIBLE);
                    calefaccionon.setVisibility(View.INVISIBLE);
                    botoncalefaccion.setOnClickListener(view -> {
                        base.child("calefaccion_salon").setValue(true);
                        base.child("aire_salon").setValue(false);
                        base.child("temp_d_salon").setValue(cero);

                    });
                    calefaccionoff.setOnClickListener(view -> {
                        base.child("calefaccion_salon").setValue(true);
                        base.child("aire_salon").setValue(false);
                        base.child("temp_d_salon").setValue(cero);
                    });
                }else{
                    botoncalefaccion.setText("APAGAR");
                    calefaccionoff.setVisibility(View.INVISIBLE);
                    calefaccionon.setVisibility(View.VISIBLE);
                    botoncalefaccion.setOnClickListener(view -> {
                        base.child("calefaccion_salon").setValue(false);
                        base.child("temp_d_salon").setValue(cero);
                    });
                    calefaccionon.setOnClickListener(view -> {
                        base.child("calefaccion_salon").setValue(false);
                        base.child("temp_d_salon").setValue(cero);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("aire_salon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean air = (Boolean) dataSnapshot.getValue();
                if (air == false) {
                    botonaire.setText("ENCENDER");
                    aireoff.setVisibility(View.VISIBLE);
                    aireon.setVisibility(View.INVISIBLE);
                    botonaire.setOnClickListener(view -> {
                        base.child("aire_salon").setValue(true);
                        base.child("calefaccion_salon").setValue(false);
                        base.child("temp_d_salon").setValue(cero);
                    });
                    aireoff.setOnClickListener(view -> {
                        base.child("aire_salon").setValue(true);
                        base.child("calefaccion_salon").setValue(false);
                        base.child("temp_d_salon").setValue(cero);
                    });
                }else{
                    botonaire.setText("APAGAR");
                    aireoff.setVisibility(View.INVISIBLE);
                    aireon.setVisibility(View.VISIBLE);
                    botonaire.setOnClickListener(view -> {
                        base.child("aire_salon").setValue(false);
                        base.child("temp_d_salon").setValue(cero);
                    });
                    aireon.setOnClickListener(view -> {
                        base.child("aire_salon").setValue(false);
                        base.child("temp_d_salon").setValue(cero);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("tv").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean t = (Boolean) dataSnapshot.getValue();
                if (t == false) {
                    botontv.setText("ENCENDER");
                    tvoff.setVisibility(View.VISIBLE);
                    tvon.setVisibility(View.INVISIBLE);
                    botontv.setOnClickListener(view -> {
                        base.child("tv").setValue(true);
                    });
                    tvoff.setOnClickListener(view -> {
                        base.child("tv").setValue(true);
                    });
                }else{
                    botontv.setText("APAGAR");
                    tvoff.setVisibility(View.INVISIBLE);
                    tvon.setVisibility(View.VISIBLE);
                    botontv.setOnClickListener(view -> {
                        base.child("tv").setValue(false);
                    });
                    tvon.setOnClickListener(view -> {
                        base.child("tv").setValue(false);
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });








        Button botonresumen=findViewById(R.id.botonresumen);
        botonresumen.setOnClickListener(view -> {
            Intent i = new Intent(Salon.this,MainActivity.class);
            startActivity(i);
        });
        Button botongeneral=findViewById(R.id.botongeneral);
        botongeneral.setOnClickListener(view -> {
            Intent i = new Intent(Salon.this,General.class);
            startActivity(i);
        });
        Button botonalarma=findViewById(R.id.botonalarma);
        botonalarma.setOnClickListener(view -> {
            Intent i = new Intent(Salon.this,Alarma.class);
            startActivity(i);
        });
        Button botoncocina=findViewById(R.id.botoncocina);
        botoncocina.setOnClickListener(view -> {
            Intent i = new Intent(Salon.this,Cocina.class);
            startActivity(i);
        });
        Button botonbano=findViewById(R.id.botonbano);
        botonbano.setOnClickListener(view -> {
            Intent i = new Intent(Salon.this,Bano.class);
            startActivity(i);
        });
        Button botonhab1=findViewById(R.id.botonhab1);
        botonhab1.setOnClickListener(view -> {
            Intent i = new Intent(Salon.this,Habitacion1.class);
            startActivity(i);
        });
        Button botonhab2=findViewById(R.id.botonhab2);
        botonhab2.setOnClickListener(view -> {
            Intent i = new Intent(Salon.this,Habitacion2.class);
            startActivity(i);
        });



    }
}