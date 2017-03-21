package cz.micr.remoteshutdowntimer.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import cz.micr.remoteshutdowntimer.injection.ApplicationContext;
import cz.micr.remoteshutdowntimer.injection.components.ActivityComponent;


public abstract class BaseViewModel extends BaseObservable {

    @Inject
    @ApplicationContext
    protected Context appContext;

    protected BaseViewModel(@NonNull ActivityComponent activityComponent,
                            @Nullable State savedInstanceState) {
        activityComponent.inject(this);
    }

    @CallSuper
    public void onStart() {

    }

    public State getInstanceState() {
        return new State(this);
    }

    @CallSuper
    public void onStop() {

    }

    public static class State implements Parcelable {

        public static Creator<State> CREATOR = new Creator<State>() {
            @Override
            public State createFromParcel(Parcel source) {
                return new State(source);
            }

            @Override
            public State[] newArray(int size) {
                return new State[size];
            }
        };

        protected State(BaseViewModel baseViewModel) {

        }

        public State(Parcel in) {

        }

        @Override
        public int describeContents() {
            return 0;
        }

        @CallSuper
        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }
}
