package com.miuihooker.miuionesecond;

import android.widget.CheckBox;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Main implements IXposedHookLoadPackage {
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("com.miui.securitycenter")) {
            XposedHelpers.findAndHookMethod("com.miui.permcenter.privacymanager.f$b", loadPackageParam.classLoader, "b", new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    int a = XposedHelpers.getIntField(param.thisObject, "a");
                    if (a > 1) {
                        XposedHelpers.setIntField(param.thisObject, "a", 1);
                    }
                }
            });
            XposedHelpers.findAndHookMethod("com.miui.permcenter.privacymanager.h", loadPackageParam.classLoader, "initData", new XC_MethodHook() {
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    CheckBox checkBox = (CheckBox) XposedHelpers.getObjectField(param.thisObject, "i");
                    checkBox.setChecked(true);
                }
            });
        }
    }
}
