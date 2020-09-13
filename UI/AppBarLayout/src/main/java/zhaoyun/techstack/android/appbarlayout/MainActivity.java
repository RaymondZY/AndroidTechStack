package zhaoyun.techstack.android.appbarlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_KEY_SCROLL_FLAGS = "scroll_flags";

    public static void startActivity(Context context, int scrollFlags) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_KEY_SCROLL_FLAGS, scrollFlags);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initScrollFlags();
        initView();
    }

    private void initScrollFlags() {
        int scrollFlags = getIntent().getIntExtra(EXTRA_KEY_SCROLL_FLAGS, AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);

        Toolbar toolBar = findViewById(R.id.toolbar);

        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) toolBar.getLayoutParams();
        layoutParams.setScrollFlags(scrollFlags);
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new Adapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private static class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_number, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.mTextView.setText(holder.mTextView.getContext().getString(R.string.item_view_text, position));
        }

        @Override
        public int getItemCount() {
            return 100;
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
