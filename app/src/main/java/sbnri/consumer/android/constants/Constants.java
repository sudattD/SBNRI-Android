package sbnri.consumer.android.constants;

import android.Manifest;

import java.util.regex.Pattern;

public interface Constants {

    int GOOGLE_PLAY_SERVICES = 1;
    String CONSUMER_MOBILE_NUMBER = "MobileNumber";
    String CONSUMER_ID = "ConsumerID";
    String BASE_URL = "BaseURL";
    String ACCESS_TOKEN = "AccessToken";

    int COMPRESSED_IMAGE_WIDTH = 720;

    int REQUEST_PERMISSION_INDIVIDUAL = 71;

    String FRAG_ID = "fragId";

    String PREFERRED_BANKS_META_DATA = "mPreferredBanksMetaData";
    String OTHERS_BANKS_META_DATA = "mOthersaBanksMetaData";
    String PREFERRED_BANK_LIST = "mPreferredBanksList";
    String OTHERS_BANK_LIST = "mOthersBanksList";
    String ALL_BANKS_LIST = "allBanksList";

    String  SBNRI_USER_OBJ = "UserDetails";

    // PERMISSIONS
    String[] CAMERA_PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};


    String ID_TOKEN = "idToken";
    String IS_PUSHED = "isPushed";

    // Network Constants
    int TIMEOUT = 90;

    public static final String EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    ).pattern();
}
