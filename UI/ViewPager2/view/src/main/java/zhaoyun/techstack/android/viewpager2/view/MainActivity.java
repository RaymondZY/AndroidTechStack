package zhaoyun.techstack.android.viewpager2.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new Adapter(this));
    }

    private static class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private static final int N = 10;

        private LayoutInflater mLayoutInflater;

        private Adapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = mLayoutInflater.inflate(R.layout.item_view_number, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.mTextView.setText(String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            return N;
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.textView);
        }
    }
}
