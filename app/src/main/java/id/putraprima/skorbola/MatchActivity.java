package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {

    private TextView homeText;
    private TextView awayText;
    private ImageView homeImage;
    private ImageView awayImage;
    private Integer skorHome;
    private TextView HomeScore;
    private TextView AwayScore;
    private Integer skorAway;

    private static final String HASIL_KEY = "hasil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);
        HomeScore = findViewById(R.id.score_home);
        AwayScore = findViewById(R.id.score_away);

        skorAway=0;
        skorHome=0;
        HomeScore.setText("0");
        AwayScore.setText("0");

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            Bundle bundle2 = getIntent().getExtras();
            Bitmap bmp1 = bundle2.getParcelable("homeimage");
            Bitmap bmp2 = bundle2.getParcelable("awayimage");

            homeImage.setImageBitmap(bmp1);
            awayImage.setImageBitmap(bmp2);

            homeText.setText(bundle.getString("hometeam"));
            awayText.setText(bundle.getString("awayteam"));
        }

        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }

    public void handleAddHomeScore(View view) {
        skorHome++;
        HomeScore.setText(String.valueOf(skorHome));
    }

    public void handleHasil(View view) {
        String hasil;
        if(skorHome > skorAway){
            hasil = homeText.getText().toString();
        }
        else if(skorAway > skorHome){
            hasil = awayText.getText().toString();
        }
        else{
            hasil = "DRAW";
        }
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra(HASIL_KEY, hasil);
        startActivity(intent);
    }

    public void handleAddAwayScore(View view) {
        skorAway++;
        AwayScore.setText(String.valueOf(skorAway));
    }
}
