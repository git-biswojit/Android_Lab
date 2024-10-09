package notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.onenightbeforeexam.R;


public class NotesFragment extends Fragment {

    public NotesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        Button Back = view.findViewById(R.id.saveBtn);
        Back.setOnClickListener(v -> getBackToParent());
        return view;
    }

    private void getBackToParent(){
        getParentFragmentManager().popBackStack();
    }
}