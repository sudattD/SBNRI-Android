package sbnri.consumer.android.constants;

import java.util.regex.Pattern;

public interface Constants {


    String CONSUMER_MOBILE_NUMBER = "MobileNumber";
    String CONSUMER_ID = "ConsumerID";
    String BASE_URL = "BaseURL";
    String ACCESS_TOKEN = "AccessToken";

    String FRAG_ID = "fragId";

    String PREFERRED_BANKS_META_DATA = "mPreferredBanksMetaData";
    String OTHERS_BANKS_META_DATA = "mOthersaBanksMetaData";
    String PREFERRED_BANK_LIST = "mPreferredBanksList";
    String OTHERS_BANK_LIST = "mOthersBanksList";
    String ALL_BANKS_LIST = "allBanksList";

    String  SBNRI_USER_OBJ = "UserDetails";


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
