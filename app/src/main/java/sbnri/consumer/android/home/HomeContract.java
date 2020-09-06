package servify.android.consumer.home;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import servify.android.consumer.base.contract.BasePresenterImp;
import servify.android.consumer.base.contract.BaseView;
import servify.android.consumer.base.schedulers.SchedulerProvider;
import servify.android.consumer.common.analytics.AnalyticsManager;
import servify.android.consumer.data.models.Banner;
import servify.android.consumer.data.models.Consumer;
import servify.android.consumer.data.models.ConsumerProduct;
import servify.android.consumer.data.models.UpgradeDetails;
import servify.android.consumer.data.source.ServifyDataSource;
import servify.android.consumer.data.source.local.ServifyLocalDataSource;
import servify.android.consumer.diagnosis.models.TradeInDetailResponse;
import servify.android.consumer.insurance.models.PlanDetail;
import servify.android.consumer.insurance.models.PlanGroup;
import servify.android.consumer.insurance.models.SoldPlan;
import servify.android.consumer.util.ReadDeviceUtils;

/**
 * Created by yashThakur on 23/12/16.
 */

public interface HomeContract {

    interface HomeFragmentView extends BaseView {
        void setupTrackRepair(ArrayList<ConsumerProduct> deviceUnderRepairs);

        void showPlanGroupsActivationSheet(ArrayList<PlanGroup> planGroups);

        void setupBannersForPlans(ArrayList<Banner> banners, ArrayList<PlanGroup> planGroups);

        void setupPlanActions(ArrayList<PlanGroup> planGroups);

        void startRefreshing();

        void stopRefreshing();

        void navigateToEditProfileActivity(Consumer consumer);

        void showDeviceAddedSheet(boolean showDeviceAddedSheet);

        void showAddMoreDetails();

        void showComplimentaryPlanSheet(PlanGroup planGroup, PlanDetail complimentaryPlanDetail);

        void createUserProfile(Consumer consumer);

        void pushEligiblePlansEvent(ArrayList<PlanGroup> planGroups);

        void showPlanActivationScreen(ArrayList<SoldPlan> soldPlans);

        void navigatoToDeviceDetailsActivity(ConsumerProduct consumerProduct);

        void showErrorSheet(String msg);

        void setupTradeInAction(TradeInDetailResponse tradeInDetailResponse);

        boolean handleDeeplinking();

        void setAvailableOptions(UpgradeDetails upgradeDetails);

        void askIMEIForPlanActivation(String callTag);

        void showTradeInError(String message, boolean isToastOrDialog);
    }

    interface HomeFragmentMedionView extends BaseView {

        void startRefreshing();

        void stopRefreshing();

        void setupBanners(ArrayList<Banner> banners);

        void showDiagnosisActionNotification(boolean shouldShowNotification, int notificationCount);

        void navigateToDiagnosisSelectionActivity();

        void showErrorSheet(String msg);
    }

    interface HomeActivityView extends BaseView {
        void showNotificationStatus(int unreadCount);
    }

    abstract class HomePresenter extends BasePresenterImp {

        HomePresenter(@NonNull ServifyDataSource servifyRepository, ServifyLocalDataSource servifyLocalDataSource, @NonNull SchedulerProvider schedulerProvider, BaseView baseView, Context context, AnalyticsManager analyticsManager) {
            super(servifyRepository, schedulerProvider, baseView, context, analyticsManager);
        }

        abstract void cleverTapAddDeviceEvent(ConsumerProduct consumerProduct, Activity activity);

        abstract void saveFirstBootDate();

        abstract void getTrackRepair(boolean isFromRxbus);

        abstract void fetchEligiblePlans(ReadDeviceUtils readDeviceUtils);

        abstract void createSoldPlan(ConsumerProduct consumerProduct, PlanDetail planDetail);

        abstract void autoActivatePlan(PlanDetail planDetail, int consumerID, ConsumerProduct consumerProduct);

        abstract void getTradeInRequest(ConsumerProduct consumerProduct);

        abstract void addDefaultDevice(Activity activity, boolean shouldShowSheet);

        abstract void getUserProfile(int consumerID);

        abstract void getNotifications();

        abstract void getBanners();

        abstract void getConsumerProductDetails();

        abstract void setHomeActivityInstance(HomeActivityView homeActivityView);

        abstract void getAvailableOptions(int consumerProductId, int consumerId);
    }

    abstract class HomeFragmentMedionPresenter extends BasePresenterImp {

        HomeFragmentMedionPresenter(@NonNull ServifyDataSource servifyRepository, ServifyLocalDataSource servifyLocalDataSource, @NonNull SchedulerProvider schedulerProvider, BaseView baseView, Context context, AnalyticsManager analyticsManager) {
            super(servifyRepository, schedulerProvider, baseView, context, analyticsManager);
        }

        abstract void addDefaultDevice(Activity activity);

        abstract void getBanners();

        abstract void loadLocalDiagnosis();

        abstract void loadDiagnosisHistory(Activity activity);


    }
}
