package zhaoyun.techstack.android.fragment.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyun
 * @version 2018/10/29
 */
public class AnimeRepository {

    public static List<Anime> createAnimeList() {
        String[] names = new String[] {
                "Fate/stay night",
                "Overload III",
                "Gosick",
                "Violet Evergarden",
                "EVA",
                "Lost Song",
                "如果有妹妹就好了",
                "宅男宅女恋爱真难",
                "后街女孩"
        };
        List<Anime> animeList = new ArrayList<>();
        for (String name : names) {
            Anime anime = new Anime(name, 100, true);
            animeList.add(anime);
        }
        return animeList;
    }
}
