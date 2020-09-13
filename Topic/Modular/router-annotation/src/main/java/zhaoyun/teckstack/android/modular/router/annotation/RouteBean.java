package zhaoyun.teckstack.android.modular.router.annotation;

/**
 * @author zhaoyun
 * @version 2020/6/29
 */
public class RouteBean {

    private String mGroup;
    private String mPath;
    private Class<?> mClazz;

    public RouteBean(String group, String path, Class<?> clazz) {
        mGroup = group;
        mPath = path;
        mClazz = clazz;
    }

    public String getGroup() {
        return mGroup;
    }

    public String getPath() {
        return mPath;
    }

    public Class<?> getClazz() {
        return mClazz;
    }
}
