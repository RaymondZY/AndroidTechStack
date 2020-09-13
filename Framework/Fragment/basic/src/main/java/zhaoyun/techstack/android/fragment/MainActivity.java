package zhaoyun.techstack.android.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import zhaoyun.techstack.android.fragment.detail.AnimeDetailFragment;
import zhaoyun.techstack.android.fragment.list.AnimeListFragment;
import zhaoyun.techstack.android.fragment.model.Anime;

public class MainActivity extends AppCompatActivity implements AnimeListFragment.OnAnimeClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            onAnimeClicked(new Anime(String.valueOf(System.currentTimeMillis()), 100, true));
        }
    }

    @Override
    public void onAnimeClicked(Anime anime) {
        Fragment fragment = AnimeDetailFragment.newInstance(anime);
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.fragment_in,
                        R.anim.fragment_out,
                        R.anim.fragment_in,
                        R.anim.fragment_out
                )
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
        boolean result = getSupportFragmentManager().executePendingTransactions();
        Log.d("DEBUG", "onAnimeClicked() result = " + result);
    }
}
