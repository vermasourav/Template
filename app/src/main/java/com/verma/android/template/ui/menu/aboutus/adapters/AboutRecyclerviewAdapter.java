package com.verma.android.template.ui.menu.aboutus.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.verma.android.template.R;
import com.verma.android.template.ui.menu.aboutus.ImageSupport;
import com.verma.android.template.ui.menu.aboutus.models.Member;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class AboutRecyclerviewAdapter extends RecyclerView.Adapter<AboutRecyclerviewAdapter.ViewHolder> {

    private static final String TAG = "AboutRecyclerviewAdapte";
    Context context;
    ArrayList<Member> members;

    public AboutRecyclerviewAdapter(Context context, List<Member> members) {
        this.context = context;
        this.members = (ArrayList<Member>) members;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.office_member, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Member member = members.get(position);

        try {
            new ImageSupport().setImageWithGlide(context, member.getImageUrl(), holder.imageView);
            //Picasso.get().load(member.getImageUrl()).placeholder(R.drawable.ic_face_profile_grey600_48dp).into(holder.imageView);
        } catch (Exception e) {
            Timber.e("onBindViewHolder: %s", e.toString());
        }
        holder.textViewName.setText(member.getName());
        holder.textViewPost.setText(member.getPost());

        holder.cardView.setOnClickListener(view -> {
            if (member.getContactUrl().isEmpty()) {
                Toast.makeText(context, "" + member.getName() + " has added no contact info", Toast.LENGTH_SHORT).show();
            } else {
                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(member.getContactUrl()));
                context.startActivity(browse);
            }
        });
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName;
        TextView textViewPost;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.faceImage);
            textViewName = itemView.findViewById(R.id.nameTv);
            textViewPost = itemView.findViewById(R.id.postTv);

            cardView = itemView.findViewById(R.id.backcard);
        }
    }
}
