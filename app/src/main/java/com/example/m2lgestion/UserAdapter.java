package com.example.m2lgestion;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.m2lgestion.dao.UserDao;
import com.example.m2lgestion.entity.User;

import java.util.List;



public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>
{
    private List<User> userList;
    private Context context;

// Constructeur pour initialiser la liste et le contexte
    public UserAdapter(List<User> userList, Context context) {
    this.userList = userList ;
    this.context = context ;
}

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item,
                parent, false);
        return new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.textViewNom.setText(user.getNom());
        holder.textViewPrenom.setText(user.getPrenom());
// Définissez les listeners pour les icônes de suppression et de modification ici
    }
    @Override
    public int getItemCount() {
        return userList.size();
    }
    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNom, textViewPrenom;
        ImageView imageViewDelete, imageViewEdit;
        UserViewHolder(View itemView) {
            super(itemView);
            textViewNom = itemView.findViewById(R.id.textViewNom);
            textViewPrenom = itemView.findViewById(R.id.textViewPrenom);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            imageViewEdit = itemView.findViewById(R.id.imageViewEdit);
            imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    User user = userList.get(position);
// Supprimez l'utilisateur de votre base de données
// Mettez à jour la liste des utilisateurs et notifiez l'adaptateur
                    UserDao.deleteUser(user.getId());
                    userList.remove(position);
                    notifyItemRemoved(position);
                }
            });
            imageViewEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    User userToEdit = userList.get(position);

                    Intent editIntent = new Intent(context, EditUserActivity.class);
                    editIntent.putExtra("user_id", (CharSequence) userToEdit); // Exemple si vous passez juste
                    context.startActivity(editIntent);
                }
            });
// Définissez les actions pour imageViewDelete et imageViewEdit ici
        }
    }
}
