package com.example.gestiondefete.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.gestiondefete.R;
import com.example.gestiondefete.models.Participant;

import java.util.List;

public class ParticipantListAdapter extends RecyclerView.Adapter<ParticipantListAdapter.ParticipantViewHolder> {
    private List<Participant> participants;
    private LayoutInflater inflater;
    private Context context;
    public ParticipantListAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.participant_detail,parent,false);
        return new ParticipantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantViewHolder participantViewHolder, int position) {
        if (participants != null) {
            Participant participant = participants.get(position);
            participantViewHolder.tvIdentity.setText(participant.getPrenom() + " " + participant.getNom());
            participantViewHolder.tvTel.setText(participant.getTel());
            participantViewHolder.tvBoisson.setText(participant.getBoissonFavorite());
            participantViewHolder.itemView.setTag(participant);
        }
        else participantViewHolder.tvIdentity.setText(context.getString(R.string.pas_dinscrit));
    }

    @Override
    public int getItemCount() {
        if (participants != null)
            return participants.size();
        return 0;
    }

    class ParticipantViewHolder extends RecyclerView.ViewHolder{
        private TextView tvIdentity;
        private TextView tvTel;
        private TextView tvBoisson;
        public ParticipantViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdentity = itemView.findViewById(R.id.tv_identity);
            tvTel = itemView.findViewById(R.id.tv_tel);
            tvBoisson = itemView.findViewById(R.id.tv_boisson);
        }
    }

    public void setParticipants(List<Participant> participants){
        this.participants = participants;
        notifyDataSetChanged();
    }
}
