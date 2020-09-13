package zhaoyun.techstack.android.viewpager.loop;

import android.widget.Scroller;

import java.lang.reflect.Field;

import androidx.viewpager.widget.ViewPager;

/**
 * @author zhaoyun
 * @version 2020/5/20
 */
public class Utils {

    public static void setViewPagerScroller(ViewPager viewPager, Scroller scroller) {
        try {
            Class clazz = ViewPager.class;
            Field f = clazz.getDeclaredField("mScroller");
            f.setAccessible(true);
            f.set(viewPager, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
