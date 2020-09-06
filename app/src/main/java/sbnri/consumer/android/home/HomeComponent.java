package servify.android.consumer.home;

import android.content.Context;

import dagger.Component;
import servify.android.consumer.android.modules.HomeModule;
import servify.android.consumer.android.qualifiers.ApplicationContext;
import servify.android.consumer.android.scopes.ActivityScope;
import servify.android.consumer.base.activity.BaseActivityComponent;

/**
 * Created by Aneesha on 18/10/17.
 */
@ActivityScope
@Component(modules = HomeModule.class, dependencies = BaseActivityComponent.class)
public interface HomeComponent {

    @ApplicationContext
    Context getContext();

    void injectDependencies(HomeActivity activity);

    void injectDependencies(HomeFragment fragment);

    void injectDependencies(HomeFragmentMedion fragment);
}
