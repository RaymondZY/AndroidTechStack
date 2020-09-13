package zhaoyun.techstack.android.service;

import zhaoyun.techstack.android.service.AidlServiceCallback;
import zhaoyun.techstack.android.service.bound.remote.WrappedObject;

interface AidlServiceInterface {

    oneway void asyncHeavyWork(in WrappedObject wrappedObject, in AidlServiceCallback callback);

    void syncLightWork(inout WrappedObject wrappedObject);
}
