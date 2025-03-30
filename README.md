# 🌍 Multi-Currency Exchange  
 
![Kotlin](https://img.shields.io/badge/Kotlin-1.8.0-blueviolet?logo=kotlin)  
![Android](https://img.shields.io/badge/Android-12-brightgreen?logo=android)  
![Architecture](https://img.shields.io/badge/Architecture-Clean%20Architecture-blue)  
![Coroutines](https://img.shields.io/badge/Coroutines-%E2%9A%A1-lightgrey)  
![Hilt](https://img.shields.io/badge/Hilt-DI-yellow)  
![License](https://img.shields.io/badge/License-MIT-green)  
 
## ✨ Overview  
**Multi-Currency Exchange** is an Android application that allows users to:  
- 🔄 Convert **currencies in real-time**.  
- 📈 Retrieve **historical exchange rates** for the last **4 days**.  
- 📶 Work efficiently by **caching** previously fetched exchange rates, reducing unnecessary API calls.  
 
---  
 
## 📸 Screenshots  
 
| Screenshot 1 | Screenshot 2 |
|--------------|--------------|
| ![Screenshot 1](Screenshots/1.jfif) | ![Screenshot 1](Screenshots/2.jfif) |
| ![Screenshot 1](Screenshots/3.jfif) | 
 
---  
 
## 🏗️ Architecture  
 
This project follows **Clean Architecture** to ensure **separation of concerns**, **scalability**, and **maintainability**.  
 
### **Modularization Structure:**  
- **`app`** → Handles UI and Navigation.  
- **`data`** → Repository, API calls, and Local database.  
- **`domain`** → Business logic and use cases.  
 
### **Tech Stack Used:**  
✅ **Kotlin** – Modern programming language for Android.  
✅ **Coroutines & Flow** – Asynchronous programming.  
✅ **Hilt** – Dependency Injection.  
✅ **Retrofit** – Networking.  
✅ **Room** – Local database for caching.  
✅ **Navigation Component** – For screen transitions.  
✅ **DataBinding & ViewBinding** – UI management.  
 
---  
 
## 🚀 Getting Started  
 
### **1️⃣ Clone the Repository**  
```bash  
git clone https://github.com/EslamEmad99/Multi-Currency.git  
cd Multi-Currency  
```  
 
### **2️⃣ Open in Android Studio**  
- Open **Android Studio (latest version recommended)**.  
- Select **"Open an Existing Project"** and choose the cloned folder.  
 
### **3️⃣ Run the App**  
- Build and run on an **Android Emulator** or **physical device**.  
 
---  
 
## 📌 Features  
 
✔️ **Real-time currency conversion**.  
✔️ **Historical exchange rate lookup** (last 4 days).  
✔️ **Offline support with caching**.  
✔️ **Modularized Clean Architecture**.  
✔️ **Optimized network calls using Retrofit & Coroutines**.  
✔️ **Dependency injection with Hilt**.  
✔️ **Smooth navigation using Navigation Component**.  
✔️ **Unit Testing (Fake Repository, ViewModel Tests, Use Case Tests)**.  
 
---  
 
## 🧪 Testing  
 
The project includes:  
- ✅ **Unit tests** for the repository, use cases, and ViewModels.  
- ✅ **Mocking API responses using Fake DataSources**.  
 
Run tests using:  
```bash  
./gradlew testDebugUnitTest  
```  
 
---  
 
## 🎯 Future Improvements  
 
🚀 **Upcoming Enhancements:**  
- 🔹 Add **Lint** for better code quality.  
- 🔹 Integrate **Fastlane** for automated deployment.  
- 🔹 Migrate UI to **Jetpack Compose** for a modern declarative UI.  
 
---  
 
## 📄 License  
 
This project is licensed under the **MIT License** – feel free to use and contribute!  
 
---  
 
🔥 **Enjoy coding!** If you have any feedback, feel free to open an issue or contribute! 🚀  
