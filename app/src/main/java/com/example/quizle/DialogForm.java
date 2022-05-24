package com.example.quizle;


import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogForm extends DialogFragment {

    String judul, subjudul, key, pilih;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();



    public DialogForm(String s, String judul, String subjudul, String key, String pilih) {
        this.judul = judul;
        this.subjudul = subjudul;

        this.key = key;
        this.pilih = pilih;
    }


    TextView judulnya, subjudulnya;
    Button simpan;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.input_form, container,false);

        judulnya = view.findViewById(R.id.judul_if);
        subjudulnya = view.findViewById(R.id.subjudul_if);


        judulnya.setText(judul);
        subjudulnya.setText(subjudul);


        simpan = view.findViewById(R.id.simpan);
        simpan.setOnClickListener(v -> {


            String judul = judulnya.getText().toString();
            String subjudul = subjudulnya.getText().toString();


            if (TextUtils.isEmpty(judul)){
                input((EditText) judulnya, "judul");
            } else if(TextUtils.isEmpty(subjudul)){
                input((EditText) subjudulnya, "subjudul");

            } else{
                if(pilih.equals("tambah")){
                    database.child("data").push().setValue(new Quiz(judul, subjudul)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void avoid) {
                            Toast.makeText(view.getContext(), "Data tersimpan", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(), "Dta gagal tersimpan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else if(pilih.equals("ubah")){
                    database.child("data").child(key).setValue(new Quiz(judul, subjudul )).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void avoid) {
                            Toast.makeText(view.getContext(), "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(), "Data gagal diubah", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

        });

        return view;
    }

    public void onStart(){
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    private void input(EditText txt, String s){
        txt.setError(s+ "Tidak boleh kosong");
        txt.requestFocus();
    }
}
