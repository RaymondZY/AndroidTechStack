package zhaoyun.techstack.android.toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "NavigationBar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem menuItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) menuItem.getActionView();

        // 设置输入提示
        searchView.setQueryHint("query hint");
        // 设置是否一开始显示成图标
        searchView.setIconified(true);
        // 设置是否一开始就显示输入框
        searchView.onActionViewExpanded();

        // 设置监听 - Expand
        menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Log.i(TAG, "onMenuItemActionExpand()");
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Log.i(TAG, "onMenuItemActionCollapse()");
                return true;
            }
        });
        // 设置监听 - Focus
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i(TAG, "onQueryTextFocusChange()");
                Log.i(TAG, "hasFocus = " + hasFocus);
            }
        });
        // 设置监听 - Query
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(TAG, "onQueryTextSubmit()");
                Log.i(TAG, "query = " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i(TAG, "onQueryTextChange()");
                Log.i(TAG, "newText = " + newText);
                return false;
            }
        });
        // 设置监听 - Click
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onSearchClick()");
            }
        });
        // 设置监听 - Suggestion
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                Log.i(TAG, "onSuggestionSelect()");
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                Log.i(TAG, "onSuggestionClick()");
                return false;
            }
        });
        // 设置监听 - Close
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.i(TAG, "onClose()");
                return false;
            }
        });

        return true;
    }
}
