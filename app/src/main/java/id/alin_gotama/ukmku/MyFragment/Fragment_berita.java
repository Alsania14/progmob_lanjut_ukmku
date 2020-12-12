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

public class Fragment_berita extends Fragment {
    private WebView webView;
    private Button btnWebViewBack,btnWebViewForward;
    public Fragment_berita() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_berita,container,false);
        webView = view.findViewById(R.id.wvBerita1);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://sinmawa.unud.ac.id/settings/view_organisasi/65");

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
