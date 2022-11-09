package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object URL
     
    /**
     * <p></p>
     */
    public static Object SUPER_USER
     
    /**
     * <p></p>
     */
    public static Object SUPER_PW
     
    /**
     * <p></p>
     */
    public static Object TS_NAME
     
    /**
     * <p></p>
     */
    public static Object TC_NAME
     
    /**
     * <p></p>
     */
    public static Object APPROVER_USER
     
    /**
     * <p></p>
     */
    public static Object APPROVER_PW
     
    /**
     * <p></p>
     */
    public static Object EMAIL_URL
     
    /**
     * <p></p>
     */
    public static Object ORG_HALLMARKHCS
     
    /**
     * <p></p>
     */
    public static Object ORG_CH
     
    /**
     * <p>Profile default : Automation</p>
     */
    public static Object ORG_AUTOMATION
     
    /**
     * <p></p>
     */
    public static Object ROLE_SYSTEM_ADMIN
     
    /**
     * <p></p>
     */
    public static Object ROLE_ORGANIZATION_ADMIN
     
    /**
     * <p></p>
     */
    public static Object COMMON_PW
     
    /**
     * <p></p>
     */
    public static Object SIGNER_USER1
     
    /**
     * <p>Profile default : old:hallmarksigner1@MailTemp.top, hallmarksigner1@yopmail.com</p>
     */
    public static Object SIGNER_MAIL_USER1
     
    /**
     * <p></p>
     */
    public static Object SIGNER_USER2
     
    /**
     * <p>Profile default : old: hallmarksigner2@MailTemp.top,hallmarksigner2@yopmail.com</p>
     */
    public static Object SIGNER_MAIL_USER2
     
    /**
     * <p></p>
     */
    public static Object APPROVER_USER1
     
    /**
     * <p>Profile default : old: appr1@MailTemp.top,appr1@yopmail.com</p>
     */
    public static Object APPROVER_MAIL_USER1
     
    /**
     * <p></p>
     */
    public static Object APPROVER_USER2
     
    /**
     * <p>Profile default : old: appr2@MailTemp.top,appr2@yopmail.com</p>
     */
    public static Object APPROVER_MAIL_USER2
     
    /**
     * <p></p>
     */
    public static Object APPROVER_USER3
     
    /**
     * <p>Profile default : old: appr3@MailTemp.top,appr3@yopmail.com</p>
     */
    public static Object APPROVER_MAIL_USER3
     
    /**
     * <p></p>
     */
    public static Object NOTIFIER_USER1
     
    /**
     * <p>Profile default : old: ntf1@MailTemp.top,ntf1@yopmail.com</p>
     */
    public static Object NOTIFIER_MAIL_USER1
     
    /**
     * <p></p>
     */
    public static Object MAIL_TEMP_TOP_URL
     
    /**
     * <p></p>
     */
    public static Object GMAIL_URL
     
    /**
     * <p></p>
     */
    public static Object GMAIL_PWD
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += TestCaseMain.getParsedValues(RunConfiguration.getOverridingParameters())
    
            URL = selectedVariables['URL']
            SUPER_USER = selectedVariables['SUPER_USER']
            SUPER_PW = selectedVariables['SUPER_PW']
            TS_NAME = selectedVariables['TS_NAME']
            TC_NAME = selectedVariables['TC_NAME']
            APPROVER_USER = selectedVariables['APPROVER_USER']
            APPROVER_PW = selectedVariables['APPROVER_PW']
            EMAIL_URL = selectedVariables['EMAIL_URL']
            ORG_HALLMARKHCS = selectedVariables['ORG_HALLMARKHCS']
            ORG_CH = selectedVariables['ORG_CH']
            ORG_AUTOMATION = selectedVariables['ORG_AUTOMATION']
            ROLE_SYSTEM_ADMIN = selectedVariables['ROLE_SYSTEM_ADMIN']
            ROLE_ORGANIZATION_ADMIN = selectedVariables['ROLE_ORGANIZATION_ADMIN']
            COMMON_PW = selectedVariables['COMMON_PW']
            SIGNER_USER1 = selectedVariables['SIGNER_USER1']
            SIGNER_MAIL_USER1 = selectedVariables['SIGNER_MAIL_USER1']
            SIGNER_USER2 = selectedVariables['SIGNER_USER2']
            SIGNER_MAIL_USER2 = selectedVariables['SIGNER_MAIL_USER2']
            APPROVER_USER1 = selectedVariables['APPROVER_USER1']
            APPROVER_MAIL_USER1 = selectedVariables['APPROVER_MAIL_USER1']
            APPROVER_USER2 = selectedVariables['APPROVER_USER2']
            APPROVER_MAIL_USER2 = selectedVariables['APPROVER_MAIL_USER2']
            APPROVER_USER3 = selectedVariables['APPROVER_USER3']
            APPROVER_MAIL_USER3 = selectedVariables['APPROVER_MAIL_USER3']
            NOTIFIER_USER1 = selectedVariables['NOTIFIER_USER1']
            NOTIFIER_MAIL_USER1 = selectedVariables['NOTIFIER_MAIL_USER1']
            MAIL_TEMP_TOP_URL = selectedVariables['MAIL_TEMP_TOP_URL']
            GMAIL_URL = selectedVariables['GMAIL_URL']
            GMAIL_PWD = selectedVariables['GMAIL_PWD']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
