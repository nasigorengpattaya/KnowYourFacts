package sg.edu.rp.c346.knowyourfacts;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag3 extends Fragment {

    TextView tv1, tv2, tv3, tv4;
    Button btnColour;

    public Frag3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag3, container, false);

        tv1 = (TextView)view.findViewById(R.id.textView1);
        tv2 = (TextView)view.findViewById(R.id.textView2);
        tv3 = (TextView)view.findViewById(R.id.textView3);
        tv4 = (TextView)view.findViewById(R.id.textView4);
        btnColour = (Button) view.findViewById(R.id.btnColour);

        btnColour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int randomInt = r.nextInt(2 - 0 + 1);

                if (randomInt == 0) {
                    tv1.setBackgroundColor(Color.parseColor("#ff33b5e5"));
                    tv2.setBackgroundColor(Color.parseColor("#ff33b5e5"));
                    tv3.setBackgroundColor(Color.parseColor("#ff33b5e5"));
                    tv4.setBackgroundColor(Color.parseColor("#ff33b5e5"));
                } else if (randomInt == 1) {
                    tv1.setBackgroundColor(Color.parseColor("#ffffbb33"));
                    tv2.setBackgroundColor(Color.parseColor("#ffffbb33"));
                    tv3.setBackgroundColor(Color.parseColor("#ffffbb33"));
                    tv4.setBackgroundColor(Color.parseColor("#ffffbb33"));
                } else if (randomInt == 2) {
                    tv1.setBackgroundColor(Color.parseColor("#ff99cc00"));
                    tv2.setBackgroundColor(Color.parseColor("#ff99cc00"));
                    tv3.setBackgroundColor(Color.parseColor("#ff99cc00"));
                    tv4.setBackgroundColor(Color.parseColor("#ff99cc00"));
                }
            }
        });

        return view;
    }

}
