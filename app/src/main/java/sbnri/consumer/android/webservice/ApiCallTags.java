package sbnri.consumer.android.webservice;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

public interface ApiCallTags {


    String GET_ALL_NEWS = "getAllNews";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({GET_ALL_NEWS})

    @interface ApiCallIdentifiers {
    }
}
