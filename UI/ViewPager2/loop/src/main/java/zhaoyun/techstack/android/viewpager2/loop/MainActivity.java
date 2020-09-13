package zhaoyun.techstack.android.viewpager2.loop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewpager);
        Adapter adapter = new Adapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(adapter.calcStartPosition(), false);
    }

    private static class Adapter extends FragmentStateAdapter {

        private static final int N = 10;

        private Adapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return NumberFragment.createInstance(adaptPosition(position));
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        private int adaptPosition(int position) {
            return position % N;
        }

        private int calcStartPosition() {
            return Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2) % N;
        }
    }
}
