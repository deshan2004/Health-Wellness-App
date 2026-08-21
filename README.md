# 🌿 Health & Wellness Android Application

[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Language](https://img.shields.io/badge/Language-Kotlin%20%2F%20Java-blue.svg)](https://kotlinlang.org)
[![UI](https://img.shields.io/badge/Design-Material%20Design%203-teal.svg)](https://m3.material.io/)
[![Architecture](https://img.shields.io/badge/Architecture-Single%20Activity%20%2B%20Jetpack%20Navigation-orange.svg)](https://developer.android.com/guide/navigation)

A native Android mobile application designed to help users track personal biometric indicators, discover curated health tips, and follow structured nutritional and wellness guidelines. Engineered with modern Android development best practices, **Single-Activity Architecture**, **Jetpack Navigation Components**, and **ViewBinding**.

---

## 📱 Features & Modules

- **📊 BMI Calculator:** 
  - Calculates Body Mass Index in real time using weight ($kg$) and height ($cm/m$).
  - Automatic WHO category categorization (*Underweight, Normal, Overweight, Obese*).
  - Defensive input sanitization and zero-division protection.
- **💡 Health Tips Feed:** 
  - Dynamic, memory-efficient list powered by `RecyclerView` with custom `ViewHolder` pattern and category chips (*Hydration, Sleep, Cardio, Nutrition*).
- **🥗 Eating Habits & Nutrition:** 
  - Informative guides on macronutrient balancing, meal timings, and balanced diet planning presented via clean `CardView` layouts.
- **🧘 Health & Lifestyle Advice:** 
  - Actionable guidance on mental wellness, stress relief, circadian sleep hygiene, and daily physical benchmarks.

---

## 🏗️ Architecture & Navigation

The app strictly follows Google's **Single-Activity Architecture**:
- **Host Activity:** `MainActivity` with `NavHostFragment`
- **Navigation Pattern:** `BottomNavigationView` synced with Android Jetpack Navigation Graph (`nav_graph.xml`)
- **UI & Lifecycle Management:** `ViewBinding` with zero memory leaks (`_binding = null` in `onDestroyView`)


---

## 🛠️ Tech Stack & Dependencies

- **Language:** Kotlin / Java
- **IDE:** Android Studio
- **Minimum SDK:** API 24 (Android 7.0)
- **Target SDK:** API 34 (Android 14)
- **Components:**
  - Android Jetpack Navigation Component
  - Material Design 3 Components (`com.google.android.material`)
  - AndroidX RecyclerView & CardView
  - Android ViewBinding

---

## 📸 Screenshots

| Home Dashboard | BMI Calculator | Health Tips |
| :---: | :---: | :---: |
| *(Add Screenshot)* | *(Add Screenshot)* | *(Add Screenshot)* |

| Eating Habits | Health Advice |
| :---: | :---: |
| *(Add Screenshot)* | *(Add Screenshot)* |

---

## 🚀 Getting Started

### 1. Clone the repository
```bash
git clone [https://github.com/your-username/health-and-wellness-app.git](https://github.com/your-username/health-and-wellness-app.git)
