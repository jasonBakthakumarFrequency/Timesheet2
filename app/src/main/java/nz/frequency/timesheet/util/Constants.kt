package nz.frequency.timesheet.util

class Constants {

    companion object{

        const val SHARED_PREFERENCES_KEY_FOR_THE_APP = "FrequencyTimesheet"
        const val READ_USER_API_ADDRESS = "https://frequencytimesheetfunctions.azurewebsites.net/api/readUserData"
        const val READ_USER_DATA_ACCESS_CODE = "VyWAa62iWiPr7o3T1iKu4FDPsf6lKsVmdHb6fmA5yGX8IWDE36F3Cw=="
        const val READ_USER_PARAM_1 = "code"
        const val READ_USER_PARAM_2 = "phonenumber"
        const val PHONE_NUMBER_RESPONSE_DATA = "response_data"
        const val FINISHED_PHONE_NUMBER_ACTIVITY = "phoneNumberActivity"
        const val FINISHED_USER_DETAILS_ACTIVITY = "userDetailsActivity"
        const val USER_ID = "userID"
        const val USER_PHONE = "userPhone"
        const val GENERATE_JWT_API_ADDRESS = "https://frequencytimesheetfunctions.azurewebsites.net/api/generateJWTTokenForTwilioVerification"
        const val GENERATE_WEB_TOKEN_PARAM_1 = "code"
        const val GENERATE_WEB_TOKEN_PARAM_2 = "phonenumber"
        const val GENERATE_WEB_TOKEN_ACCESS_CODE = "VyWAa62iWiPr7o3T1iKu4FDPsf6lKsVmdHb6fmA5yGX8IWDE36F3Cw=="
        const val SINCH_APPLICATION_KEY = "a3697c15-e5ba-4794-948e-7b674688dbb6"

    }

}