# Qreact app rate
QReact is an easy-to-use Survey Software for Customers, Employees and Research. You can redirect your users feedback to qreact and get detailed information about your app. For more information please see [the website](https://www.qreact.net)

## Quick start

### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

### Step 2. Add the dependency
```gradle
dependencies {
   implementation 'com.github.qreact:app-rate:Tag'
}
```

### Step 3. Add Qreact rate to your activity class
Including the rate only in one activity is enough.
```java
 QreactRate rate = new QreactRate()
     .setAppTitle(R.string.app_name) // application title
     .setLaunchesUntilPrompt(0) // number of launches until prompt
     .setDaysUntilPrompt(0) // number of days until prompt
     .setTargetLevel(2) // redirects to qreact if rate is smaller than and equal to target level
     .setToken("qreact token") // you should get this one from the website
     .prepare(this); // prepare rate
     
 rate.show();
```

Default conditions of dialog as below:
1. App is launched more than n days later than installation. Default value is zero. Change via setDaysUntilPrompt().
2. App is launched more than n times. Default value is zero. Change via setLaunchesUntilPrompt().
3. App redirects to qreact if rate value smaller than and equal to target level value. Otherwise app redirects user to play store. Default value is zero. Change via setTargetLevel().
4. You should get token from [the website](https://www.qreact.net) to get qreact work. Then set via setToken().

### Step 4. Customize dialog
You can set your description and customize cancel, rate and never buttons.

```java
 QreactDialog dialog = new QreactDialog(this)
     .setAppDescription(R.string.app_name)
     .setButtonsColor(R.color.colorAccent)
     .setRateButtonColor(R.color.colorAccent)
     .setCancelButtonTitle(R.string.app_name);
     
 QreactRate rate = new QreactRate()
     .setDialog(dialog)
     .setAppTitle(R.string.app_name)
     .setLaunchesUntilPrompt(0)
     .setDaysUntilPrompt(0)
     .setTargetLevel(2)
     .setToken("qreact token")
     .prepare(this);
     
 rate.show();
```

# Licence
MIT License
