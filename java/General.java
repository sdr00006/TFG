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

public class General extends AppCompatActivity {

    private DatabaseReference base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        base = FirebaseDatabase.getInstance().getReference();

        ImageView luz_salon_on = findViewById(R.id.luz_salon_on);
        ImageView luz_salon_off = findViewById(R.id.luz_salon_off);
        ImageView luz_cocina_on = findViewById(R.id.luz_cocina_on);
        ImageView luz_cocina_off = findViewById(R.id.luz_cocina_off);
        ImageView luz_bano_on = findViewById(R.id.luz_bano_on);
        ImageView luz_bano_off = findViewById(R.id.luz_bano_off);
        ImageView luz_hab1_on = findViewById(R.id.luz_hab1_on);
        ImageView luz_hab1_off = findViewById(R.id.luz_hab1_off);
        ImageView luz_hab2_on = findViewById(R.id.luz_hab2_on);
        ImageView luz_hab2_off = findViewById(R.id.luz_hab2_off);
        Button encenderluz = findViewById(R.id.encenderluz);
        Button apagarluz = findViewById(R.id.conectar);
        TextView tempmedia = findViewById(R.id.tempmedia);
        TextView tempdeseada = findViewById(R.id.tempdeseada);
        Button botonmas = findViewById(R.id.botonmas);
        Button botonborrar = findViewById(R.id.botonborrar);
        Button botonmenos = findViewById(R.id.botonmenos);
        ImageView cal_salon_on = findViewById(R.id.cal_salon_on);
        ImageView cal_salon_off = findViewById(R.id.cal_salon_off);
        ImageView cal_cocina_on = findViewById(R.id.cal_cocina_on);
        ImageView cal_cocina_off = findViewById(R.id.cal_cocina_off);
        ImageView cal_bano_on = findViewById(R.id.cal_bano_on);
        ImageView cal_bano_off = findViewById(R.id.cal_bano_off);
        ImageView cal_hab1_on = findViewById(R.id.cal_hab1_on);
        ImageView cal_hab1_off = findViewById(R.id.cal_hab1_off);
        ImageView cal_hab2_on = findViewById(R.id.cal_hab2_on);
        ImageView cal_hab2_off = findViewById(R.id.cal_hab2_off);
        Button encendercalefaccion = findViewById(R.id.encendercalefacion);
        Button apagarcalefaccion = findViewById(R.id.apagarcalefacion);
        ImageView aire_salon_on = findViewById(R.id.aire_salon_on);
        ImageView aire_salon_off = findViewById(R.id.aire_salon_off);
        ImageView aire_cocina_on = findViewById(R.id.aire_cocina_on);
        ImageView aire_cocina_off = findViewById(R.id.aire_cocina_off);
        ImageView aire_bano_on = findViewById(R.id.aire_bano_on);
        ImageView aire_bano_off = findViewById(R.id.aire_bano_off);
        ImageView aire_hab1_on = findViewById(R.id.aire_hab1_on);
        ImageView aire_hab1_off = findViewById(R.id.aire_hab1_off);
        ImageView aire_hab2_on = findViewById(R.id.aire_hab2_on);
        ImageView aire_hab2_off = findViewById(R.id.aire_hab2_off);
        Button encenderaire = findViewById(R.id.encenderaire);
        Button apagaraire = findViewById(R.id.apagaraire);

        double cero = 0.0000000001;

        base.child("temp_salon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double tempsalon = (Double) dataSnapshot.getValue();

                base.child("temp_cocina").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Double tempcocina = (Double) dataSnapshot.getValue();

                        base.child("temp_bano").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Double tempbano = (Double) dataSnapshot.getValue();

                                base.child("temp_hab1").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Double temphab1 = (Double) dataSnapshot.getValue();

                                        base.child("temp_hab2").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                Double temphab2 = (Double) dataSnapshot.getValue();

                                                Double tmedia = (tempsalon+tempcocina+tempbano+temphab1+temphab2)/5;
                                                tempmedia.setText(tmedia.toString());

                                                base.child("temp_deseada").addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        Double tdeseada = (Double) dataSnapshot.getValue();

                                                        if(tdeseada != cero) {
                                                            double temp2 = (tdeseada - cero);
                                                            String t = String.valueOf((double) temp2);
                                                            tempdeseada.setText(t);
                                                            botonmenos.setOnClickListener(view -> {
                                                                double temp3 = (tdeseada - 0.5);
                                                                base.child("temp_d_salon").setValue(temp3);
                                                                base.child("temp_d_cocina").setValue(temp3);
                                                                base.child("temp_d_bano").setValue(temp3);
                                                                base.child("temp_d_hab1").setValue(temp3);
                                                                base.child("temp_d_hab2").setValue(temp3);
                                                                base.child("temp_deseada").setValue(temp3);
                                                            });
                                                            botonborrar.setOnClickListener(view -> {
                                                                base.child("temp_d_salon").setValue(cero);
                                                                base.child("temp_d_cocina").setValue(cero);
                                                                base.child("temp_d_bano").setValue(cero);
                                                                base.child("temp_d_hab1").setValue(cero);
                                                                base.child("temp_d_hab2").setValue(cero);
                                                                base.child("temp_deseada").setValue(cero);

                                                                base.child("calefaccion_salon").setValue(false);
                                                                base.child("aire_salon").setValue(false);
                                                                base.child("calefaccion_cocina").setValue(false);
                                                                base.child("aire_cocina").setValue(false);
                                                                base.child("calefaccion_bano").setValue(false);
                                                                base.child("aire_bano").setValue(false);
                                                                base.child("calefaccion_hab1").setValue(false);
                                                                base.child("aire_hab1").setValue(false);
                                                                base.child("calefaccion_hab2").setValue(false);
                                                                base.child("aire_hab2").setValue(false);
                                                            });
                                                            botonmas.setOnClickListener(view -> {
                                                                double temp3 = (tdeseada + 0.5);
                                                                base.child("temp_d_salon").setValue(temp3);
                                                                base.child("temp_d_cocina").setValue(temp3);
                                                                base.child("temp_d_bano").setValue(temp3);
                                                                base.child("temp_d_hab1").setValue(temp3);
                                                                base.child("temp_d_hab2").setValue(temp3);
                                                                base.child("temp_deseada").setValue(temp3);
                                                            });

                                                            if (tempsalon > (tdeseada+0.5)) {
                                                                base.child("calefaccion_salon").setValue(false);
                                                                base.child("aire_salon").setValue(true);
                                                            } else if (tempsalon < (tdeseada-0.5)) {
                                                                base.child("calefaccion_salon").setValue(true);
                                                                base.child("aire_salon").setValue(false);
                                                            } else {
                                                                base.child("calefaccion_salon").setValue(false);
                                                                base.child("aire_salon").setValue(false);
                                                            }

                                                            if (tempcocina > (tdeseada+0.5)) {
                                                                base.child("calefaccion_cocina").setValue(false);
                                                                base.child("aire_cocina").setValue(true);
                                                            } else if (tempcocina < (tdeseada-0.5)) {
                                                                base.child("calefaccion_cocina").setValue(true);
                                                                base.child("aire_cocina").setValue(false);
                                                            } else {
                                                                base.child("calefaccion_cocina").setValue(false);
                                                                base.child("aire_cocina").setValue(false);
                                                            }

                                                            if (tempbano > (tdeseada+0.5)) {
                                                                base.child("calefaccion_bano").setValue(false);
                                                                base.child("aire_bano").setValue(true);
                                                            } else if (tempbano < (tdeseada-0.5)) {
                                                                base.child("calefaccion_bano").setValue(true);
                                                                base.child("aire_bano").setValue(false);
                                                            } else {
                                                                base.child("calefaccion_bano").setValue(false);
                                                                base.child("aire_bano").setValue(false);
                                                            }

                                                            if (temphab1 > (tdeseada+0.5)) {
                                                                base.child("calefaccion_hab1").setValue(false);
                                                                base.child("aire_hab1").setValue(true);
                                                            } else if (temphab1 < (tdeseada-0.5)) {
                                                                base.child("calefaccion_hab1").setValue(true);
                                                                base.child("aire_hab1").setValue(false);
                                                            } else {
                                                                base.child("calefaccion_hab1").setValue(false);
                                                                base.child("aire_hab1").setValue(false);
                                                            }

                                                            if (temphab2 > (tdeseada+0.5)) {
                                                                base.child("calefaccion_hab2").setValue(false);
                                                                base.child("aire_hab2").setValue(true);
                                                            } else if (temphab2 < (tdeseada-0.5)) {
                                                                base.child("calefaccion_hab2").setValue(true);
                                                                base.child("aire_hab2").setValue(false);
                                                            } else {
                                                                base.child("calefaccion_hab2").setValue(false);
                                                                base.child("aire_hab2").setValue(false);
                                                            }


                                                        }else{
                                                            tempdeseada.setText("N/A");

                                                            botonmenos.setOnClickListener(view -> {
                                                                double tm=Math.floor(tmedia)+cero;
                                                                if(tm>(tmedia-0.5)){
                                                                    base.child("temp_d_salon").setValue(tm);
                                                                    base.child("temp_d_cocina").setValue(tm);
                                                                    base.child("temp_d_bano").setValue(tm);
                                                                    base.child("temp_d_hab1").setValue(tm);
                                                                    base.child("temp_d_hab2").setValue(tm);
                                                                    base.child("temp_deseada").setValue(tm);
                                                                }
                                                                else{
                                                                    base.child("temp_d_salon").setValue(tm+0.5);
                                                                    base.child("temp_d_cocina").setValue(tm+0.5);
                                                                    base.child("temp_d_bano").setValue(tm+0.5);
                                                                    base.child("temp_d_hab1").setValue(tm+0.5);
                                                                    base.child("temp_d_hab2").setValue(tm+0.5);
                                                                    base.child("temp_deseada").setValue(tm+0.5);
                                                                }
                                                            });
                                                            botonmas.setOnClickListener(view -> {
                                                                double tm=Math.ceil(tmedia)+cero;
                                                                if(tm<(tmedia+0.5)){
                                                                    base.child("temp_d_salon").setValue(tm);
                                                                    base.child("temp_d_cocina").setValue(tm);
                                                                    base.child("temp_d_bano").setValue(tm);
                                                                    base.child("temp_d_hab1").setValue(tm);
                                                                    base.child("temp_d_hab2").setValue(tm);
                                                                    base.child("temp_deseada").setValue(tm);
                                                                }
                                                                else{
                                                                    base.child("temp_d_salon").setValue(tm-0.5);
                                                                    base.child("temp_d_cocina").setValue(tm-0.5);
                                                                    base.child("temp_d_bano").setValue(tm-0.5);
                                                                    base.child("temp_d_hab1").setValue(tm-0.5);
                                                                    base.child("temp_d_hab2").setValue(tm-0.5);
                                                                    base.child("temp_deseada").setValue(tm-0.5);
                                                                }
                                                            });
                                                            botonborrar.setOnClickListener(view -> {
                                                                base.child("temp_d_salon").setValue(cero);
                                                                base.child("temp_d_cocina").setValue(cero);
                                                                base.child("temp_d_bano").setValue(cero);
                                                                base.child("temp_d_hab1").setValue(cero);
                                                                base.child("temp_d_hab2").setValue(cero);
                                                                base.child("temp_deseada").setValue(cero);
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

        base.child("luz_salon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean luz = (Boolean) dataSnapshot.getValue();
                if (luz == false) {
                    luz_salon_off.setVisibility(View.VISIBLE);
                    luz_salon_on.setVisibility(View.INVISIBLE);
                }else{
                    luz_salon_off.setVisibility(View.INVISIBLE);
                    luz_salon_on.setVisibility(View.VISIBLE);
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
                    luz_cocina_off.setVisibility(View.VISIBLE);
                    luz_cocina_on.setVisibility(View.INVISIBLE);
                }else{
                    luz_cocina_off.setVisibility(View.INVISIBLE);
                    luz_cocina_on.setVisibility(View.VISIBLE);
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
                    luz_bano_off.setVisibility(View.VISIBLE);
                    luz_bano_on.setVisibility(View.INVISIBLE);
                }else{
                    luz_bano_off.setVisibility(View.INVISIBLE);
                    luz_bano_on.setVisibility(View.VISIBLE);
                }
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
                    luz_hab1_off.setVisibility(View.VISIBLE);
                    luz_hab1_on.setVisibility(View.INVISIBLE);
                }else{
                    luz_hab1_off.setVisibility(View.INVISIBLE);
                    luz_hab1_on.setVisibility(View.VISIBLE);
                }
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
                    luz_hab2_off.setVisibility(View.VISIBLE);
                    luz_hab2_on.setVisibility(View.INVISIBLE);
                }else{
                    luz_hab2_off.setVisibility(View.INVISIBLE);
                    luz_hab2_on.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        encenderluz.setOnClickListener(view -> {
            base.child("luz_salon").setValue(true);
            base.child("luz_cocina").setValue(true);
            base.child("luz_bano").setValue(true);
            base.child("luz_hab1").setValue(true);
            base.child("luz_hab2").setValue(true);
        });

        apagarluz.setOnClickListener(view -> {
            base.child("luz_salon").setValue(false);
            base.child("luz_cocina").setValue(false);
            base.child("luz_bano").setValue(false);
            base.child("luz_hab1").setValue(false);
            base.child("luz_hab2").setValue(false);
        });

        base.child("calefaccion_salon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean cal = (Boolean) dataSnapshot.getValue();
                if (cal == false) {
                    cal_salon_off.setVisibility(View.VISIBLE);
                    cal_salon_on.setVisibility(View.INVISIBLE);
                }else{
                    cal_salon_off.setVisibility(View.INVISIBLE);
                    cal_salon_on.setVisibility(View.VISIBLE);
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
                    cal_cocina_off.setVisibility(View.VISIBLE);
                    cal_cocina_on.setVisibility(View.INVISIBLE);
                }else{
                    cal_cocina_off.setVisibility(View.INVISIBLE);
                    cal_cocina_on.setVisibility(View.VISIBLE);
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
                    cal_bano_off.setVisibility(View.VISIBLE);
                    cal_bano_on.setVisibility(View.INVISIBLE);
                }else{
                    cal_bano_off.setVisibility(View.INVISIBLE);
                    cal_bano_on.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("calefaccion_hab1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean cal = (Boolean) dataSnapshot.getValue();
                if (cal == false) {
                    cal_hab1_off.setVisibility(View.VISIBLE);
                    cal_hab1_on.setVisibility(View.INVISIBLE);
                }else{
                    cal_hab1_off.setVisibility(View.INVISIBLE);
                    cal_hab1_on.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("calefaccion_hab2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean cal = (Boolean) dataSnapshot.getValue();
                if (cal == false) {
                    cal_hab2_off.setVisibility(View.VISIBLE);
                    cal_hab2_on.setVisibility(View.INVISIBLE);
                }else{
                    cal_hab2_off.setVisibility(View.INVISIBLE);
                    cal_hab2_on.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        encendercalefaccion.setOnClickListener(view -> {
            base.child("calefaccion_salon").setValue(true);
            base.child("calefaccion_cocina").setValue(true);
            base.child("calefaccion_bano").setValue(true);
            base.child("calefaccion_hab1").setValue(true);
            base.child("calefaccion_hab2").setValue(true);

            base.child("aire_salon").setValue(false);
            base.child("aire_cocina").setValue(false);
            base.child("aire_bano").setValue(false);
            base.child("aire_hab1").setValue(false);
            base.child("aire_hab2").setValue(false);

            base.child("temp_d_salon").setValue(cero);
            base.child("temp_d_cocina").setValue(cero);
            base.child("temp_d_bano").setValue(cero);
            base.child("temp_d_hab1").setValue(cero);
            base.child("temp_d_hab2").setValue(cero);
            base.child("temp_deseada").setValue(cero);
        });

        apagarcalefaccion.setOnClickListener(view -> {
            base.child("calefaccion_salon").setValue(false);
            base.child("calefaccion_cocina").setValue(false);
            base.child("calefaccion_bano").setValue(false);
            base.child("calefaccion_hab1").setValue(false);
            base.child("calefaccion_hab2").setValue(false);

            base.child("temp_d_salon").setValue(cero);
            base.child("temp_d_cocina").setValue(cero);
            base.child("temp_d_bano").setValue(cero);
            base.child("temp_d_hab1").setValue(cero);
            base.child("temp_d_hab2").setValue(cero);
            base.child("temp_deseada").setValue(cero);
        });

        base.child("aire_salon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean aire = (Boolean) dataSnapshot.getValue();
                if (aire == false) {
                    aire_salon_off.setVisibility(View.VISIBLE);
                    aire_salon_on.setVisibility(View.INVISIBLE);
                }else{
                    aire_salon_off.setVisibility(View.INVISIBLE);
                    aire_salon_on.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("aire_cocina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean aire = (Boolean) dataSnapshot.getValue();
                if (aire == false) {
                    aire_cocina_off.setVisibility(View.VISIBLE);
                    aire_cocina_on.setVisibility(View.INVISIBLE);
                }else{
                    aire_cocina_off.setVisibility(View.INVISIBLE);
                    aire_cocina_on.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("aire_bano").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean aire = (Boolean) dataSnapshot.getValue();
                if (aire == false) {
                    aire_bano_off.setVisibility(View.VISIBLE);
                    aire_bano_on.setVisibility(View.INVISIBLE);
                }else{
                    aire_bano_off.setVisibility(View.INVISIBLE);
                    aire_bano_on.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("aire_hab1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean aire = (Boolean) dataSnapshot.getValue();
                if (aire == false) {
                    aire_hab1_off.setVisibility(View.VISIBLE);
                    aire_hab1_on.setVisibility(View.INVISIBLE);
                }else{
                    aire_hab1_off.setVisibility(View.INVISIBLE);
                    aire_hab1_on.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        base.child("aire_hab2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean aire = (Boolean) dataSnapshot.getValue();
                if (aire == false) {
                    aire_hab2_off.setVisibility(View.VISIBLE);
                    aire_hab2_on.setVisibility(View.INVISIBLE);
                }else{
                    aire_hab2_off.setVisibility(View.INVISIBLE);
                    aire_hab2_on.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        encenderaire.setOnClickListener(view -> {
            base.child("aire_salon").setValue(true);
            base.child("aire_cocina").setValue(true);
            base.child("aire_bano").setValue(true);
            base.child("aire_hab1").setValue(true);
            base.child("aire_hab2").setValue(true);

            base.child("calefaccion_salon").setValue(false);
            base.child("calefaccion_cocina").setValue(false);
            base.child("calefaccion_bano").setValue(false);
            base.child("calefaccion_hab1").setValue(false);
            base.child("calefaccion_hab2").setValue(false);

            base.child("temp_d_salon").setValue(cero);
            base.child("temp_d_cocina").setValue(cero);
            base.child("temp_d_bano").setValue(cero);
            base.child("temp_d_hab1").setValue(cero);
            base.child("temp_d_hab2").setValue(cero);
            base.child("temp_deseada").setValue(cero);

        });

        apagaraire.setOnClickListener(view -> {
            base.child("aire_salon").setValue(false);
            base.child("aire_cocina").setValue(false);
            base.child("aire_bano").setValue(false);
            base.child("aire_hab1").setValue(false);
            base.child("aire_hab2").setValue(false);

            base.child("temp_d_salon").setValue(cero);
            base.child("temp_d_cocina").setValue(cero);
            base.child("temp_d_bano").setValue(cero);
            base.child("temp_d_hab1").setValue(cero);
            base.child("temp_d_hab2").setValue(cero);
            base.child("temp_deseada").setValue(cero);
        });


        Button botonresumen=findViewById(R.id.botonresumen);
        botonresumen.setOnClickListener(view -> {
            Intent i = new Intent(General.this,MainActivity.class);
            startActivity(i);
        });
        Button botonsala=findViewById(R.id.botonsala);
        botonsala.setOnClickListener(view -> {
            Intent i = new Intent(General.this,Salon.class);
            startActivity(i);
        });
        Button botonalarma=findViewById(R.id.botonalarma);
        botonalarma.setOnClickListener(view -> {
            Intent i = new Intent(General.this,Alarma.class);
            startActivity(i);
        });

    }
}