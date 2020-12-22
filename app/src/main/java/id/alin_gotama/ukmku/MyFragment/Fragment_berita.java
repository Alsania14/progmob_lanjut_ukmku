package id.alin_gotama.ukmku.MyFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.alin_gotama.ukmku.R;
import id.alin_gotama.ukmku.Room.Database.MyDatabase;
import id.alin_gotama.ukmku.Room.Entity.UKM;

public class Fragment_berita extends Fragment {
    public static final String UKM_ID = "UKM_ID";
    private WebView webView;
    private Button btnWebViewBack,btnWebViewForward;
    private Long ukm_id;
    private UKM ukm;
    private MyDatabase myDatabase;

    public Fragment_berita() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_berita,container,false);

        this.ukm_id = getArguments().getLong(UKM_ID);
        myDatabase = MyDatabase.createDatabase(getContext());
        this.ukm = myDatabase.myDao().ambilSatuUKMid(this.ukm_id);

        webView = view.findViewById(R.id.wvBerita1);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        StringBuilder builder = new StringBuilder();

        if(!ukm.getUkm_link().contains("https://")){
            builder.append("https://");
            builder.append(ukm.ukm_link);
        }else{
            builder.append(ukm.ukm_link);
        }
        
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(builder.toString());




        this.btnWebViewBack = view.findViewById(R.id.btnBeritaBack);
        this.btnWebViewForward = view.findViewById(R.id.btnBeritaForward);

//        this.ivCover = view.findViewById(R.id.ivDetailUKM1);

        this.btnWebViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goBack();
            }
        });

        this.btnWebViewForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goForward();
            }
        });

        return view;
    }
}
