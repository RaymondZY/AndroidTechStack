package zhaoyun.techstack.android.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;

/**
 * @author zhaoyun
 * @version 2020/6/23
 */
public interface AddInterface extends IInterface {

    int add(int a, int b) throws RemoteException;

    abstract class Stub extends Binder implements AddInterface {

        private static final String DESCRIPTOR = "zhaoyun.techstack.android.ipc";
        private static final int TRANSACTION_add = FIRST_CALL_TRANSACTION;

        public static AddInterface asInterface(IBinder binder) {
            if (binder != null) {
                IInterface iInterface = binder.queryLocalInterface(DESCRIPTOR);
                if (iInterface instanceof AddInterface) {
                    // 返回Local Binder对象
                    return (AddInterface) iInterface;
                } else {
                    // 返回Proxy Binder对象
                    return new Proxy(binder);
                }
            }
            return null;
        }

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case INTERFACE_TRANSACTION:
                    reply.writeString(DESCRIPTOR);
                    return true;

                case TRANSACTION_add:
                    data.enforceInterface(DESCRIPTOR);
                    int a = data.readInt();
                    int b = data.readInt();
                    int c = add(a, b);
                    reply.writeNoException();
                    reply.writeInt(c);
                    return true;
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements AddInterface {

            private IBinder mRemote;

            public Proxy(IBinder binder) {
                mRemote = binder;
            }

            @Override
            public int add(int a, int b) throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    data.writeInt(a);
                    data.writeInt(b);
                    mRemote.transact(TRANSACTION_add, data, reply, 0);
                    reply.readException();
                    return reply.readInt();
                } finally {
                    data.recycle();
                    reply.recycle();
                }
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }
        }
    }
}
