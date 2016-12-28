package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.demo.LoginActivity;
import com.example.admin.demo.R;

public class ProfileFragment extends Fragment {
    TextView tv5,tv6,tv7,tv8,tv9;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_profile,container,false);
        tv5 = (TextView) rootView.findViewById(R.id.textView5);
        tv6 = (TextView) rootView.findViewById(R.id.textView11);
        tv7 = (TextView) rootView.findViewById(R.id.textView12);
        tv8 = (TextView) rootView.findViewById(R.id.textView13);
        tv9 = (TextView) rootView.findViewById(R.id.textView14);


        String text1 = LoginActivity.prof.Vehicle;
        String text2 = LoginActivity.prof.Sensor;
        String text3 = LoginActivity.prof.Name;
        String text4 = LoginActivity.prof.Phone;
        String text5 = LoginActivity.prof.Address;
        /*
        Toast.makeText(getActivity(), text1.toString().trim(), Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), text2.toString().trim(), Toast.LENGTH_LONG).show();
        Toast.makeText(getActivity(), text3.toString().trim(), Toast.LENGTH_LONG).show();
        Toast.makeText(getActivity(), text4.toString().trim(), Toast.LENGTH_LONG).show();
        Toast.makeText(getActivity(), text5.toString().trim(), Toast.LENGTH_LONG).show();
*/



        tv5.setText(text1.toString().trim());
        tv6.setText(text2.toString().trim());
        tv7.setText(text3.toString().trim());
        tv8.setText(text4.toString().trim());
        tv9.setText(text5.toString().trim());

        return rootView;
    }

}
