<h1 align="center">OtpTextField</h1>
<p align="center">
  Custom Compose OtpTextField that can be used as an OTP field for authentication, PIN code, or any behavior that requires a password
</p>
<p align="center">
<img src="https://github.com/SamirAhmedSaad/OtpTextField/blob/main/demo.gif" width="270" height="500" /> &nbsp;&nbsp;
</p>

## Installation in your Project

Step 1. Add the repository in your root build.gradle:
```java
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2. Add the dependency

```java
dependencies {
        implementation 'com.github.SamirAhmedSaad:OtpTextField:1.0.2' 
}
```

## How to use the library?



```
var otpText by remember { mutableStateOf(TextFieldValue()) }
var cellProperties by remember { mutableStateOf(OtpCellProperties()) }

OtpTextField(
    modifier = Modifier.fillMaxWidth(),
    otpText = otpText,
    otpCellProperties = OtpCellProperties(),
    onValueChange = { otpStatus ->
        when(otpStatus){
	    is OtpStatus.Typing -> otp = otpStatus.otp // update state
	    is OtpStatus.Filled -> // otp detected
	}
    }
)
```
## Customize Otp Cell using OtpCellProperties
```
OtpCellProperties(
    otpLength = 6,
    otpCellSize = 50.dp,
    otpDistanceBetweenCells = 10.dp,
    otpTextStyle = TextStyle(),
    borderWidth = 1.dp,
    borderRound = 8.dp,
    cursorWidth = 2.dp,
    cursorColor = Color.Black,
    isHasError = false,
    isHasCursor = true,
    hint = "-",
    mask = "*"
)
```

| Parameter                   | Use                                                          |
| --------------------------- | ------------------------------------------------------------ |
| otpLength                   | sets the length of otp                                       |
| otpCellSize                 | sets the size(width/height) of otp cell                      |
| otpDistanceBetweenCells     | sets the distance among cells                                |
| otpTextStyle                | sets the text style for otp text                             |
| borderWidth                 | sets the cell border width                                   | 
| isHasError	              | sets if otp cell should has error or not                     |
| isHasCursor                 | sets if otp cell should has cursor or not                    |
| cursorWidth                 | sets cursor width                                            |
| cursorColor                 | sets cursor color                                            |
| hint                        | sets hint for otp cells                                      |
| mask                        | adds visual transformation for entered text                                      |

## Author

Maintained by [Samir Ahmed](https://www.linkedin.com/in/samir-ahmed-hayan)

## Contribution

-   Bug reports and pull requests are welcome.
