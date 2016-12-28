package Fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.demo.R;

/**
 * Created by ABHIJNU on 10/22/2016.
 */
public class GuidelinesFragment extends Fragment {
    TextView tvl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_guidelines,container,false);
        tvl = (TextView) rootView.findViewById(R.id.guide_text);
        return rootView;
    }
}
