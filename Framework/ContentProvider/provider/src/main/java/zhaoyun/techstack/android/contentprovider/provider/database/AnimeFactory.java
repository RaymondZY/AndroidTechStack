package zhaoyun.techstack.android.contentprovider.provider.database;

import java.util.ArrayList;
import java.util.List;

import zhaoyun.techstack.android.contentprovider.provider.entity.Anime;

/**
 * @author zhaoyun
 * @version 2018/10/26
 */
public class AnimeFactory {

    public static List<Anime> createDefaultAnime() {
        String[] name = {
                "四月是你的谎言",
                "Overload III",
                "紫罗兰永恒花园",
                "Fate/Unlimited Blade Works"
        };
        int[] likeCount = {0, 0, 0, 0};
        boolean[] watched = {true, false, true, false};

        List<Anime> animeList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            int index = i % 4;
            animeList.add(new Anime(name[index], likeCount[index], watched[index]));
        }
        return animeList;
    }
}
