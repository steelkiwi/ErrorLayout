# ErrorLayout
Simple layout to show custom error toast with animation

# View

Toast will arise from bottom of screen to top

![](https://media.giphy.com/media/3og0IDq5JE9OopUPL2/giphy.gif)

or top of screen to bottom

![](https://media.giphy.com/media/l4FGsyU7ctXSoLEIM/giphy.gif)

# Download

Download via Gradle:

```gradle
compile 'com.steelkiwi:error-layout:1.0.0'
```

# Usage:

Add ErrorLayout to your xml layout

```xml
<steelkiwi.com.library.view.ErrorContainer
        android:id="@+id/container"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
```

After that you need include your own layout with views. Than find our ErrorContainer view

```java
ErrorContainer errorContainer = (ErrorContainer) findViewById(R.id.container);
```

# Custom toast view configuration

```java
ToastView toastView = new ToastView.Builder(this)
                .setTextSize(14)
                .setTextColor(Color.WHITE)
                .setTextGravity(Gravity.CENTER)
                .setAnimationType(AnimationType.TOP)
                .setShowDelay(2) // in seconds
                .setBackground(R.drawable.toast_background)
                .build();
```

After that you need only set this toast to the ErrorContainer instance.

```java
errorContainer.setToast(toastView);
```

And that it, now you can show this toast.

# Show toast

```java
loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText = name.getText().toString();
                if(nameText.equals("")) {
                    errorContainer.showError(name, getString(R.string.empty_name_message));
                    return;
                }
        });
```

To show toast you need call method showError(View view, String message) and add  parameters. View, where error happened and message. That all!!

# License

```
Copyright © 2017 SteelKiwi, http://steelkiwi.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```