package sbnri.consumer.android.webservice;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

public interface ApiCallTags {


    String GET_ALL_NEWS = "getAllNews";
    String GET_ALL_BANKS_DATA = "getAllBanksData";
    String GENERATE_LINK_FOR_EMAIL = "generateLinkForEmail";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({GET_ALL_NEWS,GET_ALL_BANKS_DATA,GENERATE_LINK_FOR_EMAIL})

    @interface ApiCallIdentifiers {
    }
}
