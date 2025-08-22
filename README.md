# Compose Calendar
<img width="330" height="287" alt="스크린샷 2025-08-23 오전 2 14 25" src="https://github.com/user-attachments/assets/dbe5e6de-9f2f-427f-bf9f-384032f76700" />

## Overview

Compose Calendar is a simple and customizable calendar component built using Jetpack Compose for
Android. It provides an easy-to-use calendar view with month navigation and date selection
capabilities. I encourage you to ⭐star⭐ this repository if you find it useful!

## Quick Start
[![](https://jitpack.io/v/lyh990517/Compose-Calendar-Template.svg)](https://jitpack.io/#lyh990517/Compose-Calendar-Template)

If you're using **Groovy DSL**, 

```gradle
//settings.gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            mavenCentral()
            maven { url 'https://jitpack.io' }
        }
}
//app.gradle
dependencies {
    implementation 'com.github.lyh990517:Compose-Calendar-Template:latest-release'
}
```

If you're using **Kotlin DSL**
```kotlin
//settings.gradle.kts
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            google()
            mavenCentral()
            maven(url = "https://jitpack.io")
        }
}
//app.gradle.kts
dependencies {
    implementation("com.github.lyh990517:Compose-Calendar-Template:latest-release")
}
```

## How to Use

### Basic
```kotlin
@Composable
fun BasicCalendar() {
    val calendarState = rememberCalendarState()

    // Listen to date selection
    LaunchedEffect(calendarState.selectedDate) {
        calendarState.selectedDate?.let { selectedDate ->
            // Handle selected date
            println("Selected date: $selectedDate")
        }
    }

    Calendar(
        calendarState = calendarState,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
```

### Customization

```kotlin
@Composable
fun CustomCalendar() {
    val calendarState = rememberCalendarState(
        weekInMonth = 5,  // Number of weeks to display
        daysInWeek = 7    // Number of days per week
    )
    
    // Create your own UI using CalendarState.value
}
```

## Contact
If you have any questions, suggestions, or issues, feel free to reach out to us. You can find our contact information on our GitHub repository.

We appreciate your support and contributions to Compose Calendar!
