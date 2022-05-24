package com.example.quizle;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.MyViewHolder> {

    private List<Quiz> mlist;
    private Activity activity;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public QuizAdapter(List<Quiz> mlist, Activity activity) {
        this.mlist = mlist;
        this.activity = activity;
    }

    @NonNull
    @Override
    public QuizAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.card_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Quiz quiz = mlist.get(position);
        holder.quiz_judul.setText(quiz.getJudul());
        holder.quiz_subjudul.setText(quiz.getSubjudul());




    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView quiz_judul, quiz_subjudul;
        CardView card_view;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            quiz_judul = itemView.findViewById(R.id.quizjudul);
            quiz_subjudul = itemView.findViewById(R.id.quizsubjudul);

            card_view =itemView.findViewById(R.id.recycler_view);
        }
    }
}
