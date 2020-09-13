package zhaoyun.techstack.android.service;

import zhaoyun.techstack.android.service.bound.remote.WrappedObject;

interface AidlServiceCallback {

    oneway void onProgressChanged(int percent, in WrappedObject changedObject);
}
