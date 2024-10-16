package notes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeeWork.R;


public class NoteViewHolder extends RecyclerView.ViewHolder {

    TextView tvTitle, tvDescription;

    public NoteViewHolder(@NonNull View itemView) {

        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvDescription = itemView.findViewById(R.id.tvDescription);
    }
}
