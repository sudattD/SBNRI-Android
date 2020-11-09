package sbnri.consumer.android;


import android.app.Activity;
import android.content.Context;

import dagger.Component;
import sbnri.consumer.android.EmailConfirmation.EmailConfirmationActivity;
import sbnri.consumer.android.accountflow.AccountIntroSplashActivity;
import sbnri.consumer.android.accountflow.BankOnBoardingActivity;
import sbnri.consumer.android.accountflow.ShowBanksListActivity;
import sbnri.consumer.android.base.activity.BaseActivityComponent;
import sbnri.consumer.android.base.activity.BaseViewModule;
import sbnri.consumer.android.base.contract.BaseView;
import sbnri.consumer.android.base.schedulers.SchedulerProvider;
import sbnri.consumer.android.bottomsheetDialoguesFrags.UserEmailBottomSheetFragment;
import sbnri.consumer.android.data.local.SBNRILocalDataSource;
import sbnri.consumer.android.data.local.SBNRIPref;
import sbnri.consumer.android.data.source.SBNRIDataSource;
import sbnri.consumer.android.onboarding.OnBoardingActivity;
import sbnri.consumer.android.profile.ProfileBiometricAuthorizeFragment;
import sbnri.consumer.android.profile.ProfileCompletionActivity;
import sbnri.consumer.android.profile.ProfileNameAndCityFragment;
import sbnri.consumer.android.qualifiers.ActivityContext;
import sbnri.consumer.android.qualifiers.ApplicationContext;
import sbnri.consumer.android.qualifiers.LocalDataSource;
import sbnri.consumer.android.qualifiers.SBNRIRepositoryQualifier;
import sbnri.consumer.android.scopes.ActivityScope;
import sbnri.consumer.android.splash.SplashActivity;

@ActivityScope
@Component(modules = BaseViewModule.class, dependencies = BaseActivityComponent.class)
public interface DependencyInjectorComponent {

    BaseView baseView();

    BaseViewModule baseViewModule();

    @ApplicationContext
    Context getContext();

    @ActivityContext
    Context getActivityContext();

    Activity getActivity();



    @LocalDataSource
    SBNRILocalDataSource getLocalDataSource();

    @SBNRIRepositoryQualifier
    SBNRIDataSource getRemoteDataSource();

    SchedulerProvider getSchedulerProvider();

//    Picasso getPicasso();

    SBNRIPref getSBNRIPref();

    //Activities
    void injectDependencies(SplashActivity activity);
    void injectDependencies(OnBoardingActivity activity);

    void injectDependencies(ShowBanksListActivity activity);


    void injectDependencies(UserEmailBottomSheetFragment fragment);

    void injectDependencies(EmailConfirmationActivity activity);

    void injectDependencies(ProfileNameAndCityFragment fragment);
    void injectDependencies(ProfileCompletionActivity activity);
    void injectDependencies(ProfileBiometricAuthorizeFragment fragment);
    void injectDependencies(AccountIntroSplashActivity activity);
    void injectDependencies(BankOnBoardingActivity activity);
}
