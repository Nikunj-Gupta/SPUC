package Fragments;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.demo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HomeFragment extends Fragment {

    TextView tv2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        tv2 = (TextView) rootView.findViewById(R.id.home_text);
        String data = "";
        StringBuffer sbuffer = new StringBuffer();
        AssetManager assetManager = getContext().getResources().getAssets();
        InputStream is = null;
        try {
            is = assetManager.open("test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //InputStream is = this.getResources().openRawResource(+ R.drawable.test);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is != null) {
            try {
                while ((data = reader.readLine()) != null) {
                    sbuffer.append(data + "\n");
                }

                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();            }
        }
        //Toast.makeText(getBaseContext(), sbuffer.toString(), Toast.LENGTH_LONG).show();
        String[] lines = sbuffer.toString().split("\\n");
        for(int i=0;i<lines.length;i++) {
            //Toast.makeText(getActivity(),lines[i].toString(),Toast.LENGTH_LONG).show();

            tv2.setText(lines[i]);
        }

        return rootView;
    }
}
