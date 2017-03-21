/*
 * Copyright (C) 2015 Brian Lee (@hiBrianLee)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cz.micr.remoteshutdowntimer.viewmodel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;
import javax.inject.Singleton;

import cz.micr.remoteshutdowntimer.injection.components.ActivityComponent;

@Singleton
public class AppViewModelFactory implements ViewModelFactory {

    @Inject
    AppViewModelFactory() {

    }

    @NonNull
    @Override
    public MainViewModel createMainViewModel(@NonNull ActivityComponent activityComponent, @Nullable BaseViewModel.State savedViewModelState) {
        return new MainViewModel(activityComponent, savedViewModelState);
    }
}
