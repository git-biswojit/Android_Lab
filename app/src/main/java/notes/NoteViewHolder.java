package notes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeeWork.R;


public class NoteViewHolder extends RecyclerView.ViewHolder {

    TextView tvTitle, tvDescription;
    CardView cvNoteItem;

    public NoteViewHolder(@NonNull View itemView) {

        super(itemView);
        init(itemView);
    }
    private void init(View itemView){
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvDescription = itemView.findViewById(R.id.tvDescription);
        cvNoteItem = itemView.findViewById(R.id.cvNoteItem);
    }
}
