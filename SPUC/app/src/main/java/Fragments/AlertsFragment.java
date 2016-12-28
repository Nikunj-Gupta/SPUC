package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.demo.LoginActivity;
import com.example.admin.demo.R;


public class AlertsFragment extends Fragment {

    TextView tv10;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_alerts,container,false);
        tv10 = (TextView) rootView.findViewById(R.id.textView15);
        String text1 = LoginActivity.prof.Data;
        if (text1 !="")
        //Toast.makeText(getActivity(), text1.toString().trim(), Toast.LENGTH_SHORT).show();


        tv10.setText(text1.toString().trim());
        return rootView;
    }

}
