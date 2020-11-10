# 2020/11/11작성

# dev 브랜치에서 **ignore** 하고 있는 파일

* /build.gradle

* /app/build.gradle

* /app/src/main/AndroidManifest.xml

* /app/src/main/res/values/string.xml

  (네이티브 앱키가 서로 달라서 바꿔주어야 하는 수고를 피하기 위해..)



# dev 브랜치 사용시 추가해주어야 할 것

## /app/build.gradle 파일

```
android {
	...
	
	//자바 컴파일 옵션
	compileOptions {
		targetCompatibility = "8"
		sourceCompatibility = "8"
	}
}
```



```
dependencies {
	...
	
	//tedPermission (사용자에게 권한 요청시 편리하게 해준다)
	implementation 'gun0912.ted:tedpermission:2.2.3'

}
```



## /app/src/main/AndroidManifest.xml

```
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.BLUETOOTH"/>
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>

<application
	...
	//http 통신을 하기 위해서 추가해주어야 하는 문장
	android:usesCleartextTraffic="true">
	
	...
</application>
```

## /app/src/main/res/values/string.xml

```
<resources>
    <string name="app_name">SleepDog</string>
    <string name="KAKAO_APP_KEY">카카오 네이티브 앱키</string>
</resources>
```

ex)네이티브 앱키 **12345678**의 경우

```
<string name="KAKAO_APP_KEY">12345678</string>
```

